package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import vokabeltrainer.TextImage;
import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.SaveExpressions;
import vokabeltrainer.common.Settings;
import vokabeltrainer.panels.input.ChapterComboBox;
import vokabeltrainer.panels.input.TableConnector;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.table.ExpressionTable;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.table.list.editor.NikudExpressionEditorController;
import vokabeltrainer.table.list.editor.NikudExpressionEditorView;
import vokabeltrainer.table.list.editor.TextExpressionEditorView;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;
import vokabeltrainer.types.SortingType;

public class InputPanel extends JPanel implements TableConnector
{
   private static final long serialVersionUID = 4956932074948450143L;

   private JButton newWordPunktationButton;
   private JButton newTextPunktationButton;
   private JPanel tablePanel;
   private Chapter currentChapter;
   private ChapterComboBox chapterBox;
   private JButton tableInfoButton;
   private Translator translator = Common.getTranslator();

   public InputPanel()
   {
      setLayout(new BullsEyeLayout(this));
      setOpaque(true);
      setBackground(ApplicationColors.getTexturedBackgroundColor());
      
      
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));
      vertical.setOpaque(false);

      JPanel filler1 = new JPanel();
      filler1.setOpaque(false);
      
      JPanel filler2 = new JPanel();
      filler2.setOpaque(false);
      
      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal));
      horizontal.add(filler1);
      horizontal.add(initLeftside());
      horizontal.add(filler2);
      horizontal.add(initRightside());
      horizontal.setOpaque(false);

      JPanel spanner = new JPanel();
      spanner.setMinimumSize(new Dimension(1550, 30));
      spanner.setMaximumSize(new Dimension(1550, 30));
      spanner.add(new JLabel(translator
            .realisticTranslate(Translation.UM_EINE_VOKABEL_WIEDER_AUFZURUFEN__)
            + " "
            + translator.realisticTranslate(
                  Translation.EINMAL_ANKLICKEN_UND_DANN_ENTER_EINGABE_TASTE)
            + " " + translator
                  .realisticTranslate(Translation.AUF_DER_TASTATUR_DRUECKEN_)));

      vertical.add(horizontal);
      vertical.add(spanner);

      this.add(vertical);

      initController();
   }

   public void reset()
   {
      Chapter lastModiefiedChapter = Data.getChapterWithLastModifiedDate();
      chapterBox.setModel(Data.getChapterComboBoxModelAsChapter());
      if (chapterBox.getModel().getSize() > 0)
      {
         chapterBox.setSelectedItem(lastModiefiedChapter);
      }
      else
      {
         chapterBox.addItem(new Chapter());
         chapterBox.setSelectedIndex(0);
         this.validate();
         this.repaint();
      }
   }

   private void initController()
   {
      newWordPunktationButton
            .addActionListener(event -> openNewNikudExpressionDialog());
      
      newTextPunktationButton
      .addActionListener(event -> openNewTextExpressionDialog());

      chapterBox.addActionListener(event -> {
         this.currentChapter = chapterBox
               .getItemAt(chapterBox.getSelectedIndex());
         this.doShowTable();
      });

      tableInfoButton.addActionListener(event -> {
         JOptionPane.showMessageDialog(this, "", Settings.getWindowTitle(),
               JOptionPane.INFORMATION_MESSAGE,
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
   }

   private void openNewNikudExpressionDialog()
   {
      NikudExpressionEditorView editor = new NikudExpressionEditorController()
            .getNikudExpressionEditorDialog();
      editor.setExpression(new Expression(true, false), true);
      editor.setLocationRelativeTo(Common.getjFrame());
      editor.setVisible(true);
      if (editor.isSave())
      {
         Expression expression = editor.getExpression();
         Data.putExpressionInNewMap(expression.getUuid(), expression);
         this.currentChapter = expression.getChapter();
         save();
      }
   }
   
   private void openNewTextExpressionDialog()
   {
      TextExpressionEditorView editor = new NikudExpressionEditorController()
            .getTextExpressionEditorDialog();
      editor.setExpression(new Expression(true, false), true);
      editor.setLocationRelativeTo(Common.getjFrame());
      editor.setVisible(true);
      if (editor.isSave())
      {
         Expression expression = editor.getExpression();
         Data.putExpressionInNewMap(expression.getUuid(), expression);
         this.currentChapter = expression.getChapter();
         save();
      }
   }

   private void doShowTable()
   {
      ExpressionTableModel tableModel = Data.findTranslations(
            Language.GERMAN_TO_HEBREW, null, null, null, currentChapter, null,
            SortingType.DATE);
      tablePanel.removeAll();
      ExpressionTable table = new ExpressionTable(tableModel,
            Language.GERMAN_TO_HEBREW, this, true);
      JScrollPane tableScroller = new JScrollPane(table);
      tableScroller.setOpaque(false);
      tableScroller.getViewport().setOpaque(false);
      tableScroller.setViewportBorder(BorderFactory.createEmptyBorder());
      tableScroller.getVerticalScrollBar().setUnitIncrement(30);
      tableScroller.setMinimumSize(new Dimension(500, 700));
      tableScroller.setMaximumSize(new Dimension(500, 700));

      tablePanel.add(tableScroller);
      tablePanel.validate();
      tablePanel.repaint();
   }

   private Component initRightside()
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));
      vertical.setOpaque(false);

      JPanel flow = new JPanel();
      flow.setOpaque(false);
      tableInfoButton = new JButton(
            new ImageIcon(ApplicationImages.getInfoButtonIcon()));
      tableInfoButton.setBackground(ApplicationColors.getWhite());
      tableInfoButton.setMinimumSize(new Dimension(20, 50));
      tableInfoButton.setMaximumSize(new Dimension(20, 50));
      tableInfoButton.setMargin(new Insets(0, 0, 0, 0));
      flow.add(tableInfoButton);
      vertical.add(flow);
      vertical.add(initChapterBox());
      vertical.add(initTablePanel());

      return vertical;
   }

   private Component initChapterBox()
   {
      chapterBox = new ChapterComboBox();
      chapterBox.setMinimumSize(new Dimension(500, 30));
      chapterBox.setMaximumSize(new Dimension(500, 30));
      chapterBox.setPreferredSize(new Dimension(500, 30));
      chapterBox.setSize(new Dimension(500, 30));
      chapterBox.setMaximumRowCount(10);
      chapterBox.setFont(ApplicationFonts.getComboBoxFont());
      return chapterBox;
   }

   private Component initTablePanel()
   {
      tablePanel = new JPanel(new BorderLayout());
      tablePanel.setOpaque(false);
      tablePanel.setMinimumSize(new Dimension(500, 700));
      tablePanel.setMaximumSize(new Dimension(500, 700));
      return tablePanel;
   }

   private Component initLeftside()
   {
      JPanel leftside = new JPanel();
      leftside.setLayout(new BullsEyeLayout(leftside));
      leftside.setOpaque(false);

      JPanel center = new JPanel();
      center.setLayout(new TotemLayout(center));
      center.setOpaque(false);
      
      newWordPunktationButton = new JButton(translator.realisticTranslate(Translation.NEUE_VOKABEL));
      newWordPunktationButton.setFont(ApplicationFonts.getButtonFont());
      newWordPunktationButton.setHorizontalAlignment(SwingConstants.LEFT);
      newWordPunktationButton.setMinimumSize(new Dimension(300, 60));
      newWordPunktationButton.setMaximumSize(new Dimension(300, 60));
      newWordPunktationButton
            .setIcon(new ImageIcon(ApplicationImages.getNewWord()));
      newWordPunktationButton.setBorder(BorderFactory.createMatteBorder(10, 10,
            10, 10, ApplicationColors.getGreen()));

      newTextPunktationButton = new JButton(translator.realisticTranslate(Translation.NEUER_TEXT));
      newTextPunktationButton.setFont(ApplicationFonts.getButtonFont());
      newTextPunktationButton.setHorizontalAlignment(SwingConstants.LEFT);
      newTextPunktationButton.setMinimumSize(new Dimension(300, 60));
      newTextPunktationButton.setMaximumSize(new Dimension(300, 60));
      newTextPunktationButton
            .setIcon(new ImageIcon(ApplicationImages.getNewWord()));
      newTextPunktationButton.setBorder(BorderFactory.createMatteBorder(10, 10,
            10, 10, ApplicationColors.getGreen()));

      center.add(newWordPunktationButton);
      center.add(newTextPunktationButton);
      leftside.add(center);
      return leftside;
   }

   @Override
   public void save()
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            if (new SaveExpressions().save())
            {
               chapterBox.setModel(Data.getChapterComboBoxModelAsChapter());
               chapterBox.setSelectedItem(currentChapter);
            }
            return null;
         }
      }.execute();
   }

   @Override
   public void fireTableCellUpdated(JTable table, int selectedRow, int i)
   {
      ((ExpressionTableModel) table.getModel())
            .fireTableCellUpdated(table.getSelectedRow(), 0);
   }
}
