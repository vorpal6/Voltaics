package vorpal6.voltaics.tile.blast_furnace;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;
import vorpal6.voltaics.Voltaics;
import vorpal6.voltaics.tile.IVoltaicsTile;
import vorpal6.voltaics.tile.VoltaicsTileInventory;

public class TileEntityBlastFurnace extends VoltaicsTileInventory implements IVoltaicsTile {
	public ItemStackHandler inventory;
	
	public TileEntityBlastFurnace(){
		super(5);
	}

	@Override
	public boolean activate(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		player.openGui(Voltaics.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
	    return true;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		super.breakBlock(world, pos, state, player);
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		switch(index){
		case 0: {return TileEntityFurnace.isItemFuel(stack);}
		case 1: {
			int[] oreNames = OreDictionary.getOreIDs(stack);
			for (int i = 0; i < oreNames.length; i ++){
				if (OreDictionary.getOreName(oreNames[i]).substring(0, 3).compareTo("ore") == 0
			|| OreDictionary.getOreName(oreNames[i]).substring(0, 5).compareTo("ingot") == 0){
					return true;
				}
			}
			return false;
		}
		case 2: {
			int[] oreNames = OreDictionary.getOreIDs(stack);
			for (int i = 0; i < oreNames.length; i ++){
				if (OreDictionary.getOreName(oreNames[i]).substring(0, 3).compareTo("ore") == 0
			|| OreDictionary.getOreName(oreNames[i]).substring(0, 5).compareTo("ingot") == 0){
					return true;
				}
			}
			return false;
		}
		case 3: {return false;}
		case 4: {return false;}
		}
		return false;
	}

}
