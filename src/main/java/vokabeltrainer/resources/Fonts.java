package vokabeltrainer.resources;

import java.awt.Font;

import vokabeltrainer.common.ApplicationFonts;

public class Fonts
{

   public static void read() throws Exception
   {
      ApplicationFonts.setHebrewFont(
            Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream(
                  "/_1_fonts/Cardo-regular_104s.ttf")));
      
      ApplicationFonts.setGermanFont(
            Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream(
                 "/_1_fonts/Orkney Light.ttf")));
      
      ApplicationFonts.setGermanBoldFont(
            Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream(
                  "/_1_fonts/Orkney Medium.ttf")));
      
      ApplicationFonts.setHebrewHandwrittenFont(
            Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream(
                  "/_1_fonts/AdaAdama.ttf")));
   }

   public static void define() throws Exception
   {
      ApplicationFonts.setButtonFont(ApplicationFonts.getGermanFont().deriveFont(16F));
      ApplicationFonts.setToolbarButtonFont(ApplicationFonts.getGermanFont().deriveFont(26F));
      ApplicationFonts.setSecondaryToolbarButtonFont(ApplicationFonts.getGermanFont().deriveFont(18F));
      ApplicationFonts.setRadioButtonFont(ApplicationFonts.getGermanFont().deriveFont(12F));
      ApplicationFonts.setComboBoxFont(ApplicationFonts.getGermanFont().deriveFont(14F));
   }
}
