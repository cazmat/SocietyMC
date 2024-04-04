package us.mlutz.societycore;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import us.mlutz.societycore.registry.BlockRegistry;
import us.mlutz.societycore.registry.ItemRegistry;

@Mod(Constants.ModID)
public class CoreMain {
    public CoreMain() {
        IEventBus iEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockRegistry.register(iEventBus);
        ItemRegistry.register(iEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
