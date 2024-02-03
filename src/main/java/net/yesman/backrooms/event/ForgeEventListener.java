package net.yesman.backrooms.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yesman.backrooms.Backrooms;
import net.yesman.backrooms.server.worldgen.dimension.MDDimensionsRegistry;

@Mod.EventBusSubscriber(modid = Backrooms.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventListener {
    @SubscribeEvent
    public static void entityDamaged(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();
        if (entity instanceof Player player && source.is(DamageTypes.IN_WALL)) {
            RandomSource random = RandomSource.create();
            if (player.level().dimension() != MDDimensionsRegistry.LEVEL_0 && random.nextInt(5) == 1) {
                ((ServerPlayer)player).teleportTo(player.getServer().getLevel(MDDimensionsRegistry.LEVEL_0), player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());
            }
        }
    }
}
