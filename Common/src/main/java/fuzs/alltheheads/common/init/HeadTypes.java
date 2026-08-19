package fuzs.alltheheads.common.init;

import fuzs.alltheheads.common.init.headtype.*;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.predicates.entity.EntityTypePredicate;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;

import java.util.function.Consumer;
import java.util.function.Function;

public class HeadTypes {

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        AnimalHeadType.bootstrapHeadTypes(context);
        AquaticHeadType.bootstrapHeadTypes(context);
        AxolotlHeadType.bootstrapHeadTypes(context);
        BeeHeadType.bootstrapHeadTypes(context);
        CamelHeadType.bootstrapHeadTypes(context);
        FelineHeadType.bootstrapHeadTypes(context);
        ChickenHeadType.bootstrapHeadTypes(context);
        CopperGolemHeadType.bootstrapHeadTypes(context);
        CowHeadType.bootstrapHeadTypes(context);
        CubeMobHeadType.bootstrapHeadTypes(context);
        FoxHeadType.bootstrapHeadTypes(context);
        FrogHeadType.bootstrapHeadTypes(context);
        GhastHeadType.bootstrapHeadTypes(context);
        GuardianHeadType.bootstrapHeadTypes(context);
        HoglinHeadType.bootstrapHeadTypes(context);
        EquineHeadType.bootstrapHeadTypes(context);
        IllagerHeadType.bootstrapHeadTypes(context);
        LlamaHeadType.bootstrapHeadTypes(context);
        MonsterHeadType.bootstrapHeadTypes(context);
        NautilusHeadType.bootstrapHeadTypes(context);
        PandaHeadType.bootstrapHeadTypes(context);
        ParrotHeadType.bootstrapHeadTypes(context);
        PigHeadType.bootstrapHeadTypes(context);
        PiglinHeadType.bootstrapHeadTypes(context);
        RabbitHeadType.bootstrapHeadTypes(context);
        SheepHeadType.bootstrapHeadTypes(context);
        SpiderHeadType.bootstrapHeadTypes(context);
        StriderHeadType.bootstrapHeadTypes(context);
        TropicalFishHeadType.bootstrapHeadTypes(context);
        VexHeadType.bootstrapHeadTypes(context);
        VillagerHeadType.bootstrapHeadTypes(context);
        WitherHeadType.bootstrapHeadTypes(context);
        WolfHeadType.bootstrapHeadTypes(context);
        ZombieVillagerHeadType.bootstrapHeadTypes(context);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        AnimalHeadType.bootstrapLootItemConditions(context);
        AquaticHeadType.bootstrapLootItemConditions(context);
        AxolotlHeadType.bootstrapLootItemConditions(context);
        BeeHeadType.bootstrapLootItemConditions(context);
        CamelHeadType.bootstrapLootItemConditions(context);
        FelineHeadType.bootstrapLootItemConditions(context);
        ChickenHeadType.bootstrapLootItemConditions(context);
        CopperGolemHeadType.bootstrapLootItemConditions(context);
        CowHeadType.bootstrapLootItemConditions(context);
        CubeMobHeadType.bootstrapLootItemConditions(context);
        FoxHeadType.bootstrapLootItemConditions(context);
        FrogHeadType.bootstrapLootItemConditions(context);
        GhastHeadType.bootstrapLootItemConditions(context);
        GuardianHeadType.bootstrapLootItemConditions(context);
        HoglinHeadType.bootstrapLootItemConditions(context);
        EquineHeadType.bootstrapLootItemConditions(context);
        IllagerHeadType.bootstrapLootItemConditions(context);
        LlamaHeadType.bootstrapLootItemConditions(context);
        MonsterHeadType.bootstrapLootItemConditions(context);
        NautilusHeadType.bootstrapLootItemConditions(context);
        PandaHeadType.bootstrapLootItemConditions(context);
        ParrotHeadType.bootstrapLootItemConditions(context);
        PigHeadType.bootstrapLootItemConditions(context);
        PiglinHeadType.bootstrapLootItemConditions(context);
        RabbitHeadType.bootstrapLootItemConditions(context);
        SheepHeadType.bootstrapLootItemConditions(context);
        SpiderHeadType.bootstrapLootItemConditions(context);
        StriderHeadType.bootstrapLootItemConditions(context);
        TropicalFishHeadType.bootstrapLootItemConditions(context);
        VexHeadType.bootstrapLootItemConditions(context);
        VillagerHeadType.bootstrapLootItemConditions(context);
        WitherHeadType.bootstrapLootItemConditions(context);
        WolfHeadType.bootstrapLootItemConditions(context);
        ZombieVillagerHeadType.bootstrapLootItemConditions(context);
    }

    public static ResourceKey<HeadType> register(String path) {
        return register(Identifier.withDefaultNamespace(path));
    }

    public static ResourceKey<HeadType> register(Identifier identifier) {
        return ModRegistry.REGISTRIES.makeResourceKey(ModRegistry.HEAD_REGISTRY_KEY,
                identifier.toString().replace(':', '/'));
    }

    public static void bootstrap(BootstrapContext<LootItemCondition> context, ResourceKey<HeadType> key, EntityType<?> type) {
        bootstrap(context, key, HolderSet.direct(type.builtInRegistryHolder()), Function.identity()::apply);
    }

    public static void bootstrap(BootstrapContext<LootItemCondition> context, ResourceKey<HeadType> key, EntityType<?> type, Consumer<EntityPredicate.Builder> entityPredicate) {
        bootstrap(context, key, HolderSet.direct(type.builtInRegistryHolder()), entityPredicate);
    }

    public static void bootstrap(BootstrapContext<LootItemCondition> context, ResourceKey<HeadType> key, TagKey<EntityType<?>> tag) {
        bootstrap(context, key, context.lookup(Registries.ENTITY_TYPE).getOrThrow(tag), Function.identity()::apply);
    }

    public static void bootstrap(BootstrapContext<LootItemCondition> context, ResourceKey<HeadType> key, TagKey<EntityType<?>> tag, Consumer<EntityPredicate.Builder> entityPredicate) {
        bootstrap(context, key, context.lookup(Registries.ENTITY_TYPE).getOrThrow(tag), entityPredicate);
    }

    private static void bootstrap(BootstrapContext<LootItemCondition> context, ResourceKey<HeadType> key, HolderSet<EntityType<?>> types, Consumer<EntityPredicate.Builder> entityPredicate) {
        EntityPredicate.Builder builder = EntityPredicate.Builder.entity();
        builder.entityType(new EntityTypePredicate(types));
        entityPredicate.accept(builder);
        LootItemCondition condition = LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                builder.build()).build();
        context.register(conditionKey(key), condition);
    }

    public static ResourceKey<LootItemCondition> conditionKey(ResourceKey<HeadType> key) {
        return ResourceKey.create(Registries.PREDICATE, key.identifier().withPrefix("entity/heads/"));
    }
}
