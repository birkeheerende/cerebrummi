package vokabeltrainer.editing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LetterForAnalysis
{
   private NikudLetter content;
   private List<NikudLetter> listLowerPunktation = new ArrayList<>();
   private NikudLetter dagesh;
   private Set<NikudLetter> setUpperPunktation = new HashSet<>();

   public LetterForAnalysis(NikudLetter content)
   {
      this.content = content;
   }

   public NikudLetter getContent()
   {
      return content;
   }

   public LetterForAnalysis clone()
   {
      LetterForAnalysis duplicate = new LetterForAnalysis(content);
      duplicate.setListLowerPunktation(
            Collections.nCopies(1, listLowerPunktation).get(0));
      duplicate.setDagesh(dagesh);
      duplicate.getSetUpperPunktation().addAll(setUpperPunktation);
      return duplicate;
   }
   
   public static boolean isEqual(LetterForAnalysis letter1,LetterForAnalysis letter2)
   {
      if(letter1.getContent() == letter2.getContent())
      {
         return true;
      }
      return false;
   }

   public void addToLowerPunktation(NikudLetter punktation)
   {
      listLowerPunktation.add(punktation);
   }

   public void addDagesh(NikudLetter dagesh)
   {
      this.dagesh = dagesh;
   }

   public void addToUpperPunktation(NikudLetter punktation)
   {
      setUpperPunktation.add(punktation);
   }

   public List<NikudLetter> getListLowerPunktation()
   {
      return listLowerPunktation;
   }

   public NikudLetter getDagesh()
   {
      return dagesh;
   }

   public Set<NikudLetter> getSetUpperPunktation()
   {
      return setUpperPunktation;
   }

   public void setListLowerPunktation(List<NikudLetter> listLowerPunktation)
   {
      this.listLowerPunktation = listLowerPunktation;
   }

   public void setDagesh(NikudLetter dagesh)
   {
      this.dagesh = dagesh;
   }

   public void setSetUpperPunktation(Set<NikudLetter> setUpperPunktation)
   {
      this.setUpperPunktation = setUpperPunktation;
   }

   public int getPixelWidth()
   {
      return content.getPixelWidth();
   }
}
