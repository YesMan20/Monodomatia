package net.yesman.backrooms;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yesman.backrooms.server.blocks.MDBlocksRegistry;
import net.yesman.backrooms.server.items.MDItemsRegistry;
import net.yesman.backrooms.server.misc.MDCreativeTabsRegistry;
import net.yesman.backrooms.server.misc.MDSoundsRegistry;
import net.yesman.backrooms.server.worldgen.MDChunkGeneratorsRegistry;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Backrooms.MODID)
public class Backrooms {
    public static final String MODID = "md";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Backrooms() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MDBlocksRegistry.BLOCKS.register(modEventBus);
        MDChunkGeneratorsRegistry.CHUNK_GENERATORS.register(modEventBus);
        MDCreativeTabsRegistry.CREATIVE_TABS.register(modEventBus);
        MDItemsRegistry.ITEMS.register(modEventBus);
        MDSoundsRegistry.SOUND_EVENTS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
}
