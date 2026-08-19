package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.advancements.predicates.entity.WolfPredicate;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.advancements.predicates.DataComponentMatchers;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.wolf.WolfSoundVariants;
import net.minecraft.world.entity.animal.wolf.WolfVariant;
import net.minecraft.world.entity.animal.wolf.WolfVariants;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.Optional;
import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
import static fuzs.alltheheads.common.init.HeadTypes.register;

public class WolfHeadType {
    // Ashen Wolf
    public static final ResourceKey<HeadType> ASHEN_WOLF = register("wolf/ashen");
    public static final ResourceKey<HeadType> ANGRY_ASHEN_WOLF = register("wolf/ashen/angry");
    public static final ResourceKey<HeadType> TAME_ASHEN_WOLF = register("wolf/ashen/tame");
    // Black Wolf
    public static final ResourceKey<HeadType> BLACK_WOLF = register("wolf/black");
    public static final ResourceKey<HeadType> ANGRY_BLACK_WOLF = register("wolf/black/angry");
    public static final ResourceKey<HeadType> TAME_BLACK_WOLF = register("wolf/black/tame");
    // Chestnut Wolf
    public static final ResourceKey<HeadType> CHESTNUT_WOLF = register("wolf/chestnut");
    public static final ResourceKey<HeadType> ANGRY_CHESTNUT_WOLF = register("wolf/chestnut/angry");
    public static final ResourceKey<HeadType> TAME_CHESTNUT_WOLF = register("wolf/chestnut/tame");
    // Pale Wolf
    public static final ResourceKey<HeadType> PALE_WOLF = register("wolf/pale");
    public static final ResourceKey<HeadType> ANGRY_PALE_WOLF = register("wolf/pale/angry");
    public static final ResourceKey<HeadType> TAME_PALE_WOLF = register("wolf/pale/tame");
    // Rusty Wolf
    public static final ResourceKey<HeadType> RUSTY_WOLF = register("wolf/rusty");
    public static final ResourceKey<HeadType> ANGRY_RUSTY_WOLF = register("wolf/rusty/angry");
    public static final ResourceKey<HeadType> TAME_RUSTY_WOLF = register("wolf/rusty/tame");
    // Spotted Wolf
    public static final ResourceKey<HeadType> SPOTTED_WOLF = register("wolf/spotted");
    public static final ResourceKey<HeadType> ANGRY_SPOTTED_WOLF = register("wolf/spotted/angry");
    public static final ResourceKey<HeadType> TAME_SPOTTED_WOLF = register("wolf/spotted/tame");
    // Snowy Wolf
    public static final ResourceKey<HeadType> SNOWY_WOLF = register("wolf/snowy");
    public static final ResourceKey<HeadType> ANGRY_SNOWY_WOLF = register("wolf/snowy/angry");
    public static final ResourceKey<HeadType> TAME_SNOWY_WOLF = register("wolf/snowy/tame");
    // Striped Wolf
    public static final ResourceKey<HeadType> STRIPED_WOLF = register("wolf/striped");
    public static final ResourceKey<HeadType> ANGRY_STRIPED_WOLF = register("wolf/striped/angry");
    public static final ResourceKey<HeadType> TAME_STRIPED_WOLF = register("wolf/striped/tame");
    // Woods Wolf
    public static final ResourceKey<HeadType> WOODS_WOLF = register("wolf/woods");
    public static final ResourceKey<HeadType> ANGRY_WOODS_WOLF = register("wolf/woods/angry");
    public static final ResourceKey<HeadType> TAME_WOODS_WOLF = register("wolf/woods/tame");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        bootstrapWolf(context, ASHEN_WOLF, "entity/wolf/wolf_ashen");
        bootstrapAngryWolf(context, ANGRY_ASHEN_WOLF, "entity/wolf/wolf_ashen_angry");
        bootstrapTameWolf(context, TAME_ASHEN_WOLF, "entity/wolf/wolf_ashen_tame");
        bootstrapWolf(context, BLACK_WOLF, "entity/wolf/wolf_black");
        bootstrapAngryWolf(context, ANGRY_BLACK_WOLF, "entity/wolf/wolf_black_angry");
        bootstrapTameWolf(context, TAME_BLACK_WOLF, "entity/wolf/wolf_black_tame");
        bootstrapWolf(context, CHESTNUT_WOLF, "entity/wolf/wolf_chestnut");
        bootstrapAngryWolf(context, ANGRY_CHESTNUT_WOLF, "entity/wolf/wolf_chestnut_angry");
        bootstrapTameWolf(context, TAME_CHESTNUT_WOLF, "entity/wolf/wolf_chestnut_tame");
        bootstrapWolf(context, PALE_WOLF, "entity/wolf/wolf");
        bootstrapAngryWolf(context, ANGRY_PALE_WOLF, "entity/wolf/wolf_angry");
        bootstrapTameWolf(context, TAME_PALE_WOLF, "entity/wolf/wolf_tame");
        bootstrapWolf(context, RUSTY_WOLF, "entity/wolf/wolf_rusty");
        bootstrapAngryWolf(context, ANGRY_RUSTY_WOLF, "entity/wolf/wolf_rusty_angry");
        bootstrapTameWolf(context, TAME_RUSTY_WOLF, "entity/wolf/wolf_rusty_tame");
        bootstrapWolf(context, SPOTTED_WOLF, "entity/wolf/wolf_spotted");
        bootstrapAngryWolf(context, ANGRY_SPOTTED_WOLF, "entity/wolf/wolf_spotted_angry");
        bootstrapTameWolf(context, TAME_SPOTTED_WOLF, "entity/wolf/wolf_spotted_tame");
        bootstrapWolf(context, SNOWY_WOLF, "entity/wolf/wolf_snowy");
        bootstrapAngryWolf(context, ANGRY_SNOWY_WOLF, "entity/wolf/wolf_snowy_angry");
        bootstrapTameWolf(context, TAME_SNOWY_WOLF, "entity/wolf/wolf_snowy_tame");
        bootstrapWolf(context, STRIPED_WOLF, "entity/wolf/wolf_striped");
        bootstrapAngryWolf(context, ANGRY_STRIPED_WOLF, "entity/wolf/wolf_striped_angry");
        bootstrapTameWolf(context, TAME_STRIPED_WOLF, "entity/wolf/wolf_striped_tame");
        bootstrapWolf(context, WOODS_WOLF, "entity/wolf/wolf_woods");
        bootstrapAngryWolf(context, ANGRY_WOODS_WOLF, "entity/wolf/wolf_woods_angry");
        bootstrapTameWolf(context, TAME_WOODS_WOLF, "entity/wolf/wolf_woods_tame");
    }

    private static void bootstrapWolf(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder()
                .shape(6.0, 6.0, 4.0)
                .scale(4.0 / 3.0)
                .model(ModelType.WOLF, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.WOLF_SOUNDS.get(WolfSoundVariants.SoundSet.CLASSIC)
                        .adultSounds()
                        .ambientSound())
                .build(context, resourceKey);
    }

    private static void bootstrapAngryWolf(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder()
                .shape(6.0, 6.0, 4.0)
                .scale(4.0 / 3.0)
                .model(ModelType.WOLF, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.WOLF_SOUNDS.get(WolfSoundVariants.SoundSet.CLASSIC)
                        .adultSounds()
                        .growlSound())
                .build(context, resourceKey);
    }

    private static void bootstrapTameWolf(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder()
                .shape(6.0, 6.0, 4.0)
                .scale(4.0 / 3.0)
                .model(ModelType.WOLF, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.WOLF_SOUNDS.get(WolfSoundVariants.SoundSet.CLASSIC)
                        .adultSounds()
                        .pantSound())
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrapWolf(context, WolfVariants.ASHEN, ASHEN_WOLF);
        bootstrapAngryWolf(context, WolfVariants.ASHEN, ANGRY_ASHEN_WOLF);
        bootstrapTameWolf(context, WolfVariants.ASHEN, TAME_ASHEN_WOLF);
        bootstrapWolf(context, WolfVariants.BLACK, BLACK_WOLF);
        bootstrapAngryWolf(context, WolfVariants.BLACK, ANGRY_BLACK_WOLF);
        bootstrapTameWolf(context, WolfVariants.BLACK, TAME_BLACK_WOLF);
        bootstrapWolf(context, WolfVariants.CHESTNUT, CHESTNUT_WOLF);
        bootstrapAngryWolf(context, WolfVariants.CHESTNUT, ANGRY_CHESTNUT_WOLF);
        bootstrapTameWolf(context, WolfVariants.CHESTNUT, TAME_CHESTNUT_WOLF);
        bootstrapWolf(context, WolfVariants.PALE, PALE_WOLF);
        bootstrapAngryWolf(context, WolfVariants.PALE, ANGRY_PALE_WOLF);
        bootstrapTameWolf(context, WolfVariants.PALE, TAME_PALE_WOLF);
        bootstrapWolf(context, WolfVariants.RUSTY, RUSTY_WOLF);
        bootstrapAngryWolf(context, WolfVariants.RUSTY, ANGRY_RUSTY_WOLF);
        bootstrapTameWolf(context, WolfVariants.RUSTY, TAME_RUSTY_WOLF);
        bootstrapWolf(context, WolfVariants.SPOTTED, SPOTTED_WOLF);
        bootstrapAngryWolf(context, WolfVariants.SPOTTED, ANGRY_SPOTTED_WOLF);
        bootstrapTameWolf(context, WolfVariants.SPOTTED, TAME_SPOTTED_WOLF);
        bootstrapWolf(context, WolfVariants.SNOWY, SNOWY_WOLF);
        bootstrapAngryWolf(context, WolfVariants.SNOWY, ANGRY_SNOWY_WOLF);
        bootstrapTameWolf(context, WolfVariants.SNOWY, TAME_SNOWY_WOLF);
        bootstrapWolf(context, WolfVariants.STRIPED, STRIPED_WOLF);
        bootstrapAngryWolf(context, WolfVariants.STRIPED, ANGRY_STRIPED_WOLF);
        bootstrapTameWolf(context, WolfVariants.STRIPED, TAME_STRIPED_WOLF);
        bootstrapWolf(context, WolfVariants.WOODS, WOODS_WOLF);
        bootstrapAngryWolf(context, WolfVariants.WOODS, ANGRY_WOODS_WOLF);
        bootstrapTameWolf(context, WolfVariants.WOODS, TAME_WOODS_WOLF);
    }

    private static void bootstrapWolf(BootstrapContext<LootItemCondition> context, ResourceKey<WolfVariant> variant, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.WOLF, (EntityPredicate.Builder builder) -> {
            builder.components(DataComponentMatchers.Builder.components()
                    .exact(DataComponentExactPredicate.expect(DataComponents.WOLF_VARIANT,
                            context.lookup(Registries.WOLF_VARIANT).getOrThrow(variant)))
                    .build()).put(WolfPredicate.CODEC, new WolfPredicate(Optional.of(false), Optional.of(false)));
        });
    }

    private static void bootstrapAngryWolf(BootstrapContext<LootItemCondition> context, ResourceKey<WolfVariant> variant, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.WOLF, (EntityPredicate.Builder builder) -> {
            builder.components(DataComponentMatchers.Builder.components()
                    .exact(DataComponentExactPredicate.expect(DataComponents.WOLF_VARIANT,
                            context.lookup(Registries.WOLF_VARIANT).getOrThrow(variant)))
                    .build()).put(WolfPredicate.CODEC, WolfPredicate.isAngry());
        });
    }

    private static void bootstrapTameWolf(BootstrapContext<LootItemCondition> context, ResourceKey<WolfVariant> variant, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.WOLF, (EntityPredicate.Builder builder) -> {
            builder.components(DataComponentMatchers.Builder.components()
                    .exact(DataComponentExactPredicate.expect(DataComponents.WOLF_VARIANT,
                            context.lookup(Registries.WOLF_VARIANT).getOrThrow(variant)))
                    .build()).put(WolfPredicate.CODEC, WolfPredicate.isTame());
        });
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(ASHEN_WOLF, "Ashen Wolf Head");
        translationConsumer.accept(ANGRY_ASHEN_WOLF, "Angry Ashen Wolf Head");
        translationConsumer.accept(TAME_ASHEN_WOLF, "Tame Ashen Wolf Head");
        translationConsumer.accept(BLACK_WOLF, "Black Wolf Head");
        translationConsumer.accept(ANGRY_BLACK_WOLF, "Angry Black Wolf Head");
        translationConsumer.accept(TAME_BLACK_WOLF, "Tame Black Wolf Head");
        translationConsumer.accept(CHESTNUT_WOLF, "Chestnut Wolf Head");
        translationConsumer.accept(ANGRY_CHESTNUT_WOLF, "Angry Chestnut Wolf Head");
        translationConsumer.accept(TAME_CHESTNUT_WOLF, "Tame Chestnut Wolf Head");
        translationConsumer.accept(PALE_WOLF, "Pale Wolf Head");
        translationConsumer.accept(ANGRY_PALE_WOLF, "Angry Pale Wolf Head");
        translationConsumer.accept(TAME_PALE_WOLF, "Tame Pale Wolf Head");
        translationConsumer.accept(RUSTY_WOLF, "Rusty Wolf Head");
        translationConsumer.accept(ANGRY_RUSTY_WOLF, "Angry Rusty Wolf Head");
        translationConsumer.accept(TAME_RUSTY_WOLF, "Tame Rusty Wolf Head");
        translationConsumer.accept(SPOTTED_WOLF, "Spotted Wolf Head");
        translationConsumer.accept(ANGRY_SPOTTED_WOLF, "Angry Spotted Wolf Head");
        translationConsumer.accept(TAME_SPOTTED_WOLF, "Tame Spotted Wolf Head");
        translationConsumer.accept(SNOWY_WOLF, "Snowy Wolf Head");
        translationConsumer.accept(ANGRY_SNOWY_WOLF, "Angry Snowy Wolf Head");
        translationConsumer.accept(TAME_SNOWY_WOLF, "Tame Snowy Wolf Head");
        translationConsumer.accept(STRIPED_WOLF, "Striped Wolf Head");
        translationConsumer.accept(ANGRY_STRIPED_WOLF, "Angry Striped Wolf Head");
        translationConsumer.accept(TAME_STRIPED_WOLF, "Tame Striped Wolf Head");
        translationConsumer.accept(WOODS_WOLF, "Woods Wolf Head");
        translationConsumer.accept(ANGRY_WOODS_WOLF, "Angry Woods Wolf Head");
        translationConsumer.accept(TAME_WOODS_WOLF, "Tame Woods Wolf Head");
    }
}
