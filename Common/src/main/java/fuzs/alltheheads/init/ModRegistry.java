package fuzs.alltheheads.init;

import com.google.common.collect.ImmutableSet;
import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.world.item.ModSkullBlockItem;
import fuzs.alltheheads.world.item.component.HeadType;
import fuzs.alltheheads.world.level.block.MobHeadBlock;
import fuzs.alltheheads.world.level.block.MobHeadSkullBlock;
import fuzs.alltheheads.world.level.block.entity.MobHeadBlockEntity;
import fuzs.puzzleslib.api.init.v3.registry.ContentRegistrationHelper;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
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

import java.util.function.Predicate;

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
            (Block block, Item.Properties properties) -> new ModSkullBlockItem(block,
                    MOB_WALL_HEAD_BLOCK.value(),
                    properties),
            () -> Waypoint.addHideAttribute(new Item.Properties())
                    .rarity(Rarity.UNCOMMON)
                    .equippableUnswappable(EquipmentSlot.HEAD));
    public static final Holder.Reference<BlockEntityType<MobHeadBlockEntity>> MOB_HEAD_BLOCK_ENTITY_TYPE = REGISTRIES.registerBlockEntityType(
            "head",
            MobHeadBlockEntity::new,
            () -> ImmutableSet.of(MOB_HEAD_BLOCK.value(), MOB_WALL_HEAD_BLOCK.value()));
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab("main",
            () -> new ItemStack(MOB_HEAD_ITEM),
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
                        .filterElements(Predicate.not(ModRegistry::isVillagerLike))
                        .listElements()
                        .map(ModSkullBlockItem::createHead)
                        .forEach(output::accept);
            },
            true);
    public static final Holder.Reference<CreativeModeTab> VILLAGER_CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab(
            "villager",
            () -> new ItemStack(MOB_HEAD_ITEM),
            (CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) -> {
                itemDisplayParameters.holders()
                        .lookupOrThrow(HEAD_REGISTRY_KEY)
                        .filterElements(ModRegistry::isVillagerLike)
                        .listElements()
                        .map(ModSkullBlockItem::createHead)
                        .forEach(output::accept);
            },
            true);

    public static void bootstrap() {
        // NO-OP
    }

    private static boolean isVillagerLike(HeadType headType) {
        return isVillagerLike(headType.entityType().value());
    }

    private static boolean isVillagerLike(EntityType<?> entityType) {
        return entityType == EntityType.VILLAGER || entityType == EntityType.ZOMBIE_VILLAGER;
    }
}
