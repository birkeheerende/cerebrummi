package vokabeltrainer.types.grammatical;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public interface GrammaticalEnum
{
   public String toDescription();

   public GrammaticalEnum fromEnumName(String name);

   public String name();

   public String toInfo();

   public GrammaticalParentEnum getParent();

   public int getPrintOrderNumber();
   
   public GrammaticalEnum getUnkown();

   public enum GrammaticalParentEnum
   {
      GENDER(
            10,
            Translation.GESCHLECHT),
      NUMERUS(
            20,
            Translation.NUMERUS),
      GRAMMATICAL_PERSON(
            30,
            Translation.GRAMMATISCHE_PERSON),
      BINJAN(
            40,
            Translation.BINJAN___STAMM),
      VERB_TIMES(
            50,
            Translation.ZEITFORM);

      private int sortNumber;
      private Translation identifier;

      GrammaticalParentEnum(int sortNumber, Translation identifier)
      {
         this.sortNumber = sortNumber;
         this.identifier = identifier;
      }

      public int getSortNumber()
      {
         return sortNumber;
      }

      public String getIdentifier()
      {
         Translator translator = Common.getTranslator();
         return translator.realisticTranslate(identifier);
      }
      
      public GrammaticalEnum getUnkown()
      {
         switch(this)
         {
         case BINJAN:
            return Binjan.BINJAN_UNKNOWN;
         case GENDER:
            return Gender.GENDER_UNKNOWN;
         case GRAMMATICAL_PERSON:
            return GrammaticalPerson.GRAMMATICALPERSON_UNKNOWN;
         case NUMERUS:
            return Numerus.NUMERUS_UNKNOWN;
         case VERB_TIMES:
            return VerbTimes.VERBTIMES_UNKNOWN;
         }
         return null;
      }
   }
}
