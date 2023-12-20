package vokabeltrainer.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.prefs.Preferences;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;

import vokabeltrainer.PathAndFile;
import vokabeltrainer.panels.notifications.OkayExpressionsSavedNotification;
import vokabeltrainer.types.Expression;
import vokabeltrainer.cmd.DirectoryHelper;

public final class SaveExpressions
{
   private static final String HEADER_CSV = "UUID\tUrsprung\tDatenbank\tchapter\tGerman\teinfach\tHebrew\tPlene\tDefektiv\texpression kinds\tgender\tnumerus\tgrammatical person\tbinjan\tverb time\tzusätzliche Informationen\tsearchwords German\tsearchwords Hebrew\tletzte Änderung\tSortierindex";
   private int counter;
   private PathAndFile exportpath;
   private boolean takeSelectedOnlyIntoAccount;
   private boolean takeOriginIntoAccount;
   private String origin;
   private boolean overwriteDatabaseNames;
   private String databaseName;

   public SaveExpressions()
   {
      // for regular saving
   }

   public SaveExpressions(PathAndFile exportpath)
   {
      this.exportpath = exportpath; // for exports
   }

   public void export(String databaseName, boolean overwriteDatabaseNames)
   {
      this.databaseName = databaseName;
      this.overwriteDatabaseNames = overwriteDatabaseNames;
      exportAsZip();
   }

   public void export(String databaseName, boolean overwriteDatabaseNames,
         boolean b)
   {
      this.databaseName = databaseName;
      this.overwriteDatabaseNames = overwriteDatabaseNames;
      this.takeSelectedOnlyIntoAccount = true;
      exportAsZip();
   }

   public void export(String databaseName, boolean overwriteDatabaseNames,
         String databaseChoosen)
   {
      this.databaseName = databaseName;
      this.overwriteDatabaseNames = overwriteDatabaseNames;
      this.takeOriginIntoAccount = true;
      this.origin = databaseChoosen;
      exportAsZip();
   }

   public boolean save()
   {
      ProgressMonitor bar = new ProgressMonitor(Common.getjFrame(),
            "Die Daten werden gespeichert.", "", 0, 2900);
      int progress = 0;
      bar.setProgress(progress);
      bar.setMillisToPopup(1);
      bar.setMillisToDecideToPopup(1);

      UUID uuidSearchLock = UUID.randomUUID();
      if (Data.lockDataBase(uuidSearchLock))
      {
         try
         {
            counter = 0;
            if (this.exportpath == null)
            {
               File customDir = new File(Settings.getExpressionPathFolder());
               if (!customDir.exists())
               {
                  if (!DirectoryHelper.makeDirectory(customDir))
                  {
                     JOptionPane.showMessageDialog(Common.getjFrame(),
                           "Es hat beim Speichern einen Fehler gegeben.\n"
                                 + "Wählen Sie einen anderen Speicherort.",
                           "Fehler", JOptionPane.ERROR_MESSAGE);
                  }
               }
            }
            else
            {
               return false;
            }
            for (LetterForSaving letter : LetterForSaving.values())
            {
               save(letter);
               synchronized (bar)
               {
                  progress += 100;
                  bar.setProgress(progress);
                  bar.notify();
                  Common.getjFrame().validate();
                  Common.getjFrame().repaint();
               }
            }

            Preferences preferences = Preferences.userRoot()
                  .node(CerebrummiNodes.getNode());
            preferences.putInt(CerebrummiNodes.getExpressionNode(), counter);
            saveDeletedExpressions();
            Data.integrateNewExpressions();
            synchronized (bar)
            {
               progress += 100;
               bar.setProgress(progress);
               bar.notify();
               Common.getjFrame().validate();
               Common.getjFrame().repaint();
               bar.close();
               bar.notify();
               Common.getjFrame().validate();
               Common.getjFrame().repaint();
            }
            OkayExpressionsSavedNotification.display();
            return true;
         }
         catch (Exception e)
         {
            JOptionPane
                  .showMessageDialog(Common.getjFrame(),
                        "Es hat beim Speichern einen Fehler gegeben.\n"
                              + e.getMessage(),
                        "Fehler", JOptionPane.ERROR_MESSAGE);
         }
         finally
         {
            Data.unlockDataBase(uuidSearchLock);
         }
      }
      return false;
   }
   
