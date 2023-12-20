package vokabeltrainer.panels.success.table.list;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;

public class SuccessListSelectionModel extends DefaultListSelectionModel
{

   private static final long serialVersionUID = 1683266153212839450L;

   public SuccessListSelectionModel()
   {
      setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   }

}
