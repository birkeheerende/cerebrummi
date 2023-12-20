package vokabeltrainer.types.grammatical.expressionkind;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import vokabeltrainer.GrammaticalEnumComparator;
import vokabeltrainer.types.grammatical.Binjan;
import vokabeltrainer.types.grammatical.Gender;
import vokabeltrainer.types.grammatical.GrammaticalEnum;
import vokabeltrainer.types.grammatical.GrammaticalPerson;
import vokabeltrainer.types.grammatical.Numerus;
import vokabeltrainer.types.grammatical.VerbTimes;

public class Definition
{
   private ExpressionKind expressionKind;
   protected Map<Class<? extends GrammaticalEnum>, GrammaticalEnum> grammaticalEnumMap = new HashMap<>();

   public Definition(ExpressionKind expressionKind)
   {
      this.expressionKind = expressionKind;
      fillGrammaticalEnumMap();
   }

   private void fillGrammaticalEnumMap()
   {
      for (GrammaticalEnum ge : DefinitionHelper
            .getDefinitionsNA(expressionKind))
      {
         grammaticalEnumMap.put(ge.getClass(), ge);
      }

      for (GrammaticalEnum ge : DefinitionHelper
            .getDefinitionsUNKNOWN(expressionKind))
      {
         grammaticalEnumMap.put(ge.getClass(), ge);
      }
   }

   public void setGrammaticalEnum(GrammaticalEnum e)
   {
      grammaticalEnumMap.put(e.getClass(), e);
   }

   public GrammaticalEnum getGrammaticalEnum(
         Class<? extends GrammaticalEnum> clazz)
   {
      return grammaticalEnumMap.get(clazz);
   }

   public String addGrammaticalEnumsForCopy(String tag)
   {
      return grammaticalEnumMap.values().stream()
            .sorted(new GrammaticalEnumComparator())
            .filter(gE -> !gE.toInfo().isBlank())
            .map(gE -> gE.getParent().getIdentifier() + ": " + gE.toInfo())
            .collect(Collectors.joining(tag));
   }

   public String getGrammaticalEnumsForSaving()
   {
      StringJoiner joiner = new StringJoiner("\t");
      joiner.add(grammaticalEnumMap.get(Gender.class).name());
      joiner.add(grammaticalEnumMap.get(Numerus.class).name());
      joiner.add(grammaticalEnumMap.get(GrammaticalPerson.class).name());
      joiner.add(grammaticalEnumMap.get(Binjan.class).name());
      joiner.add(grammaticalEnumMap.get(VerbTimes.class).name());
      return joiner.toString();
   }

}
