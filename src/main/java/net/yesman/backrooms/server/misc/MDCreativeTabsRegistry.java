package net.yesman.backrooms.server.misc;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.yesman.backrooms.Backrooms;
import net.yesman.backrooms.server.blocks.MDBlocksRegistry;

public class MDCreativeTabsRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Backrooms.MODID);

    public static final RegistryObject<CreativeModeTab> BACKROOMS_TAB = CREATIVE_TABS.register("backrooms_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.backrooms"))
            .icon(() -> new ItemStack(MDBlocksRegistry.YELLOW_WALLPAPER.get()))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(MDBlocksRegistry.YELLOW_WALLPAPER.get());
                output.accept(MDBlocksRegistry.YELLOW_WALLPAPER_STAIRS.get());
                output.accept(MDBlocksRegistry.YELLOW_WALLPAPER_SLAB.get());
                output.accept(MDBlocksRegistry.YELLOW_STAINED_CARPET.get());
                output.accept(MDBlocksRegistry.CEILING_TILE.get());
                output.accept(MDBlocksRegistry.CEILING_LIGHT.get());
                output.accept(MDBlocksRegistry.WHITE_WALLPAPER.get());
                output.accept(MDBlocksRegistry.WHITE_WALLPAPER_STAIRS.get());
                output.accept(MDBlocksRegistry.WHITE_WALLPAPER_SLAB.get());
                output.accept(MDBlocksRegistry.DESATURATED_DARK_PLANKS.get());
                output.accept(MDBlocksRegistry.WHITE_CEILING.get());
                output.accept(MDBlocksRegistry.PALE_WALLPAPER.get());
                output.accept(MDBlocksRegistry.PALE_WALLPAPER_STAIRS.get());
                output.accept(MDBlocksRegistry.PALE_WALLPAPER_SLAB.get());
            }).build());
}
