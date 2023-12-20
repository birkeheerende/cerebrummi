package vokabeltrainer.words;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import vokabeltrainer.editing.LetterForAnalysis;
import vokabeltrainer.editing.NikudLetter;

public class WordLetterMatching
{
   private WordLetterMatching()
   {

   }

   public static WordLetterMatchingResult matchLetters(
         LinkedList<LetterForAnalysis> dictionary,
         LinkedList<LetterForAnalysis> answer)
   {
      WordLetterMatchingResult result = new WordLetterMatchingResult();
      
      if (answer.isEmpty())
      {
         result.setAnswerEmpty(true);
      }

      if (dictionary.isEmpty())
      {
         result.setDictionaryEmpty(true);
      }
      
      if (answer.isEmpty() && dictionary.isEmpty())
      {
         result.setOkay(true);
         return result;
      }
      
      if (answer.isEmpty() || dictionary.isEmpty())
      {
         result.setCompletelyFalse(true);
         return result;
      }

      result.setSimilarity(calculateSamenessPunish(dictionary, answer));

      int sizeDic = dictionary.size();
      int sizeTest = answer.size();

      int numberOfCols = sizeDic + 2 * sizeTest;
      int numberOfRows = 2 + sizeDic + sizeTest;

      LetterForAnalysis[][] data = new LetterForAnalysis[numberOfRows][numberOfCols];

      // put the correct word into the first row of the datagrid
      Collections.reverse(dictionary);
      for (int i = sizeTest, l = 0; l < dictionary.size(); i++, l++)
      {
         data[0][i] = dictionary.get(l);
      }
      Collections.reverse(dictionary);

      // put test word into the data grid
      for (int row = 1, deltaCol = 1; row < numberOfRows; row++, deltaCol++)
      {
         int col = numberOfCols - deltaCol;
         for (int l = 0; l < sizeTest; l++)
         {
            data[row][col] = answer.get(l);
            col--;
         }
      }

      int rowMax = 1;
      int rowSameMaxValue = 0;
      int rowDiffValueAtSameMax = 0;
      int deltaColMin = Math.abs(sizeTest);
      int deltaColValue = Math.abs(sizeTest);

      // compare words
      for (int row = 1; row < numberOfRows; row++)
      {
         int rowSameValue = 0;
         int rowDiffValue = 0;
         for (int col = 0; col < numberOfCols; col++)
         {
            rowSameValue += evaluateSame(data[0][col], data[row][col]);
            rowDiffValue += evaluateDifference(data[0][col], data[row][col]);
         }

         if (rowSameValue > rowSameMaxValue || (rowSameValue == rowSameMaxValue
               && Math.abs(deltaColValue) < Math.abs(deltaColMin)))
         {
            rowSameMaxValue = rowSameValue;
            rowDiffValueAtSameMax = rowDiffValue;
            rowMax = row;
            deltaColMin = Math.abs(deltaColValue);
         }
         deltaColValue--;
      }

      if (rowSameMaxValue == 0)
      {
         result.setCompletelyFalse(true);
      }
      else if (sizeDic == rowSameMaxValue && rowDiffValueAtSameMax == 0)
      {
         result.setOkay(true);
         Collections.reverse(dictionary);
         Collections.reverse(answer);
         result.setDictionary(dictionary);
         result.setAnswer(answer);
         return result;
      }
      else
      {
         result.setPartlyFalse(true);
      }

      List<LetterForAnalysis> dataDic = new LinkedList<>();
      List<LetterForAnalysis> dataTest = new LinkedList<>();

      for (LetterForAnalysis letter : data[0])
      {
         if (letter == null)
         {
            dataDic.add(new LetterForAnalysis(NikudLetter.NEWSPACE));
         }
         else
         {
            dataDic.add(letter);
         }
      }

      for (LetterForAnalysis letter : data[rowMax])
      {
         if (letter == null)
         {
            dataTest.add(new LetterForAnalysis(NikudLetter.NEWSPACE));
         }
         else
         {
            dataTest.add(letter);
         }
      }

      int deltaCol = findDeltaColumns(dataTest, sizeTest);
      result.setDeltaCol(deltaCol);

      if (dataDic.size() != dataTest.size())
      {
         throw new IllegalStateException("Längen unterschiedlich 1");
      }

      cutOfUnnecessaryDataToTheRight(dataDic, dataTest);

      if (dataDic.size() != dataTest.size())
      {
         throw new IllegalStateException("Längen unterschiedlich 2");
      }

      if (deltaCol > 0) // dataTest moved to the left of dataDic
      {
         // dddddddddddddddd
         // tttttttttt
         moveBeginningLettersToTheRightIfPossible(dataDic, dataTest, deltaCol);
         if (dataDic.size() != dataTest.size())
         {
            throw new IllegalStateException("Längen unterschiedlich 3a");
         }
      }
      else if (deltaCol < 0) // dataTest moved to the right of dataDic
      {
         // dddddddddddddddd
         // tttttttttt
         moveBeginningLettersToTheRightIfPossible(dataTest, dataDic, deltaCol);
         if (dataDic.size() != dataTest.size())
         {
            throw new IllegalStateException("Längen unterschiedlich 3b");
         }
      }
      // else // not moved
      // dddddddddddddddd
      // tttttttttt

      if (dataDic.size() != dataTest.size())
      {
         throw new IllegalStateException("Längen unterschiedlich 3");
      }

      if (result.isPartlyFalse())
      {
         dataTest = lookForWrongLettersAndMoveDLettersToTheLeftMaximizeSameness(
               dataDic, dataTest, NikudLetter.NEWSPACE, Math.min(sizeDic, sizeTest));

         if (dataDic.size() != dataTest.size())
         {
            throw new IllegalStateException("Längen unterschiedlich 4");
         }

         dataDic = lookForWrongLettersAndMoveDLettersToTheLeftMaximizeSameness(
               dataTest, dataDic, NikudLetter.NEWSPACE, Math.min(sizeDic, sizeTest));

         if (dataDic.size() != dataTest.size())
         {
            throw new IllegalStateException("Längen unterschiedlich 5");
         }

         lookForNewspaceAndMoveLettersToTheLeftIfPossible(dataDic, dataTest);
         
         if (dataDic.size() != dataTest.size())
         {
            throw new IllegalStateException("Längen unterschiedlich 5a");
         }
         
         lookForNewspaceAndMoveLettersToTheLeftIfPossible(dataTest, dataDic);
         
         if (dataDic.size() != dataTest.size())
         {
            throw new IllegalStateException("Längen unterschiedlich 5b");
         }
      }

      cutOutCommonNewspace(dataTest, dataDic);

      if (dataDic.size() != dataTest.size())
      {
         throw new IllegalStateException("Längen unterschiedlich 6");
      }
      
      result.setDictionary(dataDic);
      result.setAnswer(dataTest);

      return result;
   }

