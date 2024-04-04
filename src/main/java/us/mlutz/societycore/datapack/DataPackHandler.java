package us.mlutz.societycore.datapack;

import net.minecraft.server.level.ServerLevel;
import us.mlutz.czlib.CZLib;
import us.mlutz.societycore.Constants;
import us.mlutz.societycore.dimension.DimensionManager;
import us.mlutz.societycore.dimension.data.AoxasData;
import us.mlutz.societycore.dimension.data.LobbyData;

public class DataPackHandler {
    public static void prepareDataPack(ServerLevel level) {
        if(level == DimensionManager.getAoxasDim()) {
            if(AoxasData.get().isDimensionLoaded()) {
                CZLib.Log.info(Constants.ModID, String.format(Constants.skipDimDataFound, "Aoxas"));
            } else {
                runDataPack(level);
                AoxasData.get().setDimensionLoaded(true);
            }
        }
        if(level == DimensionManager.getLobbyDim()) {
            if(LobbyData.get().isDimensionLoaded()) {
                CZLib.Log.info(Constants.ModID, String.format(Constants.skipDimDataFound, "Lobby"));
            } else {
                runDataPack(level);
                LobbyData.get().setDimensionLoaded(true);
            }
        }
    }
    public static void runDataPack(ServerLevel level) {
        String dimensionLocation = level.dimension().location().toString();
        String dataFunction = dimensionLocation+"_load";
        CZLib.API.executeServerFunction(dataFunction, level);
    }
}
