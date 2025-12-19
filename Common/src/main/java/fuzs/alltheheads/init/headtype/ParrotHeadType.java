package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.parrot.Parrot;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class ParrotHeadType {
    public static final ResourceKey<HeadType> BLUE_PARROT = register("parrot/blue");
    public static final ResourceKey<HeadType> CYAN_PARROT = register("parrot/cyan");
    public static final ResourceKey<HeadType> GRAY_PARROT = register("parrot/gray");
    public static final ResourceKey<HeadType> GREEN_PARROT = register("parrot/green");
    public static final ResourceKey<HeadType> RED_PARROT = register("parrot/red");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapParrot(context, Parrot.Variant.BLUE, BLUE_PARROT, "entity/parrot/parrot_blue");
        bootstrapParrot(context, Parrot.Variant.YELLOW_BLUE, CYAN_PARROT, "entity/parrot/parrot_yellow_blue");
        bootstrapParrot(context, Parrot.Variant.GRAY, GRAY_PARROT, "entity/parrot/parrot_grey");
        bootstrapParrot(context, Parrot.Variant.GREEN, GREEN_PARROT, "entity/parrot/parrot_green");
        bootstrapParrot(context, Parrot.Variant.RED_BLUE, RED_PARROT, "entity/parrot/parrot_red_blue");
    }

    private static void bootstrapParrot(BootstrapContext<HeadType> context, Parrot.Variant variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.PARROT)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.PARROT_VARIANT, variant))
                            .build());
                })
                .shape(2.0, 4.0, 2.0)
                .scale(2.0)
                .model(ModelType.PARROT, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.PARROT_AMBIENT)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(BLUE_PARROT, "Blue Parrot Head");
        translationConsumer.accept(CYAN_PARROT, "Cyan Parrot Head");
        translationConsumer.accept(GRAY_PARROT, "Gray Parrot Head");
        translationConsumer.accept(GREEN_PARROT, "Green Parrot Head");
        translationConsumer.accept(RED_PARROT, "Red Parrot Head");
    }
}
