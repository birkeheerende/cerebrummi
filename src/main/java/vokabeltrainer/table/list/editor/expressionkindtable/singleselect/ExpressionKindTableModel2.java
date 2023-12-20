package vokabeltrainer.table.list.editor.expressionkindtable.singleselect;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class ExpressionKindTableModel2 extends DefaultTableModel
{
   private static final long serialVersionUID = -3671221891329671632L;
   
   private Vector<Vector<ExpressionKindTableRow2>> data;

   public ExpressionKindTableModel2(Vector<Vector<ExpressionKindTableRow2>> data,
         Vector<String> columnNames)
   {
      super(data, columnNames);
      this.data = data;
   }

   public Vector<Vector<ExpressionKindTableRow2>> getData()
   {
      return data;
   }
}
