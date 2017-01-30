package vorpal6.voltaics.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vorpal6.voltaics.tile.blast_furnace.TileEntityBlastFurnace;

public class BlockBlastFurnace extends VoltaicsBlockTile {
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool ON = PropertyBool.create("on");

	public BlockBlastFurnace(Material material, String name, boolean addToCreativeTab) {
		super(material, name, addToCreativeTab);
	}
	
	@Override
	public BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, FACING, ON);
	}
	
	@Override
	public int getMetaFromState(IBlockState state){
		return state.getValue(FACING).getIndex() + (state.getValue(ON) ? 6 : 0);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta){
		return getDefaultState().withProperty(FACING,EnumFacing.getFront(meta % 6)).withProperty(ON, meta > 5);
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
		return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(ON, false);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBlastFurnace();
	}

}
