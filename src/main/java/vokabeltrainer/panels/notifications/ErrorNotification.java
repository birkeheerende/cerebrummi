package vokabeltrainer.panels.notifications;

import javax.swing.JDialog;
import javax.swing.Timer;

import vokabeltrainer.common.Common;

public class ErrorNotification
{
   private ErrorNotification()
   {

   }

   public static void display(String header, String top,
         String middle, String bottom)
   {
      JDialog dialog = new ErrorDialog(header, top, middle, bottom);
      dialog.setLocationRelativeTo(Common.getjFrame());
      dialog.setVisible(true);

      new Timer(50000, event -> {
         dialog.setVisible(false);
      }).start();
   }
}
