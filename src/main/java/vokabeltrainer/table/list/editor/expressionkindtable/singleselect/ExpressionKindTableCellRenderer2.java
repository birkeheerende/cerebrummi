package vokabeltrainer.table.list.editor.expressionkindtable.singleselect;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKindItem;

public class ExpressionKindTableCellRenderer2 extends DefaultTableCellRenderer
      implements TableCellEditor
{
   private static final long serialVersionUID = 1516036848858702294L;
   
   private JLabel selected;
   private JLabel empty;
   private JLabel expressionKind;

   public ExpressionKindTableCellRenderer2() // one selection only
   {
      selected = new JLabel(new ImageIcon(ApplicationImages.getSelectDone()));

      empty = new JLabel();

      expressionKind = new JLabel();
      expressionKind.setFont(ApplicationFonts.getGermanFont(14F));
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
      ExpressionKindItem expressionKind = ((ExpressionKindTableRow2) value)
            .getExpressionKind();
      
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