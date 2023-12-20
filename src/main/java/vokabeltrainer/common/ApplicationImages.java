package vokabeltrainer.common;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

import vokabeltrainer.editing.NikudLetter;

public class ApplicationImages
{
   private static BufferedImage image;
   private static BufferedImage dreidel;
   private static BufferedImage hebrewLetters;
   private static List<BufferedImage> greenImages;
   private static List<BufferedImage> blueImages;
   private static BufferedImage trashcan;
   private static BufferedImage shredder;
   private static BufferedImage copy;
   private static BufferedImage copy2;
   private static BufferedImage clear;
   private static BufferedImage cut;
   private static BufferedImage paste;
   private static BufferedImage select;
   private static BufferedImage selectDone;
   private static BufferedImage newWord;
   private static BufferedImage saveWord;
   private static BufferedImage deleteWord;
   private static BufferedImage restore;
   private static BufferedImage send;
   private static BufferedImage start;
   private static BufferedImage stop;
   private static BufferedImage search;
   private static BufferedImage okaySave;
   private static BufferedImage empty;
   private static BufferedImage infoCursor;
   private static BufferedImage infoButtonIcon;
   private static BufferedImage infoIcon;
   private static BufferedImage toggleCursor;
   private static BufferedImage toggleButtonIcon;
   private static BufferedImage trashcanBackground;
   private static BufferedImage startImage;
   private static BufferedImage errorImage;
   private static BufferedImage texturedBackground;
   private static BufferedImage arrow;
   private static BufferedImage cancel;
   private static BufferedImage done;
   private static BufferedImage emptyList;

   private static BufferedImage logo24;
   private static BufferedImage logo150;
   private static BufferedImage logo;

   private static BufferedImage turn;
   private static BufferedImage answerOkay;
   private static BufferedImage answerNotOkay;
   private static BufferedImage answerUndecided;
   private static BufferedImage reward;

   private static Map<NikudLetter, BufferedImage> letterPicturesMap;
   private static BufferedImage letterEmpty;
   private static BufferedImage letterNone;
   private static BufferedImage work;
   private static BufferedImage soundOn;
   private static BufferedImage soundOff;

   private static BufferedImage back;
   private static BufferedImage lock;
   private static BufferedImage languages;
   private static BufferedImage newWordSmall;
   private static BufferedImage l18n;

   private static Map<NikudLetter, BufferedImage> letterIconsNikudMap;
   private static Map<NikudLetter, BufferedImage> letterIconsNikudHandwrittenMap;

   private static String message = "Bitte neu starten.\nCerebrummi© konnte keine Bilder laden.\nFehler: ";

   public static void setImage(BufferedImage image)
   {
      ApplicationImages.image = image;
   }

   public static BufferedImage getImage()
   {
      return image;
   }

   public static List<BufferedImage> getGreenImages()
   {
      return greenImages;
   }

   public static void setGreenImages(List<BufferedImage> greenImages)
   {
      if (greenImages.size() < 72)
      {
         exitWithMessage("Es fehlen grüne Bilder.");
      }
      ApplicationImages.greenImages = greenImages;
   }

   public static BufferedImage getTrashcan()
   {
      return trashcan;
   }

   public static void setTrashcan(BufferedImage trashcan)
   {
      if (trashcan == null)
      {
         exitWithMessage("keine Trashcan");
      }
      ApplicationImages.trashcan = trashcan;
   }

   public static BufferedImage getShredder()
   {
      return shredder;
   }

   public static void setShredder(BufferedImage shredder)
   {
      if (shredder == null)
      {
         exitWithMessage("kein Shredder");
      }
      ApplicationImages.shredder = shredder;
   }

   public static BufferedImage getRandomGreenImage()
   {
      Random random = new Random();
      return greenImages.get(random.nextInt(greenImages.size()));
   }

   public static BufferedImage getRandomBlueImage()
   {
      Random random = new Random();
      return blueImages.get(random.nextInt(blueImages.size()));
   }

   public static void setCopy(BufferedImage copy)
   {
      if (copy == null)
      {
         exitWithMessage("kein Copy Icon");
      }
      ApplicationImages.copy = copy;
   }

