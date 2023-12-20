package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.TextImage;
import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.Settings;
import vokabeltrainer.keyboards.KeyboardHebrewAllLetters;
import vokabeltrainer.panels.dictionary.Action;
import vokabeltrainer.panels.dictionary.DataButton;
import vokabeltrainer.panels.dictionary.Tabulator;
import vokabeltrainer.panels.dictionary.DictionaryControllerConnector;
import vokabeltrainer.panels.dictionary.DictionaryViewConnector;
import vokabeltrainer.panels.dictionary.SearchAction;
import vokabeltrainer.panels.list.ChapterList;
import vokabeltrainer.panels.list.ChapterListSelectionModel;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.table.ExpressionTable;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.table.list.editor.expressionkindtable.singleselect.ExpressionKindTableRow2;
import vokabeltrainer.table.list.editor.expressionkindtable.singleselect.ExpressionKindTableSingleselect;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;
import vokabeltrainer.types.SearchType;
import vokabeltrainer.types.SortingType;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKindItem;

public class DictionaryView extends BackgroundPanelTiled
      implements DictionaryViewConnector
{
   private static final long serialVersionUID = 9130321171813967337L;

   private ButtonGroup languageGroup;
   private ButtonGroup searchTypeGroupHebrew;
   private ButtonGroup searchTypeGroupGerman;
   private ExpressionTable table;
   private JPanel tablePanel;
   private JButton copyAllSelectedButton;
   private JButton copyInTableSelectedButton;
   private JButton copyTableButton;
   private JButton selectAllInTableButton;
   private JButton clearInTableSelectedButton;
   private JButton clearAllSelectedButton;
   private JButton deleteAllSelectedButton;
   private JButton deleteInTableSelectedButton;
   private JButton wasteBinButton;
   private JButton shredderButton;
   private JTabbedPane tabbedPane;
   private ChapterList chapterList;
   private JPanel chapterPanel;
   private JPanel swapPanel;
   private CardLayout cardLayout;
   private JTextField searchPhraseGerman;
   private JTextField searchPhraseHebrew;
   private JButton hebrewSearchButton;
   private JButton germanSearchButton;
   private JButton tableInfoButton;
   private JPanel horizontalLanguagePanel;

   private DictionaryControllerConnector connector;

   private ChapterListSelectionModel listSelectionModel;

   private ListSelectionListener listSelectionListener;

   private JScrollPane tableScroller;

   private ExpressionKindTableSingleselect expressionKindTable;

   private JRadioButton sortForDateBox;
   private JRadioButton sortForIndexBox;
   private JRadioButton sortForAlphabetBox;
   private ButtonGroup sortingGroup;

   private JButton moveToChapterButton;

   private JComboBox<String> chapterChoiceBox;

   private JComboBox<String> databaseChoiceBox;

   private JButton moveToDatabaseButton;

   private Translator translator = Common.getTranslator();

   public DictionaryView(DictionaryControllerConnector connector)
   {
      this.connector = connector;
      setLayout(new BullsEyeLayout(this));
      setOpaque(true);
      setBackground(ApplicationColors.getTexturedBackgroundColor());

      JPanel layout = new JPanel();
      TrainLayout layoutLayout = new TrainLayout(layout, 15);
      layout.setLayout(layoutLayout);
      layout.setOpaque(false);
      add(layout);

      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical, 15));
      vertical.setOpaque(false);
      vertical.setBackground(ApplicationColors.getBackgroundGold());

      horizontalLanguagePanel = new JPanel();
      horizontalLanguagePanel
            .setLayout(new TrainLayout(horizontalLanguagePanel, 15));
      horizontalLanguagePanel.setOpaque(false);
      horizontalLanguagePanel
            .setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));
      languageGroup = new ButtonGroup();
      initLanguageButtonGroup(languageGroup);
      Enumeration<AbstractButton> enumeration1 = languageGroup.getElements();
      while (enumeration1.hasMoreElements())
      {
         AbstractButton button = enumeration1.nextElement();
         button.setMinimumSize(new Dimension(90, 30));
         button.setMaximumSize(new Dimension(120, 60));
         button.addActionListener(event -> this.connector
               .switchLanguage(button.getActionCommand()));
         horizontalLanguagePanel.add(button);
      }

      JPanel horizontalSortPanel = new JPanel();
      horizontalSortPanel.setLayout(new TrainLayout(horizontalSortPanel, 15));
      horizontalSortPanel.setOpaque(true);
      horizontalSortPanel.setBackground(ApplicationColors.getSunflowerYellow());
      horizontalSortPanel.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(Translation.TABELLE_SORTIEREN_NACH)));

      sortForAlphabetBox = new JRadioButton(
            translator.realisticTranslate(Translation.ALFABET));
      sortForAlphabetBox.setFont(ApplicationFonts.getRadioButtonFont());
      sortForAlphabetBox.setMinimumSize(new Dimension(70, 30));
      sortForAlphabetBox.setMaximumSize(new Dimension(100, 60));
      sortForAlphabetBox.setActionCommand(SortingType.ALPHABET.name());

      sortForDateBox = new JRadioButton(
            translator.realisticTranslate(Translation.DATUM));
      sortForDateBox.setFont(ApplicationFonts.getRadioButtonFont());
      sortForDateBox.setMinimumSize(new Dimension(70, 30));
      sortForDateBox.setMaximumSize(new Dimension(100, 60));
      sortForDateBox.setActionCommand(SortingType.DATE.name());

      sortForIndexBox = new JRadioButton(
            translator.realisticTranslate(Translation.INDEX));
      sortForIndexBox.setFont(ApplicationFonts.getRadioButtonFont());
      sortForIndexBox.setMinimumSize(new Dimension(70, 30));
      sortForIndexBox.setMaximumSize(new Dimension(100, 60));
      sortForIndexBox.setActionCommand(SortingType.INDEX.name());

      sortingGroup = new ButtonGroup();
      sortingGroup.add(sortForAlphabetBox);
      sortingGroup.add(sortForDateBox);
      sortingGroup.add(sortForIndexBox);

      sortForIndexBox.setSelected(true);

      horizontalSortPanel.add(sortForAlphabetBox);
      horizontalSortPanel.add(sortForDateBox);
      horizontalSortPanel.add(sortForIndexBox);
      horizontalLanguagePanel.add(horizontalSortPanel);

      tabbedPane = new JTabbedPane();
      tabbedPane.setOpaque(false);
      tabbedPane.setFont(ApplicationFonts.getButtonFont());
      tabbedPane.addTab(translator.realisticTranslate(Translation.LEKTIONEN),
            initChaptersTab());
      tabbedPane.addTab(translator.realisticTranslate(Translation.SUCHE),
            initSearchTab());
      tabbedPane.addTab(translator.realisticTranslate(Translation.WORTARTEN),
            initExpressionKindsTab());
      tabbedPane.addTab(translator.realisticTranslate(Translation.AUSWAHL),
            initSelectedTab());
      tabbedPane.setMinimumSize(new Dimension(420, 400));
      tabbedPane.setMaximumSize(new Dimension(600, 700));

      vertical.add(horizontalLanguagePanel);
      vertical.add(tabbedPane);

      tablePanel = new JPanel(new BorderLayout());
      tablePanel.setMinimumSize(new Dimension(420, 400));
      tablePanel.setMaximumSize(new Dimension(513, 800));
      tablePanel.setOpaque(false);

      JPanel filler2 = new JPanel();
      filler2.setMinimumSize(new Dimension(1, 1));
      filler2.setMaximumSize(new Dimension(1000, 2000));
      filler2.setOpaque(false);

      JPanel filler3 = new JPanel();
      filler3.setMinimumSize(new Dimension(1, 1));
      filler3.setMaximumSize(new Dimension(1000, 2000));
      filler3.setOpaque(false);

      layout.add(vertical);
      layout.add(filler2);
      layout.add(tablePanel);
      layout.add(filler3);
      layout.add(initServicePanel());

      Tabulator.setTabShowing(Tabulator.CHAPTER_TAB);

      initChapterController();

      loadChapters();

      initController();
   }

   private void initChapterController()
   {
      listSelectionListener = new ListSelectionListener()
      {

         @Override
         public void valueChanged(ListSelectionEvent event)
         {
            if (!event.getValueIsAdjusting())
            {
               connector.displayChapterWhich(getSelectedChapter());
            }
         }

      };
   }

   private Component initSearchTab()
   {
      JPanel vertical1 = new JPanel();
      vertical1.setLayout(new TotemLayout(vertical1));
      vertical1.setOpaque(false);

      JPanel germanSearch = new JPanel();
      germanSearch.setLayout(new TotemLayout(germanSearch, 5));
      germanSearch.setBackground(ApplicationColors.getWhite());
      searchPhraseGerman = new JTextField();
      searchPhraseGerman.setBorder(new TitledBorder(
            translator.realisticTranslate(Translation.WORT_AUF_DEUTSCH_EINGEBEN)));
      germanSearch.add(searchPhraseGerman);
      searchPhraseGerman
            .setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 70));
      searchPhraseGerman.setMaximumSize(
            new Dimension(Settings.getKeyboardWidth() + 50, 70));

      JPanel filler = new JPanel();
      filler.setOpaque(false);
      filler.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 100));
      filler.setMaximumSize(
            new Dimension(Settings.getKeyboardWidth() + 50, 270));
      germanSearch.add(filler);

      searchTypeGroupGerman = new ButtonGroup();
      germanSearch.add(initSearchRadioButtonPanel(searchTypeGroupGerman,
            Language.GERMAN_TO_HEBREW));

      germanSearchButton = new JButton(
            translator.realisticTranslate(Translation.SUCHE_STARTEN));
      germanSearchButton.setFont(ApplicationFonts.getButtonFont());
      germanSearchButton.setIcon(new ImageIcon(ApplicationImages.getSearch()));
      JPanel wrapper = new JPanel(new FlowLayout());
      wrapper.setOpaque(false);
      wrapper.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 30));
      wrapper.setMaximumSize(
            new Dimension(Settings.getKeyboardWidth() + 50, 50));
      wrapper.add(germanSearchButton);
      germanSearch.add(wrapper);

      JPanel hebrewSearch = new JPanel();
      hebrewSearch.setLayout(new TotemLayout(hebrewSearch, 5));
      hebrewSearch.setBackground(ApplicationColors.getWhite());
      searchPhraseHebrew = new JTextField();
      searchPhraseHebrew.setBorder(new TitledBorder(
            translator.realisticTranslate(Translation.WORT_AUF_HEBRAISCH_EINGEBEN)));

      KeyboardHebrewAllLetters keyboard = new KeyboardHebrewAllLetters(
            searchPhraseHebrew, new ArrayList<JTextComponent>(), 70, true);

      hebrewSearch.add(keyboard);

      JPanel filler2 = new JPanel();
      filler2.setOpaque(false);
      filler2.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 5));
      filler2.setMaximumSize(
            new Dimension(Settings.getKeyboardWidth() + 50, 14));
      hebrewSearch.add(filler2);

      searchTypeGroupHebrew = new ButtonGroup();
      hebrewSearch.add(initSearchRadioButtonPanel(searchTypeGroupHebrew,
            Language.HEBREW_TO_GERMAN));

      hebrewSearchButton = new JButton(
            translator.realisticTranslate(Translation.SUCHE_STARTEN));
      hebrewSearchButton.setFont(ApplicationFonts.getButtonFont());
      hebrewSearchButton.setIcon(new ImageIcon(ApplicationImages.getSearch()));
      JPanel wrapper1 = new JPanel(new FlowLayout());
      wrapper1.setOpaque(false);
      wrapper1.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 30));
      wrapper1.setMaximumSize(
            new Dimension(Settings.getKeyboardWidth() + 50, 50));
      wrapper1.add(hebrewSearchButton);
      hebrewSearch.add(wrapper1);

      cardLayout = new CardLayout();
      swapPanel = new JPanel(cardLayout);
      swapPanel.setOpaque(false);
      swapPanel.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 420));
      swapPanel.setMaximumSize(
            new Dimension(Settings.getKeyboardWidth() + 50, 620));
      germanSearch.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 0));
      hebrewSearch.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 0));
      swapPanel.add(Language.GERMAN_TO_HEBREW.name(), germanSearch);
      swapPanel.add(Language.HEBREW_TO_GERMAN.name(), hebrewSearch);

      vertical1.add(swapPanel);

      return vertical1;
   }

   private JPanel initSearchRadioButtonPanel(ButtonGroup group,
         Language language)
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical, 5));
      vertical.setOpaque(false);

      for (SearchType type : SearchType.values())
      {
         JRadioButton radioButton = new JRadioButton(type.getMeaning(language));
         radioButton.setActionCommand(type.name());
         if (SearchType.WORDSTART.equals(type))
         {
            radioButton.setSelected(true);
         }
         radioButton.setFont(ApplicationFonts.getButtonFont());
         JPanel wrapper = new JPanel(new FlowLayout());
         wrapper.setOpaque(false);
         wrapper.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 25));
         wrapper.setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 25));
         wrapper.add(radioButton);
         vertical.add(wrapper);
         group.add(radioButton);
      }

      return vertical;
   }

   private Component initSelectedTab()
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);
      vertical.setOpaque(false);
      vertical.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

      JPanel horizontalMoveToChapterPanel = new JPanel();
      TrainLayout horizontalMoveToChapterLayout = new TrainLayout(
            horizontalMoveToChapterPanel, 15);
      horizontalMoveToChapterPanel.setLayout(horizontalMoveToChapterLayout);

      chapterChoiceBox = new JComboBox<>();
      chapterChoiceBox.setEditable(true);
      chapterChoiceBox.setModel(Data.getChapterComboBoxModel());

      moveToChapterButton = new JButton(
            translator.realisticTranslate(Translation.AUSWAHL_ZUR_LEKTION_VERSCHIEBEN));
      moveToChapterButton.setFont(ApplicationFonts.getButtonFont());

      horizontalMoveToChapterPanel.add(chapterChoiceBox);
      horizontalMoveToChapterPanel.add(moveToChapterButton);

      JPanel horizontalMoveToDatabasePanel = new JPanel();
      TrainLayout horizontalMoveToDatabaseLayout = new TrainLayout(
            horizontalMoveToDatabasePanel, 15);
      horizontalMoveToDatabasePanel.setLayout(horizontalMoveToDatabaseLayout);

      databaseChoiceBox = new JComboBox<>();
      databaseChoiceBox.setEditable(true);
      databaseChoiceBox.setModel(Data.getOwnDatabasesComboBoxModel());

      moveToDatabaseButton = new JButton(translator
            .realisticTranslate(Translation.AUSWAHL_ZUR_DATENBANK_VERSCHIEBEN));
      moveToDatabaseButton.setFont(ApplicationFonts.getButtonFont());

      horizontalMoveToDatabasePanel.add(databaseChoiceBox);
      horizontalMoveToDatabasePanel.add(moveToDatabaseButton);

      vertical.add(horizontalMoveToChapterPanel);
      vertical.add(horizontalMoveToDatabasePanel);

      return vertical;
   }

   private JPanel initChaptersTab()
   {
      chapterPanel = new JPanel();
      chapterPanel.setLayout(new BorderLayout());
      chapterPanel.setOpaque(false);
      return chapterPanel;
   }

   private JPanel initExpressionKindsTab()
   {
      JPanel vertical1 = new JPanel(new BorderLayout());
      vertical1.setOpaque(false);

      expressionKindTable = new ExpressionKindTableSingleselect(
            ExpressionKind.getModelForSingleselect(), 300, connector);
      JScrollPane scroller = new JScrollPane(expressionKindTable);
      scroller.setMinimumSize(new Dimension(300, 470));
      scroller.setMaximumSize(new Dimension(300, 470));
      scroller.setBorder(BorderFactory.createTitledBorder(Common.getTranslator().realisticTranslate(Translation.DOPPELCLICK)));

      JPanel scrollerWrapper = new JPanel();
      BullsEyeLayout scrollerWrapperLayout = new BullsEyeLayout(
            scrollerWrapper);
      scrollerWrapper.setLayout(scrollerWrapperLayout);
      
      scrollerWrapper.add(scroller);
      vertical1.add(scrollerWrapper, BorderLayout.CENTER);

      return vertical1;
   }

   private Component initServicePanel()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setLayout(new TotemLayout(vertical));

      copyAllSelectedButton = new JButton(
            translator.realisticTranslate(Translation.GESAMTAUSWAHL_KOPIEREN));
      copyAllSelectedButton.setFont(ApplicationFonts.getButtonFont());
      copyAllSelectedButton.setHorizontalAlignment(SwingConstants.LEFT);
      copyAllSelectedButton.setIcon(new ImageIcon(ApplicationImages.getCopy()));
      copyAllSelectedButton.setToolTipText(translator.realisticTranslate(
            Translation.NUR_SELBST_EINGEGEBENE_VOKABELN_KOENNEN_KOPIERT_WERDEN));
      copyAllSelectedButton.setMinimumSize(new Dimension(200, 40));
      copyAllSelectedButton.setMaximumSize(new Dimension(600, 40));

      copyInTableSelectedButton = new JButton(
            translator.realisticTranslate(Translation.TABELLENAUSWAHL_KOPIEREN));
      copyInTableSelectedButton.setFont(ApplicationFonts.getButtonFont());
      copyInTableSelectedButton.setHorizontalAlignment(SwingConstants.LEFT);
      copyInTableSelectedButton
            .setIcon(new ImageIcon(ApplicationImages.getCopy()));
      copyInTableSelectedButton.setToolTipText(translator.realisticTranslate(
            Translation.NUR_SELBST_EINGEGEBENE_VOKABELN_KOENNEN_KOPIERT_WERDEN));
      copyInTableSelectedButton.setMinimumSize(new Dimension(200, 40));
      copyInTableSelectedButton.setMaximumSize(new Dimension(600, 40));

      copyTableButton = new JButton(
            translator.realisticTranslate(Translation.TABELLE_KOPIEREN));
      copyTableButton.setFont(ApplicationFonts.getButtonFont());
      copyTableButton.setHorizontalAlignment(SwingConstants.LEFT);
      copyTableButton.setIcon(new ImageIcon(ApplicationImages.getCopy()));
      copyTableButton.setToolTipText(translator.realisticTranslate(
            Translation.NUR_SELBST_EINGEGEBENE_VOKABELN_KOENNEN_KOPIERT_WERDEN));
      copyTableButton.setMinimumSize(new Dimension(200, 40));
      copyTableButton.setMaximumSize(new Dimension(600, 40));

      selectAllInTableButton = new JButton(
            translator.realisticTranslate(Translation.TABELLE_AUSWAEHLEN));
      selectAllInTableButton.setFont(ApplicationFonts.getButtonFont());
      selectAllInTableButton.setHorizontalAlignment(SwingConstants.LEFT);
      selectAllInTableButton
            .setIcon(new ImageIcon(ApplicationImages.getSelect()));
      selectAllInTableButton.setMinimumSize(new Dimension(200, 40));
      selectAllInTableButton.setMaximumSize(new Dimension(600, 40));

      clearAllSelectedButton = new JButton(
            translator.realisticTranslate(Translation.GESAMTAUSWAHL_AUFHEBEN));
      clearAllSelectedButton.setFont(ApplicationFonts.getButtonFont());
      clearAllSelectedButton.setHorizontalAlignment(SwingConstants.LEFT);
      clearAllSelectedButton
            .setIcon(new ImageIcon(ApplicationImages.getClear()));
      clearAllSelectedButton.setMinimumSize(new Dimension(200, 40));
      clearAllSelectedButton.setMaximumSize(new Dimension(600, 40));

      clearInTableSelectedButton = new JButton(
            translator.realisticTranslate(Translation.TABELLENAUSWAHL_AUFHEBEN));
      clearInTableSelectedButton.setFont(ApplicationFonts.getButtonFont());
      clearInTableSelectedButton.setHorizontalAlignment(SwingConstants.LEFT);
      clearInTableSelectedButton
            .setIcon(new ImageIcon(ApplicationImages.getClear()));
      clearInTableSelectedButton.setMinimumSize(new Dimension(200, 40));
      clearInTableSelectedButton.setMaximumSize(new Dimension(600, 40));

      deleteInTableSelectedButton = new JButton(
            translator.realisticTranslate(Translation.TABELLENAUSWAHL_LOESCHEN));
      deleteInTableSelectedButton.setFont(ApplicationFonts.getButtonFont());
      deleteInTableSelectedButton.setHorizontalAlignment(SwingConstants.LEFT);
      deleteInTableSelectedButton
            .setIcon(new ImageIcon(ApplicationImages.getDeleteWord()));
      deleteInTableSelectedButton.setMinimumSize(new Dimension(200, 40));
      deleteInTableSelectedButton.setMaximumSize(new Dimension(600, 40));

      deleteAllSelectedButton = new JButton(
            translator.realisticTranslate(Translation.GESAMTAUSWAHL_LOESCHEN));
      deleteAllSelectedButton.setFont(ApplicationFonts.getButtonFont());
      deleteAllSelectedButton.setHorizontalAlignment(SwingConstants.LEFT);
      deleteAllSelectedButton
            .setIcon(new ImageIcon(ApplicationImages.getDeleteWord()));
      deleteAllSelectedButton.setMinimumSize(new Dimension(200, 40));
      deleteAllSelectedButton.setMaximumSize(new Dimension(600, 40));

      JPanel copyPanel = new JPanel();
      copyPanel.setLayout(new TotemLayout(copyPanel, 10));
      copyPanel.setBackground(ApplicationColors.getLightBlue());
      copyPanel.setBorder(BorderFactory.createMatteBorder(5, 3, 5, 3,
            new Color(215, 231, 247)));
      copyPanel.add(copyInTableSelectedButton);
      copyPanel.add(copyTableButton);
      copyPanel.add(copyAllSelectedButton);

      JPanel selectUnselectPanel = new JPanel();
      selectUnselectPanel.setLayout(new TotemLayout(selectUnselectPanel, 10));
      selectUnselectPanel.setBackground(ApplicationColors.getWhite());
      selectUnselectPanel.setBorder(
            BorderFactory.createMatteBorder(5, 3, 5, 3, ApplicationColors.getWhite()));
      selectUnselectPanel.add(selectAllInTableButton);
      selectUnselectPanel.add(clearInTableSelectedButton);
      selectUnselectPanel.add(clearAllSelectedButton);

      JPanel deletePanel = new JPanel();
      deletePanel.setLayout(new TotemLayout(deletePanel, 10));
      deletePanel.setBackground(new Color(169, 136, 103));
      deletePanel.setBorder(BorderFactory.createMatteBorder(5, 3, 5, 3,
            new Color(169, 136, 103)));
      deletePanel.add(deleteInTableSelectedButton);
      deletePanel.add(deleteAllSelectedButton);

      JPanel infoPanel = new JPanel();
      infoPanel.setLayout(new TrainLayout(infoPanel, 10));
      infoPanel.setBackground(ApplicationColors.getDarkGold());
      infoPanel.setBorder(BorderFactory.createMatteBorder(5, 3, 5, 3,
            ApplicationColors.getDarkGold()));

      JLabel infoLabel = new JLabel(
            translator.realisticTranslate(Translation.TABELLE_BEDIENEN));
      infoLabel.setFont(ApplicationFonts.getButtonFont());
      infoLabel.setForeground(ApplicationColors.getWhite());
      infoLabel.setMinimumSize(new Dimension(200, 40));
      infoLabel.setMaximumSize(new Dimension(600, 40));

      tableInfoButton = new JButton(
            new ImageIcon(ApplicationImages.getInfoButtonIcon()));
      tableInfoButton.setBackground(new Color(0, 0, 0, 0));
      tableInfoButton.setMinimumSize(new Dimension(20, 40));
      tableInfoButton.setMaximumSize(new Dimension(20, 40));
      tableInfoButton.setMargin(new Insets(0, 0, 0, 0));
      infoPanel.add(tableInfoButton);
      infoPanel.add(infoLabel);

      JPanel trashPanel = new JPanel();
      TrainLayout trashPanelLayout = new TrainLayout(trashPanel, 15);
      trashPanel.setLayout(trashPanelLayout);
      trashPanel.setOpaque(false);
      trashPanel.setMinimumSize(new Dimension(300, 100));
      trashPanel.setMaximumSize(new Dimension(600, 120));

      JPanel trashIconPanel = new JPanel(new GridLayout(3, 2));
      trashIconPanel.setOpaque(false);

      wasteBinButton = new JButton(
            new ImageIcon(ApplicationImages.getTrashcan()));
      wasteBinButton.setBorder(
            BorderFactory.createLineBorder(ApplicationColors.getGreen(), 2));
      wasteBinButton.setBorderPainted(true);
      wasteBinButton.setContentAreaFilled(false);
      wasteBinButton.setFocusPainted(false);

      shredderButton = new JButton(
            new ImageIcon(ApplicationImages.getShredder()));
      shredderButton.setBorder(
            BorderFactory.createLineBorder(ApplicationColors.getDarkRed(), 2));
      shredderButton.setBorderPainted(true);
      shredderButton.setContentAreaFilled(false);
      shredderButton.setFocusPainted(false);

      JPanel filler1 = new JPanel();
      filler1.setOpaque(false);
      
      JPanel filler2 = new JPanel();
      filler2.setOpaque(false);
      
      JPanel filler3 = new JPanel();
      filler3.setOpaque(false);
      
      JPanel filler4 = new JPanel();
      filler4.setOpaque(false);
      
      trashIconPanel.add(filler1);
      trashIconPanel.add(filler2);
      trashIconPanel.add(wasteBinButton);
      trashIconPanel.add(shredderButton);
      trashIconPanel.add(filler3);
      trashIconPanel.add(filler4);

      trashPanel.add(trashIconPanel);

      JPanel filler = new JPanel();
      filler.setMinimumSize(new Dimension(1, 1));
      filler.setMaximumSize(new Dimension(800, 800));
      filler.setOpaque(false);

      vertical.add(selectUnselectPanel);
      vertical.add(copyPanel);
      vertical.add(deletePanel);
      vertical.add(infoPanel);
      vertical.add(trashPanel);
      vertical.add(filler);
      return vertical;
   }

   private void initController()
   {
      tabbedPane.addChangeListener(event -> connector
            .tabbedPaneChanged(tabbedPane.getSelectedIndex()));

      copyAllSelectedButton
            .addActionListener(event -> connector.copyAllSelectedExpressions());

      copyTableButton
            .addActionListener(event -> connector.copyExpressionsOfTable());

      copyInTableSelectedButton.addActionListener(
            event -> connector.copyInTableSelectedExpressions());

      clearInTableSelectedButton
            .addActionListener(event -> connector.unselectTableExpressions());

      clearAllSelectedButton
            .addActionListener(event -> connector.unselectAllExpressions());

      deleteAllSelectedButton.addActionListener(
            event -> connector.deleteAllSelectedExpressions());

      deleteInTableSelectedButton.addActionListener(
            event -> connector.deleteInTableSelectedExpressions());

      wasteBinButton.addActionListener(event -> connector.openTrashCanDialog());

      selectAllInTableButton
            .addActionListener(event -> connector.selectTableExpressions());

      shredderButton
            .addActionListener(event -> connector.shredderDeletedExpressions());

      hebrewSearchButton.addActionListener(event -> connector.searchHebrew());

      germanSearchButton.addActionListener(event -> connector.searchGerman());

      searchPhraseGerman.addKeyListener(new KeyAdapter()
      {
         @Override
         public void keyPressed(KeyEvent e)
         {
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
               connector.searchGerman();
            }
         }
      });

      searchPhraseHebrew.addKeyListener(new KeyAdapter()
      {
         @Override
         public void keyPressed(KeyEvent e)
         {
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
               connector.searchHebrew();
            }
         }
      });

      tableInfoButton.addActionListener(event -> {
         JOptionPane.showMessageDialog(horizontalLanguagePanel, "",
               Settings.getWindowTitle(), JOptionPane.INFORMATION_MESSAGE,
               new ImageIcon(TextImage.make(
                     translator.realisticTranslate(Translation.TABELLE),
                     translator.realisticTranslate(
                           Translation.EINMAL_KLICKEN_MARKIERT_EINEN_EINTRAG),
                     translator.realisticTranslate(
                           Translation.ENTER_DRUECKEN_OEFFNET_DEN_MARKIERTEN_EINTRAG),
                     translator.realisticTranslate(
                           Translation.ZWEIMAL_KLICKEN_WAEHLT_EINEN_EINTRAG_AUS__STECKNADEL_))));
      });

      tableInfoButton.addMouseListener(new MouseListener()
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

      sortForDateBox.addActionListener(event -> {
         connector.sortTableNow();
      });

      sortForAlphabetBox.addActionListener(event -> {
         connector.sortTableNow();
      });

      sortForIndexBox.addActionListener(event -> {
         connector.sortTableNow();
      });

      moveToChapterButton.addActionListener(event -> {
         String chapterAim = (String) chapterChoiceBox.getSelectedItem();
         chapterAim = cleanTextLeaveComma(chapterAim);
         if (chapterAim.isBlank())
         {
            JOptionPane.showMessageDialog(Common.getjFrame(), translator
                  .realisticTranslate(
                        Translation.BITTE_GEBEN_SIE_EINEN_NEUEN_LEKTIONSNAMEN_EIN)
                  + "\n "
                  + translator.realisticTranslate(
                        Translation.ODER_WAEHLEN_SIE_EINE_VORHANDENE_LEKTION_AUS),
                  translator.realisticTranslate(Translation.INFORMATION),
                  JOptionPane.INFORMATION_MESSAGE);
         }
         else
         {
            connector.moveExpressionsToChapter(chapterAim);
            chapterChoiceBox.setModel(Data.getChapterComboBoxModel());
         }
      });

      moveToDatabaseButton.addActionListener(event -> {
         String databaseAim = (String) databaseChoiceBox.getSelectedItem();
         databaseAim = cleanTextLeaveComma(databaseAim);
         if (databaseAim.isBlank())
         {
            JOptionPane.showMessageDialog(Common.getjFrame(), translator
                  .realisticTranslate(
                        Translation.BITTE_GEBEN_SIE_EINEN_NEUEN_DATENBANKNAMEN_EIN)
                  + "\n "
                  + translator.realisticTranslate(
                        Translation.ODER_WAEHLEN_SIE_EINE_VORHANDENE_DATENBANK_AUS),
                  translator.realisticTranslate(Translation.INFORMATION),
                  JOptionPane.INFORMATION_MESSAGE);
         }
         else
         {
            connector.moveExpressionsToDatabase(databaseAim);
            databaseChoiceBox.setModel(Data.getOwnDatabasesComboBoxModel());
         }
      });
   }

   @Override
   public int askForMovingToDatabaseConfirmation()
   {
      return JOptionPane.showConfirmDialog(Common.getjFrame(),
            translator.realisticTranslate(
                  Translation.WOLLEN_SIE_WIRKLICH_DIE_VOKABELN_IN_EINE_ANDERE_DATENBANK_VERSCHIEBEN__),
            translator.realisticTranslate(Translation.FRAGE),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
   }

   @Override
   public int askForMovingToChapterConfirmation()
   {
      return JOptionPane.showConfirmDialog(Common.getjFrame(),
            translator.realisticTranslate(
                  Translation.WOLLEN_SIE_WIRKLICH_DIE_VOKABELN_IN_EINE_ANDERE_LEKTION_VERSCHIEBEN__),
            translator.realisticTranslate(Translation.FRAGE),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
   }

   @Override
   public int askForShredderConfirmation()
   {
      return JOptionPane.showConfirmDialog(Common.getjFrame(),
            translator.realisticTranslate(
                  Translation.WOLLEN_SIE_WIRKLICH_DEN_PAPIERKORB_LEEREN__),
            translator.realisticTranslate(Translation.FRAGE),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
   }

   @Override
   public int askForDeletionConfirmation(int number)
   {
      String message;
      if (number == 1)
      {
         message = translator.realisticTranslate(
               Translation.WOLLEN_SIE_WIRKLICH_EINEN_EINTRAG_LOESCHEN__);
      }
      else
      {
         message = translator.realisticTranslate(Translation.WOLLEN_SIE_WIRKLICH) + " "
               + number + " "
               + translator.realisticTranslate(Translation.EINTRAEGE_LOESCHEN__);
      }

      message += "\n";
      message += translator.realisticTranslate(
            Translation.HINWEIS__DIE_VOKABELN_EINGEBAUTER_DATENBANKEN);
      message += "\n";
      message += translator
            .realisticTranslate(Translation.KOENNEN_NICHT_GELOESCHT_WERDEN_);
      return JOptionPane.showConfirmDialog(Common.getjFrame(), message,
            translator.realisticTranslate(Translation.FRAGE),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
   }

   public void notifyNothingWasSelectedForDeletion()
   {
      String message = translator.realisticTranslate(Translation.ES_WURDEN_KEINE_EINTRAEGE);
      message += "\n";
      message += translator.realisticTranslate(Translation.ZUM_LOESCHEN_AUSGEWAEHLT);
      message += "\n";
      message += translator.realisticTranslate(
            Translation.HINWEIS__DIE_VOKABELN_EINGEBAUTER_DATENBANKEN);
      message += "\n";
      message += translator
            .realisticTranslate(Translation.KOENNEN_NICHT_GELOESCHT_WERDEN_);
      JOptionPane.showMessageDialog(Common.getjFrame(), message, "Nachricht",
            JOptionPane.CLOSED_OPTION);

   }

   public void loadChapters()
   {
      chapterPanel.removeAll();
      listSelectionModel = new ChapterListSelectionModel();
      addChapterListSelectionListener();
      chapterList = new ChapterList(listSelectionModel);
      chapterList.setListData(Data.getChapterArray());
      chapterList.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

      JScrollPane scroller = new JScrollPane(chapterList);
      scroller.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 300));
      scroller.setMaximumSize(
            new Dimension(Settings.getKeyboardWidth() + 50, 700));
      scroller.setBorder(BorderFactory.createEmptyBorder());

      chapterPanel.add(scroller);
      chapterPanel.validate();
      chapterPanel.repaint();
   }

   public void addChapterListSelectionListener()
   {
      listSelectionModel.addListSelectionListener(listSelectionListener);
   }

   public void removeChapterListSelectionListener()
   {
      listSelectionModel.removeListSelectionListener(listSelectionListener);
   }

   public void clearTable()
   {
      stopTableEditing();
      tablePanel.removeAll();
      table = null;
   }

   private void stopTableEditing()
   {
      if (table != null && table.isEditing())
      {
         table.getCellEditor().stopCellEditing();
      }
   }

   public void doShowTable(ExpressionTableModel tableModel)
   {
      tablePanel.removeAll();

      JTextField scrollsearchField = new JTextField();
      scrollsearchField.setOpaque(false);
      scrollsearchField.setBorder(BorderFactory.createTitledBorder(translator.realisticTranslate(Translation.SUCHWORT)));
      DataButton scrollsearchButton = new DataButton(translator.realisticTranslate(Translation.SUCHE_WORT_IN_TABELLE));
      scrollsearchButton.setOpaque(false);
      scrollsearchButton.setFont(ApplicationFonts.getButtonFont());
      DataButton scrollsearchPinButton = new DataButton(translator.realisticTranslate(Translation.NAECHSTE_AUSWAHL));
      scrollsearchPinButton
            .setIcon(new ImageIcon(ApplicationImages.getSelect()));
      scrollsearchPinButton.setOpaque(false);
      scrollsearchPinButton.setFont(ApplicationFonts.getButtonFont());

      table = new ExpressionTable(tableModel, this.getSelectedLanguage(),
            connector, true);
      tableScroller = new JScrollPane(table);
      tableScroller.setOpaque(false);
      tableScroller.getViewport().setOpaque(false);
      tableScroller.setViewportBorder(BorderFactory.createEmptyBorder());
      tableScroller.getVerticalScrollBar().setUnitIncrement(30);
      tableScroller.setMinimumSize(new Dimension(300, 300));
      tableScroller.setMaximumSize(new Dimension(500, 800));

      JPanel southWrapperPanel = new JPanel(new BorderLayout());

      JPanel scrollsearchPanel = new JPanel();
      TrainLayout scrollsearchPanelLayout = new TrainLayout(scrollsearchPanel,
            15);
      scrollsearchPanel.setLayout(scrollsearchPanelLayout);
      scrollsearchPanel.add(scrollsearchButton);
      scrollsearchPanel.add(scrollsearchPinButton);

      scrollsearchField.getDocument().addDocumentListener(new DocumentListener()
      {
         public void changedUpdate(DocumentEvent e)
         {
            setDataForDataButton();
         }

         public void removeUpdate(DocumentEvent e)
         {
            setDataForDataButton();
         }

         public void insertUpdate(DocumentEvent e)
         {
            setDataForDataButton();
         }

         public void setDataForDataButton()
         {
            if (scrollsearchField.getText().strip().length() < 2)
            {
               return;
            }
            scrollsearchButton.setData(table.findExpressionsFromPattern(
                  scrollsearchField.getText().strip()));
         }
      });

      scrollsearchButton.addActionListener(event -> {
         table.scrollToExpression(scrollsearchButton.getIndexExpression());
         scrollsearchButton.nextIndex();
      });

      String searchCommand = "search";
      KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
      scrollsearchField
            .getInputMap(JTextField.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
            .put(enter, searchCommand);
      scrollsearchField.getActionMap().put(searchCommand,
            new SearchAction(scrollsearchButton));

      scrollsearchPinButton.addActionListener(event -> {
         scrollsearchPinButton.setData(table.getSelectedExpressions(false));
         table.scrollToExpression(scrollsearchPinButton.getCurrentExpression());
         scrollsearchPinButton.nextExpression();
      });

      JPanel infotablePanel = new JPanel();
      TrainLayout infotablePanelLayout = new TrainLayout(infotablePanel, 15);
      infotablePanel.setLayout(infotablePanelLayout);
      JLabel numberOfEntriesLabel = new JLabel(
            " " + tableModel.getRowCount() + " "
                  + translator.realisticTranslate(Translation.EINTRAEGE_IN_DIESER_TABELLE));
      numberOfEntriesLabel.setFont(ApplicationFonts.getButtonFont());
      infotablePanel.add(numberOfEntriesLabel);

      southWrapperPanel.add(scrollsearchField, BorderLayout.NORTH);
      southWrapperPanel.add(scrollsearchPanel, BorderLayout.CENTER);
      southWrapperPanel.add(infotablePanel, BorderLayout.SOUTH);

      tablePanel.add(tableScroller, BorderLayout.CENTER);
      tablePanel.add(southWrapperPanel, BorderLayout.SOUTH);
      tablePanelValidateRepaint();
   }

   @Override
   public void tablePanelValidateRepaint()
   {
      tablePanel.validate();
      tablePanel.repaint();
   }

   private void initLanguageButtonGroup(ButtonGroup languageTypeGroup)
   {
      Font font = ApplicationFonts.getGermanFont(20F);
      JRadioButton german = new JRadioButton(translator.realisticTranslate(Translation.DEUTSCH));
      german.setActionCommand(Action.GERMAN_TO_HEBREW.name());
      german.setFont(font);
      german.setSelected(true);
      languageTypeGroup.add(german);
      german.setMinimumSize(new Dimension(100, 26));
      german.setMaximumSize(new Dimension(205, 32));

      JRadioButton hebrew = new JRadioButton(translator.realisticTranslate(Translation.HEBRAEISCH));
      hebrew.setActionCommand(Action.HEBREW_TO_GERMAN.name());
      hebrew.setFont(font);
      languageTypeGroup.add(hebrew);
      hebrew.setMinimumSize(new Dimension(100, 26));
      hebrew.setMaximumSize(new Dimension(205, 32));
   }

   @Override
   public void unselectExpressionKind()
   {
      Vector<Vector<ExpressionKindTableRow2>> vektorRows = expressionKindTable
            .getModel().getData();
      for (Vector<ExpressionKindTableRow2> vektorRow : vektorRows)
      {
         ExpressionKindItem expressionKind = vektorRow.get(0)
               .getExpressionKind();
         if (expressionKind.isSelected())
         {
            expressionKind.setSelected(false);
         }
      }
   }

   @Override
   public void selectTab(Tabulator tabulator)
   {
      tabbedPane.setSelectedIndex(tabulator.getIndex());
   }

   @Override
   public Language getSelectedLanguage()
   {
      return Language.valueOf(languageGroup.getSelection().getActionCommand());
   }

   @Override
   public boolean isTableNotNull()
   {
      return table != null;
   }

   @Override
   public String getTableDataToString()
   {
      return table.getTableDataToString();
   }

   @Override
   public String getSelectedTableDataToString()
   {
      return table.getSelectedTableDataToString();
   }

   @Override
   public void clearTableDataSelection()
   {
      table.clearTableDataSelection();
   }

   @Override
   public List<Expression> getInTableSelectedExpressions(
         boolean exceptDoNotChange)
   {
      return table.getSelectedExpressions(exceptDoNotChange);
   }

   @Override
   public void selectTableData()
   {
      table.selectAllExpressions();
   }

   @Override
   public void displayNoTable()
   {
      clearTable();
      tablePanelValidateRepaint();
   }

   @Override
   public Chapter getSelectedChapter()
   {
      return chapterList.getSelectedValue();
   }

   @Override
   public ExpressionKind getSelectedExpressionKind()
   {
      if (expressionKindTable.getSelectedRow() > -1)
      {
         return ((ExpressionKindTableRow2) expressionKindTable
               .getValueAt(expressionKindTable.getSelectedRow(), 0))
                     .getExpressionKind().getKind();
      }

      return null;
   }

   @Override
   public SearchType getSelectedSearchTypeGerman()
   {
      return SearchType
            .valueOf(searchTypeGroupGerman.getSelection().getActionCommand());
   }

   @Override
   public SearchType getSelectedSearchTypeHebrew()
   {
      return SearchType
            .valueOf(searchTypeGroupHebrew.getSelection().getActionCommand());
   }

   @Override
   public String getSearchPhraseGerman()
   {
      return searchPhraseGerman.getText().trim();
   }

   @Override
   public String getSearchPhraseHebrew()
   {
      return searchPhraseHebrew.getText().trim();
   }

   @Override
   public void switchSearchLanguagePanel(String actionCommand)
   {
      cardLayout.show(swapPanel, actionCommand);
   }

   @Override
   public void selectChapter(Chapter currentChapter)
   {
      chapterList.setSelectedValue(currentChapter, true);
      if (chapterList.getSelectedValue() == null)
      {
         loadChapters();
         chapterList.setSelectedValue(currentChapter, true);
      }
   }

   public JScrollPane getTableScroller()
   {
      return tableScroller;
   }

   public ExpressionTable getTable()
   {
      return table;
   }

   @Override
   public void setValues()
   {
      Data.determineReloadDatabases();
      this.loadChapters();
      this.displayNoTable();
      connector.displayTableAfterOpeningPage();
   }

   @Override
   public SortingType getSortNow()
   {
      return SortingType
            .valueOf(sortingGroup.getSelection().getActionCommand());
   }

   private String cleanTextLeaveComma(String text)
   {
      return text.replaceAll("\t", "").replaceAll("\n", "").strip();
   }
}
