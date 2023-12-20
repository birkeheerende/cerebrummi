package vokabeltrainer.table.list.editor.expressionkindtable.multiselect;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class ExpressionKindTableModel extends DefaultTableModel
{
   private static final long serialVersionUID = 3445974088132962568L;

   private Vector<Vector<ExpressionKindTableRow>> data;
   private List<ExpressionKindTableRow> rows = new ArrayList<>();

   public ExpressionKindTableModel(Vector<Vector<ExpressionKindTableRow>> data,
         Vector<String> columnNames)
   {
      super(data, columnNames);
      this.data = data;
      for(Vector<ExpressionKindTableRow> datum : data)
      {
         rows.add(datum.get(0));
      }
   }

   public Vector<Vector<ExpressionKindTableRow>> getData()
   {
      return data;
   }

   public List<ExpressionKindTableRow> getSelectedRows()
   {
      List<ExpressionKindTableRow> selectedRows = new ArrayList<>();
      for(ExpressionKindTableRow row : rows)
      {
         if(row.getExpressionKindItem().isSelected())
         {
            selectedRows.add(row);
         }
      }
      return selectedRows;
   }
}
