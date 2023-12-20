package vokabeltrainer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;

public class InfoTextField extends JTextField
{
   private static final long serialVersionUID = -7140719570197015380L;
   private JButton button;
   private String title;

   public InfoTextField(String borderTitle, String... infoText)
   {
      this.title = borderTitle;

      button = new JButton(
            new ImageIcon(ApplicationImages.getInfoButtonIcon()));
      button.setFont(ApplicationFonts.getGermanFont(30F));
      button.setMargin(new Insets(-5, -20, -5, -20));
      button.setBackground(new Color(0, 0, 0, 0));
      button.setForeground(ApplicationColors.getGold());
      button.setPreferredSize(new Dimension(14, 32));

      button.addActionListener(event -> {
         JOptionPane.showMessageDialog(this, "",
               "Cerebrummi©", JOptionPane.INFORMATION_MESSAGE,
               new ImageIcon(TextImage.make(infoText)));
      });
      
      button.addMouseListener(new MouseAdapter()
      {

         @Override
         public void mouseClicked(MouseEvent e)
         {

         }

         @Override
         public void mousePressed(MouseEvent e)
         {

         }

         @Override
         public void mouseReleased(MouseEvent e)
         {

         }

         @Override
         public void mouseEntered(MouseEvent e)
         {  
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         }

         @Override
         public void mouseExited(MouseEvent e)
         {

            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
         }

      });

      ComponentTitledBorder border = new ComponentTitledBorder(button, this,
            BorderFactory.createTitledBorder(borderTitle), 25);

      this.setBorder(border);
   }
   

   public void setBlankBorder()
   {
      ComponentTitledBorder border = new ComponentTitledBorder(button, this,
            BorderFactory.createTitledBorder(title),025);

      this.setBorder(border);
   }

   public void setRedBorder()
   {
      ComponentTitledBorder border = new ComponentTitledBorder(button, this,
            new TitledBorder(BorderFactory.createLineBorder(Color.RED),
                  title), 25);

      this.setBorder(border);
   }

 
}
