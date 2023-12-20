package vokabeltrainer.keyboards;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Settings;
import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.scale.Scale;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class KeyboardHebrewStandard extends JPanel
{
   private static final long serialVersionUID = 2289311868636133564L;

   private final int BUTTON_SIZE = 42;
   private List<JTextComponent> components;

   private Scale scale;
   private Map<NikudLetter, Component> dataButtons;

   private JPanel keyboardPanel;
   private boolean handwritten;

   public KeyboardHebrewStandard(JTextComponent textfield,
         List<JTextComponent> arrayList, int textFieldHeight, boolean handwritten)
   {
      this.handwritten = handwritten;
      scale = new Scale(BUTTON_SIZE);
      dataButtons = new HashMap<>();

      if (textfield != null)
      {
         textfield.setFont(ApplicationFonts.getHebrewFont(30F));
         textfield.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         textfield.setMinimumSize(
               new Dimension(Settings.getKeyboardWidth(), textFieldHeight));
         textfield.setMaximumSize(
               new Dimension(Settings.getKeyboardWidth(), textFieldHeight));
         arrayList.add(textfield);
      }

      this.components = arrayList;

      setLayout(new TotemLayout(this, 4));
      this.setOpaque(false);

      if (textfield != null)
      {
         add(textfield);
         setFocusTraversalPolicy(new OneFocusTraversalPolicy(textfield));
         textfield.grabFocus();
      }

      keyboardPanel = new JPanel();
      keyboardPanel.setOpaque(false);
      keyboardPanel.setLayout(new TotemLayout(keyboardPanel));

      dataButtons.put(NikudLetter.KUF, makeButton(NikudLetter.KUF));
      dataButtons.put(NikudLetter.RESCH, makeButton(NikudLetter.RESCH));
      dataButtons.put(NikudLetter.ALEF, makeButton(NikudLetter.ALEF));
      dataButtons.put(NikudLetter.TET, makeButton(NikudLetter.TET));
      dataButtons.put(NikudLetter.WAW, makeButton(NikudLetter.WAW));
      dataButtons.put(NikudLetter.NUNSSOFIT, makeButton(NikudLetter.NUNSSOFIT));
      dataButtons.put(NikudLetter.MEMSSOFIT, makeButton(NikudLetter.MEMSSOFIT));
      dataButtons.put(NikudLetter.PAEI, makeButton(NikudLetter.PAEI));
      
      dataButtons.put(NikudLetter.SCHIN, makeButton(NikudLetter.SCHIN));
      dataButtons.put(NikudLetter.DALET, makeButton(NikudLetter.DALET));
      dataButtons.put(NikudLetter.GIMEL, makeButton(NikudLetter.GIMEL));
      dataButtons.put(NikudLetter.KAF, makeButton(NikudLetter.KAF));
      dataButtons.put(NikudLetter.AIN, makeButton(NikudLetter.AIN));
      dataButtons.put(NikudLetter.JOD, makeButton(NikudLetter.JOD));
      dataButtons.put(NikudLetter.CHET, makeButton(NikudLetter.CHET));
      dataButtons.put(NikudLetter.LAMED, makeButton(NikudLetter.LAMED));
      dataButtons.put(NikudLetter.CHAFSSOFIT, makeButton(NikudLetter.CHAFSSOFIT));
      dataButtons.put(NikudLetter.FAEISSOFIT, makeButton(NikudLetter.FAEISSOFIT));
      
      dataButtons.put(NikudLetter.SSAIN, makeButton(NikudLetter.SSAIN));
      dataButtons.put(NikudLetter.SSAMECH, makeButton(NikudLetter.SSAMECH));
      dataButtons.put(NikudLetter.BET, makeButton(NikudLetter.BET));
      dataButtons.put(NikudLetter.HAEI, makeButton(NikudLetter.HAEI));
      dataButtons.put(NikudLetter.NUN, makeButton(NikudLetter.NUN));
      dataButtons.put(NikudLetter.MEM, makeButton(NikudLetter.MEM));
      dataButtons.put(NikudLetter.ZADI, makeButton(NikudLetter.ZADI));
      dataButtons.put(NikudLetter.TAW, makeButton(NikudLetter.TAW));
      dataButtons.put(NikudLetter.ZADISSOFIT, makeButton(NikudLetter.ZADISSOFIT));

      makeRegularKeyboard();
      
      add(keyboardPanel);
   }

   private Component makeButton(NikudLetter letter)
   {
      DataButton dataButton;
      if (handwritten)
      {
         dataButton = new DataButton(ApplicationImages.getLetterIconsNikudHandwrittenMap()
               .get(letter).getScaledInstance(scale.getScaleX(), scale.getScaleY(),
                     java.awt.Image.SCALE_SMOOTH),
               letter.getUnicode());
      }
      else
      {
         dataButton = new DataButton(ApplicationImages.getLetterIconsNikudMap()
               .get(letter).getScaledInstance(scale.getScaleX(), scale.getScaleY(),
                     java.awt.Image.SCALE_SMOOTH),
               letter.getUnicode());
      }
      dataButton.setMargin(new Insets(3, -5, 0, -5));
      dataButton.setMinimumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE));
      dataButton.setMaximumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE));

      JPanel buttonPanel = new JPanel();
      buttonPanel.setOpaque(false);
      buttonPanel.setLayout(new TotemLayout(buttonPanel));
      buttonPanel.add(dataButton);

      dataButton.addMouseListener(new KeyboardListener());
      
      return buttonPanel;
   }

   class KeyboardListener implements MouseListener
   {

      public void mouseClicked(MouseEvent e)
      {

      }

      public void mousePressed(MouseEvent e)
      {
         DataButton jButton = (DataButton) e.getComponent();
         String caption = jButton.getData();

         JTextComponent focusElement = findFocusElement();
         if (focusElement != null)
         {
            int position = focusElement.getCaretPosition();
            String text = focusElement.getText();
            String before = text.substring(0, position);
            String after = text.substring(position);
            focusElement.setText(before + caption + after);
            focusElement.requestFocus();
         }
      }

      private JTextComponent findFocusElement()
      {
         for (JTextComponent component : components)
         {
            if (component.isFocusOwner())
            {
               return component;
            }
         }
         return null;
      }

      public void mouseReleased(MouseEvent e)
      {

      }

      public void mouseEntered(MouseEvent e)
      {

      }

      public void mouseExited(MouseEvent e)
      {

      }

   }

   public void shuffleKeyboard()
   {
      keyboardPanel.removeAll();
      
      List<NikudLetter> dataButtonList = new ArrayList<>(dataButtons.keySet());
      Collections.shuffle(dataButtonList);
      
      JPanel row1 = new JPanel();
      row1.setOpaque(false);
      row1.setLayout(new TrainLayout(row1, 4));

      JPanel halfSizedFiller1 = new JPanel();
      halfSizedFiller1.setMinimumSize(new Dimension(76, BUTTON_SIZE));
      halfSizedFiller1.setMaximumSize(new Dimension(76, BUTTON_SIZE));
      halfSizedFiller1.setOpaque(false);
      halfSizedFiller1.setBackground(ApplicationColors.getTransparent());
      row1.add(halfSizedFiller1);

      row1.add(dataButtons.get(dataButtonList.get(0)));
      row1.add(dataButtons.get(dataButtonList.get(1)));
      row1.add(dataButtons.get(dataButtonList.get(2)));
      row1.add(dataButtons.get(dataButtonList.get(3)));
      row1.add(dataButtons.get(dataButtonList.get(4)));
      row1.add(dataButtons.get(dataButtonList.get(5)));
      row1.add(dataButtons.get(dataButtonList.get(6)));
      row1.add(dataButtons.get(dataButtonList.get(7)));

      JPanel row2 = new JPanel();
      row2.setOpaque(false);
      row2.setLayout(new TrainLayout(row2, 4));

      JPanel tinyFiller2 = new JPanel();
      tinyFiller2.setMinimumSize(new Dimension(0, BUTTON_SIZE));
      tinyFiller2.setMaximumSize(new Dimension(0, BUTTON_SIZE));
      tinyFiller2.setOpaque(false);
      tinyFiller2.setBackground(ApplicationColors.getTransparent());
      row2.add(tinyFiller2);

      row2.add(dataButtons.get(dataButtonList.get(8)));
      row2.add(dataButtons.get(dataButtonList.get(9)));
      row2.add(dataButtons.get(dataButtonList.get(10)));
      row2.add(dataButtons.get(dataButtonList.get(11)));
      row2.add(dataButtons.get(dataButtonList.get(12)));
      row2.add(dataButtons.get(dataButtonList.get(13)));
      row2.add(dataButtons.get(dataButtonList.get(14)));
      row2.add(dataButtons.get(dataButtonList.get(15)));
      row2.add(dataButtons.get(dataButtonList.get(16)));
      row2.add(dataButtons.get(dataButtonList.get(17)));

      JPanel row3 = new JPanel();
      row3.setOpaque(false);
      row3.setLayout(new TrainLayout(row3, 4));

      JPanel halfSizedFiller3 = new JPanel();
      halfSizedFiller3.setMinimumSize(new Dimension(30, BUTTON_SIZE));
      halfSizedFiller3.setMaximumSize(new Dimension(30, BUTTON_SIZE));
      halfSizedFiller3.setOpaque(false);
      halfSizedFiller3.setBackground(ApplicationColors.getTransparent());
      row3.add(halfSizedFiller3);

      row3.add(dataButtons.get(dataButtonList.get(18)));
      row3.add(dataButtons.get(dataButtonList.get(19)));
      row3.add(dataButtons.get(dataButtonList.get(20)));
      row3.add(dataButtons.get(dataButtonList.get(21)));
      row3.add(dataButtons.get(dataButtonList.get(22)));
      row3.add(dataButtons.get(dataButtonList.get(23)));
      row3.add(dataButtons.get(dataButtonList.get(24)));
      row3.add(dataButtons.get(dataButtonList.get(25)));
      row3.add(dataButtons.get(dataButtonList.get(26)));

      keyboardPanel.add(row1);
      keyboardPanel.add(row2);
      keyboardPanel.add(row3);
      
      keyboardPanel.validate();
      keyboardPanel.repaint();
   }
   
   public void makeRegularKeyboard()
   {
      keyboardPanel.removeAll();
      
      JPanel row1 = new JPanel();
      row1.setOpaque(false);
      row1.setLayout(new TrainLayout(row1, 4));

      JPanel halfSizedFiller1 = new JPanel();
      halfSizedFiller1.setMinimumSize(new Dimension(76, BUTTON_SIZE));
      halfSizedFiller1.setMaximumSize(new Dimension(76, BUTTON_SIZE));
      halfSizedFiller1.setOpaque(false);
      halfSizedFiller1.setBackground(ApplicationColors.getTransparent());
      row1.add(halfSizedFiller1);

      row1.add(dataButtons.get(NikudLetter.KUF));
      row1.add(dataButtons.get(NikudLetter.RESCH));
      row1.add(dataButtons.get(NikudLetter.ALEF));
      row1.add(dataButtons.get(NikudLetter.TET));
      row1.add(dataButtons.get(NikudLetter.WAW));
      row1.add(dataButtons.get(NikudLetter.NUNSSOFIT));
      row1.add(dataButtons.get(NikudLetter.MEMSSOFIT));
      row1.add(dataButtons.get(NikudLetter.PAEI));

      JPanel row2 = new JPanel();
      row2.setOpaque(false);
      row2.setLayout(new TrainLayout(row2, 4));

      JPanel tinyFiller2 = new JPanel();
      tinyFiller2.setMinimumSize(new Dimension(0, BUTTON_SIZE));
      tinyFiller2.setMaximumSize(new Dimension(0, BUTTON_SIZE));
      tinyFiller2.setOpaque(false);
      tinyFiller2.setBackground(ApplicationColors.getTransparent());
      row2.add(tinyFiller2);

      row2.add(dataButtons.get(NikudLetter.SCHIN));
      row2.add(dataButtons.get(NikudLetter.DALET));
      row2.add(dataButtons.get(NikudLetter.GIMEL));
      row2.add(dataButtons.get(NikudLetter.KAF));
      row2.add(dataButtons.get(NikudLetter.AIN));
      row2.add(dataButtons.get(NikudLetter.JOD));
      row2.add(dataButtons.get(NikudLetter.CHET));
      row2.add(dataButtons.get(NikudLetter.LAMED));
      row2.add(dataButtons.get(NikudLetter.CHAFSSOFIT));
      row2.add(dataButtons.get(NikudLetter.FAEISSOFIT));

      JPanel row3 = new JPanel();
      row3.setOpaque(false);
      row3.setLayout(new TrainLayout(row3, 4));

      JPanel halfSizedFiller3 = new JPanel();
      halfSizedFiller3.setMinimumSize(new Dimension(30, BUTTON_SIZE));
      halfSizedFiller3.setMaximumSize(new Dimension(30, BUTTON_SIZE));
      halfSizedFiller3.setOpaque(false);
      halfSizedFiller3.setBackground(ApplicationColors.getTransparent());
      row3.add(halfSizedFiller3);

      row3.add(dataButtons.get(NikudLetter.SSAIN));
      row3.add(dataButtons.get(NikudLetter.SSAMECH));
      row3.add(dataButtons.get(NikudLetter.BET));
      row3.add(dataButtons.get(NikudLetter.HAEI));
      row3.add(dataButtons.get(NikudLetter.NUN));
      row3.add(dataButtons.get(NikudLetter.MEM));
      row3.add(dataButtons.get(NikudLetter.ZADI));
      row3.add(dataButtons.get(NikudLetter.TAW));
      row3.add(dataButtons.get(NikudLetter.ZADISSOFIT));

      keyboardPanel.add(row1);
      keyboardPanel.add(row2);
      keyboardPanel.add(row3);
      
      keyboardPanel.validate();
      keyboardPanel.repaint();
   }
}