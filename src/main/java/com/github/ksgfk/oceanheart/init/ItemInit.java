package com.github.ksgfk.oceanheart.init;


import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.objects.armours.ArmourBase;
import com.github.ksgfk.oceanheart.objects.items.*;
import com.github.ksgfk.oceanheart.objects.tools.*;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<Item> ITEMS = new ArrayList<Item>();
    //Material
    public static final ToolMaterial TOOL_EXPLORER = EnumHelper.addToolMaterial("tool_explorer", 6, 6666, 8.0F, 2.0F, 10);
    public static final ToolMaterial TOOL_TRAILBLAZER = EnumHelper.addToolMaterial("tool_trailblaze", 6, 50, 8.0F, -3.95F, 100);
    public static final ToolMaterial TOOL_NATURE = EnumHelper.addToolMaterial("tool_nature", 3, 2333, 8.0F, 4.0F, 10);
    public static final ArmorMaterial ARMOUR_NATURE_DETERIORATION = EnumHelper.addArmorMaterial("armour_nature_deterioration", OceanHeart.MODID + ":nature", 18, new int[]{3, 6, 7, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F);
    public static final ToolMaterial TOOL_BALDRHONOR = EnumHelper.addToolMaterial("tool_baldrhonor", 6, 1280, 8.0F, 10, 10);
    public static final ToolMaterial TOOL_MORGES = EnumHelper.addToolMaterial("tool_morges", 6, 2560, 8.0F, 9, 10);
    public static final ToolMaterial TOOL_SARGART = EnumHelper.addToolMaterial("tool_sargart", 6, 3840, 8.0F, 14, 10);
    public static final ToolMaterial TOOL_YAN = EnumHelper.addToolMaterial("tool_yan", 6, 1280, 8.0F, 17, 10);
    public static final ToolMaterial TOOL_DEEPSEA = EnumHelper.addToolMaterial("tool_deepsea", 6, 1280, 8.0F, 8, 10);
    public static final ToolMaterial TOOL_ATLANTIS = EnumHelper.addToolMaterial("tool_atlantis", 6, 2560, 8.0F, 8, 10);
    public static final ToolMaterial TOOL_VAN = EnumHelper.addToolMaterial("tool_van", 6, 2333, 8.0F, 8, 10);
    public static final ToolMaterial TOOL_DRUIDHEART = EnumHelper.addToolMaterial("tool_druidheart", 6, 651, 8.0F, 8, 10);
    public static final ToolMaterial TOOL_SUN = EnumHelper.addToolMaterial("tool_sun", 6, 1280, 8.0F, 8, 10);
    public static final ToolMaterial TOOL_FIREHUG = EnumHelper.addToolMaterial("tool_firehug", 6, 2560, 8.0F, 8, 10);
    //Items
    public static final Item INGOT_CRYSTAL_NATURE = new ItemBase("ingot_crystal_nature");
    public static final Item INGOT_CRYSTAL_NATURE_DETERIORATION = new ItemBase("ingot_crystal_nature_deterioration");
    public static final Item POWDER_CRYSTAL_NATURE = new ItemBase("powder_crystal_nature");
    public static final Item POWDER_CRYSTAL_NATURE_SMALL = new ItemBase("powder_crystal_nature_small");
    public static final Item LEAVES_CRYSTAL_NATURE = new ItemBase("leaves_crystal_nature");
    public static final Item INGOT_LEGEND = new ItemIngotLegend("ingot_legend");
    public static final Item INGOT_LEGEND_GOLDEN = new ItemIngotLegend("ingot_legend_golden");
    public static final Item NUGGET_LEGEND = new ItemIngotLegend("nugget_legend");
    public static final Item INGOT_LEGEND_END = new ItemIngotLegendEnd("ingot_legend_end");
    public static final Item INGOT_LEVE_SOUL = new ItemIngotLeve("ingot_leve_soul");
    public static final Item INGOT_OCEAN_SOUL = new ItemIngotOcean("ingot_ocean_soul");
    public static final Item INGOT_OCEAN_SOUL_UNFORGE = new ItemIngotOcean("ingot_ocean_soul_unforge");
    public static final Item TERMINAL = new ItemLore("terminal");
    public static final Item BUTTON_HOME = new ItemBase("button_home");
    public static final Item BUTTON_SIGNIN = new ItemBase("button_signin");
    public static final Item BUTTON_TERRITORY = new ItemBase("button_territory");
    public static final Item BUTTON_WORLD = new ItemBase("button_world");
    public static final Item BUTTON_SPAWN = new ItemBase("button_spawn");
    public static final Item BUTTON_BANK = new ItemBase("button_bank");
    public static final Item BUTTON_BACK = new ItemBase("button_back");
    //Tools.
    //怎么有这么多斧头? --ksgfk
    public static final Item SWORD_FIREHUG = new ToolSwordFirehug("firehug_sword", TOOL_FIREHUG);
    public static final Item SWORD_SUN = new ToolSwordSun("sun_sword", TOOL_SUN);
    public static final Item SWORD_DRUIDHEART = new ToolSwordDruidHeart("druidheart_sword", TOOL_DRUIDHEART);
    public static final Item SWORD_VAN = new ToolSwordVan("van_sword", TOOL_VAN);
    public static final Item SWORD_ATLANTIS = new ToolSwordAtlantis("atlantis_sword", TOOL_ATLANTIS);
    public static final Item SWORD_DEEPSEA = new ToolSwordDeepSea("deepsea_sword", TOOL_DEEPSEA);
    public static final Item SWORD_YAN = new ToolSwordYan("yan_sword", TOOL_YAN);
    public static final Item SWORD_SARGART = new ToolSwordSargart("sargart_sword", TOOL_SARGART);
    public static final Item SWORD_MORGES = new ToolSwordMorges("morges_sword", TOOL_MORGES);
    public static final Item SWORD_BALDRHONOR = new ToolSwordBaldrhonor("baldr_sword", TOOL_BALDRHONOR);
    public static final Item SWORD_TRAILBLAZER = new ToolSwordTrailblazer("trailblazer_sword", TOOL_TRAILBLAZER);
    public static final Item SWORD_EXPLORER = new ToolSwordExplorer("explorer_sword", TOOL_EXPLORER);//Sword or axe?Who care
    public static final Item SWORD_NATURE = new ToolSword("nature_sword", TOOL_NATURE);
    public static final Item PICKAXE_NATURE = new ToolPickaxe("nature_pickaxe", TOOL_NATURE);
    public static final Item AXE_NATURE = new ToolAxe("nature_axe", TOOL_NATURE);
    public static final Item HOE_NATURE = new ToolHoe("nature_hoe", TOOL_NATURE);
    public static final Item SHOVEL_NATURE = new ToolShovel("nature_shovel", TOOL_NATURE);
    //Armours
    public static final Item HELMET_NATURE_DETERIORATION = new ArmourBase("nature_helmet_deterioration", ARMOUR_NATURE_DETERIORATION, 1, EntityEquipmentSlot.HEAD);
    public static final Item CHESTPLATE_NATURE_DETERIORATION = new ArmourBase("nature_chestplate_deterioration", ARMOUR_NATURE_DETERIORATION, 1, EntityEquipmentSlot.CHEST);
    public static final Item LEGGINGS_NATURE_DETERIORATION = new ArmourBase("nature_leggings_deterioration", ARMOUR_NATURE_DETERIORATION, 2, EntityEquipmentSlot.LEGS);
    public static final Item BOOTS_NATURE_DETERIORATION = new ArmourBase("nature_boots_deterioration", ARMOUR_NATURE_DETERIORATION, 1, EntityEquipmentSlot.FEET);
    //实验物品

}
