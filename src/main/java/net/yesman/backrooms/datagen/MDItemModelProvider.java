package net.yesman.backrooms.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.yesman.backrooms.Backrooms;

public class MDItemModelProvider extends ItemModelProvider {
    public MDItemModelProvider(PackOutput gen, ExistingFileHelper existingFileHelper) {
        super(gen, Backrooms.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
    }

    private void simpleItem(RegistryObject<Item> item) {
        simpleItem(item, false);
    }

    private void simpleItem(RegistryObject<Item> item, boolean handheld) {
        withExistingParent(item.getId().getPath(), new ResourceLocation(handheld ? "item/handheld" : "item/generated")).texture("layer0", new ResourceLocation(Backrooms.MODID, "item/" + item.getId().getPath()));
    }

    private void spawnEggItem(RegistryObject<Item> item) {
        withExistingParent(item.getId().getPath(), new ResourceLocation("item/template_spawn_egg"));
    }
}