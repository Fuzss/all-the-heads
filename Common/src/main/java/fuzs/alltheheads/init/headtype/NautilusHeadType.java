package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.world.item.component.headtype.Builder;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.nautilus.ZombieNautilusVariant;
import net.minecraft.world.entity.animal.nautilus.ZombieNautilusVariants;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class NautilusHeadType {
    public static final ResourceKey<HeadType> CORAL_ZOMBIE_NAUTILUS = register("zombie_nautilus/coral");
    public static final ResourceKey<HeadType> NAUTILUS = register("nautilus");
    public static final ResourceKey<HeadType> ZOMBIE_NAUTILUS = register("zombie_nautilus");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapNautilus(EntityType.NAUTILUS, "entity/nautilus/nautilus", SoundEvents.NAUTILUS_AMBIENT).build(context,
                NAUTILUS);
        bootstrapZombieNautilus(context,
                ZombieNautilusVariants.TEMPERATE,
                ZOMBIE_NAUTILUS,
                "entity/nautilus/zombie_nautilus");
        bootstrapZombieNautilus(context,
                ZombieNautilusVariants.WARM,
                CORAL_ZOMBIE_NAUTILUS,
                "entity/nautilus/zombie_nautilus_coral");
    }

    private static void bootstrapZombieNautilus(BootstrapContext<HeadType> context, ResourceKey<ZombieNautilusVariant> variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        bootstrapNautilus(EntityType.ZOMBIE_NAUTILUS,
                textureLocation,
                SoundEvents.ZOMBIE_NAUTILUS_AMBIENT).entityPredicate((EntityPredicate.Builder builder) -> {
            builder.components(DataComponentMatchers.Builder.components()
                    .exact(DataComponentExactPredicate.expect(DataComponents.ZOMBIE_NAUTILUS_VARIANT,
                            context.lookup(Registries.ZOMBIE_NAUTILUS_VARIANT).getOrThrow(variant)))
                    .build());
        }).build(context, resourceKey);
    }

    private static Builder bootstrapNautilus(EntityType<?> entityType, String textureLocation, SoundEvent noteBlockSound) {
        return HeadType.builder(entityType)
                .shape(10.0, 8.0, 12.0)
                .scale(6.0 / 8.0)
                .model(ModelType.NAUTILUS, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(NAUTILUS, "Nautilus Head");
        translationConsumer.accept(ZOMBIE_NAUTILUS, "Zombie Nautilus Head");
        translationConsumer.accept(CORAL_ZOMBIE_NAUTILUS, "Coral Zombie Nautilus Head");
    }
}
