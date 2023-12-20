package vokabeltrainer.table.list.editor.expressionkindtable.multiselect;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class ExpressionKindTableColumnModel extends DefaultTableColumnModel
{
   private static final long serialVersionUID = -2011627608532876165L;

   private ExpressionKindTableCellRenderer renderer;
   
   public ExpressionKindTableColumnModel(int totalWidth)
   {
      renderer = new ExpressionKindTableCellRenderer();
      
      TableColumn column = new TableColumn();
      column.setHeaderValue("Wahl");
      column.setCellRenderer(renderer);
      column.setCellEditor(renderer);
      column.setPreferredWidth(43);
      addColumn(column);
      
      TableColumn column1 = new TableColumn();
      column1.setHeaderValue("Wortart");
      column1.setCellRenderer(renderer);
      column1.setCellEditor(renderer);
      column1.setPreferredWidth(totalWidth-43);
      addColumn(column1);
   }
}
