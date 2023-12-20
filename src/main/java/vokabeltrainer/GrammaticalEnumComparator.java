package vokabeltrainer;

import java.util.Comparator;

import vokabeltrainer.types.grammatical.GrammaticalEnum;

public class GrammaticalEnumComparator implements Comparator<GrammaticalEnum>
{

   @Override
   public int compare(GrammaticalEnum o1, GrammaticalEnum o2)
   {
      if(o1.getPrintOrderNumber() > o2.getPrintOrderNumber())
      {
         return 1;
      }
      if(o1.getPrintOrderNumber() < o2.getPrintOrderNumber())
      {
         return -1;
      }
      return 0;
   }
}
