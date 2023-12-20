package vokabeltrainer.panels.trainer;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.tonionlayout.TrainLayout;

public class HebrewAnswerWordPanel extends JPanel
{
   private static final long serialVersionUID = 3783416522992027245L;

   public HebrewAnswerWordPanel(Result result)
   {
      this.setPreferredSize(new Dimension(
            result.getWidth() + (result.getFeedbackImageList().size() - 1) * 4,
            110));
      this.setLayout(new TrainLayout(this, 4));

      for (BufferedImage image : result.getFeedbackImageList())
      {
         JLabel label = new JLabel(new ImageIcon(image));
         label.setMinimumSize(new Dimension(image.getWidth(), 110));
         label.setMaximumSize(new Dimension(image.getWidth(), 110));
         this.add(label);
      }
      
      this.setOpaque(true);
      this.setBackground(ApplicationColors.getTexturedBackgroundColor());
   }
}
