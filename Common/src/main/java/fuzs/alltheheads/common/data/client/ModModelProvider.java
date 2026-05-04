package fuzs.alltheheads.common.data.client;

import fuzs.alltheheads.common.client.renderer.special.MobHeadSpecialRenderer;
import fuzs.alltheheads.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.common.api.client.data.v2.models.ModelLocationHelper;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.resources.Identifier;
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
     * @see fuzs.puzzleslib.common.api.client.data.v2.models.ItemModelGenerationHelper#generateHead(Block, Block,
     *         SkullBlock.Type, BlockModelGenerators)
     */
    public final void generateHead(Block headBlock, Block wallHeadBlock, BlockModelGenerators blockModelGenerators) {
        this.createHead(headBlock,
                wallHeadBlock,
                ModelLocationHelper.getItemModel(Identifier.withDefaultNamespace("template_skull")),
                blockModelGenerators);
    }

    /**
     * @see BlockModelGenerators#createHead(Block, Block, SkullBlock.Type, Identifier)
     */
    public final void createHead(Block headBlock, Block wallHeadBlock, Identifier modelLocation, BlockModelGenerators blockModelGenerators) {
        MultiVariant multiVariant = BlockModelGenerators.plainVariant(ModelLocationHelper.getBlockModel(Identifier.withDefaultNamespace(
                "skull")));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(headBlock, multiVariant));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(wallHeadBlock,
                multiVariant));
        blockModelGenerators.itemModelOutput.accept(headBlock.asItem(),
                ItemModelUtils.specialModel(modelLocation, new MobHeadSpecialRenderer.Unbaked()));
    }
}
