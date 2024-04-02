package us.mlutz.societycore.datapack;

import net.minecraft.server.level.ServerLevel;
import us.mlutz.societycore.CoreMain;
import us.mlutz.societycore.command.CommandHandler;
import us.mlutz.societycore.dimension.DimensionManager;
import us.mlutz.societycore.dimension.data.AoxasData;

public class DataPackHandler {
    public static void prepareDataPack(ServerLevel level) {
        if(level == DimensionManager.getAoxasDim()) {
            if(AoxasData.get().isDimensionLoaded()) {
                CoreMain.logInfo("Skipping datapack for Aoxas dimension, as dimension already loaded.");
            } else {
                runDataPack(level);
                AoxasData.get().setDimensionLoaded(true);
            }
        }
    }
    public static void runDataPack(ServerLevel level) {
        String dimensionLocation = level.dimension().location().toString();
        CoreMain.logInfo(dimensionLocation);
        String dataFunction = dimensionLocation+"_load";
        CommandHandler.executeServerFunction(dataFunction, level);
    }
}
