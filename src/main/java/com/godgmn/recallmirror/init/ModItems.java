package com.godgmn.recallmirror.init;

import Items.ItemPortalScroll;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber()
public class ModItems
{
	
	static Item tutorialItem;
	
	public static void init()
	{
		tutorialItem = new ItemPortalScroll("portal_scroll");
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(tutorialItem);
	}


	public static void registerRenders(ModelRegistryEvent event)
	{
		registerRender(tutorialItem);
	}

	private static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
	}
}