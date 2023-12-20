package vokabeltrainer.panels.input;

import javax.swing.JComboBox;

import vokabeltrainer.types.Chapter;

public class ChapterComboBox extends JComboBox<Chapter>
{
   private static final long serialVersionUID = -5633721990953684860L;
   
   public ChapterComboBox ()
   {
      this.setRenderer(new ChapterComboBoxRenderer());
      this.setLightWeightPopupEnabled(true);
      this.setEditable(false);
   }
}
