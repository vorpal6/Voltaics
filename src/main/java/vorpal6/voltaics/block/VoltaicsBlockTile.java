package vorpal6.voltaics.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vorpal6.voltaics.tile.IVoltaicsTile;
import vorpal6.voltaics.tile.blast_furnace.TileEntityBlastFurnace;

public class VoltaicsBlockTile extends VoltaicsBlock implements ITileEntityProvider {
	public VoltaicsBlockTile(Material material, String name, boolean addToCreativeTab) {
		super(material, name, addToCreativeTab);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return null;
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
		return ((IVoltaicsTile)world.getTileEntity(pos)).activate(world,pos,state,player,hand,player.getHeldItem(hand),side,hitX,hitY,hitZ);
	}
	
	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player){
		((IVoltaicsTile)world.getTileEntity(pos)).breakBlock(world,pos,state,player);
	}
}
