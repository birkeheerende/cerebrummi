package vokabeltrainer.editing;

import java.util.StringJoiner;

public enum SignLetter implements Letter
{
   QUESTION_MARK(" 003f", "\u003f"),            // ?
   EXCLAMATION_MARK(" 0021", "\u0021"),         // !
   FULL_STOP(" 002e", "\u002e"),                // .
   APOSTROPHE(" 0027", "\u0027"),               // '
   LEFT_PARENTHESIS(" 0028", "\u0028"),         // (
   RIGHT_PARENTHESIS(" 0029", "\u0029"),        // )
   COMMA(" 002c", "\u002c"),                    // ,
   HYPHEN_MINUS(" 002d", "\u002d"),             // -
   QUOTATION_MARK(" 0022", "\u005c\u0022"),     // "
   SECTION_SIGN(" 00A7", "\u00A7"),             // §
   DOLLAR_SIGN(" 0024", "\u0024"),              // $
   EURO_SIGN(" 20AC", "\u20AC"),                // €
   PERCENT_SIGN(" 0025", "\u0025"),             // %
   SOLIDUS(" 002F", "\u002F"),                  // /
   LEFT_SQUARE_BRACKET(" 005B", "\u005B"),      // [
   RIGHT_SQUARE_BRACKET(" 005D", "\u005D"),     // ]
   LEFT_CURLY_BRACKET(" 007B", "\u007B"),       // {
   RIGHT_CURLY_BRACKET(" 007D", "\u007D"),      // }
   EQUALS_SIGN(" 003D", "\u003D"),              // =
   REVERSE_SOLIDUS(" 005C", "\\u005C"),         // \ causes problems, do not allow anywhere
   ASTERISK(" 002A", "\u002A"),                 // *
   PLUS_SIGN(" 002B", "\u002B"),                // +
   NUMBER_SIGN(" 0023", "\u0023"),              // #
   SEMICOLON(" 003B", "\u003B"),                // ;
   COLON(" 003A", "\u003A"),                    // :
   LOW_LINE(" 005F", "\u005F"),                 // _
   DEGREE_SIGN(" 00B0", "\u00B0"),               // °
   CIRCUMFLEX_ACCENT(" 005E", "\u005E"),         // ^ does not work properly, known Swing bug
   ACUTE_ACCENT(" 00B4", "\u00B4"),              // ´ does not work properly, known Swing bug
   GRAVE_ACCENT(" 0060", "\u0060"),              // ` does not work properly, known Swing bug
   COMMERCIAL_AT(" 0040", "\u0040"),             // @
   MICRO_SIGN(" 00B5", "\u00B5"),                // µ
   LESS_THAN_SIGN(" 003C", "\u003C"),            // <
   GREATER_THAN_SIGN(" 003E", "\u003E"),         // >
   VERTICAL_LINE(" 007C", "\u007C"),             // |
   AMPERSAND(" 0026","\u0026");                  // &

   private String code;
   private String unicode;

   private static SignLetter[] germanSigns = { QUESTION_MARK, EXCLAMATION_MARK,
         FULL_STOP, APOSTROPHE, LEFT_PARENTHESIS, RIGHT_PARENTHESIS,
         HYPHEN_MINUS, QUOTATION_MARK, SECTION_SIGN, DOLLAR_SIGN, EURO_SIGN,
         PERCENT_SIGN, SOLIDUS, LEFT_SQUARE_BRACKET, RIGHT_SQUARE_BRACKET,
         LEFT_CURLY_BRACKET, RIGHT_CURLY_BRACKET, EQUALS_SIGN, ASTERISK,
         PLUS_SIGN, NUMBER_SIGN, SEMICOLON, COLON, LOW_LINE, DEGREE_SIGN,
         COMMERCIAL_AT, MICRO_SIGN,
         LESS_THAN_SIGN, GREATER_THAN_SIGN, VERTICAL_LINE, AMPERSAND,
         CIRCUMFLEX_ACCENT, ACUTE_ACCENT, GRAVE_ACCENT};
   
