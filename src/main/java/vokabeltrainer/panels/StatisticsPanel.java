package vokabeltrainer.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.panels.statistics.StatisticsTable;
import vokabeltrainer.panels.statistics.StatisticsTableRow;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class StatisticsPanel extends JPanel
{
   private static final long serialVersionUID = -3937351898121564812L;

   private JPanel tablePanel;
   private JPanel wordPanel;
   private JPanel horizontalPanel;
   private Translator translator = Common.getTranslator();

   public StatisticsPanel()
   {
      horizontalPanel = new JPanel();
      TrainLayout horizontalPanelLayout = new TrainLayout(horizontalPanel);
      horizontalPanel.setLayout(horizontalPanelLayout);
      
      tablePanel = new JPanel();
      tablePanel.setLayout(new TotemLayout(tablePanel));
      tablePanel.setOpaque(true);
      tablePanel.setBackground(ApplicationColors.getVeryLightGold());
      tablePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

      wordPanel = new JPanel();
      BullsEyeLayout wordPanelLayout = new BullsEyeLayout(wordPanel);
      wordPanel.setLayout(wordPanelLayout);
      wordPanel.setMinimumSize(new Dimension(500, 160));
      wordPanel.setMaximumSize(new Dimension(700, 800));
      wordPanel.setOpaque(true);
      wordPanel.setBackground(ApplicationColors.getVeryLightGold());

      horizontalPanel.add(tablePanel);
      horizontalPanel.add(wordPanel);
      
      add(horizontalPanel);
      setLayout(new BullsEyeLayout(this));
      setOpaque(true);
      setBackground(ApplicationColors.getTexturedBackgroundColor());
   }

   public void setValues()
   {
      tablePanel.removeAll();
      wordPanel.removeAll();

      JPanel titlePanel = new JPanel(new FlowLayout());
      titlePanel.setOpaque(false);
      titlePanel.setBackground(ApplicationColors.getTransparent());
      titlePanel.setMinimumSize(new Dimension(580, 50));
      titlePanel.setMaximumSize(new Dimension(580, 50));

      JLabel title = new JLabel(translator.realisticTranslate(Translation.TRAININGSUEBERSICHT));
      title.setFont(ApplicationFonts.getGermanFont(30F));
      titlePanel.add(title);

      StatisticsTable table = new StatisticsTable(Data.findStatisticsModel());

      table.addMouseListener(new MouseAdapter()
      {
         public void mousePressed(MouseEvent mouseEvent)
         {
            JTable table = (JTable) mouseEvent.getSource();
            Point point = mouseEvent.getPoint();
            int row = table.rowAtPoint(point);
            int column = table.columnAtPoint(point);
            if (table.getSelectedRow() != -1 && row == table.getSelectedRow())
            {
               wordPanel.removeAll();

               if (column == 1)
               {
                  StatisticsTableRow statisticsTableRow = ((StatisticsTableRow) table
                        .getValueAt(table.getSelectedRow(), 1));
                  JScrollPane scroller = new JScrollPane(
                        statisticsTableRow.getJListHtoD());
                  scroller.setOpaque(false);
                  scroller.setBackground(ApplicationColors.getTransparent());
                  scroller.setBorder(BorderFactory.createEmptyBorder());
                  scroller.getViewport().setOpaque(false);
                  scroller.getViewport()
                        .setBackground(ApplicationColors.getTransparent());
                  scroller.setViewportBorder(BorderFactory.createEmptyBorder());
                  wordPanel.add(scroller);
               }
               else if (column == 2)
               {
                  StatisticsTableRow statisticsTableRow = ((StatisticsTableRow) table
                        .getValueAt(table.getSelectedRow(), 2));
                  JScrollPane scroller = new JScrollPane(
                        statisticsTableRow.getJListDtoH());
                  scroller.setOpaque(false);
                  scroller.setBackground(ApplicationColors.getTransparent());
                  scroller.setBorder(BorderFactory.createEmptyBorder());
                  scroller.getViewport().setOpaque(false);
                  scroller.getViewport()
                        .setBackground(ApplicationColors.getTransparent());
                  scroller.setViewportBorder(BorderFactory.createEmptyBorder());
                  wordPanel.add(scroller);
               }
               wordPanel.validate();
               wordPanel.repaint();
            }
         }
      });
      
      JScrollPane scroller = new JScrollPane(table);
      scroller.setOpaque(false);
      scroller.setBackground(ApplicationColors.getTransparent());
      scroller.setBorder(BorderFactory.createEmptyBorder());
      scroller.getViewport().setOpaque(false);
      scroller.getViewport().setBackground(ApplicationColors.getTransparent());
      scroller.setViewportBorder(BorderFactory.createEmptyBorder());
      scroller.setBackground(ApplicationColors.getDarkRed());

      tablePanel.add(titlePanel);
      tablePanel.add(scroller);
   }
}
