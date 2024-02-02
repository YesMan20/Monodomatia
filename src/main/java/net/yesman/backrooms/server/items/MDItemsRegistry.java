package net.yesman.backrooms.server.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.yesman.backrooms.Backrooms;

public class MDItemsRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Backrooms.MODID);

    //public static final RegistryObject<Item> CRABSHELL = ITEMS.register("crabshell",
            //() -> new Item(new Item.Properties().stacksTo(16).fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
