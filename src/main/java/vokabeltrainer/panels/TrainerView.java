package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.ComponentTitledBorder;
import vokabeltrainer.InfoTextField;
import vokabeltrainer.InputHebrewPanel;
import vokabeltrainer.InputHebrewPanel.Selection;
import vokabeltrainer.TextImage;
import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Settings;
import vokabeltrainer.editing.GermanDocument;
import vokabeltrainer.editing.NikudDocument;
import vokabeltrainer.keyboards.KeyboardHebrewAllLetters;
import vokabeltrainer.keyboards.OneFocusTraversalPolicy;
import vokabeltrainer.panels.letterpicture.LetterPictureWordPanel;
import vokabeltrainer.panels.trainer.HebrewAnswerWordPanel;
import vokabeltrainer.panels.trainer.ImagePanelBlue;
import vokabeltrainer.panels.trainer.ImagePanelError;
import vokabeltrainer.panels.trainer.ImagePanelGreen;
import vokabeltrainer.panels.trainer.ImagePanelStart;
import vokabeltrainer.panels.trainer.Result;
import vokabeltrainer.panels.trainer.TrainerControllerConnector;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Language;

public class TrainerView extends BackgroundPanelTiled
{
   private static final long serialVersionUID = -6552073033311684589L;

   private LetterPictureWordPanel pictureWordPanelPlene;
   private LetterPictureWordPanel pictureWordPanelDefektiv;
   private JPanel swapPanel;
   private ImagePanelGreen imageFieldGreen;
   private ImagePanelStart imageFieldStart;
   private ImagePanelError imageFieldError;
   private ImagePanelBlue imageFieldBlue;
   private CardLayout cardLayout;
   private JPanel questionPanel;
   private JTextField questionFieldGerman;
   private InputHebrewPanel questionFieldHebrew;
   private JTextPane grammarInfoField;
   private JTextPane additionalInfoField;
   private InfoTextField answerField;
   private JLabel languageDirectionLabel;
   private JCheckBox additionalInfo;
   private JCheckBox grammarInfo;
   private JPanel answerPanel;
   private JButton sendButton;
   private Language languageDirection;
   private JPanel feedbackPanel;
   private JButton nextWordButton;
   private JLabel wordsRight;
   private JLabel wordsWrong;
   private JLabel wordsToDo;
   private int wordsRightNumber;
   private int wordsWrongNumber;
   private JButton answerOkay;
   private JButton answerUndecided;
   private JButton answerNotOkay;
   private JButton stopTrainingButton;
   private JButton soundButton;
   private KeyboardHebrewAllLetters keyboardNikud;
   private TrainerControllerConnector connector;
   private JButton infoStopTrainingButton;
   private JPanel infoStopTrainingPanel;
   private JSlider soundslider;
   private JCheckBox pictureToggleBox;
   private JPanel verticalTrainerPanel;
   private Translator translator = Common.getTranslator();
   private JPanel textFieldPanelWrapper;

   public TrainerView(TrainerControllerConnector connector)
   {
      this.connector = connector;
      this.languageDirection = connector.getLanguageDirection();
      BullsEyeLayout trainerLayout = new BullsEyeLayout(this);
      setLayout(trainerLayout);
      setBackground(ApplicationColors.getTexturedBackgroundColor());
      verticalTrainerPanel = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(verticalTrainerPanel);
      verticalTrainerPanel.setLayout(verticalLayout);
      verticalTrainerPanel.setBackground(ApplicationColors.getTexturedBackgroundColor());
      setBorder(BorderFactory.createEmptyBorder());
      setOpaque(false);
      initGui();
      this.add(verticalTrainerPanel);
      initController();
   }

   public void init()
   {
      languageDirectionLabel
            .setText(
                  languageDirection.equals(Language.HEBREW_TO_GERMAN)
                        ? translator.realisticTranslate(Translation.HEBRAEISCH)
                              + " >> "
                              + translator
                                    .realisticTranslate(Translation.DEUTSCH)
                        : translator
                                    .realisticTranslate(Translation.DEUTSCH)
                              + translator.realisticTranslate(
                                    Translation.HEBRAEISCH));

      initTextField(languageDirection);
      initQuestionPanel(languageDirection);

      connector.setNextTest();

      this.textFieldPanelWrapper.validate();
      this.textFieldPanelWrapper.repaint();
      this.questionPanel.validate();
      this.questionPanel.repaint();
      answerField.setRequestFocusEnabled(true);
   }

