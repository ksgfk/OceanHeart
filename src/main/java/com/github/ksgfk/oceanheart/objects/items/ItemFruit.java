package com.github.ksgfk.oceanheart.objects.items;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.util.IHasMod;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemFruit extends ItemFood implements IHasMod {
    public ItemFruit(String name, int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabsOceanHeart.tabsOceanHeart);

        ItemInit.ITEMS.add(this);

        setAlwaysEdible();
    }

    @Override
    public void registerModels() {
        OceanHeart.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        super.onFoodEaten(stack, worldIn, player);
        if (!worldIn.isRemote) {
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 6000, 5));
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 6000, 5));
            player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, 5));
            player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 6000, 5));
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 6000, 5));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 6000, 0));
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.set(0, TextFormatting.RED + I18n.format(getUnlocalizedName() + ".name"));
        tooltip.add(TextFormatting.GOLD + I18n.format("tooltip." + getUnlocalizedName(stack) + ".desc"));
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
