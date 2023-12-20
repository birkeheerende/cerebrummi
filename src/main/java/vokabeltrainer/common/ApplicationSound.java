package vokabeltrainer.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.swing.JOptionPane;

public class ApplicationSound
{
   private static byte[] shredderSound = {};
   private static byte[] splotchSound = {};
   private static byte[] clappingSound = {};
   private static byte[] waveSound = {};

   private static AudioFormat audioFormat = new AudioFormat(44100, 16, 2, true,
         false);

   private static String message = "Cerebrummi© konnte keine Geräusche laden.\\nFehler: ";

   public static void setShredderSound(InputStream in)
   {
      try
      {
         shredderSound = in.readAllBytes();
      }
      catch (Exception e)
      {
         // nothing
      }

      if (shredderSound.length == 0)
      {
         exitWithMessage("Shredder Geräusch fehlt.");
      }
   }

   public static AudioInputStream getShredderSound()
   {
      return new AudioInputStream(new ByteArrayInputStream(shredderSound),
            audioFormat, shredderSound.length);
   }

   public static void setSplotchSound(InputStream audioInputStream)
   {
      try
      {
         splotchSound = audioInputStream.readAllBytes();
      }
      catch (Exception e)
      {
         // nothing
      }

      if (splotchSound.length == 0)
      {
         exitWithMessage("Splotch Geräusch fehlt.");
      }
   }

   public static AudioInputStream getSplotchSound()
   {
      return new AudioInputStream(new ByteArrayInputStream(splotchSound),
            audioFormat, splotchSound.length);
   }

   public static void setClappingSound(InputStream audioInputStream)
   {
      try
      {
         clappingSound = audioInputStream.readAllBytes();
      }
      catch (Exception e)
      {
         // nothing
      }

      if (clappingSound.length == 0)
      {
         exitWithMessage("Clapping Geräusch fehlt.");
      }
   }

   public static AudioInputStream getClappingSound()
   {
      return new AudioInputStream(new ByteArrayInputStream(clappingSound),
            audioFormat, clappingSound.length);
   }

   public static void setWaveSound(InputStream audioInputStream)
   {
      try
      {
         waveSound = audioInputStream.readAllBytes();
      }
      catch (Exception e)
      {
         // nothing
      }

      if (waveSound.length == 0)
      {
         exitWithMessage("Wave Geräusch fehlt.");
      }
   }

   public static AudioInputStream getWaveSound()
   {
      return new AudioInputStream(new ByteArrayInputStream(waveSound),
            audioFormat, waveSound.length);
   }

   private static void exitWithMessage(String localMessage)
   {
      JOptionPane.showMessageDialog(null, message + localMessage, "Nachricht",
            JOptionPane.CLOSED_OPTION);
      System.exit(1);
   }
}
