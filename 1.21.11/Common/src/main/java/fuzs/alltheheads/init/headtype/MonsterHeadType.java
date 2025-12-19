package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.advancements.critereon.CreeperPredicate;
import fuzs.alltheheads.world.item.component.headtype.Color;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class MonsterHeadType {
    public static final ResourceKey<HeadType> BLAZE = register("blaze");
    public static final ResourceKey<HeadType> BOGGED = register("bogged");
    public static final ResourceKey<HeadType> BREEZE = register("breeze");
    public static final ResourceKey<HeadType> CHARGED_CREEPER = register("creeper/charged");
    public static final ResourceKey<HeadType> CREAKING = register("creaking");
    public static final ResourceKey<HeadType> DROWNED = register("drowned");
    public static final ResourceKey<HeadType> ENDERMAN = register("enderman");
    public static final ResourceKey<HeadType> ENDERMITE = register("endermite");
    public static final ResourceKey<HeadType> GIANT = register("giant");
    public static final ResourceKey<HeadType> HUSK = register("husk");
    public static final ResourceKey<HeadType> MAGMA_CUBE = register("magma_cube");
    public static final ResourceKey<HeadType> PARCHED = register("parched");
    public static final ResourceKey<HeadType> PHANTOM = register("phantom");
    public static final ResourceKey<HeadType> RAVAGER = register("ravager");
    public static final ResourceKey<HeadType> SHULKER = register("shulker");
    public static final ResourceKey<HeadType> SILVERFISH = register("silverfish");
    public static final ResourceKey<HeadType> SLIME = register("slime");
    public static final ResourceKey<HeadType> STRAY = register("stray");
    public static final ResourceKey<HeadType> WARDEN = register("warden");
    public static final ResourceKey<HeadType> WITCH = register("witch");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        HeadType.builder(EntityType.BLAZE)
                .shape(8.0, 8.0, 8.0)
                .litModel(ModelType.MOB, Identifier.withDefaultNamespace("entity/blaze"))
                .noteBlockSound(SoundEvents.BLAZE_AMBIENT)
                .build(context, BLAZE);
        HeadType.builder(EntityType.BOGGED)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.BOGGED, Identifier.withDefaultNamespace("entity/skeleton/bogged"))
                .model(ModelType.MOB_OVERLAY, Identifier.withDefaultNamespace("entity/skeleton/bogged_overlay"))
                .noteBlockSound(SoundEvents.BOGGED_AMBIENT)
                .build(context, BOGGED);
        HeadType.builder(EntityType.BREEZE)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.BREEZE, Identifier.withDefaultNamespace("entity/breeze/breeze"))
                .model(ModelType.BREEZE_EYES, Identifier.withDefaultNamespace("entity/breeze/breeze_eyes"))
                .noteBlockSound(SoundEvents.BREEZE_IDLE_AIR)
                .build(context, BREEZE);
        HeadType.builder(EntityType.CREEPER)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(CreeperPredicate.isPowered(true));
                })
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.MOB, Identifier.withDefaultNamespace("entity/creeper/creeper"))
                .dyedModel(ModelType.CREEPER_CHARGE,
                        Identifier.withDefaultNamespace("entity/creeper/creeper_armor"),
                        new Color.Constant(0xFF808080))
                .noteBlockSound(SoundEvents.CREEPER_PRIMED)
                .build(context, CHARGED_CREEPER);
        HeadType.builder(EntityType.CREAKING)
                .shape(6.0, 10.0, 6.0)
                .model(ModelType.CREAKING, Identifier.withDefaultNamespace("entity/creaking/creaking"))
                .model(ModelType.CREAKING_EYES, Identifier.withDefaultNamespace("entity/creaking/creaking_eyes"))
                .noteBlockSound(SoundEvents.CREAKING_AMBIENT)
                .build(context, CREAKING);
        HeadType.builder(EntityType.DROWNED)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.HUMANOID, Identifier.withDefaultNamespace("entity/zombie/drowned"))
                .model(ModelType.HUMANOID_OVERLAY, Identifier.withDefaultNamespace("entity/zombie/drowned_outer_layer"))
                .noteBlockSound(SoundEvents.DROWNED_AMBIENT)
                .build(context, DROWNED);
        HeadType.builder(EntityType.ENDERMAN)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.ENDERMAN, Identifier.withDefaultNamespace("entity/enderman/enderman"))
                .model(ModelType.ENDERMAN_EYES, Identifier.withDefaultNamespace("entity/enderman/enderman_eyes"))
                .noteBlockSound(SoundEvents.ENDERMAN_AMBIENT)
                .build(context, ENDERMAN);
        HeadType.builder(EntityType.ENDERMITE)
                .shape(6.0, 4.0, 3.0)
                .scale(4.0 / 3.0)
                .model(ModelType.ENDERMITE, Identifier.withDefaultNamespace("entity/endermite"))
                .noteBlockSound(SoundEvents.ENDERMITE_AMBIENT)
                .build(context, ENDERMITE);
        HeadType.builder(EntityType.GIANT)
                .shape(8.0, 8.0, 8.0)
                .scale(1.25)
                .model(ModelType.HUMANOID, Identifier.withDefaultNamespace("entity/zombie/zombie"))
                .noteBlockSound(SoundEvents.ZOMBIE_AMBIENT)
                .build(context, GIANT);
        HeadType.builder(EntityType.HUSK)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.HUMANOID, Identifier.withDefaultNamespace("entity/zombie/husk"))
                .noteBlockSound(SoundEvents.HUSK_AMBIENT)
                .build(context, HUSK);
        HeadType.builder(EntityType.MAGMA_CUBE)
                .shape(8.0, 8.0, 8.0)
                .litModel(ModelType.MAGMA_CUBE, Identifier.withDefaultNamespace("entity/slime/magmacube"))
                .noteBlockSound(SoundEvents.MAGMA_CUBE_SQUISH_SMALL)
                .build(context, MAGMA_CUBE);
        HeadType.builder(EntityType.PARCHED)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.PARCHED, Identifier.withDefaultNamespace("entity/skeleton/parched"))
                .noteBlockSound(SoundEvents.PARCHED_AMBIENT)
                .build(context, PARCHED);
        HeadType.builder(EntityType.PHANTOM)
                .shape(7.0, 3.0, 5.0)
                .scale(8.0 / 7.0)
                .model(ModelType.PHANTOM, Identifier.withDefaultNamespace("entity/phantom"))
                .model(ModelType.PHANTOM_EYES, Identifier.withDefaultNamespace("entity/phantom_eyes"))
                .noteBlockSound(SoundEvents.PHANTOM_AMBIENT)
                .build(context, PHANTOM);
        HeadType.builder(EntityType.RAVAGER)
                .shape(16.0, 20.0, 16.0)
                .scale(0.5)
                .model(ModelType.RAVAGER, Identifier.withDefaultNamespace("entity/illager/ravager"))
                .noteBlockSound(SoundEvents.RAVAGER_ROAR)
                .build(context, RAVAGER);
        HeadType.builder(EntityType.SHULKER)
                .shape(6.0, 6.0, 6.0)
                .model(ModelType.SHULKER, Identifier.withDefaultNamespace("entity/shulker/shulker"))
                .noteBlockSound(SoundEvents.SHULKER_AMBIENT)
                .build(context, SHULKER);
        HeadType.builder(EntityType.SILVERFISH)
                .shape(6.0, 4.0, 3.0)
                .scale(4.0 / 3.0)
                .model(ModelType.SILVERFISH, Identifier.withDefaultNamespace("entity/silverfish"))
                .noteBlockSound(SoundEvents.SILVERFISH_AMBIENT)
                .build(context, SILVERFISH);
        HeadType.builder(EntityType.SLIME)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.SLIME, Identifier.withDefaultNamespace("entity/slime/slime"))
                .model(ModelType.SLIME_GEL, Identifier.withDefaultNamespace("entity/slime/slime"))
                .noteBlockSound(SoundEvents.SLIME_SQUISH)
                .build(context, SLIME);
        HeadType.builder(EntityType.STRAY)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.MOB, Identifier.withDefaultNamespace("entity/skeleton/stray"))
                .model(ModelType.MOB_OVERLAY, Identifier.withDefaultNamespace("entity/skeleton/stray_overlay"))
                .noteBlockSound(SoundEvents.STRAY_AMBIENT)
                .build(context, STRAY);
        HeadType.builder(EntityType.WARDEN)
                .shape(16.0, 16.0, 10.0)
                .scale(0.6)
                .model(ModelType.WARDEN, Identifier.withDefaultNamespace("entity/warden/warden"))
                .noteBlockSound(SoundEvents.WARDEN_AMBIENT)
                .build(context, WARDEN);
        HeadType.builder(EntityType.WITCH)
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.WITCH, Identifier.withDefaultNamespace("entity/witch"))
                .noteBlockSound(SoundEvents.WITCH_AMBIENT)
                .build(context, WITCH);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(BLAZE, "Blaze Head");
        translationConsumer.accept(BOGGED, "Bogged Head");
        translationConsumer.accept(BREEZE, "Breeze Head");
        translationConsumer.accept(CHARGED_CREEPER, "Charged Creeper Head");
        translationConsumer.accept(CREAKING, "Creaking Head");
        translationConsumer.accept(DROWNED, "Drowned Head");
        translationConsumer.accept(ENDERMAN, "Enderman Head");
        translationConsumer.accept(ENDERMITE, "Endermite Head");
        translationConsumer.accept(GIANT, "Giant Head");
        translationConsumer.accept(HUSK, "Husk Head");
        translationConsumer.accept(MAGMA_CUBE, "Magma Cube Head");
        translationConsumer.accept(PARCHED, "Parched Head");
        translationConsumer.accept(PHANTOM, "Phantom Head");
        translationConsumer.accept(RAVAGER, "Ravager Head");
        translationConsumer.accept(SHULKER, "Shulker Head");
        translationConsumer.accept(SILVERFISH, "Silverfish Head");
        translationConsumer.accept(SLIME, "Slime Head");
        translationConsumer.accept(STRAY, "Stray Head");
        translationConsumer.accept(WARDEN, "Warden Head");
        translationConsumer.accept(WITCH, "Witch Head");
    }
}
