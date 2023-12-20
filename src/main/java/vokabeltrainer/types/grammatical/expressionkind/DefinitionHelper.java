package vokabeltrainer.types.grammatical.expressionkind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import vokabeltrainer.types.grammatical.Binjan;
import vokabeltrainer.types.grammatical.Gender;
import vokabeltrainer.types.grammatical.GrammaticalEnum;
import vokabeltrainer.types.grammatical.GrammaticalPerson;
import vokabeltrainer.types.grammatical.Numerus;
import vokabeltrainer.types.grammatical.VerbTimes;

public class DefinitionHelper
{

   private DefinitionHelper()
   {

   }

   public static List<GrammaticalEnum> getDefinitionsNA(
         ExpressionKind expressionKind)
   {
      List<GrammaticalEnum> listNA = getDefinitionsUNKNOWN(expressionKind);
      List<GrammaticalEnum> list = new ArrayList<>();
      if (!listNA.contains(Binjan.BINJAN_UNKNOWN))
      {
         list.add(Binjan.BINJAN_NA);
      }
      if (!listNA.contains(Gender.GENDER_UNKNOWN))
      {
         list.add(Gender.GENDER_NA);
      }
      if (!listNA.contains(GrammaticalPerson.GRAMMATICALPERSON_UNKNOWN))
      {
         list.add(GrammaticalPerson.GRAMMATICALPERSON_NA);
      }
      if (!listNA.contains(Numerus.NUMERUS_UNKNOWN))
      {
         list.add(Numerus.NUMERUS_NA);
      }
      if (!listNA.contains(VerbTimes.VERBTIMES_UNKNOWN))
      {
         list.add(VerbTimes.VERBTIMES_NA);
      }
      return list;
   }

   public static List<GrammaticalEnum> getDefinitionsUNKNOWN(
         ExpressionKind expressionKind)
   {
      return Arrays.stream(expressionKind.getGrammaticalParentEnums())
                   .map(parent -> parent.getUnkown())
                   .collect(Collectors.toList());
   }
}
