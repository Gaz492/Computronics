package pl.asie.computronics.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import li.cil.oc.api.network.Environment;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import pl.asie.computronics.gui.GUIs;
import pl.asie.computronics.tile.TileTicketMachine;

/**
 * @author Vexatos
 */
public class BlockTicketMachine extends BlockMachineSidedIcon {

	private IIcon mFront;

	public BlockTicketMachine() {
		super();
		this.setBlockName("computronics.ticketMachine");
		this.setGuiID(GUIs.TICKET_MACHINE);
		this.setNoNedoComputers(true);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileTicketMachine) {
			((TileTicketMachine) tile).onBlockPlacedBy(entity, stack);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileTicketMachine();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getAbsoluteSideIcon(int sideNumber, int metadata) {
		return sideNumber == 2 ? mFront : super.getAbsoluteSideIcon(sideNumber, metadata);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		super.registerBlockIcons(r);
		// TODO add proper texture
		// mFront = r.registerIcon("computronics:ticket_machine_front");
		mFront = r.registerIcon("computronics:tape_drive_front");
	}

	@Override
	public Class<? extends Environment> getTileEntityClass(int meta) {
		return TileTicketMachine.class;
	}
}
