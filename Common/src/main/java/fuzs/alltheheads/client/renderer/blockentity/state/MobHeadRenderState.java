package fuzs.alltheheads.client.renderer.blockentity.state;

import fuzs.alltheheads.world.item.component.headtype.HeadType;
import net.minecraft.client.renderer.blockentity.state.SkullBlockRenderState;
import net.minecraft.core.Holder;
import org.jspecify.annotations.Nullable;

public class MobHeadRenderState extends SkullBlockRenderState {
    @Nullable
    public Holder<HeadType> headType;
    public float time;
    public int outlineColor;
    public boolean guiOffset;

    public MobHeadRenderState() {
        this.direction = null;
    }

    public static MobHeadRenderState create(int packedLight, float rotationDegrees, float animationProgress, @Nullable Holder<HeadType> headType, float time, int outlineColor, boolean guiOffset) {
        MobHeadRenderState mobHeadRenderState = new MobHeadRenderState();
        mobHeadRenderState.lightCoords = packedLight;
        mobHeadRenderState.rotationDegrees = rotationDegrees;
        mobHeadRenderState.animationProgress = animationProgress;
        mobHeadRenderState.headType = headType;
        mobHeadRenderState.time = time;
        mobHeadRenderState.outlineColor = outlineColor;
        mobHeadRenderState.guiOffset = guiOffset;
        return mobHeadRenderState;
    }
}
