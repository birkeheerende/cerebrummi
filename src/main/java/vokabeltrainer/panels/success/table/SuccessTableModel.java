package vokabeltrainer.panels.success.table;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class SuccessTableModel extends DefaultTableModel
{
   private static final long serialVersionUID = 5839657636851611176L;

   private Vector<Vector<SuccessTableRow>> data;
   
   public SuccessTableModel(Vector<Vector<SuccessTableRow>> data, Vector<String> columnNames)
   {
      super(data, columnNames);
      this.data = data;
   }

   public Vector<Vector<SuccessTableRow>> getData()
   {
      return data;
   }
}
