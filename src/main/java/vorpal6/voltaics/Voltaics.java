package vorpal6.voltaics;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vorpal6.voltaics.proxy.CommonProxy;

@Mod(modid = Voltaics.MODID, version = Voltaics.VERSION, name = Voltaics.NAME)
public class Voltaics
{
	public static final String NAME = "Voltaics";
    public static final String MODID = "voltaics";
    public static final String VERSION = "0.1";

    @SidedProxy(clientSide = "electrodev.voltaics.proxy.ClientProxy", serverSide = "electrodev.voltaics.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    @Instance("roots")
    public static Voltaics instance;
    
    public static CreativeTabs tab = new CreativeTabs("voltaics") {
    	@Override
    	public String getTabLabel(){
    		return "voltaics";
    	}
    	
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem(){
			return new ItemStack(MainRegistry.copper_ingot,1);
		}
	};
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ConfigManager.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new ConfigManager());
    	proxy.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event){
    	proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	proxy.postInit(event);
    }
}
