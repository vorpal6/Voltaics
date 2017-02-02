package vorpal6.voltaics.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
        return false;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state, EntityPlayer player) {

    }
}
