package vorpal6.voltaics.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vorpal6.voltaics.Voltaics;
import vorpal6.voltaics.power.IPowered;

public class TileEntityPowerTester extends TileEntity implements IVoltaicsTile, IPowered{
	
    public TileEntityPowerTester(){
        super();
    }

    @Override
    public double getVoltage() {
        return 0;
    }

    @Override
    public void setVoltage() {

    }

    @Override
    public boolean activate(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        player.openGui(Voltaics.instance, 1, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state, EntityPlayer player) {

    }

	@Override
	public void markForUpdate() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void clean(){
		
	}

	@Override
	public boolean needsUpdate() {
		// TODO Auto-generated method stub
		return false;
	}
}
