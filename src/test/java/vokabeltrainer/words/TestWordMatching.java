package vokabeltrainer.words;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import vokabeltrainer.editing.LetterForAnalysis;
import vokabeltrainer.panels.trainer.Result;
import vokabeltrainer.panels.trainer.NikudResultFactory;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.SelectionHebrewType;

public class TestWordMatching
{
   String wordDic = "באוניברסיטה";
   
   String[] wordDicArray = { "HAEI", "TET", "JOD", "SSAMECH", "RESCH", "BET",
         "JOD", "NUN", "WAW", "ALEF", "BET" };
   List<String> wordDicList = new LinkedList<String>(
         Arrays.asList(wordDicArray));

   String wordTest1 = "באוניברסיטה";
   String wordTest2 = "גדפ";
   String wordTest3 = "בוניברסיטה";
   String wordTest4 = "בוניבסיטה"; // 2 letters miSCHINg
   String wordTest5 = "בוניבסטה"; // 3 letters miSCHINg
   String wordTest6 = "בונבסטה"; // 4 letters miSCHINg
   String wordTest7 = "בונבסטהעפש"; // 4 letters miSCHINg plus 3 letters stupid
   String wordTest8 = "בונבסטהעפשצק"; // 4 letters miSCHINg plus 5 letters
                                       // stupid
   String wordTest9 = "אובאוניברסיטה"; // 2 letters added at the beginning =>
                                        // right shift case
   String wordTest10 = "אובאוניבריטה"; // 2 letters added at the beginning =>
                                        // right shift case, and 1 letter
                                        // miSCHINg inside
   String wordTest11 = "מסרדם"; // example from real life, wrong spelling
   String wordDic11 = "משרדים"; // correct spelling of 11
   String wordTest12 = "פקדה"; // example from real life, wrong spelling
   String wordDic12 = "פקידה"; // correct spelling of 12
   String wordTest13 = "חברים"; // correct spelling test
   String wordTest14 = "בקר טוב"; // wrong spelling
   String wordDic14 = "בוקר טוב"; // correct spelling of 14
   String wordTest15 = "נימות"; // wrong spelling
   String wordDic15 = "נעימות"; // correct spelling
   String wordTest15b = "נ ימות"; // corrected spelling
   String wordDic16 = "נעימות"; // correct spelling
   String wordTest16b = "נא מ ת"; // corrected spelling
   String wordTest16 = "נאמת"; // wrong spelling

   String wordTest17 = "מא נישמה"; // wrong spelling
   String wordDic17 = "מה נשמע"; // correct spelling
   String wordDic17b = "מה נ שמע"; // dic corrected spelling
   String wordTest17b = "מא נישמה"; // test corrected spelling

   String wordTest18 = "בוקרטוב"; // wrong spelling
   String wordDic18 = "בוקר טוב"; // correct spelling

   String wordTest19 = "במשראד"; // wrong spelling
   String wordDic19 = "במשרד"; // correct spelling

   String wordDic20 = "בוקר טוב"; // correct spelling
   String wordTest20 = "????????"; // empty
   String wordTest20a = ""; // empty

   String wordDic21 = "?"; // empty
   String wordDic21a = ""; // empty
   String wordDic21b = "?????????"; // empty
   String wordTest21 = "בוקר טוב"; // not empty

   String wordDic22 = "פסיכולוג";
   String wordTest22 = "פסיכולוגית"; // two letters too much at the end

   String wordDic23 = "פיסיקאי";
   String wordTest23 = "פיסיקית"; // two letters wrong at the end

   String wordDic24 = "נעימות";
   String wordTest24 = "נוימת"; // mixed up
   
   String wordDic25 = "נעי מות"; 
   String wordTest25 = "נעימות"; // space missing
   
   String wordDic26 = "אִישׁ ... רֵעֵהוּ";
   String wordTest26 = "איש ... רעהו";
   
   String[] wordTest4Array = { "HAEI", "TET", "JOD", "SSAMECH", "NEWSPACE",
         "BET", "JOD", "NUN", "WAW", "NEWSPACE", "BET" };
   List<String> wordTest4List = new LinkedList<String>(
         Arrays.asList(wordTest4Array));

   String[] wordTest5Array = { "HAEI", "TET", "NEWSPACE", "SSAMECH", "NEWSPACE",
         "BET", "JOD", "NUN", "WAW", "NEWSPACE", "BET" };
   List<String> wordTest5List = new LinkedList<String>(
         Arrays.asList(wordTest5Array));