   private void initGui()
   {
      verticalTrainerPanel.add(initTextFieldPanel()); 
      verticalTrainerPanel.add(initTopPanel());
      pictureWordPanelPlene = new LetterPictureWordPanel();
      pictureWordPanelPlene.setPreferredSize(new Dimension(1200, 110));
      pictureWordPanelPlene.setOpaque(true);
      pictureWordPanelPlene.setBackground(ApplicationColors.getTexturedBackgroundColor());
      
      JScrollPane scroller = new JScrollPane(pictureWordPanelPlene);
      scroller.setMinimumSize(new Dimension(1200, 110));
      scroller.setMaximumSize(new Dimension(1200, 110));
      scroller.setBorder(BorderFactory.createEmptyBorder());
      scroller.setOpaque(true);
      scroller.setBackground(ApplicationColors.getTexturedBackgroundColor());
      verticalTrainerPanel.add(scroller);
      
      pictureWordPanelDefektiv = new LetterPictureWordPanel();
      pictureWordPanelDefektiv.setPreferredSize(new Dimension(1200, 110));
      pictureWordPanelDefektiv.setOpaque(true);
      pictureWordPanelDefektiv.setBackground(ApplicationColors.getTexturedBackgroundColor());
      
      JScrollPane scroller2 = new JScrollPane(pictureWordPanelDefektiv);
      scroller2.setMinimumSize(new Dimension(1200, 110));
      scroller2.setMaximumSize(new Dimension(1200, 110));
      scroller2.setBorder(BorderFactory.createEmptyBorder());
      scroller2.setOpaque(true);
      scroller2.setBackground(ApplicationColors.getTexturedBackgroundColor());
      
      verticalTrainerPanel.add(scroller2);
   }

   private Component initTextFieldPanel() 
   {	   
	   textFieldPanelWrapper = new JPanel();
	   BullsEyeLayout wrapperLayout = new BullsEyeLayout(textFieldPanelWrapper);
	   textFieldPanelWrapper.setLayout(wrapperLayout);
	   textFieldPanelWrapper.setBackground(ApplicationColors.getLightBlue());
	   
	   return textFieldPanelWrapper;
   }
   
