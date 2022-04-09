package fuzs.alltheheads.registry;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.resources.SkullManager;
import fuzs.alltheheads.world.level.block.entity.ModSkullBlockEntity;
import fuzs.puzzleslib.registry.RegistryManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class ModRegistry {
    private static final RegistryManager REGISTRY = RegistryManager.of(AllTheHeads.MOD_ID);
    public static final RegistryObject<BlockEntityType<ModSkullBlockEntity>> MOB_HEAD_BLOCK_ENTITY_TYPE = REGISTRY.registerRawBlockEntityType("mob_head", () -> BlockEntityType.Builder.of(ModSkullBlockEntity::new, SkullManager.INSTANCE.getAllSkullBlocks()));

    public static final CreativeModeTab ALL_THE_HEADS_CREATIVE_TAB = new CreativeModeTab(AllTheHeads.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(SkullManager.INSTANCE.getSkullType("blaze").item.get());
        }
    };

    public static void touch() {
        SkullManager.INSTANCE.register(ModRegistry.REGISTRY);
    }
}