   public static BufferedImage getCopy()
   {
      return copy;
   }

   public static BufferedImage getClear()
   {
      return clear;
   }

   public static void setClear(BufferedImage clear)
   {
      if (clear == null)
      {
         exitWithMessage("kein Clear Icon");
      }
      ApplicationImages.clear = clear;
   }

   public static BufferedImage getSelect()
   {
      return select;
   }

   public static void setSelect(BufferedImage selected)
   {
      if (selected == null)
      {
         exitWithMessage("kein Selected Icon");
      }
      ApplicationImages.select = selected;
   }

   public static BufferedImage getNewWord()
   {
      return newWord;
   }

   public static void setNewWord(BufferedImage newWord)
   {
      if (newWord == null)
      {
         exitWithMessage("kein NewWord Icon");
      }
      ApplicationImages.newWord = newWord;
   }

   public static BufferedImage getLanguages()
   {
      return languages;
   }

   public static void setLanguages(BufferedImage languages)
   {
      if (languages == null)
      {
         exitWithMessage("kein Languages Icon");
      }
      ApplicationImages.languages = languages;
   }

   public static BufferedImage getNewWordSmall()
   {
      return newWordSmall;
   }

   public static void setNewWordSmall(BufferedImage newWordSmall)
   {
      if (newWordSmall == null)
      {
         exitWithMessage("kein NewWordSmall Icon");
      }
      ApplicationImages.newWordSmall = newWordSmall;
   }
   
   public static BufferedImage getL18n()
   {
      return l18n;
   }

   public static void setL18n(BufferedImage l18n)
   {
      if (l18n == null)
      {
         exitWithMessage("kein L18n Icon");
      }
      ApplicationImages.l18n = l18n;
   }

   public static BufferedImage getSaveWord()
   {
      return saveWord;
   }

   public static void setSaveWord(BufferedImage saveWord)
   {
      if (saveWord == null)
      {
         exitWithMessage("kein SaveWord Icon");
      }
      ApplicationImages.saveWord = saveWord;
   }

   public static BufferedImage getDeleteWord()
   {
      return deleteWord;
   }

   public static void setDeleteWord(BufferedImage deleteWord)
   {
      if (deleteWord == null)
      {
         exitWithMessage("kein deleteWord");
      }
      ApplicationImages.deleteWord = deleteWord;
   }

   public static BufferedImage getRestore()
   {
      return restore;
   }

   public static void setRestore(BufferedImage restore)
   {
      if (restore == null)
      {
         exitWithMessage("kein Restore Icon");
      }
      ApplicationImages.restore = restore;
   }

   public static BufferedImage getSend()
   {
      return send;
   }

   public static void setSend(BufferedImage send)
   {
      if (send == null)
      {
         exitWithMessage("kein Send Icon");
      }
      ApplicationImages.send = send;
   }

   public static BufferedImage getStart()
   {
      return start;
   }

   public static void setStart(BufferedImage start)
   {
      if (start == null)
      {
         exitWithMessage("kein Start Icon");
      }
      ApplicationImages.start = start;
   }

   public static BufferedImage getStop()
   {
      return stop;
   }

   public static void setStop(BufferedImage stop)
   {
      if (stop == null)
      {
         exitWithMessage("kein Stop Icon");
      }
      ApplicationImages.stop = stop;
   }

   public static BufferedImage getSearch()
   {
      return search;
   }

   public static void setSearch(BufferedImage search)
   {
      if (search == null)
      {
         exitWithMessage("kein Search Icon");
      }
      ApplicationImages.search = search;
   }

   public static BufferedImage getOkaySave()
   {
      return okaySave;
   }

   public static void setOkaySave(BufferedImage okaySave)
   {
      if (okaySave == null)
      {
         exitWithMessage("kein OkaySave Icon");
      }
      ApplicationImages.okaySave = okaySave;
   }

   public static BufferedImage getEmpty()
   {
      return empty;
   }

   public static void setEmpty(BufferedImage empty)
   {
      if (empty == null)
      {
         exitWithMessage("kein empty Icon");
      }
      ApplicationImages.empty = empty;
   }

   public static BufferedImage getInfoCursor()
   {
      return infoCursor;
   }

