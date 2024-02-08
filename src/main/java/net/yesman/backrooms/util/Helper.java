package net.yesman.backrooms.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.yesman.backrooms.Backrooms;

public class Helper {
    public static ResourceLocation createResource(String resource) {
        return new ResourceLocation(Backrooms.MODID, resource);
    }

    public static boolean checkBiomeInChunk(ResourceKey<Biome> biome, int x, int y, int z, ChunkAccess chunk) {
        return chunk.getNoiseBiome(x, y, z).is(biome);
    }
}
