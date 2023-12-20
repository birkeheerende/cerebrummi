package vokabeltrainer;

import java.awt.Graphics;

import javax.swing.JPanel;

import vokabeltrainer.common.ApplicationImages;

public class TrashCanBackgroundPanel extends JPanel
{

   private static final long serialVersionUID = -7918923007938243168L;

   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      if (ApplicationImages.getTrashcanBackground() != null)
      {
         int x = this.getParent().getWidth() / 2 - 1000 / 2;
         int y = this.getParent().getHeight() / 2 - 620 / 2;
         g.drawImage(ApplicationImages.getTrashcanBackground(), x, y, this);
      }
   }
}
