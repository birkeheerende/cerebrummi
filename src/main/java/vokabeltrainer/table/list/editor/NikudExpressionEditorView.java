package vokabeltrainer.table.list.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import vokabeltrainer.InfoTextField;
import vokabeltrainer.InputHebrewPanel;
import vokabeltrainer.InputHebrewPanel.Selection;
import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.ImageData;
import vokabeltrainer.common.LetterForSaving;
import vokabeltrainer.common.Settings;
import vokabeltrainer.editing.ExtraInformationDocument;
import vokabeltrainer.editing.GermanDocument;
import vokabeltrainer.editing.NikudDocument;
import vokabeltrainer.keyboards.KeyboardHebrewNikud;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.table.list.editor.expressionkindtable.multiselect.ExpressionKindTableMultiselect;
import vokabeltrainer.table.list.editor.expressionkindtable.multiselect.ExpressionKindTableRow;
import vokabeltrainer.tonionlayout.ExpanderLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Chapter.Database;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Hebrew;
import vokabeltrainer.types.SortingIndex;
import vokabeltrainer.types.grammatical.Binjan;
import vokabeltrainer.types.grammatical.Gender;
import vokabeltrainer.types.grammatical.GrammaticalEnum.GrammaticalParentEnum;
import vokabeltrainer.types.grammatical.GrammaticalPerson;
import vokabeltrainer.types.grammatical.Numerus;
import vokabeltrainer.types.grammatical.VerbTimes;
import vokabeltrainer.types.grammatical.expressionkind.Definitions;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKindItem;

