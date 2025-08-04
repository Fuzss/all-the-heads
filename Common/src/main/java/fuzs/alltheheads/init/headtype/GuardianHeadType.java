package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class GuardianHeadType {
    public static final ResourceKey<HeadType> GUARDIAN = register("guardian");
    public static final ResourceKey<HeadType> ELDER_GUARDIAN = register("elder_guardian");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        GuardianHeadType.bootstrapGuardian(context,
                EntityType.GUARDIAN,
                GuardianHeadType.GUARDIAN,
                "entity/guardian",
                SoundEvents.GUARDIAN_AMBIENT_LAND);
        GuardianHeadType.bootstrapGuardian(context,
                EntityType.ELDER_GUARDIAN,
                GuardianHeadType.ELDER_GUARDIAN,
                "entity/guardian_elder",
                SoundEvents.ELDER_GUARDIAN_AMBIENT_LAND);
    }

    private static void bootstrapGuardian(BootstrapContext<HeadType> context, EntityType<?> entityType, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder(entityType)
                .shape(16.0, 16.0, 16.0)
                .scale(0.5)
                .model(ModelType.GUARDIAN, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(GUARDIAN, "Guardian Head");
        translationConsumer.accept(ELDER_GUARDIAN, "Elder Guardian Head");
    }
}
