package vokabeltrainer.panels.trainer.dialog.table;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public class TrainingTableColumnModel extends DefaultTableColumnModel
{
   private static final long serialVersionUID = -1884221408045680993L;

   private TrainingTableCellRendererEditor rendererEditor;
   private Translator translator = Common.getTranslator();
   
   public TrainingTableColumnModel()
   {
      rendererEditor = new TrainingTableCellRendererEditor();
    
      TableColumn column = new TableColumn();
      column.setHeaderValue(translator.realisticTranslate(Translation.KAPITEL));
      column.setCellRenderer(rendererEditor);
      column.setCellEditor(rendererEditor);
      column.setPreferredWidth(850);
      addColumn(column);

      TableColumn column1 = new TableColumn();
      column1.setHeaderValue(translator.realisticTranslate(Translation.WIEDERHOLEN));
      column1.setCellRenderer(rendererEditor);
      column1.setCellEditor(rendererEditor);
      column1.setPreferredWidth(50);
      addColumn(column1);

      TableColumn column2 = new TableColumn();
      column2.setHeaderValue(translator.realisticTranslate(Translation.UNGELERNT));
      column2.setCellRenderer(rendererEditor);
      column2.setCellEditor(rendererEditor);
      column2.setPreferredWidth(50);
      addColumn(column2);

      TableColumn column3 = new TableColumn();
      column3.setHeaderValue(translator.realisticTranslate(Translation.NEU_LERNEN));
      column3.setCellRenderer(rendererEditor);
      column3.setCellEditor(rendererEditor);
      column3.setPreferredWidth(50);
      addColumn(column3);

      TableColumn column4 = new TableColumn();
      column4.setHeaderValue("");
      column4.setCellRenderer(rendererEditor);
      column4.setCellEditor(rendererEditor);
      addColumn(column4);
   }

   public TrainingTableCellRendererEditor getRendererEditor()
   {
      return rendererEditor;
   }
}
