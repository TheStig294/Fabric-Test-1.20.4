package net.thestig294.testmod.screen;

import net.minecraft.util.Identifier;
import net.thestig294.testmod.TestMod;

public class ModScreenTextures {
    public static final Identifier CACC_SCREEN = register("textures/gui/caac_screen.png");

    private static Identifier register(String name){
        return new Identifier(TestMod.MOD_ID, name);
    }

    public static void registerTextures(){
        TestMod.LOGGER.info("Registering custom screen textures for " + TestMod.MOD_ID);
    }
}
