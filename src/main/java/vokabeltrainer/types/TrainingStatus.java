package vokabeltrainer.types;

import java.time.LocalDate;
import java.time.Period;

import vokabeltrainer.common.Common;
import vokabeltrainer.common.Settings;

public class TrainingStatus
{
   private Repetition repetition;
   private int trys;
   private int totalTrys;
   private LocalDate nextDate;
   private boolean trainingStarted;

   public TrainingStatus(Repetition repetition)
   {
      if (!repetition.equals(Repetition.NOW))
      {
         throw new IllegalArgumentException(
               "This constructor is only for initilization of new training words");
      }
      this.repetition = Repetition.NOW;
      trys = 1;
      nextDate = LocalDate.now();
      trainingStarted = true;
   }

   public TrainingStatus(Repetition repetition, int trys, LocalDate nextDate)
   {
      this.repetition = repetition;
      this.trys = trys;
      this.nextDate = nextDate;
      trainingStarted = true;
   }

   public TrainingStatus()
   {
      trainingStarted = false;
   }

   public void nextRepetition()
   {
      LocalDate now = LocalDate.now();
      switch (repetition)
      {
      case NOW:
         repetition = Repetition.ONE_DAY;
         if (Settings.isRepetition_one_day())
         {
            LocalDate future = now.plus(Period.ofDays(1));
            if(!Settings.isSchabbat_modus() ^ !Common.isSchabbatPossible(future))
            {
               break;
            }
         }
      case ONE_DAY:
         repetition = Repetition.TWO_DAYS;
         if (Settings.isRepetition_two_days())
         {
            LocalDate future = now.plus(Period.ofDays(1));
            if(!Settings.isSchabbat_modus() ^ !Common.isSchabbatPossible(future))
            {
               break;
            }
         }
      case TWO_DAYS:
         repetition = Repetition.FIVE_DAYS;
         if (Settings.isRepetition_five_days())
         {
            LocalDate future = now.plus(Period.ofDays(1));
            if(!Settings.isSchabbat_modus() ^ !Common.isSchabbatPossible(future))
            {
               break;
            }
         }
      case FIVE_DAYS:
         repetition = Repetition.ELEVEN_DAYS;
         if (Settings.isRepetition_eleven_days())
         {
            LocalDate future = now.plus(Period.ofDays(1));
            if(!Settings.isSchabbat_modus() ^ !Common.isSchabbatPossible(future))
            {
               break;
            }
         }
      case ELEVEN_DAYS:
         repetition = Repetition.NINETEEN_DAYS;
         if (Settings.isRepetition_nineteen_days())
         {
            LocalDate future = now.plus(Period.ofDays(1));
            if(!Settings.isSchabbat_modus() ^ !Common.isSchabbatPossible(future))
            {
               break;
            }
         }
      case NINETEEN_DAYS:
         repetition = Repetition.ONE_MONTH;
         if (Settings.isRepetition_one_month())
         {
            LocalDate future = now.plus(Period.ofDays(1));
            if(!Settings.isSchabbat_modus() ^ !Common.isSchabbatPossible(future))
            {
               break;
            }
         }
      case ONE_MONTH:
         repetition = Repetition.TWO_MONTHS;
         if (Settings.isRepetition_two_months())
         {
            LocalDate future = now.plus(Period.ofDays(1));
            if(!Settings.isSchabbat_modus() ^ !Common.isSchabbatPossible(future))
            {
               break;
            }
         }
      case TWO_MONTHS:
         repetition = Repetition.FIVE_MONTHS;
         if (Settings.isRepetition_five_months())
         {
            LocalDate future = now.plus(Period.ofDays(1));
            if(!Settings.isSchabbat_modus() ^ !Common.isSchabbatPossible(future))
            {
               break;
            }
         }
      case FIVE_MONTHS:
         repetition = Repetition.DONE;
         break;
      case DONE:
         break;
      case NONE:
         break;
      default:
         break;
      }

      this.nextDate = now.plus(repetition.getPeriod());
   }

   public void previousRepetition()
   {
      LocalDate now = LocalDate.now();
      switch (repetition)
      {
      case DONE:
         break;
      case NONE:
         break;
      case FIVE_MONTHS:
         repetition = Repetition.TWO_MONTHS;
         if (Settings.isRepetition_two_months())
         {
            LocalDate future = now.plus(Period.ofDays(1));
            if(!Settings.isSchabbat_modus() ^ !Common.isSchabbatPossible(future))
            {
               break;
            }
         }
      case TWO_MONTHS:
         repetition = Repetition.ONE_MONTH;
         if (Settings.isRepetition_one_month())
         {
            LocalDate future = now.plus(Period.ofDays(1));
            if(!Settings.isSchabbat_modus() ^ !Common.isSchabbatPossible(future))
            {
               break;
            }
         }
      case ONE_MONTH:
         repetition = Repetition.NINETEEN_DAYS;
         if (Settings.isRepetition_nineteen_days())
         {
            LocalDate future = now.plus(Period.ofDays(1));
            if(!Settings.isSchabbat_modus() ^ !Common.isSchabbatPossible(future))
            {
               break;
            }
         }
      case NINETEEN_DAYS:
         repetition = Repetition.ELEVEN_DAYS;
         if (Settings.isRepetition_eleven_days())
         {
            LocalDate future = now.plus(Period.ofDays(1));
            if(!Settings.isSchabbat_modus() ^ !Common.isSchabbatPossible(future))
            {
               break;
            }
         }
      case ELEVEN_DAYS:
         repetition = Repetition.FIVE_DAYS;
         if (Settings.isRepetition_five_days())
         {
            LocalDate future = now.plus(Period.ofDays(1));
            if(!Settings.isSchabbat_modus() ^ !Common.isSchabbatPossible(future))
            {
               break;
            }
         }
      case FIVE_DAYS:
         repetition = Repetition.TWO_DAYS;
         if (Settings.isRepetition_two_days())
         {
            LocalDate future = now.plus(Period.ofDays(1));
            if(!Settings.isSchabbat_modus() ^ !Common.isSchabbatPossible(future))
            {
               break;
            }
         }
      case TWO_DAYS:
         repetition = Repetition.ONE_DAY;
         if (Settings.isRepetition_one_day())
         {
            LocalDate future = now.plus(Period.ofDays(1));
            if(!Settings.isSchabbat_modus() ^ !Common.isSchabbatPossible(future))
            {
               break;
            }
         }
      case ONE_DAY:
         repetition = Repetition.NOW;
         break;
      case NOW:
         break;
      }
      this.nextDate = now.plus(repetition.getPeriod());
   }

   public boolean isTrainingDone()
   {
      if (Repetition.DONE == this.repetition)
      {
         return true;
      }
      return false;
   }

   public boolean isTrainingStarted()
   {
      return trainingStarted;
   }

   public void setTrainingStarted(boolean trainingStarted)
   {
      this.trainingStarted = trainingStarted;
   }

   public Repetition getRepetition()
   {
      return repetition;
   }

   public void setRepetition(Repetition repetition)
   {
      this.repetition = repetition;
   }

   public LocalDate getNextDate()
   {
      return nextDate;
   }

   public void setNextDate(LocalDate nextDate)
   {
      this.nextDate = nextDate;
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
