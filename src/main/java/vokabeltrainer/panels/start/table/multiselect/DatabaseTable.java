package vokabeltrainer.panels.start.table.multiselect;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;
import vokabeltrainer.types.DatabaseItem;

public class DatabaseTable extends JTable
{
   private static final long serialVersionUID = 4815287371476856952L;
   private MouseListener mouseListener;

   public DatabaseTable(DatabaseTableModel model,
         int totalWidth)
   {
      super(model, new DatabaseTableColumnModel(totalWidth));
      this.setShowVerticalLines(false);
      setOpaque(true);
      setRowHeight(30);
      setShowHorizontalLines(true);
      this.setRowSelectionAllowed(true);
      this.setColumnSelectionAllowed(false);
      this.setCellSelectionEnabled(false);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      this.setBorder(BorderFactory.createEmptyBorder());
      this.setTableHeader(null);
      setBackground(ApplicationColors.getBackgroundGold());

      mouseListener = getMultiselectMouseListener();
      addMouseListener(mouseListener);
   }

   private MouseAdapter getMultiselectMouseListener()
   {
      return new MouseAdapter()
      {
         public void mousePressed(MouseEvent mouseEvent)
         {
            JTable table = (JTable) mouseEvent.getSource();
            Point point = mouseEvent.getPoint();
            int row = table.rowAtPoint(point);
            if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1
                  && row == table.getSelectedRow())
            {
               DatabaseTableRow tableRow = ((DatabaseTableRow) table
                     .getValueAt(table.getSelectedRow(), 0));

               DatabaseItem database = tableRow.getDatabaseItem();

               database.toggleSelected();
               
               if(database.isSelected())
               {
                  Settings.addChosenDatabase(database.getDatabase());
               }
               else
               {
                  Settings.removeChosenDatabase(database.getDatabase());
               }

               ((DatabaseTableModel) table.getModel())
                     .fireTableCellUpdated(table.getSelectedRow(), 0);
            }
         }
      };
   }

   @Override
   public DatabaseTableModel getModel()
   {
      return (DatabaseTableModel) super.getModel();
   }

   @Override
   public Class<?> getColumnClass(int column)
   {
      return JLabel.class;
   }
}
