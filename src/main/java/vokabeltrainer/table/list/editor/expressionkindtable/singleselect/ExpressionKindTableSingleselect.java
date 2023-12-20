package vokabeltrainer.table.list.editor.expressionkindtable.singleselect;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.panels.dictionary.DictionaryControllerConnector;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKindItem;

public class ExpressionKindTableSingleselect extends JTable
{
   private static final long serialVersionUID = 4773612153340762368L;

   private DictionaryControllerConnector connector;

   public ExpressionKindTableSingleselect(ExpressionKindTableModel2 model,
         int totalWidth, DictionaryControllerConnector connector)
   {
      super(model, new ExpressionKindTableColumnModel2(totalWidth));
      this.connector = connector;
      this.setShowVerticalLines(false);
      setOpaque(true);
      setRowHeight(30);
      setShowHorizontalLines(true);
      this.setRowSelectionAllowed(true);
      this.setColumnSelectionAllowed(false);
      this.setCellSelectionEnabled(false);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      this
            .setBorder(BorderFactory
                  .createLineBorder(ApplicationColors.getDarkGold()));
      this.setTableHeader(null);

      addMouseListener(new MouseAdapter()
      {
         public void mousePressed(MouseEvent mouseEvent)
         {
            JTable table = (JTable) mouseEvent.getSource();
            Point point = mouseEvent.getPoint();
            int row = table.rowAtPoint(point);
            if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1
                  && row == table.getSelectedRow())
            {
               ExpressionKindTableRow2 tableRow = ((ExpressionKindTableRow2) table
                     .getValueAt(table.getSelectedRow(), 0));

               ExpressionKindItem expressionKind = tableRow.getExpressionKind();

               expressionKind.toggleSelected();
               if (expressionKind.isSelected())
               {
                  for (int i = 0; i < table.getRowCount(); i++)
                  {
                     ExpressionKindItem kind = ((ExpressionKindTableRow2) table
                           .getValueAt(i, 0)).getExpressionKind();
                     if (i != row && kind.isSelected())
                     {
                        kind.toggleSelected();
                        ((ExpressionKindTableModel2) table.getModel())
                              .fireTableCellUpdated(i, 0);
                     }
                  }
                  connector.displayExpressionKindWhich();
               }

               ((ExpressionKindTableModel2) table.getModel())
                     .fireTableCellUpdated(table.getSelectedRow(), 0);
            }
         }
      });
   }

   public void redisplaySelectedExpressionKindIfAny()
   {
      for (int i = 0; i < getRowCount(); i++)
      {
         ExpressionKindItem kind = ((ExpressionKindTableRow2) getValueAt(i, 0))
               .getExpressionKind();
         if (kind.isSelected())
         {
            connector.displayExpressionKindWhich();
            break;
         }
      }
   }

   @Override
   public ExpressionKindTableModel2 getModel()
   {
      return (ExpressionKindTableModel2) super.getModel();
   }

   @Override
   public Class<?> getColumnClass(int column)
   {
      return JLabel.class;
   }
}
