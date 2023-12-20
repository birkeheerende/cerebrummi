package vokabeltrainer.panels.notifications;

import java.awt.Color;
import java.awt.Dialog;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.tonionlayout.TotemLayout;

public class ErrorDialog extends JDialog
{
   private static final long serialVersionUID = -2919642120086961576L;
   
   public ErrorDialog(String textA, String textB, String middle, String textD)
   {
      super(Common.getjFrame(), "", Dialog.ModalityType.MODELESS);

      setSize(400, 200);
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical);
      vertical.setLayout(verticalLayout);
      vertical.setBackground(ApplicationColors.getWhite());
      vertical.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ApplicationColors.getDarkRed()),Common.getTranslator().realisticTranslate(Translation.FEHLERMELDUNG)));
      
      JLabel rowA = new JLabel(textA);
      rowA.setFont(ApplicationFonts.getGermanFont(24F));
      JLabel rowB = new JLabel(textB);
      rowB.setFont(ApplicationFonts.getGermanFont(20F));
      JLabel rowC = new JLabel(middle);
      rowC.setFont(ApplicationFonts.getGermanFont(20F));
      JLabel rowD = new JLabel(textD);
      rowD.setFont(ApplicationFonts.getGermanFont(16F));
      
      vertical.add(rowA);
      vertical.add(new JPanel());
      vertical.add(rowB);
      vertical.add(rowC);
      vertical.add(new JPanel());
      vertical.add(rowD);
      
      getContentPane().add(vertical);
      setUndecorated(true);
      this.setBackground(new Color(0, 0, 0, 0));
   }

}
