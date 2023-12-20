package vokabeltrainer.panels.statistics;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public class StatisticsTableColumnModel extends DefaultTableColumnModel
{
   private static final long serialVersionUID = 1858850934582009695L;
   
   private StatisticsTableCellRenderer renderer;
   private Translator translator = Common.getTranslator();

   public StatisticsTableColumnModel()
   {
      renderer = new StatisticsTableCellRenderer();
      
      TableColumn column = new TableColumn();
      column.setHeaderValue(translator.realisticTranslate(Translation.DATUM));
      column.setCellRenderer(renderer);
      column.setCellEditor(renderer);
      column.setPreferredWidth(250);
      addColumn(column);
      
      TableColumn column1 = new TableColumn();
      column1.setHeaderValue(translator.realisticTranslate(Translation.HEBRAEISCH)
            + " >> "
            + translator.realisticTranslate(Translation.DEUTSCH));
      column1.setCellRenderer(renderer);
      column1.setCellEditor(renderer);
      column1.setPreferredWidth(150);
      addColumn(column1);
      
      TableColumn column2 = new TableColumn();
      column2.setHeaderValue(translator.realisticTranslate(Translation.DEUTSCH)
            + " >> "
            + translator.realisticTranslate(Translation.HEBRAEISCH));
      column2.setCellRenderer(renderer);
      column2.setCellEditor(renderer);
      column2.setPreferredWidth(150);
      addColumn(column2);
   }

   public StatisticsTableCellRenderer getRenderer()
   {
      return renderer;
   }

}
