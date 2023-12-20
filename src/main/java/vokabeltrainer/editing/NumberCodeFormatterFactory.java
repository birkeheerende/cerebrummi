package vokabeltrainer.editing;

import java.awt.Toolkit;
import java.text.ParseException;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.text.DefaultFormatterFactory;

import org.apache.commons.lang3.StringUtils;

public class NumberCodeFormatterFactory extends DefaultFormatterFactory
{
   private static final long serialVersionUID = -5068274823185920930L;
   
   private NumberCodeFormatter codeFormatter = new NumberCodeFormatter();

   @Override
   public JFormattedTextField.AbstractFormatter getFormatter(
         JFormattedTextField tf)
   {
      return codeFormatter;
   }

   @Override
   public JFormattedTextField.AbstractFormatter getDisplayFormatter()
   {
      return codeFormatter;
   }

   @Override
   public JFormattedTextField.AbstractFormatter getDefaultFormatter()
   {
      return codeFormatter;
   }

   @Override
   public JFormattedTextField.AbstractFormatter getEditFormatter()
   {
      return codeFormatter;
   }

   @Override
   public JFormattedTextField.AbstractFormatter getNullFormatter()
   {
      return codeFormatter;
   }

   class NumberCodeFormatter extends AbstractFormatter
   {
      private static final long serialVersionUID = 7933675034782342475L;
      String pattern = NumberLetter.getPatternString();
      
      @Override
      public Object stringToValue(String text) throws ParseException
      {
         if(text == null)
         {
            return "";
         }
         if(text.isEmpty())
         {
            return text;
         }
         try
         {
            return Integer.valueOf(text);
         }
         catch(Exception e)
         {
            return "";
         }
      }

      @Override
      public String valueToString(Object value) throws ParseException
      {
         if(value == null)
         {
            return "";
         }
         if(value instanceof Integer)
         {
            return String.valueOf(value);
         }
         if(value instanceof String)
         {
            String text = (String)value;
            if(text.length()>4)
            {
               text = text.substring(text.length()-4);
            }
            
            if(text.isEmpty())
            {
               return text;
            }
            List<String> list = LetterHelper.findLetterCodes(text);
            for (String letter : list)
            {
               if (!StringUtils.containsIgnoreCase(pattern, letter))
               {
                  Toolkit.getDefaultToolkit().beep();
                  return "";
               }
            }
            return text;
         }
         return "";
      }
   }
}
