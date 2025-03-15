package net.imwillg.speedmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpeedMod implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "speedmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static KeyBinding speedKeyBinding;

	private static int speedIndex = 1;

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}

	@Override
	public void onInitializeClient() {
		// Initialize the keybinding for the "P" key
		speedKeyBinding = new KeyBinding(
				"key.speedmod.change",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_P,
				"category.speedmod"
		);

		// Register the tick callback to check for key press
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (speedKeyBinding.wasPressed()) {
				incrementSpeed();
				LOGGER.info("Speed Index: " + speedIndex);
			}
		});
	}

	// Method to increment the speed
	public static void incrementSpeed() {
		// Cycle speed through 1 to 5
		speedIndex = (speedIndex % 5) + 1;
	}

	// Method to get the current speed
	public static int getSpeed() {
		return speedIndex;
	}
}