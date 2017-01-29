package vorpal6.voltaics;

import java.io.File;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigManager {
	public static Configuration config;

	public static void init(File configFile)
	{
		if(config == null){
			config = new Configuration(configFile);
			load();
		}
	}
	
	public static void load(){
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.getModID().equalsIgnoreCase(Voltaics.MODID))
		{
			load();
		}
	}
}
