package fuzs.alltheheads.data.client;

import fuzs.alltheheads.init.ModRegistry;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.client.data.v2.models.ModelLocationHelper;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addBlockModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.blockEntityModels(ModelLocationHelper.getBlockModel(ResourceLocation.withDefaultNamespace(
                        "skull")), Blocks.SOUL_SAND)
                .createWithCustomBlockItemModel(ModelTemplates.SKULL_INVENTORY, ModRegistry.MOB_HEAD_BLOCK.value())
                .createWithoutBlockItem(ModRegistry.MOB_WALL_HEAD_BLOCK.value());
    }
}
