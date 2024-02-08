package net.yesman.backrooms.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.yesman.backrooms.Backrooms;
import net.yesman.backrooms.server.blocks.MDBlocksRegistry;

public class MDBlockStateProvider extends BlockStateProvider {
    public MDBlockStateProvider(PackOutput gen, ExistingFileHelper exFileHelper) {
        super(gen, Backrooms.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(MDBlocksRegistry.PALE_WALLPAPER);
        slabBlock(MDBlocksRegistry.PALE_WALLPAPER_SLAB, MDBlocksRegistry.PALE_WALLPAPER);
        stairBlock(MDBlocksRegistry.PALE_WALLPAPER_STAIRS, MDBlocksRegistry.PALE_WALLPAPER);

        blockWithItem(MDBlocksRegistry.CEILING_LIGHT);

        blockWithItem(MDBlocksRegistry.CEILING_TILE);
        blockWithItem(MDBlocksRegistry.YELLOW_STAINED_CARPET);
        blockWithItem(MDBlocksRegistry.YELLOW_WALLPAPER);
        slabBlock(MDBlocksRegistry.YELLOW_WALLPAPER_SLAB, MDBlocksRegistry.YELLOW_WALLPAPER);
        stairBlock(MDBlocksRegistry.YELLOW_WALLPAPER_STAIRS, MDBlocksRegistry.YELLOW_WALLPAPER);

        blockWithItem(MDBlocksRegistry.WHITE_CEILING);
        blockWithItem(MDBlocksRegistry.DESATURATED_DARK_PLANKS);
        blockWithItem(MDBlocksRegistry.WHITE_WALLPAPER);
        slabBlock(MDBlocksRegistry.WHITE_WALLPAPER_SLAB, MDBlocksRegistry.WHITE_WALLPAPER);
        stairBlock(MDBlocksRegistry.WHITE_WALLPAPER_STAIRS, MDBlocksRegistry.WHITE_WALLPAPER);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void horizontalBlockWithItem(RegistryObject<Block> blockRegistryObject) {
        Block block = blockRegistryObject.get();
        ModelFile.ExistingModelFile model = new ModelFile.ExistingModelFile(new ResourceLocation(Backrooms.MODID, "block/" + block.getDescriptionId().replace("block.scpff.", "")), models().existingFileHelper);
        horizontalBlock(block, model);
        simpleBlockItem(block, model);
    }

    private void blockWithBlockState(RegistryObject<Block> block) {
        ModelFile.ExistingModelFile model = new ModelFile.ExistingModelFile(new ResourceLocation(Backrooms.MODID, "block/" + block.get().getDescriptionId().replace("block.scpff.", "")), models().existingFileHelper);
        simpleBlock(block.get(), model);
        simpleBlockItem(block.get(), model);
    }

    private void sidedBlock(RegistryObject<Block> block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        simpleBlockWithItem(block.get(), models().cubeBottomTop(block.getId().getPath(), side, bottom, top));
    }

    private void stairBlock(RegistryObject<Block> block, RegistryObject<Block> ogBlock) {
        stairsBlock((StairBlock)block.get(), new ResourceLocation(Backrooms.MODID, "block/" + ogBlock.getId().getPath()));
        simpleBlockItem(block.get(), new ModelFile.ExistingModelFile(new ResourceLocation(Backrooms.MODID, "block/" + block.getId().getPath()), models().existingFileHelper));
    }

    private void stairBlock(RegistryObject<Block> block, ResourceLocation texture) {
        stairsBlock((StairBlock)block.get(), texture);
        simpleBlockItem(block.get(), new ModelFile.ExistingModelFile(new ResourceLocation(Backrooms.MODID, "block/" + block.getId().getPath()), models().existingFileHelper));
    }

    private void slabBlock(RegistryObject<Block> block, RegistryObject<Block> ogBlock) {
        slabBlock((SlabBlock)block.get(), new ResourceLocation(Backrooms.MODID, "block/" + ogBlock.getId().getPath()), new ResourceLocation(Backrooms.MODID, "block/" + ogBlock.getId().getPath()));
        simpleBlockItem(block.get(), new ModelFile.ExistingModelFile(new ResourceLocation(Backrooms.MODID, "block/" + block.getId().getPath()), models().existingFileHelper));
    }

    private void slabBlock(RegistryObject<Block> block, ResourceLocation texture) {
        slabBlock((SlabBlock)block.get(), texture, texture);
        simpleBlockItem(block.get(), new ModelFile.ExistingModelFile(new ResourceLocation(Backrooms.MODID, "block/" + block.getId().getPath()), models().existingFileHelper));
    }

    private void doorBlock(RegistryObject<Block> block, ResourceLocation bottom, ResourceLocation top, ResourceLocation item) {
        doorBlock((DoorBlock)block.get(), bottom, top);
        simpleBlockItem(block.get(), itemModels().basicItem(item).parent(new ModelFile.ExistingModelFile(new ResourceLocation("item/generated"), models().existingFileHelper)));
    }
}
