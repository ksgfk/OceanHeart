package com.github.ksgfk.oceanheart.objects.tools;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.util.IHasMod;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ToolSwordVan extends ItemAxe implements IHasMod {
    private int rageLimit = 25;

    public ToolSwordVan(String name, ToolMaterial material) {
        super(material, 21F, -3.2F);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabsOceanHeart.tabsOceanHeart);

        //MinecraftForge.EVENT_BUS.register(this);

        ItemInit.ITEMS.add(this);
    }

    public void registerModels() {
        OceanHeart.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase victim, EntityLivingBase player) {
        int rageValue = stack.getTagCompound().getInteger("count");
        boolean rageSwitch = stack.getTagCompound().getBoolean("switch");

        if (player.world.isRemote) {
            return true;
        }

        if (rageValue < 25) {
            if (rageSwitch == false) {
                if (victim instanceof Entity) {
                    stack.getTagCompound().setInteger("count", stack.getTagCompound().getInteger("count") + 1);
                }
            }
        }

        player.sendMessage(new TextComponentString(I18n.format("tooltip." + getUnlocalizedName(stack) + ".desc.2") + rageValue + "/" + rageLimit));

        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack item = player.getHeldItemMainhand();
        int rageValue = item.getTagCompound().getInteger("count");
        boolean rageSwitch = item.getTagCompound().getBoolean("switch");

        if (rageValue == rageLimit) {
            if (rageSwitch == true) {
                item.getTagCompound().setBoolean("switch", true);
                player.sendMessage(new TextComponentString(I18n.format("tooltip." + getUnlocalizedName(item) + ".desc.3")));
            } else {
                item.getTagCompound().setBoolean("switch", true);
                player.sendMessage(new TextComponentString(I18n.format("tooltip." + getUnlocalizedName(item) + ".desc.3")));
            }
        }

        if (rageSwitch == true) {
            item.getTagCompound().setBoolean("switch", false);
            player.setHealth(player.getMaxHealth());
            item.getTagCompound().setInteger("tick", 100);
        }

        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        NBTTagCompound nbt;
        if (stack.hasTagCompound()) {
            nbt = stack.getTagCompound();
        } else {
            nbt = new NBTTagCompound();
        }
        stack.setTagCompound(nbt);

        nbt.setBoolean("switch", false);
        nbt.setInteger("count", 0);
        nbt.setInteger("tick", 100);

        int rageValue = stack.getTagCompound().getInteger("count");
        boolean ifSwitchIsTrue = stack.getTagCompound().getBoolean("switch");

        try {
            tooltip.add(I18n.format("tooltip." + getUnlocalizedName(stack) + ".desc.1"));
            tooltip.add(I18n.format("tooltip.desc.underline"));
            tooltip.add(I18n.format("tooltip." + getUnlocalizedName(stack) + ".desc.2") + rageValue + "/" + rageLimit);
            tooltip.add(I18n.format("tooltip." + getUnlocalizedName(stack) + ".desc.3"));
            tooltip.add(I18n.format("tooltip.desc.underline"));
            if (stack.getEnchantmentTagList().tagCount() == 0) {
                stack.addEnchantment(Enchantment.getEnchantmentByID(16), 15);
                stack.addEnchantment(Enchantment.getEnchantmentByID(17), 10);
                stack.addEnchantment(Enchantment.getEnchantmentByID(18), 10);
                stack.addEnchantment(Enchantment.getEnchantmentByID(49), 3);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 25);
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
