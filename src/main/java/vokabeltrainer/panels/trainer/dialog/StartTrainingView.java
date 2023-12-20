package vokabeltrainer.panels.trainer.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Settings;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.FieldOfTraining;
import vokabeltrainer.types.Language;

public class StartTrainingView extends JDialog
{
   private static final long serialVersionUID = -2204963503225031512L;

   private JPanel layout;
   private JTabbedPane tabbedPane;
   private Language languageDirection;
   private FieldOfTraining fieldOfTraining;
   private List<Expression> newExpressions;
   private List<Expression> oldExpressions;
   private boolean training;
   private Translator translator = Common.getTranslator();

   public StartTrainingView(StartTrainingControllerConnector connector)
   {
      super(Common.getjFrame(), Settings.getWindowTitle(),
            Dialog.ModalityType.APPLICATION_MODAL);

      training = false;
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      setSize(Math.min(screenSize.width - 60, 1600),
              Math.min(screenSize.height - 60, 900));
      layout = new JPanel();
      layout.setBorder(BorderFactory.createLineBorder(new Color(169, 136, 103),
            15, false));
      layout.setLayout(new BorderLayout());
      getContentPane().add(layout);

      tabbedPane = new JTabbedPane();
      tabbedPane.setFont(ApplicationFonts.getToolbarButtonFont());
      layout.add(tabbedPane, BorderLayout.CENTER);

      tabbedPane.addTab(translator.realisticTranslate(Translation.GEBIET), new ImageIcon(ApplicationImages.getArrow()),
            new FieldOfTrainingTab(this));
   }

   public void initTraining()
   {
      training = true;
      this.setVisible(false);
   }

   public void cancelTrainingStart()
   {
      training = false;
      this.setVisible(false);
   }

   public Language getLanguageDirection()
   {
      return languageDirection;
   }

   public void setLanguageDirection(Language languageDirection)
   {
      this.languageDirection = languageDirection;
   }

   public FieldOfTraining getFieldOfTraining()
   {
      return fieldOfTraining;
   }

   public void setNewExpressions(List<Expression> newExpressions)
   {
      this.newExpressions = newExpressions;
   }

   public void setFieldOfTraining(FieldOfTraining fieldOfTraining)
   {
      this.fieldOfTraining = fieldOfTraining;
   }

   public JTabbedPane getTabbedPane()
   {
      return tabbedPane;
   }

   public List<Expression> getNewExpressions()
   {
      return newExpressions;
   }

   public boolean isTraining()
   {
      return training;
   }

   public List<Expression> getOldExpressions()
   {
      return oldExpressions;
   }

   public void setOldExpressions(List<Expression> oldExpressions)
   {
      this.oldExpressions = oldExpressions;
   }

}
