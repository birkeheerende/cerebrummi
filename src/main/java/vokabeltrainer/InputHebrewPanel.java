package vokabeltrainer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusEvent.Cause;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Settings;
import vokabeltrainer.editing.NikudDocument;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;

public class InputHebrewPanel extends JTextArea
{
   private static final long serialVersionUID = 2787773393300243696L;

   private JTextField hebrewField;
   private JTextField pleneField;
   private JTextField defektivField;

   private JButton toggleButton;

   private CardLayout layout;
   private Selection selection;
   private int heightTotal;
   private int widthTotal;
   private int heightBorderTitel;
   private List<JTextComponent> components = new ArrayList<>();

   private ComponentTitledBorder toggleBorder;

   private JPanel cards;
   
   private Container parent;
   private Translator translator = Common.getTranslator();

   private Color color;

   public enum Selection
   {
      SIMPLE,
      PLENE_DEFEKTIV
   }

   class FocusForwardAction extends AbstractAction
   {
      private static final long serialVersionUID = -8790050293258845388L;

      @Override
      public void actionPerformed(ActionEvent e)
      {
         if (Selection.SIMPLE == selection && hebrewField.isFocusOwner())
         {
            forwardToOutsideTraversalCycle();
         }
         else if (Selection.PLENE_DEFEKTIV == selection
               && pleneField.isFocusOwner())
         {
            forwardInside();
         }
         else if (Selection.PLENE_DEFEKTIV == selection
               && defektivField.isFocusOwner())
         {
            forwardToOutsideTraversalCycle();
         }
      }

      private void forwardInside()
      {
         defektivField.requestFocusInWindow();
      }

      private void forwardToOutsideTraversalCycle()
      {
         parent.getFocusTraversalPolicy()
         .getComponentAfter(parent,
               InputHebrewPanel.this)
         .requestFocusInWindow(Cause.TRAVERSAL_FORWARD);
      }

   }
   
   class FocusBackwardAction extends AbstractAction
   {
      private static final long serialVersionUID = -8790050293258845388L;

      @Override
      public void actionPerformed(ActionEvent e)
      {
         if (Selection.SIMPLE == selection && hebrewField.isFocusOwner())
         {
            backwardToOutsideTraversalCycle();
         }
         else if (Selection.PLENE_DEFEKTIV == selection
               && defektivField.isFocusOwner())
         {
            backwardInside();
         }
         else if (Selection.PLENE_DEFEKTIV == selection
               && pleneField.isFocusOwner())
         {
            backwardToOutsideTraversalCycle();
         }
      }

      private void backwardInside()
      {
         pleneField.requestFocusInWindow();
      }

      private void backwardToOutsideTraversalCycle()
      {
         parent.getFocusTraversalPolicy()
         .getComponentBefore(parent,
               InputHebrewPanel.this)
         .requestFocusInWindow(Cause.TRAVERSAL_FORWARD);
      }

   }

