package vokabeltrainer.panels.dictionary;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;

import vokabeltrainer.Command;
import vokabeltrainer.common.ApplicationSound;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.SaveExpressions;
import vokabeltrainer.common.Settings;
import vokabeltrainer.panels.DictionaryView;
import vokabeltrainer.panels.notifications.EmptyNotification;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

public class DictionaryController implements DictionaryControllerConnector
{
   private DictionaryViewConnector dictionaryView;
   private Chapter currentChapter;

   public DictionaryController()
   {
      this.dictionaryView = new DictionaryView(this);
      Status.push(Status.OPENED_PAGE);
   }

   @Override
   public void tabbedPaneChanged(int selectedIndex)
   {
      if (selectedIndex == Tabulator.KIND_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.KIND_TAB);
         Status.push(Status.TAB_EXPRESSIONKIND);
         decideOnTableInteraction(Action.TAB_EXPRESSIONKIND);
      }
      else if (selectedIndex == Tabulator.CHAPTER_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.CHAPTER_TAB);
         dictionaryView.unselectExpressionKind();
         Status.push(Status.TAB_CHAPTER);
         decideOnTableInteraction(Action.TAB_CHAPTER);
         dictionaryView.loadChapters();
      }
      else if (selectedIndex == Tabulator.SELECTED_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.SELECTED_TAB);
         dictionaryView.unselectExpressionKind();
         Status.push(Status.TAB_SELECTED_EXPRESSIONS);
         decideOnTableInteraction(Action.TAB_SELECTED_EXPRESSIONS);
      }
      else if (selectedIndex == Tabulator.SEARCH_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.SEARCH_TAB);
         dictionaryView.unselectExpressionKind();
         Status.push(Status.TAB_SEARCH);
         decideOnTableInteraction(Action.TAB_SEARCH);
      }
   }

   public DictionaryViewConnector getDictionaryPanel()
   {
      return dictionaryView;
   }

   @Override
   public void copyAllSelectedExpressions()
   {
      copyStringToClipboard(Data
            .getAllSelectedExpressionsAsString(
                  dictionaryView.getSelectedLanguage()));
   }

   @Override
   public void copyExpressionsOfTable()
   {
      if (dictionaryView.isTableNotNull())
      {
         copyStringToClipboard(dictionaryView.getTableDataToString());
      }
   }

   @Override
   public void copyInTableSelectedExpressions()
   {
      if (dictionaryView.isTableNotNull())
      {
         copyStringToClipboard(dictionaryView.getSelectedTableDataToString());
      }
   }

   private void copyStringToClipboard(String stringToCopy)
   {
      if (checkIfAnythingToCopyWithMessageIfNot(stringToCopy))
      {
         Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
         clipboard.setContents(new StringSelection(stringToCopy), null);
      }
   }

   private boolean checkIfAnythingToCopyWithMessageIfNot(String stringToCopy)
   {
      if (stringToCopy.isBlank())
      {
         JOptionPane
               .showMessageDialog(Common.getjFrame(),
                     "Nur selbst eingegebene Vokabeln\nkönnen kopiert werden.");
         return false;
      }
      return true;
   }

   @Override
   public void unselectTableExpressions()
   {
      if (dictionaryView.isTableNotNull())
      {
         dictionaryView.clearTableDataSelection();
         Status.push(Status.peek());
         decideOnTableInteraction(Action.UNSELECT_TABLE);
      }
   }

   @Override
   public void unselectAllExpressions()
   {
      Data.clearAllSelectedExpressions();
      if (dictionaryView.isTableNotNull())
      {
         Status.push(Status.peek());
         decideOnTableInteraction(Action.UNSELECT_ALL);
      }
   }

   @Override
   public void deleteAllSelectedExpressions()
   {
      List<Expression> list = Data.getAllSelectedExpressions(true);

      if (list.isEmpty())
      {
         dictionaryView.notifyNothingWasSelectedForDeletion();
         return;
      }
      if (dictionaryView.askForDeletionConfirmation(list.size()) == 0)
      {
         Data.deleteExpressions(list);
      }
      if (Tabulator.CHAPTER_TAB.equals(Tabulator.getTabShowing()))
      {
         dictionaryView.loadChapters();
      }
      Status.push(Status.peek());
      decideOnTableInteraction(Action.DELETE_ALL_SELECTED);
      save();
   }

   @Override
   public void deleteInTableSelectedExpressions()
   {
      if (dictionaryView.isTableNotNull())
      {
         List<Expression> list = dictionaryView
               .getInTableSelectedExpressions(true);
         if (list.isEmpty())
         {
            dictionaryView.notifyNothingWasSelectedForDeletion();
            return;
         }
         if (dictionaryView.askForDeletionConfirmation(list.size()) == 0)
         {
            Data.deleteExpressions(list);
         }
         if (Tabulator.CHAPTER_TAB.equals(Tabulator.getTabShowing()))
         {
            dictionaryView.loadChapters();
         }
         Status.push(Status.peek());
         decideOnTableInteraction(Action.DELETE_SELECTED_IN_TABLE);
         save();
      }
      else
      {
         dictionaryView.notifyNothingWasSelectedForDeletion();
      }
   }

   @Override
   public void openTrashCanDialog()
   {
      TrashCanDialog dialog = new TrashCanController(this,
            dictionaryView.getSelectedLanguage()).getTrashCanDialog();
      dialog.setLocationRelativeTo(Common.getjFrame());
      dialog.setVisible(true);
      if (dialog.isRestore())
      {
         Status.push(Status.peek());
         decideOnTableInteraction(Action.WORK_WASTEBIN);
      }
   }

   @Override
   public void selectTableExpressions()
   {
      if (dictionaryView.isTableNotNull())
      {
         dictionaryView.selectTableData();
         Status.push(Status.peek());
         decideOnTableInteraction(Action.SELECT_TABLE);
      }
   }

   @Override
   public void shredderDeletedExpressions()
   {
      if (dictionaryView.askForShredderConfirmation() == 0)
      {
         if (Settings.isSoundOn())
         {
            try
            {
               Clip clip = AudioSystem.getClip();
               clip.open(ApplicationSound.getShredderSound());
               FloatControl volume = (FloatControl) clip
                     .getControl(FloatControl.Type.MASTER_GAIN);
               volume.setValue(Settings.getVolume());
               clip.start();
            }
            catch (LineUnavailableException | IOException e)
            {
               // nothing
            }
         }

         Data.shredderDeletedExpressions();
         save();
      }
   }

   @Override
   public void searchHebrew()
   {
      dictionaryView.clearTable();
      Status.push(Status.SEARCH_WHICH_HEBREW);
      decideOnTableInteraction(Action.SEARCH_WHICH_HEBREW);
   }

   @Override
   public void searchGerman()
   {
      dictionaryView.clearTable();
      Status.push(Status.SEARCH_WHICH_GERMAN);
      decideOnTableInteraction(Action.SEARCH_WHICH_GERMAN);
   }

   public void decideOnTableInteraction(Action action)
   {
      new SwingWorker<Void, Void>()
      {
         private ExpressionTableModel tableModel = null;

         @Override
         protected Void doInBackground() throws Exception
         {
            Status status = Status.pop();
            vokabeltrainer.panels.dictionary.Command commando = Interaction
                  .getCommand(new Interaction(action, status));

            if (commando == null)
            {
               dictionaryView.displayNoTable();
            }
            else
            {
               switch (commando)
               {
               case ERROR:
                  break;
               case NOTHING:
                  break;
               case NO_TABLE:
                  dictionaryView.displayNoTable();
                  break;
               case RESTORE_WHICH_CHAPTER:
                  dictionaryView.selectChapter(currentChapter);
                  break;
               case RESTORE_WHICH_SEARCH_GERMAN:
                  searchGerman();
                  break;
               case RESTORE_WHICH_SEARCH_HEBREW:
                  searchHebrew();
                  break;
               case TABLE_CHAPTER_WHICH:
                  dictionaryView.clearTable();
                  tableModel = Data
                        .findTranslations(dictionaryView.getSelectedLanguage(),
                              null, null, null, currentChapter, null,
                              dictionaryView.getSortNow());
                  dictionaryView.removeChapterListSelectionListener();
                  dictionaryView.selectChapter(currentChapter);
                  dictionaryView.addChapterListSelectionListener();
                  break;
               case TABLE_EXPRESSIONKIND_WHICH:
                  dictionaryView.clearTable();
                  ExpressionKind expressionKind = dictionaryView
                        .getSelectedExpressionKind();
                  if (expressionKind != null)
                  {
                     tableModel = Data
                           .findTranslations(
                                 dictionaryView.getSelectedLanguage(), null,
                                 expressionKind, null, null, null,
                                 dictionaryView.getSortNow());
                  }
                  break;
               case TABLE_SEARCH_WHICH_GERMAN:
                  dictionaryView.clearTable();
                  tableModel = Data
                        .findTranslations(dictionaryView.getSelectedLanguage(),
                              dictionaryView.getSearchPhraseGerman(), null,
                              dictionaryView.getSelectedSearchTypeGerman(),
                              null, null, dictionaryView.getSortNow());
                  break;
               case TABLE_SEARCH_WHICH_HEBREW:
                  dictionaryView.clearTable();
                  tableModel = Data
                        .findTranslations(dictionaryView.getSelectedLanguage(),
                              dictionaryView.getSearchPhraseHebrew(), null,
                              dictionaryView.getSelectedSearchTypeHebrew(),
                              null, null, dictionaryView.getSortNow());
                  break;
               case TABLE_SELECTED_EXPRESSIONS:
                  dictionaryView.clearTable();
                  tableModel = Data
                        .findTranslations(dictionaryView.getSelectedLanguage(),
                              null, null, null, null, Command.ALL_SELECTED,
                              dictionaryView.getSortNow());
               }
            }

            return null;
         }

         @Override
         protected void done()
         {
            if (tableModel == null)
            {
               // nothing
            }
            else if (tableModel.getRowCount() == 0)
            {
               EmptyNotification.display();
               dictionaryView.tablePanelValidateRepaint();
            }
            else
            {
               dictionaryView.doShowTable(tableModel);
            }
         }
      }.execute();
   }

   @Override
   public void switchLanguage(String actionCommand)
   {
      dictionaryView.switchSearchLanguagePanel(actionCommand);
      Status.push(Status.peek());
      decideOnTableInteraction(Action.valueOf(actionCommand));
   }

   @Override
   public void displayChapterWhich(Chapter chapter)
   {
      this.currentChapter = chapter;
      Status.push(Status.CHAPTER_WHICH);
      decideOnTableInteraction(Action.CHAPTER_WHICH);
   }

   @Override
   public void displayExpressionKindWhich()
   {
      Status.push(Status.EXPRESSIONKIND_WHICH);
      decideOnTableInteraction(Action.EXPRESSIONKIND_WHICH);
   }

   @Override
   public void sortTableNow()
   {
      if (dictionaryView.isTableNotNull())
      {
         Status.push(Status.peek());
         decideOnTableInteraction(Action.SORT_NOW);
      }
   }

   @Override
   public void displayTableAfterOpeningPage()
   {
      Status.push(Status.peek());
      decideOnTableInteraction(Action.OPENED_PAGE);
   }

   @Override
   public void moveExpressionsToChapter(String toChapter)
   {
      if (dictionaryView.askForMovingToChapterConfirmation() == 0)
      {
         Data.moveSelectedExpressionsToChapter(toChapter);
         
         SaveExpressions saver = new SaveExpressions();
         saver.save();
         
         Status.push(Status.peek());
         decideOnTableInteraction(Action.MOVE_TO_CHAPTER);
      }
   }
   
   @Override
   public void moveExpressionsToDatabase(String toDatabase)
   {
      if (dictionaryView.askForMovingToDatabaseConfirmation() == 0)
      {
         Data.moveSelectedExpressionsToDatabase(toDatabase);
         
         SaveExpressions saver = new SaveExpressions();
         saver.save();
         
         Status.push(Status.peek());
         decideOnTableInteraction(Action.MOVE_TO_DATABASE);
      }
   }

   @Override
   public void save()
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            if (new SaveExpressions().save())
            {
               if (Tabulator.CHAPTER_TAB.equals(Tabulator.getTabShowing()))
               {
                  dictionaryView.loadChapters();
               }
               Status.push(Status.peek());
               decideOnTableInteraction(Action.SAVE);
            }
            return null;
         }
      }.execute();
   }
   
   @Override
   public void fireTableCellUpdated(JTable table, int selectedRow, int column)
   {
      if (Tabulator.SELECTED_TAB.equals(Tabulator.getTabShowing()))
      {
         Status.push(Status.peek());
         decideOnTableInteraction(Action.UNSELECT_EXPRESSION);
      }
      else
      {
         ((ExpressionTableModel) table.getModel())
         .fireTableCellUpdated(table.getSelectedRow(), 0);
      }
   }
}
