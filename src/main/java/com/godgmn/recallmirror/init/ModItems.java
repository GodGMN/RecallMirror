package com.godgmn.recallmirror.init;

import com.godgmn.recallmirror.RecallMirror;

import Items.ItemPortalScroll;
import Items.ItemRecallMirror;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber()
public class ModItems
{
	
	static Item portalScroll;
	static Item recallMirror;
	
	public static void init()
	{
		portalScroll = new ItemPortalScroll("portal_scroll").setCreativeTab(CreativeTabs.TOOLS).setMaxStackSize(40);
		recallMirror = new ItemRecallMirror("recall_mirror");
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(portalScroll);
		event.getRegistry().registerAll(recallMirror);
	}


	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event)
	{
		registerRender(portalScroll);
		registerRender(recallMirror);
		registerRender(Item.getItemFromBlock(ModBlocks.BUTT_BLOCK));
	}

	private static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
	}
}