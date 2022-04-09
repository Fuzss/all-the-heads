package fuzs.alltheheads.registry;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.world.level.block.entity.ModSkullBlockEntity;
import fuzs.puzzleslib.registry.RegistryManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class ModRegistry {
    private static final RegistryManager REGISTRY = RegistryManager.of(AllTheHeads.MOD_ID);
    public static final RegistryObject<BlockEntityType<ModSkullBlockEntity>> MOB_HEAD_BLOCK_ENTITY_TYPE = REGISTRY.registerRawBlockEntityType("mob_head", () -> BlockEntityType.Builder.of(ModSkullBlockEntity::new, SkullManager.INSTANCE.getAllSkullBlocks()));

    public static final SkullType PIGLIN_SKULL_TYPE = new SkullType.Builder("piglin").textureLocation("textures/entity/piglin/piglin.png").skullSize(10.0F, 8.0F, 8.0F).build();;
    public static final SkullType ZOMBIFIED_PIGLIN_SKULL_TYPE = new SkullType.Builder("zombified_piglin").textureLocation("textures/entity/piglin/zombified_piglin.png").skullSize(10.0F, 8.0F, 8.0F).build();
    public static final SkullType PIGLIN_BRUTE_SKULL_TYPE = new SkullType.Builder("piglin_brute").textureLocation("textures/entity/piglin/piglin_brute.png").skullSize(10.0F, 8.0F, 8.0F).build();
    public static final SkullType COW_SKULL_TYPE = new SkullType.Builder("cow").textureLocation("textures/entity/cow/cow.png").skullSize(8.0F, 8.0F, 6.0F).modelOffsets(0.0F, -8.0F, 11.0F).build();
    public static final SkullType VILLAGER_SKULL_TYPE = new SkullType.Builder("villager").textureLocation("textures/entity/villager/villager.png").skullSize(8.0F, 10.0F, 8.0F).build();
    public static final SkullType ENDERMAN_SKULL_TYPE = new SkullType.Builder("enderman").textureLocation("textures/entity/enderman/enderman.png").modelOffsets(0.0F, 13.0F, 0.0F).build();
    public static final SkullType BLAZE_SKULL_TYPE = new SkullType.Builder("blaze").textureLocation("textures/entity/blaze.png").modelOffsets(0.0F, -4.0F, 0.0F).build();
    public static final SkullType SPIDER_SKULL_TYPE = new SkullType.Builder("spider").textureLocation("textures/entity/spider/spider.png").modelOffsets(-4.0F, -19.0F, 3.0F).build();
    public static final SkullType CAVE_SPIDER_SKULL_TYPE = new SkullType.Builder("cave_spider").textureLocation("textures/entity/spider/cave_spider.png").modelOffsets(-4.0F, -19.0F, 3.0F).build();
    public static final SkullType WITCH_SKULL_TYPE = new SkullType.Builder("witch").textureLocation("textures/entity/witch.png").skullSize(8.0F, 10.0F, 8.0F).build();
    public static final SkullType SQUID_SKULL_TYPE = new SkullType.Builder("squid").textureLocation("textures/entity/squid/squid.png").skullSize(12.0F, 16.0F, 12.0F).modelOffsets(0.0F, -16.0F, 0.0F).customHeadKey("body").build();

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
