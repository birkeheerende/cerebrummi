package vokabeltrainer;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import vokabeltrainer.common.ApplicationImages;

public class BackgroundPanelTiled extends JPanel
{
   private static final long serialVersionUID = 51778743171783453L;

   BufferedImage tileImage = ApplicationImages.getTexturedBackground();

   public BackgroundPanelTiled()
   {

   }
   
   public BackgroundPanelTiled(BorderLayout borderLayout)
   {
      super(borderLayout);
   }

   protected void paintComponent(Graphics g)
   {
      int width = getWidth();
      int height = getHeight();
      for (int x = 0; x < width; x += tileImage.getWidth())
      {
         for (int y = 0; y < height; y += tileImage.getHeight())
         {
            g.drawImage(tileImage, x, y, this);
         }
      }
   }
}
