package net.yesman.backrooms.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.yesman.backrooms.Backrooms;
import net.yesman.backrooms.server.misc.MDSoundsRegistry;
import net.yesman.backrooms.util.Helper;

public class MDSoundProvider extends SoundDefinitionsProvider {
    public MDSoundProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Backrooms.MODID, helper);
    }

    @Override
    public void registerSounds() {
        SoundDefinition definition = SoundDefinition.definition();;
        definition.with(sound(Helper.createResource("ambient/fluorescent_buzzing")));
        add(MDSoundsRegistry.LEVEL_0_AMBIENT, definition);
    }
}
