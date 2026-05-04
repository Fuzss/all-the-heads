package fuzs.alltheheads.common.init;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.MapCodec;
import fuzs.alltheheads.common.AllTheHeads;
import fuzs.alltheheads.common.advancements.critereon.*;
import fuzs.alltheheads.common.init.headtype.MonsterHeadType;
import fuzs.alltheheads.common.init.headtype.VillagerHeadType;
import fuzs.alltheheads.common.world.item.MobHeadItem;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.level.block.MobHeadBlock;
import fuzs.alltheheads.common.world.level.block.MobHeadSkullBlock;
import fuzs.alltheheads.common.world.level.block.entity.MobHeadBlockEntity;
import fuzs.puzzleslib.common.api.init.v3.registry.ContentRegistrationHelper;
import fuzs.puzzleslib.common.api.init.v3.registry.RegistryManager;
import fuzs.puzzleslib.common.api.init.v3.tags.TagFactory;
import fuzs.puzzleslib.common.api.util.v1.CommonHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.waypoints.Waypoint;

public class ModRegistry {
    public static final ResourceKey<Registry<HeadType>> HEAD_REGISTRY_KEY = ResourceKey.createRegistryKey(AllTheHeads.id(
            "head"));
    public static final RegistrySetBuilder REGISTRY_SET_BUILDER = new RegistrySetBuilder().add(HEAD_REGISTRY_KEY,
            HeadTypes::bootstrap);
    public static final SkullBlock.Type MOB_SKULL_BLOCK_TYPE = ContentRegistrationHelper.registerSkullBlockType(
            AllTheHeads.id("mob"));

