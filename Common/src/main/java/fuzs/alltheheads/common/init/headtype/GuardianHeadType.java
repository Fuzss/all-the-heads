package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.resources.Identifier;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.register;

public class GuardianHeadType {
    public static final ResourceKey<HeadType> GUARDIAN = register("guardian");
    public static final ResourceKey<HeadType> ELDER_GUARDIAN = register("elder_guardian");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapGuardian(context, EntityType.GUARDIAN, GUARDIAN, "entity/guardian/guardian", SoundEvents.GUARDIAN_AMBIENT_LAND);
        bootstrapGuardian(context,
                EntityType.ELDER_GUARDIAN,
                ELDER_GUARDIAN,
                "entity/guardian/guardian_elder",
                SoundEvents.ELDER_GUARDIAN_AMBIENT_LAND);
    }

    private static void bootstrapGuardian(BootstrapContext<HeadType> context, EntityType<?> entityType, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder(entityType)
                .shape(16.0, 16.0, 16.0)
                .scale(0.5)
                .model(ModelType.GUARDIAN, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(GUARDIAN, "Guardian Head");
        translationConsumer.accept(ELDER_GUARDIAN, "Elder Guardian Head");
    }
}