   private static void lookForNewspaceAndMoveLettersToTheLeftIfPossible(
         List<LetterForAnalysis> dataReference, List<LetterForAnalysis> dataToBeChanged)
   {
      for (int i = 0; i < dataToBeChanged.size(); i++)
      {
         if(dataToBeChanged.get(i).getContent().isNewspace() &&
               !dataReference.get(i).getContent().isNewspace())
         {
            int index = readIndexOfNextNotNewspaceLetterToTheRight(dataToBeChanged, i);
            if (index > 0 && evaluateSame(dataReference.get(i), dataToBeChanged.get(index)) == 1)
            {
               LetterForAnalysis letterToBeMoved = dataToBeChanged.remove(index);
               dataToBeChanged.add(i, letterToBeMoved);
            }
         }
      }
   }
   
   private static int readIndexOfNextNotNewspaceLetterToTheRight(
         List<LetterForAnalysis> dataTest, int i)
   {
      for (int index = i; index < dataTest.size(); index++)
      {
         if (!dataTest.get(index).getContent().isNewspace())
         {
            return index;
         }
      }
      return -1;
   }
   
   private static void cutOutCommonNewspace(List<LetterForAnalysis> dataT,
         List<LetterForAnalysis> dataD)
   {
      List<Integer> indexesToBeRemoved = new ArrayList<Integer>();
      for (int i = 0; i < Math.min(dataT.size(), dataD.size()); i++)
      {
         if (dataT.get(i).getContent().isNewspace()
               && dataD.get(i).getContent().isNewspace())
         {
            indexesToBeRemoved.add(i);
         }
      }

      int[] indexes = indexesToBeRemoved.stream().mapToInt(i->i).toArray();
      
      for(int i = indexes.length-1; i>=0; i--)
      {
         dataT.remove(indexes[i]);
      }
      
      for(int i = indexes.length-1; i>=0; i--)
      {
         dataD.remove(indexes[i]);
      }
   }

