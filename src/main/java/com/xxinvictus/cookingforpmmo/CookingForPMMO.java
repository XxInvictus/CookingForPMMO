package com.xxinvictus.cookingforpmmo;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("cookingforpmmo")
public class CookingForPMMO
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public CookingForPMMO() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(new OvenEventHandler());
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("Cooking For PMMO Loaded ready for some XP hot off the grill!");
    }
}
