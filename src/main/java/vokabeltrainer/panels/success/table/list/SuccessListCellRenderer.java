package vokabeltrainer.panels.success.table.list;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;

public class SuccessListCellRenderer implements ListCellRenderer<Object>, Serializable
{

   private static final long serialVersionUID = -5594206011077904779L;

   private JTextField german;   
   private JTextField hebrew;   
   private JTextField grammar;
   
   public SuccessListCellRenderer()
   {
      german = new JTextField();
      hebrew = new JTextField();
      grammar = new JTextField();
      
      german.setOpaque(false);
      hebrew.setOpaque(false);
      grammar.setOpaque(false);
      
      german.setEditable(false);
      hebrew.setEditable(false);
      grammar.setEditable(false);
      
      german.setBackground(ApplicationColors.getTransparent());
      hebrew.setBackground(ApplicationColors.getTransparent());
      grammar.setBackground(ApplicationColors.getTransparent());
      
      german.setBorder(BorderFactory.createEmptyBorder());
      hebrew.setBorder(BorderFactory.createEmptyBorder());
      grammar.setBorder(BorderFactory.createEmptyBorder());
      
      german.setFont(ApplicationFonts.getGermanFont(16F));
      hebrew.setFont(ApplicationFonts.getHebrewFont(20F));
      grammar.setFont(ApplicationFonts.getGermanFont(16F));
      
      hebrew.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
   }

   @Override
   public Component getListCellRendererComponent(JList<? extends Object> list,
         Object value, int index, boolean isSelected, boolean cellHasFocus)
   {
      if(index == 0)
      {
         german.setText((String) value);
         return german;
      }
      if(index == 1)
      {
         hebrew.setText((String) value);
         return hebrew;
      }
      grammar.setText((String) value);
      return grammar;
   }

}
