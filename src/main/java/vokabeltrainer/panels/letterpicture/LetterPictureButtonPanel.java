package vokabeltrainer.panels.letterpicture;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.scale.Scale;

public class LetterPictureButtonPanel extends JPanel
{
   private static final long serialVersionUID = 7788782278925301915L;

   private BufferedImage picture;
   private NikudLetter letter;

   private JButton pictureButton;
   private JButton letterButton;
   private JButton letterHandwrittenButton;
   private JButton germanButton;
   private JButton hebrewButton;
   private JButton blankButton;

   private JPanel pictureCard;
   private JPanel letterCard;
   private JPanel letterHandwrittenCard;
   private JPanel germanCard;
   private JPanel hebrewCard;
   private JPanel blankCard;

   private CardLayout layout;

   private Scale scale;

   public LetterPictureButtonPanel(BufferedImage picture, NikudLetter letter,
         Card[] cards)
   {
      this.letter = letter;
      scale = new Scale(50);

      this.setMinimumSize(new Dimension(50, 50));
      this.setMaximumSize(new Dimension(50, 50));

      this.picture = picture;
      layout = new CardLayout();
      this.setLayout(layout);
      this.setOpaque(false);

      initPictureCard();
      initLetterCard();
      initLetterHandwrittenCard();
      initHebrewCard();
      initGermanCard();
      initBlankCard();

      for (Card card : cards)
      {
         switch (card)
         {
         case BLANK:
            this.add(blankCard);
            break;
         case GERMAN:
            this.add(germanCard);
            break;
         case HEBREW:
            this.add(hebrewCard);
            break;
         case LETTER:
            this.add(letterCard);
            break;
         case LETTER_HANDWRITTEN:
            this.add(letterHandwrittenCard);
            break;
         case PICTURE:
            this.add(pictureCard);
            break;
         }
      }

      initController();
   }

   private void initController()
   {
      pictureButton.addActionListener(event -> {
         layout.next(this);
      });

      letterButton.addActionListener(event -> {
         layout.next(this);
      });
      
      letterHandwrittenButton.addActionListener(event -> {
         layout.next(this);
      });

      germanButton.addActionListener(event -> {
         layout.next(this);
      });

      hebrewButton.addActionListener(event -> {
         layout.next(this);
      });

      blankButton.addActionListener(event -> {
         layout.next(this);
      });

   }

   private void initGermanCard()
   {
      germanCard = new JPanel(new BorderLayout());
      germanCard.setOpaque(false);
      germanCard.setPreferredSize(new Dimension(50, 50));
      if (letter == NikudLetter.SPACE)
      {
         germanButton = new JButton(new ImageIcon(picture));
         germanButton.setBorder(BorderFactory.createEmptyBorder());
         germanButton.setMargin(new Insets(0, 0, 0, 0));
         germanButton.setOpaque(false);
      }
      else
      {
         germanButton = new JButton(letter.getGermanPictureName());
         germanButton.setFont(ApplicationFonts.getGermanFont(10F));
         germanButton.setBorder(BorderFactory.createEmptyBorder());
         germanButton.setMargin(new Insets(0, 0, 0, 0));
         germanButton.setContentAreaFilled(false);
         germanButton.setOpaque(false);
      }
      germanCard.add(germanButton, BorderLayout.CENTER);
   }

   private void initHebrewCard()
   {
      hebrewCard = new JPanel(new BorderLayout());
      hebrewCard.setOpaque(false);
      hebrewCard.setPreferredSize(new Dimension(50, 50));
      if (letter == NikudLetter.SPACE)
      {
         hebrewButton = new JButton(new ImageIcon(picture));
         hebrewButton.setBorder(BorderFactory.createEmptyBorder());
         hebrewButton.setMargin(new Insets(0, 0, 0, 0));
         hebrewButton.setOpaque(false);
      }
      else
      {
         hebrewButton = new JButton(letter.getTranscript());
         hebrewButton.setBorder(BorderFactory.createEmptyBorder());
         hebrewButton.setMargin(new Insets(0, 0, 0, 0));
         hebrewButton.setFont(ApplicationFonts.getGermanFont(10F));
         hebrewButton.setContentAreaFilled(false);
         hebrewButton.setOpaque(false);
      }
      hebrewCard.add(hebrewButton, BorderLayout.CENTER);
   }

