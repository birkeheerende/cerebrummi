package vokabeltrainer.panels.success;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class InformationTabHtoD extends JPanel
{
   private static final long serialVersionUID = 8516410904944879380L;
   
   private Translator translator = Common.getTranslator();

   public InformationTabHtoD()
   {
      this.setLayout(new TrainLayout(this));
      setOpaque(false);
      setBackground(ApplicationColors.getTransparent());
      
      JPanel leftSide = new JPanel();
      leftSide.setLayout(new TotemLayout(leftSide));
      leftSide.setOpaque(true);
      leftSide.setBackground(ApplicationColors.getLightGold());
      
      JPanel fillerBottom = new JPanel(new FlowLayout());
      fillerBottom.setMinimumSize(new Dimension(200, 100));
      fillerBottom.setMaximumSize(new Dimension(400, 700));
      fillerBottom.setOpaque(false);
      fillerBottom.setBackground(ApplicationColors.getTransparent());
      
      JPanel boxWrapper = new JPanel(new FlowLayout());
      boxWrapper.setOpaque(false);
      boxWrapper.setBackground(ApplicationColors.getTransparent());
      boxWrapper.setPreferredSize(new Dimension(355, 100));
      JLabel box = new JLabel("<html>"
            + translator.realisticTranslate(Translation.HEBRAEISCH)
            + " >> "
            + translator.realisticTranslate(Translation.DEUTSCH)
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
      
      JPanel fillerRight = new JPanel(new FlowLayout());
      fillerRight.setMinimumSize(new Dimension(600, 500));
      fillerRight.setMaximumSize(new Dimension(900, 850));
      fillerRight.setOpaque(false);
      fillerRight.setBackground(ApplicationColors.getTransparent());
      
      leftSide.add(boxWrapper);
      leftSide.add(fillerBottom);
      
      add(leftSide);
      add(fillerRight);
   }
   
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      if (ApplicationImages.getImage() != null)
      {
         int x = this.getParent().getWidth() / 2 - 1280 / 2;
         int y = this.getParent().getHeight() / 2 - 853 / 2;
         g.drawImage(ApplicationImages.getHebrewLetters(), x, y, this);
      }
   }
}
