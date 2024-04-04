package us.mlutz.societycore.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import us.mlutz.czlib.CZLib;
import us.mlutz.societycore.Constants;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.ModID);
    public static void register(IEventBus iEventBus) {
        ITEMS.register(iEventBus);
        CZLib.Log.info(Constants.ModID, String.format("Registering %s items.", ITEMS.getEntries().size()));
    }
}
