package vokabeltrainer.types;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public enum SearchType
{
   SEARCHWORD(Translation.SUCHE_NACH_SUCHWORT),
   WORDSTART(
         Translation.SUCHE_NACH_WORTANFANG);

   Translation meaning;

   SearchType(Translation meaning)
   {
      this.meaning = meaning;
   }

   public String getMeaning(Language language)
   {
      Translator translator = Common.getTranslator();
      switch (language)
      {
      case GERMAN_TO_HEBREW:
         return translator.realisticTranslate(meaning);
      case HEBREW_TO_GERMAN:
         switch (this)
         {
         case SEARCHWORD:
            return translator.realisticTranslate(meaning) + " " + translator
                  .realisticTranslate(Translation._OHNE_PUNKTIERUNG_);
         case WORDSTART:
            return translator.realisticTranslate(meaning) + " " + translator
                  .realisticTranslate(Translation._OHNE_PUNKTIERUNG_);
         }
      }
      return "";
   }

}
