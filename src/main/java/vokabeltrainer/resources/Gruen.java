package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import vokabeltrainer.common.ApplicationImages;

public class Gruen
{
   private static List<BufferedImage> greenImagesList = new ArrayList<>();

   public static void read() throws Exception
   {

      for (int i = 0; i < 72; i++)
      {
         greenImagesList.add(ImageIO.read(
               Gruen.class.getResourceAsStream("/gruen/green_" + i + ".jpg")));
      }
      ApplicationImages.setGreenImages(greenImagesList);
   }
}
