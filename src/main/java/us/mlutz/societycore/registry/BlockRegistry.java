package us.mlutz.societycore.registry;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import us.mlutz.societycore.Constants;
import us.mlutz.societycore.CoreMain;

import java.util.function.Supplier;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.ModID);
    public static <T extends Block> RegistryObject<T> register_block(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }
    public static void register(IEventBus iEventBus) {
        BLOCKS.register(iEventBus);
        CoreMain.logInfo("Registering " + BLOCKS.getEntries().size() + " blocks.");
    }
}
