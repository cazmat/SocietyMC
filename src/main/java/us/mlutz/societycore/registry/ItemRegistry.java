package us.mlutz.societycore.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import us.mlutz.societycore.Constants;
import us.mlutz.societycore.CoreMain;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.ModID);
    public static void register(IEventBus iEventBus) {
        ITEMS.register(iEventBus);
        CoreMain.logInfo("Registering " + ITEMS.getEntries().size() + " items.");
    }
}
