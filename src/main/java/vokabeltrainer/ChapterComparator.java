package vokabeltrainer;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import vokabeltrainer.types.Chapter;

public class ChapterComparator implements Comparator<Chapter>
{

   @Override
   public int compare(Chapter o1, Chapter o2)
   {
      Collator coll = Collator.getInstance(Locale.GERMAN);
      coll.setStrength(Collator.PRIMARY);
      return coll.compare(o1.getName(), o2.getName());
   }

}
