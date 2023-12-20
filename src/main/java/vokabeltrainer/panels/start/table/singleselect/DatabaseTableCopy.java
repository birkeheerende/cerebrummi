package vokabeltrainer.panels.start.table.singleselect;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.SaveExpressions;
import vokabeltrainer.panels.settings.InputDatabaseNameDialog;

public class DatabaseTableCopy extends JTable
{
   private static final long serialVersionUID = 4815287371476856952L;
   private MouseListener mouseListener;

   public DatabaseTableCopy(DatabaseTableCopyModel model, int totalWidth)
   {
      super(model, new DatabaseTableCopyColumnModel(totalWidth));
      this.setShowVerticalLines(false);
      setOpaque(true);
      setBackground(ApplicationColors.getBackgroundGold());
      setRowHeight(30);
      setShowHorizontalLines(true);
      this.setRowSelectionAllowed(true);
      this.setColumnSelectionAllowed(false);
      this.setCellSelectionEnabled(false);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      this.setBorder(BorderFactory.createEmptyBorder());
      this.setTableHeader(null);

      mouseListener = getSingleselectMouseListener();
      addMouseListener(mouseListener);
   }

   private MouseAdapter getSingleselectMouseListener()
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
               DatabaseTableCopyRow tableRow = ((DatabaseTableCopyRow) table
                     .getValueAt(table.getSelectedRow(), 0));

               InputDatabaseNameDialog dialog = new InputDatabaseNameDialog(
                     "Export interne Datenbank");
               dialog.setVisible(true);

               final String databaseName;
               final boolean overwriteDatabaseNames;

               if (!dialog.isStartImportOrExport())
               {
                  dialog.dispose();
                  return;
               }
               else
               {
                  databaseName = dialog.getDatabaseName();
                  overwriteDatabaseNames = dialog.isOverwrite();
                  dialog.dispose();
                  Data
                  .copyInternalDatabase(
                        tableRow.getDatabaseItem().getDatabase(),
                        overwriteDatabaseNames, databaseName);
                  new SwingWorker<Void, Void>()
                  {

                     @Override
                     protected Void doInBackground() throws Exception
                     {
                        new SaveExpressions().save();
                        return null;
                     }

                  }.execute();
               } 
            }
         }
      };
   }

   @Override
   public DatabaseTableCopyModel getModel()
   {
      return (DatabaseTableCopyModel) super.getModel();
   }

   @Override
   public Class<?> getColumnClass(int column)
   {
      return JLabel.class;
   }
}
