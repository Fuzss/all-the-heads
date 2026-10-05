package fuzs.alltheheads.common.data;

import com.google.common.collect.Sets;
import fuzs.alltheheads.common.AllTheHeads;
import fuzs.alltheheads.common.init.ModRegistry;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class HeadTypesValidator implements DataProvider {
    private final CompletableFuture<HolderLookup.Provider> registries;

    public HeadTypesValidator(DataProviderContext context) {
        this.registries = context.getRegistries();
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return this.registries.thenAccept(HeadTypesValidator::validateHeadTypes);
    }

    private static void validateHeadTypes(HolderLookup.Provider context) {
        Set<ResourceKey<EntityType<?>>> mobEntities = BuiltInRegistries.ENTITY_TYPE.stream()
                .filter((EntityType<?> entityType) -> entityType.getCategory() != MobCategory.MISC)
                .map(BuiltInRegistries.ENTITY_TYPE::getResourceKey)
                .<ResourceKey<EntityType<?>>>mapMulti(Optional::ifPresent)
                .collect(Collectors.toSet());
        Set<ResourceKey<EntityType<?>>> headTypeEntities = context.lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY)
                .listElements()
                .map(Holder.Reference::key)
                .map(HeadType::entityType)
                .map(Holder::value)
                .distinct()
                .map(BuiltInRegistries.ENTITY_TYPE::getResourceKey)
                .<ResourceKey<EntityType<?>>>mapMulti(Optional::ifPresent)
                .collect(Collectors.toSet());
        Sets.difference(mobEntities, headTypeEntities).forEach((ResourceKey<EntityType<?>> resourceKey) -> {
            AllTheHeads.LOGGER.warn("Missing head type for {}", resourceKey);
        });
    }

    @Override
    public String getName() {
        return "Head Types Validator";
    }
}
