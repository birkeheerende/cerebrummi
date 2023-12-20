package vokabeltrainer.panels.translation;

import vokabeltrainer.common.Settings;

public class Translator
{

   public String realisticTranslate(Translation translation)
   {
      if (TranslationCode.de_original == Settings.getTranslationCode())
      {
         return translation.getGerman();
      }
      else if (TranslationCode.en == Settings.getTranslationCode())
      {
    	  return translation.getEnglish();
      }

      return "missing";
   }

}
