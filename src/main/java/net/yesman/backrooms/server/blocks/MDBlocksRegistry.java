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

    public static final RegistryObject<Block> PALE_WALLPAPER = registerBlock("pale_wallpaper", () ->
            new Block(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> PALE_WALLPAPER_STAIRS = registerBlock("pale_wallpaper_stairs", () ->
            new StairBlock(() -> MDBlocksRegistry.YELLOW_WALLPAPER.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> PALE_WALLPAPER_SLAB = registerBlock("pale_wallpaper_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));


    public static final RegistryObject<Block> WHITE_WALLPAPER = registerBlock("white_wallpaper", () ->
            new Block(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> WHITE_CEILING = registerBlock("white_ceiling", () ->
            new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> DESATURATED_DARK_PLANKS = registerBlock("desaturated_dark_planks", () ->
            new Block(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> WHITE_WALLPAPER_STAIRS = registerBlock("white_wallpaper_stairs", () ->
            new StairBlock(() -> MDBlocksRegistry.YELLOW_WALLPAPER.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> WHITE_WALLPAPER_SLAB = registerBlock("white_wallpaper_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));


    public static final RegistryObject<Block> YELLOW_WALLPAPER = registerBlock("yellow_wallpaper", () ->
            new Block(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> CEILING_TILE = registerBlock("ceiling_tile", () ->
            new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> YELLOW_STAINED_CARPET = registerBlock("yellow_stained_carpet", () ->
            new Block(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> YELLOW_WALLPAPER_STAIRS = registerBlock("yellow_wallpaper_stairs", () ->
            new StairBlock(() -> MDBlocksRegistry.YELLOW_WALLPAPER.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> YELLOW_WALLPAPER_SLAB = registerBlock("yellow_wallpaper_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1.0F, Float.MAX_VALUE)));

    public static final RegistryObject<Block> CEILING_LIGHT = registerBlock("ceiling_light", () ->
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
