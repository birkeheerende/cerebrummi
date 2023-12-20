package vokabeltrainer.panels.settings;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.editing.GermanDocument;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class InputDatabaseNameDialog extends JDialog
{

   private static final long serialVersionUID = 1663927478782742504L;
   private JTextField inputImportField;
   private JButton okButton;
   private JButton stopButton;
   private String databaseName;
   private boolean startImport;
   private boolean overwrite;
   private JRadioButton overwriteYes;
   private ButtonGroup overwriteGroup;
   private JRadioButton overwriteNo;

   public InputDatabaseNameDialog(String title)
   {
      super(Common.getjFrame(), title, true);

      this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      this.setIconImage(ApplicationImages.getLogo24());
      this.setMinimumSize(new Dimension(400, 320));
      this.setLocationRelativeTo(Common.getjFrame());
      
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);
      
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);
      
      inputImportField = new JTextField();
      inputImportField.setDocument(new GermanDocument(60));
      inputImportField.setMinimumSize(new Dimension(200, 30));
      inputImportField.setMaximumSize(new Dimension(300, 30));
      inputImportField.setColumns(20);
      inputImportField.setEditable(false);
      inputImportField.setEnabled(false);
      
      overwriteGroup = new ButtonGroup();
      overwriteNo = new JRadioButton("vorhandenen Datenbanknamen NICHT überschreiben");
      overwriteYes = new JRadioButton("vorhandenen Datenbanknamen überschreiben");
      overwriteGroup.add(overwriteNo);
      overwriteGroup.add(overwriteYes);
      overwriteNo.setSelected(true);
      
      okButton = new JButton("OK");
      stopButton = new JButton("abbrechen");
      
      JPanel buttonPanel = new JPanel();
      TrainLayout buttonPanelLayout = new TrainLayout(buttonPanel, 15);
      buttonPanel.setLayout(buttonPanelLayout);
      
      buttonPanel.add(okButton);
      buttonPanel.add(stopButton);
      
      vertical.add(overwriteNo);
      vertical.add(overwriteYes);
      vertical.add(new JLabel("Namen für die Datenbank"));
      vertical.add(inputImportField);
      vertical.add(buttonPanel);
      
      panel.add(vertical);
      this.add(panel);
      
      this.initController();
   }

   private void initController()
   {
      okButton.addActionListener(event -> {
         databaseName = inputImportField.getText();
         overwrite = overwriteYes.isSelected();
         if (overwrite && databaseName.isBlank())
         {
            this.inputImportField
                  .setBorder(BorderFactory
                        .createLineBorder(ApplicationColors.getDarkRed(),
                              3));
            return;
         }
         startImport = true;
         this.setVisible(false);
      });
      
      stopButton.addActionListener(event -> {
         startImport = false;
         this.setVisible(false);
      });
      
      overwriteYes.addActionListener(event -> {
         if(overwriteYes.isSelected())
         {
            inputImportField.setEditable(true);
            inputImportField.setEnabled(true);
         }
      });
      
      overwriteNo.addActionListener(event -> {
         if(overwriteNo.isSelected())
         {
            inputImportField.setEditable(false);
            inputImportField.setEnabled(false);
         }
      });
   }

   public String getDatabaseName()
   {
      return databaseName;
   }

   public boolean isStartImportOrExport()
   {
      return startImport;
   }

   public boolean isOverwrite()
   {
      return overwrite;
   }

   public JTextField getInputImportField()
   {
      return inputImportField;
   }
}
