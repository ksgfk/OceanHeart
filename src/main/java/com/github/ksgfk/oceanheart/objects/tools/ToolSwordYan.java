package com.github.ksgfk.oceanheart.objects.tools;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.event.EventHandler;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.util.IHasMod;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ToolSwordYan extends ItemAxe implements IHasMod {
    public ToolSwordYan(String name, ToolMaterial material) {
        super(material, 22F, -3.2F);
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
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!entity.world.isRemote) {
            EventHandler.hitBlockBoom event;
            event = new EventHandler.hitBlockBoom(player, entity.getPosition(), entity.world);
            EventHandler.EVENT_BUS.post(event);

            IBlockState fire = Block.getStateById(51);
            int count = stack.getTagCompound().getInteger("count");
            boolean ifSwitchIsTrue = stack.getTagCompound().getBoolean("switch");

            if (ifSwitchIsTrue) {
                if (count < 7) {
                    stack.getTagCompound().setInteger("count", stack.getTagCompound().getInteger("count") + 1);
                    player.world.setBlockState(new BlockPos(entity.posX, entity.posY, entity.posZ), fire);
                } else if (count == 7) {
                    if (!event.isCanceled() && !entity.world.isRemote) {
                        for (int a = 0; a <= 2; a++) {
                            for (int b = 0; b <= 2; b++) {
                                Entity tnt = new EntityTNTPrimed(event.world, entity.getPosition().getX() + a, entity.getPosition().getY() + 30, entity.getPosition().getZ() + b, null);
                                player.world.spawnEntity(tnt);
                            }
                        }

                        for (int c = -4; c <= 4; c++) {
                            for (int d = -4; d <= 4; d++) {
                                player.world.setBlockState(new BlockPos(entity.posX + c, entity.posY, entity.posZ + d), fire);
                            }
                        }
                    }
                    stack.getTagCompound().setInteger("count", 0);
                }
            }
        }
        return false;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            ItemStack item = player.getHeldItemMainhand();

            boolean ifSwitchIsTrue = item.getTagCompound().getBoolean("switch");
            if (!ifSwitchIsTrue) {
                item.getTagCompound().setBoolean("switch", true);
                player.sendMessage(new TextComponentString(I18n.translateToLocal("tooltip." + getUnlocalizedName(item) + ".desc.2") + I18n.translateToLocal("tooltip.desc.on")));
            } else {
                item.getTagCompound().setBoolean("switch", false);
                player.sendMessage(new TextComponentString(I18n.translateToLocal("tooltip." + getUnlocalizedName(item) + ".desc.2") + I18n.translateToLocal("tooltip.desc.off")));
            }
        }
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
            NBTTagCompound nbt = new NBTTagCompound();
            NBTTagCompound nbt1 = new NBTTagCompound();


            nbt.setBoolean("switch", false);
            nbt1.setInteger("count", 0);
            stack.getTagCompound().setTag("switch", nbt);
            stack.getTagCompound().setTag("count", nbt1);
        }

        int count = stack.getTagCompound().getInteger("count");
        boolean ifSwitchIsTrue = stack.getTagCompound().getBoolean("switch");
        String switchOnOff;

        if (ifSwitchIsTrue) {
            switchOnOff = I18n.translateToLocal("tooltip.desc.on");
        } else {
            switchOnOff = I18n.translateToLocal("tooltip.desc.off");
        }

        try {
            tooltip.add(I18n.translateToLocal("tooltip." + getUnlocalizedName(stack) + ".desc.1"));
            tooltip.add(I18n.translateToLocal("tooltip.desc.underline"));
            tooltip.add(I18n.translateToLocal("tooltip." + getUnlocalizedName(stack) + ".desc.2") + switchOnOff);
            tooltip.add(I18n.translateToLocal("tooltip." + getUnlocalizedName(stack) + ".desc.3") + count + "/7");
            tooltip.add(I18n.translateToLocal("tooltip." + getUnlocalizedName(stack) + ".desc.4"));
            tooltip.add(I18n.translateToLocal("tooltip.desc.underline"));
            if (stack.getEnchantmentTagList().tagCount() == 0) {
                stack.addEnchantment(Enchantment.getEnchantmentByID(20), 20);
                stack.addEnchantment(Enchantment.getEnchantmentByID(49), 20);
                stack.addEnchantment(Enchantment.getEnchantmentByID(21), 10);
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
