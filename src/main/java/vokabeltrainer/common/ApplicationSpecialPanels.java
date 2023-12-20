package vokabeltrainer.common;

import java.util.Map;

import javax.swing.JOptionPane;

import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.panels.letterpicture.LetterPictureButtonPanel;

public class ApplicationSpecialPanels
{
   private static Map<NikudLetter, LetterPictureButtonPanel> letterPicturesPanelMap;
   private static String message = "Cerebrummi© konnte Resourcen nicht laden.\nFehler: ";

   public static Map<NikudLetter, LetterPictureButtonPanel> getLetterPicturesPanelMap()
   {
      return letterPicturesPanelMap;
   }

   public static void setLetterPicturesPanelMap(
         Map<NikudLetter, LetterPictureButtonPanel> letterPicturesPanelMap)
   {
      if(letterPicturesPanelMap.size() < 27)
      {
         exitWithMessage("Es fehlen Buchstabenbilder Panels.");
      }
      else if(letterPicturesPanelMap.size() > 27)
      {
         exitWithMessage("Es gibt zuviele Buchstabenbilder Panels.");
      }
      ApplicationSpecialPanels.letterPicturesPanelMap = letterPicturesPanelMap;
   }

   private static void exitWithMessage(String localMessage)
   {
      JOptionPane.showMessageDialog(null, message + localMessage, "Nachricht",
            JOptionPane.CLOSED_OPTION);
      System.exit(1);
   }
}
