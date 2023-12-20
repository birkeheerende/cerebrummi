package vokabeltrainer.words;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import vokabeltrainer.editing.LetterForAnalysis;

public class WordLetterMatchingResult
{
   private boolean dictionaryEmpty;
   private boolean answerEmpty;
   
   private boolean completelyFalse;
   private boolean partlyFalse;
   private boolean okay;
   private int similarity;
   
   private int deltaCol;
   
   private List<LetterForAnalysis> dictionary = new LinkedList<>();
   private List<LetterForAnalysis> answer = new LinkedList<>();
   
   private List<BufferedImage> feedbackImageList = new LinkedList<>();

   public boolean isDictionaryEmpty()
   {
      return dictionaryEmpty;
   }

   public void setDictionaryEmpty(boolean dictionaryEmpty)
   {
      this.dictionaryEmpty = dictionaryEmpty;
   }

   public boolean isAnswerEmpty()
   {
      return answerEmpty;
   }

   public void setAnswerEmpty(boolean answerEmpty)
   {
      this.answerEmpty = answerEmpty;
   }

   public boolean isCompletelyFalse()
   {
      return completelyFalse;
   }

   public void setCompletelyFalse(boolean completelyFalse)
   {
      if(completelyFalse)
      {
         this.okay = false;
      }
      this.completelyFalse = completelyFalse;
   }

   public boolean isPartlyFalse()
   {
      return partlyFalse;
   }

   public void setPartlyFalse(boolean partlyFalse)
   {
      if(partlyFalse)
      {
         this.okay = false;
      }
      this.partlyFalse = partlyFalse;
   }

   public boolean isOkay()
   {
      return okay;
   }

   public void setOkay(boolean okay)
   {
      this.okay = okay;
   }

   public int getDeltaCol()
   {
      return deltaCol;
   }

   public void setDeltaCol(int deltaCol)
   {
      this.deltaCol = deltaCol;
   }

   public List<LetterForAnalysis> getDictionary()
   {
      return dictionary;
   }

   public void setDictionary(List<LetterForAnalysis> dictionary)
   {
      this.dictionary = dictionary;
   }

   public List<LetterForAnalysis> getAnswer()
   {
      return answer;
   }

   public void setAnswer(List<LetterForAnalysis> answer)
   {
      this.answer = answer;
   }

   public List<BufferedImage> getFeedbackImageList()
   {
      return feedbackImageList;
   }

   public void setFeedbackImageList(List<BufferedImage> feedbackImageList)
   {
      this.feedbackImageList = feedbackImageList;
   }

   public int getSimilarity()
   {
      return similarity;
   }

   public void setSimilarity(int similarity)
   {
      this.similarity = similarity;
   }
}
