package vokabeltrainer.types.grammatical;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public enum VerbTimes
      implements
      GrammaticalEnum
{
   PLEASE_CHOOSE(
         Translation.BITTE_WAEHLEN),
   VERBTIMES_UNKNOWN(
         Translation.UNBEKANNT),
   ROOT(
         Translation.WURZEL),
   INFINITIVE(
         Translation.INFINITIV),
   PAST(
         Translation.VERGANGENHEIT),
   PRESENT(
         Translation.GEGENWART),
   FUTURE(
         Translation.ZUKUNFT),
   IMPERARTIVE(
         Translation.BEFEHLSFORM),
   VERBTIMES_NA(
         Translation.NICHT_ANWENDBAR);

   private Translation description;

   VerbTimes(Translation description)
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
      case FUTURE:
      case IMPERARTIVE:
      case INFINITIVE:
      case PAST:
      case PRESENT:
      case ROOT:
         return translator.realisticTranslate(description);
      case VERBTIMES_UNKNOWN:
         return translator.realisticTranslate(Translation.ZEITFORM)
               + " " + translator.realisticTranslate(description);
      case VERBTIMES_NA:
      default:
         return "";
      }
   }

   @Override
   public VerbTimes fromEnumName(String name)
   {
      return VerbTimes.valueOf(name);
   }

   @Override
   public String toInfo()
   {
      Translator translator = Common.getTranslator();
      switch (this)
      {
      case FUTURE:
      case IMPERARTIVE:
      case INFINITIVE:
      case PAST:
      case PRESENT:
      case ROOT:
         return translator.realisticTranslate(description);
      case VERBTIMES_UNKNOWN:
      case VERBTIMES_NA:
      default:
         return "";
      }
   }

   @Override
   public GrammaticalParentEnum getParent()
   {
      return GrammaticalParentEnum.VERB_TIMES;
   }

   @Override
   public int getPrintOrderNumber()
   {
      return getParent().getSortNumber();
   }

   @Override
   public GrammaticalEnum getUnkown()
   {
      return VerbTimes.VERBTIMES_UNKNOWN;
   }
}
