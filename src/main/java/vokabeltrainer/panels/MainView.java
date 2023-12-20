package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vokabeltrainer.TextImage;
import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.Main;
import vokabeltrainer.common.Settings;
import vokabeltrainer.panels.dictionary.DictionaryController;
import vokabeltrainer.panels.dictionary.DictionaryViewConnector;
import vokabeltrainer.panels.trainer.TrainerController;
import vokabeltrainer.panels.trainer.dialog.StartTrainingController;
import vokabeltrainer.panels.trainer.dialog.StartTrainingView;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public class MainView extends JPanel
{
   private static final long serialVersionUID = -8541715304466855004L;

   private JButton vocabularyCardsButton;
   private JButton dictionaryButton;
   private JButton startButton;
   private JButton inputButton;
   private JButton statisticsButton;
   private JButton aboutButton;
   private JButton letterPicturesButton;
   private JButton successButton;
   private Component activeComponent;
   private InputPanel inputPanel;
   private DictionaryViewConnector dictionaryPanel;
   private StatisticsPanel statisticsPanel;
   private StartPanel startPanel;
   private SettingsPanel settingsPanel;
   private AlefbetPanel letterPicturesPanel;
   private SuccessPanel successPanel;
   private JButton languageButton;

   private JMenuBar menuBar;
   
   private JButton backButton;
   private TranslationPanel languagePanel;
   
   private Translator translator;

   public MainView()
   {
      initContent();
      initController();
      activeComponent = startPanel;
      add(activeComponent);
   }

   private void initContent()
   {
      setLayout(new BorderLayout());
      setBackground(ApplicationColors.getBackgroundGold());
      
      translator = Common.getTranslator();

      initToolBar();

      startPanel = new StartPanel();
      inputPanel = new InputPanel();
      dictionaryPanel = new DictionaryController().getDictionaryPanel();
      letterPicturesPanel = new AlefbetPanel();
      statisticsPanel = new StatisticsPanel();
      settingsPanel = new SettingsPanel();
      successPanel = new SuccessPanel();
   }
   
   private void initLanguageContent()
   {
      this.removeAll();
      
      initLanguageToolBar();
      languagePanel = new TranslationPanel();
      add(languagePanel);
      initLanguageController();
      
      Main.resetMenuBar();
      this.validate();
      this.repaint();
   }

   private void initLanguageController()
   {
      backButton.addActionListener(event -> {
         this.removeAll();
         initContent();
         initController();
         activeComponent = startPanel;
         add(activeComponent);
         Main.resetMenuBar();
         Common.getjFrame().validate();
         Common.getjFrame().repaint();
      });
      
   }

   private void initLanguageToolBar()
   {
      menuBar = new JMenuBar();
      menuBar.setOpaque(true);
      menuBar.setBackground(ApplicationColors.getLightGold());
      menuBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      menuBar.setMinimumSize(new Dimension(1200,80));
      menuBar.setMaximumSize(new Dimension(6000,80));
      
      backButton = new JButton(new ImageIcon(ApplicationImages.getBack()));
      
      menuBar.add(backButton);
   }

   private void initToolBar()
   {
      menuBar = new JMenuBar();
      menuBar.setOpaque(true);
      menuBar.setBackground(ApplicationColors.getLightGold());
      menuBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      menuBar.setMinimumSize(new Dimension(1200,80));
      menuBar.setMaximumSize(new Dimension(6000,80));

      startButton = new JButton(translator.realisticTranslate(Translation.START));
      inputButton = new JButton(translator.realisticTranslate(Translation.EINGABE));
      vocabularyCardsButton = new JButton(translator.realisticTranslate(Translation.VOKABELN_ABFRAGEN));
      dictionaryButton = new JButton(translator.realisticTranslate(Translation.WOERTERBUCH));
      statisticsButton = new JButton(translator.realisticTranslate(Translation.TRAININGSUEBERSICHT));
      aboutButton = new JButton(new ImageIcon(ApplicationImages.getLogo24()));
      letterPicturesButton = new JButton(translator.realisticTranslate(Translation.ALEFBET));
      successButton = new JButton(translator.realisticTranslate(Translation.KARTEIKASTEN));
      languageButton = new JButton(new ImageIcon(ApplicationImages.getL18n()));

      startButton.setFont(ApplicationFonts.getToolbarButtonFont());
      inputButton.setFont(ApplicationFonts.getToolbarButtonFont());
      vocabularyCardsButton.setFont(ApplicationFonts.getToolbarButtonFont());
      dictionaryButton.setFont(ApplicationFonts.getToolbarButtonFont());
      statisticsButton.setFont(ApplicationFonts.getToolbarButtonFont());
      letterPicturesButton.setFont(ApplicationFonts.getToolbarButtonFont());
      successButton.setFont(ApplicationFonts.getToolbarButtonFont());
      languageButton.setFont(ApplicationFonts.getToolbarButtonFont());

      menuBar.add(startButton);
      menuBar.add(inputButton);
      menuBar.add(dictionaryButton);
      menuBar.add(vocabularyCardsButton);
      menuBar.add(letterPicturesButton);
      menuBar.add(statisticsButton);
      menuBar.add(successButton);
      menuBar.add(languageButton);
      menuBar.add(Box.createHorizontalGlue());
      menuBar.add(aboutButton);
   }

   private void initController()
   {
      startButton.addActionListener(event -> {
         moveToStartPanel();
      });
      
      inputButton.addActionListener(event -> {
         if (activeComponent != null)
         {
            remove(activeComponent);
         }
         activeComponent = (Component) inputPanel;
         inputPanel.reset();
         add(activeComponent);
         validate();
         repaint();
      });

      dictionaryButton.addActionListener(event -> {
         if (activeComponent != null)
         {
            remove(activeComponent);
         }
         activeComponent = (Component) dictionaryPanel;
         dictionaryPanel.setValues();
         add(activeComponent);
         validate();
         repaint();
      });

      vocabularyCardsButton.addActionListener(event -> {
         
         if(Settings.isSchabbat_modus() && Common.isSchabbat())
         {
            return;
         }
         
         Data.determineReloadDatabases();
         StartTrainingView dialog = new StartTrainingController()
               .getStartTrainingView();
         dialog.setLocationRelativeTo(null);
         dialog.setVisible(true);

         
         if (dialog.isTraining())
         {
            if(dialog.getNewExpressions().isEmpty() && (dialog.getOldExpressions() == null || dialog.getOldExpressions().isEmpty()))
            {
               this.showNoWordsForTraining();
               return;
            }
            
            if (activeComponent != null)
            {
               remove(activeComponent);
            }

            TrainerView trainerPanel = new TrainerController(
                  dialog.getLanguageDirection(), dialog.getFieldOfTraining(),
                  dialog.getNewExpressions(), dialog.getOldExpressions()).getTrainerView();

            activeComponent = trainerPanel;
            add(activeComponent);
            validate();
            repaint();
         }
      });

      letterPicturesButton.addActionListener(event -> {
         if (activeComponent != null)
         {
            remove(activeComponent);
         }
         activeComponent = letterPicturesPanel;
         add(activeComponent);
         validate();
         repaint();
      });

      statisticsButton.addActionListener(event -> {
         Data.determineReloadDatabases();
         moveToStatisticsPanel();
      });
      
      successButton.addActionListener(event -> {
         Data.determineReloadDatabases();
         if (activeComponent != null)
         {
            remove(activeComponent);
         }
         activeComponent = successPanel;
         successPanel.reset();
         add(activeComponent);
         validate();
         repaint();
      });

      aboutButton.addActionListener(event -> {
         if (activeComponent != null)
         {
            remove(activeComponent);
         }
         activeComponent = new JScrollPane(settingsPanel);
         settingsPanel.setValues();
         add(activeComponent);
         validate();
         repaint();
      });
      
      languageButton.addActionListener(event -> {
         initLanguageContent();
      });
   }

   public void moveToStartPanel()
   {
      if (activeComponent != null)
      {
         remove(activeComponent);
      }
      startPanel.setValues();
      activeComponent = startPanel;
      add(activeComponent);
      validate();
      repaint();
   }
   
   public void moveToStatisticsPanel()
   {
      if (activeComponent != null)
      {
         remove(activeComponent);
      }
      statisticsPanel.setValues();
      activeComponent = statisticsPanel;
      add(activeComponent);
      validate();
      repaint();
   }
   
   private void showNoWordsForTraining()
   {
      JOptionPane.showMessageDialog(Common.getjFrame(), "", Settings.getWindowTitle(),
            JOptionPane.PLAIN_MESSAGE,
            new ImageIcon(TextImage.make(translator.realisticTranslate(Translation.KEINE_WORTE_ZUM_UEBEN_AUSGEWAEHLT),
                  translator.realisticTranslate(Translation.BITTE_NEUE_WORTE_DURCH_EINGABE_EINER_ANZAHL),
                  translator.realisticTranslate(Translation.AUSWAHLEN__UM_DANN_DAS_TRAINING_ZU_STARTEN_))));
   }

   public JButton getVocabularyCardsButton()
   {
      return vocabularyCardsButton;
   }

   public JButton getDictionaryButton()
   {
      return dictionaryButton;
   }

   public JButton getStartButton()
   {
      return startButton;
   }

   public JButton getStatisticsButton()
   {
      return statisticsButton;
   }

   public JButton getAboveButton()
   {
      return aboutButton;
   }

   public JButton getLetterPicturesButton()
   {
      return letterPicturesButton;
   }

   public DictionaryViewConnector getDictionaryPanel()
   {
      return dictionaryPanel;
   }

   public JMenuBar getMenuBar()
   {
      return menuBar;
   }

}
