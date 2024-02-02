package net.yesman.backrooms.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yesman.backrooms.Backrooms;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Backrooms.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new MDLanguageProvider(packOutput));
        generator.addProvider(true, new MDItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(true, new MDBlockStateProvider(packOutput, existingFileHelper));
    }
}
