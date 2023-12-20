package vokabeltrainer.common;

import java.awt.Font;

public class ApplicationFonts
{
   private static Font hebrewFont;
   private static Font hebrewHandwrittenFont;
   private static Font germanFont;
   private static Font germanBoldFont;
   
   private static Font buttonFont;
   private static Font toolbarButtonFont;
   private static Font secondaryToolbarButtonFont;
   private static Font radioButtonFont;
   private static Font comboBoxFont;

   public static void setHebrewFont(Font font)
   {
      hebrewFont = font;
   }

   public static void setGermanFont(Font font)
   {
      germanFont = font;
   }

   public static void setGermanBoldFont(Font font)
   {
      germanBoldFont = font;
   }

   public static void setHebrewHandwrittenFont(Font font)
   {
      hebrewHandwrittenFont = font;
   }
   
   
   
   public static Font getHebrewFont()
   {
      return hebrewFont;
   }
   
   public static Font getHebrewFont(float size)
   {
      return hebrewFont.deriveFont(size);
   }
   
   public static Font getHebrewHandwrittenFont()
   {
      return hebrewHandwrittenFont;
   }
   
   public static Font getHebrewHandwrittenFont(float size)
   {
      return hebrewHandwrittenFont.deriveFont(size);
   }

   public static Font getGermanFont()
   {
      return germanFont;
   }
   
   public static Font getGermanFont(float size)
   {
      return germanFont.deriveFont(size);
   }

   public static Font getGermanBoldFont()
   {
      return germanBoldFont;
   }
   
   public static Font getGermanBoldFont(float size)
   {
      return germanBoldFont.deriveFont(size);
   }

   
   
   public static void setButtonFont(Font font)
   {
      buttonFont = font;
   }

   public static void setToolbarButtonFont(Font font)
   {
      toolbarButtonFont = font;
   }

   public static void setSecondaryToolbarButtonFont(
         Font font)
   {
      secondaryToolbarButtonFont = font;
   }

   public static void setRadioButtonFont(Font font)
   {
      radioButtonFont = font;
   }
   
   public static void setComboBoxFont(Font font)
   {
      comboBoxFont = font;
   }
   
   
   public static Font getButtonFont()
   {
      return buttonFont;
   }

   public static Font getToolbarButtonFont()
   {
      return toolbarButtonFont;
   }

   public static Font getSecondaryToolbarButtonFont()
   {
      return secondaryToolbarButtonFont;
   }
   
   public static Font getRadioButtonFont()
   {
      return radioButtonFont;
   }

   public static Font getComboBoxFont()
   {
      return comboBoxFont;
   }
}
