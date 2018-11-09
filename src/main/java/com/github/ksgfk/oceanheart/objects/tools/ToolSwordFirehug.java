package com.github.ksgfk.oceanheart.objects.tools;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.entity.EntityOHFireBall;
import com.github.ksgfk.oceanheart.event.EventHandler;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.util.IHasMod;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ToolSwordFirehug extends ItemAxe implements IHasMod {
    public ToolSwordFirehug(String name, ToolMaterial materual) {
        super(materual, 18F, -2.0F);
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

        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
            NBTTagCompound nbt1 = new NBTTagCompound();

            nbt1.setInteger("count", 0);
            stack.getTagCompound().setTag("count", nbt1);
        }

        int count = stack.getTagCompound().getInteger("count");

        try {
            tooltip.add(I18n.format("tooltip." + getUnlocalizedName(stack) + ".desc.1"));
            tooltip.add(I18n.format("tooltip.desc.underline"));
            //tooltip.add(Config.swordYanhug_3 + switchOnOff);
            tooltip.add(I18n.format("tooltip." + getUnlocalizedName(stack) + ".desc.2") + count + "/10");
            tooltip.add(I18n.format("tooltip." + getUnlocalizedName(stack) + ".desc.3"));
            tooltip.add(I18n.format("tooltip.desc.underline"));
            if (stack.getEnchantmentTagList().tagCount() == 0) {
                stack.addEnchantment(Enchantment.getEnchantmentByID(16), 15);
                stack.addEnchantment(Enchantment.getEnchantmentByID(49), 2);
                stack.addEnchantment(Enchantment.getEnchantmentByID(17), 8);
                stack.addEnchantment(Enchantment.getEnchantmentByID(18), 8);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 10);
                stack.addEnchantment(Enchantment.getEnchantmentByID(20), 10);
            }
            if (!(stack.getTagCompound().hasKey("Unbreakable"))) {
                stack.setTagCompound(new NBTTagCompound().getCompoundTag("Unbreakable"));
                stack.getTagCompound().setBoolean("Unbreakable", true);
            }
        } catch (NullPointerException e) {
            System.out.println("No nbt key called \"levelup\"");
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        EventHandler.hitBlockBoom event;
        event = new EventHandler.hitBlockBoom(player, player.getPosition(), player.world);
        EventHandler.EVENT_BUS.post(event);
        if (!world.isRemote) {
            ItemStack item1 = player.getHeldItemMainhand();

            Entity fireball1 = new EntityOHFireBall(world, player, player.getLook(1.0F).x, player.getLook(1.0F).y, player.getLook(1.0F).z,5.0F);
            Entity fireball = new EntityOHFireBall(world, player, player.getLook(1.0F).x, player.getLook(1.0F).y, player.getLook(1.0F).z,0.0F);

            int count1 = item1.getTagCompound().getInteger("count");
            if (count1 == 10) {
                player.world.spawnEntity(fireball1);
                item1.getTagCompound().setInteger("count", 0);
            } else {
                player.world.spawnEntity(fireball);
                item1.getTagCompound().setInteger("count", item1.getTagCompound().getInteger("count") + 1);
            }
        }
        return super.onItemRightClick(world, player, hand);
    }
}
