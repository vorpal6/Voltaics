package vorpal6.voltaics.container.basic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBasic extends Container {

    //The Attached Tile Entity
    private IInventory objectInventory;
    private int numSlots;
    private boolean hasPlayerInventory;

    /**
     * Base constructor of ContainerBasic. More on params can be found in the addSlots function.
     *
     * @param playerInv The player's inventory
     * @param objectInventory The inventory of the object (TileEntity, Player, Item, etc.) the container is for.
     * @param numSlots The number of slots for the object (don't include player inventory)
     * @param startX The X-position to start drawing at
     * @param startY The Y-position to start drawing at
     * @param slotDistanceX The distance between slots horizontally (including the size of the slot itself) [Default is 18]
     * @param slotDistanceY The distance between slots vertiacally (including the size of the slot itself) [Default is 18]
     * @param numSlotsInRow The number of slots to draw in a row before starting a new one.
     * @param addPlayerInventory Whether or not the container should include the player's inventory.
     */
    public ContainerBasic(InventoryPlayer playerInv, IInventory objectInventory, int numSlots, int startX, int startY, int slotDistanceX, int slotDistanceY, int numSlotsInRow, boolean addPlayerInventory) {
        this.objectInventory = objectInventory;
        this.numSlots = numSlots;
        this.hasPlayerInventory = addPlayerInventory;
        this.addSlots(playerInv, numSlots, startX, startY, slotDistanceX, slotDistanceY, numSlotsInRow, addPlayerInventory);
    }

    public ContainerBasic(InventoryPlayer playerInv, IInventory objectInventory, int numSlots, int startX, int startY, int numSlotsInRow, boolean addPlayerInventory) {
        this(playerInv, objectInventory, numSlots, startX, startY, 18, 18, numSlotsInRow, addPlayerInventory);
    }

    public ContainerBasic(InventoryPlayer playerInv, IInventory objectInventory, int numSlots, int numSlotsInRow) {
        this(playerInv, objectInventory, numSlots, 56, 17, 18, 18, numSlotsInRow, true);
    }

    //Most Basic constructor - probably the most common...
    //TODO: Make the slots centered horizontally instead of starting at a certain x-pos.
    public ContainerBasic(InventoryPlayer playerInv, IInventory objectInventory, int numSlots) {
        this(playerInv, objectInventory, numSlots, 56, 17, 18, 18, 9, true);
    }

    /**
     * Adds slots in a grid based on inputs. Can be overridden
     * to add more and different slots to your container, or to
     * change the behavior of base mechanics.
     *
     * For Reference: The default distance between slots (in inventory and chests, etc.) is 18:
     * 16 for the slot itself, then 2 between each slot.
     *
     * @param playerInv The player's inventory.
     * @param numSlots The total number of slots to add to the container.
     * @param startX The X-Position to start the upper left slot at.
     * @param startY The Y-Position to start the upper left slot at.
     * @param slotDistanceX The horizontal distance between slots. This includes the size of the slots themselves.
     * @param slotDistanceY The vertical distance between slots. This includes the size of the slots themselves.
     * @param numSlotsInRow The amount of slots per row. Columns will be automatically added if row overflows.
     * @param addPlayerInventory Whether or not to add the player's inventory to the GUI - This is the 36 slots that
     *                           comprise the inventory (hotbar included), not the armor slots or anything else. The
     *                           player inventory is not affected by slotDistanceX, slotDistanceY, or numSlotsInRow.
     *                           The player's inventory will be drawn beginning at startX and slotDistanceY below the
     *                           last container-specific slot drawn.
     */
    private void addSlots(InventoryPlayer playerInv, int numSlots, int startX, int startY, int slotDistanceX, int slotDistanceY, int numSlotsInRow, boolean addPlayerInventory) {
        for(int i=0; i<numSlots; i++) {
            int row = i/numSlotsInRow;
            int xPos = startX + (i%numSlotsInRow)*slotDistanceX;
            int yPos = startY + (row*slotDistanceY);
            this.addSlotToContainer(new Slot(objectInventory, i, xPos, yPos));
        }

        //Draw the player's inventory (9x3 grid of their inventory slots)
        if(addPlayerInventory) {
            int yPosStart = startY + (slotDistanceY * (numSlots/numSlotsInRow)) + slotDistanceY;
            for(int i=0; i<27; i++){
                int row = i/9;
                int xPos = startX + (i%9)*18;
                int yPos = yPosStart + (row*18);
                this.addSlotToContainer(new Slot(playerInv, numSlots+i, xPos, yPos));
            }

            //Draw hot-bar
            yPosStart += 58; //Add the distance of the 3 rows of inventory
            for(int i=0; i<9; i++){
                int xPos = startX + (i*18);
                int yPos = yPosStart;
                this.addSlotToContainer(new Slot(playerInv, numSlots+27+i, xPos, yPos));
            }
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()){
            ItemStack stack2 = slot.getStack();
            stack = stack2.copy();

            if(index < this.numSlots) {
                if(!this.mergeItemStack(stack2, this.numSlots + 27, this.inventorySlots.size()-1, false)){
                    return ItemStack.EMPTY;
                } else if(!this.mergeItemStack(stack2, this.numSlots, this.numSlots + 26, false)){
                    return ItemStack.EMPTY;
                }
            }
            else if(index >= this.numSlots && index < this.numSlots + 27){
                if(!this.mergeItemStack(stack2, 0, this.numSlots-1, false)){
                    return ItemStack.EMPTY;
                } else if(!this.mergeItemStack(stack2, this.numSlots + 27, this.inventorySlots.size()-1, false)){
                    return ItemStack.EMPTY;
                }
            }
            else if(index >= this.numSlots + 27 && index < this.inventorySlots.size()){
                if(!this.mergeItemStack(stack2, 0, this.numSlots +26, false)){
                    return ItemStack.EMPTY;
                }
            }

            if(stack2.getCount() == 0){
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if(stack2.getCount() == stack.getCount()){
                return ItemStack.EMPTY;
            }
            slot.onTake(player, stack2);
        }
        return stack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        if(playerIn != null) {
            return true;
        }
        return false;
    }
}
