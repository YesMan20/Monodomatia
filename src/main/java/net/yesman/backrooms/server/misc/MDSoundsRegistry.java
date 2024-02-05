package net.yesman.backrooms.server.misc;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yesman.backrooms.Backrooms;
import net.yesman.backrooms.util.Helper;

public class MDSoundsRegistry {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Backrooms.MODID);

    public static final RegistryObject<SoundEvent> LEVEL_0_AMBIENT = registerSound("ambient.level_0.buzzing");

    public static RegistryObject<SoundEvent> registerSound(String name) {
        ResourceLocation id = Helper.createResource(name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
}
