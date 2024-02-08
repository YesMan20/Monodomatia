package net.yesman.backrooms.server.worldgen.chunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.yesman.backrooms.server.blocks.MDBlocksRegistry;
import net.yesman.backrooms.server.worldgen.biome.MDBiomesRegistry;
import net.yesman.backrooms.util.Helper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class Level0ChunkGenerator extends ChunkGenerator {
    public static final Codec<Level0ChunkGenerator> CODEC = RecordCodecBuilder.create((codec) -> codec.group(
            BiomeSource.CODEC.fieldOf("biome_source").forGetter(ChunkGenerator::getBiomeSource)
    ).apply(codec, Level0ChunkGenerator::new));
    private ToweringHallsChunkGenerator toweringHallsGenerator;

    public Level0ChunkGenerator(BiomeSource pBiomeSource) {
        super(pBiomeSource);
        this.toweringHallsGenerator = new ToweringHallsChunkGenerator(pBiomeSource);
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void applyCarvers(WorldGenRegion pLevel, long pSeed, RandomState pRandom, BiomeManager pBiomeManager, StructureManager pStructureManager, ChunkAccess pChunk, GenerationStep.Carving pStep) {
    }

    @Override
    public void buildSurface(WorldGenRegion pLevel, StructureManager pStructureManager, RandomState pRandom, ChunkAccess pChunk) {
    }

    @Override
    public void spawnOriginalMobs(WorldGenRegion pLevel) {
    }

    @Override
    public int getGenDepth() {
        return 256;
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor pExecutor, Blender pBlender, RandomState pRandom, StructureManager pStructureManager, ChunkAccess pChunk) {
        int chunkX = pChunk.getPos().getRegionX();
        int chunkZ = pChunk.getPos().getRegionZ();
        if (Helper.checkBiomeInChunk(MDBiomesRegistry.TOWERING_HALLS, chunkX, 10, chunkZ, pChunk)) {
            this.toweringHallsGenerator.fillFromNoise(pExecutor, pBlender, pRandom, pStructureManager, pChunk);

        } else {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = 2; y > 0; y--) {
                        pChunk.setBlockState(new BlockPos(chunkX + x, 10 - y, chunkZ + z), MDBlocksRegistry.YELLOW_STAINED_CARPET.get().defaultBlockState(), false);
                        pChunk.setBlockState(new BlockPos(chunkX + x, 13 + y, chunkZ + z), MDBlocksRegistry.CEILING_TILE.get().defaultBlockState(), false);

                        // Generates a 4 block square ceiling light in the middle, and then adds 4 lights on each corner
                        pChunk.setBlockState(new BlockPos(chunkX + 8, 13 + y, chunkZ + 7), MDBlocksRegistry.CEILING_LIGHT.get().defaultBlockState(), false);
                        pChunk.setBlockState(new BlockPos(chunkX + 9, 13 + y, chunkZ + 8), MDBlocksRegistry.CEILING_LIGHT.get().defaultBlockState(), false);
                        pChunk.setBlockState(new BlockPos(chunkX + 8, 13 + y, chunkZ + 8), MDBlocksRegistry.CEILING_LIGHT.get().defaultBlockState(), false);
                        pChunk.setBlockState(new BlockPos(chunkX + 9, 13 + y, chunkZ + 7), MDBlocksRegistry.CEILING_LIGHT.get().defaultBlockState(), false);
                        pChunk.setBlockState(new BlockPos(chunkX + 2, 13 + y, chunkZ + 3), MDBlocksRegistry.CEILING_LIGHT.get().defaultBlockState(), false);
                        pChunk.setBlockState(new BlockPos(chunkX + 2, 13 + y, chunkZ + 14), MDBlocksRegistry.CEILING_LIGHT.get().defaultBlockState(), false);
                        pChunk.setBlockState(new BlockPos(chunkX + 13, 13 + y, chunkZ + 3), MDBlocksRegistry.CEILING_LIGHT.get().defaultBlockState(), false);
                        pChunk.setBlockState(new BlockPos(chunkX + 13, 13 + y, chunkZ + 14), MDBlocksRegistry.CEILING_LIGHT.get().defaultBlockState(), false);
                    }
                }
            }

            // Wall generation
            // uses a maze algorithm called recursive division to generate the walls
            // it starts off with an empty 16x16 (the size of a chunk) room

            RandomSource random = RandomSource.create();
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = 0; y < 4; y++) {
                        pChunk.setBlockState(new BlockPos(chunkX + x, 10 + y, chunkZ), MDBlocksRegistry.YELLOW_WALLPAPER.get().defaultBlockState(), false);
                        pChunk.setBlockState(new BlockPos(chunkX, 10 + y, chunkZ + z), MDBlocksRegistry.YELLOW_WALLPAPER.get().defaultBlockState(), false);
                    }
                }
            }

            // we then randomly add in 4 gaps on each side of the empty room
            int gapX = random.nextInt(3, 13);
            int gapZ = random.nextInt(3, 13);
            int maxGapSizeA = random.nextBoolean() ? 2 : 3;
            for (int i = 0; i < maxGapSizeA; i++) {
                for (int y = 0; y < 4; y++) {
                    pChunk.setBlockState(new BlockPos(chunkX + gapX + i, 10 + y, chunkZ), Blocks.AIR.defaultBlockState(), false);
                    pChunk.setBlockState(new BlockPos(chunkX, 10 + y, chunkZ + gapZ + i), Blocks.AIR.defaultBlockState(), false);
                }
            }

            // then we divide the room into 2 pieces either horizontally or vertically by placing wallpaper blocks by choosing a random x or z position
            // then we simply place a gap between the blocks that divide the room, then we repeat this several times, and tada, a randomized room

            for (int i = 0; i < random.nextInt(2, 4); i++) {
                int randX = random.nextInt(3, 13);
                int randZ = random.nextInt(3, 13);
                int targetX = random.nextInt(3, 13);
                int targetZ = random.nextInt(3, 13);
                int maxGapSizeB = random.nextBoolean() ? 1 : 2;

                if (random.nextBoolean()) {
                    for (int z = 0; z < 16; z++) {
                        for (int y = 0; y < 4; y++) {
                            if (z < targetX || z > targetX + maxGapSizeB) {
                                pChunk.setBlockState(new BlockPos(chunkX + randX, 10 + y, chunkZ + z), MDBlocksRegistry.YELLOW_WALLPAPER.get().defaultBlockState(), false);
                            } else {
                                pChunk.setBlockState(new BlockPos(chunkX + randX, 10 + y, chunkZ + z), Blocks.AIR.defaultBlockState(), false);
                            }
                        }
                    }
                } else {
                    for (int x = 0; x < 16; x++) {
                        for (int y = 0; y < 4; y++) {
                            if (x < targetZ || x > targetZ + maxGapSizeB) {
                                pChunk.setBlockState(new BlockPos(chunkX + x, 10 + y, chunkZ + randZ), MDBlocksRegistry.YELLOW_WALLPAPER.get().defaultBlockState(), false);
                            } else {
                                pChunk.setBlockState(new BlockPos(chunkX + x, 10 + y, chunkZ + randZ), Blocks.AIR.defaultBlockState(), false);
                            }
                        }
                    }
                }
            }
        }
        return CompletableFuture.completedFuture(pChunk);
    }

    @Override
    public int getSeaLevel() {
        return 0;
    }

    @Override
    public int getMinY() {
        return 0;
    }

    @Override
    public int getBaseHeight(int pX, int pZ, Heightmap.Types pType, LevelHeightAccessor pLevel, RandomState pRandom) {
        return 0;
    }

    @Override
    public NoiseColumn getBaseColumn(int pX, int pZ, LevelHeightAccessor pHeight, RandomState pRandom) {
        return null;
    }

    @Override
    public void addDebugScreenInfo(List<String> pInfo, RandomState pRandom, BlockPos pPos) {
    }
}
