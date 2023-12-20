package vokabeltrainer.types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.regex.Pattern;

import vokabeltrainer.types.Chapter.Database;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Letter;
import vokabeltrainer.common.LetterForSaving;
import vokabeltrainer.common.Settings;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.types.grammatical.expressionkind.Definitions;

public class Expression
{
   private UUID uuid;
   private String german;
   private Hebrew hebrew = new Hebrew();
   private List<String> searchwordsGerman = new ArrayList<>();
   private List<String> searchwordsHebrew = new ArrayList<>();
   private TrainingStatus trainingStatusDToH = new TrainingStatus();
   private TrainingStatus trainingStatusHToD = new TrainingStatus();
   private Chapter chapter;
   private boolean selected;
   private boolean doNotChange;
   private Database origin;
   private Definitions definitions;
   private LetterForSaving letterForSaving;
   private String additionalInformation;
   private LocalDateTime lastModified;
   private String sortingIndex = "";
   private Translator translator = Common.getTranslator();

   public Expression(boolean preset) // for unit testing
   {
      this.doNotChange = true;

      if (preset)
      {
         uuid = UUID.randomUUID();
         german = "";
         chapter = new Chapter(Database.SELF);
         definitions = new Definitions();
         lastModified = LocalDateTime.now();
      }
   }

   public Expression(boolean preset, boolean doNotChange)
   {
      this.doNotChange = doNotChange;

      if (preset)
      {
         uuid = UUID.randomUUID();
         german = "";
         chapter = new Chapter(Database.SELF);
         definitions = new Definitions();
         lastModified = LocalDateTime.now();
         hebrew.setSimpleHebrew(Settings.isSimpleHebrewInput());
      }
   }

   public void setSearchwordsGerman(String[] searchwords)
   {
      for (int i = 0; i < searchwords.length; i++)
      {
         this.searchwordsGerman.add(searchwords[i].trim());
      }
   }

   public void setSearchwordsHebrew(String[] searchwords)
   {
      for (int i = 0; i < searchwords.length; i++)
      {
         this.searchwordsHebrew.add(searchwords[i].trim());
      }
   }

   public UUID getUuid()
   {
      return uuid;
   }

   public void setUuid(UUID uuid)
   {
      this.uuid = uuid;
   }

   public String getGerman()
   {
      return german;
   }

   public void setGerman(String german)
   {
      this.german = german;
   }

   public Hebrew getHebrew()
   {
      return hebrew;
   }

   public void setHebrew(Hebrew hebrew)
   {
      this.hebrew = hebrew;
   }

   public List<String> getSearchwordsGerman()
   {
      return searchwordsGerman;
   }

   public void setSearchwordsGerman(List<String> searchwordsGerman)
   {
      this.searchwordsGerman = searchwordsGerman;
   }

   public List<String> getSearchwordsHebrew()
   {
      return searchwordsHebrew;
   }

   public void setSearchwordsHebrew(List<String> searchwordsHebrew)
   {
      this.searchwordsHebrew = searchwordsHebrew;
   }

   public TrainingStatus getTrainingStatusDToH()
   {
      return trainingStatusDToH;
   }

   public void setTrainingStatusDToH(TrainingStatus trainingStatusDToH)
   {
      this.trainingStatusDToH = trainingStatusDToH;
   }

   public TrainingStatus getTrainingStatusHToD()
   {
      return trainingStatusHToD;
   }

   public void setTrainingStatusHToD(TrainingStatus trainingStatusHToD)
   {
      this.trainingStatusHToD = trainingStatusHToD;
   }

   public TemporaryTrainingStatus getTemporaryTrainingStatus() 
   {
      return new TemporaryTrainingStatus();
   }

   public void setTemporaryTrainingStatus(TemporaryTrainingStatus temporaryTrainingStatus) 
   {
	 // nothing
   }

   public Chapter getChapter()
   {
      return chapter;
   }

   public void setChapter(Chapter chapter)
   {
      this.chapter = chapter;
   }

   public String getChapterGermanComparison()
   {
      return chapter.getName() + " " + german;
   }

   public boolean isSelected()
   {
      return selected;
   }

