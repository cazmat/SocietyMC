package us.mlutz.societycore.registry;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import us.mlutz.societycore.Constants;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.ModID);
    public static void register(IEventBus iEventBus) {
        BLOCKS.register(iEventBus);
    }
}
