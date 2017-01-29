package vorpal6.voltaics.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import vorpal6.voltaics.MainRegistry;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event){
		MainRegistry.init();
	}
	
	public void init(FMLInitializationEvent event){
		
	}
	
	public void postInit(FMLPostInitializationEvent event){
		//NetworkRegistry.INSTANCE.registerGuiHandler(Voltaics.instance, new GuiHandler());
	}
}
