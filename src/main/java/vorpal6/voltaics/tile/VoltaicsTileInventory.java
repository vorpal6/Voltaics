package vorpal6.voltaics.tile;

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

public class VoltaicsTileInventory extends TileEntity implements IVoltaicsTile, IInventory {
	public int slotCount = 0;
	public ItemStackHandler inventory;
	public boolean needsUpdate = false;
	
	public VoltaicsTileInventory(int slotCount){
		this.slotCount = slotCount;
		inventory = new ItemStackHandler(slotCount){
	        @Override
	        protected void onContentsChanged(int slot) {
	        	markDirty();
	        }
		};
	}
	
	@Override
	public void markDirty(){
		super.markDirty();
		markForUpdate();
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setTag("inventory", inventory.serializeNBT());
		return tag;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		inventory.deserializeNBT(tag.getCompoundTag("inventory"));
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public boolean activate(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		return false;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (inventory != null && !world.isRemote){
			for (int i = 0; i < inventory.getSlots(); i ++){
				if (inventory.getStackInSlot(i) != null){
					world.spawnEntity(new EntityItem(world,getPos().getX()+0.5,getPos().getY()+0.5,getPos().getZ()+0.5,inventory.getStackInSlot(i)));
				}
			}
		}
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return slotCount;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < inventory.getSlots(); i ++){
			if (inventory.getStackInSlot(i) != ItemStack.EMPTY){
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory.getStackInSlot(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack toReturn = inventory.extractItem(index, count, false);
		markDirty();
		return toReturn;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack toReturn = inventory.getStackInSlot(index).copy();
		inventory.setStackInSlot(index,ItemStack.EMPTY);
		markDirty();
		return toReturn;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inventory.setStackInSlot(index,stack);
		markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < inventory.getSlots(); i ++){
			inventory.setStackInSlot(i, ItemStack.EMPTY);
		}
		markDirty();
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public void markForUpdate() {
		this.needsUpdate = true;
	}

	@Override
	public void clean() {
		this.needsUpdate = false;
	}
	
	@Override
	public boolean needsUpdate(){
		return needsUpdate;
	}

}
