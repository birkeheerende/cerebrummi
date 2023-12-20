package vokabeltrainer.panels.start.table.multiselect;

import vokabeltrainer.types.DatabaseItem;

public class DatabaseTableRow
{
   private DatabaseItem database;

   public DatabaseTableRow(DatabaseItem database)
   {
      this.database = database;
   }

   public DatabaseItem getDatabaseItem()
   {
      return database;
   }
}
