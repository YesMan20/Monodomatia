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

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class ToweringHallsChunkGenerator extends ChunkGenerator {
    public static final Codec<ToweringHallsChunkGenerator> CODEC = RecordCodecBuilder.create((codec) -> codec.group(
            BiomeSource.CODEC.fieldOf("biome_source").forGetter(ChunkGenerator::getBiomeSource)
    ).apply(codec, ToweringHallsChunkGenerator::new));
    
    public ToweringHallsChunkGenerator(BiomeSource pBiomeSource) {
        super(pBiomeSource);
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void applyCarvers(WorldGenRegion worldGenRegion, long l, RandomState randomState, BiomeManager biomeManager, StructureManager structureManager, ChunkAccess chunkAccess, GenerationStep.Carving carving) {
    }

    @Override
    public void buildSurface(WorldGenRegion worldGenRegion, StructureManager structureManager, RandomState randomState, ChunkAccess chunkAccess) {
    }

    @Override
    public void spawnOriginalMobs(WorldGenRegion worldGenRegion) {
    }

    @Override
    public int getGenDepth() {
        return 256;
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess chunkAccess) {
        int chunkX = chunkAccess.getPos().getRegionX();
        int chunkZ = chunkAccess.getPos().getRegionZ();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 2; y > 0; y--) {
                    chunkAccess.setBlockState(new BlockPos(chunkX + x, 10 - y, chunkZ + z), MDBlocksRegistry.YELLOWCARPET.get().defaultBlockState(), false);
                    chunkAccess.setBlockState(new BlockPos(chunkX + x, 18 + y, chunkZ + z), MDBlocksRegistry.YELLOWCEILING.get().defaultBlockState(), false);

                    // Generates a 4 block square ceiling light in the middle, and then adds 4 lights on each corner
                    chunkAccess.setBlockState(new BlockPos(chunkX + 8, 18 + y, chunkZ + 7), MDBlocksRegistry.LIGHT.get().defaultBlockState(), false);
                    chunkAccess.setBlockState(new BlockPos(chunkX + 9, 18 + y, chunkZ + 8), MDBlocksRegistry.LIGHT.get().defaultBlockState(), false);
                    chunkAccess.setBlockState(new BlockPos(chunkX + 8, 18 + y, chunkZ + 8), MDBlocksRegistry.LIGHT.get().defaultBlockState(), false);
                    chunkAccess.setBlockState(new BlockPos(chunkX + 9, 18 + y, chunkZ + 7), MDBlocksRegistry.LIGHT.get().defaultBlockState(), false);
                    chunkAccess.setBlockState(new BlockPos(chunkX + 2, 18 + y, chunkZ + 3), MDBlocksRegistry.LIGHT.get().defaultBlockState(), false);
                    chunkAccess.setBlockState(new BlockPos(chunkX + 2, 18 + y, chunkZ + 14), MDBlocksRegistry.LIGHT.get().defaultBlockState(), false);
                    chunkAccess.setBlockState(new BlockPos(chunkX + 13, 18 + y, chunkZ + 3), MDBlocksRegistry.LIGHT.get().defaultBlockState(), false);
                    chunkAccess.setBlockState(new BlockPos(chunkX + 13, 18 + y, chunkZ + 14), MDBlocksRegistry.LIGHT.get().defaultBlockState(), false);
                }
            }
        }

        // Wall generation
        // uses a maze algorithm called recursive division to generate the walls
        // it starts off with an empty 16x16 (the size of a chunk) room

        RandomSource random = RandomSource.create();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 9; y++) {
                    chunkAccess.setBlockState(new BlockPos(chunkX + x, 10 + y, chunkZ), MDBlocksRegistry.YELLOWWALL.get().defaultBlockState(), false);
                    chunkAccess.setBlockState(new BlockPos(chunkX, 10 + y, chunkZ + z), MDBlocksRegistry.YELLOWWALL.get().defaultBlockState(), false);
                }
            }
        }

        // we then randomly add in 4 gaps on each side of the empty room
        int gapX = random.nextInt(3, 13);
        int gapZ = random.nextInt(3, 13);
        int maxGapSizeA = random.nextBoolean() ? 2 : 3;
        for (int i = 0; i < maxGapSizeA; i++) {
            for (int y = 0; y < 4; y++) {
                chunkAccess.setBlockState(new BlockPos(chunkX + gapX + i, 10 + y, chunkZ), Blocks.AIR.defaultBlockState(), false);
                chunkAccess.setBlockState(new BlockPos(chunkX, 10 + y, chunkZ + gapZ + i), Blocks.AIR.defaultBlockState(), false);
            }
        }

        // then we divide the room into 2 pieces either horizontally or vertically by placing wallpaper blocks by choosing a random x or z position
        // then we simply place a gap between the blocks that divide the room, then we repeat this several times, and tada, a randomized room

        for (int i = 0; i < random.nextInt(1, 2); i++) {
            int randX = random.nextInt(3, 13);
            int randZ = random.nextInt(3, 13);
            int targetX = random.nextInt(3, 13);
            int targetZ = random.nextInt(3, 13);
            int maxGapSizeB = random.nextBoolean() ? 1 : 2;

            if (random.nextBoolean()) {
                for (int z = 0; z < 16; z++) {
                    for (int y = 0; y < 9; y++) {
                        if (z < targetX || z > targetX + maxGapSizeB) {
                            chunkAccess.setBlockState(new BlockPos(chunkX + randX, 10 + y, chunkZ + z), MDBlocksRegistry.YELLOWWALL.get().defaultBlockState(), false);
                        } else {
                            chunkAccess.setBlockState(new BlockPos(chunkX + randX, 10 + y, chunkZ + z), Blocks.AIR.defaultBlockState(), false);
                        }
                    }
                }
            } else {
                for (int x = 0; x < 16; x++) {
                    for (int y = 0; y < 9; y++) {
                        if (x < targetZ || x > targetZ + maxGapSizeB) {
                            chunkAccess.setBlockState(new BlockPos(chunkX + x, 10 + y, chunkZ + randZ), MDBlocksRegistry.YELLOWWALL.get().defaultBlockState(), false);
                        } else {
                            chunkAccess.setBlockState(new BlockPos(chunkX + x, 10 + y, chunkZ + randZ), Blocks.AIR.defaultBlockState(), false);
                        }
                    }
                }
            }
        }
        return CompletableFuture.completedFuture(chunkAccess);
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
    public int getBaseHeight(int i, int i1, Heightmap.Types types, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
        return 0;
    }

    @Override
    public NoiseColumn getBaseColumn(int i, int i1, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
        return null;
    }

    @Override
    public void addDebugScreenInfo(List<String> list, RandomState randomState, BlockPos blockPos) {
    }
}
