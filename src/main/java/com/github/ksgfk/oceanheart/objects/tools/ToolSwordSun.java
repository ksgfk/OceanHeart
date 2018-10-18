package com.github.ksgfk.oceanheart.objects.tools;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.util.IHasMod;
import net.minecraft.client.resources.I18n;
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

public class ToolSwordSun extends ItemAxe implements IHasMod {
    public ToolSwordSun(String name, ToolMaterial materual) {
        super(materual, 16F, -2.4F);
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
        super.addInformation(stack, worldIn, tooltip, flagIn);
        try {
            tooltip.add(I18n.format("tooltip." + getUnlocalizedName(stack) + ".desc.1"));
            tooltip.add(I18n.format("tooltip.desc.underline"));
            if (stack.getEnchantmentTagList().tagCount() == 0) {
                stack.addEnchantment(Enchantment.getEnchantmentByID(17), 18);
                stack.addEnchantment(Enchantment.getEnchantmentByID(18), 18);
                stack.addEnchantment(Enchantment.getEnchantmentByID(16), 15);
                stack.addEnchantment(Enchantment.getEnchantmentByID(49), 2);
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
