package vokabeltrainer.panels.success;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.panels.success.table.SuccessTable;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.types.Language;
import vokabeltrainer.types.Repetition;

public class LanguageTab extends JPanel
{
   private static final long serialVersionUID = 7350720885659255518L;

   private JPanel notStartedPanel;
   private JPanel nowPanel;
   private JPanel oneDayPanel;
   private JPanel twoDaysPanel;
   private JPanel fiveDaysPanel;
   private JPanel elevenDaysPanel;
   private JPanel nineteenDaysPanel;
   private JPanel oneMonthPanel;
   private JPanel twoMonthsPanel;
   private JPanel fiveMonthsPanel;
   private JPanel donePanel;

   private JTabbedPane register;

   private JPanel infoPanel;
   private Language languageDirection; 
   private Translator translator = Common.getTranslator();

   public LanguageTab(JPanel infoPanel, Language languageDirection)
   {
      this.infoPanel = infoPanel;
      this.languageDirection = languageDirection;
      this.setLayout(new BorderLayout());
      this.setOpaque(false);
      this.setBackground(ApplicationColors.getTransparent());
   }

   public void loadBoxes()
   {
      this.removeAll();

      register = new JTabbedPane();
      register.setFont(ApplicationFonts.getSecondaryToolbarButtonFont());

      register.addTab(translator.realisticTranslate(Translation.RICHTUNG), infoPanel); // 0
      register.addTab(translator.realisticTranslate(Translation.VORRAT), initNotStarted()); // 1
      register.addTab(translator.realisticTranslate(Translation.HEUTE), initNow()); // 2
      register.addTab(translator.realisticTranslate(Translation.MORGEN), initOneDay()); // 3
      register.addTab(translator.realisticTranslate(Translation._2_TAGE), initTwoDays()); // 4
      register.addTab(translator.realisticTranslate(Translation._5_TAGE), initFiveDays()); // 5
      register.addTab(translator.realisticTranslate(Translation._11_TAGE), initElevenDays()); // 6
      register.addTab(translator.realisticTranslate(Translation._19_TAGE), initNineteenDays()); // 7
      register.addTab(translator.realisticTranslate(Translation._1_MONAT), initOneMonth()); // 8
      register.addTab(translator.realisticTranslate(Translation._2_MONATE), initTwoMonths()); // 9
      register.addTab(translator.realisticTranslate(Translation._5_MONATE), initFiveMonths()); // 10
      register.addTab(translator.realisticTranslate(Translation.FERTIG), initDone()); // 11

      this.add(register, BorderLayout.CENTER);

      initController();
   }

   private Component initNotStarted()
   {
      notStartedPanel = new BackgroundPanelTiled(new BorderLayout());
      return notStartedPanel;
   }

   private Component initNow()
   {
      nowPanel = new JPanel();
      return SuccessHelper.makePanel(nowPanel);
   }

   private Component initOneDay()
   {
      oneDayPanel = new JPanel();
      return SuccessHelper.makePanel(oneDayPanel);
   }

   private Component initTwoDays()
   {
      twoDaysPanel = new JPanel();
      return SuccessHelper.makePanel(twoDaysPanel);
   }

   private Component initFiveDays()
   {
      fiveDaysPanel = new JPanel();
      return SuccessHelper.makePanel(fiveDaysPanel);
   }

   private Component initElevenDays()
   {
      elevenDaysPanel = new JPanel();
      return SuccessHelper.makePanel(elevenDaysPanel);
   }

   private Component initNineteenDays()
   {
      nineteenDaysPanel = new JPanel();
      return SuccessHelper.makePanel(nineteenDaysPanel);
   }

   private Component initOneMonth()
   {
      oneMonthPanel = new JPanel();
      return SuccessHelper.makePanel(oneMonthPanel);
   }

   private Component initTwoMonths()
   {
      twoMonthsPanel = new JPanel();
      return SuccessHelper.makePanel(twoMonthsPanel);
   }

   private Component initFiveMonths()
   {
      fiveMonthsPanel = new JPanel();
      return SuccessHelper.makePanel(fiveMonthsPanel);
   }

   private Component initDone()
   {
      donePanel = new JPanel();
      return SuccessHelper.makePanel(donePanel);
   }

   private void initController()
   {
      register.addChangeListener(event -> {
         switch (register.getSelectedIndex())
         {
         case 0:
            break;
         case 1:
            notStartedPanel.removeAll();
            JPanel wrapper = new JPanel();
            BullsEyeLayout wrapperLayout = new BullsEyeLayout(wrapper);
            wrapper.setLayout(wrapperLayout);
            wrapper.setBackground(ApplicationColors.getDarkGold());
            wrapper.setOpaque(true);         
            
            SuccessTable table = new SuccessTable(
                  Data.findSuccessModel(languageDirection, null));
            JScrollPane scroller = new JScrollPane(table);
            scroller.setMinimumSize(new Dimension(1017,508));
            scroller.setMaximumSize(new Dimension(1200,655));
            scroller.setBackground(ApplicationColors.getDarkGold());
            scroller.setOpaque(true);
            scroller.getViewport().setBackground(ApplicationColors.getLightBlue());
            scroller.getViewport().setOpaque(true);
            
            wrapper.add(scroller);
            
            notStartedPanel.add(wrapper, BorderLayout.CENTER);
            break;
         case 2:
            SuccessHelper.addContent(Repetition.NOW, nowPanel,
                  languageDirection);
            break;
         case 3:
            SuccessHelper.addContent(Repetition.ONE_DAY, oneDayPanel,
                  languageDirection);
            break;
         case 4:
            SuccessHelper.addContent(Repetition.TWO_DAYS, twoDaysPanel,
                  languageDirection);
            break;
         case 5:
            SuccessHelper.addContent(Repetition.FIVE_DAYS, fiveDaysPanel,
                  languageDirection);
            break;
         case 6:
            SuccessHelper.addContent(Repetition.ELEVEN_DAYS, elevenDaysPanel,
                  languageDirection);
            break;
         case 7:
            SuccessHelper.addContent(Repetition.NINETEEN_DAYS, nineteenDaysPanel,
                  languageDirection);
            break;
         case 8:
            SuccessHelper.addContent(Repetition.ONE_MONTH, oneMonthPanel,
                  languageDirection);
            break;
         case 9:
            SuccessHelper.addContent(Repetition.TWO_MONTHS, twoMonthsPanel,
                  languageDirection);
            break;
         case 10:
            SuccessHelper.addContent(Repetition.FIVE_MONTHS, fiveMonthsPanel,
                  languageDirection);
            break;
         case 11:
            SuccessHelper.addContent(Repetition.DONE, donePanel,
                  languageDirection);
            break;
         }
      });
   }

}
