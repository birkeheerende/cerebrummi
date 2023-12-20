package vokabeltrainer.panels.notifications;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;

import javax.swing.JDialog;

import vokabeltrainer.IconPanel;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;

public class OkayExpressionsSavedDialog extends JDialog
{
   private static final long serialVersionUID = 3958358982334677951L;

   public OkayExpressionsSavedDialog()
   {
      super(Common.getjFrame(), "", Dialog.ModalityType.MODELESS);

      setSize(200, 200);
      getContentPane().setPreferredSize(new Dimension(200, 200));
      IconPanel content = new IconPanel(ApplicationImages.getOkaySave(), 200,
            200);
      getContentPane().add(content);
      setUndecorated(true);
      setBackground(new Color(0, 0, 0, 0));
      setAlwaysOnTop(true);
   }

}