   public static void setInfoCursor(BufferedImage infoCursor)
   {
      if (infoCursor == null)
      {
         exitWithMessage("kein InfoCursor");
      }
      ApplicationImages.infoCursor = infoCursor;
   }

   public static BufferedImage getToggleCursor()
   {
      return toggleCursor;
   }

   public static void setToggleCursor(BufferedImage toggleCursor)
   {
      if (toggleCursor == null)
      {
         exitWithMessage("kein ToggleCursor");
      }
      ApplicationImages.toggleCursor = toggleCursor;
   }

   public static BufferedImage getInfoButtonIcon()
   {
      return infoButtonIcon;
   }

   public static void setInfoButtonIcon(BufferedImage infoButtonIcon)
   {
      if (infoButtonIcon == null)
      {
         exitWithMessage("kein InfoButton Icon");
      }
      ApplicationImages.infoButtonIcon = infoButtonIcon;
   }

   public static BufferedImage getToggleButtonIcon()
   {
      return toggleButtonIcon;
   }

   public static void setToggleButtonIcon(BufferedImage toggleButtonIcon)
   {
      if (toggleButtonIcon == null)
      {
         exitWithMessage("kein ToggleButton Icon");
      }
      ApplicationImages.toggleButtonIcon = toggleButtonIcon;
   }

   public static BufferedImage getInfoIcon()
   {
      return infoIcon;
   }

   public static void setInfoIcon(BufferedImage infoIcon)
   {
      if (infoIcon == null)
      {
         exitWithMessage("kein InfoIcon Icon");
      }
      ApplicationImages.infoIcon = infoIcon;
   }

   public static BufferedImage getTrashcanBackground()
   {
      return trashcanBackground;
   }

   public static void setTrashcanBackground(BufferedImage trashcanBackground)
   {
      if (trashcanBackground == null)
      {
         exitWithMessage("kein Trashcan Hintergrund");
      }
      ApplicationImages.trashcanBackground = trashcanBackground;
   }

   public static BufferedImage getStartImage()
   {
      return startImage;
   }

   public static void setStartImage(BufferedImage startImage)
   {
      if (startImage == null)
      {
         exitWithMessage("kein StartImage");
      }
      ApplicationImages.startImage = startImage;
   }

   public static BufferedImage getErrorImage()
   {
      return errorImage;
   }

   public static void setErrorImage(BufferedImage errorImage)
   {
      if (errorImage == null)
      {
         exitWithMessage("kein errorImage");
      }
      ApplicationImages.errorImage = errorImage;
   }

   public static BufferedImage getTexturedBackground()
   {
      return texturedBackground;
   }

   public static void setTexturedBackground(BufferedImage texturedBackground)
   {
      if (texturedBackground == null)
      {
         exitWithMessage("kein textured Hintergrund");
      }
      ApplicationImages.texturedBackground = texturedBackground;
   }

   public static BufferedImage getArrow()
   {
      return arrow;
   }

   public static void setArrow(BufferedImage arrow)
   {
      if (arrow == null)
      {
         exitWithMessage("kein Arrow Icon");
      }
      ApplicationImages.arrow = arrow;
   }

   public static BufferedImage getDone()
   {
      return done;
   }

   public static void setDone(BufferedImage done)
   {
      if (done == null)
      {
         exitWithMessage("kein Done Icon");
      }
      ApplicationImages.done = done;
   }

   public static BufferedImage getEmptyList()
   {
      return emptyList;
   }

   public static void setEmptyList(BufferedImage emptyList)
   {
      if (emptyList == null)
      {
         exitWithMessage("kein EmptyList Icon");
      }
      ApplicationImages.emptyList = emptyList;
   }

   public static BufferedImage getLogo()
   {
      return logo;
   }

   public static void setLogo(BufferedImage logo)
   {
      if (logo == null)
      {
         exitWithMessage("kein Logo");
      }
      ApplicationImages.logo = logo;
   }

   public static BufferedImage getLogo24()
   {
      return logo24;
   }

   public static void setLogo24(BufferedImage logo24)
   {
      if (logo24 == null)
      {
         exitWithMessage("kein Logo 24");
      }
      ApplicationImages.logo24 = logo24;
   }

