package us.mlutz.czlib;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.logging.LogUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.slf4j.Logger;

public class CZLib {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static String getResourceLocation(String a, String b) {
        return a + ":" + b;
    }
    public static class API {
        public static void executeServerCommand(String c, ServerLevel l) {
            MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
            if(server == null) {
                return;
            }
            Commands cs = server.getCommands();
            CommandSourceStack css = server.createCommandSourceStack().withLevel(l).withSuppressedOutput();
            CommandDispatcher<CommandSourceStack> cd = cs.getDispatcher();
            ParseResults<CommandSourceStack> pr = cd.parse(c, css);
            cs.performCommand(pr, c);
        }
        public static void executeServerFunction(String f, ServerLevel l) {
            executeServerCommand(String.format("function %s", f), l);
        }
    }
    public static class Log {
        public static void debug(String mid, String message) {
            LOGGER.debug(String.format("[%1$s] %2$s", mid, message));
        }
        public static void error(String mid, String message) {
            LOGGER.error(String.format("[%1$s] %2$s", mid, message));
        }
        public static void info(String mid, String message) {
            LOGGER.info(String.format("[%1$s] %2$s", mid, message));
        }
        public static void warning(String mid, String message) {
            LOGGER.warn(String.format("[%1$s] %2$s", mid, message));
        }
    }
    public static class Teleport {
        public static boolean player(ServerPlayer player, ServerLevel level, int x, int y, int z) {
            if(player.getLevel().isClientSide() || level == null) {
                return false;
            }
            if(player.level == level) {
                player.teleportTo(x, y, z);
                return true;
            }
            player.teleportTo(level, x, y, z, player.getYRot(), player.getXRot());
            return true;
        }
    }
}
