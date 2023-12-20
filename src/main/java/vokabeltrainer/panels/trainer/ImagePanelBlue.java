package vokabeltrainer.panels.trainer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import vokabeltrainer.common.ApplicationImages;

public class ImagePanelBlue extends JPanel
{
   private static final long serialVersionUID = -8547040821904422107L;
   private static BufferedImage currentImage = ApplicationImages.getRandomBlueImage();

   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);

      int x = this.getParent().getWidth() / 2 - 501 / 2;
      int y = this.getParent().getHeight() / 2 - 210 / 2;
      g.drawImage(currentImage, x, y, this);
   }
   
   public static void setNextImage()
   {
      currentImage = ApplicationImages.getRandomBlueImage();
   }
}
