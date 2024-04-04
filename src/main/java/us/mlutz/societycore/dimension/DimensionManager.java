package us.mlutz.societycore.dimension;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;
import us.mlutz.czlib.CZLib;
import us.mlutz.societycore.Constants;
import us.mlutz.societycore.datapack.DataPackHandler;

@Mod.EventBusSubscriber
public class DimensionManager {
    private static ServerLevel aoxasDim = null;
    private static ServerLevel lobbyDim = null;
    @SubscribeEvent
    public static void handleServerAboutToStart(ServerAboutToStartEvent event) {
        aoxasDim = null;
        lobbyDim = null;
    }
    @SubscribeEvent
    public static void handleServerStarted(ServerStartedEvent event) {
        mapServerDimensions(event.getServer());
    }
    private static void mapServerDimensions(MinecraftServer server) {
        if(aoxasDim != null && lobbyDim != null) {
            return;
        }
        for(ServerLevel dim : server.getAllLevels()) {
            String dimLocation = dim.dimension().location().toString();
            if(dimLocation.equals(Constants.AoxasDim)) {
                if(aoxasDim == null) {
                    CZLib.Log.info(Constants.ModID, String.format(Constants.DimFound, "Aoxas"));
                    aoxasDim = dim;
                    DataPackHandler.prepareDataPack(aoxasDim);
                }
            }
            if(dimLocation.equals(Constants.LobbyDim)) {
                if(lobbyDim == null) {
                    CZLib.Log.info(Constants.ModID, String.format(Constants.DimFound, "Lobby"));
                    lobbyDim = dim;
                    DataPackHandler.prepareDataPack(lobbyDim);
                }
            }
        }
    }
    public static ServerLevel getAoxasDim() {
        if(aoxasDim == null) {
            mapServerDimensions(ServerLifecycleHooks.getCurrentServer());
        }
        return aoxasDim;
    }
    public static ServerLevel getLobbyDim() {
        if(lobbyDim == null) {
            mapServerDimensions(ServerLifecycleHooks.getCurrentServer());
        }
        return lobbyDim;
    }
}
