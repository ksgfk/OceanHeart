package com.github.ksgfk.oceanheart.objects.tools;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.Config;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.util.IHasMod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ToolSwordDeepSea extends ItemAxe implements IHasMod {
    public ToolSwordDeepSea(String name, ToolMaterial materual) {
        super(materual, 8F, 1.2F);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabsOceanHeart.tabsOceanHeart);

        MinecraftForge.EVENT_BUS.register(this);

        ItemInit.ITEMS.add(this);
    }

    public void registerModels() {
        OceanHeart.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound nbt;
        if (stack.hasTagCompound()) {
            nbt = stack.getTagCompound();
        } else {
            nbt = new NBTTagCompound();
        }
        stack.setTagCompound(nbt);
        nbt.setInteger("tick", 100);

        try {
            tooltip.add(Config.swordDeepSeaInfo_1);
            tooltip.add(Config.underline);
            tooltip.add(Config.swordDeepSeaInfo_2);
            tooltip.add(Config.underline);
            if (stack.getEnchantmentTagList().tagCount() == 0) {
                stack.addEnchantment(Enchantment.getEnchantmentByID(16), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(19), 6);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 6);
                stack.addEnchantment(Enchantment.getEnchantmentByID(17), 6);
                stack.addEnchantment(Enchantment.getEnchantmentByID(18), 6);
            }
            if (!(stack.getTagCompound().hasKey("Unbreakable"))) {
                stack.setTagCompound(new NBTTagCompound().getCompoundTag("Unbreakable"));
                stack.getTagCompound().setBoolean("Unbreakable", true);
            }
        } catch (NullPointerException e) {
            System.out.println("No nbt key called \"levelup\"");
        }
    }
}