   public void setSelected(boolean selected)
   {
      this.selected = selected;
   }

   public void toggleSelected()
   {
      this.selected = !this.selected;
   }

   public boolean isDoNotChange()
   {
      return doNotChange;
   }

   public boolean isDoChange()
   {
      return !doNotChange;
   }

   public Database getOrigin()
   {
      return origin;
   }

   public void setOrigin(Database origin)
   {
      this.origin = origin;
   }

   public Definitions getDefinitions()
   {
      return definitions;
   }

   public void setDefinitions(Definitions definitions)
   {
      this.definitions = definitions;
   }

   public LetterForSaving getLetterForSaving()
   {
      return letterForSaving;
   }

   public void setLetterForSaving(Letter letterForSaving)
   {
      if (letterForSaving instanceof LetterForSaving)
      {
         this.letterForSaving = (LetterForSaving) letterForSaving;
      }
   }

   public String getAdditionalInformation()
   {
      return additionalInformation;
   }

   public void setAdditionalInformation(String additionalInformation)
   {
      this.additionalInformation = additionalInformation;
   }

   public LocalDateTime getLastModified()
   {
      return lastModified;
   }

   public void setLastModified(LocalDateTime lastModified)
   {
      this.lastModified = lastModified;
   }

   public void toggleLastModified()
   {
      this.lastModified = LocalDateTime.now();
   }

   public String getSortingIndex()
   {
      return sortingIndex;
   }

   public void setSortingIndex(String sortingIndex)
   {
      this.sortingIndex = sortingIndex;
   }

   public String[] toGermanArrayForTableEntry()
   {
      int index = 0;
      String[] result = new String[10];
      result[index] = String.valueOf(selected);
      index++;
      result[index] = german;
      index++;
      result[index] = hebrew.toString();
      index++;
      result[index] = hebrew.toTableEntry();
      index++;
      result[index] = definitions.getGenderDescriptions();
      index++;
      result[index] = definitions.getNumerusDescriptions() + " "
            + definitions.getGrammaticalPersonDescriptions();
      index++;
      StringJoiner joiner = new StringJoiner(", ");
      if (!definitions.getBinjanDescriptions().isBlank())
      {
         joiner.add(definitions.getBinjanDescriptions());
      }
      if (!definitions.getVerbTimeDescriptions().isBlank())
      {
         joiner.add(definitions.getVerbTimeDescriptions());
      }
      result[index] = joiner.toString();
      index++;
      result[index] = definitions.getExpressionKindDescriptions();
      index++;
      result[index] = translator.realisticTranslate(Translation.KAPITEL) + ": "
            + chapter.getName() + ", "
            + translator.realisticTranslate(Translation.INDEX) + ": "
            + sortingIndex;
      index++;
      result[index] = chapter.getDatabaseName() + " "
            + translator.realisticTranslate(Translation.VOM) + " "
            + lastModified.format(DateTimeFormatter.ofPattern(
                  translator.realisticTranslate(Translation._DATE_TIME)));
      return result;
   }

   public String[] toHebrewArrayForTableEntry()
   {
      int index = 0;
      String[] result = new String[10];
      result[index] = String.valueOf(selected);
      index++;
      result[index] = hebrew.toString();
      index++;
      result[index] = german;
      index++;
      result[index] = hebrew.toTableEntry();
      index++;
      result[index] = definitions.getGenderDescriptions();
      index++;
      result[index] = definitions.getNumerusDescriptions() + " "
            + definitions.getGrammaticalPersonDescriptions();
      index++;
      StringJoiner joiner = new StringJoiner(", ");
      if (!definitions.getBinjanDescriptions().isBlank())
      {
         joiner.add(definitions.getBinjanDescriptions());
      }
      if (!definitions.getVerbTimeDescriptions().isBlank())
      {
         joiner.add(definitions.getVerbTimeDescriptions());
      }
      result[index] = joiner.toString();
      index++;
      result[index] = definitions.getExpressionKindDescriptions();
      index++;
      result[index] = translator.realisticTranslate(Translation.KAPITEL) + ": "
            + chapter.getName() + ", "
            + translator.realisticTranslate(Translation.INDEX) + ": "
            + sortingIndex;
      index++;
      result[index] = chapter.getDatabaseName() + " "
            + translator.realisticTranslate(Translation.VOM) + " "
            + lastModified.format(DateTimeFormatter.ofPattern(
                  translator.realisticTranslate(Translation._DATE_TIME)));
      return result;
   }

