package wtf.choco.veinminer.pattern;

import java.util.Set;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import wtf.choco.veinminer.VeinMiner;
import wtf.choco.veinminer.data.AlgorithmConfig;
import wtf.choco.veinminer.data.MaterialAlias;
import wtf.choco.veinminer.data.block.VeinBlock;
import wtf.choco.veinminer.tool.ToolCategory;
import wtf.choco.veinminer.tool.ToolTemplate;

public final class PatternTunnel implements VeinMiningPattern {

    private static PatternTunnel instance;

    private final NamespacedKey key;

    private PatternTunnel() {
        this.key = new NamespacedKey(VeinMiner.getPlugin(), "tunnel");
    }

    @Override
    public void allocateBlocks(Set<Block> blocks, VeinBlock type, Block origin, ToolCategory category, ToolTemplate toolTemplate, AlgorithmConfig algorithmConfig, MaterialAlias alias, Player player) {
        int maxVeinSize = algorithmConfig.getMaxVeinSize();
        
        BlockFace facing = player.getFacing();

        for (int i = 0; blocks.size() < maxVeinSize; i++) {

			Block b = origin.getRelative((int)(facing.getDirection().getX() * i), (int)(facing.getDirection().getY() * i), (int) (facing.getDirection().getZ() * i));

			if (!PatternUtils.isOfType(type, alias, b))
			{
				break;
			}

            blocks.add(b);
        }
    }

    @Override
    public NamespacedKey getKey() {
        return key;
    }

    /**
     * Get a singleton instance of the default pattern.
     *
     * @return the default pattern
     */
    public static PatternTunnel get() {
        return (instance == null) ? instance = new PatternTunnel() : instance;
    }

}
