package wa;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {

		if (packet.channel.equals(Wa.modid))
		{
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
			try
			{
				String type = data.readUTF();
				if(type.equals("tatara")) {
					int x, y, z;
					short cx, cy, cz;
					int burnTime;
					x = data.readInt();
					y = data.readInt();
					z = data.readInt();

					cx = data.readShort();
					cy = data.readShort();
					cz = data.readShort();
					burnTime = data.readInt();

					World world = Wa.proxy.getClientWorld();
					TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

					if (tileEntity instanceof TileEntityTatara)
					{
						TileEntityTatara tileEntityTatara = (TileEntityTatara)tileEntity;
						tileEntityTatara.cx = cx;
						tileEntityTatara.cy = cy;
						tileEntityTatara.cz = cz;
						tileEntityTatara.burnTime = burnTime;
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public static Packet getPacket(TileEntityTatara tileEntityTatara)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);

		int x = tileEntityTatara.xCoord;
		int y = tileEntityTatara.yCoord;
		int z = tileEntityTatara.zCoord;
		short cx = tileEntityTatara.cx;
		short cy = tileEntityTatara.cy;
		short cz = tileEntityTatara.cz;
		int burnTime = tileEntityTatara.burnTime;

		try
		{
			dos.writeUTF("tatara");
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
			dos.writeShort(cx);
			dos.writeShort(cy);
			dos.writeShort(cz);
			dos.writeInt(burnTime);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = Wa.modid;
		packet.data    = bos.toByteArray();
		packet.length  = bos.size();
		packet.isChunkDataPacket = true;

		return packet;
	}
}
