package vokabeltrainer.table;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import vokabeltrainer.types.Expression;

public class SelectAction extends AbstractAction
{
   private static final long serialVersionUID = 664304155083309795L;
   ExpressionTable table;

   public SelectAction(ExpressionTable table)
   {
      this.table = table;
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      int selectedRow = table.getSelectedRow();
      if (selectedRow >= 0)
      {
         Expression expression = ((Expression) table.getValueAt(selectedRow,
               0));

         expression.setSelected(!expression.isSelected());

         ((ExpressionTableModel) table.getModel())
               .fireTableCellUpdated(selectedRow, 0);
      }

   }

}
