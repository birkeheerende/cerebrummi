package vokabeltrainer.panels.start.table.multiselect;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DatabaseTableModel extends DefaultTableModel
{

   private static final long serialVersionUID = 5442352055546967989L;

   private Vector<Vector<DatabaseTableRow>> data;
   private List<DatabaseTableRow> rows = new ArrayList<>();

   public DatabaseTableModel(Vector<Vector<DatabaseTableRow>> data,
         Vector<String> columnNames)
   {
      super(data, columnNames);
      this.data = data;
      for(Vector<DatabaseTableRow> datum : data)
      {
         rows.add(datum.get(0));
      }
   }

   public Vector<Vector<DatabaseTableRow>> getData()
   {
      return data;
   }

   public List<DatabaseTableRow> getSelectedRows()
   {
      List<DatabaseTableRow> selectedRows = new ArrayList<>();
      for(DatabaseTableRow row : rows)
      {
         if(row.getDatabaseItem().isSelected())
         {
            selectedRows.add(row);
         }
      }
      return selectedRows;
   }
   
   
}
