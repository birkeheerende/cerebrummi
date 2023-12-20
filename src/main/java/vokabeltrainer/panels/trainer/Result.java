package vokabeltrainer.panels.trainer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import vokabeltrainer.editing.LetterForAnalysis;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.SelectionHebrewType;

public class Result
{
   private boolean okay = true;
   private boolean dictionaryEmpty;
   private boolean answerEmpty;
   private Expression expression;
   private SelectionHebrewType selectionType;
   private int similarity;
   
   private List<LetterForAnalysis> dictionary = new LinkedList<>(); // for unit testing
   private List<LetterForAnalysis> answer = new LinkedList<>(); // for unit testing
   
   private List<BufferedImage> feedbackImageList = new ArrayList<BufferedImage>();

   public Result(SelectionHebrewType selectionType)
   {
      this.selectionType = selectionType;
   }
   
   public boolean isOkay()
   {
      return okay;
   }

   public void setOkay(boolean okay)
   {
      this.okay = okay;
   }

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

   public Expression getExpression()
   {
      return expression;
   }

   public void setExpression(Expression expression)
   {
      this.expression = expression;
   }

   public List<BufferedImage> getFeedbackImageList()
   {
      return feedbackImageList;
   }

   public void setFeedbackImageList(List<BufferedImage> feedbackImageList)
   {
      this.feedbackImageList = feedbackImageList;
   }
   
   public void addFeedbackImageList(List<BufferedImage> list)
   {
      this.feedbackImageList.addAll(list);
   }
   
   public void addFeedbackImage(BufferedImage image)
   {
      this.feedbackImageList.add(image);
   }
   
   public int getWidth()
   {
      int width = 0;
      for (BufferedImage image : feedbackImageList)
      {
         width += image.getWidth();
      }
      return width;
   }

   public List<LetterForAnalysis> getDictionary()
   {
      return dictionary;
   }

   public void setDictionary(List<LetterForAnalysis> dictionary)
   {
      this.dictionary = dictionary;
   }
   
   public void addDictionary(List<LetterForAnalysis> dictionary)
   {
      this.dictionary.addAll(dictionary);
   }
   
   public void addDictionarySpace(LetterForAnalysis space)
   {
      this.dictionary.add(space);
   }

   public List<LetterForAnalysis> getAnswer()
   {
      return answer;
   }

   public void setAnswer(List<LetterForAnalysis> answer)
   {
      this.answer = answer;
   }
   
   public void addAnswer(List<LetterForAnalysis> answer)
   {
      this.answer.addAll(answer);
   }
   
   public void addAnswerSpace(LetterForAnalysis space)
   {
      this.answer.add(space);
   }

   public SelectionHebrewType getSelectionType()
   {
      return selectionType;
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
