
package vokabeltrainer.table.list.editor;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import vokabeltrainer.common.ApplicationColors;

public class AntiFocusTextField extends JTextField
{
   private static final long serialVersionUID = -4364086687323301340L;

   public AntiFocusTextField(String value)
   {
      super(value);
      setFocusable(false);
      setOpaque(true);
      setBackground(ApplicationColors.getBackgroundGold());
      setBorder(BorderFactory.createEmptyBorder());
      setEnabled(true);
   }
   
   public AntiFocusTextField()
   {
      setFocusable(false);
      setOpaque(true);
      setBackground(ApplicationColors.getBackgroundGold());
      setBorder(BorderFactory.createEmptyBorder());
      setEnabled(true);
   }

   @Override
   public String toString()
   {
      return this.getText();
   }
   
   
}
