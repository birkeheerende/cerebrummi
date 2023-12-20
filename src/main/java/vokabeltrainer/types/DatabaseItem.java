package vokabeltrainer.types;

import java.util.ArrayList;
import java.util.List;

import vokabeltrainer.common.Settings;
import vokabeltrainer.types.Chapter.Database;

public class DatabaseItem
{
   private Database database;
   private boolean selected;
   private String databaseName = "";
   private String authors = "";
   private String company = "";

   public DatabaseItem(Database database)
   {
      this.database = database;
   }

   public DatabaseItem(Database database, boolean selected)
   {
      this.database = database;
      this.selected = selected;
   }

   public DatabaseItem(DatabaseDescription database)
   {
      this.database = database.getDatabase();
      this.databaseName = database.getDatabaseName();
      this.authors = database.getAuthors();
      this.company = database.getCompany();
   }

   public Database getDatabase()
   {
      return database;
   }

   public void setDatabase(Database database)
   {
      this.database = database;
   }

   public void toggleSelected()
   {
      this.selected = !this.selected;
   }

   public boolean isSelected()
   {
      return selected;
   }

   public void setSelected(boolean selected)
   {
      this.selected = selected;
   }

   public String getDatabaseName()
   {
      return databaseName;
   }

   public void setDatabaseName(String databaseName)
   {
      this.databaseName = databaseName;
   }

   public String getAuthors()
   {
      return authors;
   }

   public void setAuthors(String authors)
   {
      this.authors = authors;
   }

   public String getCompany()
   {
      return company;
   }

   public void setCompany(String company)
   {
      this.company = company;
   }

   public static List<DatabaseItem> getAllAvailableDatabaseItems()
   {
      List<DatabaseItem> databaseItemList = new ArrayList<>();

      for (Database database : Settings.getAvailableDatabases())
      {
         databaseItemList
               .add(new DatabaseItem(database,
                     Settings.isDatabaseChoosen(database)));
      }

      return databaseItemList;
   }
}
