package com.github.ksgfk.oceanheart.init;

import com.github.ksgfk.oceanheart.objects.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();
    //Normal method to register blocks
    public static final Block BLOCK_NATURAL_FORCE_CRYSTAL = new BlockBase("block_natural_force_crystal", Material.IRON);
    //Register ores
    public static final Block ORE_END = new BlockOres("ore_end", "end");
    public static final Block ORE_OVERWORLD = new BlockOres("ore_overworld", "overworld");
    public static final Block ORE_NETHER = new BlockOres("ore_nether", "nether");
    //Register trees 不会，一直报错QAQ
    //public static final Block PLANKS = new CustomBlockPlanks("planks");//木板
    //public static final Block LOGS = new CustomBlockLog("planks");//原木
    //public static final Block LEAVES = new CustomBlockLeaf("leaves");//树叶
    //public static final Block SAPLINGS = new CustomBlockSapling("planks");//树苗

}
