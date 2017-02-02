package vorpal6.voltaics.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vorpal6.voltaics.tile.TileEntityPowerTester;
import vorpal6.voltaics.tile.blast_furnace.TileEntityBlastFurnace;

public class BlockPowerTester extends VoltaicsBlockTile {
    public static final PropertyBool ON = PropertyBool.create("on");

    public BlockPowerTester(Material material, String name, boolean addToCreativeTab) {
        super(material, name, addToCreativeTab);
    }

    @Override
    public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, ON);
    }

    @Override
    public int getMetaFromState(IBlockState state){
        return state.getValue(ON) ? 1 : 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return getDefaultState().withProperty(ON, meta > 0);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityPowerTester();
    }
}
