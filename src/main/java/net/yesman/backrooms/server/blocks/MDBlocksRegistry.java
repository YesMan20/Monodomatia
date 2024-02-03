package net.yesman.backrooms.server.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yesman.backrooms.Backrooms;
import net.yesman.backrooms.server.items.MDItemsRegistry;

import java.util.function.Supplier;

public class MDBlocksRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Backrooms.MODID);

    public static final RegistryObject<Block> PALEWALL = registerBlock("palewall", () ->
            new Block(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> PALEWALL_STAIRS = registerBlock("palewall_stairs", () ->
            new StairBlock(() -> MDBlocksRegistry.YELLOWWALL.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> PALEWALL_SLAB = registerBlock("palewall_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));


    public static final RegistryObject<Block> WHITEWALL = registerBlock("whitewall", () ->
            new Block(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> WHITECEILING = registerBlock("whiteceiling", () ->
            new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> BROWNFLOOR = registerBlock("brownfloor", () ->
            new Block(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> WHITEWALL_STAIRS = registerBlock("whitewall_stairs", () ->
            new StairBlock(() -> MDBlocksRegistry.YELLOWWALL.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> WHITEWALL_SLAB = registerBlock("whitewall_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));


    public static final RegistryObject<Block> YELLOWWALL = registerBlock("yellowwall", () ->
            new Block(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> YELLOWCEILING = registerBlock("yellowceiling", () ->
            new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> YELLOWCARPET = registerBlock("yellowcarpet", () ->
            new Block(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> YELLOWWALL_STAIRS = registerBlock("yellowwall_stairs", () ->
            new StairBlock(() -> MDBlocksRegistry.YELLOWWALL.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> YELLOWWALL_SLAB = registerBlock("yellowwall_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> LIGHT = registerBlock("light", () ->
            new Block(BlockBehaviour.Properties.of().sound(SoundType.GLASS).strength(-1.0F, Float.MAX_VALUE).lightLevel(s -> 15)));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return MDItemsRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
