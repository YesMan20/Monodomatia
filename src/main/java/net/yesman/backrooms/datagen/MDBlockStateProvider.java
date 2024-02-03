package net.yesman.backrooms.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
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
        blockWithItem(MDBlocksRegistry.PALEWALL);
        slabBlock(MDBlocksRegistry.PALEWALL_SLAB, MDBlocksRegistry.PALEWALL);
        stairBlock(MDBlocksRegistry.PALEWALL_STAIRS, MDBlocksRegistry.PALEWALL);

        blockWithItem(MDBlocksRegistry.LIGHT);

        blockWithItem(MDBlocksRegistry.YELLOWCEILING);
        blockWithItem(MDBlocksRegistry.YELLOWCARPET);
        blockWithItem(MDBlocksRegistry.YELLOWWALL);
        slabBlock(MDBlocksRegistry.YELLOWWALL_SLAB, MDBlocksRegistry.YELLOWWALL);
        stairBlock(MDBlocksRegistry.YELLOWWALL_STAIRS, MDBlocksRegistry.YELLOWWALL);

        blockWithItem(MDBlocksRegistry.WHITECEILING);
        blockWithItem(MDBlocksRegistry.BROWNFLOOR);
        blockWithItem(MDBlocksRegistry.WHITEWALL);
        slabBlock(MDBlocksRegistry.WHITEWALL_SLAB, MDBlocksRegistry.WHITEWALL);
        stairBlock(MDBlocksRegistry.WHITEWALL_STAIRS, MDBlocksRegistry.WHITEWALL);

        //horizontalBlockWithItem(MDBlocksRegistry.CONTAINMENT_CASE);
    }

    private void horizontalBlockWithItem(RegistryObject<Block> blockRegistryObject) {
        Block block = blockRegistryObject.get();
        ModelFile.ExistingModelFile model = new ModelFile.ExistingModelFile(new ResourceLocation(Backrooms.MODID, "block/" + block.getDescriptionId().replace("block.scpff.", "")), models().existingFileHelper);
        horizontalBlock(block, model);
        simpleBlockItem(block, model);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void stairBlock(RegistryObject<Block> block, RegistryObject<Block> ogBlock) {
        stairsBlock((StairBlock)block.get(), new ResourceLocation(Backrooms.MODID, "block/" + ogBlock.getId().getPath()));
        simpleBlockItem(block.get(), new ModelFile.ExistingModelFile(new ResourceLocation(Backrooms.MODID, "block/" + block.getId().getPath()), models().existingFileHelper));
    }

    private void stairBlock(RegistryObject<Block> block, String texture) {
        stairsBlock((StairBlock)block.get(), new ResourceLocation("block/" + texture));
        simpleBlockItem(block.get(), new ModelFile.ExistingModelFile(new ResourceLocation(Backrooms.MODID, "block/" + block.getId().getPath()), models().existingFileHelper));
    }

    private void slabBlock(RegistryObject<Block> block, RegistryObject<Block> ogBlock) {
        slabBlock((SlabBlock)block.get(), new ResourceLocation(Backrooms.MODID, "block/" + ogBlock.getId().getPath()), new ResourceLocation(Backrooms.MODID, "block/" + ogBlock.getId().getPath()));
        simpleBlockItem(block.get(), new ModelFile.ExistingModelFile(new ResourceLocation(Backrooms.MODID, "block/" + block.getId().getPath()), models().existingFileHelper));
    }

    private void slabBlock(RegistryObject<Block> block, String texture) {
        slabBlock((SlabBlock)block.get(), new ResourceLocation("block/" + texture), new ResourceLocation("block/" + texture));
        simpleBlockItem(block.get(), new ModelFile.ExistingModelFile(new ResourceLocation(Backrooms.MODID, "block/" + block.getId().getPath()), models().existingFileHelper));
    }

    private void sidedBlock(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), models().cubeBottomTop(block.getId().getPath(), new ResourceLocation(Backrooms.MODID, "block/" + block.getId().getPath() + "_side"), new ResourceLocation(Backrooms.MODID, "block/" + block.getId().getPath() + "_down"), new ResourceLocation(Backrooms.MODID, "block/" + block.getId().getPath())));
    }
}
