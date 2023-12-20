package vokabeltrainer.editing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import vokabeltrainer.types.Expression;

public class LetterHelper
{
   private static Map<String, Letter> codeMap;
   static
   {
      codeMap = new HashMap<>();
      for (Letter german : GermanLetter.values())
      {
         codeMap.put(german.getCode().toLowerCase(), german);
         codeMap.put(german.getCode().toUpperCase(), german);
      }
      for (Letter nikud : NikudLetter.values())
      {
         codeMap.put(nikud.getCode().toLowerCase(), nikud);
         codeMap.put(nikud.getCode().toUpperCase(), nikud);
      }
      for (Letter sign : SignLetter.values())
      {
         codeMap.put(sign.getCode().toLowerCase(), sign);
         codeMap.put(sign.getCode().toUpperCase(), sign);
      }
      for (Letter number : NumberLetter.values())
      {
         codeMap.put(number.getCode().toLowerCase(), number);
         codeMap.put(number.getCode().toUpperCase(), number);
      }
   }

   private LetterHelper()
   {
      // nothing
   }

   public static Letter getLetterFromCode(String code, LetterType type)
   {
      if (LetterType.GERMAN == type
            && GermanLetter.SPACE.getCode().equalsIgnoreCase(code))
      {
         return GermanLetter.SPACE;
      }
      return codeMap.get(code);
   }

   public static List<NikudLetter> findNikudLetters(String hebrewWord)
   {
      List<String> letterCodes = LetterHelper.findLetterCodes(hebrewWord);
      List<NikudLetter> hebrewLetters = new ArrayList<>();
      for (String code : letterCodes)
      {
         Letter hebrewLetter = LetterHelper
               .getLetterFromCode(code, LetterType.HEBREW);
         if (hebrewLetter != null && hebrewLetter instanceof NikudLetter)
         {
            hebrewLetters.add((NikudLetter) hebrewLetter);
         }
      }
      return hebrewLetters;
   }

   public static List<String> findLetterCodes(String word)
   {
      List<String> letterCodes = new LinkedList<>();
      if (word == null)
      {
         return letterCodes;
      }
      for (int c = 0; c < word.length(); c++)
      {
         try
         {
            String code = String.format(" %04x", (int) word.charAt(c));

            letterCodes.add(code);
         }
         catch (Exception e)
         {
            // nothing
         }
      }
      return letterCodes;
   }

   public static String makeWordFromCodes(List<String> codes)
   {
      StringBuilder builder = new StringBuilder();
      for (String code : codes)
      {
         if (codeMap.get(code) != null)
         {
            builder.append(codeMap.get(code).getUnicode());
         }
      }
      return builder.toString();
   }

   public static LinkedList<LetterForAnalysis> findNikudLetterForAnalysisList(
         String word)
   {
      LinkedList<LetterForAnalysis> analysisList = new LinkedList<>();

      List<String> codeList = findLetterCodes(word);

      LetterForAnalysis currentLetterForAnalysis = new LetterForAnalysis(
            NikudLetter.SPACE);

      for (int i = 0; i < codeList.size(); i++)
      {
         Letter letter = codeMap.get(codeList.get(i));
         if (letter != null && LetterType.HEBREW == letter.isType())
         {
            NikudLetter nikudLetter = (NikudLetter) letter;
            switch (nikudLetter.getDistinction())
            {
            case LETTER:
               currentLetterForAnalysis = new LetterForAnalysis(nikudLetter);
               analysisList.add(currentLetterForAnalysis);
               break;
            case LOWER_PUNKTATION:
               currentLetterForAnalysis.addToLowerPunktation(nikudLetter);
               break;
            case MIDDLE_PUNKTATION:
               currentLetterForAnalysis.addDagesh(nikudLetter);
               break;
            case UPPER_PUNKTATION:
               currentLetterForAnalysis.addToUpperPunktation(nikudLetter);
               break;
            }
         }
         else if (letter == null)
         {
            System.out.println("LetterHelper: " + codeList.get(i));
         }
      }
      
      return analysisList;
   }

   public static boolean areLettersEqual(LetterForAnalysis one,
         LetterForAnalysis two)
   {
      if (one.getContent() != two.getContent())
      {
         return false;
      }

      return true;
   }

   public static String turnExchangeSsinIntoNikudSsin(String hebrew)
   {
      List<String> nikudCodeList = new ArrayList<>();
      List<String> hebrewCodeList = LetterHelper.findLetterCodes(hebrew);
      for (String hebrewCode : hebrewCodeList)
      {
         if (ExchangeLetter.SSIN.getCode().equalsIgnoreCase(hebrewCode))
         {
            nikudCodeList.add(NikudLetter.SCHIN.getCode());
            nikudCodeList.add(NikudLetter.SIN_DOT.getCode());
         }
         else
         {
            nikudCodeList.add(hebrewCode);
         }
      }
      return LetterHelper.makeWordFromCodes(nikudCodeList);
   }

   public static List<List<LetterForAnalysis>> findListofNikudLetterForAnalysisListsHebrewSearchwords(
         Expression expression)
   {
      return expression
            .getSearchwordsHebrew()
            .stream()
            .map(word -> findNikudLetterForAnalysisList(word))
            .collect(Collectors.toList());
   }
   
   public static String findHebrewWithoutPunctation(String hebrew)
   {
      LinkedList<LetterForAnalysis> list = LetterHelper.findNikudLetterForAnalysisList(hebrew);
      
      StringBuilder result = new StringBuilder();
      
      for(LetterForAnalysis letter : list)
      {
         result.append(letter.getContent().getUnicode());
      }
      
      return result.toString();
   }

}
