package net.thestig294.testmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.util.Identifier;
import net.thestig294.testmod.network.ModNetworking;
import net.thestig294.testmod.screen.ModScreenTextures;

public class TestModClient implements ClientModInitializer {
    private boolean displayCaacScreen = false;
    private float totalTickDelta = 0.0f;

    @Override
    public void onInitializeClient() {
        TestMod.LOGGER.info("===Starting client for " + TestMod.MOD_ID);

        ClientPlayNetworking.registerGlobalReceiver(ModNetworking.DISPLAY_CAAC_SCREEN,
                (client, handler, buf, responseSender) -> client.execute(() -> {
                    totalTickDelta = 0.0f;
                    displayCaacScreen = !displayCaacScreen;
                }));

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            totalTickDelta += tickDelta;

            if (displayCaacScreen) {
                int width = drawContext.getScaledWindowWidth();
                int height = drawContext.getScaledWindowHeight();
                Identifier screen = totalTickDelta < 80.0f ? ModScreenTextures.CACC_SCREEN_1 : ModScreenTextures.CACC_SCREEN_2;

                drawContext.drawTexture(screen,0,0,0,0, width, height, width, height);
            }
        });
    }
}
