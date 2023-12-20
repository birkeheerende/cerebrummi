package vokabeltrainer.panels;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Settings;
import vokabeltrainer.panels.translation.TranslationCode;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.resources.Buchstabenbilder;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class TranslationPanel extends JPanel
{
   private static final long serialVersionUID = 369293645105172512L;

   private JButton applyButton;
   private JComboBox<TranslationCode> chooseLanguage;

   TranslationPanel()
   {
      setLayout(new BullsEyeLayout(this));
      
      add(initChooseLanguage());

      initController();
   }

   private Component initChooseLanguage()
   {      
      JPanel horizontal = new JPanel();
      TrainLayout horizontalLayout = new TrainLayout(horizontal, 15);
      horizontal.setLayout(horizontalLayout);
      
      JLabel appTranslation = new JLabel();
      
      chooseLanguage = new JComboBox<>(TranslationCode.valuesAvailable());
      chooseLanguage.setMinimumSize(new Dimension(300,30));
      chooseLanguage.setMaximumSize(new Dimension(300,50));
      
      applyButton = new JButton(new ImageIcon(ApplicationImages.getSelectDone()));
      
      horizontal.add(appTranslation);
      horizontal.add(chooseLanguage);
      horizontal.add(applyButton);
      
      return horizontal;
   }

   private void initController()
   {   
      applyButton.addActionListener(event -> {
         TranslationCode choosen = chooseLanguage.getItemAt(chooseLanguage.getSelectedIndex());
         Settings.setTranslationCode(choosen);
         Common.setTranslator(new Translator());
         try 
         {
			Buchstabenbilder.read();
		 } 
         catch (Exception e) 
         {
			// nothing
		 }
      });
   }
}
