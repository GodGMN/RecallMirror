package com.godgmn.recallmirror;

import com.godgmn.recallmirror.init.ModBlocks;

import Items.ItemPortalScroll;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class RegistryHandler
{
	@SubscribeEvent
	public static void onBlockRegistry(RegistryEvent.Register<Block> event)
	{
		IForgeRegistry<Block> registry = event.getRegistry();
		registerBlock(registry, "butt_block", new Block(Material.SAND));
	}

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
    IForgeRegistry<Item> registry = event.getRegistry();
	registerItem(registry, "butt_block", new ItemBlock(ModBlocks.BUTT_BLOCK));
	//registerItem(registry, RecallMirror.PORTAL_SCROLL, new ItemPortalScroll());
    }

    // freebie helper methods
    public static void registerBlock(IForgeRegistry<Block> registry, String registryKey, Block entry)
    {
        ResourceLocation registryLocation = new ResourceLocation(RecallMirror.MODID, registryKey);
        entry.setRegistryName(registryLocation);
        entry.setUnlocalizedName(registryLocation.toString());    // I think translation keys have a different format in 1.12? may want to reevaluate
        registry.register(entry);
    }

    // freebie helper methods
    public static void registerItem(IForgeRegistry<Item> registry, String registryKey, Item entry)
    {
        ResourceLocation registryLocation = new ResourceLocation(RecallMirror.MODID, registryKey);
        entry.setRegistryName(registryLocation);
        entry.setUnlocalizedName(registryLocation.toString());    // I think translation keys have a different format in 1.12? may want to reevaluate
        registry.register(entry);
    }
}
