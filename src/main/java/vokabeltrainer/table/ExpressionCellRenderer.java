package vokabeltrainer.table;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.table.list.ExpressionList;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public class ExpressionCellRenderer
      implements TableCellRenderer, TableCellEditor
{
   private ExpressionList list;
   private Language language;

   public ExpressionCellRenderer(Language language)
   {
      this.language = language;
      list = new ExpressionList(language);
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
         boolean isSelected, boolean hasFocus, int row, int column)
   {
      Expression expression = (Expression) value;
      if (Language.GERMAN_TO_HEBREW.equals(language))
      {
         list.setListData(expression.toGermanArrayForTableEntry());
      }
      else
      {
         list.setListData(expression.toHebrewArrayForTableEntry());
      }

      if (isSelected)
      {
         list.setBorder(BorderFactory.createLineBorder(ApplicationColors.getGreen(), 3));
      }
      else
      {
         list.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
      }

      if (row % 2 == 1)
      {
         list.setBackground(ApplicationColors.getLightBlue());
      }
      else
      {
         list.setBackground(ApplicationColors.getVeryLightGold());
      }
      
      list.setLock(expression.isDoNotChange());

      return list;
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
}
