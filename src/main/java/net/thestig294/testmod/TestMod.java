package net.thestig294.testmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Uuids;
import net.thestig294.testmod.network.ModNetworking;
import net.thestig294.testmod.screen.ModScreenTextures;
import net.thestig294.testmod.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class TestMod implements ModInitializer {
	public static final String MOD_ID = "testmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private int tickCount = 0;
	private int nextSoundTick = -1;
	private UUID playerSoundName;

	@Override
	public void onInitialize() {
		LOGGER.info("===Starting server for " + MOD_ID);

		ModSounds.registerSounds();
		ModNetworking.registerNetworking();
		ModScreenTextures.registerTextures();

		ServerLivingEntityEvents.ALLOW_DEATH.register((entity, damageSource, damageAmount) -> {
//			entity.sendMessage(Text.literal(entity.getName() + " jumped in the caac..."));

			if (entity instanceof ServerPlayerEntity player) {
//				player.sendMessage(Text.literal(player.getName() + " jumped in the caac..."));
				player.playSound(ModSounds.JUMP_IN_THE_CAAC, SoundCategory.NEUTRAL, 1.0f, 1.0f);
				nextSoundTick = tickCount + 400;
				player.setHealth(player.defaultMaxHealth);
				playerSoundName = player.getUuid();

				ServerPlayNetworking.send(player, ModNetworking.DISPLAY_CAAC_SCREEN, PacketByteBufs.empty());

				return false;
			}

//			entity.getWorld().playSound(null, entity.getBlockPos(), ModSounds.JUMP_IN_THE_CAAC, SoundCategory.NEUTRAL, 1.0f, 1.0f);
			return true;
		});

		ServerTickEvents.START_SERVER_TICK.register(server -> {
			tickCount++;

			if (nextSoundTick == tickCount) {
//				server.getPlayerManager().getPlayer(playerSoundName).playSound(SoundEvents.BLOCK_ANVIL_PLACE, 1.0f, 1.0f);
				List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();

				for (var player : players) {
					if (player.getUuid().equals(playerSoundName)) {
						player.playSound(SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.NEUTRAL, 1.0f, 1.0f);
						ServerPlayNetworking.send(player, ModNetworking.DISPLAY_CAAC_SCREEN, PacketByteBufs.empty());
					}
				}
			}
		});
	}
}