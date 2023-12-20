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
import vokabeltrainer.editing.LetterForAnalysis;
import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.scale.Scale;

public class NikudPictureButtonPanel extends JPanel
{
   private static final long serialVersionUID = 7788782278925301915L;

   private BufferedImage letterPicture;
   private BufferedImage imagePicture;
   private LetterForAnalysis letter;

   private JButton pictureButton;
   private JButton letterButton;
   private JButton germanButton;
   private JButton hebrewButton;

   private JPanel pictureCard;
   private JPanel letterCard;
   private JPanel germanCard;
   private JPanel hebrewCard;

   private CardLayout layout;
   private Scale scale;

   public NikudPictureButtonPanel(LetterForAnalysis letter, Card[] cards)
   {
      this.letter = letter;
      scale = new Scale(50);

      this.setMinimumSize(new Dimension(50, 50));
      this.setMaximumSize(new Dimension(50, 50));

      this.letterPicture = NikudLetterImage.make(letter);
      
      layout = new CardLayout();
      this.setLayout(layout);
      this.setOpaque(false);

      initPictureCard();
      initLetterCard();
      initHebrewCard();
      initGermanCard();

      for (Card card : cards)
      {
         switch (card)
         {
         case BLANK:
            // nothing
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
         case PICTURE:
            this.add(pictureCard);
            break;
         case LETTER_HANDWRITTEN:
            // nothing
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

      germanButton.addActionListener(event -> {
         layout.next(this);
      });

      hebrewButton.addActionListener(event -> {
         layout.next(this);
      });
      
   }

   private void initGermanCard()
   {
      germanCard = new JPanel(new BorderLayout());
      germanCard.setOpaque(false);
      germanCard.setPreferredSize(new Dimension(50, 50));
      if (letter.getContent() == NikudLetter.SPACE)
      {
         germanButton = new JButton(new ImageIcon(letterPicture));
      }
      else
      {
         germanButton = new JButton(
               letter.getContent().getGermanPictureName());
         germanButton.setFont(ApplicationFonts.getGermanFont(10F));
         germanButton.setContentAreaFilled(false);
      }
      germanButton.setBorder(BorderFactory.createEmptyBorder());
      germanButton.setMargin(new Insets(0, 0, 0, 0));
      germanButton.setOpaque(false);
      germanCard.add(germanButton, BorderLayout.CENTER);
   }

   private void initHebrewCard()
   {
      hebrewCard = new JPanel(new BorderLayout());
      hebrewCard.setOpaque(false);
      hebrewCard.setPreferredSize(new Dimension(50, 50));
      if (letter.getContent() == NikudLetter.SPACE)
      {
         hebrewButton = new JButton(new ImageIcon(letterPicture));
         hebrewButton.setBorder(BorderFactory.createEmptyBorder());
         hebrewButton.setMargin(new Insets(0, 0, 0, 0));
         hebrewButton.setOpaque(false);
      }     
      else
      {
         hebrewButton = new JButton(letter.getContent().getTranscript());
         hebrewButton.setFont(ApplicationFonts.getGermanFont(10F));
         hebrewButton.setContentAreaFilled(false); 
      }
      hebrewButton.setBorder(BorderFactory.createEmptyBorder());
      hebrewButton.setMargin(new Insets(0, 0, 0, 0));
      hebrewButton.setOpaque(false);
      
      hebrewCard.add(hebrewButton, BorderLayout.CENTER);
   }

   private void initLetterCard()
   {
      letterCard = new JPanel(new BorderLayout());
      letterCard.setOpaque(false);
      letterCard.setPreferredSize(new Dimension(50, 50));
      letterButton = new JButton(new ImageIcon(letterPicture));
      letterButton.setBorder(BorderFactory.createEmptyBorder());
      letterButton.setMargin(new Insets(0, 0, 0, 0));
      letterButton.setOpaque(false);

      letterCard.add(letterButton, BorderLayout.CENTER);
   }

   private void initPictureCard()
   {
      pictureCard = new JPanel(new BorderLayout());
      pictureCard.setOpaque(false);
      pictureCard.setPreferredSize(new Dimension(50, 50));
      imagePicture = ApplicationImages.getLetterPicturesMap().get(letter.getContent());
      if(imagePicture != null)
      {
         pictureButton = new JButton(new ImageIcon(imagePicture));
      }
      else
      {
         pictureButton = new JButton(
               new ImageIcon(ApplicationImages.getLetterIconsNikudMap().get(letter.getContent())
                     .getScaledInstance(scale.getScaleX(), scale.getScaleY(),
                           java.awt.Image.SCALE_SMOOTH)));
      }
      pictureButton.setBorder(BorderFactory.createEmptyBorder());
      pictureButton.setMargin(new Insets(0, 0, 0, 0));
      pictureButton.setOpaque(false);
      pictureCard.add(pictureButton, BorderLayout.CENTER);
   }

   public void nextCard()
   {
      layout.next(this);
   }
}
