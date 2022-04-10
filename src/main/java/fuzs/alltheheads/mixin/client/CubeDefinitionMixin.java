package fuzs.alltheheads.mixin.client;

import com.mojang.math.Vector3f;
import net.minecraft.client.model.geom.builders.CubeDefinition;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.UVPair;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;

@Mixin(CubeDefinition.class)
public abstract class CubeDefinitionMixin {
    @Nullable
    @Shadow
    @Final
    private String comment;
    @Shadow
    @Final
    private Vector3f origin;
    @Shadow
    @Final
    private Vector3f dimensions;
    @Shadow
    @Final
    private CubeDeformation grow;
    @Shadow
    @Final
    private boolean mirror;
    @Shadow
    @Final
    private UVPair texCoord;
    @Shadow
    @Final
    private UVPair texScale;
}
