package fuzs.alltheheads.client.resources;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import fuzs.alltheheads.registry.ModRegistry;
import fuzs.alltheheads.resources.SkullManager;
import fuzs.alltheheads.resources.SkullType;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class ClientSkullManager {
    public static final ClientSkullManager INSTANCE = new ClientSkullManager();

    private Map<SkullType, ClientSkullType> clientDataBySkullType;
    private Map<ResourceLocation, byte[]> resourceDataByLocation;

    public Map<SkullType, ClientSkullType> getSkullTypeClientData() {
        this.dissolve();
        return this.clientDataBySkullType;
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
            Set<ClientSkullType.Builder> builders = Sets.newHashSet();
            builders.add(new ClientSkullType.Builder(ModRegistry.PIGLIN_SKULL_TYPE).textureLocation("textures/entity/piglin/piglin.png"));
            builders.add(new ClientSkullType.Builder(ModRegistry.ZOMBIFIED_PIGLIN_SKULL_TYPE).textureLocation("textures/entity/piglin/zombified_piglin.png"));
            builders.add(new ClientSkullType.Builder(ModRegistry.PIGLIN_BRUTE_SKULL_TYPE).textureLocation("textures/entity/piglin/piglin_brute.png"));
            builders.add(new ClientSkullType.Builder(ModRegistry.COW_SKULL_TYPE).textureLocation("textures/entity/cow/cow.png").modelOffsets(0.0F, -8.0F, 11.0F));
            builders.add(new ClientSkullType.Builder(ModRegistry.VILLAGER_SKULL_TYPE).textureLocation("textures/entity/villager/villager.png"));
            builders.add(new ClientSkullType.Builder(ModRegistry.ENDERMAN_SKULL_TYPE).textureLocation("textures/entity/enderman/enderman.png").modelOffsets(0.0F, 13.0F, 0.0F));
            builders.add(new ClientSkullType.Builder(ModRegistry.BLAZE_SKULL_TYPE).textureLocation("textures/entity/blaze.png").modelOffsets(0.0F, -4.0F, 0.0F));
            builders.add(new ClientSkullType.Builder(ModRegistry.SPIDER_SKULL_TYPE).textureLocation("textures/entity/spider/spider.png").modelOffsets(-4.0F, -19.0F, 3.0F));
            builders.add(new ClientSkullType.Builder(ModRegistry.CAVE_SPIDER_SKULL_TYPE).textureLocation("textures/entity/spider/cave_spider.png").modelOffsets(-4.0F, -19.0F, 3.0F));
            builders.add(new ClientSkullType.Builder(ModRegistry.WITCH_SKULL_TYPE).textureLocation("textures/entity/witch.png"));
            builders.add(new ClientSkullType.Builder(ModRegistry.SQUID_SKULL_TYPE).textureLocation("textures/entity/squid/squid.png").modelOffsets(0.0F, -16.0F, 0.0F).customHeadKey("body"));
            builders.add(new ClientSkullType.Builder(ModRegistry.LUCY_AXOLOTL_SKULL_TYPE).textureLocation("textures/entity/axolotl/axolotl_lucy.png").modelOffsets(5.0F, -3.0F, 0.0F).customHeadKey("body", "head"));
            builders.add(new ClientSkullType.Builder(ModRegistry.WILD_AXOLOTL_SKULL_TYPE).textureLocation("textures/entity/axolotl/axolotl_wild.png").modelOffsets(0.0F, -3.0F, 5.0F).customHeadKey("body", "head"));
            builders.add(new ClientSkullType.Builder(ModRegistry.GOLD_AXOLOTL_SKULL_TYPE).textureLocation("textures/entity/axolotl/axolotl_gold.png").modelOffsets(-5.0F, -2.0F, 0.0F).customHeadKey("body", "head"));
            this.clientDataBySkullType = builders.stream().map(ClientSkullType.Builder::build).collect(ImmutableMap.toImmutableMap(ClientSkullType::getBaseSkullType, Function.identity()));
            this.verifyClientSkullTypes();
        }
    }

    private void verifyClientSkullTypes() {
        for (SkullType skullType : SkullManager.INSTANCE.getAllSkullTypes()) {
            if (!this.clientDataBySkullType.containsKey(skullType)) {
                throw new IllegalStateException("Skull type of " + skullType.getMobType().toString() + " missing from client data");
            }
        }
    }
}
