package us.mlutz.societycore.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.server.ServerLifecycleHooks;

public class CommandHandler {
    public static void executeServerCommand(String command, ServerLevel level) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if(server == null) {
            return;
        }
        Commands commands = server.getCommands();
        CommandSourceStack commandSourceStack = server.createCommandSourceStack().withLevel(level).withSuppressedOutput();
        CommandDispatcher<CommandSourceStack> commandDispatcher = commands.getDispatcher();
        ParseResults<CommandSourceStack> parseResults = commandDispatcher.parse(command, commandSourceStack);
        commands.performCommand(parseResults, command);
    }
    public static void executeServerFunction(String function, ServerLevel level) {
        executeServerCommand(String.format("function %s", function), level);
    }
}
