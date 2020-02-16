package Items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class ItemRecallMirror extends Item{
	
	public static BlockPos oldPlayerCoords = null;
	public static Biome oldBiome = null;
	
	public ItemRecallMirror(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.TOOLS);
	}
	
	@Override
    public void addInformation(ItemStack stack, World player, List<String> list, ITooltipFlag whatisthis) {
        
        list.add("Returns you to your bed.");
        list.add("");
        list.add("\u00A7oWon't work if you never slept.");
    }
	
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean playerhasbed = true;
        
        if(playerIn.bedLocation == null) { playerhasbed = false; }
        
        if(playerhasbed) {
        	oldPlayerCoords = playerIn.getPosition();
        
        	BlockPos bed = playerIn.getBedLocation();
        	double bedX = bed.getX();
        	double bedY = bed.getY();
        	double bedZ = bed.getZ();
        
        	playerIn.setPositionAndUpdate(bedX, bedY+1, bedZ);
        	
        	playerIn.getCooldownTracker().setCooldown(this, 200);
            	
        	worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.NEUTRAL, 1F, 1F);
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}