   private static List<LetterForAnalysis> lookForWrongLettersAndMoveDLettersToTheLeftMaximizeSameness(
         List<LetterForAnalysis> dataT, List<LetterForAnalysis> dataD,
         NikudLetter NEWSPACE, int maxSameness)
   {
      // for example
      // nnnnnnnnnndddddddddddddddd
      // nnnnnnntttttttttttttnnnntt
      // could be
      // nnnnnnnnnnndnnddddddddddddddd
      // nnnnnnnnnntttttttttttttnnnntt

      // just in case
      // nnnnnnnnnnndnnxdddddddddddddd
      // nnnnnnnnnnxtttyttttttttnnnntt
      // would become
      // nnnnnnnnnnndnnxnnnndddddddddddddd
      // nnnnnnnnnnnnnnxtttyttttttttnnnntt
      // would be rejected because sameness is low

      Map<Integer, List<LetterForAnalysis>> samenessMap = new HashMap<>();
      List<LetterForAnalysis> dataDcloneOriginal = cloneList(dataD);
      int startSameness = calculateSamenessPunish(dataT, dataDcloneOriginal);
      samenessMap.put(startSameness, dataDcloneOriginal);

      for (int i = 0; i < dataD.size(); i++)
      {
         if (evaluateCandidate(dataT.get(i), dataD.get(i)))
         {
            int match = findPossibleMatchToTheLeft(dataD.get(i), i, dataT);
            if (match > 0)
            {
               List<LetterForAnalysis> dataDclone = cloneList(dataD);
               for (int k = 0; k < i - match; k++)
               {
                  dataDclone.add(i + 1, new LetterForAnalysis(NEWSPACE));
               }
               int sameness = calculateSamenessPunish(dataT, dataDclone);
               samenessMap.put(sameness, dataDclone);
            }
         }
      }

      for (int i = maxSameness; i >= startSameness; i--)
      {
         if (samenessMap.get(i) != null)
         {
            if(dataT.size() != samenessMap.get(i).size())
            {
               makeBothTheSameSize(dataT, samenessMap.get(i),
                     NEWSPACE);
            }

            return samenessMap.get(i);
         }
      }

      return dataD;
   }

   private static void makeBothTheSameSize(
         List<LetterForAnalysis> other, List<LetterForAnalysis> list,
         NikudLetter NEWSPACE)
   {
      if(other.size() == list.size())
      {
         return;
      }
      
      if(other.size() > list.size())
      {
         int diff = other.size()-list.size();
         for (int i = 0; i < diff; i++)
         {
            list.add(0, new LetterForAnalysis(NEWSPACE));
         }
         return;
      }
      
      int diff = list.size() - other.size();
      for (int i = 0; i < diff; i++)
      {
         other.add(0, new LetterForAnalysis(NEWSPACE));
      }
   }

   private static int findPossibleMatchToTheLeft(
         LetterForAnalysis letterForAnalysis, int i,
         List<LetterForAnalysis> dataT)
   {
      for (int j = i; j > dataT.size() - findSizeOf(dataT); j--)
      {
         if (evaluateSame(dataT.get(j), letterForAnalysis) == 1)
         {
            return j;
         }
      }
      return -1;
   }

   private static int findSizeOf(List<LetterForAnalysis> dataT)
   {
      for (int i = 0; i < dataT.size(); i++)
      {
         if (!dataT.get(i).getContent().isNewspace())
         {
            return dataT.size() - i;
         }
      }
      return 0;
   }

   private static List<LetterForAnalysis> cloneList(
         List<LetterForAnalysis> datalist)
   {
      List<LetterForAnalysis> duplicate = new LinkedList<>();
      for (LetterForAnalysis letter : datalist)
      {
         duplicate.add(letter.clone());
      }
      return duplicate;
   }

