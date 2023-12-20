package vokabeltrainer.table.list.editor.expressionkindtable.multiselect;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKindItem;

public class ExpressionKindTableCellRenderer implements TableCellRenderer, TableCellEditor
{

   private JLabel selected;
   private JLabel empty;
   private JLabel expressionKind;

   public ExpressionKindTableCellRenderer() // multiple selection possible, except unknown selection row 0
   {
      selected = new JLabel(new ImageIcon(ApplicationImages.getSelectDone()));
      selected.setBackground(ApplicationColors.getTransparent());
      selected.setOpaque(false);
      
      empty = new JLabel();
      empty.setBackground(ApplicationColors.getTransparent());
      empty.setOpaque(false);

      expressionKind = new JLabel();
      expressionKind.setFont(ApplicationFonts.getGermanFont(14F));
      expressionKind.setBackground(ApplicationColors.getTransparent());
      expressionKind.setOpaque(false);
   }

   @Override
   public Object getCellEditorValue()
   {
      return null;
   }

   @Override
   public boolean isCellEditable(EventObject anEvent)
   {
      return false;
   }

   @Override
   public boolean shouldSelectCell(EventObject anEvent)
   {
      return false;
   }

   @Override
   public boolean stopCellEditing()
   {
      return false;
   }

   @Override
   public void cancelCellEditing()
   {

   }

   @Override
   public void addCellEditorListener(CellEditorListener l)
   {

   }

   @Override
   public void removeCellEditorListener(CellEditorListener l)
   {

   }

   @Override
   public Component getTableCellEditorComponent(JTable table, Object value,
         boolean isSelected, int row, int column)
   {
      return null;
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
         boolean isSelected, boolean hasFocus, int row, int column)
   {
      ExpressionKindItem expressionKind = ((ExpressionKindTableRow) value).getExpressionKindItem();
      
      if(row == 0 && expressionKind.isSelected())
      {
         for (int i = 1; i < table.getRowCount(); i++)
         {
            ExpressionKindTableRow rowValue = (ExpressionKindTableRow) table.getValueAt(i, 1);
            rowValue.getExpressionKindItem().setSelected(false);
            table.setValueAt(rowValue, i, 1);
         }
      }
      else if(row >  0 && expressionKind.isSelected() && expressionKind.getKind().equals(ExpressionKind.TEXT))
      {
    	  for (int i = 0; i < table.getRowCount(); i++)
          {
             ExpressionKindTableRow rowValue = (ExpressionKindTableRow) table.getValueAt(i, 1);
             if(!rowValue.getExpressionKindItem().getKind().equals(ExpressionKind.TEXT))
             {
            	 rowValue.getExpressionKindItem().setSelected(false);
             }
             table.setValueAt(rowValue, i, 1);
          }
      }
      else if(row >  0 && expressionKind.isSelected())
      {
         ExpressionKindTableRow rowValue = (ExpressionKindTableRow) table.getValueAt(0, 1);
         rowValue.getExpressionKindItem().setSelected(false);
         table.setValueAt(rowValue, 0, 1);
         for (int i = 0; i < table.getRowCount(); i++)
         {
            rowValue = (ExpressionKindTableRow) table.getValueAt(i, 1);
            if(rowValue.getExpressionKindItem().getKind().equals(ExpressionKind.TEXT))
            {
           	 rowValue.getExpressionKindItem().setSelected(false);
            }
            table.setValueAt(rowValue, i, 1);
         }
      }
      
      if (column == 0)
      {
         if (expressionKind.isSelected())
         {
            return selected;
         }
         else
         {
            return empty;
         }
      }

      this.expressionKind.setText(expressionKind.getKind().toString());
      return this.expressionKind;
   }

}
