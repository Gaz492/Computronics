package pl.asie.computronics.tile;

import cpw.mods.fml.common.Optional;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import gnu.trove.set.hash.TIntHashSet;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import pl.asie.computronics.api.audio.AudioPacket;
import pl.asie.computronics.api.audio.IAudioReceiver;
import pl.asie.computronics.api.audio.IAudioSource;
import pl.asie.computronics.cc.ISidedPeripheral;
import pl.asie.computronics.reference.Config;
import pl.asie.computronics.reference.Mods;

public class TileSpeaker extends TileEntityPeripheralBase implements IAudioReceiver, ISidedPeripheral {
	private final TIntHashSet packetIds = new TIntHashSet();
	private IAudioSource lastSource;

	public TileSpeaker() {
		super("speaker");
	}

	@Override
	public void updateEntity() {
		packetIds.clear();
	}

	@Override
	public World getSoundWorld() {
		return worldObj;
	}

	@Override
	public int getSoundX() {
		return xCoord;
	}

	@Override
	public int getSoundY() {
		return yCoord;
	}

	@Override
	public int getSoundZ() {
		return zCoord;
	}

	@Override
	public int getSoundDistance() {
		return Config.TAPEDRIVE_DISTANCE;
	}

	@Override
	public void receivePacket(AudioPacket packet, ForgeDirection direction) {
		if(!packetIds.contains(packet.id)) {
			packetIds.add(packet.id);

			lastSource = packet.source;
			packet.addReceiver(this);
		}
	}

	@Override
	@Optional.Method(modid = Mods.ComputerCraft)
	public String[] getMethodNames() {
		return new String[0];
	}

	@Override
	@Optional.Method(modid = Mods.ComputerCraft)
	public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException, InterruptedException {
		return new Object[0];
	}

	@Override
	public boolean connectsAudio(ForgeDirection side) {
		return true;
	}

	@Override
	public boolean canConnectPeripheralOnSide(int side) {
		return false;
	}

	@Override
	@Optional.Method(modid = Mods.OpenComputers)
	protected void initOC(double s) {
		// NO-OP
	}

	@Override
	@Optional.Method(modid = Mods.OpenComputers)
	protected void initOC() {
		// NO-OP
	}
}
