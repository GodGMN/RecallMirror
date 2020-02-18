package Items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemPortalScroll extends Item{
	
	public ItemPortalScroll(String name) {			//
		setUnlocalizedName(name);					// Todo esto es información básica del objeto, los dos primeros es para que se registre con el nombre
		setRegistryName(name);						// que le das al crear el objeto, como el nombre interno
		this.maxStackSize = 40;						//
		this.setCreativeTab(CreativeTabs.TOOLS);	//
	}
	
	
	@Override
    public void addInformation(ItemStack stack, World player, List<String> list, ITooltipFlag whatisthis) {
        
        list.add("Returns you to your bed or world spawn.");	//Todo esto es para añadirle lore, va por list.add("") y a tomar por culo lo que escribes ahí dentro se mete
        list.add("");											//en el item
        list.add("Doesn't work in the nether");
        list.add("Single use.");
    }
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) //esto detecta cuando haces botón derecho con el item en la mano
    {
    	
        ItemStack itemstack = playerIn.getHeldItem(handIn); //esto es para conseguir el itemstack, hay dos cosas, el item que es el objeto en sí, y el itemstack que es el de tu inventario
        													//osea los objetos que tiras al suelo o que pones en la mesa de crafteo son itemstacks no items a secas
        
	        if (playerIn instanceof EntityPlayerMP && playerIn.dimension == 0)	//esto no lo entiendo mucho porque me lo hizo un tio pero basicamente le dice al juego que te has
	        {																	//muerto y que te lleve a la cama/spawn del mundo
	        	((EntityPlayerMP) playerIn).connection.player = playerIn.getServer().getPlayerList().recreatePlayerEntity((EntityPlayerMP) playerIn, playerIn.dimension, true);
		    	
		        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.NEUTRAL, 1F, 1F);	//esto reproduce
		        																																							//sonido sin mas
		        itemstack.shrink(1); //y esto te quita un objeto del que acabas de usar de la mano, basicamente es lo que lo hace consumible
	        }
        
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack); //esto tampoco lo entiendo mucho la estructura la copié de una enderpearl
    }
}