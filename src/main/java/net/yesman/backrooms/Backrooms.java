package net.yesman.backrooms;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yesman.backrooms.server.blocks.MDBlocksRegistry;
import net.yesman.backrooms.server.items.MDItemsRegistry;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Backrooms.MODID)
public class Backrooms {
    public static final String MODID = "md";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Backrooms() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MDBlocksRegistry.BLOCKS.register(modEventBus);
        MDItemsRegistry.ITEMS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(MDBlocksRegistry.YELLOWWALL);
            event.accept(MDBlocksRegistry.YELLOWWALL_STAIRS);
            event.accept(MDBlocksRegistry.YELLOWWALL_SLAB);
            event.accept(MDBlocksRegistry.YELLOWCARPET);
            event.accept(MDBlocksRegistry.YELLOWCEILING);

            event.accept(MDBlocksRegistry.LIGHT);

            event.accept(MDBlocksRegistry.WHITEWALL);
            event.accept(MDBlocksRegistry.WHITEWALL_STAIRS);
            event.accept(MDBlocksRegistry.WHITEWALL_SLAB);
            event.accept(MDBlocksRegistry.BROWNFLOOR);
            event.accept(MDBlocksRegistry.WHITECEILING);

            event.accept(MDBlocksRegistry.PALEWALL);
            event.accept(MDBlocksRegistry.PALEWALL_STAIRS);
            event.accept(MDBlocksRegistry.PALEWALL_SLAB);
        }
    }
}
