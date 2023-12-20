package vokabeltrainer.types;

import vokabeltrainer.editing.LetterHelper;

public class Hebrew
{
   private boolean simpleHebrew = true;
   private String hebrew = "";
   private String hebrewPlene = "";
   private String hebrewDefektiv = "";

   public Hebrew()
   {
      
   }

   public Hebrew(String hebrew, String hebrewPlene, String hebrewDefektiv,
         boolean simpleHebrew)
   {
      this.hebrew = hebrew;
      this.hebrewPlene = hebrewPlene;
      this.hebrewDefektiv = hebrewDefektiv;
      this.simpleHebrew = simpleHebrew;
   }

   public boolean isSimpleHebrew()
   {
      return simpleHebrew;
   }

   public void setSimpleHebrew(boolean simpleHebrew)
   {
      this.simpleHebrew = simpleHebrew;
   }

   public String getHebrewPlene()
   {
      return hebrewPlene;
   }

   public void setHebrewPlene(String hebrewPlene)
   {
      this.hebrewPlene = hebrewPlene;
   }

   public String getHebrewDefektiv()
   {
      return hebrewDefektiv;
   }

   public void setHebrewDefektiv(String hebrewDefektiv)
   {
      this.hebrewDefektiv = hebrewDefektiv;
   }

   public String getHebrew()
   {
      return hebrew;
   }
   
   public String getHebrewNoMatterWhichKind()
   {
      if(simpleHebrew)
      {
         return hebrew;
      }
      
      return hebrewPlene + " | " + hebrewDefektiv;
   }

   public void setHebrew(String hebrew)
   {
      this.hebrew = hebrew;
   }

   @Override
   public String toString()
   {
      if (this.simpleHebrew)
      {
         return hebrew;
      }
      return hebrewPlene + " | " + hebrewDefektiv;
   }
   
   public String toTableEntry()
   {
      if (this.simpleHebrew)
      {  
         return LetterHelper.findHebrewWithoutPunctation(hebrew);
      }      
      
      return LetterHelper.findHebrewWithoutPunctation(hebrewPlene) + " | " + LetterHelper.findHebrewWithoutPunctation(hebrewDefektiv);
   }

   public String getHewbrewAccordingToType(SelectionHebrewType selectionType)
   {
      switch (selectionType)
      {
      case DEFEKTIV:
         return hebrewDefektiv;
      case PLENE:
         return hebrewPlene;
      case SIMPLE:
         return hebrew;
      }
      return null;
   }
}
