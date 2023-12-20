package vokabeltrainer.scale;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;

public class Scale
{
   private int scaleX;
   private int scaleY;

   public Scale(int size)
   {
      GraphicsConfiguration asdf = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getDefaultScreenDevice()
            .getDefaultConfiguration();

      AffineTransform asfd2 = asdf.getDefaultTransform();

      if (asfd2.getScaleX() < 1.1)
      {
         scaleX = size + 2;
         scaleY = size;
      }
      else if (asfd2.getScaleX() < 1.35)
      {
         scaleX = (int) (asfd2.getScaleX() * (size + 2) * 0.75);
         scaleY = (int) (asfd2.getScaleY() * size * 0.75);
      }
      else if (asfd2.getScaleX() < 1.6)
      {
         scaleX = (int) (asfd2.getScaleX() * (size + 2) * 0.65);
         scaleY = (int) (asfd2.getScaleY() * size * 0.65);
      }
      else
      {
         scaleX = (int) (asfd2.getScaleX() * (size + 2) * 0.6);
         scaleY = (int) (asfd2.getScaleY() * size * 0.6);
      }
   }

   public int getScaleX()
   {
      return scaleX;
   }

   public int getScaleY()
   {
      return scaleY;
   }
}
