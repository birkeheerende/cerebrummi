package vokabeltrainer.table;

import javax.swing.table.DefaultTableModel;

import vokabeltrainer.types.Expression;

public class ExpressionTableModel extends DefaultTableModel
{
   private static final long serialVersionUID = 2869978472820089697L;

   private Expression[][] data;

   public ExpressionTableModel(Expression[][] data, Object[] columnNames)
   {
      super(data, columnNames);
      this.data = data;
   }

   @Override
   public void fireTableCellUpdated(int row, int column)
   {
      super.fireTableCellUpdated(row, column);
   }

   public Expression[][] getTableData()
   {
      return data;
   }
}
