package vokabeltrainer.panels.trainer;

import vokabeltrainer.InputHebrewPanel.Selection;

public class BestResult
{
   private Selection selection;
   private Result resultHebrew;
   private Result resultPlene;
   private Result resultDefektiv;
   
   public BestResult(Selection selection)
   {
      this.selection = selection;
   }

   public Selection getSelection()
   {
      return selection;
   }

  public void setResultHebrew(Result resultHebrew)
   {
      this.resultHebrew = resultHebrew;
   }

   public void setResultPlene(Result resultPlene)
   {
      this.resultPlene = resultPlene;
   }

   public void setResultDefektiv(Result resultDefektiv)
   {
      this.resultDefektiv = resultDefektiv;
   }

public Result getBestResult()
  {
     if(Selection.SIMPLE == selection)
     {
        return resultHebrew;
     }
     
     if(resultPlene.getSimilarity() >= resultDefektiv.getSimilarity())
     {
        return resultPlene;
     }
     
     return resultDefektiv;
  }

}
