package vokabeltrainer.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.resources.Blue;
import vokabeltrainer.resources.Buchstabenbilder;
import vokabeltrainer.resources.Fonts;
import vokabeltrainer.resources.Gruen;
import vokabeltrainer.resources.Images;
import vokabeltrainer.resources.LetterIcons;
import vokabeltrainer.resources.LetterIconsHandwritten;
import vokabeltrainer.resources.Sounds;

public final class Main
{
   private static String message = "Cerebrummi bitte neu starten.\nFehler: ";

   // run application with
   // -Dsun.java2d.uiScale=1 

   public static void main(String[] args)
   {	  
      CerebrummiPreferences.read();
      
      try
      {
         Fonts.read();
      }
      catch (Exception e1)
      {
         JOptionPane
               .showMessageDialog(null, message + "Schriftarten fehlen",
                     "Nachricht", JOptionPane.CLOSED_OPTION);
         System.exit(1);
      }
      
      try
      {
         Fonts.define();
      }
      catch (Exception e1)
      {
         JOptionPane
               .showMessageDialog(null, message + "Schriftarten können nicht definiert werden",
                     "Nachricht", JOptionPane.CLOSED_OPTION);
         System.exit(1);
      }

      try
      {
         Images.read();
      }
      catch (Exception e1)
      {
         JOptionPane
               .showMessageDialog(null, message + "Bilder fehlen", "Nachricht",
                     JOptionPane.CLOSED_OPTION);
         System.exit(1);
      }

      try
      {
         LetterIcons.readNikud();
      }
      catch (Exception e1)
      {
         JOptionPane
               .showMessageDialog(null,
                     message + "Buchstaben Nikud Icons fehlen", "Nachricht",
                     JOptionPane.CLOSED_OPTION);
         System.exit(1);
      }

      try
      {
         LetterIconsHandwritten.readNikud();
      }
      catch (Exception e1)
      {
         JOptionPane
               .showMessageDialog(null,
                     message + "Buchstaben Nikud Handwritten Icons fehlen",
                     "Nachricht", JOptionPane.CLOSED_OPTION);
         System.exit(1);
      }

      try
      {
         Buchstabenbilder.read();
      }
      catch (Exception e)
      {
         JOptionPane
               .showMessageDialog(null, message + "Buchstabenbilder fehlen",
                     "Nachricht", JOptionPane.CLOSED_OPTION);
         e.printStackTrace();
         System.exit(1);
      }

      try
      {
         Sounds.read();
      }
      catch (Exception e1)
      {
         JOptionPane
               .showMessageDialog(null, message + "Geräusche fehlen",
                     "Nachricht", JOptionPane.CLOSED_OPTION);
         System.exit(1);
      }
      
      Translator translator = Common.getTranslator();
      
      Data.initDataBase();
      ImageData.initImageDataBase();

      try
      {
         NimbusLookAndFeel nimbus = new NimbusLookAndFeel();
         UIManager.setLookAndFeel(nimbus);
         UIManager
               .put("control", Color.WHITE);
         UIManager.put("nimbusBlueGrey", ApplicationColors.getLightGrayGold());
         UIManager.put("nimbusBase", ApplicationColors.getDarkGold());
         UIManager.put("textForeground", Color.BLACK);
         UIManager.put("nimbusFocus", ApplicationColors.getSunflowerYellow());
         UIManager
               .put("ToolBar:Button.contentMargins", new Insets(5, 15, 5, 15));
         UIManager
               .put("TextField.background", ApplicationColors.getLightYellow());
         UIManager.put("ComboBox.forceOpaque", false);
         UIManager.put("TitledBorder.border", new Insets(10, 10, 10, 10));
         UIManager.put("TitledBorder.position", TitledBorder.ABOVE_BOTTOM);
         UIManager.put("TitledBorder.font", ApplicationFonts.getGermanFont(16F));
         UIManager.put("TitledBorder.titleColor", Color.GRAY);
         UIManager.put("Table.opaque", false);
         UIManager.put("List.opaque", false);
         UIManager.put("Table.cellRenderer", false);
         UIManager.put("OptionPane.buttonFont", ApplicationFonts.getGermanFont(16F));

         UIManager.put("OptionPane.cancelButtonText", translator.realisticTranslate(Translation.ABBRECHEN));
         UIManager.put("OptionPane.yesButtonText", translator.realisticTranslate(Translation.JA));
         UIManager.put("OptionPane.noButtonText", translator.realisticTranslate(Translation.NEIN));
         UIManager.put("OptionPane.titleText", translator.realisticTranslate(Translation.BILD_LOESCHEN));
         
         UIManager.put("FileChooser.openButtonText", translator.realisticTranslate(Translation.OEFFNEN));
         UIManager.put("FileChooser.cancelButtonText", translator.realisticTranslate(Translation.ABBRECHEN));
         UIManager.put("FileChooser.saveButtonText", translator.realisticTranslate(Translation.SPEICHERN));
         UIManager.put("FileChooser.cancelButtonToolTipText", translator.realisticTranslate(Translation.ABBRECHEN_DER_AUSWAHL));
         UIManager
               .put("FileChooser.saveButtonToolTipText",
                     translator.realisticTranslate(Translation.AUSGEWAEHLTE_DATEI_SPEICHERN));
         UIManager
               .put("FileChooser.openButtonToolTipText",
                     "Ausgewählte Datei öffnen");
         UIManager.put("FileChooser.upFolderToolTipText", "Eine Ebene höher");
         UIManager.put("FileChooser.homeFolderToolTipText", "Home");
         UIManager
               .put("FileChooser.newFolderToolTipText",
                     "Neuen Ordner erstellen");
         UIManager.put("FileChooser.listViewButtonToolTipText", "Liste");
         UIManager.put("FileChooser.detailsViewButtonToolTipText", "Details");
         UIManager.put("FileChooser.lookInLabelText", "Suchen in:");
         UIManager.put("FileChooser.fileNameLabelText", "Dateiname:");
         UIManager.put("FileChooser.filesOfTypeLabelText", "Dateityp:");
         UIManager
               .put("FileChooser.acceptAllFileFilterText",
                     "Alle Dateien (*.*)");
         UIManager.put("FileChooser.folderNameLabelText", "Ordnername:");
         UIManager.put("FileChooser.openDialogTitleText", translator.realisticTranslate(Translation.OEFFNEN));
         UIManager.put("FileChooser.saveDialogTitleText", translator.realisticTranslate(Translation.SPEICHERN));
         UIManager.put("OptionPane.background", ApplicationColors.getWhite());
      }
      catch (UnsupportedLookAndFeelException e3)
      {
         // nothing
      }

      SwingUtilities.invokeLater(() -> {
         javax.swing.JFrame window = new JFrame();
         window.setResizable(true);
         window.setIconImage(ApplicationImages.getLogo());
         vokabeltrainer.common.Common.setjFrame(window);
         window
               .setTitle(Settings.getWindowTitle()
                     + " "
                     + Settings.getVersion());
         window.setFont(ApplicationFonts.getGermanFont(14F));
         ToolTipManager.sharedInstance().setDismissDelay(8000);
         ToolTipManager.sharedInstance().setInitialDelay(1000);
         vokabeltrainer.common.Common
               .setMainJPanel(
                     new vokabeltrainer.common.MainController().getMainView());
         window
               .getContentPane()
               .add(new JScrollPane(
                     vokabeltrainer.common.Common.getMainJPanel()));
         window.setJMenuBar(Common.getMainJPanel().getMenuBar());
         window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
         window.setSize(new Dimension(1600, 1020));
         // window.setExtendedState(JFrame. MAXIMIZED_BOTH);
         window.setLocationRelativeTo(null);
         window.setVisible(true);
      });

      new SwingWorker<Void, Void>()
      {

         @Override
         protected Void doInBackground() throws Exception
         {
            try
            {
               Blue.read();
            }
            catch (Exception e)
            {
               JOptionPane
                     .showMessageDialog(null, message + "Blaue Bilder fehlen",
                           "Nachricht", JOptionPane.CLOSED_OPTION);
               System.exit(1);
            }

            try
            {
               Gruen.read();
            }
            catch (Exception e)
            {
               JOptionPane
                     .showMessageDialog(null, message + "Grüne Bilder fehlen",
                           "Nachricht", JOptionPane.CLOSED_OPTION);
               System.exit(1);
            }
            return null;
         }

      }.execute();
   }

   public static void initDatabase()
   {
      Data.initDataBase();
   }
   
   public static void resetMenuBar()
   {
      SwingUtilities.invokeLater(() -> { 
         vokabeltrainer.common.Common.getjFrame().setJMenuBar(Common.getMainJPanel().getMenuBar());
         vokabeltrainer.common.Common.getjFrame().validate();
         vokabeltrainer.common.Common.getjFrame().repaint();
      });
   }
}
