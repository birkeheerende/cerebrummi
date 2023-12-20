package vokabeltrainer.panels.success.table;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.panels.success.table.list.SuccessList;
import vokabeltrainer.types.Expression;

public class SuccessTableCellRenderer
      implements TableCellRenderer, TableCellEditor
{
   private JLabel selected;
   private JLabel empty;
   private JLabel chapter;
   
   private SuccessList content;

   public SuccessTableCellRenderer()
   {
      selected = new JLabel(new ImageIcon(ApplicationImages.getSelect()));
      selected.setBackground(ApplicationColors.getVeryLightGold());
      selected.setOpaque(true);
      
      empty = new JLabel();
      empty.setBackground(ApplicationColors.getVeryLightGold());
      empty.setOpaque(true);

      content = new SuccessList();
      content.setBackground(ApplicationColors.getVeryLightGold());
      content.setOpaque(true);;
      
      chapter = new JLabel();
      chapter.setFont(ApplicationFonts.getGermanFont(20));
      chapter.setBackground(ApplicationColors.getVeryLightGold());
      chapter.setOpaque(true);
      chapter.setHorizontalAlignment(SwingConstants.CENTER);
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
      Expression expression = ((SuccessTableRow) value).getExpression();

      if (column == 0)
      {
         if (expression.isSelected())
         {
            return selected;
         }
         else
         {
            return empty;
         }
      }

      if(column == 1)
      {
         content.setListData(expression.getGermanHebrewGrammarArrayForSuccess());
         return this.content;
      }
      
      chapter.setText(expression.getChapter().getName());
      return chapter;
   }

}
