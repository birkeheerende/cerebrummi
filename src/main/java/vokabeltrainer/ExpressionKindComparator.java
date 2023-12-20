package vokabeltrainer;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

public class ExpressionKindComparator implements Comparator<ExpressionKind>
{

   @Override
   public int compare(ExpressionKind o1, ExpressionKind o2)
   {
      if (o1.equals(ExpressionKind.EXPRESSIONKIND_UNKNOWN)
            && o2.equals(ExpressionKind.EXPRESSIONKIND_UNKNOWN))
      {
         return 0;
      }
      if (o1.equals(ExpressionKind.EXPRESSIONKIND_UNKNOWN))
      {
         return -1;
      }
      if (o2.equals(ExpressionKind.EXPRESSIONKIND_UNKNOWN))
      {
         return 1;
      }

      Collator coll = Collator.getInstance(Locale.GERMAN);
      coll.setStrength(Collator.PRIMARY);
      return coll.compare(o1.getDescription(), o2.getDescription());
   }
   
}
