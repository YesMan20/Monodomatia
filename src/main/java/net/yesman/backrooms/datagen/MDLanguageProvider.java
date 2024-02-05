package net.yesman.backrooms.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import net.yesman.backrooms.Backrooms;
import net.yesman.backrooms.server.blocks.MDBlocksRegistry;
import net.yesman.backrooms.server.misc.MDSoundsRegistry;

public class MDLanguageProvider extends LanguageProvider {
    public MDLanguageProvider(PackOutput output) {
        super(output, Backrooms.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Blocks
        addBlock(MDBlocksRegistry.PALEWALL, "Pale Wallpaper");
        addBlock(MDBlocksRegistry.PALEWALL_STAIRS, "Pale Wallpaper Stairs");
        addBlock(MDBlocksRegistry.PALEWALL_SLAB, "Pale Wallpaper Slab");

        addBlock(MDBlocksRegistry.WHITEWALL, "White Wallpaper");
        addBlock(MDBlocksRegistry.WHITEWALL_STAIRS, "White Wallpaper Stairs");
        addBlock(MDBlocksRegistry.WHITEWALL_SLAB, "White Wallpaper Slab");
        addBlock(MDBlocksRegistry.BROWNFLOOR, "Desaturated Dark Planks");
        addBlock(MDBlocksRegistry.WHITECEILING, "White Ceiling");

        addBlock(MDBlocksRegistry.YELLOWWALL, "Yellow Wallpaper");
        addBlock(MDBlocksRegistry.YELLOWWALL_STAIRS, "Yellow Wallpaper Stairs");
        addBlock(MDBlocksRegistry.YELLOWWALL_SLAB, "Yellow Wallpaper Slab");
        addBlock(MDBlocksRegistry.YELLOWCARPET, "Yellow Stained Carpet");
        addBlock(MDBlocksRegistry.YELLOWCEILING, "Ceiling Tile");

        addBlock(MDBlocksRegistry.LIGHT, "Ceiling Light");


        // Subtitles
        addSubtitle(MDSoundsRegistry.LEVEL_0_AMBIENT, "Fluorescent buzzing");
    }

    private void addSubtitle(RegistryObject<SoundEvent> key, String value) {
        add("subtitles." + key.get().getLocation().getPath(), value);
    }
}
