package net.thestig294.testmod.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.thestig294.testmod.TestMod;

public class ModSounds {
    public static final SoundEvent JUMP_IN_THE_CAAC = registerSoundEvent("jump_in_the_caac");

    private static SoundEvent registerSoundEvent(String soundName){
        Identifier soundID = new Identifier(TestMod.MOD_ID, soundName);
        return Registry.register(Registries.SOUND_EVENT, soundID, SoundEvent.of(soundID));
    }

    public static void registerSounds(){
        TestMod.LOGGER.info("Registering mod sounds for " + TestMod.MOD_ID);
    }
}
