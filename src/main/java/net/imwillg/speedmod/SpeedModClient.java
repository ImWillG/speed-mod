package net.imwillg.speedmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class SpeedModClient implements ClientModInitializer {
    public static KeyBinding speedKeyBinding;

    @Override
    public void onInitializeClient() {
        // Initialize the keybinding for the "P" key
        speedKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.speedmod.change",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_P,
                "category.speedmod"
        ));
        System.out.println("Speed Mod Keybind Initialised");

        // Register the tick callback to check for key press
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (speedKeyBinding.wasPressed()) {
                SpeedMod.incrementSpeed();

                // Send a message to the action bar
                if (client.player != null) {
                    Text message;
                    if (SpeedMod.getSpeed() == 1) {
                        message = Text.literal("Speed: " + SpeedMod.getSpeed() + " (default)").formatted(Formatting.WHITE);
                    } else {
                        message = Text.literal("Speed: " + SpeedMod.getSpeed()).formatted(Formatting.WHITE);
                    }
                    client.player.sendMessage(message, true); // Send to the action bar
                }

            }
        });
    }
}