package vokabeltrainer.table.list.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;

import vokabeltrainer.InfoTextField;
import vokabeltrainer.InputHebrewPanel;
import vokabeltrainer.InputHebrewPanel.Selection;
import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.LetterForSaving;
import vokabeltrainer.common.Settings;
import vokabeltrainer.editing.GermanDocument;
import vokabeltrainer.editing.NikudDocument;
import vokabeltrainer.keyboards.KeyboardHebrewNikud;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Chapter.Database;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Hebrew;
import vokabeltrainer.types.SortingIndex;
import vokabeltrainer.types.grammatical.GrammaticalEnum.GrammaticalParentEnum;
import vokabeltrainer.types.grammatical.expressionkind.Definitions;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

public class TextExpressionEditorView extends JDialog
      implements ExpressionEditorViewConnector
{
   private static final int WIDTH_INFO_PANEL = 240;

   private static final int WIDTH_INPUT_PANEL = Settings.getKeyboardWidth();
   
   private static final int WIDTH_PANEL = 320;

   private static final long serialVersionUID = 5853498340870217732L;
   
   private Translator translator = Common.getTranslator();
   private Expression expression;
   private boolean newExpression;
   private JTextArea german;
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
   private String germanTitle = translator.realisticTranslate(Translation.DEUTSCH);
   private String searchwordJListGermanTitle = translator.realisticTranslate(Translation.DEUTSCHE_SUCHWOERTER);
   private String searchwordsJListHebrewTitle = translator.realisticTranslate(Translation.HEBRAEISCHE_SUCHWOERTER);
   private String chapterTitle = translator.realisticTranslate(Translation.LEKTION);
   private JComboBox<String> chapter;

   private JButton pasteButton;
   private JButton cutButton;
   private JButton copyButton;

   private boolean frozen;

   @SuppressWarnings("unused")
   private NikudExpressionEditorControllerConnector connector;

   private JComboBox<String> databaseNameField;

   private JLabel lastModiefiedLabel;

   private JCheckBox textBox;

public TextExpressionEditorView(
         NikudExpressionEditorControllerConnector connector)
   {
      super(Common.getjFrame(), Settings.getWindowTitle(),
            Dialog.ModalityType.APPLICATION_MODAL);
      this.connector = connector;
      save = false;
      setResizable(true);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      setSize(Math.min(screenSize.width - 60, 1301),
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

      outerLayout.add(initTopPanel());
      outerLayout.add(layout);

      getContentPane().add(new JScrollPane(outerLayout));

      initController();
      Component[] focusList = { german, hebrew, newSearchwordGerman,
            newSearchwordHebrew };
      this.setFocusTraversalPolicy(
            new CerebrummiFocusTraversalPolicy(focusList));
   }

   private void initGuiFields()
   {
      Font germanfont = ApplicationFonts.getGermanFont(16F);
      Font hebrewfont = ApplicationFonts.getHebrewFont(30F);

      german = new JTextArea();
      german.setLineWrap(true);
      german.setWrapStyleWord(true);
      german.setBorder(makeBorderBlank(germanTitle));
      german.setFont(germanfont);
      german.setMinimumSize(new Dimension(WIDTH_INPUT_PANEL, 150));
      german.setMaximumSize(new Dimension(WIDTH_INPUT_PANEL, 250));
      german.setDocument(new GermanDocument(true));

      if (Settings.isSimpleHebrewInput())
      {
         hebrew = new InputHebrewPanel(Selection.SIMPLE, 415, 6, true, this,  WIDTH_INPUT_PANEL, ApplicationColors.getLightYellow());
      }
      else
      {
         hebrew = new InputHebrewPanel(Selection.PLENE_DEFEKTIV, 415, 6, true,
               this,  WIDTH_INPUT_PANEL, ApplicationColors.getLightYellow());
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

      saveButton = new JButton(translator.realisticTranslate(Translation.SPEICHERN));
      saveButton.setFont(ApplicationFonts.getButtonFont());
      saveButton.setMinimumSize(new Dimension(120, 40));
      saveButton.setMaximumSize(new Dimension(160, 40));

      restoreButton = new JButton(translator.realisticTranslate(Translation.ZURUECKSETZEN));
      restoreButton.setFont(ApplicationFonts.getButtonFont());
      restoreButton.setMinimumSize(new Dimension(120, 40));
      restoreButton.setMaximumSize(new Dimension(160, 40));

      cancelButton = new JButton(translator.realisticTranslate(Translation.ABBRECHEN));
      cancelButton.setFont(ApplicationFonts.getButtonFont());
      cancelButton.setMinimumSize(new Dimension(120, 40));
      cancelButton.setMaximumSize(new Dimension(160, 40));

      chapter = new JComboBox<String>();
      chapter.setEditable(true);
      chapter.setMaximumRowCount(20);
      chapter.setBorder(new TitledBorder(this.chapterTitle));
      chapter.setOpaque(false);
      chapter.setBackground(new Color(0, 0, 0, 0));
      chapter.setMinimumSize(new Dimension(WIDTH_PANEL, 70));
      chapter.setMaximumSize(new Dimension(WIDTH_INPUT_PANEL, 70));

      indexField = new JTextField();
      indexField.setBorder(makeBorderBlank(translator.realisticTranslate(Translation.INDEX)));
      indexField.setFont(germanfont);
      indexField.setOpaque(false);
      indexField.setBackground(ApplicationColors.getTransparent());
      indexField.setMinimumSize(new Dimension(85, 70));
      indexField.setMaximumSize(new Dimension(85, 70));

      databaseNameField = new JComboBox<>();
      databaseNameField.setFont(ApplicationFonts.getButtonFont());
      databaseNameField.setMinimumSize(new Dimension(WIDTH_PANEL, 70));
      databaseNameField.setMaximumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      databaseNameField.setBorder(new TitledBorder(translator.realisticTranslate(Translation.DATENBANK)));
      databaseNameField.setEditable(true);
      databaseNameField.setMaximumRowCount(20);

      pasteButton = new JButton(new DefaultEditorKit.PasteAction());
      pasteButton.setIcon(new ImageIcon(ApplicationImages.getPaste()));
      pasteButton.setText("");
      pasteButton.setToolTipText(translator.realisticTranslate(Translation.EINFUEGEN));
      pasteButton
            .setMinimumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));
      pasteButton
            .setMaximumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));

      cutButton = new JButton(new DefaultEditorKit.CutAction());
      cutButton.setIcon(new ImageIcon(ApplicationImages.getCut()));
      cutButton.setText("");
      cutButton.setToolTipText(translator.realisticTranslate(Translation.AUSSCNEIDEN));
      cutButton.setMinimumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));
      cutButton.setMaximumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));

      copyButton = new JButton(new DefaultEditorKit.CopyAction());
      copyButton.setIcon(new ImageIcon(ApplicationImages.getCopy2()));
      copyButton.setText("");
      copyButton.setToolTipText(translator.realisticTranslate(Translation.KOPIEREN));
      copyButton.setMinimumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));
      copyButton.setMaximumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));

      keyboard = new KeyboardHebrewNikud(hebrew, components, 152, true, true);
      
      textBox = new JCheckBox("Text");
      textBox.setSelected(true);
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
      horizontal.add(copyButton);
      horizontal.add(cutButton);
      horizontal.add(pasteButton);
      lastModiefiedLabel = new JLabel();
      lastModiefiedLabel.setFont(ApplicationFonts.getGermanFont(16F));
      horizontal.add(lastModiefiedLabel);
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
      horizontal.add(textBox);

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

   private void resetAllBorders()
   {
      chapter.setBorder(makeBorderBlank(this.chapterTitle));
      german.setBorder(makeBorderBlank(this.germanTitle));
      hebrew.setBlankBorder();
   }

   private void initController()
   {
      german.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (!german.getText().isEmpty())
		         {
		            german.setBorder(makeBorderBlank(germanTitle));
		         }
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (!german.getText().isEmpty())
		         {
		            german.setBorder(makeBorderBlank(germanTitle));
		         }
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
   }

   public void showGrammaticalParentEnums(
         Set<GrammaticalParentEnum> grammaticalParentEnumsToShow)
   {
      
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
      if(textBox.isSelected())
      {
    	  definitions.addExpressionKind(ExpressionKind.TEXT);
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
            .setAdditionalInformation("");

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

      lastModiefiedLabel
            .setText(translator.realisticTranslate(Translation.VOM)
                  + " "
                  + expression.getLastModified()
                        .format(DateTimeFormatter.ofPattern(translator.realisticTranslate(Translation._DATE_TIME)))
                  + " "
                  + translator.realisticTranslate(Translation.UHR));
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
         this.databaseNameField.setEditable(works);
         this.copyButton.setVisible(works);
         this.cutButton.setVisible(works);
         this.pasteButton.setVisible(works);
         this.indexField.setEditable(works);
         this.textBox.setEnabled(works);
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
