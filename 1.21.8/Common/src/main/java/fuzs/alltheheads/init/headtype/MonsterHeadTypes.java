package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.advancements.critereon.CreeperPredicate;
import fuzs.alltheheads.advancements.critereon.GhastPredicate;
import fuzs.alltheheads.advancements.critereon.VexPredicate;
import fuzs.alltheheads.advancements.critereon.WitherPredicate;
import fuzs.alltheheads.init.HeadTypes;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;

public class MonsterHeadTypes {
    // Monsters
    public static final ResourceKey<HeadType> ENDERMAN = register("enderman");
    public static final ResourceKey<HeadType> BLAZE = register("blaze");
    public static final ResourceKey<HeadType> WITCH = register("witch");
    public static final ResourceKey<HeadType> RAVAGER = register("ravager");
    public static final ResourceKey<HeadType> PHANTOM = register("phantom");
    public static final ResourceKey<HeadType> CREAKING = register("creaking");
    public static final ResourceKey<HeadType> BREEZE = register("breeze");
    public static final ResourceKey<HeadType> CHARGED_CREEPER = register("creeper/charged");
    public static final ResourceKey<HeadType> SHULKER = register("shulker");
    public static final ResourceKey<HeadType> WARDEN = register("warden");
    // Guardians
    public static final ResourceKey<HeadType> GUARDIAN = register("guardian");
    public static final ResourceKey<HeadType> ELDER_GUARDIAN = register("elder_guardian");
    // Withers
    public static final ResourceKey<HeadType> WITHER = register("wither");
    public static final ResourceKey<HeadType> SHIELED_WITHER = register("wither/shielded");
    public static final ResourceKey<HeadType> BLUE_WITHER = register("wither/blue");
    public static final ResourceKey<HeadType> SHIELDED_BLUE_WITHER = register("wither/blue/shielded");
    // Piglins
    public static final ResourceKey<HeadType> ZOMBIFIED_PIGLIN = register("zombified_piglin");
    public static final ResourceKey<HeadType> PIGLIN_BRUTE = register("piglin_brute");
    // Ghasts
    public static final ResourceKey<HeadType> GHAST = register("ghast");
    public static final ResourceKey<HeadType> CHARGING_GHAST = register("ghast/charging");
    // Zombies
    public static final ResourceKey<HeadType> HUSK = register("husk");
    public static final ResourceKey<HeadType> DROWNED = register("drowned");
    // Skeletons
    public static final ResourceKey<HeadType> STRAY = register("stray");
    public static final ResourceKey<HeadType> BOGGED = register("bogged");
    // Slimes
    public static final ResourceKey<HeadType> SLIME = register("slime");
    public static final ResourceKey<HeadType> MAGMA_CUBE = register("magma_cube");
    // Spiders
    public static final ResourceKey<HeadType> SPIDER = register("spider");
    public static final ResourceKey<HeadType> CAVE_SPIDER = register("cave_spider");
    // Vexes
    public static final ResourceKey<HeadType> VEX = register("vex");
    public static final ResourceKey<HeadType> CHARGING_VEX = register("vex/charging");
    // Illagers
    public static final ResourceKey<HeadType> VINDICATOR = register("vindicator");
    public static final ResourceKey<HeadType> EVOKER = register("evoker");
    public static final ResourceKey<HeadType> PILLAGER = register("pillager");
    public static final ResourceKey<HeadType> ILLUSIONER = register("illusioner");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        // Monsters
        HeadType.builder(EntityType.ENDERMAN)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.ENDERMAN, ResourceLocationHelper.withDefaultNamespace("entity/enderman/enderman"))
                .model(ModelType.ENDERMAN_EYES,
                        ResourceLocationHelper.withDefaultNamespace("entity/enderman/enderman_eyes"))
                .noteBlockSound(SoundEvents.ENDERMAN_AMBIENT)
                .build(context, ENDERMAN);
        HeadType.builder(EntityType.BLAZE)
                .shape(8.0, 8.0, 8.0)
                .litModel(ModelType.MOB, ResourceLocationHelper.withDefaultNamespace("entity/blaze"))
                .noteBlockSound(SoundEvents.BLAZE_AMBIENT)
                .build(context, BLAZE);
        HeadType.builder(EntityType.WITCH)
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.WITCH, ResourceLocationHelper.withDefaultNamespace("entity/witch"))
                .noteBlockSound(SoundEvents.WITCH_AMBIENT)
                .build(context, WITCH);
        HeadType.builder(EntityType.RAVAGER)
                .shape(16.0, 20.0, 16.0)
                .scale(0.5)
                .model(ModelType.RAVAGER, ResourceLocationHelper.withDefaultNamespace("entity/illager/ravager"))
                .noteBlockSound(SoundEvents.RAVAGER_AMBIENT)
                .build(context, RAVAGER);
        HeadType.builder(EntityType.PHANTOM)
                .shape(7.0, 3.0, 5.0)
                .scale(8.0 / 7.0)
                .model(ModelType.PHANTOM, ResourceLocationHelper.withDefaultNamespace("entity/phantom"))
                .model(ModelType.PHANTOM_EYES, ResourceLocationHelper.withDefaultNamespace("entity/phantom_eyes"))
                .noteBlockSound(SoundEvents.PHANTOM_AMBIENT)
                .build(context, PHANTOM);
        HeadType.builder(EntityType.CREAKING)
                .shape(6.0, 10.0, 6.0)
                .model(ModelType.CREAKING, ResourceLocationHelper.withDefaultNamespace("entity/creaking/creaking"))
                .model(ModelType.CREAKING_EYES,
                        ResourceLocationHelper.withDefaultNamespace("entity/creaking/creaking_eyes"))
                .noteBlockSound(SoundEvents.CREAKING_AMBIENT)
                .build(context, CREAKING);
        HeadType.builder(EntityType.BREEZE)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.BREEZE, ResourceLocationHelper.withDefaultNamespace("entity/breeze/breeze"))
                .model(ModelType.BREEZE_EYES, ResourceLocationHelper.withDefaultNamespace("entity/breeze/breeze_eyes"))
                .noteBlockSound(SoundEvents.BREEZE_IDLE_GROUND)
                .build(context, BREEZE);
        HeadType.builder(EntityType.CREEPER)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(CreeperPredicate.isPowered(true));
                })
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.MOB, ResourceLocationHelper.withDefaultNamespace("entity/creeper/creeper"))
                .dyedModel(ModelType.CREEPER_CHARGE,
                        ResourceLocationHelper.withDefaultNamespace("entity/creeper/creeper_armor"),
                        0XFF808080)
                .noteBlockSound(SoundEvents.CREEPER_PRIMED)
                .build(context, CHARGED_CREEPER);
        HeadType.builder(EntityType.SHULKER)
                .shape(6.0, 6.0, 6.0)
                .scale(4.0 / 3.0)
                .model(ModelType.SHULKER, ResourceLocationHelper.withDefaultNamespace("entity/shulker/shulker"))
                .noteBlockSound(SoundEvents.SHULKER_AMBIENT)
                .build(context, SHULKER);
        HeadType.builder(EntityType.WARDEN)
                .shape(16.0, 16.0, 10.0)
                .scale(0.6)
                .model(ModelType.WARDEN, ResourceLocationHelper.withDefaultNamespace("entity/warden/warden"))
                .noteBlockSound(SoundEvents.WARDEN_AMBIENT)
                .build(context, WARDEN);

        // Guardians
        HeadType.builder(EntityType.GUARDIAN)
                .shape(16.0, 16.0, 16.0)
                .scale(0.5)
                .model(ModelType.GUARDIAN, ResourceLocationHelper.withDefaultNamespace("entity/guardian"))
                .noteBlockSound(SoundEvents.GUARDIAN_AMBIENT)
                .build(context, GUARDIAN);
        HeadType.builder(EntityType.ELDER_GUARDIAN)
                .shape(16.0, 16.0, 16.0)
                .scale(0.5)
                .model(ModelType.GUARDIAN, ResourceLocationHelper.withDefaultNamespace("entity/guardian_elder"))
                .noteBlockSound(SoundEvents.ELDER_GUARDIAN_AMBIENT)
                .build(context, ELDER_GUARDIAN);

        // Withers
        HeadType.builder(EntityType.WITHER)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(WitherPredicate.isPowered(false));
                })
                .shape(8.0, 8.0, 8.0)
                .litModel(ModelType.HUMANOID, ResourceLocationHelper.withDefaultNamespace("entity/wither/wither"))
                .noteBlockSound(SoundEvents.WITHER_AMBIENT)
                .build(context, WITHER);
        HeadType.builder(EntityType.WITHER)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(WitherPredicate.isPowered(true));
                })
                .shape(8.0, 8.0, 8.0)
                .litModel(ModelType.HUMANOID, ResourceLocationHelper.withDefaultNamespace("entity/wither/wither"))
                .dyedModel(ModelType.WITHER_SHIELD,
                        ResourceLocationHelper.withDefaultNamespace("entity/wither/wither_armor"),
                        0XFF808080)
                .noteBlockSound(SoundEvents.WITHER_AMBIENT)
                .build(context, SHIELED_WITHER);
        HeadType.builder(EntityType.WITHER)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(WitherPredicate.isPowered(false));
                })
                .shape(8.0, 8.0, 8.0)
                .litModel(ModelType.HUMANOID,
                        ResourceLocationHelper.withDefaultNamespace("entity/wither/wither_invulnerable"))
                .noteBlockSound(SoundEvents.WITHER_AMBIENT)
                .build(context, BLUE_WITHER);
        HeadType.builder(EntityType.WITHER)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(WitherPredicate.isPowered(true));
                })
                .shape(8.0, 8.0, 8.0)
                .litModel(ModelType.HUMANOID,
                        ResourceLocationHelper.withDefaultNamespace("entity/wither/wither_invulnerable"))
                .dyedModel(ModelType.WITHER_SHIELD,
                        ResourceLocationHelper.withDefaultNamespace("entity/wither/wither_armor"),
                        0XFF808080)
                .noteBlockSound(SoundEvents.WITHER_AMBIENT)
                .build(context, SHIELDED_BLUE_WITHER);

        // Piglins
        HeadType.builder(EntityType.ZOMBIFIED_PIGLIN)
                .shape(10.0, 8.0, 8.0)
                .model(ModelType.PIGLIN, ResourceLocationHelper.withDefaultNamespace("entity/piglin/zombified_piglin"))
                .noteBlockSound(SoundEvents.ZOMBIFIED_PIGLIN_AMBIENT)
                .build(context, ZOMBIFIED_PIGLIN);
        HeadType.builder(EntityType.PIGLIN_BRUTE)
                .shape(10.0, 8.0, 8.0)
                .model(ModelType.PIGLIN, ResourceLocationHelper.withDefaultNamespace("entity/piglin/piglin_brute"))
                .noteBlockSound(SoundEvents.PIGLIN_BRUTE_AMBIENT)
                .build(context, PIGLIN_BRUTE);

        // Ghasts
        bootstrapGhast(context, false, GHAST, "entity/ghast/ghast", SoundEvents.GHAST_AMBIENT);
        bootstrapGhast(context, true, CHARGING_GHAST, "entity/ghast/ghast_shooting", SoundEvents.GHAST_WARN);

        // Zombies
        HeadType.builder(EntityType.HUSK)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.HUMANOID, ResourceLocationHelper.withDefaultNamespace("entity/zombie/husk"))
                .noteBlockSound(SoundEvents.HUSK_AMBIENT)
                .build(context, HUSK);
        HeadType.builder(EntityType.DROWNED)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.HUMANOID, ResourceLocationHelper.withDefaultNamespace("entity/zombie/drowned"))
                .model(ModelType.HUMANOID_OVERLAY,
                        ResourceLocationHelper.withDefaultNamespace("entity/zombie/drowned_outer_layer"))
                .noteBlockSound(SoundEvents.DROWNED_AMBIENT)
                .build(context, DROWNED);

        // Skeletons
        HeadType.builder(EntityType.STRAY)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.MOB, ResourceLocationHelper.withDefaultNamespace("entity/skeleton/stray"))
                .model(ModelType.MOB_OVERLAY,
                        ResourceLocationHelper.withDefaultNamespace("entity/skeleton/stray_overlay"))
                .noteBlockSound(SoundEvents.STRAY_AMBIENT)
                .build(context, STRAY);
        HeadType.builder(EntityType.BOGGED)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.BOGGED, ResourceLocationHelper.withDefaultNamespace("entity/skeleton/bogged"))
                .model(ModelType.MOB_OVERLAY,
                        ResourceLocationHelper.withDefaultNamespace("entity/skeleton/bogged_overlay"))
                .noteBlockSound(SoundEvents.BOGGED_AMBIENT)
                .build(context, BOGGED);

        // Slime
        HeadType.builder(EntityType.SLIME)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.SLIME, ResourceLocationHelper.withDefaultNamespace("entity/slime/slime"))
                .model(ModelType.SLIME_GEL, ResourceLocationHelper.withDefaultNamespace("entity/slime/slime"))
                .noteBlockSound(SoundEvents.SLIME_SQUISH_SMALL)
                .build(context, SLIME);
        HeadType.builder(EntityType.MAGMA_CUBE)
                .shape(8.0, 8.0, 8.0)
                .litModel(ModelType.MAGMA_CUBE, ResourceLocationHelper.withDefaultNamespace("entity/slime/magmacube"))
                .noteBlockSound(SoundEvents.MAGMA_CUBE_SQUISH_SMALL)
                .build(context, MAGMA_CUBE);

        // Spiders
        HeadType.builder(EntityType.SPIDER)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.SPIDER, ResourceLocationHelper.withDefaultNamespace("entity/spider/spider"))
                .model(ModelType.SPIDER_EYES, ResourceLocationHelper.withDefaultNamespace("entity/spider_eyes"))
                .noteBlockSound(SoundEvents.SPIDER_AMBIENT)
                .build(context, SPIDER);
        HeadType.builder(EntityType.CAVE_SPIDER)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.SPIDER, ResourceLocationHelper.withDefaultNamespace("entity/spider/cave_spider"))
                .model(ModelType.SPIDER_EYES, ResourceLocationHelper.withDefaultNamespace("entity/spider_eyes"))
                .noteBlockSound(SoundEvents.SPIDER_AMBIENT)
                .build(context, CAVE_SPIDER);

        // Vexes
        bootstrapVex(context, false, VEX, "entity/illager/vex", SoundEvents.VEX_AMBIENT);
        bootstrapVex(context, true, CHARGING_VEX, "entity/illager/vex_charging", SoundEvents.VEX_CHARGE);

        // Illagers
        HeadType.builder(EntityType.VINDICATOR)
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.ILLAGER, ResourceLocationHelper.withDefaultNamespace("entity/illager/vindicator"))
                .noteBlockSound(SoundEvents.VINDICATOR_AMBIENT)
                .build(context, VINDICATOR);
        HeadType.builder(EntityType.EVOKER)
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.ILLAGER, ResourceLocationHelper.withDefaultNamespace("entity/illager/evoker"))
                .noteBlockSound(SoundEvents.EVOKER_AMBIENT)
                .build(context, EVOKER);
        HeadType.builder(EntityType.PILLAGER)
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.ILLAGER, ResourceLocationHelper.withDefaultNamespace("entity/illager/pillager"))
                .noteBlockSound(SoundEvents.PILLAGER_AMBIENT)
                .build(context, PILLAGER);
        HeadType.builder(EntityType.ILLUSIONER)
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.ILLAGER, ResourceLocationHelper.withDefaultNamespace("entity/illager/illusioner"))
                .noteBlockSound(SoundEvents.ILLUSIONER_AMBIENT)
                .build(context, ILLUSIONER);
    }

    private static void bootstrapGhast(BootstrapContext<HeadType> context, boolean charging, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder(EntityType.GHAST)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(GhastPredicate.isCharging(charging));
                })
                .shape(16.0, 16.0, 16.0)
                .scale(0.5)
                .model(ModelType.GHAST, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    private static void bootstrapVex(BootstrapContext<HeadType> context, boolean charging, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder(EntityType.VEX)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(VexPredicate.isCharging(charging));
                })
                .shape(5.0, 5.0, 5.0)
                .scale(1.6)
                .litModel(ModelType.VEX, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    private static ResourceKey<HeadType> register(String path) {
        return HeadTypes.register(path);
    }
}
