package vokabeltrainer.panels.success;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.SaveTraining;
import vokabeltrainer.panels.success.table.SuccessTable;
import vokabeltrainer.panels.success.table.SuccessTableModel;
import vokabeltrainer.panels.success.table.SuccessTableRow;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;
import vokabeltrainer.types.Repetition;
import vokabeltrainer.types.TrainingStatus;

public class SuccessHelper
{

   private SuccessHelper()
   {
      // nothing
   }
   
   static JPanel makePanel(JPanel panel)
   {
      JPanel wrapper = new JPanel();
      BullsEyeLayout wrapperLayout = new BullsEyeLayout(wrapper);
      wrapper.setLayout(wrapperLayout);
      wrapper.setBackground(ApplicationColors.getDarkGold());
      wrapper.setOpaque(true);
      TotemLayout totemLayout = new TotemLayout(panel);
      panel.setLayout(totemLayout);
      panel.setBackground(ApplicationColors.getDarkGold());
      panel.setOpaque(true);
      wrapper.add(panel);
      return wrapper;
   }

   static void addContent(Repetition repetition, JPanel panel,
         Language direction)
   {
      Translator translator = Common.getTranslator();
      panel.removeAll();
      SuccessTableModel model = Data.findSuccessModel(direction, repetition);
      SuccessTable table = new SuccessTable(model);
      
      JScrollPane scroller = new JScrollPane(table);
      scroller.setMinimumSize(new Dimension(1200,600));
      scroller.setMaximumSize(new Dimension(1500,750));
      scroller.setBackground(ApplicationColors.getDarkGold());
      scroller.setOpaque(true);
      scroller.getViewport().setBackground(ApplicationColors.getLightBlue());
      scroller.getViewport().setOpaque(true);
      
      JPanel tablePanel = new JPanel();
      BullsEyeLayout tableLayout = new BullsEyeLayout(tablePanel);
      tablePanel.setLayout(tableLayout);
      tablePanel.setBackground(ApplicationColors.getDarkGold());
      tablePanel.add(scroller);
      
      JPanel fillerPanel = new JPanel(new FlowLayout());
      fillerPanel.setBackground(ApplicationColors.getDarkGold());
      fillerPanel.setOpaque(true);
      fillerPanel.setMinimumSize(new Dimension(1200,15));
      fillerPanel.setMaximumSize(new Dimension(1500,15));
      
      JPanel buttonWrapperPanel = new JPanel();
      BullsEyeLayout buttonWrapperLayout = new BullsEyeLayout(buttonWrapperPanel);
      buttonWrapperPanel.setLayout(buttonWrapperLayout);
      buttonWrapperPanel.setBackground(ApplicationColors.getDarkGold());
      
      JPanel buttonPanel = new JPanel();
      buttonPanel.setBackground(ApplicationColors.getDarkGold());
      buttonPanel.setOpaque(true);
      buttonPanel.setLayout(new TrainLayout(buttonPanel, 15));
      buttonPanel.setMinimumSize(new Dimension(1200,40));
      buttonPanel.setMaximumSize(new Dimension(1500,40));
      
      JButton selectAllButton = new JButton(translator.realisticTranslate(Translation.ALLE_AUSWAEHLEN));
      selectAllButton.setIcon(new ImageIcon(ApplicationImages.getSelect()));
      selectAllButton.setFont(ApplicationFonts.getButtonFont());
      selectAllButton.addActionListener(event -> {

         for (Vector<SuccessTableRow> row : model.getData())
         {
            row.get(0).getExpression().setSelected(true);
         }
         model.fireTableDataChanged();

      });
      JButton unselectAllButton = new JButton(translator.realisticTranslate(Translation.ALLE_NICHT_AUSWAEHLEN));
      unselectAllButton.setIcon(new ImageIcon(ApplicationImages.getClear()));
      unselectAllButton.setFont(ApplicationFonts.getButtonFont());
      unselectAllButton.addActionListener(event -> {

         for (Vector<SuccessTableRow> row : model.getData())
         {
            row.get(0).getExpression().setSelected(false);
         }
         model.fireTableDataChanged();

      });
      JButton moveButton = new JButton(
            translator.realisticTranslate(Translation.AUSGEWAEHLTE_WOERTER_ZU)
            + " \""
            + translator.realisticTranslate(Translation.VORRAT)
            + "\" "
            + translator.realisticTranslate(Translation.VERSCHIEBEN));
      moveButton.setIcon(new ImageIcon(ApplicationImages.getBack()));
      moveButton.setFont(ApplicationFonts.getButtonFont());
      moveButton.addActionListener(event -> {
         List<Vector<SuccessTableRow>> rows = new ArrayList<>();
         for (Vector<SuccessTableRow> row : model.getData())
         {
            Expression expression = row.get(0).getExpression();
            if (expression.isSelected() && Language.GERMAN_TO_HEBREW == direction)
            {
               expression.setTrainingStatusDToH(new TrainingStatus());
               expression.setSelected(false);
               rows.add(row);
            }
            else if (expression.isSelected() && Language.HEBREW_TO_GERMAN == direction)
            {
               expression.setTrainingStatusHToD(new TrainingStatus());
               expression.setSelected(false);
               rows.add(row);
            }
         }
         for (Vector<SuccessTableRow> row : rows)
         {
            model.getData().remove(row);
         }
         SaveTraining saver = new SaveTraining();
         saver.save();
         model.fireTableDataChanged();

      });
      buttonPanel.add(selectAllButton);
      buttonPanel.add(unselectAllButton);
      buttonPanel.add(moveButton);
      
      buttonWrapperPanel.add(buttonPanel);
      
      panel.add(tablePanel);
      panel.add(fillerPanel);
      panel.add(buttonWrapperPanel);
   }
}
