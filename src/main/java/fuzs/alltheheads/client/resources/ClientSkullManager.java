package fuzs.alltheheads.client.resources;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import fuzs.alltheheads.resources.SkullManager;
import fuzs.alltheheads.resources.SkullType;
import net.minecraft.resources.ResourceLocation;

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
            this.clientDataBySkullType = builders.stream().map(builder -> builder.build(SkullManager.INSTANCE::getSkullType)).collect(ImmutableMap.toImmutableMap(ClientSkullType::getBaseSkullType, Function.identity()));
            this.verifyClientSkullTypes();
        }
    }

    private List<ClientSkullType.Builder> load() {
        List<ClientSkullType.Builder> builders = Lists.newArrayList();
        builders.add(new ClientSkullType.Builder("piglin").textureLocation("textures/entity/piglin/piglin.png"));
        builders.add(new ClientSkullType.Builder("zombified_piglin").textureLocation("textures/entity/piglin/zombified_piglin.png"));
        builders.add(new ClientSkullType.Builder("piglin_brute").textureLocation("textures/entity/piglin/piglin_brute.png"));
        builders.add(new ClientSkullType.Builder("cow").textureLocation("textures/entity/cow/cow.png").modelOffsets(0.0F, -8.0F, 11.0F));
        builders.add(new ClientSkullType.Builder("villager").textureLocation("textures/entity/villager/villager.png"));
        builders.add(new ClientSkullType.Builder("enderman").textureLocation("textures/entity/enderman/enderman.png").modelOffsets(0.0F, 13.0F, 0.0F));
        builders.add(new ClientSkullType.Builder("blaze").textureLocation("textures/entity/blaze.png").modelOffsets(0.0F, -4.0F, 0.0F));
        builders.add(new ClientSkullType.Builder("spider").textureLocation("textures/entity/spider/spider.png").modelOffsets(-4.0F, -19.0F, 3.0F));
        builders.add(new ClientSkullType.Builder("cave_spider").textureLocation("textures/entity/spider/cave_spider.png").modelOffsets(-4.0F, -19.0F, 3.0F));
        builders.add(new ClientSkullType.Builder("witch").textureLocation("textures/entity/witch.png"));
        builders.add(new ClientSkullType.Builder("squid").textureLocation("textures/entity/squid/squid.png").modelOffsets(0.0F, -5.3333F, 0.0F).setCustomHeadKey("body").modelScale(0.6667F));
        builders.add(new ClientSkullType.Builder("axolotl#lucy").textureLocation("textures/entity/axolotl/axolotl_lucy.png").setCustomHeadKey("body", "head"));
        builders.add(new ClientSkullType.Builder("axolotl#wild").textureLocation("textures/entity/axolotl/axolotl_wild.png").modelOffsets(5.0F, -2.0F, 0.0F).setCustomHeadKey("body", "head"));
        builders.add(new ClientSkullType.Builder("axolotl#gold").textureLocation("textures/entity/axolotl/axolotl_gold.png").modelOffsets(-5.0F, -2.0F, 0.0F).setCustomHeadKey("body", "head"));
        builders.add(new ClientSkullType.Builder("chicken").textureLocation("textures/entity/chicken.png").addCustomHeadKey("beak").addCustomHeadKey("red_thing"));
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
