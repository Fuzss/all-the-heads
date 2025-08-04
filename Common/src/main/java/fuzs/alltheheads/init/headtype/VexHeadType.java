package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.advancements.critereon.VexPredicate;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class VexHeadType {
    public static final ResourceKey<HeadType> VEX = register("vex");
    public static final ResourceKey<HeadType> CHARGING_VEX = register("vex/charging");
    public static final ResourceKey<HeadType> ALLAY = register("allay");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        VexHeadType.bootstrapVex(context, false, VexHeadType.VEX, "entity/illager/vex", SoundEvents.VEX_AMBIENT);
        VexHeadType.bootstrapVex(context,
                true,
                VexHeadType.CHARGING_VEX,
                "entity/illager/vex_charging",
                SoundEvents.VEX_CHARGE);
        HeadType.builder(EntityType.ALLAY)
                .shape(5.0, 5.0, 5.0)
                .scale(1.2)
                .litModel(ModelType.ALLAY, ResourceLocationHelper.withDefaultNamespace("entity/allay/allay"))
                .noteBlockSound(SoundEvents.ALLAY_AMBIENT_WITHOUT_ITEM)
                .build(context, VexHeadType.ALLAY);
    }

    private static void bootstrapVex(BootstrapContext<HeadType> context, boolean charging, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder(EntityType.VEX)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(VexPredicate.isCharging(charging));
                })
                .shape(5.0, 5.0, 5.0)
                .scale(1.2)
                .litModel(ModelType.VEX, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(VEX, "Vex Head");
        translationConsumer.accept(CHARGING_VEX, "Charging Vex Head");
        translationConsumer.accept(VexHeadType.ALLAY, "Allay Head");
    }
}
