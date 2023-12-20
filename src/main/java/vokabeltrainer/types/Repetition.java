package vokabeltrainer.types;

import java.time.Period;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public enum Repetition
{
   NONE(null,null),
   NOW(
         Period.ZERO, Translation.HEUTE),
   ONE_DAY(
         Period.ofDays(1), Translation.MORGEN),
   TWO_DAYS(
         Period.ofDays(2), Translation._2_TAGE),
   FIVE_DAYS(
         Period.ofDays(5), Translation._5_TAGE),
   ELEVEN_DAYS(
         Period.ofDays(11), Translation._11_TAGE),
   NINETEEN_DAYS(
         Period.ofDays(19), Translation._19_TAGE),
   ONE_MONTH(
         Period.ofMonths(1), Translation._1_MONAT),
   TWO_MONTHS(
         Period.ofMonths(2), Translation._2_MONATE),
   FIVE_MONTHS(
         Period.ofMonths(5), Translation._5_MONATE),
   DONE(
         Period.ofYears(100), Translation.FERTIG);
   
   private Period period;
   private Translation translation;

   Repetition(Period period, Translation translation)
   {
      this.period = period;
      this.translation = translation;
   }

   public Period getPeriod()
   {
      return period;
   }

   public String getTranslation()
   {
      Translator translator = Common.getTranslator();
      return translator.realisticTranslate(translation);
   }
}
