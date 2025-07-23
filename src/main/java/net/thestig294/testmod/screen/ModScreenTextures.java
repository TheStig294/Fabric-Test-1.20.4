package net.thestig294.testmod.screen;

import net.minecraft.util.Identifier;
import net.thestig294.testmod.TestMod;

public class ModScreenTextures {
    public static final Identifier CACC_SCREEN_1 = register("textures/gui/caac_screen_1.png");
    public static final Identifier CACC_SCREEN_2 = register("textures/gui/caac_screen_2.png");

    private static Identifier register(String name){
        return new Identifier(TestMod.MOD_ID, name);
    }

    public static void registerTextures(){
        TestMod.LOGGER.info("Registering custom screen textures for " + TestMod.MOD_ID);
    }
}
