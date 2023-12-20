package vokabeltrainer.types.grammatical;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public enum GrammaticalPerson implements GrammaticalEnum
{
   PLEASE_CHOOSE(
         Translation.BITTE_WAEHLEN),
   GRAMMATICALPERSON_UNKNOWN(
         Translation.UNBEKANNT),
   ERSTE_PERSON(
         Translation._1_PERSON),
   ZWEITE_PERSON(
         Translation._2_PERSON),
   DRITTE_PERSON(
         Translation._3_PERSON),
   ALL_PERSON(Translation._1_2_3_PERSON),
   GRAMMATICALPERSON_NA(
         Translation.NICHT_ANWENDBAR);

   private Translation description;

   GrammaticalPerson(Translation description)
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
      case DRITTE_PERSON:
      case ZWEITE_PERSON:
      case ERSTE_PERSON:
      case ALL_PERSON:
         return translator.realisticTranslate(description);
      case GRAMMATICALPERSON_UNKNOWN:
         return translator.realisticTranslate(Translation.GRAMMATISCHE_PERSON)
               + " " + translator.realisticTranslate(description);
      case GRAMMATICALPERSON_NA:
      default:
         return "";
      }
   }
   
   @Override
   public GrammaticalPerson fromEnumName(String name)
   {
      return GrammaticalPerson.valueOf(name);
   }

   @Override
   public String toInfo()
   {
      Translator translator = Common.getTranslator();
      switch (this)
      {
      case DRITTE_PERSON:
      case ZWEITE_PERSON:
      case ERSTE_PERSON:
      case ALL_PERSON:
         return translator.realisticTranslate(description);
      case GRAMMATICALPERSON_UNKNOWN:
      case GRAMMATICALPERSON_NA:
      default:
         return "";
      }
   }

   @Override
   public GrammaticalParentEnum getParent()
   {
      return GrammaticalParentEnum.GRAMMATICAL_PERSON;
   }
   
   @Override
   public int getPrintOrderNumber()
   {
      return getParent().getSortNumber();
   }

   @Override
   public GrammaticalEnum getUnkown()
   {
      return GrammaticalPerson.GRAMMATICALPERSON_UNKNOWN;
   }
}
