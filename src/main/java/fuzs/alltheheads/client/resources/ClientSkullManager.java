package fuzs.alltheheads.client.resources;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import fuzs.alltheheads.client.model.geom.SkullLayerDefinitions;
import fuzs.alltheheads.resources.SkullManager;
import fuzs.alltheheads.resources.SkullType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.axolotl.Axolotl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ClientSkullManager {
    public static final ClientSkullManager INSTANCE = new ClientSkullManager();

    private Map<SkullType, ClientSkullType> clientDataBySkullType;
    private Map<ResourceLocation, byte[]> resourceDataByLocation;

    public Map<SkullType, ClientSkullType> getSkullTypeClientData() {
        this.dissolve();
        return this.clientDataBySkullType;
    }

    public ClientSkullType getClientSkullType(SkullType skullType) {
        return this.getSkullTypeClientData().get(skullType);
    }

    public Map<ResourceLocation, byte[]> getBuiltInResourceData() {
        if (this.resourceDataByLocation == null) {
            ImmutableMap.Builder<ResourceLocation, byte[]> builder = new ImmutableMap.Builder<>();
            for (ClientSkullType skullType : this.getSkullTypeClientData().values()) {
                skullType.buildResourceMap(builder::put);
            }
            this.resourceDataByLocation = builder.build();
        }
        return this.resourceDataByLocation;
    }

    private void dissolve() {
        if (this.clientDataBySkullType == null) {
            List<ClientSkullType.Builder> builders = this.load();
            this.clientDataBySkullType = builders.stream()
                    .map(builder -> builder.build(SkullManager.INSTANCE::getSkullType))
                    .collect(ImmutableMap.toImmutableMap(ClientSkullType::getBaseSkullType, Function.identity()));
            this.verifyClientSkullTypes();
        }
    }

    private List<ClientSkullType.Builder> load() {
        List<ClientSkullType.Builder> builders = Lists.newArrayList();
        builders.add(new ClientSkullType.Builder("piglin").textureLocation("textures/entity/piglin/piglin.png").layerDefinition(() -> SkullLayerDefinitions.createPiglinHeadLayer(false)));
        builders.add(new ClientSkullType.Builder("zombified_piglin").textureLocation("textures/entity/piglin/zombified_piglin.png").layerDefinition(() -> SkullLayerDefinitions.createPiglinHeadLayer(true)));
        builders.add(new ClientSkullType.Builder("piglin_brute").textureLocation("textures/entity/piglin/piglin_brute.png").layerDefinition(() -> SkullLayerDefinitions.createPiglinHeadLayer(false)));
        builders.add(new ClientSkullType.Builder("cow").textureLocation("textures/entity/cow/cow.png").layerDefinition(SkullLayerDefinitions::createCowHeadLayer));
        builders.add(new ClientSkullType.Builder("villager").textureLocation("textures/entity/villager/villager.png").layerDefinition(() -> SkullLayerDefinitions.createVillagerHeadLayer(false)));
        builders.add(new ClientSkullType.Builder("enderman").textureLocation("textures/entity/enderman/enderman.png").layerDefinition(SkullLayerDefinitions::createEndermanHeadLayer));
        builders.add(new ClientSkullType.Builder("blaze").textureLocation("textures/entity/blaze.png").layerDefinition(SkullLayerDefinitions::createBlazeHeadLayer));
        builders.add(new ClientSkullType.Builder("spider").textureLocation("textures/entity/spider/spider.png").layerDefinition(SkullLayerDefinitions::createSpiderHeadLayer));
        builders.add(new ClientSkullType.Builder("cave_spider").textureLocation("textures/entity/spider/cave_spider.png").layerDefinition(SkullLayerDefinitions::createSpiderHeadLayer));
        builders.add(new ClientSkullType.Builder("witch").textureLocation("textures/entity/witch.png").layerDefinition(() -> SkullLayerDefinitions.createVillagerHeadLayer(true)));
        builders.add(new ClientSkullType.Builder("squid").textureLocation("textures/entity/squid/squid.png").layerDefinition(SkullLayerDefinitions::createSquidHeadLayer).modelScale(0.6667F));
        for (Axolotl.Variant variant : Axolotl.Variant.values()) {
            builders.add(new ClientSkullType.Builder("axolotl#" + variant.getName()).textureLocation(String.format("textures/entity/axolotl/axolotl_%s.png", variant.getName())).layerDefinition(SkullLayerDefinitions::createAxolotlHeadLayer));
        }
        builders.add(new ClientSkullType.Builder("chicken").textureLocation("textures/entity/chicken.png").layerDefinition(SkullLayerDefinitions::createChickenHeadLayer).modelScale(2.0F));
        return builders;
    }

    private void verifyClientSkullTypes() {
        for (SkullType skullType : SkullManager.INSTANCE.getAllSkullTypes()) {
            if (!this.clientDataBySkullType.containsKey(skullType)) {
                throw new IllegalStateException("Skull type for " + skullType.getMobType().toString() + " missing from client data");
            }
        }
    }
}
