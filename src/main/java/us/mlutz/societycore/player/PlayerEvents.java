package us.mlutz.societycore.player;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class PlayerEvents {
    @SubscribeEvent
    public static void PlayerDrops(LivingDropsEvent event) {
        if(!(event.getEntity() instanceof ServerPlayer)) return;
        if(event.getEntity() instanceof FakePlayer) return;
        final List<ItemEntity> drops = (List<ItemEntity>) event.getDrops();
        if(drops.isEmpty()) return;
        ServerPlayer player = (ServerPlayer) event.getEntity();
        ServerLevel level = player.getLevel();
        GameRules gameRules = level.getGameRules();
        if(gameRules.getBoolean(GameRules.RULE_KEEPINVENTORY)) return;

        event.setCanceled(false);
    }
}
