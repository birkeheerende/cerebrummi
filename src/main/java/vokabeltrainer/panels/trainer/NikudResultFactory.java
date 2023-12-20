package vokabeltrainer.panels.trainer;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import vokabeltrainer.InputHebrewPanel.Selection;
import vokabeltrainer.editing.LetterForAnalysis;
import vokabeltrainer.editing.LetterHelper;
import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.SelectionHebrewType;
import vokabeltrainer.words.WordLetterMatching;
import vokabeltrainer.words.WordLetterMatchingResult;

public class NikudResultFactory
{

   private NikudResultFactory()
   {
      // nothing
   }

   public static BestResult getBestResultPossible(Expression expression,
         String answer, Font hebrewFont)

   {
      if (expression.getHebrew().isSimpleHebrew())
      {
         BestResult bestResult = new BestResult(Selection.SIMPLE);
         bestResult.setResultHebrew(getResultDtoNikudSentence(expression,
               answer, hebrewFont, SelectionHebrewType.SIMPLE));
         return bestResult;
      }

      BestResult bestResult = new BestResult(Selection.PLENE_DEFEKTIV);
      bestResult.setResultPlene(getResultDtoNikudSentence(expression, answer,
            hebrewFont, SelectionHebrewType.PLENE));
      bestResult.setResultDefektiv(getResultDtoNikudSentence(expression, answer,
            hebrewFont, SelectionHebrewType.DEFEKTIV));
      return bestResult;
   }

   public static Result getResultDtoNikudSentence(Expression expression,
         String answer, Font hebrewFont, SelectionHebrewType selectionType)
   {
      String[] expressionArray = expression.getHebrew()
            .getHewbrewAccordingToType(selectionType)
            .split(NikudLetter.SPACE.getUnicode());

      String[] answerArray = answer.split(NikudLetter.SPACE.getUnicode());

      if (expressionArray.length == 1 && answerArray.length == 1)
      {
         return getResultDtoNikud(expression, answer, hebrewFont,
               selectionType);
      }

      if (expressionArray.length == answerArray.length)
      {
         Result result = new Result(selectionType);
         result.setExpression(expression);
         List<Result> resultList = new ArrayList<>(expressionArray.length);
         for (int i = expressionArray.length - 1; i >= 0; i--)
         {
            resultList.add(getResultDtoNikudString(expressionArray[i],
                  answerArray[i], new Result(selectionType), hebrewFont));
         }

         result.setOkay(true);
         result.setAnswerEmpty(true);
         result.setDictionaryEmpty(true);
         int index = 0;

         for (Result singleResult : resultList)
         {
            result.setOkay(result.isOkay() && singleResult.isOkay());
            result.setAnswerEmpty(
                  result.isAnswerEmpty() && singleResult.isAnswerEmpty());
            result.setDictionaryEmpty(result.isDictionaryEmpty()
                  && singleResult.isDictionaryEmpty());
            if (index > 0)
            {
               result.addFeedbackImage(LetterFeedbackImage.makeSpace());
               result.addAnswerSpace(new LetterForAnalysis(NikudLetter.SPACE));
               result.addDictionarySpace(new LetterForAnalysis(NikudLetter.SPACE));
            }
            result.addFeedbackImageList(singleResult.getFeedbackImageList());
            result.addAnswer(singleResult.getAnswer()); // for unit testing
            result.addDictionary(singleResult.getDictionary()); // for unit testing
            index++;
         }

         return result;
      }

      return getResultDtoNikud(expression, answer, hebrewFont, selectionType);
   }

   private static Result getResultDtoNikud(Expression expression, String answer,
         Font hebrewFont, SelectionHebrewType selectionType)
   {
      Result result = new Result(selectionType);
      result.setExpression(expression);

      return getResultDtoNikudString(
            expression.getHebrew().getHewbrewAccordingToType(selectionType),
            answer, result, hebrewFont);
   }

   private static Result getResultDtoNikudString(String dictionary,
         String answer, Result result, Font hebrewFont)
   {

      WordLetterMatchingResult matchingResult = WordLetterMatching.matchLetters(
            LetterHelper.findNikudLetterForAnalysisList(dictionary),
            LetterHelper.findNikudLetterForAnalysisList(answer));

      result.setAnswerEmpty(matchingResult.isAnswerEmpty());
      result.setDictionaryEmpty(matchingResult.isDictionaryEmpty());
      result.setOkay(matchingResult.isOkay());
      result.setSimilarity(matchingResult.getSimilarity());

      List<LetterForAnalysis> dictionaryList = matchingResult.getDictionary();
      List<LetterForAnalysis> answerList = matchingResult.getAnswer();

      result.setDictionary(matchingResult.getDictionary()); // for unit testing
      result.setAnswer(matchingResult.getAnswer()); // for unit testing

      List<BufferedImage> feedbackImageList = new ArrayList<BufferedImage>();
      for (int i = 0; i < dictionaryList.size() && i < answerList.size(); i++)
      {
         boolean letterresult = LetterHelper
               .areLettersEqual(dictionaryList.get(i), answerList.get(i));

         feedbackImageList.add(LetterFeedbackImage.make(dictionaryList.get(i),
               answerList.get(i), letterresult, hebrewFont));
         result.setOkay(result.isOkay() && letterresult);
      }
      result.setFeedbackImageList(feedbackImageList);
      return result;
   }
}
