package vokabeltrainer.panels.trainer;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import vokabeltrainer.InputHebrewPanel.Selection;
import vokabeltrainer.TextImage;
import vokabeltrainer.TextImageWithPicture;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.ApplicationSound;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.SaveTraining;
import vokabeltrainer.common.Settings;
import vokabeltrainer.panels.TrainerView;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.FieldOfTraining;
import vokabeltrainer.types.Language;

public class TrainerController implements TrainerControllerConnector
{
   private TrainerView trainerView;
   private Language languageDirection;
   private FieldOfTraining fieldOfTraining;
   private List<Expression> newExpressions;
   private List<Expression> oldExpressions;
   private Expression currentExpression;
   private int newWordsToLearn;
   private int oldWordsToRepeat;
   private Set<Expression> allExpressions;
   private List<Expression> expressionsToBeTested;
   private Translator translator = Common.getTranslator();

   public TrainerController(Language languageDirection,
         FieldOfTraining fieldOfTraining, List<Expression> newExpressions,
         List<Expression> oldExpressions)
   {
      this.languageDirection = languageDirection;
      this.fieldOfTraining = fieldOfTraining;
      this.newExpressions = newExpressions;
      this.oldExpressions = oldExpressions;
      allExpressions = new HashSet<>();

      trainerView = new TrainerView(this);

      newWordsToLearn = this.newExpressions.size();
      
      if (FieldOfTraining.AREA_SELECTED_TEMPORARY != this.fieldOfTraining)
      {
         oldWordsToRepeat = this.oldExpressions.size();
         allExpressions.addAll(this.oldExpressions);
      }
      else
      {
         oldWordsToRepeat = 0;
      }
      allExpressions.addAll(this.newExpressions);
      expressionsToBeTested = new LinkedList<>();
      expressionsToBeTested.addAll(allExpressions);
      if (FieldOfTraining.AREA_SELECTED_TEMPORARY != this.fieldOfTraining)
      {
         if (languageDirection == Language.GERMAN_TO_HEBREW)
         {
            expressionsToBeTested.forEach(expression -> {
               expression.getTrainingStatusDToH().setTotalTrys(
                     expression.getTrainingStatusDToH().getTrys());
            });
         }
         else
         {
            expressionsToBeTested.forEach(expression -> {
               expression.getTrainingStatusHToD().setTotalTrys(
                     expression.getTrainingStatusHToD().getTrys());
            });
         }
      }

      trainerView.getWordsToDo()
            .setText(String.valueOf(expressionsToBeTested.size()));

      Collections.shuffle(expressionsToBeTested);
      trainerView.init();
      EventQueue.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            trainerView.getAnswerField().grabFocus();
            trainerView.getAnswerField().requestFocus();// or inWindow
         }
      });
   }

   public TrainerView getTrainerView()
   {
      return trainerView;
   }

   public Language getLanguageDirection()
   {
      return languageDirection;
   }

   public FieldOfTraining getFieldOfTraining()
   {
      return fieldOfTraining;
   }

   public List<Expression> getNewExpressions()
   {
      return newExpressions;
   }

   public List<Expression> getOldExpressions()
   {
      return oldExpressions;
   }

   public void setAdditionalInfo()
   {
      if (trainerView.getAdditionalInfoField().getText().isEmpty())
      {
         trainerView.getAdditionalInfoField()
               .setText(currentExpression.getAdditionalInfo());
      }
      else
      {
         trainerView.getAdditionalInfoField().setText("");
      }
      trainerView.getFocusTraversalPolicy().getFirstComponent(null)
            .requestFocus();
   }

   @Override
   public void setGrammarInfo()
   {
      if (trainerView.getGrammarInfoField().getText().isEmpty())
      {
         trainerView.getGrammarInfoField()
               .setText(currentExpression.getGrammarInfo(true));
      }
      else
      {
         trainerView.getGrammarInfoField().setText("");
      }
      trainerView.getFocusTraversalPolicy().getFirstComponent(null)
            .requestFocus();
   }

   public void setNextTest()
   {
      trainerView.getWordPanelPlene().clear();
      trainerView.getWordPanelDefektiv().clear();
      currentExpression = expressionsToBeTested.get(0);

      switch (languageDirection)
      {
      case GERMAN_TO_HEBREW:
         trainerView.getQuestionFieldGerman()
               .setText(currentExpression.getGerman());
         break;
      case HEBREW_TO_GERMAN:
         if (currentExpression.getHebrew().isSimpleHebrew())
         {
            trainerView.getQuestionFieldHebrew().setHebrewFieldText(
                  currentExpression.getHebrew().getHebrew());
            trainerView.getQuestionFieldHebrew()
                  .setHebrewLayout(Selection.SIMPLE);
         }
         else
         {
            trainerView.getQuestionFieldHebrew().setPleneFieldText(
                  currentExpression.getHebrew().getHebrewPlene());
            trainerView.getQuestionFieldHebrew().setDefektivFieldText(
                  currentExpression.getHebrew().getHebrewDefektiv());
            trainerView.getQuestionFieldHebrew()
                  .setHebrewLayout(Selection.PLENE_DEFEKTIV);
         }
         break;
      }

      trainerView.enableSendButton();
   }

   @Override
   public void send()
   {
      try
      {
         if (Language.GERMAN_TO_HEBREW.equals(languageDirection))
         {
            BestResult bestResult = NikudResultFactory.getBestResultPossible(
                  currentExpression,
                  trainerView.getAnswerField().getText().trim(),
                  ApplicationFonts.getHebrewFont(30F));
            Result result = bestResult.getBestResult();
            if (result.isAnswerEmpty())
            {
               JOptionPane.showMessageDialog(Common.getjFrame(), "",
                     Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                     new ImageIcon(TextImage.make(translator.realisticTranslate(
                           Translation.BITTE_SCHREIBEN_SIE_EINE_ANTWORT))));
               return;
            }
            else if (result.isDictionaryEmpty())
            {
               JOptionPane.showMessageDialog(Common.getjFrame(), "",
                     Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                     new ImageIcon(TextImage.make(translator.realisticTranslate(
                           Translation.IHR_TRAININGSWORT_ENTHAELT_KEINE_BUCHSTABEN_),
                           translator.realisticTranslate(
                                 Translation.BITTE_LOESCHEN_SIE_DIESEN_AUSDRUCK),
                           translator
                                 .realisticTranslate(Translation.AUS_KAPITEL)
                                 + " " + currentExpression.getChapter())));
               return;
            }
            trainerView.prepareDtoNikudFeedbackPanel(result);
            if (result.isOkay())
            {
               resultDtoIsOkay();
            }
            else
            {
               resultDtoIsNotOkay();
            }
            reactToAnswer(result.isOkay());
         }
         else
         {
            trainerView.prepareHtoDFeedbackPanel();
         }
         trainerView.getFeedbackPanel().validate();
         trainerView.getFeedbackPanel().repaint();
         trainerView.disableSendButton();
      }
      catch (Exception e1)
      {
         e1.printStackTrace();
      }
   }

   private void resultDtoIsNotOkay()
   {
      if (FieldOfTraining.AREA_SELECTED_TEMPORARY != this.fieldOfTraining)
      {
         if (currentExpression.getTrainingStatusDToH().getTrys() < 4)
         {
            currentExpression.getTrainingStatusDToH().setTrys(
                  currentExpression.getTrainingStatusDToH().getTrys() + 1);
            currentExpression.getTrainingStatusDToH().setTotalTrys(
                  currentExpression.getTrainingStatusDToH().getTotalTrys() + 1);
            expressionsToBeTested.add(currentExpression);
         }
         else
         {
            currentExpression.getTrainingStatusDToH().previousRepetition();
         }
      }
      else
      {
         if (currentExpression.getTemporaryTrainingStatus().getTrys() < 4)
         {
            currentExpression.getTemporaryTrainingStatus().setTrys(
                  currentExpression.getTemporaryTrainingStatus().getTrys() + 1);
            currentExpression.getTemporaryTrainingStatus().setTotalTrys(
                  currentExpression.getTemporaryTrainingStatus().getTotalTrys()
                        + 1);
            expressionsToBeTested.add(currentExpression);
         }
      }
   }

   private void resultDtoIsOkay()
   {
      if (FieldOfTraining.AREA_SELECTED_TEMPORARY != this.fieldOfTraining)
      {
         currentExpression.getTrainingStatusDToH().setTrys(
               currentExpression.getTrainingStatusDToH().getTrys() - 1);
         if (currentExpression.getTrainingStatusDToH().getTrys() == 0)
         {
            currentExpression.getTrainingStatusDToH().nextRepetition();
            currentExpression.getTrainingStatusDToH().setTrys(1);
         }
      }
      else
      {
         currentExpression.getTemporaryTrainingStatus().setTrys(
               currentExpression.getTemporaryTrainingStatus().getTrys() - 1);
      }
      expressionsToBeTested.remove(0);
   }

   @Override
   public void resultHtoDOkay()
   {
      if (FieldOfTraining.AREA_SELECTED_TEMPORARY != this.fieldOfTraining)
      {
         currentExpression.getTrainingStatusHToD().setTrys(
               currentExpression.getTrainingStatusHToD().getTrys() - 1);
         if (currentExpression.getTrainingStatusHToD().getTrys() == 0)
         {
            currentExpression.getTrainingStatusHToD().nextRepetition();
            currentExpression.getTrainingStatusHToD().setTrys(1);
         }
      }
      else
      {
         currentExpression.getTemporaryTrainingStatus().setTrys(
               currentExpression.getTemporaryTrainingStatus().getTrys() - 1);
      }
      trainerView.enableHtoDAnswerButtons(false);
      expressionsToBeTested.remove(0);
      reactToAnswer(true);
   }

   @Override
   public void resultHtoDUndecided()
   {
      trainerView.enableHtoDAnswerButtons(false);
      reactToAnswer(null);
   }

   @Override
   public void resultHtoDFalse()
   {
      if (FieldOfTraining.AREA_SELECTED_TEMPORARY != this.fieldOfTraining)
      {
         if (currentExpression.getTrainingStatusHToD().getTotalTrys() < 4)
         {
            currentExpression.getTrainingStatusHToD().setTrys(
                  currentExpression.getTrainingStatusHToD().getTrys() + 1);
            currentExpression.getTrainingStatusHToD().setTotalTrys(
                  currentExpression.getTrainingStatusHToD().getTotalTrys() + 1);
            expressionsToBeTested.add(currentExpression);
         }
         else
         {
            currentExpression.getTrainingStatusHToD().previousRepetition();
         }
      }
      else
      {
         if (currentExpression.getTemporaryTrainingStatus().getTrys() < 4)
         {
            currentExpression.getTemporaryTrainingStatus().setTrys(
                  currentExpression.getTemporaryTrainingStatus().getTrys() + 1);
            currentExpression.getTemporaryTrainingStatus().setTotalTrys(
                  currentExpression.getTemporaryTrainingStatus().getTotalTrys()
                        + 1);
            expressionsToBeTested.add(currentExpression);
         }
      }
      trainerView.enableHtoDAnswerButtons(false);
      reactToAnswer(false);
   }

   public void reactToAnswer(Boolean okay)
   {
      if (okay == null)
      {
         if (Settings.isSoundOn())
         {
            reactUndecidedWithSoundOn();
         }
         else
         {
            trainerView.showResultBlue();
         }
      }
      else if (okay)
      {
         if (Settings.isSoundOn())
         {
            reactOkayWithSoundOn();
         }
         else
         {
            trainerView.showResultGreen();
         }
      }
      else
      {
         if (Settings.isSoundOn())
         {
            reactFalseWithSoundOn();
         }
         else
         {
            trainerView.showResultRed();
         }
      }

      if (!expressionsToBeTested.isEmpty())
      {
         Collections.shuffle(expressionsToBeTested,
               new Random(System.nanoTime()));
         trainerView.getNextWordButton().setEnabled(true);
      }
      else
      {
         stopTraining(true);
      }
   }

   private void reactFalseWithSoundOn()
   {
      try
      {
         Clip clip = AudioSystem.getClip();
         clip.open(ApplicationSound.getSplotchSound());
         FloatControl volume = (FloatControl) clip
               .getControl(FloatControl.Type.MASTER_GAIN);
         volume.setValue(Settings.getVolume());
         clip.start();
      }
      catch (LineUnavailableException | IOException e)
      {
         // nothing
      }

      trainerView.showResultRed();
   }

   private void reactOkayWithSoundOn()
   {
      try
      {
         Clip clip = AudioSystem.getClip();
         clip.open(ApplicationSound.getClappingSound());
         FloatControl volume = (FloatControl) clip
               .getControl(FloatControl.Type.MASTER_GAIN);
         volume.setValue(Settings.getVolume());
         clip.start();
      }
      catch (LineUnavailableException | IOException e)
      {
         // nothing
      }

      trainerView.showResultGreen();
   }

   private void reactUndecidedWithSoundOn()
   {
      try
      {
         Clip clip = AudioSystem.getClip();
         clip.open(ApplicationSound.getWaveSound());
         FloatControl volume = (FloatControl) clip
               .getControl(FloatControl.Type.MASTER_GAIN);
         volume.setValue(Settings.getVolume());
         clip.start();
      }
      catch (LineUnavailableException | IOException e)
      {
         // nothing
      }

      trainerView.showResultBlue();
   }

   public void stopTraining(boolean finished)
   {
      Common.getMainJPanel().moveToStatisticsPanel();

      if (finished)
      {
         if (newWordsToLearn > 0 && oldWordsToRepeat > 0)
         {
            JOptionPane.showMessageDialog(Common.getjFrame(), "",
                  Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                  new ImageIcon(TextImageWithPicture.make(
                        ApplicationImages.getReward(),
                        translator.realisticTranslate(
                              Translation.WUNDERBAR__SIE_HABEN_DIESE),
                        translator.realisticTranslate(
                              Translation.TRAININGSEINHEIT_ERFOLGREICH),
                        translator.realisticTranslate(Translation.BEENDET_),
                        translator.realisticTranslate(Translation.SIE_HABEN)
                              + " " + newWordsToLearn + " "
                              + translator.realisticTranslate(
                                    Translation.NEUE_WOERTER),
                        translator.realisticTranslate(Translation.UND) + " "
                              + oldWordsToRepeat + " "
                              + translator.realisticTranslate(
                                    Translation.BEKANNTE_WOERTER)
                              + " " + translator.realisticTranslate(
                                    Translation.BEARBEITET_))));
         }
         else if (newWordsToLearn > 0 && oldWordsToRepeat == 0)
         {
            JOptionPane.showMessageDialog(Common.getjFrame(), "",
                  Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                  new ImageIcon(TextImageWithPicture.make(
                        ApplicationImages.getReward(),
                        translator.realisticTranslate(
                              Translation.WUNDERBAR__SIE_HABEN_DIESE),
                        translator.realisticTranslate(
                              Translation.TRAININGSEINHEIT_ERFOLGREICH),
                        translator.realisticTranslate(Translation.BEENDET_),
                        translator.realisticTranslate(Translation.SIE_HABEN)
                              + " " + newWordsToLearn + " "
                              + translator.realisticTranslate(
                                    Translation.NEUE_WOERTER),
                        translator
                              .realisticTranslate(Translation.BEARBEITET_))));
         }
         else if (newWordsToLearn == 0 && oldWordsToRepeat > 0)
         {
            JOptionPane.showMessageDialog(Common.getjFrame(), "",
                  Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                  new ImageIcon(TextImageWithPicture.make(
                        ApplicationImages.getReward(),
                        translator.realisticTranslate(
                              Translation.WUNDERBAR__SIE_HABEN_DIESE),
                        translator.realisticTranslate(
                              Translation.TRAININGSEINHEIT_ERFOLGREICH),
                        translator.realisticTranslate(Translation.BEENDET_),
                        translator.realisticTranslate(Translation.SIE_HABEN)
                              + " " + oldWordsToRepeat + " "
                              + translator.realisticTranslate(
                                    Translation.BEKANNTE_WOERTER)
                              + " " + translator.realisticTranslate(
                                    Translation.BEARBEITET_))));
         }
      }
      else if (trainerView.getWordsRight().getText().equals("0"))
      {
         JOptionPane.showMessageDialog(Common.getjFrame(), "",
               Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
               new ImageIcon(TextImage.make(translator.realisticTranslate(
                     Translation.DAS_TRAINING_WURDE_ABGEBROCHEN_))));
      }
      else if (trainerView.getWordsRight().getText().equals("1"))
      {
         JOptionPane.showMessageDialog(Common.getjFrame(), "",
               Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
               new ImageIcon(TextImage.make(
                     translator.realisticTranslate(
                           Translation.DAS_TRAINING_WURDE_ABGEBROCHEN_),
                     translator.realisticTranslate(
                           Translation.SIE_HABEN_1NE_RICHTIGE),
                     translator
                           .realisticTranslate(Translation.ANTWORT_GEGEBEN_))));
      }
      else
      {
         JOptionPane.showMessageDialog(Common.getjFrame(), "",
               Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
               new ImageIcon(TextImage.make(
                     translator.realisticTranslate(
                           Translation.DAS_TRAINING_WURDE_ABGEBROCHEN_),
                     translator.realisticTranslate(Translation.SIE_HABEN) + " "
                           + trainerView.getWordsRight().getText() + " "
                           + translator
                                 .realisticTranslate(Translation.RICHTIGE),
                     translator.realisticTranslate(
                           Translation.ANTWORTEN_GEGEBEN_))));
      }

      saveTraining();
   }

   private void saveTraining()
   {
      if (FieldOfTraining.AREA_SELECTED_TEMPORARY == this.fieldOfTraining)
      {
         return;
      }
      SaveTraining saveTraining = new SaveTraining();
      SwingUtilities.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            int counter = 0;
            while (counter < 10 && !saveTraining.save())
            {
               try
               {
                  Thread.sleep(200);
               }
               catch (InterruptedException e)
               {

               }
               counter++;
            }

            if (counter == 10)
            {
               JOptionPane
                     .showMessageDialog(Common.getjFrame(),
                           translator
                                 .realisticTranslate(Translation.FEHLERMELDUNG)
                                 + "\n"
                                 + translator.realisticTranslate(
                                       Translation.DIE_TRAININGSDATEN_KONTEN)
                                 + "\n"
                                 + translator.realisticTranslate(
                                       Translation.NICHT_GESPEICHERT_WERDEN_),
                           Settings.getWindowTitle(),
                           JOptionPane.WARNING_MESSAGE);
            }
         }
      });
   }

   @Override
   public void removeFirstExpressionToBeTested()
   {
      expressionsToBeTested.remove(0);
   }

   public int getNewWordsToLearn()
   {
      return newWordsToLearn;
   }

   public int getOldWordsToRepeat()
   {
      return oldWordsToRepeat;
   }

   public Expression getCurrentExpression()
   {
      return currentExpression;
   }

   public List<Expression> getExpressionsToBeTested()
   {
      return expressionsToBeTested;
   }

   @Override
   public void toggleSound()
   {
      Settings.toggleSoundOnOff();
      trainerView.getSoundButton().setIcon(new ImageIcon(Settings.getSound()));
   }

   @Override
   public void toggleLetterPictures()
   {
      Settings.toggleLetterImagesOnOff();
   }

}
