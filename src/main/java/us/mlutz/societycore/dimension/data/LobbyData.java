package us.mlutz.societycore.dimension.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;
import us.mlutz.societycore.Constants;
import us.mlutz.societycore.dimension.DimensionManager;

import java.util.Date;

@Mod.EventBusSubscriber
public class LobbyData extends SavedData {
    private static MinecraftServer server = null;
    private static LobbyData data = null;
    private static ServerLevel dimension = null;
    private boolean dimensionLoaded = false;
    private long lastUpdate;
    public LobbyData() {
        this.setDirty();
    }
    @SubscribeEvent
    public static void handleServerAboutToStart(ServerAboutToStartEvent event) {
        data = null;
        dimension = null;
        server = null;
    }
    public static LobbyData get() {
        if(LobbyData.data == null || LobbyData.dimension == null) {
            prepare(ServerLifecycleHooks.getCurrentServer());
        }
        return LobbyData.data;
    }
    public static void prepare(MinecraftServer server) {
        if(server == LobbyData.server && LobbyData.data != null && LobbyData.dimension != null) {
            return;
        }
        LobbyData.server = server;
        LobbyData.dimension = DimensionManager.getAoxasDim();
        if(LobbyData.dimension != null) {
            LobbyData.data = LobbyData.dimension.getDataStorage().computeIfAbsent(LobbyData::load, LobbyData::new, Constants.ModID);
        }
    }

    public boolean isDimensionLoaded() {
        return this.dimensionLoaded;
    }
    public void setDimensionLoaded(boolean loaded) {
        this.dimensionLoaded = loaded;
    }

    public static LobbyData load(CompoundTag compoundTag) {
        LobbyData aoxasData = new LobbyData();
        aoxasData.dimensionLoaded = compoundTag.getBoolean("DimensionLoaded");
        aoxasData.lastUpdate = compoundTag.getLong("LastUpdate");
        return aoxasData;
    }
    @Override
    public @NotNull CompoundTag save(CompoundTag compoundTag) {
        compoundTag.putBoolean("DimensionLoaded", this.dimensionLoaded);
        compoundTag.putLong("LastUpdate", new Date().getTime());
        return compoundTag;
    }
}
