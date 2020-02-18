package Items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.item.IItemPropertyGetter;


public class ItemNetherRecallMirror extends Item{
	
	public ItemNetherRecallMirror(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.TOOLS);
        
        this.addPropertyOverride(new ResourceLocation("charged"), new IItemPropertyGetter() //this sets an unknown value to 1. 0 = regular sprite, 1 = charged sprite.
	            {
        			@Override
	                public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) //it will change sprite to charged.
	                {
	                	NBTTagCompound tag = stack.getTagCompound();
	                	if(tag == null || !tag.getBoolean("used")) return 0F;
	                	else return 1F;
	                }
	            });
	}
	
	@Override	
    public void addInformation(ItemStack stack, World player, List<String> list, ITooltipFlag whatisthis) {
		
        list.add("An upgraded version of the regular Recall Mirror.");
        list.add("It will teleport you to your bed and back to where you used it.");
        list.add("");
        list.add("Doesn't work in the nether.");
    }
	
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        
        /*
         * If tag doesn't exist, it adds one so it doesn't give null errors
         */
        
        NBTTagCompound tag = itemstack.getTagCompound();
        
        if (tag == null) {
        	tag = new NBTTagCompound();
        }

        /*
         * This is all for item functionality now
         */
        
        if (playerIn.dimension == 0) //It won't work if the player is on the nether. I could have done it more cleanly with an elseif or something but my brain hurts
        {
	        if (!tag.getBoolean("used")) //if the mirror is not used (used = last teleport was to bed/spawn)
	        {
	        	tag.setDouble("PosX", playerIn.posX); //saves coordinates in NBT tags
	            tag.setDouble("PosY", playerIn.posY);
	            tag.setDouble("PosZ", playerIn.posZ);
	            
	            if (playerIn instanceof EntityPlayerMP) //I don't quite understand this one, it teleports you to spawn/bed via faking a respawn
		        {
		        	((EntityPlayerMP) playerIn).connection.player = playerIn.getServer().getPlayerList().recreatePlayerEntity((EntityPlayerMP) playerIn, playerIn.dimension, true);

		        }
	            
	            worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.NEUTRAL, 1F, 1F); //some sound
	        	
	            tag.setBoolean("used", true); //changes used to true because it teleported you to your bed/spawn
	        	
	        	itemstack.setTagCompound(tag); //saves NBT tags
	        	
	        } else { //this is what it will do if the mirror is used. Intended behaviour: teleport you back to where you used it and set used to false again so you can teleport again to bed
	        	double tagX = tag.getDouble("PosX"); //gets the saved position and stores it into local variables
	        	double tagY = tag.getDouble("PosY");
	        	double tagZ = tag.getDouble("PosZ");
	        	
	        	playerIn.setPositionAndUpdate(tagX, tagY, tagZ); //sets player position to stored variables
	        	
	        	worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.NEUTRAL, 1F, 1F); //some more sound
	        	
	        	tag.setBoolean("used", false); //sets used to false since next teleport should bring you back to bed again
	        	
	        	itemstack.setTagCompound(tag); //saves NBT tags
	        }
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}
