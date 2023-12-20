package vokabeltrainer.keyboards;

import java.awt.ComponentOrientation;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import vokabeltrainer.common.ApplicationFonts;

public class DataButton extends JButton
{
   private static final long serialVersionUID = 578075610960102010L;
   
   private String data;

   public DataButton(Image caption, String data)
   {
      super(new ImageIcon(caption));
      this.data = data;
   }
   
   public DataButton(String caption, String data)
   {
      super(caption);
      this.data = data;
      this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      this.setFont(ApplicationFonts.getHebrewFont(30F));
   }

   public String getData()
   {
      return data;
   }
}
