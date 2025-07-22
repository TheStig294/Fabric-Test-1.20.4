package net.thestig294.testmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.thestig294.testmod.network.ModNetworking;
import net.thestig294.testmod.screen.ModScreenTextures;

public class TestModClient implements ClientModInitializer {
    private boolean displayCaacScreen = false;

    @Override
    public void onInitializeClient() {
        TestMod.LOGGER.info("===Starting client for " + TestMod.MOD_ID);

        ClientPlayNetworking.registerGlobalReceiver(ModNetworking.DISPLAY_CAAC_SCREEN,
                (client, handler, buf, responseSender) -> {
            client.execute(() -> {
                displayCaacScreen = !displayCaacScreen;
            });
                });

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (displayCaacScreen) {
                drawContext.drawTexture(ModScreenTextures.CACC_SCREEN, 0,0,0,0, drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight());
            }
        });
    }
}
