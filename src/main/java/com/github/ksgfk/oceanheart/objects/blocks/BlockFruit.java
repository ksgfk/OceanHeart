package com.github.ksgfk.oceanheart.objects.blocks;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.init.BlockInit;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.util.IHasMod;
import net.minecraft.block.BlockGlowstone;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class BlockFruit extends BlockGlowstone implements IHasMod {
    //private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger("测试");

    public BlockFruit(String name, Material materialIn) {
        super(materialIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabsOceanHeart.tabsOceanHeart);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));

        setLightLevel(1);
    }

    @Override
    public void registerModels() {
        OceanHeart.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.set(0, TextFormatting.RED + I18n.format(getUnlocalizedName() + ".name"));
        tooltip.add(TextFormatting.GOLD + I18n.format("tooltip." + getUnlocalizedName() + ".desc"));
    }
}
