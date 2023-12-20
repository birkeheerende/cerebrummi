package vokabeltrainer.types.grammatical;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public enum Numerus implements GrammaticalEnum
{
   PLEASE_CHOOSE(
         Translation.BITTE_WAEHLEN),
   NUMERUS_UNKNOWN(
         Translation.UNBEKANNT),
   SINGULAR(
         Translation.SINGULAR),
   DUAL(
         Translation.DUAL),
   PLURAL(
         Translation.PLURAL),
   NUMERUS_NA(
         Translation.NICHT_ANWENDBAR);

   private Translation description;

   Numerus(Translation description)
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
      case DUAL:
      case PLURAL:
      case SINGULAR:
         return translator.realisticTranslate(description);
      case NUMERUS_UNKNOWN:
         return translator.realisticTranslate(Translation.NUMERUS)
               + " " + translator.realisticTranslate(description);
      case NUMERUS_NA:
      default:
         return "";
      }
   }
   
   public String toInfo()
   {
      Translator translator = Common.getTranslator();
      switch (this)
      {
      case DUAL:
      case PLURAL:
      case SINGULAR:
         return translator.realisticTranslate(description);
      case NUMERUS_UNKNOWN:
      case NUMERUS_NA:
      default:
         return "";
      }
   }
   
   @Override
   public Numerus fromEnumName(String name)
   {
      return Numerus.valueOf(name);
   }

   @Override
   public GrammaticalParentEnum getParent()
   {
      return GrammaticalParentEnum.NUMERUS;
   }
   
   @Override
   public int getPrintOrderNumber()
   {
      return getParent().getSortNumber();
   }

   @Override
   public GrammaticalEnum getUnkown()
   {
      return Numerus.NUMERUS_UNKNOWN;
   }
}
