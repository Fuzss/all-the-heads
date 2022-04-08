package fuzs.alltheheads.registry;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.world.item.ModStandingAndWallBlockItem;
import fuzs.alltheheads.world.level.block.ModSkullBlock;
import fuzs.alltheheads.world.level.block.ModWallSkullBlock;
import fuzs.alltheheads.world.level.block.entity.ModSkullBlockEntity;
import fuzs.puzzleslib.registry.RegistryManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRegistry {
    public static final SkullBlock.Type PIGLIN_SKULL_BLOCK_TYPE = new ModSkullType.Builder().mobType("piglin").textureLocation("textures/entity/piglin/piglin.png").modelLayerLocation("piglin").skull().build();

    public static final RegistryManager REGISTRY = RegistryManager.of(AllTheHeads.MOD_ID);
    public static final RegistryObject<BlockEntityType<ModSkullBlockEntity>> MOB_HEAD_BLOCK_ENTITY_TYPE = REGISTRY.registerRawBlockEntityType("mob_head", () -> BlockEntityType.Builder.of(ModSkullBlockEntity::new, SkullManager.INSTANCE.getAllSkullBlocks()));
    public static final RegistryObject<Item> PIGLIN_HEAD_ITEM = RegistryObject.create(new ResourceLocation(AllTheHeads.MOD_ID, "piglin_head"), ForgeRegistries.ITEMS);

    public static final CreativeModeTab ALL_THE_HEADS_CREATIVE_TAB = new CreativeModeTab(AllTheHeads.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModRegistry.PIGLIN_HEAD_ITEM.get());
        }
    };

    public static final RegistryObject<Block> PIGLIN_HEAD_BLOCK = REGISTRY.registerBlock("piglin_head", () -> new ModSkullBlock(PIGLIN_SKULL_BLOCK_TYPE, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
    public static final RegistryObject<Block> PIGLIN_WALL_HEAD_BLOCK = REGISTRY.registerBlock("piglin_wall_head", () -> new ModWallSkullBlock(PIGLIN_SKULL_BLOCK_TYPE, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F).lootFrom(() -> PIGLIN_HEAD_BLOCK.get())));
//    public static final RegistryObject<Item> PIGLIN_HEAD_ITEM = REGISTRY.registerItem("piglin_head", () -> new ModStandingAndWallBlockItem(PIGLIN_HEAD_BLOCK.get(), PIGLIN_WALL_HEAD_BLOCK.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_DECORATIONS).rarity(Rarity.UNCOMMON)));

    public static void touch() {

    }
}
