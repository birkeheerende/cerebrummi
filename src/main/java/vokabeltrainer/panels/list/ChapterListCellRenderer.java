package vokabeltrainer.panels.list;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.types.Chapter;

public class ChapterListCellRenderer extends DefaultListCellRenderer
{
   private static final long serialVersionUID = 6061565751599958392L;

   private JLabel label;

   public ChapterListCellRenderer()
   {
      label = new JLabel();
      label.setBackground(ApplicationColors.getLightGrayGold());
      label.setFont(ApplicationFonts.getGermanFont(16F));
   }

   @Override
   public Component getListCellRendererComponent(JList<? extends Object> list,
         Object value, int index, boolean isSelected, boolean cellHasFocus)
   {
      if (value != null)
      {
         Chapter chapter = (Chapter) value;
         label
               .setText(chapter.getDatabaseDescription().getDatabaseName()
                     + " | " + chapter.getName());
      }
      else
      {
         label.setText("");
      }

      label.setBorder(BorderFactory.createEmptyBorder());
      if (list.getSelectedIndex() == index)
      {
         label.setOpaque(true);
      }
      else
      {
         label.setOpaque(false);
      }
      return label;
   }
}
