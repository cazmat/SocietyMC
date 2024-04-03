package us.mlutz.societycore.teleporter;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import us.mlutz.societycore.dimension.DimensionManager;

public class TeleportManager {
    public static boolean teleportToAoxasDimension(ServerPlayer player) {
        ServerLevel aoxasDim = DimensionManager.getAoxasDim();
        return teleportPlayer(player, aoxasDim, 0, 1, 0);
    }

    private static boolean teleportPlayer(ServerPlayer player, ServerLevel dim, int x, int y, int z) {
        if(player.getLevel().isClientSide() || dim == null) {
            return false;
        }
        if(player.level == dim) {
            player.teleportTo(x, y, z);
            return true;
        }
        player.teleportTo(dim, x, y, z, player.getYRot(), player.getXRot());
        return true;
    }
}
