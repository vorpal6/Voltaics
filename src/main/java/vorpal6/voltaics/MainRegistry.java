package vorpal6.voltaics;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vorpal6.voltaics.block.IModeledBlock;
import vorpal6.voltaics.block.VoltaicsBlock;
import vorpal6.voltaics.item.IModeledItem;
import vorpal6.voltaics.item.VoltaicsItem;

public class MainRegistry {
	public static ArrayList<Item> items = new ArrayList<Item>();
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	
	public static Item copper_ingot, copper_nugget;
	
	public static Block copper_block;
	
	public static void init(){
		items.add(copper_ingot = new VoltaicsItem("copper_ingot",true));
		items.add(copper_nugget = new VoltaicsItem("copper_nugget",true));

		blocks.add(copper_block = (new VoltaicsBlock(Material.IRON,"copper_block",true).setHardness(1.4f)));
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
