package Items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
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
	
	@Override
    public void addInformation(ItemStack stack, World player, List<String> list, ITooltipFlag whatisthis) {
        
        list.add("Returns you to where you used the Recall Mirror last time.");
        list.add("");
        if(ItemRecallMirror.oldPlayerCoords == null) { list.add("You haven't used the Recall Mirror yet."); }
        else
        {
        	list.add("\u00A74Ready to teleport back.");
        	list.add("\u00A74Depth: \u00A7r" + ItemRecallMirror.oldPlayerCoords.getY() );
        }
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
