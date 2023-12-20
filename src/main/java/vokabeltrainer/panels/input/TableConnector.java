package vokabeltrainer.panels.input;

import javax.swing.JTable;

public interface TableConnector
{
   public void save();

   public void fireTableCellUpdated(JTable table, int selectedRow, int i);
}
