package com.xxinvictus.cookingforpmmo;

import harmonised.pmmo.api.APIUtils;
import harmonised.pmmo.config.JType;
import harmonised.pmmo.events.ChunkDataHandler;
import net.blay09.mods.cookingforblockheads.api.event.OvenCookedEvent;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

@Mod.EventBusSubscriber
public class OvenEventHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void onOvenEvent(OvenCookedEvent event){
        handleCooking(event.getResultItem(), event.getWorld(), event.getPos());
    }

    public static void handleCooking(ItemStack input, World world, BlockPos pos)
    {
        UUID uuid = ChunkDataHandler.checkPos(world, pos);
        //LOGGER.debug("Got UUID, " + uuid);
        if(uuid != null)
        {
            Double award = APIUtils.getXp(input, JType.XP_VALUE_COOK).getOrDefault("cooking", 0d);
            if( award.equals(0d) && input.getItem().isEdible()) {
                award = new Double(input.getItem().getFoodProperties().getNutrition() * 4);
            }

            String source = "Cooking " + input.getItem().getRegistryName();
            APIUtils.addXp("cooking", uuid, award, source, false, false);
        }
    }
}