   public InputHebrewPanel(Selection selection, int heightTotal,
         int heightBorderTitel, boolean canBeToggled, Container parent, int widthTotal, Color color)
   {
      this.selection = selection;
      this.heightTotal = heightTotal;
      this.heightBorderTitel = heightBorderTitel;
      this.parent = parent;
      this.widthTotal = widthTotal;
      this.color = color;
      
      this.setLayout(new BullsEyeLayout(this));

      cards = new JPanel();
      layout = new CardLayout();
      cards.setLayout(layout);
      cards.setOpaque(true);
      cards.setBackground(ApplicationColors.getWhite());
      cards.setBorder(BorderFactory.createEmptyBorder());

      this.setOpaque(true);
      this.setBackground(ApplicationColors.getWhite());

      toggleButton = new JButton(
            new ImageIcon(ApplicationImages.getToggleButtonIcon()));

      if (canBeToggled)
      {
         toggleButton.setFont(ApplicationFonts.getGermanFont(30F));
         toggleButton.setMargin(new Insets(-5, 0, -5, 0));
         toggleButton.setBackground(new Color(0, 0, 0, 0));
         toggleButton.setForeground(ApplicationColors.getGold());
         toggleButton.setPreferredSize(new Dimension(40, 32));
         toggleButton.setActionCommand("was_toggled");
         toggleBorder = new ComponentTitledBorder(toggleButton, this,
               BorderFactory.createEmptyBorder(), 40);
         cards.setBorder(toggleBorder);
      }
      else
      {
         cards.setBorder(BorderFactory.createEmptyBorder());
      }

      cards.add("simple", initSimpleHebrew());
      cards.add("pleneDefektiv", initPleneDefektivHebrew());

      this.add(cards);
      initController();
      if (Selection.PLENE_DEFEKTIV == selection)
      {
         layout.next(cards);
      }

      String focusCommand = "focus_forward";
      KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
      getInputMap(InputHebrewPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(tab,
            focusCommand);
      getActionMap().put(focusCommand, new FocusForwardAction());
      
      String focusBackwardCommand = "focus_backward";
      KeyStroke tabBack = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK);
      getInputMap(InputHebrewPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(tabBack,
            focusBackwardCommand);
      getActionMap().put(focusBackwardCommand, new FocusBackwardAction());
   }

   private void initController()
   {
      addFocusListener(new FocusListener()
      {
         @Override
         public void focusGained(FocusEvent e)
         {          
            fromOutsideToInside(e);
         }

         private void fromOutsideToInside(FocusEvent e)
         {
            if (e.getCause() == Cause.TRAVERSAL_FORWARD)
            {
               if (Selection.SIMPLE == selection)
               {
                  hebrewField.requestFocusInWindow();
               }
               else
               {
                  pleneField.requestFocusInWindow();
               }
            }
            else if (e.getCause() == Cause.TRAVERSAL_BACKWARD)
            {
               if (Selection.SIMPLE == selection)
               {
                  hebrewField.requestFocusInWindow();
               }
               else
               {
                  defektivField.requestFocusInWindow();
               }
            }
         }

         @Override
         public void focusLost(FocusEvent e)
         {
         }
      });

      hebrewField.addCaretListener(event -> {
         if (!hebrewField.getText().isEmpty())
         {
            setBlankBorder();
         }
      });
      pleneField.addCaretListener(event -> {
         if (!pleneField.getText().isBlank()
               && !defektivField.getText().isBlank())
         {
            setBlankBorder();
         }
      });
      defektivField.addCaretListener(event -> {
         if (!pleneField.getText().isBlank()
               && !defektivField.getText().isBlank())
         {
            setBlankBorder();
         }
      });

      toggleButton.addActionListener(event -> toggleLayout());

      toggleButton.addMouseListener(new MouseAdapter()
      {

         @Override
         public void mouseClicked(MouseEvent e)
         {
            toggleFocusInside();
         }

         private void toggleFocusInside()
         {
            if (Selection.SIMPLE == selection)
            {
               hebrewField.requestFocusInWindow();
            }
            else
            {
               pleneField.requestFocusInWindow();
            }
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

   private Component initPleneDefektivHebrew()
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));
      vertical.setBackground(this.color);
      vertical.setBorder(BorderFactory.createLineBorder(this.color));

      pleneField = new JTextField();
      pleneField.setDocument(new NikudDocument(true));
      pleneField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      pleneField.setFont(ApplicationFonts.getHebrewFont(30F));
      pleneField.setMinimumSize(new Dimension(Settings.getKeyboardWidth() - 30,
            (heightTotal - heightBorderTitel) / 2));
      pleneField.setMaximumSize(new Dimension(this.widthTotal,
            (heightTotal - heightBorderTitel) / 2));
      pleneField
            .setBorder(BorderFactory.createTitledBorder(translator.realisticTranslate(Translation.HEBRAEISCH__PLENE)));
      components.add(pleneField);

      defektivField = new JTextField();
      defektivField.setDocument(new NikudDocument(true));
      defektivField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      defektivField.setFont(ApplicationFonts.getHebrewFont(30F));
      defektivField
            .setMinimumSize(new Dimension(Settings.getKeyboardWidth() - 30,
                  (heightTotal - heightBorderTitel) / 2));
      defektivField
            .setMaximumSize(new Dimension(this.widthTotal,
                  (heightTotal - heightBorderTitel) / 2));
      defektivField
            .setBorder(BorderFactory.createTitledBorder(translator.realisticTranslate(Translation.HEBRAEISCH__DEFEKTIV)));

      components.add(defektivField);

      vertical.add(pleneField);
      vertical.add(defektivField);

      return vertical;
   }

   private Component initSimpleHebrew()
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));
      vertical.setBackground(this.color);
      vertical.setOpaque(true);

