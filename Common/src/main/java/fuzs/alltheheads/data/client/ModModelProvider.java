package fuzs.alltheheads.data.client;

import fuzs.alltheheads.init.ModRegistry;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.client.data.v2.models.ItemModelGenerationHelper;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.renderer.special.SkullSpecialRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addBlockModels(BlockModelGenerators blockModelGenerators) {
        ItemModelGenerationHelper.generateHead(ModRegistry.MOB_HEAD_BLOCK.value(),
                ModRegistry.MOB_WALL_HEAD_BLOCK.value(),
                ModRegistry.MOB_SKULL_BLOCK_TYPE,
                blockModelGenerators);
    }

    public final void createHead(Block headBlock, Block wallHeadBlock, SkullBlock.Type type, ResourceLocation modelLocation) {
        MultiVariant multiVariant = BlockModelGenerators.plainVariant(ModelLocationUtils.decorateBlockModelLocation("skull"));
        this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(headBlock, multiVariant));
        this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(wallHeadBlock, multiVariant));
        this.itemModelOutput.accept(headBlock.asItem(), ItemModelUtils.specialModel(modelLocation, new SkullSpecialRenderer.Unbaked(type)));
    }
}
