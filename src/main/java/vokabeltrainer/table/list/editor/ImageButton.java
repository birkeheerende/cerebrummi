package vokabeltrainer.table.list.editor;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton
{
   private static final long serialVersionUID = 578075610960102010L;
   
   private String imagefile;

   public ImageButton()
   {
      super();
   }
   
   public ImageButton(Image image, String imagefile)
   {
      super(new ImageIcon(image));
      this.imagefile = imagefile;
   }

   public String getImagefile() 
   {
	   return imagefile;
   }

   public void setImagefile(String imagefile) 
   {
	   this.imagefile = imagefile;
   }
}
