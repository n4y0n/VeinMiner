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

public final class Pattern3x3 implements VeinMiningPattern {
    private static Pattern3x3 instance;

    private final NamespacedKey key;

    private Pattern3x3() {
        this.key = new NamespacedKey(VeinMiner.getPlugin(), "3x3");
    }

    @Override
    public void allocateBlocks(Set<Block> blocks, VeinBlock type, Block origin, ToolCategory category, ToolTemplate toolTemplate, AlgorithmConfig algorithmConfig, MaterialAlias alias, Player player) {        
        BlockFace facing = player.getFacing();


        for (int a = -1; a <= 1; a++)
		{
			for (int b = -1; b <= 1; b++)
			{
				if (a == 0 && b == 0)
				{
					continue;
				}

				Block p = null;

				switch (facing)
				{
                    case WEST:
                    case EAST:
						p = origin.getRelative(0, a, b);
						break;
                    case UP:
                    case DOWN:
						p = origin.getRelative(a, 0, b);
						break;
                    case NORTH:
                    case SOUTH:
						p = origin.getRelative(a, b, 0);
                        break;
                    default:
                        break;
				}

				if (PatternUtils.isOfType(type, alias, p) || !algorithmConfig.isUseSameType())
				{
					blocks.add(p);
				}
			}
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
    public static Pattern3x3 get() {
        return (instance == null) ? instance = new Pattern3x3() : instance;
    }
}