   private static SignLetter[] germanSignsWithComma = { QUESTION_MARK, EXCLAMATION_MARK,
         FULL_STOP, APOSTROPHE, LEFT_PARENTHESIS, RIGHT_PARENTHESIS,
         HYPHEN_MINUS, QUOTATION_MARK, SECTION_SIGN, DOLLAR_SIGN, EURO_SIGN,
         PERCENT_SIGN, SOLIDUS, LEFT_SQUARE_BRACKET, RIGHT_SQUARE_BRACKET,
         LEFT_CURLY_BRACKET, RIGHT_CURLY_BRACKET, EQUALS_SIGN, ASTERISK,
         PLUS_SIGN, NUMBER_SIGN, SEMICOLON, COLON, LOW_LINE, DEGREE_SIGN,
         COMMERCIAL_AT, MICRO_SIGN,
         LESS_THAN_SIGN, GREATER_THAN_SIGN, VERTICAL_LINE, AMPERSAND, COMMA };

   private static SignLetter[] hebrewSigns = { QUESTION_MARK, EXCLAMATION_MARK,
         FULL_STOP };
   private static SignLetter[] hebrewSignsWithComma = { QUESTION_MARK,
         EXCLAMATION_MARK, FULL_STOP, COMMA };
   
   private static SignLetter[] nikudSigns = { QUESTION_MARK, EXCLAMATION_MARK,
         FULL_STOP };
   private static SignLetter[] nikudSignsWithComma = { QUESTION_MARK,
         EXCLAMATION_MARK, FULL_STOP, COMMA };

   private static SignLetter[] extraInformationSigns = { QUESTION_MARK,
         EXCLAMATION_MARK, FULL_STOP, APOSTROPHE, LEFT_PARENTHESIS,
         RIGHT_PARENTHESIS, HYPHEN_MINUS, COMMA, QUOTATION_MARK, SECTION_SIGN,
         DOLLAR_SIGN, EURO_SIGN, PERCENT_SIGN, SOLIDUS, LEFT_SQUARE_BRACKET,
         RIGHT_SQUARE_BRACKET, LEFT_CURLY_BRACKET, RIGHT_CURLY_BRACKET,
         EQUALS_SIGN, ASTERISK, PLUS_SIGN, NUMBER_SIGN, 
         SEMICOLON, COLON, LOW_LINE, DEGREE_SIGN, COMMERCIAL_AT, MICRO_SIGN, LESS_THAN_SIGN,
         GREATER_THAN_SIGN, VERTICAL_LINE, AMPERSAND };
   
   private static SignLetter[] forFileNames = {HYPHEN_MINUS, LOW_LINE};

   SignLetter(String code, String unicode)
   {
      this.code = code;
      this.unicode = unicode;
   }

   public static String getPatternStringGerman()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : germanSigns)
      {
         joiner.add(letter.getCode());
      }
      return joiner.toString();
   }

   public static String getPatternStringGermanWithComma()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : germanSignsWithComma)
      {
         joiner.add(letter.getCode());
      }
      return joiner.toString();
   }

   public static String getPatternStringHebrew()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : hebrewSigns)
      {
         joiner.add(letter.getCode());
      }
      return joiner.toString();
   }

   public static String getPatternStringHebrewWithComma()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : hebrewSignsWithComma)
      {
         joiner.add(letter.getCode());
      }
      return joiner.toString();
   }
   
   public static String getPatternStringNikud()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : nikudSigns)
      {
         joiner.add(letter.getCode());
      }
      return joiner.toString();
   }
   
   public static String getPatternStringNikudWithComma()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : nikudSignsWithComma)
      {
         joiner.add(letter.getCode());
      }
      return joiner.toString();
   }

   public static String getPatternStringExtraInformation()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : extraInformationSigns)
      {
         joiner.add(letter.getCode());
      }
      return joiner.toString();
   }
   
   public static String getPatternStringForFileNames()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : forFileNames)
      {
         joiner.add(letter.getCode());
      }
      return joiner.toString();
   }

   @Override
   public String getCode()
   {
      return code;
   }

   @Override
   public String getUnicode()
   {
      return unicode;
   }

   @Override
   public boolean isNewspace()
   {
      return false;
   }

   @Override
   public boolean isSpace()
   {
      return false;
   }
   
   @Override
   public LetterType isType()
   {
      return LetterType.SIGN;
   }

   @Override
   public Letter getNewspace()
   {
      return null;
   }
   
   @Override
   public int getPixelWidth()
   {
      return -1;
   }
   
   @Override
   public String getTranscript()
   {
      return name().toLowerCase();
   }
}
