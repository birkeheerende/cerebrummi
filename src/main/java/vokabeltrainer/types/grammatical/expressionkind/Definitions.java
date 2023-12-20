package vokabeltrainer.types.grammatical.expressionkind;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import vokabeltrainer.types.grammatical.Binjan;
import vokabeltrainer.types.grammatical.Gender;
import vokabeltrainer.types.grammatical.GrammaticalEnum;
import vokabeltrainer.types.grammatical.GrammaticalPerson;
import vokabeltrainer.types.grammatical.Numerus;
import vokabeltrainer.types.grammatical.VerbTimes;

public class Definitions
{
   private Map<ExpressionKind, Definition> definitions = new HashMap<>();

   public Definitions(Map<ExpressionKind, Definition> definitions)
   {
      this.definitions = definitions;
   }

   public Definitions()
   {

   }

   public boolean addExpressionKind(ExpressionKind expressionKind)
   {
      if (!definitions.containsKey(expressionKind))
      {
         definitions.put(expressionKind, new Definition(expressionKind));
         return true;
      }
      return false;
   }

   public void setGrammaticalEnum(ExpressionKind expressionKind,
         GrammaticalEnum e)
   {
      definitions.get(expressionKind).setGrammaticalEnum(e);
   }

   public GrammaticalEnum getGrammaticalEnum(ExpressionKind expressionKind,
         Class<? extends GrammaticalEnum> clazz)
   {
      return definitions.get(expressionKind).grammaticalEnumMap.get(clazz);
   }

   public String getGenderDescriptions()
   {
      return grammaticalEnumToDescription(Gender.class);
   }

   public String getNumerusDescriptions()
   {
      return grammaticalEnumToDescription(Numerus.class);
   }

   public String getBinjanDescriptions()
   {
      return grammaticalEnumToDescription(Binjan.class);
   }
   
   public String getVerbTimeDescriptions()
   {
      return grammaticalEnumToDescription(VerbTimes.class);
   }
   
   public String getGrammaticalPersonDescriptions()
   {
      return grammaticalEnumToDescription(GrammaticalPerson.class);
   }

   private String grammaticalEnumToDescription(
         Class<? extends GrammaticalEnum> clazz)
   {
      if (definitions.isEmpty())
      {
         return "";
      }
      return definitions
            .values()
            .stream()
            .findAny()
            .get()
            .getGrammaticalEnum(clazz)
            .toDescription();
   }

   public String getVerbConjugationInfos()
   {
      return grammaticalEnumToInfos(VerbTimes.class);
   }

   public String getBinjanInfos()
   {
      return grammaticalEnumToInfos(Binjan.class);
   }

   public String getNumerusInfos()
   {
      return grammaticalEnumToInfos(Numerus.class);
   }

   public String getGenderInfos()
   {
      return grammaticalEnumToInfos(Gender.class);
   }
   
   public String getGrammaticalPersonInfos()
   {
      return grammaticalEnumToInfos(GrammaticalPerson.class);
   }

   private String grammaticalEnumToInfos(Class<? extends GrammaticalEnum> clazz)
   {
      if (definitions.isEmpty())
      {
         return "";
      }
      return definitions
            .values()
            .stream()
            .findAny()
            .get()
            .getGrammaticalEnum(clazz)
            .toInfo();
   }
   
   public boolean isExpressionKindText()
   {
	   return definitions.keySet().stream().anyMatch(kind -> kind.equals(ExpressionKind.TEXT));
   }

   public String getExpressionKindDescriptions()
   {
      return definitions
            .keySet()
            .stream()
            .filter(kind -> !kind.toDescription().isEmpty())
            .map(kind -> kind.toDescription())
            .collect(Collectors.joining(", "));
   }

   public String getExpressionKindsForSaving()
   {
      return definitions
            .keySet()
            .stream()
            .map(kind -> kind.name())
            .collect(Collectors.joining(","));
   }

   public String getGrammaticalEnumsForSaving()
   {
      if (definitions.isEmpty())
      {
         return new Definition(ExpressionKind.EXPRESSIONKIND_UNKNOWN)
               .getGrammaticalEnumsForSaving();
      }
      return definitions
            .values()
            .stream()
            .findAny()
            .get()
            .getGrammaticalEnumsForSaving();
   }

   public String addGrammaticalEnumsForCopy(String tag)
   {
      if (definitions.isEmpty())
      {
         return "";
      }
      return definitions
            .values()
            .stream()
            .findAny()
            .get()
            .addGrammaticalEnumsForCopy(tag);
   }

   public String addExpressionKindsForCopy(String tag)
   {
      if (definitions.isEmpty())
      {
         return "";
      }
      return definitions
            .keySet()
            .stream()
            .map(kind -> kind.getDescription())
            .collect(Collectors.joining(tag));
   }

   public Set<ExpressionKind> getExpressionKindSet()
   {
      return definitions.keySet();
   }


}
