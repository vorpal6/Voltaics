package vorpal6.voltaics.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import vorpal6.voltaics.container.basic.ContainerBasic;
import vorpal6.voltaics.gui.basic.GuiBasic;
import vorpal6.voltaics.tile.blast_furnace.ContainerBlastFurnace;
import vorpal6.voltaics.tile.blast_furnace.GuiBlastFurnace;
import vorpal6.voltaics.tile.blast_furnace.TileEntityBlastFurnace;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID){
			case 0: {
				return new ContainerBlastFurnace(player.inventory, (TileEntityBlastFurnace) world.getTileEntity(new BlockPos(x, y, z)));
			}
			case 1: {
				return new GuiBasic(player.inventory, new InventoryBasic("inventory.basic", false, 9), 9);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID){
			case 0: {
				return new GuiBlastFurnace(new ContainerBlastFurnace(player.inventory, (TileEntityBlastFurnace) world.getTileEntity(new BlockPos(x, y, z))));
			}
			case 1: {
				return new GuiBasic(player.inventory, new InventoryBasic("inventory.basic", false, 9), 9);
			}
		}
		return null;
	}

}
