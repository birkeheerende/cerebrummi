package vokabeltrainer.resources;

import vokabeltrainer.common.ApplicationSound;

public class Sounds
{
   public static void read() throws Exception
   {
      ApplicationSound.setSplotchSound(
            Sounds.class.getResourceAsStream("/sounds/splotch-sound.byt"));
      ApplicationSound.setClappingSound(
            Sounds.class.getResourceAsStream("/sounds/clapping-sound.byt"));
      ApplicationSound
            .setWaveSound(Sounds.class.getResourceAsStream("/sounds/wave-sound.byt"));
      ApplicationSound.setShredderSound(
            Sounds.class.getResourceAsStream("/sounds/shredder-sound.byt"));
   }
}
