package net.yesman.backrooms.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.yesman.backrooms.Backrooms;
import net.yesman.backrooms.server.blocks.MDBlocksRegistry;

public class MDLanguageProvider extends LanguageProvider {
    public MDLanguageProvider(PackOutput output) {
        super(output, Backrooms.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(MDBlocksRegistry.YELLOWWALL.get(), "Yellow Wall");
        add(MDBlocksRegistry.YELLOWWALL_STAIRS.get(), "Yellow Wall Stairs");
        add(MDBlocksRegistry.YELLOWWALL_SLAB.get(), "Yellow Wall Slab");
        add(MDBlocksRegistry.YELLOWCARPET.get(), "Yellow Carpet");
        add(MDBlocksRegistry.YELLOWCEILING.get(), "Yellow Ceiling");
        add(MDBlocksRegistry.LIGHT.get(), "Yellow Light");
    }
}