   String[] wordTest6Array = { "HAEI", "TET", "NEWSPACE", "SSAMECH", "NEWSPACE",
         "BET", "NEWSPACE", "NUN", "WAW", "NEWSPACE", "BET" };
   List<String> wordTest6List = new LinkedList<String>(
         Arrays.asList(wordTest6Array));

   String[] wordTest7Array = { "SCHIN", "PAEI", "AIN", "HAEI", "TET",
         "NEWSPACE", "SSAMECH", "NEWSPACE", "BET", "NEWSPACE", "NUN", "WAW",
         "NEWSPACE", "BET" };
   List<String> wordTest7List = new LinkedList<String>(
         Arrays.asList(wordTest7Array));

   String[] wordTest8Array = { "KUF", "ZADI", "SCHIN", "PAEI", "AIN", "HAEI",
         "TET", "NEWSPACE","SSAMECH", "NEWSPACE","BET","NEWSPACE", "NUN", "WAW", "NEWSPACE", "BET" };
   List<String> wordTest8List = new LinkedList<String>(
         Arrays.asList(wordTest8Array));
   
   String[] wordDic8Array = {"NEWSPACE", "NEWSPACE", "NEWSPACE", "NEWSPACE", "NEWSPACE", "HAEI", "TET", "JOD", "SSAMECH", "RESCH", "BET", "JOD", "NUN", "WAW", "ALEF", "BET"};
   List<String> wordDic8List = new LinkedList<String>(Arrays.asList(wordDic8Array));
  
   String[] wordDic9Array = { "HAEI", "TET", "JOD", "SSAMECH", "RESCH", "BET",
         "JOD", "NUN", "WAW", "ALEF", "BET", "NEWSPACE", "NEWSPACE" };
   List<String> wordDic9List = new LinkedList<String>(
         Arrays.asList(wordDic9Array));

   String[] wordTest9Array = { "HAEI", "TET", "JOD", "SSAMECH", "RESCH", "BET",
         "JOD", "NUN", "WAW", "ALEF", "BET", "WAW", "ALEF" };
   List<String> wordTest9List = new LinkedList<String>(
         Arrays.asList(wordTest9Array));

   String[] wordTest10Array = { "HAEI", "TET", "JOD", "NEWSPACE", "RESCH",
         "BET", "JOD", "NUN", "WAW", "ALEF", "BET", "WAW", "ALEF" };
   List<String> wordTest10List = new LinkedList<String>(
         Arrays.asList(wordTest10Array));

   String[] wordTest11Array = { "MEMSSOFIT", "NEWSPACE", "DALET", "RESCH",
         "SSAMECH", "MEM" };
   List<String> wordTest11List = new LinkedList<String>(
         Arrays.asList(wordTest11Array));

   String[] wordTest12Array = { "HAEI", "DALET", "NEWSPACE", "KUF", "PAEI" };
   List<String> wordTest12List = new LinkedList<String>(
         Arrays.asList(wordTest12Array));

   String[] wordTest13Array = { "MEMSSOFIT", "JOD", "RESCH", "BET", "CHET" };
   List<String> wordTest13List = new LinkedList<String>(
         Arrays.asList(wordTest13Array));

   String[] wordTest14Array = { "BET", "WAW", "TET", "SPACE", "RESCH", "KUF",
         "NEWSPACE", "BET" };
   List<String> wordTest14List = new LinkedList<String>(
         Arrays.asList(wordTest14Array));

   String[] wordTest15Array = { "TAW", "WAW", "MEM", "JOD", "NEWSPACE", "NUN" };
   List<String> wordTest15List = new LinkedList<String>(
         Arrays.asList(wordTest15Array));

   String[] wordTest15bArray = { "TAW", "WAW", "MEM", "JOD", "NEWSPACE",
         "NUN" };
   List<String> wordTest15bList = new LinkedList<String>(
         Arrays.asList(wordTest15bArray));

   String[] wordTest16bArray = { "TAW", "NEWSPACE", "MEM", "NEWSPACE", "ALEF",
         "NUN" };
   List<String> wordTest16bList = new LinkedList<String>(
         Arrays.asList(wordTest16bArray));

   String[] wordTest17bArray = { "HAEI", "MEM", "SCHIN", "JOD", "NUN", "SPACE",
         "ALEF", "MEM" };
   List<String> wordTest17bList = new LinkedList<String>(
         Arrays.asList(wordTest17bArray));

   String[] wordDic17bArray = { "AIN", "MEM", "SCHIN", "NEWSPACE", "NUN",
         "SPACE", "HAEI", "MEM" };
   List<String> wordDic17bList = new LinkedList<String>(
         Arrays.asList(wordDic17bArray));

