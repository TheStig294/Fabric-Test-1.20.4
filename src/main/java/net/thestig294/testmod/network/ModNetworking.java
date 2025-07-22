package net.thestig294.testmod.network;

import net.minecraft.util.Identifier;
import net.thestig294.testmod.TestMod;

public class ModNetworking {
    public static final Identifier DISPLAY_CAAC_SCREEN = register("display_caac_screen");

    private static Identifier register(String name){
        return new Identifier(TestMod.MOD_ID, name);
    }

    public static void registerNetworking(){
        TestMod.LOGGER.info("Registering custom networking for " + TestMod.MOD_ID);
    }
}
