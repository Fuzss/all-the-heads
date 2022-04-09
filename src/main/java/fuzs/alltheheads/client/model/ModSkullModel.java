package fuzs.alltheheads.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import fuzs.alltheheads.registry.ModSkullType;
import fuzs.puzzleslib.util.PuzzlesUtil;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelPart;

public class ModSkullModel extends SkullModelBase {
    private final ModelPart head;
    private final float offsetX;
    private final float offsetY;
    private final float offsetZ;

    public ModSkullModel(ModelPart modelPart, ModSkullType skullType) {
        this.head = modelPart.getChild(skullType.getModelPartHeadKey());
        Vector3f modelOffsets = skullType.getModelOffsets();
        this.offsetX = this.head.x + modelOffsets.x();
        this.offsetY = this.head.y + modelOffsets.y();
        this.offsetZ = this.head.z + modelOffsets.z();
    }

    @Override
    public void setupAnim(float p_103811_, float p_103812_, float p_103813_) {
        this.head.yRot = p_103812_ * ((float)Math.PI / 180F);
        this.head.xRot = p_103813_ * ((float)Math.PI / 180F);
        this.head.x = this.offsetX;
        this.head.y = this.offsetY;
        this.head.z = this.offsetZ;
    }

    @Override
    public void renderToBuffer(PoseStack p_103815_, VertexConsumer p_103816_, int p_103817_, int p_103818_, float p_103819_, float p_103820_, float p_103821_, float p_103822_) {
        this.head.render(p_103815_, p_103816_, p_103817_, p_103818_, p_103819_, p_103820_, p_103821_, p_103822_);
    }
}
