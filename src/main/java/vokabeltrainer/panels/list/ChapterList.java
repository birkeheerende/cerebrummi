package vokabeltrainer.panels.list;

import javax.swing.JList;

import vokabeltrainer.types.Chapter;

public class ChapterList extends JList<Chapter>
{
   private static final long serialVersionUID = 6405025849507747779L;

   public ChapterList(ChapterListSelectionModel selectionModel)
   {
      setSelectionModel(selectionModel);
      setFixedCellHeight(30);
      getActionMap().get("clearSelection").setEnabled(true);
      setCellRenderer(new ChapterListCellRenderer());
   }

}
