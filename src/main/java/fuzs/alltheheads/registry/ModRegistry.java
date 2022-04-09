package fuzs.alltheheads.registry;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.resources.SkullManager;
import fuzs.alltheheads.resources.SkullType;
import fuzs.alltheheads.world.level.block.entity.ModSkullBlockEntity;
import fuzs.puzzleslib.registry.RegistryManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class ModRegistry {
    private static final RegistryManager REGISTRY = RegistryManager.of(AllTheHeads.MOD_ID);
    public static final RegistryObject<BlockEntityType<ModSkullBlockEntity>> MOB_HEAD_BLOCK_ENTITY_TYPE = REGISTRY.registerRawBlockEntityType("mob_head", () -> BlockEntityType.Builder.of(ModSkullBlockEntity::new, SkullManager.INSTANCE.getAllSkullBlocks()));

    public static final SkullType PIGLIN_SKULL_TYPE = new SkullType.Builder("piglin").skullSize(10.0F, 8.0F, 8.0F).build();
    public static final SkullType ZOMBIFIED_PIGLIN_SKULL_TYPE = new SkullType.Builder("zombified_piglin").skullSize(10.0F, 8.0F, 8.0F).build();
    public static final SkullType PIGLIN_BRUTE_SKULL_TYPE = new SkullType.Builder("piglin_brute").skullSize(10.0F, 8.0F, 8.0F).build();
    public static final SkullType COW_SKULL_TYPE = new SkullType.Builder("cow").skullSize(8.0F, 8.0F, 6.0F).build();
    public static final SkullType VILLAGER_SKULL_TYPE = new SkullType.Builder("villager").skullSize(8.0F, 10.0F, 8.0F).build();
    public static final SkullType ENDERMAN_SKULL_TYPE = new SkullType.Builder("enderman").build();
    public static final SkullType BLAZE_SKULL_TYPE = new SkullType.Builder("blaze").build();
    public static final SkullType SPIDER_SKULL_TYPE = new SkullType.Builder("spider").build();
    public static final SkullType CAVE_SPIDER_SKULL_TYPE = new SkullType.Builder("cave_spider").build();
    public static final SkullType WITCH_SKULL_TYPE = new SkullType.Builder("witch").skullSize(8.0F, 10.0F, 8.0F).build();
    public static final SkullType SQUID_SKULL_TYPE = new SkullType.Builder("squid").skullSize(12.0F, 16.0F, 12.0F).build();
    public static final SkullType LUCY_AXOLOTL_SKULL_TYPE = new SkullType.Builder("axolotl").variant("lucy", "{Variant:0}").skullSize(8.0F, 5.0F, 5.0F).build();
    public static final SkullType WILD_AXOLOTL_SKULL_TYPE = new SkullType.Builder("axolotl").variant("wild", "{Variant:1}").skullSize(8.0F, 5.0F, 5.0F).build();
    public static final SkullType GOLD_AXOLOTL_SKULL_TYPE = new SkullType.Builder("axolotl").variant("gold", "{Variant:2}").skullSize(8.0F, 5.0F, 5.0F).build();

    public static final CreativeModeTab ALL_THE_HEADS_CREATIVE_TAB = new CreativeModeTab(AllTheHeads.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BLAZE_SKULL_TYPE.item.get());
        }
    };

    public static void touch() {
        SkullManager.INSTANCE.register(ModRegistry.REGISTRY);
    }
}
