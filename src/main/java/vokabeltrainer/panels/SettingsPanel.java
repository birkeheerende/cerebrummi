package vokabeltrainer.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.StringJoiner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.PathAndFile;
import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.ApplicationSound;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.ImportExpressions;
import vokabeltrainer.common.Main;
import vokabeltrainer.common.SaveExpressions;
import vokabeltrainer.common.Settings;
import vokabeltrainer.panels.settings.InputDatabaseNameDialog;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.resources.html.Nachweise;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class SettingsPanel extends BackgroundPanelTiled
{
   private static final int WIDTH = 330;
   private static final long serialVersionUID = 1162776912036921483L;
   private JSlider soundslider;
   private JButton soundButton;
   private JButton waveSoundButton;
   private JButton clappingSoundButton;
   private JButton splotchSoundButton;
   private JButton shredderSoundButton;
   private JButton folderChooserButtonWithoutSaving;
   private JTextArea folderLabel;
   private JButton importButton;
   private JButton exportButton;
   private JButton exportSelectedButton;
   private JButton exportDatabaseButton;
   private JButton deleteDatabaseButton;
   private Translator translator = Common.getTranslator();
   private JCheckBox modus;

   public SettingsPanel()
   {
      setLayout(new BullsEyeLayout(this));

      JTabbedPane tabbedPane = new JTabbedPane();
      tabbedPane.setOpaque(false);
      tabbedPane.setFont(ApplicationFonts.getButtonFont());
      tabbedPane.addTab(translator.realisticTranslate(
            Translation.EINSTELLUNGEN_UND_SERVICE), initSettingsTab());
      
      try
      {
         tabbedPane.addTab(translator.realisticTranslate(Translation.INFO),
               initImpressumTab());
      }
      catch (IOException e1)
      {
         // nothing
      }
      try
      {
         tabbedPane.addTab(translator.realisticTranslate(Translation.NACHWEISE),
               initLicencingTab());
      }
      catch (IOException e)
      {
         // nothing
      }
      
      tabbedPane.addTab(translator.realisticTranslate(
            Translation.SICHERHEITSKOPIEN), initBackupsTab());
      
      tabbedPane.addTab(translator.realisticTranslate(
            Translation.SCHABBAT_MODUS), initSchabbatTab());

      add(tabbedPane);

      initController();
   }

   private Component initSchabbatTab()
   {
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);
      panel.setBackground(ApplicationColors.getShadyBlue());
      
      modus = new JCheckBox(Common.getTranslator().realisticTranslate(Translation.SCHABBAT_MODUS));
      modus.setFont(ApplicationFonts.getButtonFont());
      modus.setForeground(ApplicationColors.getWhite());
      modus.setSelected(Settings.isSchabbat_modus());
      if(Common.isSchabbat())
      {
         modus.setEnabled(false);
      }
      
      panel.add(modus);
      return panel;
   }

   private Component initBackupsTab()
   {
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);
      
      panel.add(new JLabel("work in progress"));
      return panel;
   }

   private Component initLicencingTab() throws IOException
   {
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);

      JEditorPane editorPane = new JEditorPane();
      editorPane.setFont(ApplicationFonts.getButtonFont());
      editorPane.setContentType("text/html");
      editorPane.setPage(Nachweise.class.getResource("Nachweise"));
      editorPane.setEditable(false);
      editorPane.addHyperlinkListener(event -> {
         if (event.getEventType() == EventType.ACTIVATED
               && event.getInputEvent() instanceof MouseEvent)
         {
            MouseEvent mouseEvent = (MouseEvent) event.getInputEvent();
            if (mouseEvent.getClickCount() == 1)
            {
               if (!Desktop.isDesktopSupported())
               {
                  return;
               }
               try
               {
                  Desktop.getDesktop().browse(event.getURL().toURI());
               }
               catch (Exception e)
               {
                  // nothing
               }
            }
         }

      });

      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JScrollPane scroller = new JScrollPane(editorPane);
      scroller.setMinimumSize(new Dimension(500, 300));
      scroller.setMaximumSize(new Dimension(1000, 500));

      vertical.add(scroller);
      panel.add(vertical);

      return panel;
   }

   private Component initImpressumTab() throws IOException
   {
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);

      JEditorPane editorPane = new JEditorPane();
      editorPane.setFont(ApplicationFonts.getButtonFont());
      editorPane.setContentType("text/html");
      editorPane.setPage(Nachweise.class.getResource("lizenz.txt"));
      editorPane.setEditable(false);
      editorPane.addHyperlinkListener(event -> {
         if (event.getEventType() == EventType.ACTIVATED
               && event.getInputEvent() instanceof MouseEvent)
         {
            MouseEvent mouseEvent = (MouseEvent) event.getInputEvent();
            if (mouseEvent.getClickCount() == 1)
            {
               if (!Desktop.isDesktopSupported())
               {
                  return;
               }
               try
               {
                  Desktop.getDesktop().browse(event.getURL().toURI());
               }
               catch (Exception e)
               {
                  // nothing
               }
            }
         }

      });

      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JScrollPane scroller = new JScrollPane(editorPane);
      scroller.setMinimumSize(new Dimension(500, 300));
      scroller.setMaximumSize(new Dimension(1000, 500));

      vertical.add(scroller);
      panel.add(vertical);

      return panel;
   }

   private Component initSettingsTab()
   {
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);

      JPanel horizontal = new JPanel();
      TrainLayout horizontalLayout = new TrainLayout(horizontal, 60);
      horizontal.setLayout(horizontalLayout);

      JPanel vertical1 = new JPanel();
      TotemLayout vertical1Layout = new TotemLayout(vertical1, 60);
      vertical1.setLayout(vertical1Layout);

      JPanel vertical2 = new JPanel();
      TotemLayout vertical2Layout = new TotemLayout(vertical2, 60);
      vertical2.setLayout(vertical2Layout);

      JPanel vertical3 = new JPanel();
      TotemLayout vertical3Layout = new TotemLayout(vertical3, 60);
      vertical3.setLayout(vertical3Layout);

      vertical1.add(initSoundPanel());

      vertical2.add(initSavePanel());
      vertical2.add(initDeletePanel());
      
      vertical3.add(initImportPanel());
      vertical3.add(initExportPanel());

      horizontal.add(vertical1);
      horizontal.add(vertical2);
      horizontal.add(vertical3);

      panel.add(horizontal);

      return panel;
   }

   private Component initDeletePanel()
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);
      
      JLabel deleteLabel = new JLabel(
            translator.realisticTranslate(Translation.LOESCHEN));
      deleteLabel.setFont(ApplicationFonts.getGermanFont(30F));
      deleteLabel.setForeground(ApplicationColors.getGold());

      deleteDatabaseButton = new JButton(translator
            .realisticTranslate(Translation.EIGENE_DATENBANK_LOESCHEN));
      deleteDatabaseButton.setFont(ApplicationFonts.getButtonFont());
      deleteDatabaseButton.setToolTipText(translator.realisticTranslate(
            Translation.VERSCHIEBT_ALLE_VOKABELN_EINER_DATENBANK_IN_DEN_PAPIERKORB_));
      deleteDatabaseButton.setMinimumSize(new Dimension(WIDTH, 30));
      deleteDatabaseButton.setMaximumSize(new Dimension(WIDTH, 30));
      
      vertical.add(deleteLabel);
      vertical.add(deleteDatabaseButton);
      
      return vertical;
   }

   private Component initSavePanel()
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JLabel saverLabel = new JLabel(
            translator.realisticTranslate(Translation.SPEICHERORT));
      saverLabel.setFont(ApplicationFonts.getGermanFont(30F));
      saverLabel.setForeground(ApplicationColors.getGold());

      folderLabel = new JTextArea(Settings.getExpressionPath());
      folderLabel.setFont(ApplicationFonts.getButtonFont());
      folderLabel.setEditable(false);
      folderLabel.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(Translation.ORDNER)));
      folderLabel.setMinimumSize(new Dimension(WIDTH, 100));
      folderLabel.setMaximumSize(new Dimension(WIDTH, 100));

      folderChooserButtonWithoutSaving = new JButton(
            translator.realisticTranslate(Translation.AENDERN));
      folderChooserButtonWithoutSaving.setFont(ApplicationFonts.getButtonFont());
      folderChooserButtonWithoutSaving.setToolTipText(translator
            .realisticTranslate(
                  Translation.LAEDT_ALLE_VOKABELN__DIE_AM_NEUEN_ORT_SCHON_VORHANDEN_SIND_)
            + " " + translator.realisticTranslate(
                  Translation.BELAESST_DIE_AKTUELLEN_VOKABELN_AM_ALTEN_ORT));

     

      vertical.add(saverLabel);
      vertical.add(folderLabel);
      vertical.add(folderChooserButtonWithoutSaving);

      return vertical;
   }

   private Component initExportPanel()
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      exportButton = new JButton(translator
            .realisticTranslate(Translation.ALLE_VOKABELN_EXPORTIEREN));
      exportButton.setFont(ApplicationFonts.getButtonFont());
      exportButton.setToolTipText(
            translator.realisticTranslate(Translation.ALLE_VOKABELN_EXPORTIEREN)
                  + " " + translator.realisticTranslate(
                        Translation.IM_CSV_FORMAT_ALS_ZIP_DATEI));

      exportSelectedButton = new JButton(translator
            .realisticTranslate(Translation.MARKIERTE_VOKABELN_EXPORTIEREN));
      exportSelectedButton.setFont(ApplicationFonts.getButtonFont());
      exportSelectedButton.setToolTipText(translator
            .realisticTranslate(Translation.MARKIERTE_VOKABELN_EXPORTIEREN)
            + " " + translator
                  .realisticTranslate(Translation.IM_CSV_FORMAT_ALS_ZIP_DATEI));

      exportDatabaseButton = new JButton(translator.realisticTranslate(
            Translation.VOKABELN_EINER_DATENBANK_EXPORTIEREN));
      exportDatabaseButton.setFont(ApplicationFonts.getButtonFont());
      exportDatabaseButton.setToolTipText(translator.realisticTranslate(
            Translation.VOKABELN_EINER_DATENBANK_EXPORTIEREN) + " "
            + translator
                  .realisticTranslate(Translation.IM_CSV_FORMAT_ALS_ZIP_DATEI));
      exportDatabaseButton.setMinimumSize(new Dimension(WIDTH, 30));
      exportDatabaseButton.setMaximumSize(new Dimension(WIDTH, 30));

      JLabel exportLabel = new JLabel(
            translator.realisticTranslate(Translation.EXPORT));
      exportLabel.setFont(ApplicationFonts.getGermanFont(30F));
      exportLabel.setForeground(ApplicationColors.getGold());

      JLabel exportExplanation = new JLabel(" " + translator.realisticTranslate(
            Translation.KEIN_EXPORT_VON_INTERNEN_DATENBANKEN_MOEGLICH));
      exportExplanation.setFont(ApplicationFonts.getGermanFont(14F));
      exportExplanation.setForeground(ApplicationColors.getGold());

      vertical.add(exportLabel);
      vertical.add(exportButton);
      vertical.add(exportSelectedButton);
      vertical.add(exportDatabaseButton);
      vertical.add(exportExplanation);

      return vertical;
   }

   private Component initImportPanel()
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JLabel importLabel = new JLabel(
            translator.realisticTranslate(Translation.IMPORT));
      importLabel.setFont(ApplicationFonts.getGermanFont(30F));
      importLabel.setForeground(ApplicationColors.getGold());

      importButton = new JButton(
            translator.realisticTranslate(Translation.DATENBANK_IMPORTIEREN));
      importButton.setFont(ApplicationFonts.getButtonFont());
      importButton.setMinimumSize(new Dimension(WIDTH, 30));
      importButton.setMaximumSize(new Dimension(WIDTH, 30));

      vertical.add(importLabel);
      vertical.add(importButton);

      return vertical;
   }

   public void setValues()
   {
      soundButton.setIcon(new ImageIcon(Settings.getSound()));
      soundslider.setValue((int) Settings.getVolume());
   }

   private Component initSoundPanel()
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JLabel soundLabel = new JLabel(
            translator.realisticTranslate(Translation.LAUTSTAERKE));
      soundLabel.setFont(ApplicationFonts.getGermanFont(30F));
      soundLabel.setForeground(ApplicationColors.getDarkGold());

      soundButton = new JButton(new ImageIcon(Settings.getSound()));
      soundButton.setBorder(BorderFactory.createEmptyBorder());
      soundButton.setOpaque(false);
      soundButton.setBackground(new Color(0, 0, 0, 0));

      soundslider = new JSlider();
      soundslider.setMinimum(-30);
      soundslider.setMaximum(5);
      soundslider.setValue((int) Settings.getVolume());
      soundslider.setMajorTickSpacing(5);
      soundslider.setMinorTickSpacing(1);
      soundslider.setPaintTicks(true);
      soundslider.setPaintLabels(true);
      soundslider.setSnapToTicks(true);
      soundslider.setMinimumSize(new Dimension(WIDTH, 60));
      soundslider.setMaximumSize(new Dimension(WIDTH, 60));

      waveSoundButton = new JButton(
            translator.realisticTranslate(Translation.WELLEN));
      waveSoundButton.setFont(ApplicationFonts.getButtonFont());

      clappingSoundButton = new JButton(
            translator.realisticTranslate(Translation.APPLAUS));
      clappingSoundButton.setFont(ApplicationFonts.getButtonFont());

      splotchSoundButton = new JButton(
            translator.realisticTranslate(Translation.TINTENSPRITZER));
      splotchSoundButton.setFont(ApplicationFonts.getButtonFont());

      shredderSoundButton = new JButton(
            translator.realisticTranslate(Translation.AKTENVERNICHTER));
      shredderSoundButton.setFont(ApplicationFonts.getButtonFont());

      vertical.add(soundLabel);
      vertical.add(soundButton);
      vertical.add(soundslider);
      vertical.add(waveSoundButton);
      vertical.add(clappingSoundButton);
      vertical.add(splotchSoundButton);
      vertical.add(shredderSoundButton);

      return vertical;
   }

   private void initController()
   {
      soundslider.addChangeListener(event -> {
         if (!soundslider.getValueIsAdjusting())
         {
            Settings.setVolume(soundslider.getValue());
         }
      });

      soundButton.addActionListener(event -> {
         Settings.toggleSoundOnOff();
         soundButton.setIcon(new ImageIcon(Settings.getSound()));
      });

      waveSoundButton.addActionListener(event -> {
         if (Settings.isSoundOn())
         {
            try
            {
               Clip clip = AudioSystem.getClip();
               clip.open(ApplicationSound.getWaveSound());
               FloatControl volume = (FloatControl) clip
                     .getControl(FloatControl.Type.MASTER_GAIN);
               volume.setValue(Settings.getVolume());
               clip.start();
            }
            catch (LineUnavailableException | IOException e)
            {
               // nothing
            }
         }
      });

      clappingSoundButton.addActionListener(event -> {
         if (Settings.isSoundOn())
         {
            try
            {
               Clip clip = AudioSystem.getClip();
               clip.open(ApplicationSound.getClappingSound());
               FloatControl volume = (FloatControl) clip
                     .getControl(FloatControl.Type.MASTER_GAIN);
               volume.setValue(Settings.getVolume());
               clip.start();
            }
            catch (LineUnavailableException | IOException e)
            {
               // nothing
            }
         }
      });

      splotchSoundButton.addActionListener(event -> {
         if (Settings.isSoundOn())
         {
            try
            {
               Clip clip = AudioSystem.getClip();
               clip.open(ApplicationSound.getSplotchSound());
               FloatControl volume = (FloatControl) clip
                     .getControl(FloatControl.Type.MASTER_GAIN);
               volume.setValue(Settings.getVolume());
               clip.start();
            }
            catch (LineUnavailableException | IOException e)
            {
               // nothing
            }
         }
      });

      shredderSoundButton.addActionListener(event -> {
         if (Settings.isSoundOn())
         {
            try
            {
               Clip clip = AudioSystem.getClip();
               clip.open(ApplicationSound.getShredderSound());
               FloatControl volume = (FloatControl) clip
                     .getControl(FloatControl.Type.MASTER_GAIN);
               volume.setValue(Settings.getVolume());
               clip.start();
            }
            catch (LineUnavailableException | IOException e)
            {
               // nothing
            }
         }
      });

      folderChooserButtonWithoutSaving.addActionListener(event -> {

         String pathOfFolder = choosesFolderForSave();
         if (pathOfFolder != null)
         {
            Settings.setChoosenExpressionPath(pathOfFolder);
            this.folderLabel.setText(Settings.getExpressionPath());
            Main.initDatabase();
         }

      });

      importButton.addActionListener(event -> {

         InputDatabaseNameDialog dialog = new InputDatabaseNameDialog(
               translator.realisticTranslate(Translation.IMPORT));
         dialog.setVisible(true);

         final String databaseName;
         final boolean overwriteDatabaseNames;

         if (!dialog.isStartImportOrExport())
         {
            dialog.dispose();
            return;
         }
         else
         {
            databaseName = dialog.getDatabaseName();
            overwriteDatabaseNames = dialog.isOverwrite();
            dialog.dispose();
         }

         String pathOfFolderOrFile = choosesFolderOrZipFileForOpen();
         if (pathOfFolderOrFile != null)
         {
            new SwingWorker<Void, Void>()
            {
               @Override
               protected Void doInBackground() throws Exception
               {
                  ImportExpressions importer = new ImportExpressions();
                  if (importer.importExpressions(databaseName,
                        overwriteDatabaseNames, pathOfFolderOrFile))
                  {
                     SaveExpressions saver = new SaveExpressions();
                     saver.save();
                  }

                  return null;
               }

            }.execute();
         }
      });

      exportButton.addActionListener(event -> {

         InputDatabaseNameDialog dialog = new InputDatabaseNameDialog(translator
               .realisticTranslate(Translation.EXPORT_ALLER_VOKABELN));
         dialog.setVisible(true);

         final String databaseName;
         final boolean overwriteDatabaseNames;

         if (!dialog.isStartImportOrExport())
         {
            dialog.dispose();
            return;
         }
         else
         {
            databaseName = dialog.getDatabaseName();
            overwriteDatabaseNames = dialog.isOverwrite();
            dialog.dispose();
         }

         PathAndFile pathOfFolder = choosesFolderAndFileForSave();
         if (pathOfFolder != null)
         {
            new SwingWorker<Void, Void>()
            {

               @Override
               protected Void doInBackground() throws Exception
               {
                  SaveExpressions saver = new SaveExpressions(pathOfFolder);
                  saver.export(databaseName, overwriteDatabaseNames);

                  return null;
               }

            }.execute();
         }
      });

      this.exportSelectedButton.addActionListener(event -> {
         InputDatabaseNameDialog dialog = new InputDatabaseNameDialog(translator
               .realisticTranslate(Translation.EXPORT_MARKIERTER_VOKABELN));
         dialog.setVisible(true);

         final String databaseName;
         final boolean overwriteDatabaseNames;

         if (!dialog.isStartImportOrExport())
         {
            dialog.dispose();
            return;
         }
         else
         {
            databaseName = dialog.getDatabaseName();
            overwriteDatabaseNames = dialog.isOverwrite();
            dialog.dispose();
         }

         PathAndFile pathOfFolder = choosesFolderAndFileForSave();
         if (pathOfFolder != null)
         {
            new SwingWorker<Void, Void>()
            {

               @Override
               protected Void doInBackground() throws Exception
               {
                  SaveExpressions saver = new SaveExpressions(pathOfFolder);
                  saver.export(databaseName, overwriteDatabaseNames, true);

                  return null;
               }

            }.execute();
         }
      });

      exportDatabaseButton.addActionListener(event -> {

         String databaseChoosen = (String) JOptionPane.showInputDialog(
               Common.getMainJPanel(),
               translator.realisticTranslate(
                     Translation.WAEHLEN_SIE_EINE_DATENBANK_FUER_DEN_EXPORT_AUS_),
               translator.realisticTranslate(Translation.AUSWAHL),
               JOptionPane.QUESTION_MESSAGE,
               new ImageIcon(ApplicationImages.getLogo24()),
               Data.getAllOwnDistinctDatabaseDescriptions(false), null);

         if (databaseChoosen == null)
         {
            return;
         }

         InputDatabaseNameDialog dialog = new InputDatabaseNameDialog(translator
               .realisticTranslate(Translation.EXPORT_EINER_DATENBANK));
         dialog.setVisible(true);

         final String databaseName;
         final boolean overwriteDatabaseNames;

         if (!dialog.isStartImportOrExport())
         {
            dialog.dispose();
            return;
         }
         else
         {
            databaseName = dialog.getDatabaseName();
            overwriteDatabaseNames = dialog.isOverwrite();
            dialog.dispose();
         }

         PathAndFile pathOfFolder = choosesFolderAndFileForSave();
         if (pathOfFolder != null)
         {
            new SwingWorker<Void, Void>()
            {

               @Override
               protected Void doInBackground() throws Exception
               {
                  SaveExpressions saver = new SaveExpressions(pathOfFolder);
                  saver.export(databaseName, overwriteDatabaseNames,
                        databaseChoosen);
                  return null;
               }

            }.execute();
         }
      });

      this.deleteDatabaseButton.addActionListener(event -> {
         String databaseChoosen = (String) JOptionPane.showInputDialog(
               Common.getMainJPanel(),
               translator.realisticTranslate(
                     Translation.WAEHLEN_SIE_EINE_DATENBANK_ZUM_LOESCHEN_AUS),
               translator.realisticTranslate(
                     Translation.DATENBANK_IN_DEN_PAPIERKORB),
               JOptionPane.QUESTION_MESSAGE,
               new ImageIcon(ApplicationImages.getLogo24()),
               Data.getAllOwnDistinctDatabaseDescriptions(false), null);

         if (databaseChoosen == null)
         {
            return;
         }

         if (JOptionPane.showConfirmDialog(Common.getjFrame(),
               translator.realisticTranslate(
                     Translation.WOLLEN_SIE_WIRKLICH_DIE_VOKABELN_VON__)
                     + databaseChoosen
                     + translator.realisticTranslate(Translation.__LOESCHEN_),
               translator.realisticTranslate(Translation.FRAGE),
               JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) != 0)
         {
            return;
         }

         new SwingWorker<Void, Void>()
         {

            @Override
            protected Void doInBackground() throws Exception
            {
               Data.deleteExpressionsOfDatabase(databaseChoosen);
               new SaveExpressions().save();
               return null;
            }

         }.execute();
      });
      
      this.modus.addActionListener(event -> {
         if(!Common.isSchabbat())
         {
            Settings.setSchabbat_modus(modus.isSelected());
         }
      });
   }

   private boolean testIfFolderExists(String path)
   {
      File folder = new File(path);
      return folder.exists() && folder.isDirectory();
   }

   private boolean testIfZipFileExists(String path)
   {
      File folder = new File(path);
      return folder.exists() && folder.isFile()
            && (path.endsWith(".zip") || path.endsWith(".ZIP"));
   }

   private boolean testIfFileExists(String path)
   {
      File folder = new File(path);
      return folder.exists() && folder.isFile();
   }

   private String choosesFolderForSave()
   {
      JFileChooser folderChooser = new JFileChooser(
            Settings.getExpressionPath());
      folderChooser.setAcceptAllFileFilterUsed(false);
      folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

      int choice = folderChooser.showSaveDialog(this);

      if (JFileChooser.APPROVE_OPTION == choice)
      {
         String result = folderChooser.getSelectedFile().getPath();
         if (!testIfFolderExists(folderChooser.getSelectedFile().getPath()))
         {
            JOptionPane.showMessageDialog(this, translator.realisticTranslate(
                  Translation.DER_GEWAEHLTE_ORDNER_EXISTIERT_NICHT_) + "\n"
                  + result + "\n"
                  + translator.realisticTranslate(
                        Translation.BITTE_WAEHLEN_SIE_EINEN_EXISTIERENDEN_ORDNER_)
                  + "\n" + translator.realisticTranslate(Translation.DANKE_),
                  translator.realisticTranslate(Translation.NACHRICHT),
                  JOptionPane.CLOSED_OPTION);
            return null;
         }
         return result;
      }
      return null;
   }

   private PathAndFile choosesFolderAndFileForSave()
   {
      JFileChooser folderChooser = new JFileChooser(
            Settings.getExpressionPath());
      folderChooser.setAcceptAllFileFilterUsed(false);
      folderChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

      int choice = folderChooser.showSaveDialog(this);

      if (JFileChooser.APPROVE_OPTION == choice)
      {
         String splitter = "\\" + File.separator;
         String[] foldersAndFile = folderChooser.getSelectedFile().getPath()
               .split(splitter);
         PathAndFile pathAndFile = new PathAndFile();
         StringJoiner joiner = new StringJoiner(splitter);
         for (int i = 0; i < foldersAndFile.length; i++)
         {
            if (i == foldersAndFile.length - 1)
            {
               pathAndFile.setFile(foldersAndFile[i]);
            }
            else
            {
               joiner.add(foldersAndFile[i]);
            }
         }
         pathAndFile.setPath(joiner.toString());

         if (!testIfFolderExists(pathAndFile.getPath()))
         {
            JOptionPane.showMessageDialog(this, translator.realisticTranslate(
                  Translation.DER_GEWAEHLTE_ORDNER_EXISTIERT_NICHT_) + "\n"
                  + pathAndFile.getPath() + "\n"
                  + translator.realisticTranslate(
                        Translation.BITTE_WAEHLEN_SIE_EINEN_EXISTIERENDEN_ORDNER_)
                  + "\n" + translator.realisticTranslate(Translation.DANKE_),
                  translator.realisticTranslate(Translation.NACHRICHT),
                  JOptionPane.CLOSED_OPTION);
            return null;
         }

         if (testIfFileExists(pathAndFile.getPathFile()))
         {
            int answer = JOptionPane.showConfirmDialog(this,
                  translator.realisticTranslate(
                        Translation.DIE_DATEI_EXISTIERT_SCHON)
                        + "\n"
                        + translator.realisticTranslate(
                              Translation.SOLL_SIE_UEBERSCHRIEBEN_WERDEN_),
                  translator.realisticTranslate(Translation.FRAGE),
                  JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (JOptionPane.OK_OPTION != answer)
            {
               return null;
            }
         }

         if (testIfFileExists(pathAndFile.getPathFile() + ".zip"))
         {
            int answer = JOptionPane.showConfirmDialog(this,
                  translator.realisticTranslate(
                        Translation.DIE_DATEI_EXISTIERT_SCHON)
                        + "\n"
                        + translator.realisticTranslate(
                              Translation.SOLL_SIE_UEBERSCHRIEBEN_WERDEN_),
                  translator.realisticTranslate(Translation.FRAGE),
                  JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (JOptionPane.OK_OPTION != answer)
            {
               return null;
            }
         }

         if (testIfFileExists(pathAndFile.getPathFile() + ".ZIP"))
         {
            int answer = JOptionPane.showConfirmDialog(this,
                  translator.realisticTranslate(
                        Translation.DIE_DATEI_EXISTIERT_SCHON)
                        + "\n"
                        + translator.realisticTranslate(
                              Translation.SOLL_SIE_UEBERSCHRIEBEN_WERDEN_),
                  translator.realisticTranslate(Translation.FRAGE),
                  JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (JOptionPane.OK_OPTION != answer)
            {
               return null;
            }
         }
         return pathAndFile;
      }
      return null;
   }

   private String choosesFolderOrZipFileForOpen()
   {
      JFileChooser folderChooser = new JFileChooser(
            Settings.getExpressionPath());
      folderChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
      FileFilter filter = new FileNameExtensionFilter("zip-Datei", "zip");
      folderChooser.setAcceptAllFileFilterUsed(false);
      folderChooser.addChoosableFileFilter(filter);

      int choice = folderChooser.showOpenDialog(this);
      if (JFileChooser.APPROVE_OPTION == choice)
      {
         String result = folderChooser.getSelectedFile().getPath();
         if (testIfFolderExists(result))
         {
            return result;
         }
         else if (testIfZipFileExists(result))
         {
            return result;
         }
         else
         {
            JOptionPane.showMessageDialog(this, translator.realisticTranslate(
                  Translation.DER_GEWAEHLTE_ORDNER_DIE_DATEI_EXISTIERT_NICHT_)
                  + "\n"
                  + translator.realisticTranslate(
                        Translation.ODER_DIE_DATEI_IST_KEINE_ZIP_DATEI)
                  + "\n" + folderChooser.getSelectedFile().getPath() + "\n"
                  + translator.realisticTranslate(
                        Translation.BITTE_WAEHLEN_SIE_EINEN_EXISTIERENDEN_ORDNER_BZW__ZIP_DATEI_)
                  + "\n" + translator.realisticTranslate(Translation.DANKE_),
                  translator.realisticTranslate(Translation.NACHRICHT),
                  JOptionPane.CLOSED_OPTION);
            return null;
         }
      }

      return null;
   }
}
