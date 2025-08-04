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

public class SpiderHeadType {
    public static final ResourceKey<HeadType> SPIDER = register("spider");
    public static final ResourceKey<HeadType> CAVE_SPIDER = register("cave_spider");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        SpiderHeadType.bootstrapSpider(context,
                EntityType.SPIDER,
                SpiderHeadType.SPIDER,
                "entity/spider/spider",
                SoundEvents.SPIDER_AMBIENT);
        SpiderHeadType.bootstrapSpider(context,
                EntityType.CAVE_SPIDER,
                SpiderHeadType.CAVE_SPIDER,
                "entity/spider/cave_spider",
                SoundEvents.SPIDER_AMBIENT);
    }

    private static void bootstrapSpider(BootstrapContext<HeadType> context, EntityType<?> entityType, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder(entityType)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.SPIDER, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .model(ModelType.SPIDER_EYES, ResourceLocationHelper.withDefaultNamespace("entity/spider_eyes"))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(SPIDER, "Spider Head");
        translationConsumer.accept(CAVE_SPIDER, "Cave Spider Head");
    }
}
