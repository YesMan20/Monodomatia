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
        addBlock(MDBlocksRegistry.PALE_WALLPAPER, "Pale Wallpaper");
        addBlock(MDBlocksRegistry.PALE_WALLPAPER_STAIRS, "Pale Wallpaper Stairs");
        addBlock(MDBlocksRegistry.PALE_WALLPAPER_SLAB, "Pale Wallpaper Slab");

        addBlock(MDBlocksRegistry.WHITE_WALLPAPER, "White Wallpaper");
        addBlock(MDBlocksRegistry.WHITE_WALLPAPER_STAIRS, "White Wallpaper Stairs");
        addBlock(MDBlocksRegistry.WHITE_WALLPAPER_SLAB, "White Wallpaper Slab");
        addBlock(MDBlocksRegistry.DESATURATED_DARK_PLANKS, "Desaturated Dark Planks");
        addBlock(MDBlocksRegistry.WHITE_CEILING, "White Ceiling");

        addBlock(MDBlocksRegistry.YELLOW_WALLPAPER, "Yellow Wallpaper");
        addBlock(MDBlocksRegistry.YELLOW_WALLPAPER_STAIRS, "Yellow Wallpaper Stairs");
        addBlock(MDBlocksRegistry.YELLOW_WALLPAPER_SLAB, "Yellow Wallpaper Slab");
        addBlock(MDBlocksRegistry.YELLOW_STAINED_CARPET, "Yellow Stained Carpet");
        addBlock(MDBlocksRegistry.CEILING_TILE, "Ceiling Tile");

        addBlock(MDBlocksRegistry.CEILING_LIGHT, "Ceiling Light");


        // Subtitles
        addSubtitle(MDSoundsRegistry.LEVEL_0_AMBIENT, "Fluorescent buzzing");


        // Misc
        add("itemGroup.backrooms", "Monodomatia");
    }

    private void addSubtitle(RegistryObject<SoundEvent> key, String value) {
        add("subtitles." + key.get().getLocation().getPath(), value);
    }
}
