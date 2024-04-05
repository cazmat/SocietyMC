package us.mlutz.societycore.teleporter;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import us.mlutz.czlib.CZLib;
import us.mlutz.societycore.dimension.DimensionManager;

public class TeleportManager {
    public static boolean teleportToAoxasDimension(ServerPlayer player) {
        ServerLevel aoxasDim = DimensionManager.getAoxasDim();
        return CZLib.Teleport.player(player, aoxasDim, 0, 1, 0);
    }
}
