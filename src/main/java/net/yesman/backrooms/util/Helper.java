package net.yesman.backrooms.util;

import net.minecraft.resources.ResourceLocation;
import net.yesman.backrooms.Backrooms;

public class Helper {
    public static ResourceLocation createResource(String resource) {
        return new ResourceLocation(Backrooms.MODID, resource);
    }
}
