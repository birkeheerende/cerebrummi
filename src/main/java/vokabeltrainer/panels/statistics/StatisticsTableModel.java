package vokabeltrainer.panels.statistics;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class StatisticsTableModel extends DefaultTableModel
{
   private static final long serialVersionUID = -2421475557884629587L;

   public StatisticsTableModel(Vector<Vector<StatisticsTableRow>> data, Vector<String> columnNames)
   {
      super(data, columnNames);
   }
}
