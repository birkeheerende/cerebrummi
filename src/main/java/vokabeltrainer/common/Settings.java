package vokabeltrainer.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import vokabeltrainer.panels.translation.TranslationCode;
import vokabeltrainer.types.Chapter.Database;
import vokabeltrainer.types.LanguageSettings;

public class Settings
{
   private static boolean soundOn = true;
   private static String chosenExpressionPath = null;
   private static float volume = -20;
   private static boolean letterImagesOn = true;
   private static boolean simpleHebrewInput = true;
   private static String version = "4.0";

   private static LinkedList<Database> oldChosenDatabases = new LinkedList<>();
   private static LinkedList<Database> chosenDatabases = new LinkedList<>();
   private static Database[] availableDatabases = {
         Database.GRUNDWORTSCHATZADAADAMA 
         };
   
   private static String rememberDatabaseForInput = "";
   private static String rememberChapterForInput = "";
   
   private static TranslationCode translationCode = TranslationCode.de_original;
   private static String anyName = null;
   
   private static boolean repetition_now = true;
   private static boolean repetition_one_day;
   private static boolean repetition_two_days;
   private static boolean repetition_five_days;
   private static boolean repetition_eleven_days;
   private static boolean repetition_nineteen_days;
   private static boolean repetition_one_month;
   private static boolean repetition_two_months;
   private static boolean repetition_five_months;
   private static boolean repetition_done = true;
   
   private static boolean schabbat_modus = true;

   private Settings()
   {
	  
   }

   public static String getVersion()
   {
      return version;
   }

   public static int dictionaryTableRowHeight()
   {
      return 250;
   }

   public static LanguageSettings getLanguage()
   {
      return LanguageSettings.GERMAN;
   }

   public static String getExpressionFolder()
   {
      return "cerebrummi-hebrewtrainer";
   }

   public static String getExpressionPath()
   {
      if (chosenExpressionPath == null)
      {
         return System.getProperty("user.home");
      }
      return chosenExpressionPath;
   }

   public static String getExpressionPathFolder()
   {
      return getExpressionPath() + File.separator + getExpressionFolder();
   }

   public static String getTrainingPath()
   {
      return getExpressionPath() + File.separator + getTrainingFolder();
   }

   private static String getTrainingFolder()
   {
      return getExpressionFolder() + "-training";
   }
   
   public static String getImagePath()
   {
      return getExpressionPath() + File.separator + getImageFolder();
   }

   private static String getImageFolder()
   {
      return getExpressionFolder() + "-images";
   }
   
   public static String getTranslationPath()
   {
      return getExpressionPath() + File.separator + getTranslationFolder();
   }
   
   private static String getTranslationFolder()
   {
      return getExpressionFolder() + "-languages";
   }

   public static int getKeyboardWidth()
   {
      return 474;
   }

   public static String getWindowTitle()
   {
      return "Cerebrummi©";
   }

   public static BufferedImage getSound()
   {
      if (soundOn)
      {
         return ApplicationImages.getSoundOn();
      }
      return ApplicationImages.getSoundOff();
   }

   public static boolean isSoundOn()
   {
      return soundOn;
   }

