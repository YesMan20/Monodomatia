package net.yesman.backrooms.server.worldgen.dimension;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.yesman.backrooms.util.Helper;

public class MDDimensionsRegistry {
    public static final ResourceKey<Level> LEVEL_0 = ResourceKey.create(Registries.DIMENSION, Helper.createResource("level_0"));
}