    static final RegistryManager REGISTRIES = RegistryManager.from(AllTheHeads.MOD_ID);
    public static final Holder.Reference<DataComponentType<Holder<HeadType>>> HEAD_TYPE_DATA_COMPONENT_TYPE = REGISTRIES.registerDataComponentType(
            "head_type",
            (DataComponentType.Builder<Holder<HeadType>> builder) -> {
                return builder.persistent(HeadType.CODEC).networkSynchronized(HeadType.STREAM_CODEC).cacheEncoding();
            });
    public static final Holder.Reference<Block> MOB_HEAD_BLOCK = REGISTRIES.registerBlock("mob_head",
            MobHeadBlock::new,
            () -> BlockBehaviour.Properties.of()
                    .instrument(NoteBlockInstrument.CUSTOM_HEAD)
                    .strength(1.0F)
                    .pushReaction(PushReaction.DESTROY));
    public static final Holder.Reference<Block> MOB_WALL_HEAD_BLOCK = REGISTRIES.registerBlock("mob_wall_head",
            MobHeadSkullBlock::new,
            () -> Blocks.wallVariant(MOB_HEAD_BLOCK.value(), true).strength(1.0F).pushReaction(PushReaction.DESTROY));
    public static final Holder.Reference<Item> MOB_HEAD_ITEM = REGISTRIES.registerBlockItem(MOB_HEAD_BLOCK,
            (Block block, Item.Properties properties) -> new MobHeadItem(block,
                    MOB_WALL_HEAD_BLOCK.value(),
                    properties),
            () -> Waypoint.addHideAttribute(new Item.Properties())
                    .rarity(Rarity.UNCOMMON)
                    .equippableUnswappable(EquipmentSlot.HEAD));
    public static final Holder.Reference<BlockEntityType<MobHeadBlockEntity>> MOB_HEAD_BLOCK_ENTITY_TYPE = REGISTRIES.registerBlockEntityType(
            "head",
            MobHeadBlockEntity::new,
            () -> ImmutableSet.of(MOB_HEAD_BLOCK.value(), MOB_WALL_HEAD_BLOCK.value()));
    public static final Holder.Reference<MapCodec<BeePredicate>> BEE_ENTITY_SUB_PREDICATE_TYPE = REGISTRIES.register(
            Registries.ENTITY_SUB_PREDICATE_TYPE,
            "bee",
            () -> BeePredicate.CODEC);
    public static final Holder.Reference<MapCodec<CopperGolemPredicate>> COPPER_GOLEM_ENTITY_SUB_PREDICATE_TYPE = REGISTRIES.register(
            Registries.ENTITY_SUB_PREDICATE_TYPE,
            "copper_golem",
            () -> CopperGolemPredicate.CODEC);
    public static final Holder.Reference<MapCodec<CreeperPredicate>> CREEPER_ENTITY_SUB_PREDICATE_TYPE = REGISTRIES.register(
            Registries.ENTITY_SUB_PREDICATE_TYPE,
            "creeper",
            () -> CreeperPredicate.CODEC);
    public static final Holder.Reference<MapCodec<GhastPredicate>> GHAST_ENTITY_SUB_PREDICATE_TYPE = REGISTRIES.register(
            Registries.ENTITY_SUB_PREDICATE_TYPE,
            "ghast",
            () -> GhastPredicate.CODEC);
    public static final Holder.Reference<MapCodec<HorsePredicate>> HORSE_ENTITY_SUB_PREDICATE_TYPE = REGISTRIES.register(
            Registries.ENTITY_SUB_PREDICATE_TYPE,
            "horse",
            () -> HorsePredicate.CODEC);
    public static final Holder.Reference<MapCodec<PandaPredicate>> PANDA_ENTITY_SUB_PREDICATE_TYPE = REGISTRIES.register(
            Registries.ENTITY_SUB_PREDICATE_TYPE,
            "panda",
            () -> PandaPredicate.CODEC);
    public static final Holder.Reference<MapCodec<StriderPredicate>> STRIDER_ENTITY_SUB_PREDICATE_TYPE = REGISTRIES.register(
            Registries.ENTITY_SUB_PREDICATE_TYPE,
            "strider",
            () -> StriderPredicate.CODEC);
    public static final Holder.Reference<MapCodec<TropicalFishPredicate>> TROPICAL_FISH_ENTITY_SUB_PREDICATE_TYPE = REGISTRIES.register(
            Registries.ENTITY_SUB_PREDICATE_TYPE,
            "tropical_fish",
            () -> TropicalFishPredicate.CODEC);
    public static final Holder.Reference<MapCodec<VexPredicate>> VEX_ENTITY_SUB_PREDICATE_TYPE = REGISTRIES.register(
            Registries.ENTITY_SUB_PREDICATE_TYPE,
            "vex",
            () -> VexPredicate.CODEC);
    public static final Holder.Reference<MapCodec<VillagerDataPredicate>> VILLAGER_DATA_ENTITY_SUB_PREDICATE_TYPE = REGISTRIES.register(
            Registries.ENTITY_SUB_PREDICATE_TYPE,
            "villager_data",
            () -> VillagerDataPredicate.CODEC);
    public static final Holder.Reference<MapCodec<WolfPredicate>> WOLF_ENTITY_SUB_PREDICATE_TYPE = REGISTRIES.register(
            Registries.ENTITY_SUB_PREDICATE_TYPE,
            "wolf",
            () -> WolfPredicate.CODEC);
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab("main",
            () -> createDisplayItemStack(MonsterHeadType.BLAZE),
            (CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) -> {
                output.accept(Items.SKELETON_SKULL);
                output.accept(Items.WITHER_SKELETON_SKULL);
                output.accept(Items.PLAYER_HEAD);
                output.accept(Items.ZOMBIE_HEAD);
                output.accept(Items.CREEPER_HEAD);
                output.accept(Items.PIGLIN_HEAD);
                output.accept(Items.DRAGON_HEAD);
                itemDisplayParameters.holders()
                        .lookupOrThrow(HEAD_REGISTRY_KEY)
                        .listElements()
                        .filter((Holder.Reference<HeadType> holder) -> !holder.is(ModRegistry.VILLAGER_LIKE_HEAD_TYPE_TAG))
                        .map(MobHeadItem::createHead)
                        .forEach(output::accept);
            },
            true);
    public static final Holder.Reference<CreativeModeTab> VILLAGER_CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab(
            "villager",
            () -> createDisplayItemStack(VillagerHeadType.PLAINS_LIBRARIAN_VILLAGER),
            (CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) -> {
                itemDisplayParameters.holders()
                        .lookupOrThrow(HEAD_REGISTRY_KEY)
                        .listElements()
                        .filter((Holder.Reference<HeadType> holder) -> holder.is(ModRegistry.VILLAGER_LIKE_HEAD_TYPE_TAG))
                        .map(MobHeadItem::createHead)
                        .forEach(output::accept);
            },
            true);

    static final TagFactory TAGS = TagFactory.make(AllTheHeads.MOD_ID);
    public static final TagKey<HeadType> VILLAGER_LIKE_HEAD_TYPE_TAG = TAGS.registerTagKey(HEAD_REGISTRY_KEY,
            "villager_like");

    public static void bootstrap() {
        ModLootTables.bootstrap();
    }

    private static ItemStack createDisplayItemStack(ResourceKey<HeadType> resourceKey) {
        RegistryAccess registries = CommonHelper.getRegistryAccess();
        if (registries != null) {
            return MobHeadItem.createHead(registries, resourceKey);
        } else {
            return new ItemStack(MOB_HEAD_ITEM);
        }
    }
}
