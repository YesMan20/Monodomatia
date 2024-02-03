package net.yesman.backrooms.server.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.yesman.backrooms.Backrooms;
import net.yesman.backrooms.server.worldgen.chunk.Level0ChunkGenerator;

public class MDChunkGeneratorsRegistry {
    public static final DeferredRegister<Codec<? extends ChunkGenerator>> CHUNK_GENERATORS = DeferredRegister.create(Registries.CHUNK_GENERATOR, Backrooms.MODID);

    public static final RegistryObject<Codec<? extends ChunkGenerator>> LEVEL_0_GENERATOR = CHUNK_GENERATORS.register("level_0_generator", () -> Level0ChunkGenerator.CODEC);
}
