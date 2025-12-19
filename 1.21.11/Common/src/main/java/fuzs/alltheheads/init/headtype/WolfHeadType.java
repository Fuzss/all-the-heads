package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.advancements.critereon.WolfPredicate;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.advancements.critereon.DataComponentMatchers;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.wolf.WolfSoundVariants;
import net.minecraft.world.entity.animal.wolf.WolfVariant;
import net.minecraft.world.entity.animal.wolf.WolfVariants;

import java.util.Optional;
import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

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

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapWolf(context, WolfVariants.ASHEN, ASHEN_WOLF, "entity/wolf/wolf_ashen");
        bootstrapAngryWolf(context, WolfVariants.ASHEN, ANGRY_ASHEN_WOLF, "entity/wolf/wolf_ashen_angry");
        bootstrapTameWolf(context, WolfVariants.ASHEN, TAME_ASHEN_WOLF, "entity/wolf/wolf_ashen_tame");
        bootstrapWolf(context, WolfVariants.BLACK, BLACK_WOLF, "entity/wolf/wolf_black");
        bootstrapAngryWolf(context, WolfVariants.BLACK, ANGRY_BLACK_WOLF, "entity/wolf/wolf_black_angry");
        bootstrapTameWolf(context, WolfVariants.BLACK, TAME_BLACK_WOLF, "entity/wolf/wolf_black_tame");
        bootstrapWolf(context, WolfVariants.CHESTNUT, CHESTNUT_WOLF, "entity/wolf/wolf_chestnut");
        bootstrapAngryWolf(context, WolfVariants.CHESTNUT, ANGRY_CHESTNUT_WOLF, "entity/wolf/wolf_chestnut_angry");
        bootstrapTameWolf(context, WolfVariants.CHESTNUT, TAME_CHESTNUT_WOLF, "entity/wolf/wolf_chestnut_tame");
        bootstrapWolf(context, WolfVariants.PALE, PALE_WOLF, "entity/wolf/wolf");
        bootstrapAngryWolf(context, WolfVariants.PALE, ANGRY_PALE_WOLF, "entity/wolf/wolf_angry");
        bootstrapTameWolf(context, WolfVariants.PALE, TAME_PALE_WOLF, "entity/wolf/wolf_tame");
        bootstrapWolf(context, WolfVariants.RUSTY, RUSTY_WOLF, "entity/wolf/wolf_rusty");
        bootstrapAngryWolf(context, WolfVariants.RUSTY, ANGRY_RUSTY_WOLF, "entity/wolf/wolf_rusty_angry");
        bootstrapTameWolf(context, WolfVariants.RUSTY, TAME_RUSTY_WOLF, "entity/wolf/wolf_rusty_tame");
        bootstrapWolf(context, WolfVariants.SPOTTED, SPOTTED_WOLF, "entity/wolf/wolf_spotted");
        bootstrapAngryWolf(context, WolfVariants.SPOTTED, ANGRY_SPOTTED_WOLF, "entity/wolf/wolf_spotted_angry");
        bootstrapTameWolf(context, WolfVariants.SPOTTED, TAME_SPOTTED_WOLF, "entity/wolf/wolf_spotted_tame");
        bootstrapWolf(context, WolfVariants.SNOWY, SNOWY_WOLF, "entity/wolf/wolf_snowy");
        bootstrapAngryWolf(context, WolfVariants.SNOWY, ANGRY_SNOWY_WOLF, "entity/wolf/wolf_snowy_angry");
        bootstrapTameWolf(context, WolfVariants.SNOWY, TAME_SNOWY_WOLF, "entity/wolf/wolf_snowy_tame");
        bootstrapWolf(context, WolfVariants.STRIPED, STRIPED_WOLF, "entity/wolf/wolf_striped");
        bootstrapAngryWolf(context, WolfVariants.STRIPED, ANGRY_STRIPED_WOLF, "entity/wolf/wolf_striped_angry");
        bootstrapTameWolf(context, WolfVariants.STRIPED, TAME_STRIPED_WOLF, "entity/wolf/wolf_striped_tame");
        bootstrapWolf(context, WolfVariants.WOODS, WOODS_WOLF, "entity/wolf/wolf_woods");
        bootstrapAngryWolf(context, WolfVariants.WOODS, ANGRY_WOODS_WOLF, "entity/wolf/wolf_woods_angry");
        bootstrapTameWolf(context, WolfVariants.WOODS, TAME_WOODS_WOLF, "entity/wolf/wolf_woods_tame");
    }

    private static void bootstrapWolf(BootstrapContext<HeadType> context, ResourceKey<WolfVariant> variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.WOLF)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.WOLF_VARIANT,
                                    context.lookup(Registries.WOLF_VARIANT).getOrThrow(variant)))
                            .build()).subPredicate(new WolfPredicate(Optional.of(false), Optional.of(false)));
                })
                .shape(6.0, 6.0, 4.0)
                .scale(4.0 / 3.0)
                .model(ModelType.WOLF, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.WOLF_SOUNDS.get(WolfSoundVariants.SoundSet.CLASSIC).ambientSound())
                .build(context, resourceKey);
    }

    private static void bootstrapAngryWolf(BootstrapContext<HeadType> context, ResourceKey<WolfVariant> variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.WOLF)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.WOLF_VARIANT,
                                    context.lookup(Registries.WOLF_VARIANT).getOrThrow(variant)))
                            .build()).subPredicate(WolfPredicate.isAngry());
                })
                .shape(6.0, 6.0, 4.0)
                .scale(4.0 / 3.0)
                .model(ModelType.WOLF, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.WOLF_SOUNDS.get(WolfSoundVariants.SoundSet.CLASSIC).growlSound())
                .build(context, resourceKey);
    }

    private static void bootstrapTameWolf(BootstrapContext<HeadType> context, ResourceKey<WolfVariant> variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.WOLF)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.WOLF_VARIANT,
                                    context.lookup(Registries.WOLF_VARIANT).getOrThrow(variant)))
                            .build()).subPredicate(WolfPredicate.isTame());
                })
                .shape(6.0, 6.0, 4.0)
                .scale(4.0 / 3.0)
                .model(ModelType.WOLF, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.WOLF_SOUNDS.get(WolfSoundVariants.SoundSet.CLASSIC).pantSound())
                .build(context, resourceKey);
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