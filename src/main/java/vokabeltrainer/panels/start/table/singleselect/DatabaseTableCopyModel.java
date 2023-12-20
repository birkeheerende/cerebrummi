package vokabeltrainer.panels.start.table.singleselect;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DatabaseTableCopyModel extends DefaultTableModel
{

   private static final long serialVersionUID = 5442352055546967989L;

   private Vector<Vector<DatabaseTableCopyRow>> data;
   private List<DatabaseTableCopyRow> rows = new ArrayList<>();

   public DatabaseTableCopyModel(Vector<Vector<DatabaseTableCopyRow>> data,
         Vector<String> columnNames)
   {
      super(data, columnNames);
      this.data = data;
      for(Vector<DatabaseTableCopyRow> datum : data)
      {
         rows.add(datum.get(0));
      }
   }

   public Vector<Vector<DatabaseTableCopyRow>> getData()
   {
      return data;
   }
}
