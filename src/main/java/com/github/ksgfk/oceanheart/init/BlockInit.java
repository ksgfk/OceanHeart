package com.github.ksgfk.oceanheart.init;

import com.github.ksgfk.oceanheart.objects.blocks.BlockBase;
import com.github.ksgfk.oceanheart.objects.blocks.BlockHaveInfo;
import com.github.ksgfk.oceanheart.objects.blocks.BlockOres;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();
    //Normal method to register blocks
    public static final Block BLOCK_NATURAL_FORCE_CRYSTAL = new BlockBase("block_natural_force_crystal", Material.IRON);
    public static final Block ORE_GOLD_STRANGE_OVERWORLD = new BlockHaveInfo("ore_overworld_gold_strange", Material.IRON);
    public static final Block ORE_GOLD_STRANGE_END = new BlockHaveInfo("ore_end_gold_strange", Material.IRON);
    public static final Block ORE_LEVE_SOUL = new BlockHaveInfo("ore_nether_leve_soul", Material.IRON);
    public static final Block ORE_OCEAN_SOUL = new BlockHaveInfo("ore_overworld_ocean_soul", Material.IRON);
    //Register ores
    public static final Block ORE_END = new BlockOres("ore_end", "end");
    public static final Block ORE_OVERWORLD = new BlockOres("ore_overworld", "overworld");
    public static final Block ORE_NETHER = new BlockOres("ore_nether", "nether");
    //实验方块
    //public static final Block EXP = new BlockExp("exp", Material.IRON);
    //Register trees 不会，一直报错QAQ
    //public static final Block PLANKS = new CustomBlockPlanks("planks");//木板
    //public static final Block LOGS = new CustomBlockLog("planks");//原木
    //public static final Block LEAVES = new CustomBlockLeaf("leaves");//树叶
    //public static final Block SAPLINGS = new CustomBlockSapling("planks");//树苗
}
