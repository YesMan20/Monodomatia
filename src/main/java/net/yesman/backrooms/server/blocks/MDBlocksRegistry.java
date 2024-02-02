package net.yesman.backrooms.server.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yesman.backrooms.Backrooms;
import net.yesman.backrooms.server.items.MDItemsRegistry;

import java.util.function.Supplier;

public class MDBlocksRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Backrooms.MODID);

    public static final RegistryObject<Block> YELLOWWALL = registerBlock("yellowwall", () ->
            new Block(BlockBehaviour.Properties.of().explosionResistance(1000000000000000f).strength(10000000000000f).destroyTime(10000000000000f)));

    public static final RegistryObject<Block> YELLOWCEILING = registerBlock("yellowceiling", () ->
            new Block(BlockBehaviour.Properties.of().explosionResistance(1000000000000000f).strength(10000000000000f).destroyTime(10000000000000f)));

    public static final RegistryObject<Block> YELLOWCARPET = registerBlock("yellowcarpet", () ->
            new Block(BlockBehaviour.Properties.of().explosionResistance(1000000000000000f).strength(10000000000000f).destroyTime(10000000000000f)));

    public static final RegistryObject<Block> YELLOWWALL_STAIRS = registerBlock("yellowwall_stairs", () ->
            new StairBlock(() -> MDBlocksRegistry.YELLOWWALL.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().explosionResistance(1000000000000000f).strength(10000000000000f).destroyTime(10000000000000f)));

    public static final RegistryObject<Block> YELLOWWALL_SLAB = registerBlock("yellowwall_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.of().explosionResistance(1000000000000000f).strength(10000000000000f).destroyTime(10000000000000f)));

    public static final RegistryObject<Block> LIGHT = registerBlock("light", () ->
            new Block(BlockBehaviour.Properties.of().explosionResistance(1000000000000000f).strength(10000000000000f).destroyTime(10000000000000f).lightLevel(s -> 15)));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return MDItemsRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
