package fuzs.alltheheads.data.client;

import fuzs.alltheheads.client.renderer.special.MobHeadSpecialRenderer;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.client.data.v2.models.ModelLocationHelper;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addBlockModels(BlockModelGenerators blockModelGenerators) {
        this.generateHead(ModRegistry.MOB_HEAD_BLOCK.value(),
                ModRegistry.MOB_WALL_HEAD_BLOCK.value(),
                blockModelGenerators);
    }

    /**
     * @see fuzs.puzzleslib.api.client.data.v2.models.ItemModelGenerationHelper
     */
    public final void generateHead(Block headBlock, Block wallHeadBlock, BlockModelGenerators blockModelGenerators) {
        this.createHead(headBlock,
                wallHeadBlock,
                ModelLocationHelper.getItemModel(ResourceLocationHelper.withDefaultNamespace("template_skull")),
                blockModelGenerators);
    }

    /**
     * @see BlockModelGenerators#createHead(Block, Block, SkullBlock.Type, ResourceLocation)
     */
    public final void createHead(Block headBlock, Block wallHeadBlock, ResourceLocation modelLocation, BlockModelGenerators blockModelGenerators) {
        MultiVariant multiVariant = BlockModelGenerators.plainVariant(ModelLocationHelper.getBlockModel(
                ResourceLocationHelper.withDefaultNamespace("skull")));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(headBlock, multiVariant));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(wallHeadBlock,
                multiVariant));
        blockModelGenerators.itemModelOutput.accept(headBlock.asItem(),
                ItemModelUtils.specialModel(modelLocation, new MobHeadSpecialRenderer.Unbaked()));
    }
}
