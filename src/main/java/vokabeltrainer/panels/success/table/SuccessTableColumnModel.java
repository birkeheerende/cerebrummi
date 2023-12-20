package vokabeltrainer.panels.success.table;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class SuccessTableColumnModel extends DefaultTableColumnModel
{
   private static final long serialVersionUID = -496621432204186003L;

   private SuccessTableCellRenderer renderer;
   
   public SuccessTableColumnModel()
   {
      renderer = new SuccessTableCellRenderer();
      
      TableColumn column = new TableColumn();
      column.setHeaderValue("ausgewählt");
      column.setCellRenderer(renderer);
      column.setCellEditor(renderer);
      column.setPreferredWidth(100);
      addColumn(column);
      
      TableColumn column1 = new TableColumn();
      column1.setHeaderValue("Wort");
      column1.setCellRenderer(renderer);
      column1.setCellEditor(renderer);
      column1.setPreferredWidth(500);
      addColumn(column1);
      
      TableColumn column2 = new TableColumn();
      column2.setHeaderValue("Kapitel");
      column2.setCellRenderer(renderer);
      column2.setCellEditor(renderer);
      column2.setPreferredWidth(600);
      addColumn(column2);
   }
}
