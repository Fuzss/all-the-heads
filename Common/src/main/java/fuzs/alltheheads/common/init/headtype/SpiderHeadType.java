package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.register;

public class SpiderHeadType {
    public static final ResourceKey<HeadType> SPIDER = register("spider");
    public static final ResourceKey<HeadType> CAVE_SPIDER = register("cave_spider");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapSpider(context, EntityTypes.SPIDER, SPIDER, "entity/spider/spider", SoundEvents.SPIDER_AMBIENT);
        bootstrapSpider(context,
                EntityTypes.CAVE_SPIDER,
                CAVE_SPIDER,
                "entity/spider/cave_spider",
                SoundEvents.SPIDER_AMBIENT);
    }

    private static void bootstrapSpider(BootstrapContext<HeadType> context, EntityType<?> entityType, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder(entityType)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.SPIDER, Identifier.withDefaultNamespace(textureLocation))
                .model(ModelType.SPIDER_EYES, Identifier.withDefaultNamespace("entity/spider/spider_eyes"))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(SPIDER, "Spider Head");
        translationConsumer.accept(CAVE_SPIDER, "Cave Spider Head");
    }
}
