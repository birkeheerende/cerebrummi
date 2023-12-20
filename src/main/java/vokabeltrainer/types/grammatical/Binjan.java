package vokabeltrainer.types.grammatical;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public enum Binjan
      implements
      GrammaticalEnum
{
   PLEASE_CHOOSE(
         Translation.BITTE_WAEHLEN),
   BINJAN_UNKNOWN(
         Translation.UNBEKANNT),
   PAAL(
         Translation.PA_AL___QAL),
   NIFAL(
         Translation.NIF_AL),
   HIFIL(
         Translation.HIF_IL),
   HUFAL(
         Translation.HUF_AL___HOFAL),
   PIEL(
         Translation.PI_EL),
   PUAL(
         Translation.PU_AL),
   HITPAEL(
         Translation.HITPA_EL),
   BINJAN_NA(
         Translation.NICHT_ANWENDBAR);

   private Translation description;

   Binjan(Translation description)
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
      case HIFIL:
      case HITPAEL:
      case HUFAL:
      case NIFAL:
      case PAAL:
      case PIEL:
      case PUAL:
         return translator.realisticTranslate(description);
      case BINJAN_UNKNOWN:
         return translator.realisticTranslate(Translation.BINJAN___STAMM)
               + " " + translator.realisticTranslate(description);
      case BINJAN_NA:
      default:
         return "";
      }
   }

   @Override
   public Binjan fromEnumName(String name)
   {
      return Binjan.valueOf(name);
   }

   @Override
   public String toInfo()
   {
      Translator translator = Common.getTranslator();
      switch (this)
      {
      case HIFIL:
      case HITPAEL:
      case HUFAL:
      case NIFAL:
      case PAAL:
      case PIEL:
      case PUAL:
         return translator.realisticTranslate(description);
      case BINJAN_UNKNOWN:
      case BINJAN_NA:
      default:
         return "";
      }
   }

   @Override
   public GrammaticalParentEnum getParent()
   {
      return GrammaticalParentEnum.BINJAN;
   }

   @Override
   public int getPrintOrderNumber()
   {
      return getParent().getSortNumber();
   }

   @Override
   public GrammaticalEnum getUnkown()
   {
      return Binjan.BINJAN_UNKNOWN;
   }
}
