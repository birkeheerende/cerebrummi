package vokabeltrainer.types;

public class TemporaryTrainingStatus
{
   private int trys;
   private int totalTrys;

   public TemporaryTrainingStatus()
   {
      trys = 1;
      totalTrys = 1;
   }
   
   public boolean isTrainingDone()
   {
      return true;
   }
   
   public boolean isTrainingStarted()
   {
      return false;
   }

   public Repetition getRepetition()
   {
      return Repetition.NONE;
   }

   public int getTrys()
   {
      return trys;
   }

   public void setTrys(int trys)
   {
      this.trys = trys;
   }

   public int getTotalTrys()
   {
      return totalTrys;
   }

   public void setTotalTrys(int totalTrys)
   {
      this.totalTrys = totalTrys;
   }
}
