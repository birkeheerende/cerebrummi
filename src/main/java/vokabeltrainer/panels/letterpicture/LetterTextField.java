package vokabeltrainer.panels.letterpicture;

import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.editing.LetterHelper;

public class LetterTextField extends JTextField
{
   private static final long serialVersionUID = -3945876344326402147L;

   private NikudLetter letter;
   
   public LetterTextField(NikudLetter letter)
   {
      this.letter = letter;
   }

   public NikudLetter getLetter()
   {
      return letter;
   }
   
   public boolean isOkay()
   {
      if(!StringUtils.strip(this.getText()).isEmpty())
      {
         return letter == LetterHelper.findNikudLetters(StringUtils.strip(this.getText())).get(0);
      }
      return false;
   }
}
