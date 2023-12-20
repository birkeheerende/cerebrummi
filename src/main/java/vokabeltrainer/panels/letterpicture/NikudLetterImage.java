package vokabeltrainer.panels.letterpicture;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.editing.LetterForAnalysis;
import vokabeltrainer.editing.NikudLetter;

public class NikudLetterImage
{

   private NikudLetterImage()
   {
      // nothing
   }

   public static BufferedImage make(LetterForAnalysis letter)
   {
      BufferedImage finalImg = new BufferedImage(50, 50,
            BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d = finalImg.createGraphics();
      g2d.setColor(ApplicationColors.getTexturedBackgroundColor());
      g2d.fillRect(0, 0, 50, 50);
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
      g2d.setFont(ApplicationFonts.getHebrewFont(36));
      FontMetrics fm = g2d.getFontMetrics();
      fm = g2d.getFontMetrics();
      g2d.setColor(Color.BLACK);
      g2d.drawString(letter.getContent().getUnicode(), 10, fm.getAscent() + 4);
      for (NikudLetter nikudLetter : letter.getSetUpperPunktation())
      {
         g2d.drawString(nikudLetter.getUnicode(), 10, fm.getAscent() + 4);
      }
      if (letter.getDagesh() != null)
      {
         g2d.drawString(letter.getDagesh().getUnicode(), 10, fm.getAscent() + 4);
      }
      for (NikudLetter nikudLetter : letter.getListLowerPunktation())
      {
         g2d.drawString(nikudLetter.getUnicode(), 10, fm.getAscent() + 4);
      }
      g2d.dispose();
      return finalImg;
   }

   public static BufferedImage makeSpace()
   {
      BufferedImage finalImg = new BufferedImage(50, 50,
            BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d = finalImg.createGraphics();
      g2d.setColor(ApplicationColors.getTexturedBackgroundColor());
      g2d.fillRect(0, 0, 50, 50);
      g2d.dispose();
      return finalImg;
   }
}
