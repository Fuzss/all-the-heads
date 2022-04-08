package fuzs.alltheheads.registry;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import fuzs.alltheheads.world.item.ModStandingAndWallBlockItem;
import fuzs.alltheheads.world.level.block.ModSkullBlock;
import fuzs.alltheheads.world.level.block.ModWallSkullBlock;
import fuzs.puzzleslib.registry.RegistryManager;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class SkullManager {
    public static final SkullManager INSTANCE = new SkullManager();

    private Set<ModSkullType> skullTypes;
    private List<Pair<RegistryObject<Block>, RegistryObject<Block>>> skullBlocks;

    public void load() {
        ImmutableSet.Builder<ModSkullType> builder = ImmutableSet.builder();
        builder.add(new ModSkullType.Builder().mobType("piglin").textureLocation("textures/entity/piglin/piglin.png").modelLayerLocation("piglin").skull().build());
        this.skullTypes = builder.build();
    }

    public void register(RegistryManager registry) {
        this.skullBlocks = Lists.newArrayList();
        for (ModSkullType skullType : this.skullTypes) {
            Pair<RegistryObject<Block>, RegistryObject<Block>> pair = this.registerBlocks(registry, skullType);
            this.skullBlocks.add(pair);
            this.registerItem(registry, skullType, pair.left(), pair.right());
        }
    }

    public Set<ModSkullType> getAllSkullTypes() {
        return this.skullTypes;
    }

    public Block[] getAllSkullBlocks() {
        return this.skullBlocks.stream().mapMulti((Pair<RegistryObject<Block>, RegistryObject<Block>> pair, Consumer<RegistryObject<Block>> s2) -> {
            s2.accept(pair.left());
            s2.accept(pair.right());
        }).map(RegistryObject::get)
                .toArray(Block[]::new);
    }

    private Pair<RegistryObject<Block>, RegistryObject<Block>> registerBlocks(RegistryManager registry, ModSkullType skullType) {
        RegistryObject<Block> headBlock = registry.registerBlock(skullType.getId(), () -> new ModSkullBlock(skullType, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
        RegistryObject<Block> wallHeadBlock = registry.registerBlock(skullType.getWallId(), () -> new ModWallSkullBlock(skullType, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F).lootFrom(() -> headBlock.get())));
        return Pair.of(headBlock, wallHeadBlock);
    }

    private void registerItem(RegistryManager registry, ModSkullType skullType, RegistryObject<Block> headBlock, RegistryObject<Block> wallHeadBlock) {
        registry.registerItem(skullType.getId(), () -> new ModStandingAndWallBlockItem(headBlock.get(), wallHeadBlock.get(), new Item.Properties().tab(ModRegistry.ALL_THE_HEADS_CREATIVE_TAB).rarity(Rarity.UNCOMMON)));
    }
}
