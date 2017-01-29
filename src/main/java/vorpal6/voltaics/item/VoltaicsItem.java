package vorpal6.voltaics.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import vorpal6.voltaics.Voltaics;

public class VoltaicsItem extends Item implements IModeledItem {
	public VoltaicsItem(String name, boolean addToCreativeTab){
		super();
		setUnlocalizedName(name);
		setRegistryName(Voltaics.MODID+":"+name);
		if (addToCreativeTab){
			setCreativeTab(Voltaics.tab);
		}
		GameRegistry.register(this);
	}

	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString(),"inventory"));
	}
}
