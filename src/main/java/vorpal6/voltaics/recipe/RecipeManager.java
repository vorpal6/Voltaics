package vorpal6.voltaics.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import vorpal6.voltaics.MainRegistry;

public class RecipeManager {
	public static void init(){
		OreDictionary.registerOre("nuggetCopper", MainRegistry.copper_nugget);
		OreDictionary.registerOre("ingotCopper", MainRegistry.copper_ingot);
		OreDictionary.registerOre("blockCopper", MainRegistry.copper_block);
		OreDictionary.registerOre("nuggetSteel", MainRegistry.steel_nugget);
		OreDictionary.registerOre("ingotSteel", MainRegistry.steel_ingot);
		OreDictionary.registerOre("blockSteel", MainRegistry.steel_block);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainRegistry.copper_block),true,
				"XXX",
				"XXX",
				"XXX",
				'X', "ingotCopper"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainRegistry.steel_block),true,
				"XXX",
				"XXX",
				"XXX",
				'X', "ingotSteel"));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainRegistry.copper_ingot),true,
				"XXX",
				"XXX",
				"XXX",
				'X', "nuggetCopper"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainRegistry.steel_ingot),true,
				"XXX",
				"XXX",
				"XXX",
				'X', "nuggetSteel"));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainRegistry.copper_ingot,9),"blockCopper"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainRegistry.steel_ingot,9),"blockSteel"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainRegistry.copper_nugget,9),"ingotCopper"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MainRegistry.steel_nugget,9),"ingotSteel"));
	}
}
