package vokabeltrainer.panels.success;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Settings;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class InformationTab extends JPanel
{
   private static final long serialVersionUID = -700729868879651952L;
   private Translator translator = Common.getTranslator();
   private JCheckBox morgenCheck;
   private JCheckBox tage_2_Check;
   private JCheckBox tage_5_Check;
   private JCheckBox tage_11_Check;
   private JCheckBox tage_19_Check;
   private JCheckBox monate_1_Check;
   private JCheckBox monate_2_Check;
   private JCheckBox monate_5_Check;

   public InformationTab()
   {
      setLayout(new TrainLayout(this));
      this.setOpaque(false);
      this.setBackground(ApplicationColors.getTransparent());
      
      JPanel leftSide = new JPanel();
      leftSide.setLayout(new TotemLayout(leftSide));
      leftSide.setOpaque(true);
      leftSide.setBackground(ApplicationColors.getLightGold());
      
      JPanel fillerBottom = new JPanel(new FlowLayout());
      fillerBottom.setMinimumSize(new Dimension(355, 100));
      fillerBottom.setMaximumSize(new Dimension(355, 700));
      fillerBottom.setOpaque(false);
      fillerBottom.setBackground(ApplicationColors.getTransparent());
      
      JPanel boxWrapper = new JPanel(new FlowLayout());
      boxWrapper.setOpaque(false);
      boxWrapper.setBackground(ApplicationColors.getTransparent());
      boxWrapper.setPreferredSize(new Dimension(355, 100));
      boxWrapper.setMinimumSize(new Dimension(355, 100));
      boxWrapper.setMaximumSize(new Dimension(355, 300));
      JLabel box = new JLabel("<html>"
            + translator.realisticTranslate(Translation.KARTEIKASTEN)
            + "</html>");
      box.setMinimumSize(new Dimension(355, 100));
      box.setMaximumSize(new Dimension(355, 100));
      box.setPreferredSize(new Dimension(355, 100));
      box.setSize(new Dimension(355, 100));
      box.setForeground(ApplicationColors.getWhite());
      box.setBackground(ApplicationColors.getTransparent());
      box.setFont(ApplicationFonts.getHebrewFont(30F));
      box.setHorizontalAlignment(SwingConstants.CENTER);
      boxWrapper.add(box);
      
      JPanel checkWrapper = new JPanel();
      TotemLayout checkLayout = new TotemLayout(checkWrapper);
      checkWrapper.setLayout(checkLayout);
      checkWrapper.setMinimumSize(new Dimension(355, 1000));
      checkWrapper.setMaximumSize(new Dimension(355, 1000));
      checkWrapper.setPreferredSize(new Dimension(355, 1000));
      checkWrapper.setBackground(ApplicationColors.getDarkGold());
      checkWrapper.setOpaque(true);
      
      Font checkBoxFont = ApplicationFonts.getGermanFont(20F);
      
      JCheckBox heuteCheck = new JCheckBox(translator.realisticTranslate(Translation.HEUTE));
      heuteCheck.setFont(checkBoxFont);
      heuteCheck.setForeground(ApplicationColors.getWhite());
      heuteCheck.setSelected(true);
      heuteCheck.setEnabled(false);
      morgenCheck = new JCheckBox(translator.realisticTranslate(Translation.MORGEN));
      morgenCheck.setFont(checkBoxFont);
      morgenCheck.setForeground(ApplicationColors.getWhite());
      morgenCheck.setSelected(Settings.isRepetition_one_day());
      tage_2_Check = new JCheckBox(translator.realisticTranslate(Translation._2_TAGE));
      tage_2_Check.setFont(checkBoxFont);
      tage_2_Check.setForeground(ApplicationColors.getWhite());
      tage_2_Check.setSelected(Settings.isRepetition_two_days());
      tage_5_Check = new JCheckBox(translator.realisticTranslate(Translation._5_TAGE));
      tage_5_Check.setFont(checkBoxFont);
      tage_5_Check.setForeground(ApplicationColors.getWhite());
      tage_5_Check.setSelected(Settings.isRepetition_five_days());
      tage_11_Check = new JCheckBox(translator.realisticTranslate(Translation._11_TAGE));
      tage_11_Check.setFont(checkBoxFont);
      tage_11_Check.setForeground(ApplicationColors.getWhite());
      tage_11_Check.setSelected(Settings.isRepetition_eleven_days());
      tage_19_Check = new JCheckBox(translator.realisticTranslate(Translation._19_TAGE));
      tage_19_Check.setFont(checkBoxFont);
      tage_19_Check.setForeground(ApplicationColors.getWhite());
      tage_19_Check.setSelected(Settings.isRepetition_nineteen_days());
      monate_1_Check = new JCheckBox(translator.realisticTranslate(Translation._1_MONAT));
      monate_1_Check.setFont(checkBoxFont);
      monate_1_Check.setForeground(ApplicationColors.getWhite());
      monate_1_Check.setSelected(Settings.isRepetition_one_month());
      monate_2_Check = new JCheckBox(translator.realisticTranslate(Translation._2_MONATE));
      monate_2_Check.setFont(checkBoxFont);
      monate_2_Check.setForeground(ApplicationColors.getWhite());
      monate_2_Check.setSelected(Settings.isRepetition_two_months());
      monate_5_Check = new JCheckBox(translator.realisticTranslate(Translation._5_MONATE));
      monate_5_Check.setFont(checkBoxFont);
      monate_5_Check.setForeground(ApplicationColors.getWhite());
      monate_5_Check.setSelected(Settings.isRepetition_five_months());
      JCheckBox done_Check = new JCheckBox(translator.realisticTranslate(Translation.FERTIG));
      done_Check.setFont(checkBoxFont);
      done_Check.setForeground(ApplicationColors.getWhite());
      done_Check.setSelected(true);
      done_Check.setEnabled(false);
      
      checkWrapper.add(heuteCheck);
      checkWrapper.add(morgenCheck);
      checkWrapper.add(tage_2_Check);
      checkWrapper.add(tage_5_Check);
      checkWrapper.add(tage_11_Check);
      checkWrapper.add(tage_19_Check);
      checkWrapper.add(monate_1_Check);
      checkWrapper.add(monate_2_Check);
      checkWrapper.add(monate_5_Check);
      checkWrapper.add(done_Check);
      
      JPanel fillerRight = new JPanel(new FlowLayout());
      fillerRight.setMinimumSize(new Dimension(600, 500));
      fillerRight.setMaximumSize(new Dimension(900, 850));
      fillerRight.setOpaque(false);
      fillerRight.setBackground(ApplicationColors.getTransparent()); 
      
      leftSide.add(boxWrapper);
      leftSide.add(checkWrapper);
      leftSide.add(fillerBottom);
      
      add(leftSide);
      add(fillerRight);
      
      initController();
   }

   private void initController()
   {
      morgenCheck.addActionListener(event -> {
         Settings.setRepetition_one_day(morgenCheck.isSelected());
      });
      tage_2_Check.addActionListener(event -> {
         Settings.setRepetition_two_days(tage_2_Check.isSelected());
      });
      tage_5_Check.addActionListener(event -> {
         Settings.setRepetition_five_days(tage_5_Check.isSelected());
      });
      tage_11_Check.addActionListener(event -> {
         Settings.setRepetition_eleven_days(tage_11_Check.isSelected());
      });
      tage_19_Check.addActionListener(event -> {
         Settings.setRepetition_nineteen_days(tage_19_Check.isSelected());
      });
      monate_1_Check.addActionListener(event -> {
         Settings.setRepetition_one_month(monate_1_Check.isSelected());
      });
      monate_2_Check.addActionListener(event -> {
         Settings.setRepetition_two_months(monate_2_Check.isSelected());
      });
      monate_5_Check.addActionListener(event -> {
         Settings.setRepetition_five_months(monate_5_Check.isSelected());
      });
   }

   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      if (ApplicationImages.getImage() != null)
      {
         int x = this.getParent().getWidth() / 2 - 1280 / 2;
         int y = this.getParent().getHeight() / 2 - 853 / 2;
         g.drawImage(ApplicationImages.getDreidel(), x, y, this);
      }
   }

}
