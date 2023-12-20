package vokabeltrainer.panels.success.table.list;

import javax.swing.BorderFactory;
import javax.swing.JList;

public class SuccessList extends JList<String>
{

   private static final long serialVersionUID = 986774920269343277L;

   public SuccessList()
   {
      setSelectionModel(new SuccessListSelectionModel());
      setBorder(BorderFactory.createEmptyBorder());
      setVisibleRowCount(3);
      this.setFixedCellHeight(25);
//      getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false),
//            new Object());
//      getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false),
//            new Object());
//      getActionMap().get("clearSelection").setEnabled(true);

      setCellRenderer(new SuccessListCellRenderer());
   }

}
