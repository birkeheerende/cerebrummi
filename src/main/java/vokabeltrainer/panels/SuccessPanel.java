package vokabeltrainer.panels;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.Common;
import vokabeltrainer.panels.success.LanguageTab;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.panels.success.InformationTab;
import vokabeltrainer.panels.success.InformationTabDtoH;
import vokabeltrainer.panels.success.InformationTabHtoD;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.types.Language;

public class SuccessPanel extends BackgroundPanelTiled
{
   private static final long serialVersionUID = 8838302053896928354L;
   private JTabbedPane germanHebrewRegister;
   private JPanel infoCard;
   private LanguageTab languageDtoHcard;
   private LanguageTab languageHtoDcard;
   private Translator translator = Common.getTranslator();

   public SuccessPanel()
   {
      setLayout(new BullsEyeLayout(this));

      germanHebrewRegister = new JTabbedPane();
      germanHebrewRegister.setFont(ApplicationFonts.getSecondaryToolbarButtonFont());
      germanHebrewRegister.setTabPlacement(JTabbedPane.LEFT);
      germanHebrewRegister.setMinimumSize(new Dimension(1254, 613));
      germanHebrewRegister.setMaximumSize(new Dimension(1500, 800));

      infoCard = new InformationTab();
      languageDtoHcard = new LanguageTab(new InformationTabDtoH(),
            Language.GERMAN_TO_HEBREW);
      languageHtoDcard = new LanguageTab(new InformationTabHtoD(),
            Language.HEBREW_TO_GERMAN);

      germanHebrewRegister.addTab(
            translator.realisticTranslate(Translation.KARTEIKASTEN), infoCard);
      germanHebrewRegister.addTab(
            translator.realisticTranslate(Translation.DEUTSCH) + " >> "
                  + translator.realisticTranslate(Translation.HEBRAEISCH),
            languageDtoHcard);
      germanHebrewRegister.addTab(
            translator.realisticTranslate(Translation.HEBRAEISCH) + " >> "
                  + translator.realisticTranslate(Translation.DEUTSCH),
            languageHtoDcard);

      add(germanHebrewRegister);

      initController();
   }

   private void initController()
   {
      germanHebrewRegister.addChangeListener(event -> {
         if (germanHebrewRegister.getSelectedIndex() == 1)
         {
            languageDtoHcard.loadBoxes();
         }
         else if (germanHebrewRegister.getSelectedIndex() == 2)
         {
            languageHtoDcard.loadBoxes();
         }
      });

   }

   public void reset()
   {
      germanHebrewRegister.setSelectedIndex(0);
   }
}
