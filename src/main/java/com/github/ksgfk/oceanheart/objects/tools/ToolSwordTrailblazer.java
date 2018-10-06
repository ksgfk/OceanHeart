package com.github.ksgfk.oceanheart.objects.tools;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.Config;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.util.IHasMod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ToolSwordTrailblazer extends ItemSword implements IHasMod {

    public ToolSwordTrailblazer(String name, ToolMaterial material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabsOceanHeart.tabsOceanHeart);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        OceanHeart.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase victim, EntityLivingBase player) {
        if (player.world.isRemote) {
            return true;
        }

        /*
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
            NBTTagCompound nbt = new NBTTagCompound();

            nbt.setInteger("levelup", 0);
            stack.getTagCompound().setTag("trability", nbt);
        }
        */

        NBTTagCompound nbt;
        if (stack.hasTagCompound()) {
            nbt = stack.getTagCompound();
        } else {
            nbt = new NBTTagCompound();
        }
        stack.setTagCompound(nbt);

        int levelupValue = stack.getTagCompound().getInteger("levelup");

        if (levelupValue <= 100000) {
            if (victim instanceof EntityMob) {//检测是怪物+1,是动物不加,是玩家,如果玩家主手没东西+1,有东西+2
                stack.getTagCompound().setInteger("levelup", stack.getTagCompound().getInteger("levelup") + 1);
            } else if (victim instanceof EntityAnimal) {
                stack.getTagCompound().setInteger("levelup", stack.getTagCompound().getInteger("levelup"));
            } else if (victim instanceof EntityPlayer) {
                if (victim.getHeldItem(EnumHand.MAIN_HAND).isEmpty()) {
                    stack.getTagCompound().setInteger("levelup", stack.getTagCompound().getInteger("levelup") + 1);
                } else {
                    stack.getTagCompound().setInteger("levelup", stack.getTagCompound().getInteger("levelup") + 2);
                }
            }
        }

        float victimDamage = (float) (levelupValue * 0.01);//对敌人伤害是levelup的1%
        if (victim.getHealth() - victimDamage <= 0) {
            victim.setHealth(0);
        } else {
            victim.setHealth(victim.getHealth() - victimDamage);
        }
/*
        if (levelupValue >= 0 & levelupValue <= 50) {//升级加附魔
            if (stack.getEnchantmentTagList().hasNoTags()) {
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 1);//经验修补1
            }
        } else if (levelupValue > 50 & levelupValue <= 200) {
            if (stack.getEnchantmentTagList().tagCount() == 1) {
                stack.getEnchantmentTagList().removeTag(0);
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 2);//经验修补2
            }
        } else if (levelupValue > 200 & levelupValue <= 500) {
            if (stack.getEnchantmentTagList().tagCount() == 1) {
                stack.addEnchantment(Enchantment.getEnchantmentByID(16), 1);//锋利1
            }
        } else if (levelupValue > 500 & levelupValue <= 1000) {
            if (stack.getEnchantmentTagList().tagCount() == 2) {
                stack.getEnchantmentTagList().removeTag(1);
                stack.addEnchantment(Enchantment.getEnchantmentByID(16), 2);//锋利2
            }
        } else if (levelupValue > 1000 & levelupValue <= 1500) {
            if (stack.getEnchantmentTagList().tagCount() == 2) {
                stack.getEnchantmentTagList().removeTag(0);
                stack.getEnchantmentTagList().removeTag(1);
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);//经验修补3,满级
                stack.addEnchantment(Enchantment.getEnchantmentByID(16), 3);//锋利3
            }
        } else if (levelupValue > 1500 & levelupValue <= 2000) {
            if (stack.getEnchantmentTagList().tagCount() == 2) {
                stack.addEnchantment(Enchantment.getEnchantmentByID(19), 1);//击退1
            }
        } else if (levelupValue > 2000 & levelupValue <= 2500) {
            if (stack.getEnchantmentTagList().tagCount() == 3) {
                stack.getEnchantmentTagList().removeTag(1);
                stack.addEnchantment(Enchantment.getEnchantmentByID(16), 5);//锋利5
            }
        } else if (levelupValue > 2500 & levelupValue <= 3000) {
            if (stack.getEnchantmentTagList().tagCount() == 3) {
                stack.getEnchantmentTagList().removeTag(1);
                stack.addEnchantment(Enchantment.getEnchantmentByID(16), 7);//锋利7
            }
        } else if (levelupValue > 3000 & levelupValue <= 3500) {
            if (stack.getEnchantmentTagList().tagCount() == 3) {
                stack.getEnchantmentTagList().removeTag(1);
                stack.addEnchantment(Enchantment.getEnchantmentByID(16), 10);//锋利10
            }
        }
        上面写的什么辣鸡玩意 --@ksgfk
*/
        switch (levelupValue) {
            case 0:
                if (stack.getEnchantmentTagList().hasNoTags()) {
                    stack.addEnchantment(Enchantment.getEnchantmentByID(70), 1);
                    stack.addEnchantment(Enchantment.getEnchantmentByID(21), 1);
                    stack.addEnchantment(Enchantment.getEnchantmentByID(34), 1);
                } else {
                    for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                        stack.getEnchantmentTagList().removeTag(a - 1);
                    }
                    stack.addEnchantment(Enchantment.getEnchantmentByID(70), 1);
                    stack.addEnchantment(Enchantment.getEnchantmentByID(21), 1);
                    stack.addEnchantment(Enchantment.getEnchantmentByID(34), 1);
                    //stack.addEnchantment(Enchantment.getEnchantmentByLocation("soulbound"),1);
                }
                break;
            case 10 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 2);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 1);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 1);
                break;
            case 50 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 2);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 2);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 1);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 1);
                break;
            case 100 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 2);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 2);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 2);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 1);
            case 200 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 2);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 2);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 1);
                break;
            case 300 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 2);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 1);
                break;
            case 400 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 1);
                break;
            case 500 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 2);
                break;
            case 600 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 3);
                break;
            case 700 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 4);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 3);
                break;
            case 800 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 4);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 4);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 3);
                break;
            case 900 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 5);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 4);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 3);
                break;
            case 1000 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 5);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 5);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 3);
                break;
            case 2000 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 5);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 15);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 3);
                break;
            case 3000 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 5);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 25);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 3);
                break;
            case 4000 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 5);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 35);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 3);
                break;
            case 5000 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 5);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 45);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 3);
                break;
            case 10000 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 5);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 100);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 3);
                break;
            case 20000 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 5);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 200);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 3);
                break;
            case 50000 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 5);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 5000);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 3);
                break;
            case 100000 - 1:
                for (int a = stack.getEnchantmentTagList().tagCount(); a >= 1; a--) {
                    stack.getEnchantmentTagList().removeTag(a - 1);
                }
                stack.addEnchantment(Enchantment.getEnchantmentByID(70), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 5);
                stack.addEnchantment(Enchantment.getEnchantmentByID(34), 10000);
                stack.addEnchantment(Enchantment.getEnchantmentByID(22), 3);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        NBTTagCompound nbt;
        if (stack.hasTagCompound()) {
            nbt = stack.getTagCompound();
        } else {
            nbt = new NBTTagCompound();
        }
        stack.setTagCompound(nbt);

        try {
            tooltip.add(Config.swordTrailblazerInfo_1);
            //if (!(stack.getTagCompound() == null)) {
            int a = stack.getTagCompound().getInteger("levelup");
            tooltip.add(Config.underline);
            if (a < 100000) {
                String power_1 = Config.swordTrailblazerInfo_5 + a + "/100000";
                tooltip.add(power_1);
                tooltip.add(Config.swordTrailblazerInfo_2);
                tooltip.add(Config.underline);
            } else {
                tooltip.add(Config.swordTrailblazerInfo_3);
                tooltip.add(Config.swordTrailblazerInfo_4);
                tooltip.add(Config.underline);
            }
            // }
        } catch (NullPointerException e) {
            System.out.println("No nbt key called \"levelup\"");
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}