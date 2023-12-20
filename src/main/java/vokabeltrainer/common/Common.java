package vokabeltrainer.common;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.swing.JFrame;

import vokabeltrainer.panels.MainView;
import vokabeltrainer.panels.translation.Translator;

public final class Common
{
   private static MainView mainJPanel;
   private static JFrame jFrame;
   private static boolean setMainJPanelOnlyOnce = false;
   private static boolean setJFrameOnlyOnce = false;
   private static Translator translator = new Translator();
   private static Settings settings;

   private Common()
   {

   }
   
   public static MainView getMainJPanel()
   {
      return mainJPanel;
   }

   static void setMainJPanel(MainView mainJPanel)
   {
      if (!setMainJPanelOnlyOnce)
      {
         Common.mainJPanel = mainJPanel;
      }
   }

   public static JFrame getjFrame()
   {
      return jFrame;
   }

   static void setjFrame(JFrame jFrame)
   {
      if (!setJFrameOnlyOnce)
      {
         Common.jFrame = jFrame;
      }
   }

   public static Translator getTranslator()
   {
      return translator;
   }

   public static void setTranslator(Translator translator)
   {
      Common.translator = translator;
   }

   public static Settings getSettings()
   {
      return settings;
   }

   public static boolean isSchabbat()
   {
      ZonedDateTime now = ZonedDateTime.now();
      DayOfWeek day = now.getDayOfWeek();
      int hour = now.getHour();
      if(day.equals(DayOfWeek.FRIDAY) && hour > 18)
      {
         return true;
      }
      else if (day.equals(DayOfWeek.SATURDAY) && hour < 18)
      {
         return true;
      }
      
      return false;
   }
   
   public static boolean isSchabbatPossible(LocalDate date)
   {
      ZonedDateTime now = ZonedDateTime.now();
      DayOfWeek day = now.getDayOfWeek();
      if(day.equals(DayOfWeek.FRIDAY) || day.equals(DayOfWeek.SATURDAY))
      {
         return true;
      }
      
      return false;
   }
}