   public static void toggleSoundOnOff()
   {
      soundOn = !soundOn;
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getSoundNode(), soundOn);
   }

   public static void setSoundOn(boolean soundOn)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getSoundNode(), soundOn);
      Settings.soundOn = soundOn;
   }

   public static void setTranslationCode(TranslationCode translationCode)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.put(CerebrummiNodes.getTranslationLanguage(), translationCode.name());
      Settings.translationCode = translationCode;
   }

   public static TranslationCode getTranslationCode()
   {
	   return translationCode;
   }

   public static String getAnyName()
   {
      return anyName;
   }

   public static void setAnyName(String anyName)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.put(CerebrummiNodes.getAnyName(), anyName);
      Settings.anyName = anyName;
   }

   public static boolean isLetterImagesOn()
   {
      return letterImagesOn;
   }

   public static void toggleLetterImagesOnOff()
   {
      letterImagesOn = !letterImagesOn;
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getLetterPicturesNode(),
                  letterImagesOn);
   }

   public static void setLetterImagesOn(boolean letterImagesOn)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getLetterPicturesNode(),
                  letterImagesOn);
      Settings.letterImagesOn = letterImagesOn;
   }

   public static void setChoosenExpressionPath(String choosenExpressionPath)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .put(CerebrummiNodes.getChoosenExpressionPathNode(),
                  choosenExpressionPath);

      Settings.chosenExpressionPath = choosenExpressionPath;
   }

   public static void setChosenDatabases(List<Database> chosenDatabases)
   {
      Settings.chosenDatabases = new LinkedList<>();
      Settings.chosenDatabases.addAll(chosenDatabases);
   }

   public static boolean isDatabaseChoosen(Database database)
   {
      return Settings.chosenDatabases.contains(database);
   }

   public static LinkedList<Database> getChosenDatabases()
   {
      return Settings.chosenDatabases;
   }

   public static void addChosenDatabase(Database chosen)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      try
      {
         if (!preferences.nodeExists(CerebrummiNodes.getChoosenDatabases()))
         {
            preferences.put(CerebrummiNodes.getChoosenDatabases(), "");
         }
      }
      catch (BackingStoreException e)
      {
         // nothing
      }
      preferences = preferences.node(CerebrummiNodes.getChoosenDatabases());
      preferences.putBoolean(chosen.name().toLowerCase(), true);

      Settings.chosenDatabases.add(chosen);
   }

   public static void removeChosenDatabase(Database chosen)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      try
      {
         if (!preferences.nodeExists(CerebrummiNodes.getChoosenDatabases()))
         {
            preferences.put(CerebrummiNodes.getChoosenDatabases(), "");
         }
      }
      catch (BackingStoreException e)
      {
         // nothing
      }
      preferences = preferences.node(CerebrummiNodes.getChoosenDatabases());

      preferences.putBoolean(chosen.name().toLowerCase(), false);

      Settings.chosenDatabases.remove(chosen);
   }

   public static LinkedList<Database> getOldChosenDatabases()
   {
      return oldChosenDatabases;
   }

   public static void setOldChosenDatabases(
         LinkedList<Database> oldChosenDatabases)
   {
      Settings.oldChosenDatabases = oldChosenDatabases;
   }

   public static float getVolume()
   {
      return volume;
   }

   public static void setVolume(float volume)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putInt(CerebrummiNodes.getVolumeNode(), (int) volume);
      Settings.volume = volume;
   }

   public static List<Database> getAvailableDatabases()
   {
      return Arrays.asList(availableDatabases);
   }
   
   public static Database[] getAvailableDatabasesAsArray()
   {
      return availableDatabases;
   }

   public static boolean isSimpleHebrewInput()
   {
      return simpleHebrewInput;
   }

   public static void setSimpleHebrewInput(boolean simpleHebrewInput)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getSimpleHebrewNode(),
                  simpleHebrewInput);
      Settings.simpleHebrewInput = simpleHebrewInput;
   }

   public static void toggleSimpleHebrewInput()
   {
      simpleHebrewInput = !simpleHebrewInput;
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getSimpleHebrewNode(),
                  simpleHebrewInput);
   }

   public static String getRememberDatabaseForInput()
   {
      return rememberDatabaseForInput;
   }

   public static void setRememberDatabaseForInput(String rememberDatabaseForInput)
   {
      Settings.rememberDatabaseForInput = rememberDatabaseForInput;
   }

   public static String getRememberChapterForInput()
   {
      return rememberChapterForInput;
   }

   public static void setRememberChapterForInput(String rememberChapterForInput)
   {
      Settings.rememberChapterForInput = rememberChapterForInput;
   }

   public boolean isRepetition_now()
   {
      return repetition_now;
   }

   public static void setRepetition_now(boolean repetition_now)
   {
      // nothing
   }

   public static boolean isRepetition_one_day()
   {
      return Settings.repetition_one_day;
   }
   public static void setRepetition_one_day(boolean repetition_one_day)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getRepetitionOneDay(),
                  repetition_one_day);
      Settings.repetition_one_day = repetition_one_day;
   }
   public static void initRepetition_one_day(boolean repetition_one_day)
   {
      Settings.repetition_one_day = repetition_one_day;
   }

   public static boolean isRepetition_two_days()
   {
      return Settings.repetition_two_days;
   }
   public static void setRepetition_two_days(boolean repetition_two_days)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getRepetitionTwoDays(),
                  repetition_two_days);
      Settings.repetition_two_days = repetition_two_days;
   }
   public static void initRepetition_two_days(boolean repetition_two_days)
   {
      Settings.repetition_two_days = repetition_two_days;
   }

   public static boolean isRepetition_five_days()
   {
      return Settings.repetition_five_days;
   }
   public static void setRepetition_five_days(boolean repetition_five_days)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getRepetitionFiveDays(),
                  repetition_five_days);
      Settings.repetition_five_days = repetition_five_days;
   }
   public static void initRepetition_five_days(boolean repetition_five_days)
   {
      Settings.repetition_five_days = repetition_five_days;
   }

   public static boolean isRepetition_eleven_days()
   {
      return Settings.repetition_eleven_days;
   }
   public static void setRepetition_eleven_days(boolean repetition_eleven_days)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getRepetitionElevenDays(),
                  repetition_eleven_days);
      Settings.repetition_eleven_days = repetition_eleven_days;
   }
   public static void initRepetition_eleven_days(boolean repetition_eleven_days)
   {
      Settings.repetition_eleven_days = repetition_eleven_days;
   }

   public static boolean isRepetition_nineteen_days()
   {
      return Settings.repetition_nineteen_days;
   }
   public static void setRepetition_nineteen_days(boolean repetition_nineteen_days)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getRepetitionNineteenDays(),
                  repetition_nineteen_days);
      Settings.repetition_nineteen_days = repetition_nineteen_days;
   }
   public static void initRepetition_nineteen_days(boolean repetition_nineteen_days)
   {
      Settings.repetition_nineteen_days = repetition_nineteen_days;
   }

   public static boolean isRepetition_one_month()
   {
      return Settings.repetition_one_month;
   }
   public static void setRepetition_one_month(boolean repetition_one_month)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getRepetitionOneMonth(),
                  repetition_one_month);
      Settings.repetition_one_month = repetition_one_month;
   }
   public static void initRepetition_one_month(boolean repetition_one_month)
   {
      Settings.repetition_one_month = repetition_one_month;
   }

   public static boolean isRepetition_two_months()
   {
      return Settings.repetition_two_months;
   }
   public static void setRepetition_two_months(boolean repetition_two_months)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getRepetitionTwoMonths(),
                  repetition_two_months);
      Settings.repetition_two_months = repetition_two_months;
   }
   public static void initRepetition_two_months(boolean repetition_two_months)
   {
      Settings.repetition_two_months = repetition_two_months;
   }

   public static boolean isRepetition_five_months()
   {
      return Settings.repetition_five_months;
   }
   public static void setRepetition_five_months(boolean repetition_five_months)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getRepetitionFiveMonths(),
                  repetition_five_months);
      Settings.repetition_five_months = repetition_five_months;
   }
   public static void initRepetition_five_months(boolean repetition_five_months)
   {
      Settings.repetition_five_months = repetition_five_months;
   }
   
   public static boolean isRepetition_done()
   {
      return Settings.repetition_done;
   }
   public static void setRepetition_done(boolean repetition_done)
   {
      // nothing
   }

   public static boolean isSchabbat_modus()
   {
      return Settings.schabbat_modus;
   }

   public static void setSchabbat_modus(boolean schabbat_modus)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getSchabbatModus(),
                  schabbat_modus);
      Settings.schabbat_modus = schabbat_modus;
   }
   
   public static void initSchabbat_modus(boolean schabbat_modus)
   {
      Settings.schabbat_modus = schabbat_modus;
   }
}



















