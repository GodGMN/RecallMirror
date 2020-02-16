package Items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemNetherRecallMirror extends Item{
	
	public ItemNetherRecallMirror(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.TOOLS);
	}
	
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean playerUsedFirstMirror = true;
        
        if(ItemRecallMirror.oldPlayerCoords == null) { playerUsedFirstMirror = false; }
        
        if(playerUsedFirstMirror) {
        	
        	BlockPos bed = playerIn.getBedLocation();
        	double fmX = ItemRecallMirror.oldPlayerCoords.getX();
        	double fmY = ItemRecallMirror.oldPlayerCoords.getY();
        	double fmZ = ItemRecallMirror.oldPlayerCoords.getZ();
        
        	playerIn.setPositionAndUpdate(fmX, fmY, fmZ);
        	
        	playerIn.getCooldownTracker().setCooldown(this, 200);
            	
        	worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.NEUTRAL, 1F, 1F);
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}
