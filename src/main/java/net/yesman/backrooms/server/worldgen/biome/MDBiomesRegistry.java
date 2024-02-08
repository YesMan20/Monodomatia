package net.yesman.backrooms.server.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.yesman.backrooms.util.Helper;

public class MDBiomesRegistry {
    public static final ResourceKey<Biome> YELLOW_HALLS = ResourceKey.create(Registries.BIOME, Helper.createResource("yellow_halls"));
    public static final ResourceKey<Biome> TOWERING_HALLS = ResourceKey.create(Registries.BIOME, Helper.createResource("towering_halls"));
}
