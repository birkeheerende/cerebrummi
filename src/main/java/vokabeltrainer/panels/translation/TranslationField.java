package vokabeltrainer.panels.translation;

import java.awt.Dimension;
import java.util.UUID;

import javax.swing.JTextField;

public class TranslationField extends JTextField
{
   private static final long serialVersionUID = -1180875060419483477L;

   private Translation translation;
   private TranslationCode code;
   private UUID uuid = null;
   private String name;
   
   public TranslationField(Translation translation)
   {
      this.translation = translation;
      this.setMinimumSize(new Dimension(400,30));
      this.setMaximumSize(new Dimension(600,30));
   }

   public Translation getTranslation()
   {
      return translation;
   }

   public TranslationCode getCode()
   {
      return code;
   }

   public void setCode(TranslationCode code)
   {
      this.code = code;
   }

   public UUID getUuid()
   {
      return uuid;
   }

   public void setUuid(UUID uuid)
   {
      this.uuid = uuid;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }
}
