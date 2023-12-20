package vokabeltrainer.panels.trainer;

import java.awt.Graphics;

import javax.swing.JPanel;

import vokabeltrainer.common.ApplicationImages;

public class ImagePanelError extends JPanel
{
   private static final long serialVersionUID = 6703471281100144482L;

   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);

      int x = this.getParent().getWidth() / 2 - 501 / 2;
      int y = this.getParent().getHeight() / 2 - 210 / 2;
      g.drawImage(ApplicationImages.getErrorImage(), x, y, this);
   }

}
