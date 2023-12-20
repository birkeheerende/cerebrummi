package vokabeltrainer;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import vokabeltrainer.common.ApplicationFonts;

public class TextImageWithPicture
{
   private TextImageWithPicture()
   {
      
   }
   public static BufferedImage make(BufferedImage picture, String... textrows)
   {
      BufferedImage helperImg = new BufferedImage(1, 1,
            BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d = helperImg.createGraphics();
      Font font = ApplicationFonts.getGermanFont(16F);
      g2d.setFont(font);
      FontMetrics fm = g2d.getFontMetrics();
      String longestText = "";
      for (String row : textrows)
      {
         if (row.length() > longestText.length())
         {
            longestText = row;
         }
      }
      int width = fm.stringWidth(longestText) + 5;
      int height = fm.getHeight() * textrows.length;
      g2d.dispose();
      
      width += picture.getWidth() + 10;
      height = Math.max(height, picture.getHeight());

      BufferedImage finalImg = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);
      g2d = finalImg.createGraphics();
    
      /*g2d.setPaint(new TexturePaint(ApplicationImages.getTexturedBackground(),
            new Rectangle(0, 0, 93, 72)));*/
      g2d.fillRect(0, 0, width, height);
      
      g2d.drawImage(picture, null, 0, 0);
      
      g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
            RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
      g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
            RenderingHints.VALUE_COLOR_RENDER_QUALITY);
      g2d.setRenderingHint(RenderingHints.KEY_DITHERING,
            RenderingHints.VALUE_DITHER_ENABLE);
      g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
            RenderingHints.VALUE_FRACTIONALMETRICS_ON);
      g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY);
      g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
            RenderingHints.VALUE_STROKE_PURE);
      g2d.setFont(font);
      fm = g2d.getFontMetrics();
      g2d.setColor(Color.BLACK);
      int y = fm.getAscent();
      for (String row : textrows)
      {
         g2d.drawString(row, picture.getWidth()+10, y);
         y += fm.getHeight();
      }
      g2d.dispose();
      return finalImg;
   }

}
