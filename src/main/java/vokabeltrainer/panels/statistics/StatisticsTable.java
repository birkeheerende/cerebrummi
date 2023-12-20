package vokabeltrainer.panels.statistics;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vokabeltrainer.common.ApplicationColors;

public class StatisticsTable extends JTable
{
   private static final long serialVersionUID = 1467979172740860765L;

   public StatisticsTable(StatisticsTableModel model)
   {
      super(model, new StatisticsTableColumnModel());
      this.setShowVerticalLines(false);
      setOpaque(true);
      setRowHeight(50);
      setShowHorizontalLines(true);
      setBackground(ApplicationColors.getDarkRed());
      this.setRowSelectionAllowed(true);
      this.setColumnSelectionAllowed(true);
      this.setCellSelectionEnabled(true);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   }
}
