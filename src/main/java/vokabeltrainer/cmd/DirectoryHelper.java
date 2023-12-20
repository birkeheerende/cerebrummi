package vokabeltrainer.cmd;

import java.io.File;
import javax.swing.JOptionPane;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;

public class DirectoryHelper
{

   private DirectoryHelper()
   {

   }

   public static boolean makeDirectory(File customDir)
   {
      try
      {
         customDir.mkdirs();
      }
      catch (Exception e)
      {
         JOptionPane.showMessageDialog(Common.getjFrame(),
               Common.getTranslator().realisticTranslate(Translation.FEHLER_BEIM_SPEICHERN), Common.getTranslator().realisticTranslate(Translation.FEHLERMELDUNG)+ " \n" + e,
               JOptionPane.ERROR_MESSAGE);
         return false;
      }
      return true;
   }
}
