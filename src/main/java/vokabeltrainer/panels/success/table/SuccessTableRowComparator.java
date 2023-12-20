package vokabeltrainer.panels.success.table;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Vector;

public class SuccessTableRowComparator implements Comparator<Vector<SuccessTableRow>>
{

   @Override
   public int compare(Vector<SuccessTableRow> o1, Vector<SuccessTableRow> o2)
   {
      Collator coll = Collator.getInstance(Locale.GERMAN);
      coll.setStrength(Collator.PRIMARY);
      return coll.compare(o1.get(0).getExpression().getChapterGermanComparison(),
            o2.get(0).getExpression().getChapterGermanComparison());
   }

}
