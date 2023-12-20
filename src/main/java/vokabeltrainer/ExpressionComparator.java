package vokabeltrainer;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;
import vokabeltrainer.types.SortingType;

public class ExpressionComparator implements Comparator<Expression>
{

   private Language language;
   private SortingType sortingType;

   public ExpressionComparator(Language language, SortingType sortingType)
   {
      this.language = language;
      this.sortingType = sortingType;
   }

   public ExpressionComparator(Language language)
   {
      this.language = language;
      this.sortingType = SortingType.ALPHABET;
   }
   
   public ExpressionComparator(SortingType sortingType)
   {
      this.sortingType = sortingType;
   }

   @Override
   public int compare(Expression o1, Expression o2)
   {
      switch (sortingType)
      {
      case DATE:
         return o2.getLastModified().compareTo(o1.getLastModified());
      case INDEX:
         int o1Int, o2Int;

         try
         {
            o1Int = Integer.valueOf(o1.getSortingIndex());
            o2Int = Integer.valueOf(o2.getSortingIndex());
            if (o1Int > o2Int)
            {
               return 1;
            }
            else if (o1Int < o2Int)
            {
               return -1;
            }
            return 0;
         }
         catch (Exception e)
         {
            // nothing
         }

         Collator coll = Collator.getInstance(Locale.GERMAN);
         coll.setStrength(Collator.PRIMARY);
         return coll.compare(o1.getSortingIndex(), o2.getSortingIndex());
      default:
         if (Language.GERMAN_TO_HEBREW.equals(language))
         {
            Collator coll2 = Collator.getInstance(Locale.GERMAN);
            coll2.setStrength(Collator.PRIMARY);
            return coll2.compare(o1.getGerman(), o2.getGerman());
         }
         else
         {
            return o1
                  .getHebrew()
                  .getHebrewNoMatterWhichKind()
                  .compareTo(o2.getHebrew().getHebrewNoMatterWhichKind());
         }
      }
   }

}
