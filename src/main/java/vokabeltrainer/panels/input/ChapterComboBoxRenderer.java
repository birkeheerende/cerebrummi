package vokabeltrainer.panels.input;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import vokabeltrainer.types.Chapter;

public class ChapterComboBoxRenderer extends JLabel
      implements ListCellRenderer<Chapter>
{
   private static final long serialVersionUID = -3955758434879426117L;

   public ChapterComboBoxRenderer()
   {
      this.setMinimumSize(new Dimension(500, 30));
      this.setMaximumSize(new Dimension(500, 30));
   }

   @Override
   public Component getListCellRendererComponent(JList<? extends Chapter> list,
         Chapter value, int index, boolean isSelected, boolean cellHasFocus)
   {
      if (value != null)
      {
         this
               .setText(
                     "  " + value.getDatabaseDescription().getDatabaseName() + " | " + value.getName());
      }

      return this;
   }

}
