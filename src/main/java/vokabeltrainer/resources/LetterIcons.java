package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.editing.NikudLetter;

public class LetterIcons
{
   
   public static void readNikud() throws Exception
   {
      Map<NikudLetter, BufferedImage> letterIconsNikudMap = new HashMap<>();
      
      for(NikudLetter letter : NikudLetter.values())
      {
         if(letter == NikudLetter.NEWSPACE || letter == NikudLetter.SPACE)
         {
            continue;
         }
         BufferedImage image = ImageIO.read(
               LetterIcons.class.getResourceAsStream("/letterIcons/"+letter.name()+".png"));
         if(image != null)
         {
            letterIconsNikudMap.put(letter, image);
         }
      }
      
      ApplicationImages.setLetterIconsNikudMap(letterIconsNikudMap);
   }
}
