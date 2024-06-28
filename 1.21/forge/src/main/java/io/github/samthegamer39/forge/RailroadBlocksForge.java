package io.github.samthegamer39.forge;

import dev.architectury.platform.forge.EventBuses;
import io.github.samthegamer39.RailroadBlocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


/**
 * Main class for the RailroadBlocks mod. Initializes the mod and adds all associated blocks and items to Minecraft.
 */
@Mod(RailroadBlocks.MOD_ID)
public class RailroadBlocksForge {

    /**
     * This function initializes the mod and registers all blocks and items to the game.
     */
    public RailroadBlocksForge() {
        EventBuses.registerModEventBus(RailroadBlocks.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        RailroadBlocks.init();

        /*
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
         */

        RailroadBlocks.LOGGER.info("Railroad Blocks successfully loaded.");
    }
}
