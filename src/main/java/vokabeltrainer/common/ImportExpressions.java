package vokabeltrainer.common;

import java.util.UUID;

import javax.swing.ProgressMonitor;

public final class ImportExpressions
{
   public boolean importExpressions(String databaseName,
         boolean overwriteDatabaseNames, String databasePath)
   {
      ProgressMonitor bar = new ProgressMonitor(null,
            "Die Daten werden geladen.", "", 0, 100);
      int progress = 0;
      bar.setProgress(progress);
      bar.setMillisToPopup(1000);
      bar.setMillisToDecideToPopup(1000);

      UUID uuidSearchLock = UUID.randomUUID();
      try
      {
         if (Data.lockDataBase(uuidSearchLock))
         {
            return (Data.importDatabase(databasePath, databaseName,
                  overwriteDatabaseNames));
         }
         return false;
      }
      catch (Exception e)
      {

      }
      finally
      {
         Data.unlockDataBase(uuidSearchLock);
      }
      return false;
   }

}
