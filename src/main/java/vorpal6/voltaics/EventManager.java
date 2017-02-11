package vorpal6.voltaics;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import vorpal6.voltaics.tile.IVoltaicsTile;

public class EventManager {
	@SubscribeEvent
	public void onServerTick(TickEvent.WorldTickEvent event){
		List<TileEntity> tiles = event.world.loadedTileEntityList;
		NBTTagList list = new NBTTagList();
		for (TileEntity t : tiles){
			if (t instanceof IVoltaicsTile){
				if (((IVoltaicsTile)t).needsUpdate()){
					((IVoltaicsTile)t).clean();
					list.appendTag(t.getUpdateTag());
				}
			}
		}
		NBTTagCompound tag = new NBTTagCompound();
		tag.setTag("data", list);
	}
}