      hebrewField = new JTextField();
      hebrewField.setDocument(new NikudDocument(true));
      hebrewField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      hebrewField.setFont(ApplicationFonts.getHebrewFont(30F));
      hebrewField.setMinimumSize(new Dimension(Settings.getKeyboardWidth() - 30,
            (heightTotal - heightBorderTitel)));
      hebrewField.setMaximumSize(new Dimension(this.widthTotal,
            (heightTotal - heightBorderTitel)));
      hebrewField.setBorder(BorderFactory.createTitledBorder( translator.realisticTranslate(Translation.HEBRAEISCH__EINFACHE_SCHREIBWEISE)));
      
      components.add(hebrewField);

      vertical.add(hebrewField);

      return vertical;
   }

   private void toggleLayout()
   {
      switch (selection)
      {
      case SIMPLE:
         selection = Selection.PLENE_DEFEKTIV;
         layout.next(cards);
         this.hebrewField.setText("");
         break;
      case PLENE_DEFEKTIV:
         selection = Selection.SIMPLE;
         layout.first(cards);
         this.pleneField.setText("");
         this.defektivField.setText("");
      }
      Settings.toggleSimpleHebrewInput();
   }

   public void setHebrewLayout(Selection newSelection)
   {
      if (selection == newSelection)
      {
         return;
      }
      toggleLayout();
   }

   public boolean isSimple()
   {
      return Selection.SIMPLE == selection;
   }

   public String getHebrewFieldText()
   {
      return hebrewField.getText();
   }

   public String getPleneFieldText()
   {
      return pleneField.getText();
   }

   public String getDefektivFieldText()
   {
      return defektivField.getText();
   }

   public void setHebrewFieldText(String hebrewText)
   {
      hebrewField.setText(hebrewText);
   }

   public void setPleneFieldText(String pleneText)
   {
      pleneField.setText(pleneText);
   }

   public void setDefektivFieldText(String defektiveText)
   {
      defektivField.setText(defektiveText);
   }

   public boolean isFilledOut()
   {
      switch (selection)
      {
      case SIMPLE:
         return !hebrewField.getText().strip().isBlank();
      case PLENE_DEFEKTIV:
         return !pleneField.getText().strip().isBlank()
               && !defektivField.getText().strip().isBlank();
      }
      return false;
   }

   public void setBlankBorder()
   {
      BorderFactory.createEmptyBorder(3, 3, 3, 3);

   }

   public void setRedBorder()
   {
      this.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
   }

   @Override
   public void setBackground(Color color)
   {
      if (hebrewField != null)
      {
         hebrewField.setBackground(color);
      }
      if (pleneField != null)
      {
         pleneField.setBackground(color);
      }
      if (defektivField != null)
      {
         defektivField.setBackground(color);
      }
   }

   @Override
   public void setEnabled(boolean enabled)
   {
      if (toggleButton != null)
      {
         this.toggleButton.setEnabled(enabled);
      }
   }

   @Override
   public void setEditable(boolean editable)
   {
      if (hebrewField != null)
      {
         this.hebrewField.setEditable(editable);
      }
      if (pleneField != null)
      {
         this.pleneField.setEditable(editable);
      }
      if (defektivField != null)
      {
         this.defektivField.setEditable(editable);
      }
   }

   public Collection<? extends JTextComponent> getTextComponents()
   {
      return components;
   }

   public Selection getSelection()
   {
      return selection;
   }

   public void setSelection(Selection selection)
   {
      this.selection = selection;
   }
}
