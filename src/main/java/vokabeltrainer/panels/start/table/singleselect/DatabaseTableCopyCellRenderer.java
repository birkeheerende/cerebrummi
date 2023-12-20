package vokabeltrainer.panels.start.table.singleselect;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.types.DatabaseItem;

public class DatabaseTableCopyCellRenderer
      implements TableCellRenderer, TableCellEditor
{
   private JLabel database;
   private JLabel authors;
   private JLabel company;

   public DatabaseTableCopyCellRenderer() // no multiple selection possible
   {
      database = new JLabel();
      database.setFont(ApplicationFonts.getGermanFont(14F));

      authors = new JLabel();
      authors.setFont(ApplicationFonts.getGermanFont(14F));

      company = new JLabel();
      company.setFont(ApplicationFonts.getGermanFont(14F));
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
      DatabaseItem databaseItem = ((DatabaseTableCopyRow) value).getDatabaseItem();

      if (column == 0)
      {
         this.database.setText(databaseItem.getDatabase().getName());
         return this.database;
      }
      if(column == 1)
      {
         this.authors.setText(databaseItem.getDatabase().getAuthors());
         return this.authors;
      }
      
      this.company.setText(databaseItem.getDatabase().getCompany());
      return this.company;
   }

}
