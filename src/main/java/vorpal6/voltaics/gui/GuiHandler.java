package vorpal6.voltaics.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import vorpal6.voltaics.tile.blast_furnace.ContainerBlastFurnace;
import vorpal6.voltaics.tile.blast_furnace.GuiBlastFurnace;
import vorpal6.voltaics.tile.blast_furnace.TileEntityBlastFurnace;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID){
			case 0: {
				return new GuiBlastFurnace(new ContainerBlastFurnace(player.inventory, (TileEntityBlastFurnace) world.getTileEntity(new BlockPos(x, y, z))));
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
		}
		return null;
	}

}
