package vokabeltrainer.panels.letterpicture;

import java.awt.Dimension;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;
import vokabeltrainer.editing.LetterForAnalysis;
import vokabeltrainer.editing.LetterHelper;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class LetterPictureWordPanel extends JPanel
{
   private static final long serialVersionUID = 4694738420843719008L;

   private Card[] cards = { Card.LETTER, Card.HEBREW };
   private Card[] cards2on = { Card.PICTURE, Card.GERMAN };
   private Card[] cards2off = { Card.BLANK };

   public LetterPictureWordPanel()
   {
      this.setLayout(new BullsEyeLayout(this));
      this.setOpaque(false);
   }

   public void displayNikudWord(String nikudWord)
   {
      removeAll();

      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal));
      horizontal.setBackground(ApplicationColors.getTexturedBackgroundColor());

      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));
      vertical.setBackground(ApplicationColors.getTexturedBackgroundColor());

      JPanel displayPanel = new JPanel();
      displayPanel.setLayout(new TrainLayout(displayPanel));
      displayPanel.setBackground(ApplicationColors.getTexturedBackgroundColor());
      displayPanel.setMinimumSize(new Dimension(1268, 200));
      displayPanel.setMaximumSize(new Dimension(1268, 200));

      List<LetterForAnalysis> hebrewLettersCode = LetterHelper
            .findNikudLetterForAnalysisList(nikudWord);
      Collections.reverse(hebrewLettersCode);

      for (LetterForAnalysis letter : hebrewLettersCode)
      {
         displayPanel.add(new NikudPictureButtonPanel(letter, cards));
      }

      vertical.add(displayPanel);

      JPanel displayPanel2 = new JPanel();
      displayPanel2.setLayout(new TrainLayout(displayPanel2));
      displayPanel2.setBackground(ApplicationColors.getTexturedBackgroundColor());
      displayPanel2.setMinimumSize(new Dimension(1268, 200));
      displayPanel2.setMaximumSize(new Dimension(1268, 200));

      for (LetterForAnalysis letter : hebrewLettersCode)
      {
         if(Settings.isLetterImagesOn())
         {
            displayPanel2.add(new NikudPictureButtonPanel(letter, cards2on));
         }
         else
         {
            displayPanel2.add(new NikudPictureButtonPanel(letter, cards2off));
         }
      }

      vertical.add(displayPanel2);

      horizontal.add(vertical);
      add(horizontal);

      validate();
      repaint();
   }

   public void clear()
   {
      removeAll();

      JLabel dummy = new JLabel();
      dummy.setMinimumSize(new Dimension(1268, 200));
      dummy.setMaximumSize(new Dimension(1268, 200));
      dummy.setBackground(ApplicationColors.getTransparent());
      dummy.setOpaque(false);
      add(dummy);

      validate();
      repaint();
   }

}
