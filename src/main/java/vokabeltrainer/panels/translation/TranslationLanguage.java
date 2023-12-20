package vokabeltrainer.panels.translation;

import java.util.UUID;

import javax.swing.JTextField;

public class TranslationLanguage extends JTextField
{
   private static final long serialVersionUID = 6017959608668426572L;
   private UUID uuid = null;
   
   public TranslationLanguage()
   {
      
   }

   public String toString()
   {
      return getText();
   }

   public UUID getUuid()
   {
      return uuid;
   }

   public void setUuid(UUID uuid)
   {
      this.uuid = uuid;
   }
}
