package wtf.choco.veinminer.data.block;

import com.google.common.base.Objects;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

class VeinBlockDatable implements VeinBlock {

	private final BlockData data;
	private final String rawData;

	protected VeinBlockDatable(BlockData data, String rawData) {
		this.data = data;
		this.rawData = rawData;
	}

	@Override
	public Material getType() {
		return data.getMaterial();
	}

	@Override
	public boolean hasSpecificData() {
		return true;
	}

	@Override
	public BlockData getBlockData() {
		return data.clone();
	}

	@Override
	public boolean encapsulates(Block block) {
		return block != null && block.getBlockData().matches(data);
	}

	@Override
	public boolean encapsulates(BlockData data) {
		return data != null && data.matches(this.data);
	}

	@Override
	public boolean encapsulates(Material material) {
		return false;
	}

	@Override
	public String asDataString() {
		return rawData;
	}

	@Override
	public int hashCode() {
		return data.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof VeinBlockDatable)) return false;

		VeinBlockDatable other = (VeinBlockDatable) obj;
		return Objects.equal(data, other.data);
	}

	@Override
	public String toString() {
		return "{VeinBlockDatable:{\"Type\":\"" + data.getMaterial() + "\",\"Data\":\"" + rawData.substring(rawData.indexOf('[')) + "\"}}";
	}

}