   String[] wordTest18bArray = { "BET", "WAW", "TET", "NEWSPACE", "RESCH",
         "KUF", "WAW", "BET" };
   List<String> wordTest18bList = new LinkedList<>(
         Arrays.asList(wordTest18bArray));

   String[] wordDic19bArray = { "DALET", "NEWSPACE", "RESCH", "SCHIN", "MEM",
         "BET" };
   List<String> wordDic19bList = new LinkedList<>(
         Arrays.asList(wordDic19bArray));

   String[] wordTest22Array = { "TAW", "JOD", "GIMEL", "WAW", "LAMED", "WAW",
         "KAF", "JOD", "SSAMECH", "PAEI" };
   List<String> wordTest22List = new LinkedList<>(
         Arrays.asList(wordTest22Array));

   String[] wordDic22Array = { "NEWSPACE", "NEWSPACE", "GIMEL", "WAW", "LAMED",
         "WAW", "KAF", "JOD", "SSAMECH", "PAEI" };
   List<String> wordDic22List = new LinkedList<>(Arrays.asList(wordDic22Array));

   String[] wordTest23Array = { "TAW", "JOD", "KUF", "JOD", "SSAMECH", "JOD",
         "PAEI" };
   List<String> wordTest23List = new LinkedList<>(
         Arrays.asList(wordTest23Array));
   String[] wordDic23Array = { "JOD", "ALEF", "KUF", "JOD", "SSAMECH", "JOD",
         "PAEI" };
   List<String> wordDic23List = new LinkedList<>(Arrays.asList(wordDic23Array));

   String[] wordTest24Array = { "TAW", "NEWSPACE", "MEM", "JOD", "WAW", "NUN" };
   List<String> wordTest24List = new LinkedList<>(
         Arrays.asList(wordTest24Array));

   String[] wordDic24Array = { "TAW", "WAW", "MEM", "JOD", "AIN", "NUN" };
   List<String> wordDic24List = new LinkedList<>(Arrays.asList(wordDic24Array));
   
   String[] wordDic25Array = { "TAW", "WAW", "MEM", "JOD", "AIN", "NUN" };
   List<String> wordDic25List = new LinkedList<>(Arrays.asList(wordDic25Array));
   
   String[] wordTest25Array = { "TAW", "WAW", "MEM", "SPACE", "JOD", "AIN", "NUN" };
   List<String> wordTest25List = new LinkedList<>(Arrays.asList(wordTest25Array));
   
   String[] wordTest26Array= {"WAW", "HAEI", "AIN", "RESCH", "SPACE", "SPACE", "SCHIN", "JOD", "ALEF"};
   List<String> wordTest26List = new LinkedList<>(Arrays.asList(wordTest26Array));

