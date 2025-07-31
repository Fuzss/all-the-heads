package fuzs.alltheheads.registry;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.resources.SkullManager;
import fuzs.alltheheads.world.item.ModSkullBlockItem;
import fuzs.alltheheads.world.level.block.ModSkullBlock;
import fuzs.alltheheads.world.level.block.ModWallSkullBlock;
import fuzs.alltheheads.world.level.block.entity.ModSkullBlockEntity;
import fuzs.puzzleslib.registry.RegistryManager;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

public class ModRegistry {
    public static final SkullBlock.Type MOB_SKULL_BLOCK_TYPE = new SkullBlock.Type() {};

    public static final CreativeModeTab DEFAULT_CREATIVE_TAB = new CreativeModeTab(AllTheHeads.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return ModSkullBlockItem.createSkullTypeStack(SkullManager.INSTANCE.getSkullType("blaze"));
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }
    }.setBackgroundSuffix("item_search.png");
    public static final CreativeModeTab VILLAGERS_CREATIVE_TAB = new CreativeModeTab(AllTheHeads.MOD_ID + "villagers") {
        @Override
        public ItemStack makeIcon() {
            return ModSkullBlockItem.createSkullTypeStack(SkullManager.INSTANCE.getSkullType("villager#plains_librarian"));
        }
    };

    private static final RegistryManager REGISTRY = RegistryManager.of(AllTheHeads.MOD_ID);
    public static final RegistryObject<Block> MOB_HEAD_BLOCK = REGISTRY.registerBlock("mob_head", () -> new ModSkullBlock(MOB_SKULL_BLOCK_TYPE, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
    public static final RegistryObject<Block> MOB_WALL_HEAD_BLOCK = REGISTRY.registerBlock("mob_wall_head", () -> new ModWallSkullBlock(MOB_SKULL_BLOCK_TYPE, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F).lootFrom(() -> MOB_HEAD_BLOCK.get())));
    public static final RegistryObject<Item> MOB_HEAD_ITEM = REGISTRY.registerItem("mob_head", () -> new ModSkullBlockItem(MOB_HEAD_BLOCK.get(), MOB_WALL_HEAD_BLOCK.get(), new Item.Properties().tab(DEFAULT_CREATIVE_TAB).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<BlockEntityType<ModSkullBlockEntity>> MOB_HEAD_BLOCK_ENTITY_TYPE = REGISTRY.registerRawBlockEntityType("mob_head", () -> BlockEntityType.Builder.of(ModSkullBlockEntity::new, MOB_HEAD_BLOCK.get(), MOB_WALL_HEAD_BLOCK.get()));

    public static void touch() {

    }
}
