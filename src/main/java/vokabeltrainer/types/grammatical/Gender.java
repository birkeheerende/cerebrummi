package vokabeltrainer.types.grammatical;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public enum Gender implements GrammaticalEnum
{
   PLEASE_CHOOSE(
         Translation.BITTE_WAEHLEN),
   GENDER_UNKNOWN(
         Translation.UNBEKANNT),
   FEMALE(
         Translation.FEMININ),
   MALE(
         Translation.MASKULIN),
   BOTH_FEMALE_MALE(
         Translation.FEMININ_UND_MASKULIN),
   GENDER_NA(
         Translation.NICHT_ANWENDBAR);

   private Translation description;

   Gender(Translation description)
   {
      this.description = description;
   }

   @Override
   public String toString()
   {
      Translator translator = Common.getTranslator();
      return translator.realisticTranslate(description);
   }

   public String toDescription()
   {
      Translator translator = Common.getTranslator();
      switch (this)
      {
      case BOTH_FEMALE_MALE:
      case FEMALE:
      case MALE:
         return translator.realisticTranslate(description);
      case GENDER_UNKNOWN:
         return translator.realisticTranslate(Translation.GESCHLECHT)
               + " " + translator.realisticTranslate(description);
      case GENDER_NA:
      default:
         return "";
      }
   }
   
   public String toInfo()
   {
      Translator translator = Common.getTranslator();
      switch (this)
      {
      case BOTH_FEMALE_MALE:
      case FEMALE:
      case MALE:
         return translator.realisticTranslate(description);
      case GENDER_UNKNOWN:
      case GENDER_NA:
      default:
         return "";
      }
   }
   
   @Override
   public Gender fromEnumName(String name)
   {
      return Gender.valueOf(name);
   }

   @Override
   public GrammaticalParentEnum getParent()
   {
      return GrammaticalParentEnum.GENDER;
   }
   
   @Override
   public int getPrintOrderNumber()
   {
      return getParent().getSortNumber();
   }

   @Override
   public GrammaticalEnum getUnkown()
   {
      return Gender.GENDER_UNKNOWN;
   }
}
