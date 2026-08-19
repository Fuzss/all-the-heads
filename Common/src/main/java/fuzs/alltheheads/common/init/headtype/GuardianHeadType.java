package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
import static fuzs.alltheheads.common.init.HeadTypes.register;

public class GuardianHeadType {
    public static final ResourceKey<HeadType> GUARDIAN = register("guardian");
    public static final ResourceKey<HeadType> ELDER_GUARDIAN = register("elder_guardian");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        bootstrapGuardian(context, GUARDIAN,
                "entity/guardian/guardian",
                SoundEvents.GUARDIAN_AMBIENT_LAND);
        bootstrapGuardian(context, ELDER_GUARDIAN,
                "entity/guardian/guardian_elder",
                SoundEvents.ELDER_GUARDIAN_AMBIENT_LAND);
    }

    private static void bootstrapGuardian(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder()
                .shape(16.0, 16.0, 16.0)
                .scale(0.5)
                .model(ModelType.GUARDIAN, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrap(context, GUARDIAN, EntityTypes.GUARDIAN);
        bootstrap(context, ELDER_GUARDIAN, EntityTypes.ELDER_GUARDIAN);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(GUARDIAN, "Guardian Head");
        translationConsumer.accept(ELDER_GUARDIAN, "Elder Guardian Head");
    }
}
