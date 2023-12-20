package vokabeltrainer.panels.start.table.singleselect;

import vokabeltrainer.types.DatabaseItem;

public class DatabaseTableCopyRow
{
   private DatabaseItem database;

   public DatabaseTableCopyRow(DatabaseItem database)
   {
      this.database = database;
   }

   public DatabaseItem getDatabaseItem()
   {
      return database;
   }
}
