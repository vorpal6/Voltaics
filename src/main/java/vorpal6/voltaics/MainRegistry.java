package vorpal6.voltaics;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vorpal6.voltaics.block.BlockBlastFurnace;
import vorpal6.voltaics.block.BlockPowerTester;
import vorpal6.voltaics.block.IModeledBlock;
import vorpal6.voltaics.block.VoltaicsBlock;
import vorpal6.voltaics.item.IModeledItem;
import vorpal6.voltaics.item.VoltaicsItem;
import vorpal6.voltaics.tile.TileEntityPowerTester;
import vorpal6.voltaics.tile.blast_furnace.TileEntityBlastFurnace;

public class MainRegistry {
	public static ArrayList<Item> items = new ArrayList<Item>();
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	
	public static Item copper_ingot, copper_nugget,
					   tin_ingot, tin_nugget,
					   steel_ingot, steel_nugget;

	public static Block copper_block,
						tin_block,
						steel_block,
						kiln_brick,
						blast_furnace;

	public static Block power_tester; //Temporary
	
	public static void init(){
		items.add(copper_ingot = new VoltaicsItem("copper_ingot",true));
		items.add(copper_nugget = new VoltaicsItem("copper_nugget",true));
		items.add(tin_ingot = new VoltaicsItem("tin_ingot",true));
		items.add(tin_nugget = new VoltaicsItem("tin_nugget",true));
		items.add(steel_ingot = new VoltaicsItem("steel_ingot",true));
		items.add(steel_nugget = new VoltaicsItem("steel_nugget",true));

		blocks.add(copper_block = (new VoltaicsBlock(Material.IRON,"copper_block",true).setHardness(1.4f)));
		blocks.add(tin_block = (new VoltaicsBlock(Material.IRON,"tin_block",true).setHardness(1.4f)));
		blocks.add(steel_block = (new VoltaicsBlock(Material.IRON,"steel_block",true).setHardness(1.8f)));
		blocks.add(kiln_brick = (new VoltaicsBlock(Material.ROCK,"kiln_brick",true).setHardness(1.2f)));
		blocks.add(blast_furnace = (new BlockBlastFurnace(Material.ROCK,"blast_furnace",true).setHardness(1.2f)));

		blocks.add(power_tester = new BlockPowerTester(Material.CACTUS, "power_tester", true).setHardness(1.2f));
		
		GameRegistry.registerTileEntity(TileEntityBlastFurnace.class, Voltaics.MODID+":blast_furnace");

		GameRegistry.registerTileEntity(TileEntityPowerTester.class, Voltaics.MODID+":power_tester");
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModels(){
		for (Item item : items){
			((IModeledItem)item).initModel();
		}
		for (Block block : blocks){
			((IModeledBlock)block).initModel();
		}
	}
}