   public String getAdditionalInfo()
   {
      return additionalInformation;
   }

   public String getGrammarInfo(boolean withExpressionKind)
   {
      StringJoiner joiner = new StringJoiner(", ");
      if (!definitions.getNumerusInfos().isEmpty())
      {
         joiner.add(definitions.getNumerusInfos());
      }
      if (!definitions.getGenderInfos().isEmpty())
      {
         joiner.add(definitions.getGenderInfos());
      }
      if (!definitions.getGrammaticalPersonInfos().isEmpty())
      {
         joiner.add(definitions.getGrammaticalPersonInfos());
      }
      if (!definitions.getBinjanInfos().isEmpty())
      {
         joiner.add(definitions.getBinjanInfos());
      }
      if (!definitions.getVerbConjugationInfos().isEmpty())
      {
         joiner.add(definitions.getVerbConjugationInfos());
      }
      if (withExpressionKind
            && !definitions.getExpressionKindDescriptions().isBlank())
      {
         joiner.add(definitions.getExpressionKindDescriptions());
      }
      return joiner.toString();
   }

   public String getExpressionPrintLineForSaving()
   {
      StringJoiner joiner = new StringJoiner("\t");
      joiner.add(uuid.toString());
      joiner.add(chapter.getOrigin().name());
      joiner.add(chapter.getDatabaseName());
      joiner.add(chapter.getName());
      joiner.add(german);
      joiner.add(String.valueOf(hebrew.isSimpleHebrew()));
      joiner.add(hebrew.getHebrew());
      joiner.add(hebrew.getHebrewPlene());
      joiner.add(hebrew.getHebrewDefektiv());
      joiner.add(definitions.getExpressionKindsForSaving());
      joiner.add(definitions.getGrammaticalEnumsForSaving());
      joiner.add(additionalInformation);
      joiner.add(getSearchWordsGermanForSaving());
      joiner.add(getSearchWordsHebrewForSaving());
      joiner.add(lastModified.toString());
      joiner.add(sortingIndex);
      return joiner.toString();
   }

   public String getExpressionPrintLineForSaving(String databaseName)
   {
      StringJoiner joiner = new StringJoiner("\t");
      joiner.add(uuid.toString());
      Database db = Chapter.findOrigin(databaseName);
      joiner.add(db.name());
      joiner.add(databaseName);
      joiner.add(chapter.getName());
      joiner.add(german);
      joiner.add(String.valueOf(hebrew.isSimpleHebrew()));
      joiner.add(hebrew.getHebrew());
      joiner.add(hebrew.getHebrewPlene());
      joiner.add(hebrew.getHebrewDefektiv());
      joiner.add(definitions.getExpressionKindsForSaving());
      joiner.add(definitions.getGrammaticalEnumsForSaving());
      joiner.add(additionalInformation);
      joiner.add(getSearchWordsGermanForSaving());
      joiner.add(getSearchWordsHebrewForSaving());
      joiner.add(lastModified.toString());
      joiner.add(sortingIndex);
      return joiner.toString();
   }

