package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Settings;
import vokabeltrainer.panels.start.table.multiselect.DatabaseTable;
import vokabeltrainer.panels.start.table.singleselect.DatabaseTableCopy;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Chapter;

public class StartPanel extends JPanel
{
   private static final long serialVersionUID = -4928761869820144146L;
   private Translator translator = Common.getTranslator();
   private JCheckBox modus;

   public StartPanel()
   {
      setLayout(new BorderLayout());

      JPanel north = new JPanel(new FlowLayout());
      north.setOpaque(false);
      JPanel titlePanel = new JPanel();
      titlePanel.setLayout(new BoxLayout(titlePanel, 1));
      titlePanel.setOpaque(false);

      JPanel nameWrapper = new JPanel(new FlowLayout());
      nameWrapper.setOpaque(false);
      nameWrapper.setPreferredSize(new Dimension(580, 130));
      JLabel name = new JLabel("<html>Cerebrummi</html>");
      name.setPreferredSize(new Dimension(580, 120));
      name.setForeground(Color.WHITE);
      name.setFont(ApplicationFonts.getHebrewFont(100F));
      nameWrapper.add(name);

      JPanel trainerWrapper = new JPanel(new FlowLayout());
      trainerWrapper.setOpaque(false);
      trainerWrapper.setPreferredSize(new Dimension(400, 80));
      JLabel trainer = new JLabel("<html>"
            + translator.realisticTranslate(Translation.VOKABELTRAINER)
            + "</html>");
      trainer.setPreferredSize(new Dimension(355, 70));
      trainer.setForeground(Color.WHITE);
      trainer.setFont(ApplicationFonts.getHebrewFont(55F));
      trainerWrapper.add(trainer);

      titlePanel.add(nameWrapper);
      titlePanel.add(trainerWrapper);

      north.add(titlePanel);
      add(north, BorderLayout.NORTH);
      
      JPanel centerWrapper = new JPanel();
      centerWrapper.setOpaque(false);
      centerWrapper.setBackground(ApplicationColors.getTransparent());
      centerWrapper.setLayout(new BullsEyeLayout(centerWrapper));
      
      JPanel center = new JPanel();
      center.setLayout(new TotemLayout(center, 15));
      center.setOpaque(false);
      center.setBackground(ApplicationColors.getTransparent());
      center.add(initSchabbatModus());
      center.add(initDatabaseTablePanel());
      center.add(initCopyTablePanel());
      
      centerWrapper.add(center);
      add(centerWrapper, BorderLayout.CENTER);
      
      JPanel horizontal = new JPanel();
      horizontal.setOpaque(false);
      horizontal.setLayout(new TrainLayout(horizontal));

      JPanel filler = new JPanel();
      filler.setOpaque(false);
      filler.setMinimumSize(new Dimension(600, 200));
      filler.setMaximumSize(new Dimension(800, 200));

      JLabel schalom = new JLabel(
            new ImageIcon(ApplicationImages.getLogo150()));

      schalom.setMinimumSize(new Dimension(400, 200));
      schalom.setMaximumSize(new Dimension(600, 200));

      horizontal.add(filler);
      horizontal.add(schalom);

      add(horizontal, BorderLayout.SOUTH);
   }

   private Component initSchabbatModus()
   {
      modus = new JCheckBox(Common.getTranslator().realisticTranslate(Translation.SCHABBAT_MODUS));
      modus.setFont(ApplicationFonts.getButtonFont());
      modus.setSelected(Settings.isSchabbat_modus());
      modus.setEnabled(false);
      modus.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      modus.setForeground(ApplicationColors.getWhite());
      return modus;
   }

   private Component initDatabaseTablePanel()
   {
      JPanel center = new JPanel();
      center.setLayout(new BullsEyeLayout(center));
      center.setOpaque(false);
      center.setBackground(ApplicationColors.getTransparent());
      
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JLabel databaseLabel = new JLabel(" "
            + translator.realisticTranslate(Translation.DATENBANKEN_ANSEHEN));
      databaseLabel.setFont(ApplicationFonts.getGermanFont(30F));
      databaseLabel.setForeground(ApplicationColors.getDarkGold());

      DatabaseTable databaseTable = new DatabaseTable(
            Chapter.Database.getModelAvailableDatabases(), 990);

      JScrollPane scroller = new JScrollPane(databaseTable);
      scroller.setMinimumSize(new Dimension(990, 120));
      scroller.setMaximumSize(new Dimension(990, 120));

      JLabel databaseLabel2 = new JLabel(" "
            + translator.realisticTranslate(Translation.INTERNE_DATENBANK_DURCH_DOPPELKLICK_SICHTBAR_MACHEN)
            + " => "
            + translator.realisticTranslate(Translation.DATENSAETZE_KOENNEN_NICHT_EDITIERT_WERDEN));
      databaseLabel2.setFont(ApplicationFonts.getGermanFont(16F));
      databaseLabel2.setForeground(ApplicationColors.getDarkGold());
      
      vertical.add(databaseLabel);
      vertical.add(scroller);
      vertical.add(databaseLabel2);
      
      center.add(vertical);

      return center;
   }
   
   private Component initCopyTablePanel()
   {
      JPanel center = new JPanel();
      center.setLayout(new BullsEyeLayout(center));
      center.setOpaque(false);
      center.setBackground(ApplicationColors.getTransparent());
      
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JLabel databaseLabel = new JLabel(" "
            + translator.realisticTranslate(Translation.DATENBANKEN_KOPIEREN));
      databaseLabel.setFont(ApplicationFonts.getGermanFont(30F));
      databaseLabel.setForeground(ApplicationColors.getDarkGold());

      DatabaseTableCopy databaseTable = new DatabaseTableCopy(
            Chapter.Database.getModelCopyAvailableDatabases(), 990);

      JScrollPane scroller = new JScrollPane(databaseTable);
      scroller.setMinimumSize(new Dimension(990, 120));
      scroller.setMaximumSize(new Dimension(990, 120));

      JLabel databaseLabel2 = new JLabel(" "  + translator.realisticTranslate(Translation.INTERNE_DATENBANK_DURCH_DOPPELKLICK_KOPIEREN)
            + " => "
            + translator.realisticTranslate(Translation.DATENSAETZE_KOENNEN_EDITIERT_WERDEN));
      databaseLabel2.setFont(ApplicationFonts.getGermanFont(16F));
      databaseLabel2.setForeground(ApplicationColors.getDarkGold());
      
      vertical.add(databaseLabel);
      vertical.add(scroller);
      vertical.add(databaseLabel2);
      
      center.add(vertical);

      return center;
   }
   
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      if (ApplicationImages.getImage() != null)
      {
         float factorWidth = getParent().getWidth() / 1280F;
         float factorHeight = getParent().getHeight() / 859F;
         if (factorWidth < factorHeight)
         {
            int width = (int) (1280F * factorHeight);
            int x = getParent().getWidth() / 2 - width / 2;
            g.drawImage(
                  ApplicationImages.getImage().getScaledInstance(width,
                        getParent().getHeight(), BufferedImage.SCALE_SMOOTH),
                  x, 0, this);
         }
         else
         {
            int height = (int) (859F * factorWidth);
            int y = getParent().getHeight() / 2 - height / 2;
            g.drawImage(ApplicationImages.getImage().getScaledInstance(
                  getParent().getWidth(), height, BufferedImage.SCALE_SMOOTH),
                  0, y, this);
         }

      }
   }

   public void setValues()
   {
      modus.setSelected(Settings.isSchabbat_modus());
   }
}
