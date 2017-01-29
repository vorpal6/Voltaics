package vorpal6.voltaics.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import vorpal6.voltaics.Voltaics;

public class VoltaicsBlock extends Block implements IModeledBlock {
	public Item itemBlock = null;
	public VoltaicsBlock(Material material, String name, boolean addToCreativeTab){
		super(material);
		setUnlocalizedName(name);
		setRegistryName(Voltaics.MODID+":"+name);
		if (addToCreativeTab){
			setCreativeTab(Voltaics.tab);
		}
		GameRegistry.register(this);
        GameRegistry.register(itemBlock = (new ItemBlock(this).setRegistryName(this.getRegistryName())));
	}

	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName().toString(),"inventory"));
	}

}
