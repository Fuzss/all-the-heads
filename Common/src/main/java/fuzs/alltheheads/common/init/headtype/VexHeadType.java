package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.advancements.predicates.entity.VexPredicate;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
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

public class VexHeadType {
    public static final ResourceKey<HeadType> VEX = register("vex");
    public static final ResourceKey<HeadType> CHARGING_VEX = register("vex/charging");
    public static final ResourceKey<HeadType> ALLAY = register("allay");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        bootstrapVex(context, VEX, "entity/illager/vex", SoundEvents.VEX_AMBIENT);
        bootstrapVex(context, CHARGING_VEX, "entity/illager/vex_charging", SoundEvents.VEX_CHARGE);
        HeadType.builder()
                .shape(5.0, 5.0, 5.0)
                .scale(1.2)
                .litModel(ModelType.ALLAY, Identifier.withDefaultNamespace("entity/allay/allay"))
                .noteBlockSound(SoundEvents.ALLAY_AMBIENT_WITHOUT_ITEM)
                .build(context, ALLAY);
    }

    private static void bootstrapVex(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder()
                .shape(5.0, 5.0, 5.0)
                .scale(1.2)
                .litModel(ModelType.VEX, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrapVex(context, false, VEX);
        bootstrapVex(context, true, CHARGING_VEX);
        bootstrap(context, ALLAY, EntityTypes.ALLAY);
    }

    private static void bootstrapVex(BootstrapContext<LootItemCondition> context, boolean charging, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.VEX, (EntityPredicate.Builder builder) -> {
            builder.put(VexPredicate.CODEC, VexPredicate.isCharging(charging));
        });
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(VEX, "Vex Head");
        translationConsumer.accept(CHARGING_VEX, "Charging Vex Head");
        translationConsumer.accept(ALLAY, "Allay Head");
    }
}
