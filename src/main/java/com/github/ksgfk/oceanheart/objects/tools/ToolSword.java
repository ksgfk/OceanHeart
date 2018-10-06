package com.github.ksgfk.oceanheart.objects.tools;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.Config;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.util.IHasMod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ToolSword extends ItemSword implements IHasMod {
    public ToolSword(String name, ToolMaterial materual) {
        super(materual);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabsOceanHeart.tabsOceanHeart);

        ItemInit.ITEMS.add(this);
    }

    public void registerModels() {
        OceanHeart.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!entity.world.isRemote && entity instanceof EntityPlayer) {

            //float a = ((EntityPlayer) entity).getHealth();
            float a = (float) (Math.random()) * 2;//pvp时随机回血0-5

            if (player.getHealth() >= 20) {
                return false;
            } else {
                if (player.getHealth() <= 0) {
                    return false;
                } else {
                    if ((a + player.getHealth()) >= 20) {
                        player.setHealth(20);
                        return true;
                    } else {
                        player.setHealth(a + player.getHealth());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase victim, EntityLivingBase player) {
        if (player.world.isRemote) {
            return true;
        }

        float a = victim.getHealth();
        a = a * 0.25f;//pve时根据怪物血量25%回血

        if (player.getHealth() >= 20) {
            return false;
        } else {
            if (player.getHealth() <= 0) {
                return false;
            } else {
                if (a <= 1) {
                    a = 1;//若怪物血量的25%不足1点，则固定回血1点;
                    if ((a + player.getHealth()) >= 20) {
                        player.setHealth(20);
                        return true;
                    } else {
                        player.setHealth(a + player.getHealth());
                        return true;
                    }
                } else {
                    if ((a + player.getHealth()) >= 20) {
                        player.setHealth(20);
                        return true;
                    } else {
                        player.setHealth(a + player.getHealth());
                        return true;
                    }
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(Config.swordNatureInfo_1);
        tooltip.add(Config.swordNatureInfo_2);
        tooltip.add(Config.underline);
    }
}