   private void initLetterCard()
   {
      letterCard = new JPanel(new BorderLayout());
      letterCard.setOpaque(false);
      letterCard.setPreferredSize(new Dimension(50, 50));
      if (letter == NikudLetter.SPACE)
      {
         letterButton = new JButton(new ImageIcon(picture));
         letterButton.setBorder(BorderFactory.createEmptyBorder());
         letterButton.setMargin(new Insets(0, 0, 0, 0));
         letterButton.setOpaque(false);
      }
      else
      {
         letterButton = new JButton(
               new ImageIcon(ApplicationImages.getLetterIconsNikudMap().get(letter)
                     .getScaledInstance(scale.getScaleX(), scale.getScaleY(),
                           java.awt.Image.SCALE_SMOOTH)));
         letterButton.setFont(ApplicationFonts.getHebrewFont(30F));
         letterButton.setBorder(BorderFactory.createEmptyBorder());
         letterButton.setContentAreaFilled(false);
         letterButton.setMargin(new Insets(-10, 0, 0, 0));
         letterButton.setOpaque(false);
      }

      letterCard.add(letterButton, BorderLayout.CENTER);
   }
   
   private void initLetterHandwrittenCard()
   {
      letterHandwrittenCard = new JPanel(new BorderLayout());
      letterHandwrittenCard.setOpaque(false);
      letterHandwrittenCard.setPreferredSize(new Dimension(50, 50));
      if (letter == NikudLetter.SPACE)
      {
         letterHandwrittenButton = new JButton(new ImageIcon(picture));
         letterHandwrittenButton.setBorder(BorderFactory.createEmptyBorder());
         letterHandwrittenButton.setMargin(new Insets(0, 0, 0, 0));
         letterHandwrittenButton.setOpaque(false);
      }
      else
      {
         letterHandwrittenButton = new JButton(
               new ImageIcon(ApplicationImages.getLetterIconsNikudHandwrittenMap().get(letter)
                     .getScaledInstance(scale.getScaleX(), scale.getScaleY(),
                           java.awt.Image.SCALE_SMOOTH)));
         letterHandwrittenButton.setFont(ApplicationFonts.getHebrewHandwrittenFont(30F));
         letterHandwrittenButton.setBorder(BorderFactory.createEmptyBorder());
         letterHandwrittenButton.setContentAreaFilled(false);
         letterHandwrittenButton.setMargin(new Insets(-10, 0, 0, 0));
         letterHandwrittenButton.setOpaque(false);
      }

      letterHandwrittenCard.add(letterHandwrittenButton, BorderLayout.CENTER);
   }

   private void initPictureCard()
   {
      pictureCard = new JPanel(new BorderLayout());
      pictureCard.setOpaque(false);
      pictureCard.setPreferredSize(new Dimension(50, 50));
      if(picture != null)
      {
         pictureButton = new JButton(new ImageIcon(picture));
      }
      else
      {
         pictureButton = new JButton(
               new ImageIcon(ApplicationImages.getLetterIconsNikudMap().get(letter)
                     .getScaledInstance(scale.getScaleX(), scale.getScaleY(),
                           java.awt.Image.SCALE_SMOOTH)));
      }
      pictureButton.setBorder(BorderFactory.createEmptyBorder());
      pictureButton.setMargin(new Insets(0, 0, 0, 0));
      pictureButton.setOpaque(false);
      pictureCard.add(pictureButton, BorderLayout.CENTER);
   }

   private void initBlankCard()
   {
      blankCard = new JPanel(new BorderLayout());
      blankCard.setOpaque(false);
      blankCard.setPreferredSize(new Dimension(50, 50));
      blankButton = new JButton(
            new ImageIcon(ApplicationImages.getLetterEmpty()));
      blankButton.setBorder(BorderFactory.createEmptyBorder());
      blankButton.setMargin(new Insets(0, 0, 0, 0));
      blankButton.setOpaque(false);
      blankCard.add(blankButton, BorderLayout.CENTER);

   }

   public void nextCard()
   {
      layout.next(this);
   }
}