   private String getSearchWordsGermanForSaving()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (String word : this.searchwordsGerman)
      {
         joiner.add(word);
      }
      return joiner.toString();
   }

   private String getSearchWordsHebrewForSaving()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (String word : this.searchwordsHebrew)
      {
         joiner.add(word);
      }
      return joiner.toString();
   }

   public String getCopyLines(Language language)
   {
      StringJoiner joiner = new StringJoiner("\t");
      if (Language.GERMAN_TO_HEBREW.equals(language))
      {
         joiner.add(german);
         joiner.add(hebrew.toString());
      }
      else
      {
         joiner.add(hebrew.toString());
         joiner.add(german);
      }
      joiner.add(chapter.getName());
      joiner.add(definitions.addExpressionKindsForCopy(", "));
      joiner.add(definitions.addGrammaticalEnumsForCopy(", "));
      StringJoiner searchJoinerGerman = new StringJoiner(", ");
      for (String word : searchwordsGerman)
      {
         searchJoinerGerman.add(word);
      }
      joiner.add(translator.realisticTranslate(Translation.SUCHWORTE)
            + " "
            + translator.realisticTranslate(Translation.DEUTSCH)
            + ": " + searchJoinerGerman.toString());
      StringJoiner searchJoinerHebrew = new StringJoiner(", ");
      for (String word : searchwordsHebrew)
      {
         searchJoinerHebrew.add(word);
      }
      joiner.add(translator.realisticTranslate(Translation.SUCHWORTE)
            + " "
            + translator.realisticTranslate(Translation.HEBRAEISCH)
            + ": " + searchJoinerHebrew.toString());
      if (!additionalInformation.isBlank())
      {
         joiner.add(additionalInformation);
      }
      return joiner.toString();
   }

   public String getTrainingPrintLine(Language languageDirection)
   {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
      StringJoiner joiner = new StringJoiner("\t");
      joiner.add(this.uuid.toString());
      if (Language.GERMAN_TO_HEBREW.equals(languageDirection))
      {
         joiner.add(
               this.trainingStatusDToH.getNextDate().format(dateTimeFormatter));
         joiner.add(this.trainingStatusDToH.getRepetition().name());
         joiner.add(String.valueOf(this.trainingStatusDToH.getTrys()));
      }
      else
      {
         joiner.add(
               this.trainingStatusHToD.getNextDate().format(dateTimeFormatter));
         joiner.add(this.trainingStatusHToD.getRepetition().name());
         joiner.add(String.valueOf(this.trainingStatusHToD.getTrys()));
      }
      return joiner.toString();
   }

   public TrainingStatus getTrainingStatus(Language languageDirection)
   {
      if (Language.GERMAN_TO_HEBREW.equals(languageDirection))
      {
         return this.getTrainingStatusDToH();
      }
      return this.getTrainingStatusHToD();
   }

   public String getAdditionalInfoGermanForStatistics()
   {
      StringJoiner joiner = new StringJoiner(", ");
      if (!definitions.getNumerusInfos().isEmpty())
      {
         joiner.add(definitions.getNumerusInfos());
      }
      if (!definitions.getGenderInfos().isEmpty())
      {
         joiner.add(definitions.getGenderInfos());
      }
      return joiner.toString();
   }

   public String getWordGermanForStatistics(Language language)
   {
      if (Language.GERMAN_TO_HEBREW.equals(language))
      {
    	 if(this.getTrainingStatusDToH().getRepetition() == null)
    	 {
    		  return "Fehler bei " + german;
    	 }
    	 
         return german + "   [" + this.getTrainingStatusDToH().getTrys() + " "
               + translator.realisticTranslate(Translation.MAL) + " "
               + this.getTrainingStatusDToH().getRepetition().getTranslation()
               + "]  [" + chapter.getName() + "]   "
               + this.getAdditionalInfoGermanForStatistics();
      }
      else
      {
         return german + "   [" + this.getTrainingStatusHToD().getTrys() + " "
               + translator.realisticTranslate(Translation.MAL) + " "
               + this.getTrainingStatusHToD().getRepetition().getTranslation()
               + "]  [" + chapter.getName() + "]   "
               + this.getAdditionalInfoGermanForStatistics();
      }
   }

   public String[] getGermanHebrewGrammarArrayForSuccess()
   {
      String[] content = new String[3];
      content[0] = german;
      content[1] = hebrew.toString();
      content[2] = getGrammarInfo(true);
      return content;
   }

   public boolean findPattern(Pattern pattern)
   {
      if (pattern.matcher(german).find())
      {
         return true;
      }
      if (pattern.matcher(hebrew.getHebrew()).find())
      {
         return true;
      }
      if (pattern.matcher(hebrew.getHebrewPlene()).find())
      {
         return true;
      }
      if (pattern.matcher(hebrew.getHebrewDefektiv()).find())
      {
         return true;
      }
      return false;
   }
}
