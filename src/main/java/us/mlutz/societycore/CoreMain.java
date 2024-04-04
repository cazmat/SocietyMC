package us.mlutz.societycore;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import us.mlutz.societycore.registry.BlockRegistry;
import us.mlutz.societycore.registry.ItemRegistry;

@Mod(Constants.ModID)
public class CoreMain {
    private static final Logger LOGGER = LogUtils.getLogger();
    public CoreMain() {
        IEventBus iEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockRegistry.register(iEventBus);
        ItemRegistry.register(iEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static void logDebug(String s) { LOGGER.debug(s); }
    public static void logError(String s) { LOGGER.error(s); }
    public static void logInfo(String s) {
        LOGGER.info(s);
    }
    public static void logWarning(String s) { LOGGER.warn(s); }
}
