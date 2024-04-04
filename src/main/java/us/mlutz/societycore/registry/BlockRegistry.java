package us.mlutz.societycore.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import us.mlutz.czlib.CZLib;
import us.mlutz.societycore.Constants;

import java.util.function.Supplier;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.ModID);
    public static final RegistryObject<Block> PLACEHOLDER_BLOCK = register_block(
            "placeholder_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)),
            CreativeModeTab.TAB_BUILDING_BLOCKS
    );
    private static <T extends Block> RegistryObject<T> register_block(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        register_blockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> void register_blockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus iEventBus) {
        BLOCKS.register(iEventBus);
        CZLib.Log.info(Constants.ModID, String.format("Registering %s blocks.", BLOCKS.getEntries().size()));
    }
}
