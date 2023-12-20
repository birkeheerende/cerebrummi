package vokabeltrainer.types;

import vokabeltrainer.types.Chapter.Database;

public class DatabaseDescription implements Comparable<DatabaseDescription>
{
   private Database database = Database.TO_BE_DETERMINED;
   private String databaseName = "";
   private String authors = "";
   private String company = "";

   public DatabaseDescription()
   {
      
   }
   
   public DatabaseDescription(Database database)
   {
      this.database = database;
      databaseName = database.getName();
      authors = database.getAuthors();
      company = database.getCompany();
   }
   
   public Database getDatabase()
   {
      return database;
   }

   public void setDatabase(Database database)
   {
      this.database = database;
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

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((authors == null) ? 0 : authors.hashCode());
      result = prime * result + ((company == null) ? 0 : company.hashCode());
      result = prime * result + ((database == null) ? 0 : database.hashCode());
      result = prime * result
            + ((databaseName == null) ? 0 : databaseName.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      DatabaseDescription other = (DatabaseDescription) obj;
      if (authors == null)
      {
         if (other.authors != null)
            return false;
      }
      else if (!authors.equals(other.authors))
         return false;
      if (company == null)
      {
         if (other.company != null)
            return false;
      }
      else if (!company.equals(other.company))
         return false;
      if (database != other.database)
         return false;
      if (databaseName == null)
      {
         if (other.databaseName != null)
            return false;
      }
      else if (!databaseName.equals(other.databaseName))
         return false;
      return true;
   }

   @Override
   public int compareTo(DatabaseDescription o)
   {
      if(this.getDatabaseName().equals(Database.SELF.getName()))
      {
         return 1;
      }
      if(o.getDatabaseName().equals(Database.SELF.getName()))
      {
         return -1;
      }
      return this.databaseName.compareTo(o.databaseName);
   }

}
