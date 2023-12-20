package vokabeltrainer.panels.letterpicture;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.text.JTextComponent;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.ApplicationSpecialPanels;
import vokabeltrainer.common.Common;
import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.editing.SingleLetterDocument;
import vokabeltrainer.keyboards.KeyboardHebrewStandard;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.table.list.editor.CerebrummiFocusTraversalPolicy;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class LetterPictureAlphabetPanel extends JPanel
{
   private static final String SHUFFLE = "SHUFFLE";

   private static final String REGULAR = "REGULAR";

   private static final String SCHREIBSCHRIFT = "SCHREIBSCHRIFT";

   private static final String DRUCKSCHRIFT = "DRUCKSCHRIFT";

   private static final long serialVersionUID = 2284393162989380186L;

   private final NikudLetter[] keys1 = { NikudLetter.CHET, NikudLetter.SSAIN,
         NikudLetter.WAW, NikudLetter.HAEI, NikudLetter.DALET,
         NikudLetter.GIMEL, NikudLetter.BET, NikudLetter.ALEF };

   private final NikudLetter[] keys2 = { NikudLetter.NUN, NikudLetter.MEMSSOFIT,
         NikudLetter.MEM, NikudLetter.LAMED, NikudLetter.CHAFSSOFIT,
         NikudLetter.KAF, NikudLetter.JOD, NikudLetter.TET };

   private final NikudLetter[] keys3 = { NikudLetter.KUF,
         NikudLetter.ZADISSOFIT, NikudLetter.ZADI, NikudLetter.FAEISSOFIT,
         NikudLetter.PAEI, NikudLetter.AIN, NikudLetter.SSAMECH,
         NikudLetter.NUNSSOFIT };

   private final NikudLetter[] keys4 = { NikudLetter.TAW, NikudLetter.SCHIN,
         NikudLetter.RESCH };

   private Translator translator = Common.getTranslator();
   
   private LetterTextField alef = new LetterTextField(NikudLetter.ALEF);
   private LetterTextField wet = new LetterTextField(NikudLetter.BET);
   private LetterTextField gimel = new LetterTextField(NikudLetter.GIMEL);
   private LetterTextField dalet = new LetterTextField(NikudLetter.DALET);
   private LetterTextField haei = new LetterTextField(NikudLetter.HAEI);
   private LetterTextField waw = new LetterTextField(NikudLetter.WAW);
   private LetterTextField ssain = new LetterTextField(NikudLetter.SSAIN);
   private LetterTextField chet = new LetterTextField(NikudLetter.CHET);
   private LetterTextField tet = new LetterTextField(NikudLetter.TET);
   private LetterTextField jod = new LetterTextField(NikudLetter.JOD);
   private LetterTextField chaf = new LetterTextField(NikudLetter.KAF);
   private LetterTextField chafssofit = new LetterTextField(
         NikudLetter.CHAFSSOFIT);
   private LetterTextField lamed = new LetterTextField(NikudLetter.LAMED);
   private LetterTextField mem = new LetterTextField(NikudLetter.MEM);
   private LetterTextField memssofit = new LetterTextField(
         NikudLetter.MEMSSOFIT);
   private LetterTextField nun = new LetterTextField(NikudLetter.NUN);
   private LetterTextField nunssofit = new LetterTextField(
         NikudLetter.NUNSSOFIT);
   private LetterTextField ssamech = new LetterTextField(NikudLetter.SSAMECH);
   private LetterTextField ain = new LetterTextField(NikudLetter.AIN);
   private LetterTextField faei = new LetterTextField(NikudLetter.PAEI);
   private LetterTextField faeissofit = new LetterTextField(
         NikudLetter.FAEISSOFIT);
   private LetterTextField zadi = new LetterTextField(NikudLetter.ZADI);
   private LetterTextField zadissofit = new LetterTextField(
         NikudLetter.ZADISSOFIT);
   private LetterTextField kuf = new LetterTextField(NikudLetter.KUF);
   private LetterTextField resch = new LetterTextField(NikudLetter.RESCH);
   private LetterTextField schin = new LetterTextField(NikudLetter.SCHIN);
   private LetterTextField taw = new LetterTextField(NikudLetter.TAW);

   private LetterTextField[] textFields1 = { chet, ssain, waw, haei, dalet,
         gimel, wet, alef };
   private LetterTextField[] textFields2 = { nun, memssofit, mem, lamed,
         chafssofit, chaf, jod, tet };
   private LetterTextField[] textFields3 = { kuf, zadissofit, zadi, faeissofit,
         faei, ain, ssamech, nunssofit };
   private LetterTextField[] textFields4 = { taw, schin, resch };

   private List<JTextComponent> textFields;

   Component[] focusList = { alef, wet, gimel, dalet, haei, waw, ssain, chet,
         tet, jod, chaf, chafssofit, lamed, mem, memssofit, nun, nunssofit,
         ssamech, ain, faei, faeissofit, zadi, zadissofit, kuf, resch, schin,
         taw };

   JRadioButton printLettersButton = new JRadioButton(translator.realisticTranslate(Translation.DRUCKSCHRIFT));
   JRadioButton handwrittenLettersButton = new JRadioButton(translator.realisticTranslate(Translation.SCHREIBSCHRIFT));
   ButtonGroup switchButtonGroup = new ButtonGroup();
   
   JRadioButton keyboardRegularButton = new JRadioButton(translator.realisticTranslate(Translation.TASTATUR_REGULAER));
   JRadioButton keyboardShuffleButton = new JRadioButton(translator.realisticTranslate(Translation.TASTATUR_VERMISCHT));
   ButtonGroup keyboardButtonGroup = new ButtonGroup();

   FocusTraversalPolicy focusTraversalPolicy;

   private JPanel keyboardPanel;

   private CardLayout cardLayout;

   private KeyboardHebrewStandard keyboardPrint;

   private KeyboardHebrewStandard keyboardHandwritten;

   public LetterPictureAlphabetPanel()
   {      
      keyboardRegularButton.setForeground(ApplicationColors.getWhite());
      keyboardShuffleButton.setForeground(ApplicationColors.getWhite());
      textFields = new ArrayList<>();
      Map<NikudLetter, LetterPictureButtonPanel> panels = ApplicationSpecialPanels
            .getLetterPicturesPanelMap();
      this.setOpaque(true);
      setBackground(ApplicationColors.getTexturedBackgroundColor());
      this.setLayout(new TotemLayout(this, 15));
      this.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 15));

      JPanel row1 = new JPanel();
      row1.setLayout(new TrainLayout(row1, 15));
      row1.setOpaque(false);

      for (int i = 0; i < keys1.length; i++)
      {
         JPanel column = new JPanel();
         column.setLayout(new TotemLayout(column));
         column.setBackground(ApplicationColors.getTexturedBackgroundColor());
         column.add(panels.get(keys1[i]));
         column.add(textFields1[i]);
         textFields.add(textFields1[i]);
         row1.add(column);
      }
      this.add(row1);

      JPanel row2 = new JPanel();
      row2.setLayout(new TrainLayout(row2, 15));
      row2.setOpaque(false);

      for (int i = 0; i < keys2.length; i++)
      {
         JPanel column = new JPanel();
         column.setLayout(new TotemLayout(column));
         column.setBackground(ApplicationColors.getTexturedBackgroundColor());
         column.add(panels.get(keys2[i]));
         column.add(textFields2[i]);
         textFields.add(textFields2[i]);
         row2.add(column);
      }
      this.add(row2);

      JPanel row3 = new JPanel();
      row3.setLayout(new TrainLayout(row3, 15));
      row3.setOpaque(false);

      for (int i = 0; i < keys3.length; i++)
      {
         JPanel column = new JPanel();
         column.setLayout(new TotemLayout(column));
         column.setBackground(ApplicationColors.getTexturedBackgroundColor());
         column.add(panels.get(keys3[i]));
         column.add(textFields3[i]);
         textFields.add(textFields3[i]);
         row3.add(column);
      }
      this.add(row3);

      JPanel row4 = new JPanel();
      row4.setLayout(new TrainLayout(row4, 15));
      row4.setOpaque(false);

      JPanel filler = new JPanel(new FlowLayout());
      filler.setOpaque(false);

      filler.setMinimumSize(new Dimension(310, 50));
      filler.setMaximumSize(new Dimension(310, 50));

      JButton turnButton = new JButton(translator.realisticTranslate(Translation.ALLE_UMDREHEN));
      turnButton.setFont(ApplicationFonts.getButtonFont());
      turnButton.setIcon(new ImageIcon(ApplicationImages.getTurn()));
      turnButton.addActionListener(event -> {
         for (LetterPictureButtonPanel panel : panels.values())
         {
            panel.nextCard();
         }
      });

      filler.add(turnButton);

      row4.add(filler);

      for (int i = 0; i < keys4.length; i++)
      {
         JPanel column = new JPanel();
         column.setLayout(new TotemLayout(column));
         column.setBackground(ApplicationColors.getTexturedBackgroundColor());
         column.add(panels.get(keys4[i]));
         column.add(textFields4[i]);
         textFields.add(textFields4[i]);
         row4.add(column);
      }
      this.add(row4);

      for (JTextComponent jtc : textFields)
      {
         jtc.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 14));
         jtc.setMinimumSize(new Dimension(50, 40));
         jtc.setMaximumSize(new Dimension(50, 40));
         jtc.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         jtc.setDocument(new SingleLetterDocument());
      }

      JPanel keyboardPrintPanel = new JPanel(new BorderLayout());
      keyboardPrintPanel.setOpaque(true);
      keyboardPrintPanel.setBackground(ApplicationColors.getTexturedBackgroundColor());
      keyboardPrint = new KeyboardHebrewStandard(null,
            textFields, 15, false);
      keyboardPrintPanel.add(keyboardPrint, BorderLayout.CENTER);

      JPanel keyboardHandwrittenPanel = new JPanel(new BorderLayout());
      keyboardHandwrittenPanel.setOpaque(true);
      keyboardHandwrittenPanel.setBackground(ApplicationColors.getTexturedBackgroundColor());
      keyboardHandwritten = new KeyboardHebrewStandard(
            null, textFields, 15, true);
      keyboardHandwrittenPanel.add(keyboardHandwritten, BorderLayout.CENTER);

      cardLayout = new CardLayout();
      keyboardPanel = new JPanel(cardLayout);
      keyboardPanel.add(DRUCKSCHRIFT, keyboardPrintPanel);
      keyboardPanel.add(SCHREIBSCHRIFT, keyboardHandwrittenPanel);
      keyboardPanel.setMinimumSize(new Dimension(501, 150));
      keyboardPanel.setMaximumSize(new Dimension(530, 200));
      keyboardPanel.setBackground(ApplicationColors.getTexturedBackgroundColor());

      this.add(keyboardPanel);

      printLettersButton.addActionListener(event -> setWriting(DRUCKSCHRIFT));
      handwrittenLettersButton.addActionListener(event -> setWriting(SCHREIBSCHRIFT));
      switchButtonGroup.add(printLettersButton);
      switchButtonGroup.add(handwrittenLettersButton);
      
      keyboardRegularButton.addActionListener(event -> setKeyboard(REGULAR));
      keyboardShuffleButton.addActionListener(event -> setKeyboard(SHUFFLE));
      keyboardButtonGroup.add(keyboardRegularButton);
      keyboardButtonGroup.add(keyboardShuffleButton);
      
      JPanel buttonPanel = new JPanel();
      TrainLayout buttonPanelLayout = new TrainLayout(buttonPanel, 30);
      buttonPanel.setOpaque(true);
      buttonPanel.setBackground(ApplicationColors.getTexturedBackgroundColor());
      buttonPanel.setLayout(buttonPanelLayout);
      
      JPanel letterSwitchPanel = new JPanel();
      TotemLayout letterSwitchPanelLayout = new TotemLayout(letterSwitchPanel, 15);
      letterSwitchPanel.setOpaque(true);
      letterSwitchPanel.setBackground(ApplicationColors.getMediumBlue());
      letterSwitchPanel.setBorder(BorderFactory.createLineBorder(ApplicationColors.getMediumBlue(), 5));
      letterSwitchPanel.setLayout(letterSwitchPanelLayout);
      
      JPanel keyboardShufflePanel = new JPanel();
      TotemLayout keyboardShufflePanelLayout = new TotemLayout(keyboardShufflePanel, 15);
      keyboardShufflePanel.setOpaque(true);
      keyboardShufflePanel.setBackground(ApplicationColors.getShadyBlue());
      keyboardShufflePanel.setBorder(BorderFactory.createLineBorder(ApplicationColors.getShadyBlue(), 5));
      keyboardShufflePanel.setLayout(keyboardShufflePanelLayout);
      
      letterSwitchPanel.add(printLettersButton);
      letterSwitchPanel.add(handwrittenLettersButton);
      printLettersButton.setSelected(true);
      
      keyboardShufflePanel.add(keyboardRegularButton);
      keyboardShufflePanel.add(keyboardShuffleButton);
      keyboardRegularButton.setSelected(true);
      
      buttonPanel.add(letterSwitchPanel);
      buttonPanel.add(keyboardShufflePanel);
      buttonPanel.add(new BackgroundPanelTiled() );
      
      this.add(buttonPanel);
      

      setWriting(switchButtonGroup.getSelection().getActionCommand());
      

      this.focusTraversalPolicy = new CerebrummiFocusTraversalPolicy(focusList);
   }

   private void setKeyboard(String actionCommand)
   {
      if(REGULAR == actionCommand)
      {
         keyboardPrint.makeRegularKeyboard();
         keyboardHandwritten.makeRegularKeyboard();
      }
      else
      {
         keyboardPrint.shuffleKeyboard();
         keyboardHandwritten.shuffleKeyboard();
      }
   }

   private void setWriting(String actionCommand)
   {
      if (SCHREIBSCHRIFT == actionCommand)
      {
         for (JTextComponent jtc : textFields)
         {
            jtc.setFont(ApplicationFonts.getHebrewHandwrittenFont(30));
         }
         cardLayout.show(keyboardPanel, SCHREIBSCHRIFT);
      }
      else
      {
         for (JTextComponent jtc : textFields)
         {
            jtc.setFont(ApplicationFonts.getHebrewFont(30));
         }
         cardLayout.show(keyboardPanel, DRUCKSCHRIFT);
      }  
   }

   public List<JTextComponent> getTextFields()
   {
      return textFields;
   }

   @Override
   public FocusTraversalPolicy getFocusTraversalPolicy()
   {
      return focusTraversalPolicy;
   }
}