   private boolean exportAsZip()
   {
      ProgressMonitor bar = new ProgressMonitor(null,
            "Die Daten werden gespeichert.", "", 0, 100);
      int progress = 0;
      bar.setProgress(progress);
      bar.setMillisToPopup(1000);
      bar.setMillisToDecideToPopup(1000);

      UUID uuidSearchLock = UUID.randomUUID();
      if (Data.lockDataBase(uuidSearchLock))
      {
         try
         {
            File f = new File(this.exportpath.getPathFileWithZipTest());
            try (ZipOutputStream out = new ZipOutputStream(
                  new FileOutputStream(f), StandardCharsets.UTF_8))
            {
               for (LetterForSaving letter : LetterForSaving.values())
               {
                  ZipEntry entry = new ZipEntry(letter.name() + ".csv");
                  out.putNextEntry(entry);

                  byte[] data = saveAsStringForZip(letter).getBytes(StandardCharsets.UTF_8);
                  out.write(data, 0, data.length);
                  out.closeEntry();

                  progress += 100 / LetterForSaving.values().length;
                  bar.setProgress(progress);
               }
            }
            catch (Exception e)
            {
               JOptionPane.showMessageDialog(Common.getjFrame(),
                     "Es hat beim Export der ZipDatei einen Fehler gegeben.\n"
                           + e.getMessage(),
                     "Fehler", JOptionPane.ERROR_MESSAGE);
               return false;
            }

            progress = 100;
            bar.setProgress(progress);
            OkayExpressionsSavedNotification.display();
            return true;
         }
         catch (Exception e)
         {
            JOptionPane
                  .showMessageDialog(Common.getjFrame(),
                        "Es hat beim Export einen Fehler gegeben.\n"
                              + e.getMessage(),
                        "Fehler", JOptionPane.ERROR_MESSAGE);
         }
         finally
         {
            Data.unlockDataBase(uuidSearchLock);
         }
      }
      return false;
   }

   private String saveAsStringForZip(LetterForSaving letter) throws IOException
   {
      StringJoiner joiner = new StringJoiner("\n");
      joiner.add(HEADER_CSV);
      for (Expression expression : getValues(letter))
      {
         if (isMarkedandMarked(expression) || isOriginandOrigin(expression)
               || isAll())
         {
            if (overwriteDatabaseNames && databaseName != null)
            {
               joiner.add(
                     expression.getExpressionPrintLineForSaving(databaseName));
            }
            else
            {
               joiner.add(expression.getExpressionPrintLineForSaving());
            }
         }
      }
      return joiner.toString();
   }

   private void saveDeletedExpressions() throws IOException
   {
      File file;
      if (exportpath == null)
      {
         file = new File(Settings.getExpressionPathFolder() + File.separator
               + "DELETED.csv");
      }
      else
      {
         return;
      }

      FileOutputStream stream = new FileOutputStream(file);
      OutputStreamWriter writer = new OutputStreamWriter(stream,
            StandardCharsets.UTF_8);
      StringJoiner joiner = new StringJoiner("\n");
      joiner.add(HEADER_CSV);
      for (Expression expression : Data.getDeletedMapValues())
      {
         if (isMarkedandMarked(expression) || isOriginandOrigin(expression)
               || isAll())
         {
            joiner.add(expression.getExpressionPrintLineForSaving());
         }
      }
      writer.write(joiner.toString());
      writer.flush();
      writer.close();
   }

   private boolean isAll()
   {
      return !takeSelectedOnlyIntoAccount && !takeOriginIntoAccount;
   }

   private boolean isOriginandOrigin(Expression expression)
   {
      return takeOriginIntoAccount
            && expression.getChapter().getDatabaseName().equals(origin);
   }

   private boolean isMarkedandMarked(Expression expression)
   {
      return takeSelectedOnlyIntoAccount && expression.isSelected();
   }

   private void save(LetterForSaving letter) throws IOException
   {
      File file;
      if (exportpath == null)
      {
         file = new File(Settings.getExpressionPathFolder() + File.separator
               + letter.name() + ".csv");
      }
      else
      {
         return;
      }
      FileOutputStream stream = new FileOutputStream(file);
      OutputStreamWriter writer = new OutputStreamWriter(stream,
            StandardCharsets.UTF_8);
      StringJoiner joiner = new StringJoiner("\n");
      joiner.add(HEADER_CSV);
      for (Expression expression : getValues(letter))
      {
         if (isMarkedandMarked(expression) || isOriginandOrigin(expression)
               || isAll())
         {
            if (overwriteDatabaseNames && databaseName != null)
            {
               joiner.add(
                     expression.getExpressionPrintLineForSaving(databaseName));
            }
            else
            {
               joiner.add(expression.getExpressionPrintLineForSaving());
            }
            counter++;
         }
      }
      writer.write(joiner.toString());
      writer.flush();
      writer.close();
   }

   private Collection<Expression> getValues(LetterForSaving letter)
   {
      List<Expression> list = new ArrayList<>();

      for (Expression expression : Data.getAlleMapValues())
      {
         if (expression.isDoNotChange())
         {
            continue;
         }
         if (expression.getLetterForSaving().equals(letter))
         {

            list.add(expression);
         }
      }

      for (Expression expression : Data.getNewMapValues())
      {
         if (expression.getLetterForSaving().equals(letter))
         {
            list.add(expression);
         }
      }

      return list;
   }
}
