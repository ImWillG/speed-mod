package net.imwillg.speedmod.mixin;

import net.imwillg.speedmod.SpeedMod;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "getMovementSpeed", at = @At("HEAD"), cancellable = true)
    private void modifyMovementSpeed(CallbackInfoReturnable<Float> cir) {
        // Get the current speed multiplier from SpeedMod
        float speedMultiplier = SpeedMod.getSpeed();

        // Get the original movement speed
        Float originalSpeed = cir.getReturnValue();

        // Bug fix
        if (originalSpeed == null) {
            originalSpeed = 0.1F; // Default movement speed
        }

        // Modify the player's movement speed
        cir.setReturnValue(originalSpeed * speedMultiplier);
    }
}