package vokabeltrainer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class IconPanel extends JPanel
{
   private static final long serialVersionUID = -8715777185660682291L;

   private BufferedImage icon;
   private int width;
   private int height;

   public IconPanel(BufferedImage icon, int width, int height)
   {
      this.icon = icon;
      this.width = width;
      this.height = height;
      this.setOpaque(false);
      this.setBackground(new Color(0, 0, 0, 0));
      this.setMinimumSize(new Dimension(width, height));
      this.setPreferredSize(new Dimension(width, height));
      this.setMaximumSize(new Dimension(width, height));
      this.setSize(new Dimension(width, height));
   }

   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      if (icon != null)
      {
         int x = this.getParent().getWidth() / 2 - width / 2;
         int y = this.getParent().getHeight() / 2 - height / 2;
         g.drawImage(icon, x, y, this);
      }
   }
}