   private static void moveBeginningLettersToTheRightIfPossible(
         List<LetterForAnalysis> dataD, List<LetterForAnalysis> dataT,
         int deltaCol)
   {
      // nnnnnnnnnndddddddddddddddD
      // nnnnnnnnnnnntttttttttT   T
      for (int d = dataD.size() - 1, t = dataT.size() - 1
            - Math.abs(deltaCol); d >= 0 && t >= 0; d--, t--)
      {
         if (evaluateSame(dataD.get(d), dataT.get(t)) == 1)
         {
            LetterForAnalysis letter = dataT.remove(t);
            dataT.add(d, letter);
         }
         else
         {
            break;
         }
      }
   }

   private static void cutOfUnnecessaryDataToTheRight(
         List<LetterForAnalysis> dataDic, List<LetterForAnalysis> dataTest)
   {
      for (int i = dataDic.size() - 1, j = dataTest.size() - 1; i >= 0
            && j >= 0; i--, j--)
      {
         if (dataDic.get(i).getContent().isNewspace()
               && dataTest.get(j).getContent().isNewspace())
         {
            dataDic.remove(i);
            dataTest.remove(j);
         }
         else
         {
            break;
         }
      }
   }

   private static int findDeltaColumns(List<LetterForAnalysis> dataTest,
         int sizeTest)
   {
      int deltaTest = 0;
      for (int i = dataTest.size() - 1; i >= 0; i--)
      {
         if (dataTest.get(i).getContent().isNewspace())
         {
            deltaTest++;
         }
         else
         {
            break;
         }
      }
      return deltaTest - sizeTest;
   }

   private static boolean evaluateCandidate(LetterForAnalysis reference,
         LetterForAnalysis toBeChanged)
   {
      if (reference.getContent().isNewspace()
            || toBeChanged.getContent().isNewspace())
      {
         return false;
      }
      if (reference.getContent() == toBeChanged.getContent())
      {
         return false;
      }
      return true;
   }

   private static int calculateSamenessPunish(List<LetterForAnalysis> dataT,
         List<LetterForAnalysis> dataD)
   {
      int sameness = 0;
      for (int t = dataT.size() - 1, d = dataD.size() - 1; t >= 0
            && d >= 0; t--, d--)
      {
         sameness += evaluateSamePunish(dataT.get(t), dataD.get(d));
      }
      return sameness;
   }

   private static int evaluateSamePunish(LetterForAnalysis letterD,
         LetterForAnalysis letterT)
   {
      if (letterD == null && letterT == null)
      {
         return 0;
      }
      if (letterD == null || letterT == null)
      {
         return 0;
      }
      if (letterD.getContent().isSpace() && letterT.getContent().isNewspace())
      {
         return 1;
      }
      if (letterD.getContent().isNewspace() && letterT.getContent().isSpace())
      {
         return 1;
      }
      if (letterD.getContent().isNewspace()
            && letterT.getContent().isNewspace())
      {
         return -1;
      }
      if (letterD.getContent() == letterT.getContent())
      {
         return 3;
      }
      return 0;
   }

   private static int evaluateSame(LetterForAnalysis letterD,
         LetterForAnalysis letterT)
   {
      if (letterD == null && letterT == null)
      {
         return 0;
      }
      if (letterD == null || letterT == null)
      {
         return 0;
      }
      if (letterD.getContent().isSpace() && letterT.getContent().isNewspace())
      {
         return 1;
      }
      if (letterD.getContent().isNewspace() && letterT.getContent().isSpace())
      {
         return 1;
      }
      if (letterD.getContent().isNewspace()
            && letterT.getContent().isNewspace())
      {
         return 0;
      }
      if (letterD.getContent() == letterT.getContent())
      {
         return 1;
      }
      return 0;
   }

   private static int evaluateDifference(LetterForAnalysis letterD,
         LetterForAnalysis letterT)
   {
      if (letterD == null && letterT == null)
      {
         return 0;
      }
      if (letterD == null || letterT == null)
      {
         return 1;
      }
      if (letterD.getContent().isSpace() && letterT.getContent().isNewspace())
      {
         return 0;
      }
      if (letterD.getContent().isNewspace() && letterT.getContent().isSpace())
      {
         return 0;
      }
      if (letterD.getContent() == letterT.getContent())
      {
         return 0;
      }
      return 1;
   }
}
