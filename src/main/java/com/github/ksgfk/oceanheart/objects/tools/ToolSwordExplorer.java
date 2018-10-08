package com.github.ksgfk.oceanheart.objects.tools;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.util.IHasMod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ToolSwordExplorer extends ItemAxe implements IHasMod {
    public ToolSwordExplorer(String name, ToolMaterial materual) {
        super(materual, 1F, 1.0F);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabsOceanHeart.tabsOceanHeart);

        MinecraftForge.EVENT_BUS.register(this);

        ItemInit.ITEMS.add(this);
    }

    public void registerModels() {
        OceanHeart.proxy.registerItemRenderer(this, 0, "inventory");
    }

    //感谢无尽贪婪作者开源的代码
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!entity.world.isRemote && entity instanceof EntityPlayer) {
            EntityPlayer victim = (EntityPlayer) entity;
            if (victim.capabilities.isCreativeMode && !victim.isDead && victim.getHealth() > 0) {
                victim.setHealth(0);
                victim.onDeath(new EntityDamageSource("peace", player));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase victim, EntityLivingBase player) {
        if (player.world.isRemote) {
            return true;
        }
        victim.setHealth(0);
        victim.onDeath(new EntityDamageSource("peace", player));
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        try {
            if (stack.getTagCompound() == null) {
                tooltip.add(I18n.translateToLocal("tooltip." + getUnlocalizedName(stack) + ".desc.1"));
                tooltip.add(I18n.translateToLocal("tooltip.desc.underline"));
            }
            /*
            if (!(stack.getTagCompound().hasKey("Unbreakable"))) {
                stack.setTagCompound(new NBTTagCompound().getCompoundTag("Unbreakable"));
                stack.getTagCompound().setBoolean("Unbreakable", true);
            }
            */
        } catch (NullPointerException e) {
            System.out.println("No nbt key called \"levelup\"");
        }
    }
}