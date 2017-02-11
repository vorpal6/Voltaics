package vorpal6.voltaics.packet;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import vorpal6.voltaics.Voltaics;

public class NetworkManager {
	public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Voltaics.MODID);
	
	public static void init(){
		int id = 0;
        INSTANCE.registerMessage(MessageTileUpdate.MessageHolder.class,MessageTileUpdate.class,id ++,Side.CLIENT);
	}
}
