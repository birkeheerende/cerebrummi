package vokabeltrainer.panels.success.table;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.types.Expression;

public class SuccessTable extends JTable
{
   private static final long serialVersionUID = 853537882592595897L;

   public SuccessTable(SuccessTableModel model)
   {
      super(model, new SuccessTableColumnModel());
      this.setShowVerticalLines(false);
      setOpaque(false);
      setRowHeight(75);
      setShowHorizontalLines(true);
      setBackground(ApplicationColors.getTransparent());
      this.setRowSelectionAllowed(false);
      this.setColumnSelectionAllowed(false);
      this.setCellSelectionEnabled(false);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      
      addMouseListener(new MouseAdapter()
      {
         public void mousePressed(MouseEvent mouseEvent)
         {
            JTable table = (JTable) mouseEvent.getSource();
            Point point = mouseEvent.getPoint();
            int row = table.rowAtPoint(point);
            if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1
                  && row == table.getSelectedRow())
            {
               SuccessTableRow tableRow = ((SuccessTableRow) table
                     .getValueAt(table.getSelectedRow(), 0));
               
               Expression expression = tableRow.getExpression();

               expression.toggleSelected();

               ((SuccessTableModel) table.getModel())
                     .fireTableCellUpdated(table.getSelectedRow(), 0);
            }
         }
      });
   }

}
