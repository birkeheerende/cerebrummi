package vokabeltrainer.panels.dictionary;

import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingWorker;

import vokabeltrainer.common.Data;
import vokabeltrainer.common.SaveExpressions;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public class TrashCanController implements TrashCanControllerConnector
{
   private TrashCanDialog trashCanDialog;
   private DictionaryControllerConnector connector;

   public TrashCanController(DictionaryControllerConnector connector,
         Language initialLanguage)
   {
      this.connector = connector;
      trashCanDialog = new TrashCanDialog(this, initialLanguage);
      trashCanDialog.doShowTable(loadTableModel(initialLanguage));
      trashCanDialog.pack();
   }

   @Override
   public DictionaryControllerConnector getDictionaryControllerConnector()
   {
      return connector;
   }

   public TrashCanDialog getTrashCanDialog()
   {
      return trashCanDialog;
   }

   @Override
   public ExpressionTableModel loadTableModel(Language language)
   {
      return Data.findTranslationsDeletedWords(language);
   }

   @Override
   public void restoreSelectedExpressions(List<Expression> selectedExpressions)
   {
      if (!selectedExpressions.isEmpty())
      {
         trashCanDialog.setRestore(true);
         Data.restoreExpressions(selectedExpressions);
         trashCanDialog.clearTable();
         trashCanDialog.doShowTable(
               loadTableModel(trashCanDialog.getSelectedLanguage()));
         trashCanDialog.tableValidateRepaint();
         save();
      }
   }

   @Override
   public void selectAllExpressionsInTable()
   {
      if (trashCanDialog.isTableNotNull())
      {
         trashCanDialog.selectAllExpressionsInTable();
         trashCanDialog.clearTable();
         trashCanDialog.doShowTable(
               loadTableModel(trashCanDialog.getSelectedLanguage()));
         trashCanDialog.tableValidateRepaint();
      }
   }

   @Override
   public void unselectAllExpressionsInTable()
   {
      if (trashCanDialog.isTableNotNull())
      {
         trashCanDialog.unselectAllExpressionsInTable();
         trashCanDialog.clearTable();
         trashCanDialog.doShowTable(
               loadTableModel(trashCanDialog.getSelectedLanguage()));
         trashCanDialog.tableValidateRepaint();
      }
   }

   @Override
   public void switchLanguage()
   {
      trashCanDialog.clearTable();
      trashCanDialog
            .doShowTable(loadTableModel(trashCanDialog.getSelectedLanguage()));
      trashCanDialog.tableValidateRepaint();
   }

   @Override
   public void save()
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            new SaveExpressions().save();
            return null;
         }
      }.execute();
   }

   @Override
   public void fireTableCellUpdated(JTable table, int selectedRow, int i)
   {
      ((ExpressionTableModel) table.getModel())
      .fireTableCellUpdated(table.getSelectedRow(), 0);
   }
}
