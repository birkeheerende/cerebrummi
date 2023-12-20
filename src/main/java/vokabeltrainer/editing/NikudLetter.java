package vokabeltrainer.editing;

import java.util.StringJoiner;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;

public enum NikudLetter
      implements
      Letter
{
   AIN(
         "\u05E2",
         " 05E2",
         "ain",
         Translation.AMEISE,
         NikudLetterDistinction.LETTER,
         18,
         true),
   ALEF(
         "\u05D0",
         " 05D0",
         "alef",
         Translation.APFEL,
         NikudLetterDistinction.LETTER,
         18,
         true),
   CHET(
         "\u05D7",
         " 05D7",
         "chet",
         Translation.CHINA,
         NikudLetterDistinction.LETTER,
         18,
         true),
   DALET(
         "\u05D3",
         " 05D3",
         "dalet",
         Translation.DOMINO,
         NikudLetterDistinction.LETTER,
         18,
         true),
   GIMEL(
         "\u05D2",
         " 05D2",
         "gimel",
         Translation.GANS,
         NikudLetterDistinction.LETTER,
         11,
         true),
   GERESCH(
         "\u05F3",
         " 05F3",
         "geresch",
         Translation._NONE_,
         NikudLetterDistinction.LETTER,
         9,
         false),
   GERSCHAYIM(
         "\u05F4",
         " 05F4",
         "gerschayim",
         Translation._NONE_,
         NikudLetterDistinction.LETTER,
         14,
         false),
   HAEI(
         "\u05D4",
         " 05D4",
         "häi",
         Translation.HAUS,
         NikudLetterDistinction.LETTER,
         18,
         true),
   JOD(
         "\u05D9",
         " 05D9",
         "jod",
         Translation.IGEL,
         NikudLetterDistinction.LETTER,
         10,
         true),
   KUF(
         "\u05E7",
         " 05E7",
         "kuf",
         Translation.KUH,
         NikudLetterDistinction.LETTER,
         18,
         true),
   LAMED(
         "\u05DC",
         " 05DC",
         "lamed",
         Translation.LATERNE,
         NikudLetterDistinction.LETTER,
         17,
         true),
   RESCH(
         "\u05E8",
         " 05E8",
         "resch",
         Translation.RAD,
         NikudLetterDistinction.LETTER,
         17,
         true),
   SSAIN(
         "\u05D6",
         " 05D6",
         "ssain",
         Translation.SAND,
         NikudLetterDistinction.LETTER,
         9,
         true),
   SCHIN(
         "\u05E9",
         " 05E9",
         "schin",
         Translation.SCHAUKEL,
         NikudLetterDistinction.LETTER,
         19,
         true),
   SSAMECH(
         "\u05E1",
         " 05E1",
         "ssamech",
         Translation.SONNE,
         NikudLetterDistinction.LETTER,
         17,
         true),
   SPACE(
         "\u0020",
         " 0020",
         "space",
         Translation._NONE_,
         NikudLetterDistinction.LETTER,
         10,
         false),
   NEWSPACE(
         "\u00A0",
         " 00A0",
         "newspace",
         Translation._NONE_,
         NikudLetterDistinction.LETTER,
         10,
         false),
   TAW(
         "\u05EA",
         " 05EA",
         "taw",
         Translation.TANNEN,
         NikudLetterDistinction.LETTER,
         17,
         true),
   TET(
         "\u05D8",
         " 05D8",
         "tet",
         Translation.TANZ,
         NikudLetterDistinction.LETTER,
         17,
         true),
   BET(
         "\u05D1",
         " 05D1",
         "bet",
         Translation.BETT,
         NikudLetterDistinction.LETTER,
         18,
         true),
   WAW(
         "\u05D5",
         " 05D5",
         "waw",
         Translation.WELLE,
         NikudLetterDistinction.LETTER,
         9,
         true),
   ZADI(
         "\u05E6",
         " 05E6",
         "zadi",
         Translation.ZITRONE,
         NikudLetterDistinction.LETTER,
         18,
         true),
   ZADISSOFIT(
         "\u05E5",
         " 05E5",
         "zadissofit",
         Translation.ZITRONE,
         NikudLetterDistinction.LETTER,
         18,
         true),
   KAF(
         "\u05DB",
         " 05DB",
         "kaf",
         Translation.KUCHEN,
         NikudLetterDistinction.LETTER,
         17,
         true),
   CHAFSSOFIT(
         "\u05DA",
         " 05DA",
         "chafssofit",
         Translation.CHEMIE,
         NikudLetterDistinction.LETTER,
         18,
         true),
   PAEI(
         "\u05E4",
         " 05E4",
         "päi",
         Translation.POST,
         NikudLetterDistinction.LETTER,
         17,
         true),
   FAEISSOFIT(
         "\u05E3",
         " 05E3",
         "fäissofit",
         Translation.FEDER,
         NikudLetterDistinction.LETTER,
         18,
         true),
   MEM(
         "\u05DE",
         " 05DE",
         "mem",
         Translation.MAUS,
         NikudLetterDistinction.LETTER,
         17,
         true),
   MEMSSOFIT(
         "\u05DD",
         " 05DD",
         "memssofit",
         Translation.MAUS,
         NikudLetterDistinction.LETTER,
         16,
         true),
   NUN(
         "\u05E0",
         " 05E0",
         "nun",
         Translation.NONNE,
         NikudLetterDistinction.LETTER,
         10,
         true),
   NUNSSOFIT(
         "\u05DF",
         " 05DF",
         "nunssofit",
         Translation.NONNE,
         NikudLetterDistinction.LETTER,
         9,
         true),
   SHEVA(
         "\u05B0",
         " 05B0",
         "schwa = e",
         Translation._NONE_,
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   HATAF_SEGOL(
         "\u05B1",
         " 05B1",
         "chataf szegol = e",
         Translation._NONE_,
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   HATAF_PATAH(
         "\u05B2",
         " 05B2",
         "chataf patach = a",
         Translation._NONE_,
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   HATAF_QAMATS(
         "\u05B3",
         " 05B3",
         "chataf kamatz = o",
         Translation._NONE_,
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   HIRIQ(
         "\u05B4",
         " 05B4",
         "chirik = i",
         Translation._NONE_,
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   TSERE(
         "\u05B5",
         " 05B5",
         "zeré = e",
         Translation._NONE_,
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   SEGOL(
         "\u05B6",
         " 05B6",
         "szegol = e",
         Translation._NONE_,
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   PATAH(
         "\u05B7",
         " 05B7",
         "patach = a",
         Translation._NONE_,
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   QAMATS(
         "\u05B8",
         " 05B8",
         "kamatz = a",
         Translation._NONE_,
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   HOLAM(
         "\u05B9",
         " 05B9",
         "cholam = o",
         Translation._NONE_,
         NikudLetterDistinction.UPPER_PUNKTATION,
         0,
         false),
   HOLAM_HASER(
         "\u05BA",
         " 05BA",
         "cholam chaser",
         Translation._NONE_,
         NikudLetterDistinction.UPPER_PUNKTATION,
         0,
         false),
   QUBUTS(
         "\u05BB",
         " 05BB",
         "kubutz = u",
         Translation._NONE_,
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   DAGESH(
         "\u05BC",
         " 05BC",
         "dagesch/schuruk",
         Translation._NONE_,
         NikudLetterDistinction.MIDDLE_PUNKTATION,
         0,
         false),
   METEG(
         "\u05BD",
         " 05BD",
         "meteg",
         Translation._NONE_,
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   MAQAF(
         "\u05BE",
         " 05BE",
         "makaf",
         Translation._NONE_,
         NikudLetterDistinction.LETTER,
         14,
         false),
   RAFE(
         "\u05BF",
         " 05BF",
         "rafi",
         Translation._NONE_,
         NikudLetterDistinction.UPPER_PUNKTATION,
         0,
         false),
   PASEQ(
         "\u05C0",
         " 05C0",
         "pasek",
         Translation._NONE_,
         NikudLetterDistinction.LETTER,
         4,
         false),
   SHIN_DOT(
         "\u05C1",
         " 05C1",
         "schin Punkt",
         Translation._NONE_,
         NikudLetterDistinction.UPPER_PUNKTATION,
         0,
         false),
   SIN_DOT(
         "\u05C2",
         " 05C2",
         "sin Punkt",
         Translation._NONE_,
         NikudLetterDistinction.UPPER_PUNKTATION,
         0,
         false),
   SOF_PASUQ(
         "\u05C3",
         " 05C3",
         "sof pasuk",
         Translation._NONE_,
         NikudLetterDistinction.LETTER,
         8,
         false),
   UPPER_DOT(
         "\u05C4",
         " 05C4",
         "oberer Punkt",
         Translation._NONE_,
         NikudLetterDistinction.UPPER_PUNKTATION,
         0,
         false),
   LOWER_DOT(
         "\u05C5",
         " 05C5",
         "unterer Punkt",
         Translation._NONE_,
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   HAFUKAH(
         "\u05C6",
         " 05C6",
         "chafukach",
         Translation._NONE_,
         NikudLetterDistinction.LETTER,
         10,
         false),
   QAMATS_QATAN(
         "\u05C7",
         " 05C7",
         "kamatz katan",
         Translation._NONE_,
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   JIDDISH_DOUBLE_WAW(
         "\u05F0",
         " 05F0",
         "waw waw",
         Translation._NONE_,
         NikudLetterDistinction.LETTER,
         19,
         false),
   JIDDISH_WAW_JOD(
         "\u05F1",
         " 05F1",
         "waw jod",
         Translation._NONE_,
         NikudLetterDistinction.LETTER,
         20,
         false),
   JIDDISH_DOUBLE_JOD(
         "\u05F2",
         " 05F2",
         "jod jod",
         Translation._NONE_,
         NikudLetterDistinction.LETTER,
         21,
         false);

   private String unicode;
   private String code;
   private String transcript;
   private Translation germanPictureName;
   private NikudLetterDistinction distinction;
   private int pixelWidth;
   private boolean handwritten;

   NikudLetter(String unicode, String code, String transcript,
         Translation germanPictureName, NikudLetterDistinction distinction,
         int pixelWidth, boolean handwritten)
   {
      this.unicode = unicode;
      this.code = code;
      this.transcript = transcript;
      this.germanPictureName = germanPictureName;
      this.distinction = distinction;
      this.pixelWidth = pixelWidth;
      this.handwritten = handwritten;
   }

   public String getUnicode()
   {
      return unicode;
   }

   public String getCode()
   {
      return code;
   }

   public String getTranscript()
   {
      return transcript;
   }

   public NikudLetterDistinction getDistinction()
   {
      return distinction;
   }

   public int getPixelWidth()
   {
      return pixelWidth;
   }

   public boolean isHandwritten()
   {
      return handwritten;
   }

   public static String getLetterPatternStringForSingleLetterDocument()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (NikudLetter letter : NikudLetter.values())
      {
         if (NikudLetterDistinction.LETTER == letter.getDistinction()
               && letter != GERSCHAYIM && letter != GERESCH
               && letter != NEWSPACE && letter != SPACE)
         {
            joiner.add(letter.code);
            joiner.add(letter.code.toLowerCase());
         }
      }
      return joiner.toString();
   }

   @Override
   public boolean isNewspace()
   {
      return NikudLetter.NEWSPACE == this;
   }

   @Override
   public boolean isSpace()
   {
      return NikudLetter.SPACE == this;
   }

   @Override
   public LetterType isType()
   {
      return LetterType.HEBREW;
   }

   @Override
   public Letter getNewspace()
   {
      return NikudLetter.NEWSPACE;
   }

   public String getGermanPictureName()
   {
      return Common.getTranslator().realisticTranslate(germanPictureName);
   }
}
