package vorpal6.voltaics.tile.blast_furnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerBlastFurnace extends Container {
	public TileEntityBlastFurnace tile = null;
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		if (tile != null){
			return true;
		}
		return false;
	}
	
	public class SlotBlastFurnace extends Slot {

		public SlotBlastFurnace(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}
		
		@Override
		public boolean isItemValid(ItemStack stack){
			return inventory.isItemValidForSlot(this.slotNumber, stack);
		}
		
	}
	
	public ContainerBlastFurnace(IInventory playerInv, TileEntityBlastFurnace te) {
	    this.tile = te;

	    this.addSlotToContainer(new SlotBlastFurnace(tile, 0, 30, 41));
	    this.addSlotToContainer(new SlotBlastFurnace(tile, 1, 65, 19));
	    this.addSlotToContainer(new SlotBlastFurnace(tile, 2, 65, 49));
	    this.addSlotToContainer(new SlotBlastFurnace(tile, 3, 125, 20));
	    this.addSlotToContainer(new SlotBlastFurnace(tile, 4, 125, 50));

	    for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
	        }
	    }

	    for (int x = 0; x < 9; ++x) {
	        this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
	    }
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer p, int i){
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot) inventorySlots.get(i);
        if (slot != null && slot.getHasStack()){
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
                
            if (i < 5){
            	if (!mergeItemStack(itemstack1, 5, inventorySlots.size(), true) && tile.isItemValidForSlot(i, itemstack1)){
            		return ItemStack.EMPTY;
            	}
            }
            else {
            	boolean isAllowed = false;
            	for (int j = 0; j < tile.slotCount; j ++){
            		if (tile.isItemValidForSlot(j, itemstack1)){
            			isAllowed = true;
            		}
            	}
            	if (!isAllowed){
            		return ItemStack.EMPTY;
            	}
                else if (!mergeItemStack(itemstack1, 0, 5, false) && tile.isItemValidForSlot(i, itemstack1)){
            		return ItemStack.EMPTY;
            	}
            }
            if (itemstack1.getCount() == 0){
                slot.putStack(ItemStack.EMPTY);
            }
            else {
            	slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}