   @Test
   public void testMatchHebrew_Okay()
   {
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest1, new Font(null), SelectionHebrewType.SIMPLE);
      assertTrue(result.isOkay());
   }
   
   @Test
   public void testMatchHebrew_NotOkay()
   {
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic25);
      
      System.out.println("TestWordMatching " + expressionDic.getHebrew().getHebrew());
      System.out.println("TestWordMatching " + wordTest25);
      
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest25, new Font(null), SelectionHebrewType.SIMPLE);
      assertFalse(result.isOkay());
   }

   @Test
   public void testMatchHebrew_Okay2()
   {
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest4, new Font(null), SelectionHebrewType.SIMPLE);
      assertFalse(result.isOkay());
   }

   @Test
   public void testMatchHebrew_Okay3()
   {
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest2, new Font(null), SelectionHebrewType.SIMPLE);
      assertFalse(result.isOkay());
   }
   
   @Test
   public void testMatchHebrew_Okay4()
   {
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic26);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest26, new Font(null), SelectionHebrewType.SIMPLE);
      assertTrue(result.isOkay());
   }
   
   @Test
   public void testMatchHebrew_Correct_Alignment1()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_Correct_Alignment1");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic26);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest26, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> dicTesting = turnIntoListOfStrings(result.getDictionary());
      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.print("test26   ");
      System.out.println(wordTest26List);
      System.out.print("test neu ");
      System.out.println(wordTesting);
      System.out.print("dic neu  ");
      System.out.println(dicTesting);
      assertFalse(result.isAnswerEmpty());
      assertFalse(result.isDictionaryEmpty());
      assertTrue(wordTest26List.equals(wordTesting));
   }

   @Test
   public void testMatchHebrew_Empty()
   {
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic20);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest20, new Font(null), SelectionHebrewType.SIMPLE);
      assertTrue(result.isAnswerEmpty());
   }

   @Test
   public void testMatchHebrew_Empty2()
   {
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic20);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest20a, new Font(null), SelectionHebrewType.SIMPLE);
      assertTrue(result.isAnswerEmpty());
   }

   @Test
   public void testMatchHebrew_Empty_Dic1()
   {
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic21);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest21, new Font(null), SelectionHebrewType.SIMPLE);
      assertTrue(result.isDictionaryEmpty());
   }

   @Test
   public void testMatchHebrew_Empty_Dic2()
   {
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic21a);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest21, new Font(null), SelectionHebrewType.SIMPLE);
      assertTrue(result.isDictionaryEmpty());
   }

   @Test
   public void testMatchHebrew_Empty_Dic3()
   {
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic21b);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest21, new Font(null), SelectionHebrewType.SIMPLE);
      assertTrue(result.isDictionaryEmpty());
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_PartlyFalse_Alignment");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest4, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.println(wordDicList);
      System.out.println(wordTest4List);
      System.out.println(wordTesting);
      assertTrue(wordTest4List.equals(wordTesting));
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment2()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_PartlyFalse_Alignment2");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest5, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.println(wordDicList);
      System.out.println(wordTest5List);
      System.out.println(wordTesting);
      assertTrue(wordTest5List.equals(wordTesting));
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment3()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_PartlyFalse_Alignment3");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest6, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.println(wordDicList);
      System.out.println(wordTest6List);
      System.out.println(wordTesting);
      assertTrue(wordTest6List.equals(wordTesting));
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment4()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_PartlyFalse_Alignment4");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest7, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> dicTesting = turnIntoListOfStrings(result.getDictionary());
      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.print("Original ");
      System.out.println(wordDicList);
      System.out.print("alt      ");
      System.out.println(wordTest7List);
      System.out.print("test neu ");
      System.out.print("");
      System.out.println(wordTesting);
      System.out.print("dic neu  ");
      System.out.println(dicTesting);
      assertTrue(wordTest7List.equals(wordTesting));
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment5()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_PartlyFalse_Alignment5");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest9, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> dicTesting = turnIntoListOfStrings(result.getDictionary());
      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.print("Original ");
      System.out.println(wordDicList);
      System.out.print("alt      ");
      System.out.println(wordTest9List);
      System.out.print("test neu ");
      System.out.print("");
      System.out.println(wordTesting);
      System.out.print("dic neu  ");
      System.out.println(dicTesting);
      assertTrue(wordTest9List.equals(wordTesting));
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment6()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_PartlyFalse_Alignment6");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest10, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> dicTesting = turnIntoListOfStrings(result.getDictionary());
      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.print("Original ");
      System.out.println(wordDicList);
      System.out.print("alt      ");
      System.out.println(wordTest10List);
      System.out.print("test neu ");
      System.out.print("");
      System.out.println(wordTesting);
      System.out.print("dic neu  ");
      System.out.println(dicTesting);
      assertTrue(wordTest10List.equals(wordTesting));
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment7()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_PartlyFalse_Alignment7");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic11);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest11, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> dicTesting = turnIntoListOfStrings(result.getDictionary());
      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.print("alt      ");
      System.out.println(wordTest11List);
      System.out.print("test neu ");
      System.out.print("");
      System.out.println(wordTesting);
      System.out.print("dic neu  ");
      System.out.println(dicTesting);
      assertTrue(wordTest11List.equals(wordTesting));
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment8()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_PartlyFalse_Alignment8");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordTest13);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest13, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> dicTesting = turnIntoListOfStrings(result.getDictionary());
      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.print("alt      ");
      System.out.println(wordTest13List);
      System.out.print("test neu ");
      System.out.println(wordTesting);
      System.out.print("dic neu  ");
      System.out.println(dicTesting);
      assertTrue(wordTest13List.equals(wordTesting));
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment9()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_PartlyFalse_Alignment9");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic14);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest14, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> dicTesting = turnIntoListOfStrings(result.getDictionary());
      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.print("alt      ");
      System.out.println(wordTest14List);
      System.out.print("test neu ");
      System.out.println(wordTesting);
      System.out.print("dic neu  ");
      System.out.println(dicTesting);
      assertFalse(result.isAnswerEmpty());
      assertFalse(result.isDictionaryEmpty());
      assertTrue(wordTest14List.equals(wordTesting));
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment10()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_PartlyFalse_Alignment10");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic15);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest15, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> dicTesting = turnIntoListOfStrings(result.getDictionary());
      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.print("alt      ");
      System.out.println(wordTest15List);
      System.out.print("test neu ");
      System.out.println(wordTesting);
      System.out.print("dic neu  ");
      System.out.println(dicTesting);
      assertFalse(result.isAnswerEmpty());
      assertFalse(result.isDictionaryEmpty());
      assertTrue(wordTest15List.equals(wordTesting));
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment11()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_PartlyFalse_Alignment11");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic19);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest19, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> dicTesting = turnIntoListOfStrings(result.getDictionary());
      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.print("alt      ");
      System.out.println(wordDic19bList);
      System.out.print("test neu ");
      System.out.println(wordTesting);
      System.out.print("dic neu  ");
      System.out.println(dicTesting);
      assertFalse(result.isAnswerEmpty());
      assertFalse(result.isDictionaryEmpty());
      assertTrue(wordDic19bList.equals(dicTesting));
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment12()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_PartlyFalse_Alignment12");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic22);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest22, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> dicTesting = turnIntoListOfStrings(result.getDictionary());
      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.print("alt      ");
      System.out.println(wordTest22List);
      System.out.print("test neu ");
      System.out.println(wordTesting);
      System.out.print("dic neu  ");
      System.out.println(dicTesting);
      assertFalse(result.isAnswerEmpty());
      assertFalse(result.isDictionaryEmpty());
      assertTrue(wordTest22List.equals(wordTesting));
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment13()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_PartlyFalse_Alignment13");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic17);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest17, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> dicTesting = turnIntoListOfStrings(result.getDictionary());
      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.print("test alt ");
      System.out.println(wordTest17bList);
      System.out.print("test neu ");
      System.out.println(wordTesting);
      System.out.print("dic neu  ");
      System.out.println(dicTesting);
      System.out.print("dic alt  ");
      System.out.println(wordDic17bList);
      assertFalse(result.isAnswerEmpty());
      assertFalse(result.isDictionaryEmpty());
      assertTrue(wordTest17bList.equals(wordTesting));
      assertTrue(wordDic17bList.equals(dicTesting));
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment14()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_PartlyFalse_Alignment14");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic23);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest23, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> dicTesting = turnIntoListOfStrings(result.getDictionary());
      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.print("test alt ");
      System.out.println(wordTest23List);
      System.out.print("test neu ");
      System.out.println(wordTesting);
      System.out.print("dic neu  ");
      System.out.println(dicTesting);
      System.out.print("dic alt  ");
      System.out.println(wordDic23List);
      assertFalse(result.isAnswerEmpty());
      assertFalse(result.isDictionaryEmpty());
      assertTrue(wordTest23List.equals(wordTesting));
      assertTrue(wordDic23List.equals(dicTesting));
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment15()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_PartlyFalse_Alignment15");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest8, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> dicTesting = turnIntoListOfStrings(result.getDictionary());
      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.print("test richtig ");
      System.out.println(wordTest8List);
      System.out.print("dic richtig  ");
      System.out.println(wordDic8List);
      System.out.print("test neu ");
      System.out.println(wordTesting);
      System.out.print("dic neu  ");
      System.out.println(dicTesting);
      
      assertFalse(result.isAnswerEmpty());
      assertFalse(result.isDictionaryEmpty());
      assertTrue(wordTest8List.equals(wordTesting));
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment16()
   {
      System.out.println("");
      System.out.println("testMatchHebrew_PartlyFalse_Alignment16");
      Expression expressionDic = new Expression(true);
      expressionDic.getHebrew().setHebrew(wordDic24);
      Result result = NikudResultFactory.getResultDtoNikudSentence(expressionDic,
            wordTest24, new Font(null), SelectionHebrewType.SIMPLE);

      List<String> dicTesting = turnIntoListOfStrings(result.getDictionary());
      List<String> wordTesting = turnIntoListOfStrings(result.getAnswer());
      System.out.print("test richtig  ");
      System.out.println(wordTest24List);
      System.out.print("test neu ");
      System.out.println(wordTesting);
      System.out.print("dic neu  ");
      System.out.println(dicTesting);
      System.out.print("dic richtig  ");
      System.out.println(wordDic24List);
      assertFalse(result.isAnswerEmpty());
      assertFalse(result.isDictionaryEmpty());
      assertTrue(wordTest24List.equals(wordTesting));
   }

   private List<String> turnIntoListOfStrings(List<LetterForAnalysis> answer)
   {
      List<String> list = new ArrayList<>();
      for (LetterForAnalysis letter : answer)
      {
         list.add(letter.getContent().name());
      }
      return list;
   }
}
