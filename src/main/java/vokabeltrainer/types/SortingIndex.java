package vokabeltrainer.types;

public class SortingIndex
{
   private static int counter = 0;
   
   private SortingIndex()
   {
      
   }

   public static int getCounter()
   {
      return ++counter;
   }

   public static void setCounter(String counterValue)
   {
      int counterValueInt;
      try
      {
         counterValueInt = Integer.valueOf(counterValue);
         if(counterValueInt > counter)
         {
            counter = counterValueInt;
         }
      }
      catch (Exception e)
      {
         // nothing
      }
   }

}
