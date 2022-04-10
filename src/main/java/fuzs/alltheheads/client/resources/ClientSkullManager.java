package fuzs.alltheheads.client.resources;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import fuzs.alltheheads.client.model.geom.SkullLayerDefinitions;
import fuzs.alltheheads.resources.ModSkullType;
import fuzs.alltheheads.resources.SkullManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.axolotl.Axolotl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ClientSkullManager {
    public static final ClientSkullManager INSTANCE = new ClientSkullManager();

    private Map<ModSkullType, ClientModSkullType> clientDataBySkullType;
    private Map<ResourceLocation, byte[]> resourceDataByLocation;
    private Map<ModSkullType, RenderType> renderBySkullType;

    public Map<ModSkullType, ClientModSkullType> getSkullTypeClientData() {
        this.dissolve();
        return this.clientDataBySkullType;
    }

    public ClientModSkullType getClientSkullType(ModSkullType skullType) {
        return this.getSkullTypeClientData().get(skullType);
    }

    public Map<ResourceLocation, byte[]> getBuiltInResourceData() {
        if (this.resourceDataByLocation == null) {
            ImmutableMap.Builder<ResourceLocation, byte[]> builder = new ImmutableMap.Builder<>();
            for (ClientModSkullType skullType : this.getSkullTypeClientData().values()) {
                skullType.buildResourceMap(builder::put);
            }
            this.resourceDataByLocation = builder.build();
        }
        return this.resourceDataByLocation;
    }

    public RenderType getSkullRenderType(ModSkullType skullType) {
        if (this.renderBySkullType == null) {
            // we use RenderType::entityCutoutNoCull to make sure layers work (vanilla uses RenderType::entityCutoutNoCullZOffset, no idea why)
            this.renderBySkullType = this.getSkullTypeClientData().entrySet().stream()
                    .collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, e -> RenderType.entityCutoutNoCull(e.getValue().getTextureLocation())));
        }
        return this.renderBySkullType.get(skullType);
    }

    private void dissolve() {
        if (this.clientDataBySkullType == null) {
            List<ClientModSkullType.Builder> builders = this.load();
            this.clientDataBySkullType = builders.stream()
                    .map(builder -> builder.build(SkullManager.INSTANCE::getSkullType))
                    .collect(ImmutableMap.toImmutableMap(ClientModSkullType::getBaseSkullType, Function.identity()));
            this.verifyClientSkullTypes();
        }
    }

    private List<ClientModSkullType.Builder> load() {
        List<ClientModSkullType.Builder> builders = Lists.newArrayList();
        builders.add(new ClientModSkullType.Builder("piglin").textureLocation("textures/entity/piglin/piglin.png").layerDefinition(() -> SkullLayerDefinitions.createPiglinHeadLayer(false)));
        builders.add(new ClientModSkullType.Builder("zombified_piglin").textureLocation("textures/entity/piglin/zombified_piglin.png").layerDefinition(() -> SkullLayerDefinitions.createPiglinHeadLayer(true)));
        builders.add(new ClientModSkullType.Builder("piglin_brute").textureLocation("textures/entity/piglin/piglin_brute.png").layerDefinition(() -> SkullLayerDefinitions.createPiglinHeadLayer(false)));
        builders.add(new ClientModSkullType.Builder("cow").textureLocation("textures/entity/cow/cow.png").layerDefinition(SkullLayerDefinitions::createCowHeadLayer));
        for (ResourceLocation villagerBiomeType : SkullManager.VILLAGER_BIOME_TYPES) {
            for (ResourceLocation villagerWorkerProfession : SkullManager.VILLAGER_WORKER_PROFESSIONS) {
                String villager = String.format("villager#%s_%s", villagerBiomeType.getPath(), villagerWorkerProfession.getPath());
                builders.add(new ClientModSkullType.Builder(villager).textureLocation("textures/entity/villager/villager.png").layerDefinition(() -> SkullLayerDefinitions.createVillagerHeadLayer(false)).layer(villager));
                String zombieVillager = String.format("zombie_villager#%s_%s", villagerBiomeType.getPath(), villagerWorkerProfession.getPath());
                builders.add(new ClientModSkullType.Builder(zombieVillager).textureLocation("textures/entity/zombie_villager/zombie_villager.png").layerDefinition(() -> SkullLayerDefinitions.createVillagerHeadLayer(false)).layer(zombieVillager));
            }
        }
        builders.add(new ClientModSkullType.Builder("enderman").textureLocation("textures/entity/enderman/enderman.png").layerDefinition(SkullLayerDefinitions::createEndermanHeadLayer).layer("eyes"));
        builders.add(new ClientModSkullType.Builder("blaze").textureLocation("textures/entity/blaze.png").layerDefinition(SkullLayerDefinitions::createBlazeHeadLayer));
        builders.add(new ClientModSkullType.Builder("spider").textureLocation("textures/entity/spider/spider.png").layerDefinition(SkullLayerDefinitions::createSpiderHeadLayer).layer("eyes"));
        builders.add(new ClientModSkullType.Builder("cave_spider").textureLocation("textures/entity/spider/cave_spider.png").layerDefinition(SkullLayerDefinitions::createSpiderHeadLayer).layer("eyes"));
        builders.add(new ClientModSkullType.Builder("witch").textureLocation("textures/entity/witch.png").layerDefinition(() -> SkullLayerDefinitions.createVillagerHeadLayer(true)));
        builders.add(new ClientModSkullType.Builder("squid").textureLocation("textures/entity/squid/squid.png").layerDefinition(SkullLayerDefinitions::createSquidHeadLayer).modelScale(0.6667F));
        for (Axolotl.Variant variant : Axolotl.Variant.values()) {
            builders.add(new ClientModSkullType.Builder("axolotl#" + variant.getName()).textureLocation(String.format("textures/entity/axolotl/axolotl_%s.png", variant.getName())).layerDefinition(SkullLayerDefinitions::createAxolotlHeadLayer));
        }
        builders.add(new ClientModSkullType.Builder("chicken").textureLocation("textures/entity/chicken.png").layerDefinition(SkullLayerDefinitions::createChickenHeadLayer).modelScale(2.0F));
        return builders;
    }

    private void verifyClientSkullTypes() {
        for (ModSkullType skullType : SkullManager.INSTANCE.getAllSkullTypes()) {
            if (!this.clientDataBySkullType.containsKey(skullType)) {
                throw new IllegalStateException("Skull type for " + skullType.getMobType().toString() + " missing from client data");
            }
        }
    }
}
