package fuzs.alltheheads.client.handler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import fuzs.alltheheads.AllTheHeads;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;

public class ModelBakeHandler {
    @SubscribeEvent
    public void onModelBake(final ModelBakeEvent evt) {
        for (Map.Entry<ResourceLocation, BakedModel> entry : evt.getModelRegistry().entrySet()) {
            if (entry.getKey().getPath().contains("piglin")) {
                AllTheHeads.LOGGER.info("{}", entry);
                BlockModel itemModel = new BlockModel(new ResourceLocation("item/template_skull"), Lists.newArrayList(), Maps.newHashMap(), true, null, ItemTransforms.NO_TRANSFORMS, Lists.newArrayList());
            }
        }
    }

    public static void extracted(ResourceLocation p_119363_, String stage) {
        AllTheHeads.LOGGER.info("{}, {}", p_119363_, stage);
    }
}