   private void initTextField(Language languageDirection) 
   {
	   textFieldPanelWrapper.removeAll();
	   if (Language.GERMAN_TO_HEBREW.equals(languageDirection))
	      {
		     JPanel expandPanel = new JPanel();
		     TrainLayout beLayout = new TrainLayout(expandPanel);
		     expandPanel.setLayout(beLayout);
	         questionFieldGerman = new JTextField("test");
	         questionFieldGerman.setBackground(ApplicationColors.getLightBlue());
	         questionFieldGerman.setFont(ApplicationFonts.getGermanFont(20F));
	         questionFieldGerman.setBorder(BorderFactory.createTitledBorder(
	               BorderFactory.createEmptyBorder(), translator.realisticTranslate(
	                     Translation.WIE_LAUTET_DIE_UEBERSETZUNG_DIESES_BEGRIFFES_)));
	         questionFieldGerman
	               .setMinimumSize(new Dimension(1200, 160));
	         questionFieldGerman
	               .setMaximumSize(new Dimension(1250, 160));
	         questionFieldGerman.setEditable(false);
	         expandPanel.add(questionFieldGerman);
	         textFieldPanelWrapper.add(expandPanel);
	      }
	      else
	      {
	         questionFieldHebrew = new InputHebrewPanel(Selection.SIMPLE, 160, 10,
	               false, this, 1268, ApplicationColors.getLightBlue());
	         questionFieldHebrew.setBackground(ApplicationColors.getLightBlue());
	         questionFieldHebrew.setBorder(
	               BorderFactory.createTitledBorder(translator.realisticTranslate(
	                     Translation.WIE_LAUTET_DIE_UEBERSETZUNG_DIESES_BEGRIFFES_)));
	         questionFieldHebrew
	               .setMinimumSize(new Dimension(600, 160));
	         questionFieldHebrew
	               .setMaximumSize(new Dimension(1268, 160));
	         questionFieldHebrew.setEditable(false);
	         questionFieldHebrew.setEnabled(false);
	         textFieldPanelWrapper.add(questionFieldHebrew);
	      }
   }

private Component initTopPanel()
   {
      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal, 15));
      horizontal.setBackground(ApplicationColors.getTexturedBackgroundColor());

      questionPanel = new JPanel();
      questionPanel.setLayout(new TotemLayout(questionPanel, 10));
      questionPanel.setBackground(ApplicationColors.getTexturedBackgroundColor());

      horizontal.add(initLeftPanel());
      horizontal.add(questionPanel);
      horizontal.add(initRightPanel());

      return horizontal;
   }

   private JPanel initLeftPanel()
   {
      Font labelFont = ApplicationFonts.getGermanBoldFont(15F);

      JPanel verticalLeftPanel = new JPanel();
      verticalLeftPanel.setLayout(new TotemLayout(verticalLeftPanel, 15));
      verticalLeftPanel.setBackground(ApplicationColors.getGold());

      JPanel choices = new JPanel();
      choices.setLayout(new TrainLayout(choices, 15));
      choices.setBorder(BorderFactory.createEmptyBorder(30, 15, 0, 15));
      choices.setBackground(ApplicationColors.getGold());
      JPanel choicesLeft = new JPanel();
      choicesLeft.setLayout(new TotemLayout(choicesLeft));
      choicesLeft.setBackground(ApplicationColors.getGold());
      JPanel choicesRight = new JPanel();
      choicesRight.setLayout(new TotemLayout(choicesRight));
      choicesRight.setBackground(ApplicationColors.getGold());
      choices.add(choicesLeft);
      choices.add(choicesRight);

      JLabel label3 = new JLabel(
            translator.realisticTranslate(Translation.RICHTUNG));
      label3.setFont(labelFont);
      label3.setBackground(ApplicationColors.getGold());
      label3.setForeground(Color.WHITE);
      choicesLeft.add(label3);

      languageDirectionLabel = new JLabel();
      languageDirectionLabel.setFont(labelFont);
      languageDirectionLabel.setBackground(ApplicationColors.getGold());
      languageDirectionLabel.setForeground(Color.WHITE);
      choicesRight.add(languageDirectionLabel);

      JPanel numbers = new JPanel();
      numbers.setLayout(new TrainLayout(numbers, 15));
      numbers.setBackground(ApplicationColors.getGold());
      numbers.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

      JPanel numbersLeft = new JPanel();
      numbersLeft.setLayout(new TotemLayout(numbersLeft));
      numbersLeft.setBackground(ApplicationColors.getGold());
      JLabel wordsRightLabel = new JLabel(
            translator.realisticTranslate(Translation.RICHTIG));
      wordsRightLabel.setBackground(ApplicationColors.getGold());
      wordsRightLabel.setFont(labelFont);
      wordsRightLabel.setForeground(Color.WHITE);
      JLabel wordsWrongLabel = new JLabel(
            translator.realisticTranslate(Translation.FALSCH));
      wordsWrongLabel.setBackground(ApplicationColors.getGold());
      wordsWrongLabel.setFont(labelFont);
      wordsWrongLabel.setForeground(Color.WHITE);
      JLabel wordsToDoLabel = new JLabel(
            translator.realisticTranslate(Translation.ZU_TUN));
      wordsToDoLabel.setBackground(ApplicationColors.getGold());
      wordsToDoLabel.setFont(labelFont);
      wordsToDoLabel.setForeground(Color.WHITE);
      numbersLeft.add(wordsRightLabel);
      numbersLeft.add(wordsWrongLabel);
      numbersLeft.add(wordsToDoLabel);

      JPanel numbersRight = new JPanel();
      numbersRight.setLayout(new TotemLayout(numbersRight));
      numbersRight.setBackground(ApplicationColors.getGold());
      wordsRight = new JLabel(String.valueOf(wordsRightNumber));
      wordsRight.setBackground(ApplicationColors.getGold());
      wordsRight.setForeground(Color.WHITE);
      wordsRight.setFont(labelFont);
      wordsWrong = new JLabel(String.valueOf(wordsWrongNumber));
      wordsWrong.setBackground(ApplicationColors.getGold());
      wordsWrong.setFont(labelFont);
      wordsWrong.setForeground(Color.WHITE);
      wordsToDo = new JLabel(" ");
      wordsToDo.setBackground(ApplicationColors.getGold());
      wordsToDo.setFont(labelFont);
      wordsToDo.setForeground(Color.WHITE);
      numbersRight.add(wordsRight);
      numbersRight.add(wordsWrong);
      numbersRight.add(wordsToDo);
      numbers.add(numbersLeft);
      numbers.add(numbersRight);

      JPanel numberFiller = new JPanel(new FlowLayout());
      numberFiller.setBackground(ApplicationColors.getGold());
      numberFiller.setMinimumSize(new Dimension(60, 30));
      numberFiller.setMaximumSize(new Dimension(280, 60));

      nextWordButton = new JButton(
            translator.realisticTranslate(Translation.NAECHSTES_WORT));
      nextWordButton.setIcon(new ImageIcon(ApplicationImages.getStart()));
      nextWordButton.setEnabled(false);

      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal));

      JPanel soundFiller = new JPanel(new FlowLayout());
      soundFiller.setBackground(ApplicationColors.getGold());
      soundFiller.setMinimumSize(new Dimension(60, 60));
      soundFiller.setMaximumSize(new Dimension(280, 100));

      soundButton = new JButton(new ImageIcon(Settings.getSound()));
      soundButton.setBorder(BorderFactory.createEmptyBorder());
      soundButton.setOpaque(false);
      soundButton.setBackground(new Color(0, 0, 0, 0));

      soundFiller.add(soundButton);
      horizontal.add(soundFiller);

      soundslider = new JSlider();
      soundslider.setMinimum(-30);
      soundslider.setMaximum(5);
      soundslider.setValue((int) Settings.getVolume());
      soundslider.setMajorTickSpacing(5);
      soundslider.setMinorTickSpacing(1);
      soundslider.setPaintTicks(true);
      soundslider.setPaintLabels(true);
      soundslider.setSnapToTicks(true);

      pictureToggleBox = new JCheckBox(
            translator.realisticTranslate(Translation.BILDERBUCHSTABEN));
      pictureToggleBox.setSelected(Settings.isLetterImagesOn());

      infoStopTrainingPanel = new JPanel(new BorderLayout());
      infoStopTrainingPanel.setMinimumSize(new Dimension(150, 40));
      infoStopTrainingPanel.setMaximumSize(new Dimension(280, 40));
      infoStopTrainingPanel.setBackground(ApplicationColors.getGold());

      infoStopTrainingButton = new JButton(
            new ImageIcon(ApplicationImages.getInfoButtonIcon()));
      infoStopTrainingButton.setBackground(new Color(0, 0, 0, 0));
      infoStopTrainingButton.setMinimumSize(new Dimension(14, 26));
      infoStopTrainingButton.setMaximumSize(new Dimension(14, 32));
      infoStopTrainingButton.setMargin(new Insets(0, 0, 0, 0));

      infoStopTrainingPanel.add(infoStopTrainingButton, BorderLayout.WEST);

      stopTrainingButton = new JButton(
            translator.realisticTranslate(Translation.ABBRECHEN));
      stopTrainingButton.setIcon(new ImageIcon(ApplicationImages.getStop()));

      verticalLeftPanel.add(choices);
      verticalLeftPanel.add(numbers);
      verticalLeftPanel.add(numberFiller);
      verticalLeftPanel.add(nextWordButton);
      verticalLeftPanel.add(horizontal);
      verticalLeftPanel.add(soundslider);
      verticalLeftPanel.add(pictureToggleBox);
      verticalLeftPanel.add(infoStopTrainingPanel);
      verticalLeftPanel.add(stopTrainingButton);

      return verticalLeftPanel;
   }

   private void initQuestionPanel(Language languageDirection)
   {
      questionPanel.removeAll();
      questionPanel.setBackground(ApplicationColors.getTexturedBackgroundColor());

      JPanel additionalInfoPanel = new JPanel();
      additionalInfoPanel.setLayout(new TrainLayout(additionalInfoPanel, 15));
      additionalInfoPanel.setBackground(ApplicationColors.getTexturedBackgroundColor());

      additionalInfo = new JCheckBox();
      additionalInfo.setFont(ApplicationFonts.getGermanFont(15F));
      additionalInfo.setMinimumSize(
            new Dimension(Settings.getKeyboardWidth() / 2 - 8, 30));
      additionalInfo.setMaximumSize(
            new Dimension(Settings.getKeyboardWidth() / 2 - 7, 30));

      additionalInfoField = new JTextPane();
      additionalInfoField.setFont(ApplicationFonts.getGermanFont(15F));
      additionalInfoField.setBorder(
            new ComponentTitledBorder(additionalInfo, additionalInfoField,
                  new TitledBorder(translator
                        .realisticTranslate(Translation.WEITERE_INFORMATIONEN)),
                  35));
      additionalInfoField.setEditable(false);

      additionalInfo.addActionListener(event -> connector.setAdditionalInfo());

      JScrollPane scrollerAdditionalInfo = new JScrollPane(additionalInfoField);
      scrollerAdditionalInfo.setMinimumSize(
            new Dimension(Settings.getKeyboardWidth() / 2 - 8, 100));
      scrollerAdditionalInfo.setMaximumSize(
            new Dimension(Settings.getKeyboardWidth() / 2 - 7, 100));
      scrollerAdditionalInfo.setBorder(BorderFactory.createEmptyBorder());
      scrollerAdditionalInfo
            .setViewportBorder(BorderFactory.createEmptyBorder());
      scrollerAdditionalInfo.getViewport()
            .setBackground(ApplicationColors.getTransparent());
      scrollerAdditionalInfo.getViewport().setOpaque(false);

      grammarInfo = new JCheckBox();
      grammarInfo.setFont(ApplicationFonts.getGermanFont(15F));
      grammarInfo.setMinimumSize(
            new Dimension(Settings.getKeyboardWidth() / 2 - 8, 30));
      grammarInfo.setMaximumSize(
            new Dimension(Settings.getKeyboardWidth() / 2 - 7, 30));

      grammarInfoField = new JTextPane();
      grammarInfoField.setFont(ApplicationFonts.getGermanFont(15F));
      grammarInfoField
            .setBorder(new ComponentTitledBorder(grammarInfo, grammarInfoField,
                  new TitledBorder(
                        translator.realisticTranslate(Translation.GRAMMATIK)),
                  35));
      grammarInfoField.setEditable(false);

      grammarInfo.addActionListener(event -> connector.setGrammarInfo());

      JScrollPane scrollerGrammarInfo = new JScrollPane(grammarInfoField);
      scrollerGrammarInfo.setMinimumSize(
            new Dimension(Settings.getKeyboardWidth() / 2 - 8, 100));
      scrollerGrammarInfo.setMaximumSize(
            new Dimension(Settings.getKeyboardWidth() / 2 - 7, 100));
      scrollerGrammarInfo.setBorder(BorderFactory.createEmptyBorder());
      scrollerGrammarInfo.setViewportBorder(BorderFactory.createEmptyBorder());
      scrollerGrammarInfo.getViewport()
            .setBackground(ApplicationColors.getTransparent());
      scrollerGrammarInfo.getViewport().setOpaque(false);

      additionalInfoPanel.add(scrollerGrammarInfo);
      additionalInfoPanel.add(scrollerAdditionalInfo);

      questionPanel.add(additionalInfoPanel);

      answerPanel = new JPanel();
      answerPanel.setLayout(new TotemLayout(answerPanel));
      answerPanel.setBackground(ApplicationColors.getTexturedBackgroundColor());

      questionPanel.add(answerPanel);

      if (Language.GERMAN_TO_HEBREW.equals(languageDirection))
      {
         answerPanel.removeAll();
         answerPanel
               .setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 308));
         answerPanel
               .setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 444));
         answerField = new InfoTextField(
               translator.realisticTranslate(Translation.ANTWORTFELD),
               translator.realisticTranslate(Translation.ANTWORTFELD) + ":",
               translator.realisticTranslate(
                     Translation.MIT_DER_HEBRAEISCHEN_TASTATUR),
               translator.realisticTranslate(
                     Translation.BITTE_DIE_ANTWORT_SCHREIBEN));
         answerField.setDocument(new NikudDocument(true));
         answerField
               .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

         keyboardNikud = new KeyboardHebrewAllLetters(answerField,
               new ArrayList<JTextComponent>(), 80, false);

         answerPanel.add(answerField);
         answerPanel.add(keyboardNikud);
      }
      else
      {
         answerPanel.removeAll();
         answerPanel
               .setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 80));
         answerPanel
               .setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 80));
         answerField = new InfoTextField(
               translator.realisticTranslate(Translation.ANTWORTFELD),
               translator.realisticTranslate(Translation.ANTWORTFELD) + ":",
               translator.realisticTranslate(
                     Translation.SIE_KOENNEN_HIER_DIE_LOESUNG_SCHREIBEN),
               translator.realisticTranslate(
                     Translation.ODER_EINfACH_LAUT_NENNEN_ODER_DENKEN));
         answerField.setDocument(new GermanDocument(true));
         answerField.setFont(ApplicationFonts.getGermanFont(20F));
         answerField
               .setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 80));
         answerField
               .setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 80));
         answerPanel.add(answerField);
      }

      this.setFocusCycleRoot(true);
      FocusTraversalPolicy focusPolicy = new OneFocusTraversalPolicy(
            answerField);
      this.setFocusTraversalPolicy(focusPolicy);
      focusPolicy.getDefaultComponent(null);
      answerField.requestFocusInWindow();

      questionPanel.validate();
      questionPanel.repaint();
      if (Language.GERMAN_TO_HEBREW.equals(languageDirection))
      {
         this.pictureToggleBox.setVisible(true);
      }
      else
      {
         this.pictureToggleBox.setVisible(false);
      }
   }

   private Component initRightPanel()
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));
      vertical.setOpaque(true);
      vertical.setBackground(ApplicationColors.getTexturedBackgroundColor());

      imageFieldGreen = new ImagePanelGreen();
      imageFieldGreen.setBorder(BorderFactory.createEmptyBorder());
      imageFieldGreen.setMinimumSize(new Dimension(501, 240));
      imageFieldGreen.setMaximumSize(new Dimension(501, 240));
      imageFieldGreen.setBorder(null);

      imageFieldBlue = new ImagePanelBlue();
      imageFieldBlue.setBorder(BorderFactory.createEmptyBorder());
      imageFieldBlue.setMinimumSize(new Dimension(501, 240));
      imageFieldBlue.setMaximumSize(new Dimension(501, 240));
      imageFieldBlue.setBorder(null);

      imageFieldStart = new ImagePanelStart();
      imageFieldStart.setBorder(BorderFactory.createEmptyBorder());
      imageFieldStart.setMinimumSize(new Dimension(501, 240));
      imageFieldStart.setMaximumSize(new Dimension(501, 240));
      imageFieldStart.setBorder(null);

      imageFieldError = new ImagePanelError();
      imageFieldError.setBorder(BorderFactory.createEmptyBorder());
      imageFieldError.setMinimumSize(new Dimension(501, 240));
      imageFieldError.setMaximumSize(new Dimension(501, 240));
      imageFieldError.setBorder(null);

      cardLayout = new CardLayout();
      swapPanel = new JPanel(cardLayout);
      swapPanel.setMinimumSize(new Dimension(501, 209));
      swapPanel.setMaximumSize(new Dimension(520, 209));
      swapPanel.setBorder(BorderFactory.createEmptyBorder());
      swapPanel.setBackground(ApplicationColors.getTexturedBackgroundColor());
      
      swapPanel.add("START", imageFieldStart);
      swapPanel.add("GREEN", imageFieldGreen);
      swapPanel.add("BLUE", imageFieldBlue);
      swapPanel.add("RED", imageFieldError);
      cardLayout.show(swapPanel, "START");

      if (Language.GERMAN_TO_HEBREW.equals(languageDirection))
      {
         sendButton = new JButton(
               translator.realisticTranslate(Translation.ANTWORT_ABSENDEN));
      }
      else
      {
         sendButton = new JButton(
               translator.realisticTranslate(Translation.ANTWORT_ANSCHAUEN));
      }
      sendButton.setFont(ApplicationFonts.getButtonFont());
      sendButton.setIcon(new ImageIcon(ApplicationImages.getSend()));
      sendButton.setMinimumSize(new Dimension(300, 40));
      sendButton.setMaximumSize(new Dimension(501, 40));
      sendButton.setEnabled(false);

      feedbackPanel = new JPanel();
      feedbackPanel.setLayout(new TotemLayout(feedbackPanel));
      feedbackPanel.setMinimumSize(new Dimension(501, 230));
      feedbackPanel.setMaximumSize(new Dimension(501, 230));
      feedbackPanel.setOpaque(true);
      feedbackPanel.setBackground(ApplicationColors.getTexturedBackgroundColor());

      vertical.add(swapPanel);
      vertical.add(sendButton);
      vertical.add(feedbackPanel);
      return vertical;
   }

   private void initController()
   {
      sendButton.addActionListener(event -> connector.send());

      nextWordButton.addActionListener(event -> nextWord());

      infoStopTrainingButton.addActionListener(
            event -> JOptionPane.showMessageDialog(infoStopTrainingPanel, "",
                  Settings.getWindowTitle(), JOptionPane.INFORMATION_MESSAGE,
                  new ImageIcon(TextImage.make(
                        translator.realisticTranslate(
                              Translation.WENN_SIE_AUF_ABBRECHEN_DRUECKEN_),
                        translator.realisticTranslate(
                              Translation.WERDEN_ALLE_GELERNTEN_ANTWORTEN),
                        translator
                              .realisticTranslate(Translation.GESPEICHERT_)))));

      infoStopTrainingButton.addMouseListener(new MouseListener()
      {

         @Override
         public void mouseClicked(MouseEvent e)
         {

         }

         @Override
         public void mousePressed(MouseEvent e)
         {

         }

         @Override
         public void mouseReleased(MouseEvent e)
         {

         }

         @Override
         public void mouseEntered(MouseEvent e)
         {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         }

         @Override
         public void mouseExited(MouseEvent e)
         {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
         }

      });

      stopTrainingButton.addActionListener(event -> {
         connector.stopTraining(false);
      });

      this.soundButton.addActionListener(event -> connector.toggleSound());

      soundslider.addChangeListener(event -> {
         if (!soundslider.getValueIsAdjusting())
         {
            Settings.setVolume(soundslider.getValue());
         }
      });

      pictureToggleBox
            .addActionListener(event -> connector.toggleLetterPictures());
   }

   public void setHtoDanswerButtons()
   {
      answerOkay = new JButton(
            new ImageIcon(ApplicationImages.getAnswerOkay()));
      answerOkay.addActionListener(event2 -> {
         connector.resultHtoDOkay();
      });
      answerOkay.setMinimumSize(new Dimension(150, 110));
      answerOkay.setMaximumSize(new Dimension(150, 110));
      answerOkay.setPreferredSize(new Dimension(150, 110));
      answerOkay.setSize(150, 110);

      answerUndecided = new JButton(
            new ImageIcon(ApplicationImages.getAnswerUndecided()));
      answerUndecided.addActionListener(event2 -> {
         connector.resultHtoDUndecided();
      });
      answerUndecided.setMinimumSize(new Dimension(150, 110));
      answerUndecided.setMaximumSize(new Dimension(150, 110));
      answerUndecided.setPreferredSize(new Dimension(150, 110));
      answerUndecided.setSize(150, 110);

      answerNotOkay = new JButton(
            new ImageIcon(ApplicationImages.getAnswerNotOkay()));
      answerNotOkay.addActionListener(event2 -> {
         connector.resultHtoDFalse();
      });
      answerNotOkay.setMinimumSize(new Dimension(150, 110));
      answerNotOkay.setMaximumSize(new Dimension(150, 110));
      answerNotOkay.setPreferredSize(new Dimension(150, 110));
      answerNotOkay.setSize(150, 110);
   }

   public void enableHtoDAnswerButtons(boolean b)
   {
      this.answerOkay.setEnabled(b);
      this.answerUndecided.setEnabled(b);
      this.answerNotOkay.setEnabled(b);
   }

   public void prepareHtoDFeedbackPanel()
   {
      JPanel answerPanel1 = new JPanel();
      answerPanel1.setLayout(new TotemLayout(answerPanel1));
      answerPanel1.setBackground(ApplicationColors.getTexturedBackgroundColor());
      JLabel correctAnswer = new JLabel(translator
            .realisticTranslate(Translation.DIE_RICHTIGE_ANTWORT_LAUTET_));
      correctAnswer.setFont(ApplicationFonts.getGermanFont(16F));
      correctAnswer.setMinimumSize(new Dimension(490, 30));
      correctAnswer.setMaximumSize(new Dimension(510, 30));
      JLabel correctAnswer2 = new JLabel(
            connector.getCurrentExpression().getGerman());
      correctAnswer2.setFont(ApplicationFonts.getGermanFont(20F));
      correctAnswer2.setMinimumSize(new Dimension(490, 30));
      correctAnswer2.setMaximumSize(new Dimension(510, 30));
      JTextField correctAnswer3 = new JTextField(
            connector.getCurrentExpression().getGrammarInfo(false));
      correctAnswer3.setFont(ApplicationFonts.getGermanFont(16F));
      correctAnswer3.setEditable(false);
      correctAnswer3.setBackground(ApplicationColors.getTransparent());
      correctAnswer3.setOpaque(false);
      correctAnswer3.setBorder(BorderFactory.createEmptyBorder());
      JScrollPane scroller = new JScrollPane(correctAnswer3);
      scroller.setMinimumSize(new Dimension(490, 40));
      scroller.setMaximumSize(new Dimension(510, 40));
      scroller.setBorder(BorderFactory.createEmptyBorder());
      scroller.getViewport().setBackground(ApplicationColors.getTexturedBackgroundColor());
      scroller.setBackground(ApplicationColors.getTexturedBackgroundColor());

      answerPanel1.add(correctAnswer);
      answerPanel1.add(correctAnswer2);
      answerPanel1.add(scroller);

      JPanel answerPanel2 = new JPanel();
      answerPanel2.setLayout(new GridLayout(1, 3));
      answerPanel2.setMinimumSize(new Dimension(501, 100));
      answerPanel2.setMaximumSize(new Dimension(501, 100));
      answerPanel2.setBackground(ApplicationColors.getTexturedBackgroundColor());

      feedbackPanel.add(answerPanel1);
      feedbackPanel.add(answerPanel2);

      setHtoDanswerButtons();
      answerPanel2.add(answerOkay);
      answerPanel2.add(answerUndecided);
      answerPanel2.add(answerNotOkay);

      enableHtoDAnswerButtons(true);
   }

   public void nextWord()
   {
      nextWordButton.setEnabled(false);
      cardLayout.show(swapPanel, "START");
      answerField.setText("");
      feedbackPanel.removeAll();
      feedbackPanel.validate();
      feedbackPanel.repaint();
      additionalInfo.setSelected(false);
      additionalInfoField.setText("");
      additionalInfoField.validate();
      additionalInfoField.repaint();
      grammarInfo.setSelected(false);
      grammarInfoField.setText("");
      grammarInfoField.validate();
      grammarInfoField.repaint();
      pictureWordPanelPlene.removeAll();
      pictureWordPanelDefektiv.removeAll();

      connector.setNextTest();
      answerField.grabFocus();
      answerField.requestFocusInWindow();
   }

   public JTextPane getAdditionalInfoField()
   {
      return additionalInfoField;
   }

   public JButton getAnswerOkay()
   {
      return answerOkay;
   }

   public JButton getAnswerUndecided()
   {
      return answerUndecided;
   }

   public JButton getAnswerNotOkay()
   {
      return answerNotOkay;
   }

   public JLabel getWordsToDo()
   {
      return wordsToDo;
   }

   public JTextField getQuestionFieldGerman()
   {
      return questionFieldGerman;
   }

   public InputHebrewPanel getQuestionFieldHebrew()
   {
      return questionFieldHebrew;
   }

   public LetterPictureWordPanel getWordPanelPlene()
   {
      return pictureWordPanelPlene;
   }

   public LetterPictureWordPanel getWordPanelDefektiv()
   {
      return pictureWordPanelDefektiv;
   }

   public InfoTextField getAnswerField()
   {
      return answerField;
   }

   public JPanel getFeedbackPanel()
   {
      return feedbackPanel;
   }

   public JLabel getWordsRight()
   {
      return wordsRight;
   }

   public void disableSendButton()
   {
      sendButton.setEnabled(false);
   }

   public void enableSendButton()
   {
      sendButton.setEnabled(true);
   }

   public CardLayout getCardLayout()
   {
      return cardLayout;
   }

   public JButton getNextWordButton()
   {
      return nextWordButton;
   }

   public JButton getSoundButton()
   {
      return soundButton;
   }

   public JCheckBox getPictureToggleBox()
   {
      return pictureToggleBox;
   }

   public JTextPane getGrammarInfoField()
   {
      return grammarInfoField;
   }

   public void showResultBlue()
   {
      ImagePanelBlue.setNextImage();
      cardLayout.show(swapPanel, "BLUE");
   }

   public void showResultGreen()
   {
      ImagePanelGreen.setNextImage();
      cardLayout.show(swapPanel, "GREEN");
      wordsRightNumber++;
      wordsRight.setText(String.valueOf(wordsRightNumber));
      wordsToDo.setText(
            String.valueOf(connector.getExpressionsToBeTested().size()));
   }

   public void showResultRed()
   {
      cardLayout.show(swapPanel, "RED");
      wordsWrongNumber++;
      wordsWrong.setText(String.valueOf(wordsWrongNumber));
      wordsToDo.setText(
            String.valueOf(connector.getExpressionsToBeTested().size()));
   }

   public void prepareDtoNikudFeedbackPanel(Result result)
   {
      HebrewAnswerWordPanel answerPanel = new HebrewAnswerWordPanel(result);
      
      JScrollPane scrollPane = new JScrollPane(answerPanel);
      scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      scrollPane.setBorder(BorderFactory.createEmptyBorder());
      scrollPane.setMinimumSize(new Dimension(501, 120));
      scrollPane.setMaximumSize(new Dimension(501, 120));
      scrollPane.setOpaque(true);
      scrollPane.getViewport().setBackground(ApplicationColors.getTexturedBackgroundColor());

      JPanel fillerAnswerPanel = new JPanel();
      TotemLayout fillerAnswerLayout = new TotemLayout(fillerAnswerPanel);
      fillerAnswerPanel.setLayout(fillerAnswerLayout);
      fillerAnswerPanel.setMinimumSize(new Dimension(501, 1));
      fillerAnswerPanel.setMaximumSize(new Dimension(501, 100));
      fillerAnswerPanel.setOpaque(true);
      fillerAnswerPanel.setBackground(ApplicationColors.getTexturedBackgroundColor());

      feedbackPanel.add(scrollPane);
      feedbackPanel.add(fillerAnswerPanel);
      if (result.getExpression().getHebrew().isSimpleHebrew())
      {
         pictureWordPanelPlene.displayNikudWord(
               result.getExpression().getHebrew().getHebrew());
      }
      else
      {
         pictureWordPanelPlene.displayNikudWord(
               result.getExpression().getHebrew().getHebrewPlene());
         pictureWordPanelDefektiv.displayNikudWord(
               result.getExpression().getHebrew().getHebrewDefektiv());
      }
   }
}
