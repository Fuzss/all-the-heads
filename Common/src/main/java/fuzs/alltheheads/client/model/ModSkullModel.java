package fuzs.alltheheads.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fuzs.alltheheads.client.resources.ClientModSkullType;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.Nullable;

public class ModSkullModel extends SkullModelBase {
    @Nullable
    private final ClientModSkullType skullType;
    private final ModelPart root;

    public ModSkullModel(ModelPart modelPart, @Nullable ClientModSkullType skullType) {
        this.skullType = skullType;
        this.root = modelPart;
    }

    public ClientModSkullType getSkullType() {
        return this.skullType;
    }

    public void copyPropertiesTo(ModSkullModel otherModel) {
        otherModel.root.xRot = this.root.xRot;
        otherModel.root.yRot = this.root.yRot;
    }

    @Override
    public void setupAnim(float p_103811_, float p_103812_, float p_103813_) {
        this.root.xRot = p_103813_ * ((float)Math.PI / 180F);
        this.root.yRot = p_103812_ * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack p_103815_, VertexConsumer p_103816_, int p_103817_, int p_103818_, float p_103819_, float p_103820_, float p_103821_, float p_103822_) {
        this.root.render(p_103815_, p_103816_, p_103817_, p_103818_, p_103819_, p_103820_, p_103821_, p_103822_);
    }
}