   public static BufferedImage getLogo150()
   {
      return logo150;
   }

   public static void setLogo150(BufferedImage logo150)
   {
      if (logo150 == null)
      {
         exitWithMessage("kein Logo 150");
      }
      ApplicationImages.logo150 = logo150;
   }

   public static Map<NikudLetter, BufferedImage> getLetterPicturesMap()
   {
      return letterPicturesMap;
   }

   public static void setLetterPicturesMap(
         Map<NikudLetter, BufferedImage> letterPicturesMap)
   {
      if (letterPicturesMap.size() < 30)
      {
         exitWithMessage("Buchstabenbilder fehlen");
      }
      else if (letterPicturesMap.size() > 30)
      {
         exitWithMessage("zuviele Buchstabenbilder");
      }
      ApplicationImages.letterPicturesMap = letterPicturesMap;
   }

   public static Map<NikudLetter, BufferedImage> getLetterIconsNikudMap()
   {
      return letterIconsNikudMap;
   }

   public static void setLetterIconsNikudMap(
         Map<NikudLetter, BufferedImage> letterIconsNikudMap)
   {
      if (letterIconsNikudMap.size() < 56)
      {
         exitWithMessage("Buchstaben Nikud Icons fehlen");
      }
      else if (letterIconsNikudMap.size() > 56)
      {
         exitWithMessage("zuviele Buchstaben Nikud Icons");
      }
      ApplicationImages.letterIconsNikudMap = letterIconsNikudMap;
   }

   public static Map<NikudLetter, BufferedImage> getLetterIconsNikudHandwrittenMap()
   {
      return letterIconsNikudHandwrittenMap;
   }

   public static void setLetterIconsNikudHandwrittenMap(
         Map<NikudLetter, BufferedImage> letterIconsNikudHandwrittenMap)
   {
      if (letterIconsNikudHandwrittenMap.size() < 27)
      {
         exitWithMessage("Buchstaben Nikud Icons Handwritten fehlen");
      }
      else if (letterIconsNikudHandwrittenMap.size() > 27)
      {
         exitWithMessage("zuviele Buchstaben Nikud Icons Handwritten");
      }
      ApplicationImages.letterIconsNikudHandwrittenMap = letterIconsNikudHandwrittenMap;
   }

   public static BufferedImage getTurn()
   {
      return turn;
   }

   public static void setTurn(BufferedImage turn)
   {
      if (turn == null)
      {
         exitWithMessage("kein Turn Icon");
      }
      ApplicationImages.turn = turn;
   }

   public static BufferedImage getAnswerOkay()
   {
      return answerOkay;
   }

   public static void setAnswerOkay(BufferedImage answerOkay)
   {
      if (answerOkay == null)
      {
         exitWithMessage("kein AnswerOkay Icon");
      }
      ApplicationImages.answerOkay = answerOkay;
   }

   public static BufferedImage getAnswerNotOkay()
   {
      return answerNotOkay;
   }

   public static void setAnswerNotOkay(BufferedImage answerNotOkay)
   {
      if (answerNotOkay == null)
      {
         exitWithMessage("kein AnswerNotOkay Icon");
      }
      ApplicationImages.answerNotOkay = answerNotOkay;
   }

   public static BufferedImage getAnswerUndecided()
   {
      return answerUndecided;
   }

   public static void setAnswerUndecided(BufferedImage answerUndecided)
   {
      if (answerUndecided == null)
      {
         exitWithMessage("kein AnswerUndecided Icon");
      }
      ApplicationImages.answerUndecided = answerUndecided;
   }

   public static List<BufferedImage> getBlueImages()
   {
      return blueImages;
   }

   public static void setBlueImages(List<BufferedImage> blueImages)
   {
      if (blueImages.size() < 32)
      {
         exitWithMessage("Es fehlen blaue Bilder.");
      }
      ApplicationImages.blueImages = blueImages;
   }

   public static BufferedImage getCancel()
   {
      return cancel;
   }

   public static void setCancel(BufferedImage cancel)
   {
      if (cancel == null)
      {
         exitWithMessage("kein Cancel Icon");
      }
      ApplicationImages.cancel = cancel;
   }

   public static BufferedImage getReward()
   {
      return reward;
   }

