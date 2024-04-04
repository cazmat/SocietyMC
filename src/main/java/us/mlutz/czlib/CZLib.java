package us.mlutz.czlib;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.server.ServerLifecycleHooks;

public class CZLib {
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
}
