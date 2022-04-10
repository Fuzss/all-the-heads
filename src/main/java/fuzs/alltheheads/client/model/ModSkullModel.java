package fuzs.alltheheads.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import fuzs.alltheheads.client.resources.ClientSkullType;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;

import java.util.List;

public class ModSkullModel extends SkullModelBase {
    private final ClientSkullType skullType;
    private final List<ModelPart> headParts;

    public ModSkullModel(ModelPart modelPart, ClientSkullType skullType) {
        this.skullType = skullType;
        this.headParts = skullType.getModelPartHeadKey().stream().map(headPart -> getModelPartHeadKey(modelPart, headPart)).toList();
    }

    private static ModelPart getModelPartHeadKey(ModelPart modelPart, String[] headKey) {
        if (headKey.length == 0) throw new IllegalArgumentException("Head key path cannot be empty");
        ModelPart head = modelPart.getChild(headKey[0]);
        for (int i = 1; i < headKey.length; i++) {
            head = head.getChild(headKey[i]);
        }
        return head;
    }

    @Override
    public void setupAnim(float p_103811_, float p_103812_, float p_103813_) {
        this.headParts.forEach(headPart -> {
            headPart.loadPose(PartPose.ZERO);
            headPart.xRot = p_103813_ * ((float)Math.PI / 180F);
            headPart.yRot = p_103812_ * ((float)Math.PI / 180F);
        });
    }

    @Override
    public void renderToBuffer(PoseStack p_103815_, VertexConsumer p_103816_, int p_103817_, int p_103818_, float p_103819_, float p_103820_, float p_103821_, float p_103822_) {
        Vector3f offsets = this.skullType.getModelOffsets();
        p_103815_.translate(offsets.x() / 16.0F, offsets.y() / 16.0F, offsets.z() / 16.0F);
        float scale = this.skullType.getModelScale();
        if (scale != 1.0F) {
            p_103815_.scale(scale, scale, scale);
        }
        this.headParts.forEach(headPart -> headPart.render(p_103815_, p_103816_, p_103817_, p_103818_, p_103819_, p_103820_, p_103821_, p_103822_));
    }
}
