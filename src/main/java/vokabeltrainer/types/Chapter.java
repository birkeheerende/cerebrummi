package vokabeltrainer.types;

import java.text.Collator;
import java.util.Locale;
import java.util.Vector;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.start.table.multiselect.DatabaseTableModel;
import vokabeltrainer.panels.start.table.multiselect.DatabaseTableRow;
import vokabeltrainer.panels.start.table.singleselect.DatabaseTableCopyModel;
import vokabeltrainer.panels.start.table.singleselect.DatabaseTableCopyRow;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public class Chapter implements Comparable<Chapter>
{
   private String name = "";
   private DatabaseDescription databaseDescription = new DatabaseDescription();

   public Chapter()
   {

   }

   public Chapter(Database origin)
   {
      databaseDescription.setDatabase(origin);
   }

   public Chapter(String name, Database origin)
   {
      this.name = name;
      databaseDescription.setDatabase(origin);
   }

   public Chapter(String databaseName, String name, Database origin)
   {
      databaseDescription.setDatabaseName(databaseName.strip());
      this.name = name;
      databaseDescription.setDatabase(origin);
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public Database getOrigin()
   {
      return databaseDescription.getDatabase();
   }

   public void setOrigin(Database origin)
   {
      databaseDescription.setDatabase(origin);
   }

   public DatabaseDescription getDatabaseDescription()
   {
      return databaseDescription;
   }

   @Override
   public int compareTo(Chapter o)
   {
      if (this.equals(o))
      {
         return 0;
      }
      Collator coll = Collator.getInstance(Locale.GERMAN);
      coll.setStrength(Collator.PRIMARY);
      return coll.compare(this.name, o.name);
   }

   

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((databaseDescription == null) ? 0
            : databaseDescription.hashCode());
      result = prime * result + ((name == null) ? 0 : name.hashCode());
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
      Chapter other = (Chapter) obj;
      if (databaseDescription == null)
      {
         if (other.databaseDescription != null)
            return false;
      }
      else if (!databaseDescription.equals(other.databaseDescription))
         return false;
      if (name == null)
      {
         if (other.name != null)
            return false;
      }
      else if (!name.equals(other.name))
         return false;
      return true;
   }

   public String getDatabaseFolder(Database database)
   {
      return database.getFolder();
   }

   public String getDatabaseName()
   {
      if (Database.IMPORTED == databaseDescription.getDatabase()
            || Database.SELF == databaseDescription.getDatabase()
            || Database.UNKNOWN == databaseDescription.getDatabase())
      {
         return databaseDescription.getDatabaseName();
      }
      return databaseDescription.getDatabase().getName();
   }

   public void setDatabaseName(String databaseName)
   {
      if (Database.IMPORTED == databaseDescription.getDatabase()
            || Database.SELF == databaseDescription.getDatabase()
            || Database.UNKNOWN == databaseDescription.getDatabase())
      {
         this.databaseDescription.setDatabaseName(databaseName);
      }
   }

   public enum Database
   {
      GRUNDWORTSCHATZADAADAMA(
    		"grundwortschatz",
            "Grundwortschatz",
            "630 Vokabeln",
            "Neuhebräisch"),
      SELF(
            "",
            "",
            "",
            ""),
      COPY(
            "",
            "Kopie",
            "",
            ""),
      IMPORTED(
            "",
            "importiert",
            "",
            ""),
      UNKNOWN(
            "",
            "unbekannt",
            "",
            ""),
      TO_BE_DETERMINED(
            "",
            "soll bestimmt werden",
            "",
            "");

      private String folder;
      private String name;
      private String authors;
      private String company;

      Database(String folder, String name, String authors, String company)
      {
         this.folder = folder;
         this.name = name;
         this.authors = authors;
         this.company = company;
      }

      public String getFolder()
      {
         return folder;
      }

      public String getName()
      {
         if(this == Database.SELF)
         {
            Translator translator = Common.getTranslator();
            return translator.realisticTranslate(Translation.SELBST_EINGEGEBEN);
         }
         return name;
      }

      public static DatabaseTableModel getModelAvailableDatabases()
      {
         Vector<Vector<DatabaseTableRow>> data = new Vector<>();
         for (DatabaseItem item : DatabaseItem.getAllAvailableDatabaseItems())
         {
            Vector<DatabaseTableRow> row = new Vector<>();
            row.add(new DatabaseTableRow(item));
            data.add(row);
         }
         Vector<String> columnNames = new Vector<>();
         columnNames.add("eins");
         return new DatabaseTableModel(data, columnNames);
      }
      
      public static DatabaseTableCopyModel getModelCopyAvailableDatabases()
      {
         Vector<Vector<DatabaseTableCopyRow>> data = new Vector<>();
         for (DatabaseItem item : DatabaseItem.getAllAvailableDatabaseItems())
         {
            Vector<DatabaseTableCopyRow> row = new Vector<>();
            row.add(new DatabaseTableCopyRow(item));
            data.add(row);
         }
         Vector<String> columnNames = new Vector<>();
         columnNames.add("eins");
         return new DatabaseTableCopyModel(data, columnNames);
      }

      public String getAuthors()
      {
         return authors;
      }

      public String getCompany()
      {
         return company;
      }
   }

   public static Database findOrigin(String databaseName)
   {
      if (Database.GRUNDWORTSCHATZADAADAMA.getName().equals(databaseName))
      {
         return Database.GRUNDWORTSCHATZADAADAMA;
      }
      return Database.SELF;
   }
}
