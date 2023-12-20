package vokabeltrainer.editing;

import java.awt.Toolkit;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import org.apache.commons.lang3.StringUtils;

public class ExtraInformationDocument extends DefaultStyledDocument
{
   private static final long serialVersionUID = 216678564395494689L;

   private static final int NUMBER_OF_LETTERS_ALLOWED = 600;

   private String signPattern;
   private String numberPattern;

   public ExtraInformationDocument()
   {
      signPattern = SignLetter.getPatternStringExtraInformation();
      numberPattern = NumberLetter.getPatternString();
   }

   @Override
   public void replace(int offset, int length, String text, AttributeSet attrs)
         throws BadLocationException
   {
      if (text != null && !text.isEmpty())
      {
         if (getLength() + text.length() - length > NUMBER_OF_LETTERS_ALLOWED)
         {
            text = text
                  .substring(0,
                        NUMBER_OF_LETTERS_ALLOWED - (getLength() - length));
            if (text.isEmpty())
            {
               Toolkit.getDefaultToolkit().beep();
               return;
            }
         }

         List<String> list = LetterHelper.findLetterCodes(text);
         StringBuilder builder = new StringBuilder();

         if (list == null || list.isEmpty())
         {
            Toolkit.getDefaultToolkit().beep();
            return;
         }

         checking(list, builder);
         super.replace(offset, length, builder.toString(), attrs);
         return;
      }
      super.replace(offset, length, text, attrs);
   }

   @Override
   public void insertString(int offset, String str, AttributeSet attr)
         throws BadLocationException
   {
      if (str != null && !str.isEmpty())
      {
         if (getLength() + str.length() > NUMBER_OF_LETTERS_ALLOWED)
         {
            str = str.substring(0, NUMBER_OF_LETTERS_ALLOWED - getLength());
            if (str.isEmpty())
            {
               Toolkit.getDefaultToolkit().beep();
               return;
            }
         }

         List<String> list = LetterHelper.findLetterCodes(str);
         StringBuilder builder = new StringBuilder();

         if (list == null || list.isEmpty())
         {
            Toolkit.getDefaultToolkit().beep();
            return;
         }

         checking(list, builder);
         super.insertString(offset, builder.toString(), attr);
         return;
      }
      super.insertString(offset, str, attr);
   }

   private void checking(List<String> list, StringBuilder builder)
   {
      for (int i = 0; i < list.size(); i++)
      {
         Letter germanLetter = LetterHelper
               .getLetterFromCode(list.get(i), LetterType.GERMAN);
         Letter nikudLetter = LetterHelper.getLetterFromCode(list.get(i), LetterType.HEBREW);
         if (germanLetter != null && germanLetter instanceof GermanLetter)
         {
            // okay
            builder.append(germanLetter.getUnicode());
         }
         else if (nikudLetter != null && nikudLetter instanceof NikudLetter)
         {
            // okay
            builder.append(nikudLetter.getUnicode());
         }
         else if (StringUtils.containsIgnoreCase(signPattern, list.get(i)))
         {
            // okay
            builder
                  .append(LetterHelper
                        .getLetterFromCode(list.get(i), LetterType.SIGN)
                        .getUnicode());
         }
         else if (StringUtils.containsIgnoreCase(numberPattern, list.get(i)))
         {
            // okay
            builder
                  .append(LetterHelper
                        .getLetterFromCode(list.get(i), LetterType.NUMBER)
                        .getUnicode());
         }
         else
         {
            // remove letter
         }
      }
   }
}
