package com.github.ksgfk.oceanheart.objects.blocks;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.init.BlockInit;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.objects.blocks.item.ItemBlockVariants;
import com.github.ksgfk.oceanheart.util.IHasMod;
import com.github.ksgfk.oceanheart.util.IMetaName;
import com.github.ksgfk.oceanheart.util.handlers.EnumHandlers;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CustomBlockPlanks extends Block implements IMetaName, IHasMod {
    public static final PropertyEnum<EnumHandlers.EnumType> VARIANT = PropertyEnum.<EnumHandlers.EnumType>create("variant", EnumHandlers.EnumType.class);

    private String name;

    public CustomBlockPlanks(String name) {
        super(Material.WOOD);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.WOOD);
        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandlers.EnumType.CRYSTAL_NATURE));
        setCreativeTab(CreativeTabsOceanHeart.tabsOceanHeart);

        this.name = name;
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
    }


    @Override
    public void registerModels() {
        for (int a = 0; a < EnumHandlers.EnumType.values().length; a++) {
            OceanHeart.proxy.registerVariantRenderer(Item.getItemFromBlock(this), a, "planks_" + EnumHandlers.EnumType.values()[a].getName(), "inventory");
        }
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumHandlers.EnumType.values()[stack.getItemDamage()].getName();
    }
}