   public static void setReward(BufferedImage reward)
   {
      if (reward == null)
      {
         exitWithMessage("kein Reward Icon");
      }
      ApplicationImages.reward = reward;
   }

   public static void setLetterEmpty(BufferedImage letterEmpty)
   {
      if (letterEmpty == null)
      {
         exitWithMessage("kein LetterEmpty Icon");
      }
      ApplicationImages.letterEmpty = letterEmpty;
   }

   public static void setLetterNone(BufferedImage letterNone)
   {
      if (letterNone == null)
      {
         exitWithMessage("kein LetterNone Icon");
      }
      ApplicationImages.letterNone = letterNone;
   }

   public static BufferedImage getLetterEmpty()
   {
      return letterEmpty;
   }

   public static BufferedImage getLetterNone()
   {
      return letterNone;
   }

   public static void setWork(BufferedImage work)
   {
      if (work == null)
      {
         exitWithMessage("kein Work Icon");
      }
      ApplicationImages.work = work;
   }

   public static BufferedImage getWork()
   {
      return work;
   }

   public static void setSoundOn(BufferedImage soundOn)
   {
      if (soundOn == null)
      {
         exitWithMessage("kein SoundOn Icon");
      }
      ApplicationImages.soundOn = soundOn;
   }

   public static BufferedImage getSoundOn()
   {
      return soundOn;
   }

   public static void setSoundOff(BufferedImage soundOff)
   {
      if (soundOff == null)
      {
         exitWithMessage("kein SoundOff Icon");
      }
      ApplicationImages.soundOff = soundOff;
   }

   public static BufferedImage getSoundOff()
   {
      return soundOff;
   }

   private static void exitWithMessage(String localMessage)
   {
      JOptionPane
            .showMessageDialog(null, message + localMessage, "Nachricht",
                  JOptionPane.CLOSED_OPTION);
      System.exit(1);
   }

   public static void setDreidel(BufferedImage dreidel)
   {
      if (dreidel == null)
      {
         exitWithMessage("kein Dreidel Bild");
      }
      ApplicationImages.dreidel = dreidel;
   }

   public static BufferedImage getDreidel()
   {
      return dreidel;
   }

   public static BufferedImage getHebrewLetters()
   {
      return hebrewLetters;
   }

   public static void setHebrewLetters(BufferedImage hebrewLetters)
   {
      if (hebrewLetters == null)
      {
         exitWithMessage("kein Hebräische Buchstaben Bild");
      }
      ApplicationImages.hebrewLetters = hebrewLetters;
   }

   public static BufferedImage getBack()
   {
      return back;
   }

   public static void setBack(BufferedImage back)
   {
      if (back == null)
      {
         exitWithMessage("kein Zurück Icon");
      }
      ApplicationImages.back = back;
   }

   public static BufferedImage getSelectDone()
   {
      return selectDone;
   }

   public static void setSelectDone(BufferedImage selectDone)
   {
      if (selectDone == null)
      {
         exitWithMessage("kein Häckchen Icon");
      }
      ApplicationImages.selectDone = selectDone;
   }

   public static void setCopy2(BufferedImage copy2)
   {
      if (copy2 == null)
      {
         exitWithMessage("kein Kopieren Nr.2 Icon");
      }
      ApplicationImages.copy2 = copy2;
   }

   public static void setCut(BufferedImage cut)
   {
      if (cut == null)
      {
         exitWithMessage("kein Ausschneiden Icon");
      }
      ApplicationImages.cut = cut;
   }

   public static void setPaste(BufferedImage paste)
   {
      if (paste == null)
      {
         exitWithMessage("kein Einfügen Icon");
      }
      ApplicationImages.paste = paste;
   }

   public static BufferedImage getCopy2()
   {
      return copy2;
   }

   public static BufferedImage getCut()
   {
      return cut;
   }

   public static BufferedImage getPaste()
   {
      return paste;
   }

   public static BufferedImage getLock()
   {
      return lock;
   }

   public static void setLock(BufferedImage lock)
   {
      if (lock == null)
      {
         exitWithMessage("kein Schloss Icon");
      }
      ApplicationImages.lock = lock;
   }

}
