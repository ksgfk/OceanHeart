package com.github.ksgfk.oceanheart.objects.tools;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.util.IHasMod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ToolSwordBaldrhonor extends ItemAxe implements IHasMod {
    public ToolSwordBaldrhonor(String name, ToolMaterial materual) {
        super(materual, 14F, -1.2F);
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
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            ItemStack item = player.getHeldItemMainhand();
            if (item.getTagCompound().getInteger("count") >= 1000) {

                NBTTagCompound potion3 = new NBTTagCompound().getCompoundTag("Potion");
                Item potion2 = new Item().getItemById(438);
                ItemStack potion1 = new ItemStack(potion2, 1, 0, potion3);

                for (int a = -7; a <= 7; a++) {
                    for (int c = -7; c <= 7; c++) {
                        Entity potion = new EntityPotion(world, player.posX + c, player.posY, player.posZ + a, potion1);
                        world.spawnEntity(potion);
                    }
                }

                PotionEffect health = new PotionEffect(Potion.getPotionById(6), 2, 6);

                List<EntityPlayer> otherEntity = world.playerEntities;

                for (EntityPlayer otherPlayer : otherEntity) {
                    double otherPlayerPosX = otherPlayer.posX;
                    double otherPlayerPosY = otherPlayer.posY;
                    double otherPlayerPosZ = otherPlayer.posZ;

                    double playerPosX = player.posX;
                    double playerPosY = player.posY;
                    double playerPosZ = player.posZ;

                    int treatRadius = 7;

                    if (otherPlayerPosX >= playerPosX - treatRadius && otherPlayerPosX <= playerPosX + treatRadius) {
                        if (otherPlayerPosY >= playerPosY - treatRadius && otherPlayerPosY <= playerPosY + treatRadius) {
                            if (otherPlayerPosZ >= playerPosZ - treatRadius && otherPlayerPosZ <= playerPosZ + treatRadius) {
                                otherPlayer.addPotionEffect(health);
                                otherPlayer.sendMessage(new TextComponentString(I18n.translateToLocal("tooltip." + getUnlocalizedName(item) + ".desc.4") + player.getDisplayNameString() + I18n.translateToLocal("tooltip." + getUnlocalizedName(item) + ".desc.5")));
                            }
                        }
                    }
                }
                item.getTagCompound().setInteger("count", 0);
            }
        }
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {


        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound().getCompoundTag("Unbreakable"));
            stack.getTagCompound().setBoolean("Unbreakable", true);

            stack.setTagCompound(new NBTTagCompound());
            NBTTagCompound nbt1 = new NBTTagCompound();

            nbt1.setInteger("count", 0);
            stack.getTagCompound().setTag("count", nbt1);
        }

        if (!(stack.getTagCompound().hasKey("Unbreakable"))) {
            stack.setTagCompound(new NBTTagCompound().getCompoundTag("Unbreakable"));
            stack.getTagCompound().setBoolean("Unbreakable", true);
        }

        int count = stack.getTagCompound().getInteger("count");

        try {
            tooltip.add(I18n.translateToLocal("tooltip." + getUnlocalizedName(stack) + ".desc.1"));
            tooltip.add(I18n.translateToLocal("tooltip.desc.underline"));
            tooltip.add(I18n.translateToLocal("tooltip." + getUnlocalizedName(stack) + ".desc.2") + count + "/1000");
            tooltip.add(I18n.translateToLocal("tooltip." + getUnlocalizedName(stack) + ".desc.3"));
            tooltip.add(I18n.translateToLocal("tooltip.desc.underline"));
            if (stack.getEnchantmentTagList().tagCount() == 0) {
                stack.addEnchantment(Enchantment.getEnchantmentByID(17), 20);
                stack.addEnchantment(Enchantment.getEnchantmentByID(18), 20);
                stack.addEnchantment(Enchantment.getEnchantmentByID(16), 11);
                stack.addEnchantment(Enchantment.getEnchantmentByID(49), 2);
            }
        } catch (NullPointerException e) {
            System.out.println("No nbt key called \"levelup\"");
        }
    }
}