package vokabeltrainer.table.list;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.types.Language;

public class ExpressionListCellRenderer
      implements ListCellRenderer<Object>, Serializable
{
   private static final long serialVersionUID = -631988254932147508L;

   private JTextField label;
   private Language language;
   private JLabel selected;
   private JLabel empty;

   public ExpressionListCellRenderer(Language language)
   {
      label = new JTextField();
      label.setOpaque(false);
      label.setEditable(false);
      label.setBackground(ApplicationColors.getTransparent());
      label.setBorder(BorderFactory.createEmptyBorder());
      selected = new JLabel(new ImageIcon(ApplicationImages.getSelect()));
      empty = new JLabel();
      this.language = language;
   }

   @Override
   public Component getListCellRendererComponent(JList<? extends Object> list,
         Object value, int index, boolean isSelected, boolean cellHasFocus)
   {
      label.setText((String) value);
      if (index == 0)
      {
         if (Boolean.valueOf((String) value))
         {
            return selected;
         }
         return empty;
      }
      if (index == 3)
      {
         label.setFont(ApplicationFonts.getHebrewHandwrittenFont(30));
         label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      }
      else if (index == 2 && language.equals(Language.GERMAN_TO_HEBREW))
      {
         label.setFont(ApplicationFonts.getHebrewFont(20F));
         label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      }
      else if (index == 1 && language.equals(Language.HEBREW_TO_GERMAN))
      {
         label.setFont(ApplicationFonts.getHebrewFont(20F));
         label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      }
      else
      {
         label.setFont(ApplicationFonts.getGermanFont(16F));
         label.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      }
      return label;
   }
}
