package fuzs.alltheheads.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;

public class ModSkullModel extends SkullModel {
    public ModSkullModel(ModelPart p_170945_) {
        super(p_170945_);
    }

    @Override
    public void renderToBuffer(PoseStack p_103815_, VertexConsumer p_103816_, int p_103817_, int p_103818_, float p_103819_, float p_103820_, float p_103821_, float p_103822_) {
        this.head.render(p_103815_, p_103816_, p_103817_, p_103818_, p_103819_, p_103820_, p_103821_, p_103822_);
    }
}
