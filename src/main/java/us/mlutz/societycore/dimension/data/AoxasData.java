package us.mlutz.societycore.dimension.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;
import us.mlutz.societycore.Constants;
import us.mlutz.societycore.dimension.DimensionManager;

import java.util.Date;

@Mod.EventBusSubscriber
public class AoxasData extends SavedData {
    private static MinecraftServer server = null;
    private static AoxasData data = null;
    private static ServerLevel dimension = null;
    private boolean dimensionLoaded = false;
    private long lastUpdate;
    public AoxasData() {
        this.setDirty();
    }
    @SubscribeEvent
    public static void handleServerAboutToStart(ServerAboutToStartEvent event) {
        data = null;
        dimension = null;
        server = null;
    }
    public static AoxasData get() {
        if(AoxasData.data == null || AoxasData.dimension == null) {
            prepare(ServerLifecycleHooks.getCurrentServer());
        }
        return AoxasData.data;
    }
    public static void prepare(MinecraftServer server) {
        if(server == AoxasData.server && AoxasData.data != null && AoxasData.dimension != null) {
            return;
        }
        AoxasData.server = server;
        AoxasData.dimension = DimensionManager.getAoxasDim();
        if(AoxasData.dimension != null) {
            AoxasData.data = AoxasData.dimension.getDataStorage().computeIfAbsent(AoxasData::load, AoxasData::new, Constants.ModID);
        }
    }

    public boolean isDimensionLoaded() {
        return this.dimensionLoaded;
    }
    public void setDimensionLoaded(boolean loaded) {
        this.dimensionLoaded = loaded;
    }

    public static AoxasData load(CompoundTag compoundTag) {
        AoxasData aoxasData = new AoxasData();
        aoxasData.dimensionLoaded = compoundTag.getBoolean("DimensionLoaded");
        aoxasData.lastUpdate = compoundTag.getLong("LastUpdate");
        return aoxasData;
    }
    @Override
    public CompoundTag save(CompoundTag compoundTag) {
        compoundTag.putBoolean("DimensionLoaded", this.dimensionLoaded);
        compoundTag.putLong("LastUpdate", new Date().getTime());
        return compoundTag;
    }
}
