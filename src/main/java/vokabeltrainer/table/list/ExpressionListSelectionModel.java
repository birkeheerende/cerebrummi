package vokabeltrainer.table.list;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;

public class ExpressionListSelectionModel extends DefaultListSelectionModel
{
   private static final long serialVersionUID = -8862868649115178949L;

   public ExpressionListSelectionModel()
   {
      setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   }

}