public class NikudExpressionEditorView extends JDialog
      implements ExpressionEditorViewConnector
{
   private static final int WIDTH_INFO_PANEL = 240;

   private static final int WIDTH_INPUT_PANEL = Settings.getKeyboardWidth();

   private static final long serialVersionUID = 5853498340870217732L;

   private Translator translator = Common.getTranslator();
   private Expression expression;
   private boolean newExpression;
   private JTextField german;
   private InputHebrewPanel hebrew;

   private JTextField indexField;

   private InfoTextField newSearchwordGerman;
   private JList<String> searchwordsJListGerman;
   private Set<String> searchwordsSetGerman;
   private JButton deleteSearchwordButtonGerman;

   private InfoTextField newSearchwordHebrew;
   private JList<String> searchwordsJListHebrew;
   private Set<String> searchwordsSetHebrew;
   private JButton deleteSearchwordButtonHebrew;

   private KeyboardHebrewNikud keyboard;
   private JPanel outerLayout;
   private JPanel layout;
   private boolean save;
   private JButton saveButton;
   private JButton restoreButton;
   private JButton cancelButton;
   private List<JTextComponent> components = new ArrayList<>();
   private String germanTitle = translator
         .realisticTranslate(Translation.DEUTSCH);
   private String searchwordJListGermanTitle = translator
         .realisticTranslate(Translation.DEUTSCHE_SUCHWOERTER);
   private String searchwordsJListHebrewTitle = translator
         .realisticTranslate(Translation.HEBRAEISCHE_SUCHWOERTER);
   private String chapterTitle = translator
         .realisticTranslate(Translation.LEKTION);
   private JComboBox<String> chapter;
   private JTextPane extraInfo;
   private JScrollPane extraInfoScroller;
   private JButton pasteButton;
   private JButton cutButton;
   private JButton copyButton;

   private ExpressionKindTableMultiselect expressionKindTable;
   private boolean frozen;

   private NikudExpressionEditorControllerConnector connector;

   private JPanel binjanBoxPanel;

   private JPanel genderBoxPanel;

   private JPanel grammaticalPersonBoxPanel;

   private JPanel numerusBoxPanel;

   private JPanel verbTimesBoxPanel;

   private JComboBox<Binjan> binjanBox;

   private JComboBox<Gender> genderBox;

   private JComboBox<GrammaticalPerson> grammaticalPersonBox;

   private JComboBox<Numerus> numerusBox;

   private JComboBox<VerbTimes> verbTimesBox;

   private JPanel definitionPanel;

   private JScrollPane scrollPaneExpressionTable;

   private JComboBox<String> databaseNameField;

   private JLabel lastModiefiedLabel;

   private ImageButton imageButton;

   private JButton loadImageButton;

   private JButton removeImageButton;

   public ImageButton getImageButton()
   {
      return imageButton;
   }

   public NikudExpressionEditorView(
         NikudExpressionEditorControllerConnector connector)
   {
      super(Common.getjFrame(), Settings.getWindowTitle(),
            Dialog.ModalityType.APPLICATION_MODAL);
      this.connector = connector;
      save = false;
      setResizable(true);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      setSize(Math.min(screenSize.width - 60, 1310),
            Math.min(screenSize.height - 60, 815));

      outerLayout = new JPanel();
      outerLayout.setBackground(ApplicationColors.getTexturedBackgroundColor());
      outerLayout.setBorder(BorderFactory
            .createLineBorder(ApplicationColors.getGreen(), 15, false));
      outerLayout.setLayout(new TotemLayout(outerLayout, 15));

      layout = new JPanel();
      layout.setOpaque(true);
      layout.setBackground(ApplicationColors.getTexturedBackgroundColor());
      layout.setLayout(new TrainLayout(layout, 15));

      initGuiFields();
      layout.add(initInput());
      layout.add(initInfosLeft());
      layout.add(initInfosRight());
      layout.add(initInfosExtra());

      outerLayout.add(initTopPanel());
      outerLayout.add(layout);

      getContentPane().add(new JScrollPane(outerLayout));

      initController();
      Component[] focusList = { german, hebrew, newSearchwordGerman,
            newSearchwordHebrew, extraInfo };
      this.setFocusTraversalPolicy(
            new CerebrummiFocusTraversalPolicy(focusList));
   }

   private void initGuiFields()
   {
      Font germanfont = ApplicationFonts.getGermanFont(16F);
      Font hebrewfont = ApplicationFonts.getHebrewFont(30F);

      german = new JTextField();
      german.setBorder(makeBorderBlank(germanTitle));
      german.setFont(germanfont);
      german.setMinimumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      german.setMaximumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      german.setDocument(new GermanDocument(true));

      if (Settings.isSimpleHebrewInput())
      {
         hebrew = new InputHebrewPanel(Selection.SIMPLE, 152, 6, true, this,
               WIDTH_INPUT_PANEL, ApplicationColors.getLightYellow());
      }
      else
      {
         hebrew = new InputHebrewPanel(Selection.PLENE_DEFEKTIV, 152, 6, true,
               this, WIDTH_INPUT_PANEL, ApplicationColors.getLightYellow());
      }
      hebrew.setBlankBorder();

      newSearchwordGerman = new InfoTextField(
            translator.realisticTranslate(Translation.NEUES_SUCHWORT_DEUTSCH)
                  + "  ",
            translator.realisticTranslate(
                  Translation.BITTE_JE_EIN_WORT_EINGEBEN) + "  ",
            translator.realisticTranslate(Translation.UND_DANN_ENTER_DRUECKEN_)
                  + "  ");
      newSearchwordGerman.setFont(germanfont);
      newSearchwordGerman.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 70));
      newSearchwordGerman.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 70));
      newSearchwordGerman.setDocument(new GermanDocument(false));

      searchwordsJListGerman = new JList<>();
      searchwordsJListGerman.setCellRenderer(new ListCellRenderer<String>()
      {
         @Override
         public Component getListCellRendererComponent(
               JList<? extends String> list, String value, int index,
               boolean isSelected, boolean cellHasFocus)
         {
            AntiFocusTextField listComponent = new AntiFocusTextField(value);
            listComponent.setFont(ApplicationFonts.getGermanFont(16F));
            if (isSelected)
            {
               listComponent.setBackground(Color.WHITE);
            }
            else
            {
               listComponent
                     .setBackground(ApplicationColors.getBackgroundGold());
            }
            return listComponent;
         }
      });
      searchwordsJListGerman.setFocusable(false);
      searchwordsJListGerman
            .setBorder(makeBorderBlank(this.searchwordJListGermanTitle));
      searchwordsJListGerman.setBackground(new Color(223, 210, 198));
      searchwordsJListGerman
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 300));
      searchwordsJListGerman
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));
      JPopupMenu popupGerman = new JPopupMenu();
      JMenuItem copyMenuGerman = new JMenuItem(
            translator.realisticTranslate(Translation.KOPIEREN));
      copyMenuGerman.addActionListener(event -> {
         StringSelection stringSelection = new StringSelection(
               searchwordsJListGerman.getSelectedValue());
         Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
         clipboard.setContents(stringSelection, null);
      });
      popupGerman.add(copyMenuGerman);
      searchwordsJListGerman.setComponentPopupMenu(popupGerman);

      newSearchwordHebrew = new InfoTextField(
            translator.realisticTranslate(Translation.NEUES_SUCHWORT_HEBRAEISCH)
                  + "  ",
            translator.realisticTranslate(Translation.BITTE_HINEINKLICKEN_),
            translator.realisticTranslate(
                  Translation.HEBRAEISCHE_TASTATUR_BENUTZEN),
            translator.realisticTranslate(Translation.DANACH_ENTER_DRUECKEN_)
                  + "  ");
      newSearchwordHebrew.setFont(hebrewfont);
      newSearchwordHebrew
            .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      newSearchwordHebrew.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 70));
      newSearchwordHebrew.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 70));
      newSearchwordHebrew.setDocument(new NikudDocument(false));
      this.components.add(newSearchwordHebrew);

      searchwordsJListHebrew = new JList<>();
      searchwordsJListHebrew.setCellRenderer(new ListCellRenderer<String>()
      {
         @Override
         public Component getListCellRendererComponent(
               JList<? extends String> list, String value, int index,
               boolean isSelected, boolean cellHasFocus)
         {
            AntiFocusTextField listComponent = new AntiFocusTextField(value);
            listComponent.setFont(hebrewfont);
            listComponent
                  .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            if (isSelected)
            {
               listComponent.setBackground(Color.WHITE);
            }
            else
            {
               listComponent
                     .setBackground(ApplicationColors.getBackgroundGold());
            }
            return listComponent;
         }
      });
      searchwordsJListHebrew.setFocusable(false);
      searchwordsJListHebrew.setFixedCellHeight(50);
      searchwordsJListHebrew
            .setBorder(makeBorderBlank(this.searchwordsJListHebrewTitle));
      searchwordsJListHebrew
            .setBackground(ApplicationColors.getBackgroundGold());
      searchwordsJListHebrew
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 300));
      searchwordsJListHebrew
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));
      JPopupMenu popupHebrew = new JPopupMenu();
      JMenuItem copyMenuHebrew = new JMenuItem(
            translator.realisticTranslate(Translation.KOPIEREN));
      copyMenuHebrew.addActionListener(event -> {
         StringSelection stringSelection = new StringSelection(
               searchwordsJListHebrew.getSelectedValue());
         Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
         clipboard.setContents(stringSelection, null);
      });
      popupHebrew.add(copyMenuHebrew);
      searchwordsJListHebrew.setComponentPopupMenu(popupHebrew);

      deleteSearchwordButtonHebrew = new JButton(translator
            .realisticTranslate(Translation.LOESCHE_SUCHWORT_HEBRAEISCH));
      deleteSearchwordButtonHebrew.setFont(ApplicationFonts.getButtonFont());
      deleteSearchwordButtonHebrew
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 40));
      deleteSearchwordButtonHebrew
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 40));

      deleteSearchwordButtonGerman = new JButton(translator
            .realisticTranslate(Translation.LOESCHE_SUCHWORT_DEUTSCH));
      deleteSearchwordButtonGerman.setFocusable(false);
      deleteSearchwordButtonGerman.setFont(ApplicationFonts.getButtonFont());
      deleteSearchwordButtonGerman
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 40));
      deleteSearchwordButtonGerman
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 40));

      saveButton = new JButton(
            translator.realisticTranslate(Translation.SPEICHERN));
      saveButton.setFont(ApplicationFonts.getButtonFont());
      saveButton.setMinimumSize(new Dimension(120, 40));
      saveButton.setMaximumSize(new Dimension(160, 40));

      restoreButton = new JButton(
            translator.realisticTranslate(Translation.ZURUECKSETZEN));
      restoreButton.setFont(ApplicationFonts.getButtonFont());
      restoreButton.setMinimumSize(new Dimension(120, 40));
      restoreButton.setMaximumSize(new Dimension(160, 40));

      cancelButton = new JButton(
            translator.realisticTranslate(Translation.ABBRECHEN));
      cancelButton.setFont(ApplicationFonts.getButtonFont());
      cancelButton.setMinimumSize(new Dimension(120, 40));
      cancelButton.setMaximumSize(new Dimension(160, 40));

      chapter = new JComboBox<String>();
      chapter.setEditable(true);
      chapter.setMaximumRowCount(20);
      chapter.setBorder(new TitledBorder(this.chapterTitle));
      chapter.setOpaque(false);
      chapter.setBackground(new Color(0, 0, 0, 0));
      chapter.setMinimumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      chapter.setMaximumSize(new Dimension(WIDTH_INPUT_PANEL, 70));

      indexField = new JTextField();
      indexField.setBorder(
            makeBorderBlank(translator.realisticTranslate(Translation.INDEX)));
      indexField.setFont(germanfont);
      indexField.setOpaque(false);
      indexField.setBackground(ApplicationColors.getTransparent());
      indexField.setMinimumSize(new Dimension(85, 70));
      indexField.setMaximumSize(new Dimension(85, 70));

      databaseNameField = new JComboBox<>();
      databaseNameField.setFont(ApplicationFonts.getButtonFont());
      databaseNameField.setMinimumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      databaseNameField.setMaximumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      databaseNameField.setBorder(new TitledBorder(
            translator.realisticTranslate(Translation.DATENBANK)));
      databaseNameField.setEditable(true);
      databaseNameField.setMaximumRowCount(20);

      extraInfo = new JTextPane();
      extraInfo.setFont(ApplicationFonts.getHebrewFont(30));
      extraInfo.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(Translation.WEITERE_INFORMATIONEN)));
      extraInfo.setDocument(new ExtraInformationDocument());
      StyledDocument doc = extraInfo.getStyledDocument();
      SimpleAttributeSet style = new SimpleAttributeSet();
      StyleConstants.setForeground(style, ApplicationColors.getDarkGold());
      StyleConstants.setFontSize(style, 20);
      StyleConstants.setFontFamily(style, "Serif");
      doc.setParagraphAttributes(0, doc.getLength(), style, true);
      extraInfo.setFocusTraversalKeys(
            KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
      extraInfo.setFocusTraversalKeys(
            KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);

      components.add(extraInfo);
      extraInfoScroller = new JScrollPane(extraInfo);
      extraInfoScroller.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 100));
      extraInfoScroller.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 240));

      pasteButton = new JButton(new DefaultEditorKit.PasteAction());
      pasteButton.setIcon(new ImageIcon(ApplicationImages.getPaste()));
      pasteButton.setText("");
      pasteButton.setToolTipText(
            translator.realisticTranslate(Translation.EINFUEGEN));
      pasteButton
            .setMinimumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));
      pasteButton
            .setMaximumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));

      cutButton = new JButton(new DefaultEditorKit.CutAction());
      cutButton.setIcon(new ImageIcon(ApplicationImages.getCut()));
      cutButton.setText("");
      cutButton.setToolTipText(
            translator.realisticTranslate(Translation.AUSSCNEIDEN));
      cutButton.setMinimumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));
      cutButton.setMaximumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));

      copyButton = new JButton(new DefaultEditorKit.CopyAction());
      copyButton.setIcon(new ImageIcon(ApplicationImages.getCopy2()));
      copyButton.setText("");
      copyButton.setToolTipText(
            translator.realisticTranslate(Translation.KOPIEREN));
      copyButton.setMinimumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));
      copyButton.setMaximumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));

      expressionKindTable = new ExpressionKindTableMultiselect(
            ExpressionKind.getModelForMultiselect(), WIDTH_INFO_PANEL, this);

      binjanBox = new JComboBox<>(Binjan.values());
      binjanBox.setFont(ApplicationFonts.getComboBoxFont());
      binjanBox.setEditable(false);
      binjanBox.setMinimumSize(new Dimension(WIDTH_INFO_PANEL - 10, 50));
      binjanBox.setMaximumSize(new Dimension(WIDTH_INFO_PANEL - 10, 50));
      binjanBox.setMaximumRowCount(Binjan.values().length);
      binjanBoxPanel = new JPanel();
      TotemLayout binjanLayout = new TotemLayout(binjanBoxPanel);
      binjanBoxPanel.setLayout(binjanLayout);
      binjanBoxPanel.add(binjanBox);
      binjanBoxPanel.setOpaque(false);
      binjanBoxPanel.setBackground(ApplicationColors.getTransparent());
      binjanBoxPanel.setBorder(new TitledBorder(
            translator.realisticTranslate(Translation.BINJAN___STAMM)));

      genderBox = new JComboBox<>(Gender.values());
      genderBox.setFont(ApplicationFonts.getComboBoxFont());
      genderBox.setEditable(false);
      genderBox.setMinimumSize(new Dimension(WIDTH_INFO_PANEL - 10, 50));
      genderBox.setMaximumSize(new Dimension(WIDTH_INFO_PANEL - 10, 50));
      genderBox.setMaximumRowCount(Gender.values().length);
      genderBoxPanel = new JPanel();
      TotemLayout genderLayout = new TotemLayout(genderBoxPanel);
      genderBoxPanel.setLayout(genderLayout);
      genderBoxPanel.add(genderBox);
      genderBoxPanel.setOpaque(false);
      genderBoxPanel.setBackground(ApplicationColors.getTransparent());
      genderBoxPanel.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(Translation.GESCHLECHT)));

      grammaticalPersonBox = new JComboBox<>(GrammaticalPerson.values());
      grammaticalPersonBox.setFont(ApplicationFonts.getComboBoxFont());
      grammaticalPersonBox.setEditable(false);
      grammaticalPersonBox
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL - 10, 50));
      grammaticalPersonBox
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL - 10, 50));
      grammaticalPersonBox
            .setMaximumRowCount(GrammaticalPerson.values().length);
      grammaticalPersonBoxPanel = new JPanel();
      TotemLayout grammaticalPersonLayout = new TotemLayout(
            grammaticalPersonBoxPanel);
      grammaticalPersonBoxPanel.setLayout(grammaticalPersonLayout);
      grammaticalPersonBoxPanel.add(grammaticalPersonBox);
      grammaticalPersonBoxPanel.setOpaque(false);
      grammaticalPersonBoxPanel
            .setBackground(ApplicationColors.getTransparent());
      grammaticalPersonBoxPanel.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(Translation.GRAMMATISCHE_PERSON)));

      numerusBox = new JComboBox<>(Numerus.values());
      numerusBox.setFont(ApplicationFonts.getComboBoxFont());

      numerusBox.setEditable(false);
      numerusBox.setMinimumSize(new Dimension(WIDTH_INFO_PANEL - 10, 50));
      numerusBox.setMaximumSize(new Dimension(WIDTH_INFO_PANEL - 10, 50));
      numerusBox.setMaximumRowCount(Numerus.values().length);
      numerusBoxPanel = new JPanel();
      TotemLayout numerusLayout = new TotemLayout(numerusBoxPanel);
      numerusBoxPanel.setLayout(numerusLayout);
      numerusBoxPanel.add(numerusBox);
      numerusBoxPanel.setOpaque(false);
      numerusBoxPanel.setBackground(ApplicationColors.getTransparent());
      numerusBoxPanel.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(Translation.NUMERUS)));

      verbTimesBox = new JComboBox<>(VerbTimes.values());
      verbTimesBox.setFont(ApplicationFonts.getComboBoxFont());
      verbTimesBox.setEditable(false);
      verbTimesBox.setMinimumSize(new Dimension(WIDTH_INFO_PANEL - 10, 50));
      verbTimesBox.setMaximumSize(new Dimension(WIDTH_INFO_PANEL - 10, 50));
      verbTimesBox.setMaximumRowCount(VerbTimes.values().length);
      verbTimesBoxPanel = new JPanel();
      TotemLayout verbConjugationLayout = new TotemLayout(verbTimesBoxPanel);
      verbTimesBoxPanel.setLayout(verbConjugationLayout);
      verbTimesBoxPanel.add(verbTimesBox);
      verbTimesBoxPanel.setOpaque(false);
      verbTimesBoxPanel.setBackground(ApplicationColors.getTransparent());
      verbTimesBoxPanel.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(Translation.ZEITFORM)));

      keyboard = new KeyboardHebrewNikud(hebrew, components, 152, true, false);
   }

   private TitledBorder makeBorderBlank(String title)
   {
      return new TitledBorder(title);
   }

   private TitledBorder makeBorderRed(String title)
   {
      return new TitledBorder(BorderFactory.createLineBorder(Color.RED, 3),
            title);
   }

   private Component initTopPanel()
   {
      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal, 15));
      horizontal.setBackground(ApplicationColors.getTexturedBackgroundColor());
      horizontal.add(databaseNameField);
      horizontal.add(chapter);
      horizontal.add(indexField);
      return horizontal;
   }

   private Component initInput()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setLayout(new TotemLayout(vertical, 15));
      vertical.add(german);
      vertical.add(keyboard);

      JPanel horizontal = new JPanel();
      horizontal.setOpaque(false);
      horizontal.setLayout(new TrainLayout(horizontal, 15));
      horizontal.add(saveButton);
      horizontal.add(restoreButton);
      horizontal.add(cancelButton);

      vertical.add(horizontal);
      return vertical;
   }

   private Component initInfosLeft()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setLayout(new TotemLayout(vertical, 15));

      JScrollPane scrollPane = new JScrollPane(searchwordsJListGerman);
      scrollPane.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 100));
      scrollPane.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));

      JScrollPane scrollPane2 = new JScrollPane(searchwordsJListHebrew);
      scrollPane2.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 100));
      scrollPane2.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));

      vertical.add(newSearchwordGerman);
      vertical.add(scrollPane);
      vertical.add(deleteSearchwordButtonGerman);

      vertical.add(newSearchwordHebrew);
      vertical.add(scrollPane2);
      vertical.add(deleteSearchwordButtonHebrew);

      return vertical;
   }

   private Component initInfosRight()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setBackground(ApplicationColors.getTransparent());
      vertical.setLayout(new TotemLayout(vertical, 15));

      scrollPaneExpressionTable = new JScrollPane(expressionKindTable);
      scrollPaneExpressionTable
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 200));
      scrollPaneExpressionTable
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));
      scrollPaneExpressionTable.setBorder(BorderFactory.createTitledBorder(
            BorderFactory
                  .createLineBorder(ApplicationColors.getLightGrayGold()),
            translator.realisticTranslate(
                  Translation.WORTARTEN__MEHRFACHAUSWAHL_)));

      lastModiefiedLabel = new JLabel();
      lastModiefiedLabel.setFont(ApplicationFonts.getGermanFont(14F));

      JPanel horizontal = new JPanel();
      horizontal.setOpaque(false);
      horizontal.setBackground(ApplicationColors.getTransparent());
      horizontal.setLayout(new TrainLayout(horizontal, 15));

      horizontal.add(copyButton);
      horizontal.add(cutButton);
      horizontal.add(pasteButton);

      vertical.add(scrollPaneExpressionTable);
      vertical.add(lastModiefiedLabel);
      vertical.add(extraInfoScroller);
      vertical.add(horizontal);

      return vertical;
   }

   private Component initInfosExtra()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(true);
      vertical.setBackground(ApplicationColors.getTexturedBackgroundColor());
      vertical.setLayout(new TotemLayout(vertical, 15));

      definitionPanel = new JPanel();
      TotemLayout definitionLayout = new TotemLayout(definitionPanel, 5);
      definitionPanel.setLayout(definitionLayout);
      definitionPanel.setBorder(BorderFactory.createEmptyBorder());
      definitionPanel.setOpaque(true);
      definitionPanel
            .setBackground(ApplicationColors.getTexturedBackgroundColor());

      JScrollPane scrollPane2 = new JScrollPane(definitionPanel);
      scrollPane2.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 200));
      scrollPane2.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 600));
      scrollPane2.setBorder(BorderFactory.createEmptyBorder());
      scrollPane2.setViewportBorder(BorderFactory.createEmptyBorder());
      scrollPane2.setBackground(ApplicationColors.getTexturedBackgroundColor());
      scrollPane2.getViewport()
            .setBackground(ApplicationColors.getTexturedBackgroundColor());

      vertical.add(scrollPane2);

      vertical.add(initImagePanel());

      return vertical;
   }

   private Component initImagePanel()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setBackground(ApplicationColors.getTransparent());
      vertical.setLayout(new TotemLayout(vertical, 5));

      this.loadImageButton = new JButton("Bild laden");
      loadImageButton.setFont(ApplicationFonts.getButtonFont());
      this.removeImageButton = new JButton("Bild löschen");
      removeImageButton.setFont(ApplicationFonts.getButtonFont());

      imageButton = new ImageButton();
      imageButton.setMinimumSize(new Dimension(230, 125));
      imageButton.setMaximumSize(new Dimension(230, 125));

      vertical.add(loadImageButton);
      vertical.add(removeImageButton);
      vertical.add(imageButton);

      return vertical;
   }

   private void resetAllBorders()
   {
      chapter.setBorder(makeBorderBlank(this.chapterTitle));
      german.setBorder(makeBorderBlank(this.germanTitle));
      hebrew.setBlankBorder();
   }

   private void initController()
   {
      german.addActionListener(event -> {
         if (!german.getText().isEmpty())
         {
            german.setBorder(makeBorderBlank(this.germanTitle));
         }
      });

      newSearchwordGerman.addActionListener(event -> {
         String add = newSearchwordGerman.getText().replaceAll(",", "");
         if (!add.isEmpty())
         {
            searchwordsSetGerman.add(add);
            searchwordsJListGerman.setModel(getSearchwordsModelGerman());
            newSearchwordGerman.setText("");
            newSearchwordGerman.requestFocus();
         }
      });

      deleteSearchwordButtonGerman.addActionListener(event -> {
         if (searchwordsJListGerman.getSelectedValue() != null)
         {
            searchwordsSetGerman
                  .remove(searchwordsJListGerman.getSelectedValue());
            searchwordsJListGerman.setModel(getSearchwordsModelGerman());
         }
      });

      newSearchwordHebrew.addActionListener(event -> {
         String add = newSearchwordHebrew.getText().replaceAll(",", "");
         if (!add.isEmpty())
         {
            searchwordsSetHebrew.add(add);
            searchwordsJListHebrew.setModel(getSearchwordsModelHebrew());
            newSearchwordHebrew.setText("");
            newSearchwordHebrew.requestFocus();
         }
      });

      deleteSearchwordButtonHebrew.addActionListener(event -> {
         if (searchwordsJListHebrew.getSelectedValue() != null)
         {
            searchwordsSetHebrew
                  .remove(searchwordsJListHebrew.getSelectedValue());
            searchwordsJListHebrew.setModel(getSearchwordsModelHebrew());
         }
      });

      saveButton.addActionListener(event -> {
         if (testForCompletness())
         {
            save = true;
            saveExpression();
            this.setVisible(false);
         }
      });

      restoreButton.addActionListener(event -> {
         setExpressionForReset();
         resetAllBorders();
      });

      cancelButton.addActionListener(event -> {
         save = false;
         this.dispose();
      });

      loadImageButton.addActionListener(event -> {
         connector.chooseImageForExpression();
      });

      removeImageButton.addActionListener(event -> {
         connector.deleteImageForExpression();
      });

      imageButton.addMouseListener(new MouseAdapter()
      {
         @Override
         public void mouseClicked(MouseEvent event)
         {      
            JDialog frame = new JDialog(Common.getjFrame());
            frame.setModal(true);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            JPanel panel = new JPanel();
            ExpanderLayout layout = new ExpanderLayout(panel);
            panel.setLayout(layout);
            if(ImageData.loadImage(expression.getUuid()) == null)
            {
               return;
            }
            panel.add(new JLabel(
                  new ImageIcon(ImageData.loadImageOriginal(expression.getUuid()))));
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
         }
      });
   }

   public void showGrammaticalParentEnums(
         Set<GrammaticalParentEnum> grammaticalParentEnumsToShow)
   {
      definitionPanel.removeAll();
      if (grammaticalParentEnumsToShow.contains(GrammaticalParentEnum.GENDER))
      {
         definitionPanel.add(this.genderBoxPanel);
      }
      else
      {
         this.genderBox.setSelectedItem(Gender.GENDER_NA);
      }
      if (grammaticalParentEnumsToShow.contains(GrammaticalParentEnum.NUMERUS))
      {
         definitionPanel.add(this.numerusBoxPanel);
      }
      else
      {
         this.numerusBox.setSelectedItem(Numerus.NUMERUS_NA);
      }
      if (grammaticalParentEnumsToShow
            .contains(GrammaticalParentEnum.GRAMMATICAL_PERSON))
      {
         definitionPanel.add(this.grammaticalPersonBoxPanel);
      }
      else
      {
         this.grammaticalPersonBox
               .setSelectedItem(GrammaticalPerson.GRAMMATICALPERSON_NA);
      }
      if (grammaticalParentEnumsToShow.contains(GrammaticalParentEnum.BINJAN))
      {
         definitionPanel.add(this.binjanBoxPanel);
      }
      else
      {
         this.binjanBox.setSelectedItem(Binjan.BINJAN_NA);
      }
      if (grammaticalParentEnumsToShow
            .contains(GrammaticalParentEnum.VERB_TIMES))
      {
         definitionPanel.add(this.verbTimesBoxPanel);
      }
      else
      {
         this.verbTimesBox.setSelectedItem(VerbTimes.VERBTIMES_NA);
      }

      JPanel filler = new JPanel();
      filler.setMinimumSize(new Dimension(WIDTH_INFO_PANEL - 10, 0));
      filler.setMaximumSize(new Dimension(WIDTH_INFO_PANEL - 10, 700));
      filler.setBackground(ApplicationColors.getTexturedBackgroundColor());
      definitionPanel.add(filler);

      definitionPanel.validate();
      definitionPanel.repaint();
   }

   private boolean testForCompletness()
   {
      boolean result = true;
      if (((String) chapter.getSelectedItem()).isBlank())
      {
         chapter.setBorder(makeBorderRed(this.chapterTitle));
         result = false;
      }
      if (german.getText().isEmpty())
      {
         german.setBorder(makeBorderRed(this.germanTitle));
         result = false;
      }
      if (!hebrew.isFilledOut())
      {
         hebrew.setRedBorder();
         result = false;
      }
      return result;
   }

   private void saveExpression()
   {
      expression.setGerman(cleanTextLeaveComma(german.getText()));

      expression.setHebrew(
            new Hebrew(cleanTextLeaveComma(hebrew.getHebrewFieldText()),
                  cleanTextLeaveComma(hebrew.getPleneFieldText()),
                  cleanTextLeaveComma(hebrew.getDefektivFieldText()),
                  hebrew.isSimple()));

      expression.setLetterForSaving(LetterForSaving
            .getLetter(cleanTextLeaveComma(expression.getGerman())));

      Definitions definitions = new Definitions();
      Vector<Vector<ExpressionKindTableRow>> vektorRows = expressionKindTable
            .getModel().getData();
      for (Vector<ExpressionKindTableRow> vektorRow : vektorRows)
      {
         ExpressionKindItem expressionKind = vektorRow.get(0)
               .getExpressionKindItem();
         if (expressionKind.isSelected())
         {
            definitions.addExpressionKind(expressionKind.getKind());
            definitions.setGrammaticalEnum(expressionKind.getKind(),
                  binjanBox.getItemAt(binjanBox.getSelectedIndex()));
            definitions.setGrammaticalEnum(expressionKind.getKind(),
                  genderBox.getItemAt(genderBox.getSelectedIndex()));
            definitions.setGrammaticalEnum(expressionKind.getKind(),
                  grammaticalPersonBox
                        .getItemAt(grammaticalPersonBox.getSelectedIndex()));
            definitions.setGrammaticalEnum(expressionKind.getKind(),
                  numerusBox.getItemAt(numerusBox.getSelectedIndex()));
            definitions.setGrammaticalEnum(expressionKind.getKind(),
                  verbTimesBox.getItemAt(verbTimesBox.getSelectedIndex()));
         }
      }
      if (definitions.getExpressionKindSet().isEmpty())
      {
         definitions.addExpressionKind(ExpressionKind.EXPRESSIONKIND_UNKNOWN);
      }

      expression.setDefinitions(definitions);

      List<String> wordsGerman = new ArrayList<>();
      for (String word : searchwordsSetGerman)
      {
         wordsGerman.add(cleanTextAndNoComma(word));
      }
      expression.setSearchwordsGerman(wordsGerman);
      List<String> wordsHebrew = new ArrayList<>();
      for (String word : searchwordsSetHebrew)
      {
         wordsHebrew.add(cleanTextAndNoComma(word));
      }
      expression.setSearchwordsHebrew(wordsHebrew);
      Chapter selfChapter = new Chapter();
      selfChapter.setOrigin(Database.SELF);
      selfChapter
            .setName(cleanTextLeaveComma((String) chapter.getSelectedItem()));
      expression.setChapter(selfChapter);

      Settings.setRememberChapterForInput(selfChapter.getName());

      expression
            .setAdditionalInformation(cleanTextLeaveComma(extraInfo.getText()));

      if (((String) databaseNameField.getSelectedItem()).isBlank())
      {
         expression.getChapter().setDatabaseName(Database.SELF.getName());
      }
      else
      {
         expression.getChapter().setDatabaseName(cleanTextLeaveComma(
               (String) databaseNameField.getSelectedItem()));
      }

      Settings.setRememberDatabaseForInput(
            expression.getChapter().getDatabaseName());

      if (indexField.getText().isBlank())
      {
         expression.setSortingIndex(String.valueOf(SortingIndex.getCounter()));
      }
      else
      {
         expression.setSortingIndex(indexField.getText());
      }
      SortingIndex.setCounter(expression.getSortingIndex());

      expression.setLastModified(LocalDateTime.now());
   }

   private String cleanTextAndNoComma(String text)
   {
      return text.replaceAll("\t", "").replaceAll("\n", "").replaceAll(",", "")
            .strip();
   }

   private String cleanTextLeaveComma(String text)
   {
      return text.replaceAll("\t", "").replaceAll("\n", "").strip();
   }

   private void setExpressionForReset()
   {
      setExpression(this.expression, this.newExpression);
   }

   public void setExpression(Expression expression, boolean newExpression)
   {
      this.save = false;
      this.expression = expression;
      this.newExpression = newExpression;

      this.chapter.setModel(Data.getChapterComboBoxModel());
      if (newExpression)
      {
         this.chapter.setSelectedItem(Settings.getRememberChapterForInput());
      }
      else
      {
         this.chapter.setSelectedItem(expression.getChapter().getName());
      }

      this.indexField.setText(expression.getSortingIndex());

      this.german.setText(expression.getGerman());

      if (expression.getHebrew().isSimpleHebrew())
      {
         this.hebrew.setHebrewLayout(Selection.SIMPLE);
         this.hebrew.setHebrewFieldText(expression.getHebrew().getHebrew());
      }
      else
      {
         this.hebrew.setHebrewLayout(Selection.PLENE_DEFEKTIV);
         this.hebrew.setPleneFieldText(expression.getHebrew().getHebrewPlene());
         this.hebrew.setDefektivFieldText(
               expression.getHebrew().getHebrewDefektiv());
      }

      this.searchwordsSetGerman = new HashSet<>();
      for (String word : expression.getSearchwordsGerman())
      {
         this.searchwordsSetGerman.add(word);
      }
      this.searchwordsJListGerman.setModel(getSearchwordsModelGerman());

      this.searchwordsSetHebrew = new HashSet<>();
      for (String word : expression.getSearchwordsHebrew())
      {
         this.searchwordsSetHebrew.add(word);
      }
      this.searchwordsJListHebrew.setModel(getSearchwordsModelHebrew());

      if (newExpression)
      {
         expressionKindTable.setModel(ExpressionKind.getModelForMultiselect());
         binjanBox.setSelectedIndex(0);
         genderBox.setSelectedIndex(0);
         grammaticalPersonBox.setSelectedIndex(0);
         numerusBox.setSelectedIndex(0);
         verbTimesBox.setSelectedIndex(0);
         extraInfo.setText("");
      }
      else
      {
         Definitions definitions = expression.getDefinitions();
         Set<ExpressionKind> kinds = definitions.getExpressionKindSet();
         expressionKindTable
               .setModel(ExpressionKind.getModelForMultiselect(kinds));

         ExpressionKind kind;
         if (kinds.stream().findAny().isPresent())
         {
            kind = kinds.stream().findAny().get();
            binjanBox.setSelectedItem(
                  definitions.getGrammaticalEnum(kind, Binjan.class));
            genderBox.setSelectedItem(
                  definitions.getGrammaticalEnum(kind, Gender.class));
            grammaticalPersonBox.setSelectedItem(definitions
                  .getGrammaticalEnum(kind, GrammaticalPerson.class));
            numerusBox.setSelectedItem(
                  definitions.getGrammaticalEnum(kind, Numerus.class));
            verbTimesBox.setSelectedItem(
                  definitions.getGrammaticalEnum(kind, VerbTimes.class));
            showGrammaticalParentEnums(
                  ExpressionKind.getSetOfGrammaticalParentEnums(kinds));

            scrollPaneExpressionTable.getVerticalScrollBar()
                  .setMaximum(expressionKindTable.getMaxScrollValue());
            scrollPaneExpressionTable.getVerticalScrollBar()
                  .setValue(expressionKindTable.getScrollValue());
         }
         extraInfo.setText(expression.getAdditionalInformation());
      }

      if (expression.isDoChange())
      {
         databaseNameField.setModel(Data.getOwnDatabasesComboBoxModel());
      }
      else
      {
         databaseNameField.setModel(Data.getInternalDatabasesComboBoxModel());
      }

      if (newExpression)
      {
         databaseNameField
               .setSelectedItem(Settings.getRememberDatabaseForInput());
      }
      else
      {
         databaseNameField
               .setSelectedItem(expression.getChapter().getDatabaseName());
      }

      lastModiefiedLabel.setText(translator.realisticTranslate(Translation.VOM)
            + " "
            + expression.getLastModified()
                  .format(DateTimeFormatter.ofPattern(
                        translator.realisticTranslate(Translation._DATE_TIME)))
            + " " + translator.realisticTranslate(Translation.UHR));

      if (ImageData.isImageForExpressionAvailable(expression.getUuid()))
      {
         imageButton.setIcon(
               new ImageIcon(ImageData.loadImage(expression.getUuid())));
      }
      else
      {
         imageButton.setIcon(null);
      }
      imageButton.validate();
      imageButton.repaint();
   }

   private DefaultComboBoxModel<String> getSearchwordsModelGerman()
   {
      DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
      for (String word : searchwordsSetGerman)
      {
         model.addElement(word);
      }
      return model;
   }

   private DefaultComboBoxModel<String> getSearchwordsModelHebrew()
   {
      DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
      for (String word : searchwordsSetHebrew)
      {
         model.addElement(word);
      }
      return model;
   }

   public Expression getExpression()
   {
      return expression;
   }

   public boolean isSave()
   {
      return save;
   }

   public void setSave(boolean save)
   {
      this.save = save;
   }

   public boolean isFrozen()
   {
      return frozen;
   }

   public boolean setFrozen(boolean frozen)
   {
      boolean works = !frozen;
      try
      {
         this.german.setEditable(works);
         this.hebrew.setEditable(works);
         this.keyboard.setFrozen(frozen);
         this.saveButton.setEnabled(works);
         this.saveButton.setVisible(works);
         this.restoreButton.setEnabled(works);
         this.restoreButton.setVisible(works);
         this.newSearchwordGerman.setEditable(works);
         this.newSearchwordGerman.setVisible(works);
         this.deleteSearchwordButtonGerman.setVisible(works);
         this.newSearchwordHebrew.setEditable(works);
         this.newSearchwordHebrew.setVisible(works);
         this.deleteSearchwordButtonHebrew.setVisible(works);
         this.expressionKindTable.setFrozen(frozen);
         this.databaseNameField.setEditable(works);
         this.extraInfo.setEditable(works);
         this.binjanBox.setEditable(works);
         this.genderBox.setEditable(works);
         this.numerusBox.setEditable(works);
         this.grammaticalPersonBox.setEditable(works);
         this.binjanBox.setEditable(works);
         this.verbTimesBox.setEditable(works);
         this.binjanBox.setEnabled(works);
         this.genderBox.setEnabled(works);
         this.numerusBox.setEnabled(works);
         this.grammaticalPersonBox.setEnabled(works);
         this.binjanBox.setEnabled(works);
         this.verbTimesBox.setEnabled(works);
         this.copyButton.setVisible(works);
         this.cutButton.setVisible(works);
         this.pasteButton.setVisible(works);
         this.indexField.setEditable(works);
         this.frozen = frozen;
         return true;
      }
      catch (Exception e)
      {
         this.frozen = false;
         return false;
      }

   }
}
