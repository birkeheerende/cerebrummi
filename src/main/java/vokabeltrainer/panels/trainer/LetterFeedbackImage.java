package vokabeltrainer.panels.trainer;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.editing.LetterForAnalysis;
import vokabeltrainer.editing.NikudLetter;

public class LetterFeedbackImage
{
   
   private LetterFeedbackImage()
   {
      // nothing
   }
   
   public static BufferedImage make(LetterForAnalysis letterDic,
         LetterForAnalysis letterAnswer, boolean okay, Font hebrewFont)
   {
      BufferedImage finalImg = new BufferedImage(
            Math.max(letterDic.getPixelWidth(), letterAnswer.getPixelWidth()),
            100, BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d = finalImg.createGraphics();
      g2d.setColor(ApplicationColors.getTexturedBackgroundColor());
      g2d.fillRect(0, 0,
            Math.max(letterDic.getPixelWidth(), letterAnswer.getPixelWidth()),
            100);
      g2d.setColor(ApplicationColors.getDarkRed());
      if (!okay)
      {
         double midX = ((double) Math.max(
               letterDic.getContent().getPixelWidth(),
               letterAnswer.getContent().getPixelWidth())) / 2;
         int[] xPoints = { (int) midX, 0,
               Math.max(letterDic.getContent().getPixelWidth(),
                     letterAnswer.getContent().getPixelWidth()),
               ((int) midX) + 1 };
         int[] yPoints = { 24 + 30, 16 + 30, 16 + 30, 24 + 30 };
         g2d.fillPolygon(xPoints, yPoints, 4);
      }
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
      g2d.setFont(hebrewFont);
      FontMetrics fm = g2d.getFontMetrics();
      fm = g2d.getFontMetrics();
      g2d.setColor(ApplicationColors.getGreen());
      g2d.drawString(letterDic.getContent().getUnicode(), 0,
            fm.getAscent() + 9);
      for (NikudLetter nikudLetter : letterDic.getSetUpperPunktation())
      {
         g2d.drawString(nikudLetter.getUnicode(), 0, fm.getAscent() + 9);
      }
      if (letterDic.getDagesh() != null)
      {
         g2d.drawString(letterDic.getDagesh().getUnicode(), 0,
               fm.getAscent() + 9);
      }
      for (NikudLetter nikudLetter : letterDic.getListLowerPunktation())
      {
         g2d.drawString(nikudLetter.getUnicode(), 0, fm.getAscent() + 9);
      }
      g2d.setColor(Color.BLACK);
      g2d.drawString(letterAnswer.getContent().getUnicode(), 0,
            fm.getAscent() + 9 + 50);
      for (NikudLetter nikudLetter : letterAnswer.getSetUpperPunktation())
      {
         g2d.drawString(nikudLetter.getUnicode(), 0, fm.getAscent() + 9 + 50);
      }
      if (letterAnswer.getDagesh() != null)
      {
         g2d.drawString(letterAnswer.getDagesh().getUnicode(), 0,
               fm.getAscent() + 9 + 50);
      }
      for (NikudLetter nikudLetter : letterAnswer.getListLowerPunktation())
      {
         g2d.drawString(nikudLetter.getUnicode(), 0, fm.getAscent() + 9 + 50);
      }
      g2d.dispose();
      return finalImg;
   }

   public static BufferedImage makeSpace()
   {
      BufferedImage finalImg = new BufferedImage(18, 100,
            BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d = finalImg.createGraphics();
      g2d.setColor(ApplicationColors.getTexturedBackgroundColor());
      g2d.fillRect(0, 0, 18, 100);
      g2d.dispose();
      return finalImg;
   }
}
