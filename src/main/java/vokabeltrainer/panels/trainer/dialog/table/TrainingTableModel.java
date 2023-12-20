package vokabeltrainer.panels.trainer.dialog.table;

import javax.swing.table.DefaultTableModel;

public class TrainingTableModel extends DefaultTableModel
{
   private static final long serialVersionUID = 6529124422960393777L;

   private TrainingTableRow[][] data;
   private final static String[][] COLUMNNAMES = { { "" },
         { "" }, { "" }, { "" },
         { "" } };

   public TrainingTableModel(TrainingTableRow[][] data)
   {
      super(data, COLUMNNAMES);
      this.data = data;
   }

   public TrainingTableRow[][] getData()
   {
      return data;
   }

}
