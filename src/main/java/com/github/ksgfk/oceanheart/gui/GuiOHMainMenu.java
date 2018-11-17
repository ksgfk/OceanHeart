package com.github.ksgfk.oceanheart.gui;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServerDemo;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.gui.NotificationModUpdateScreen;
import net.minecraftforge.fml.client.GuiModList;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.List;

public class GuiOHMainMenu extends GuiMainMenu {
    private final Object threadLock = new Object();
    private int openGLWarning2Width;
    private int openGLWarning1Width;
    private int openGLWarningX1;
    private int openGLWarningY1;
    private int openGLWarningX2;
    private int openGLWarningY2;
    private String openGLWarning1 = "";
    private String openGLWarning2 = "";
    private GuiButton modButton;
    private int widthCopyright;
    private int widthCopyrightRest;
    private NotificationModUpdateScreen modUpdateNotification;
    private int ticks;
    private GuiBackground background = new GuiBackground(new ResourceLocation(OceanHeart.MODID, "textures/gui/main/layer"), 1);

    @Override
    public void initGui() {
        this.widthCopyright = this.fontRenderer.getStringWidth("Copyright Mojang AB");
        this.widthCopyrightRest = this.width - this.widthCopyright - 2;

        this.background.mc = this.mc;
        this.background.width = this.width;
        this.background.height = this.height;
        this.background.initGui();

        this.buttonList.clear();

        int j = this.height / 4 + 48;

        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, j, I18n.format("menu.singleplayer")));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, j + 24, I18n.format("menu.multiplayer")));
        //this.buttonList.add(this.realmsButton = new GuiButton(14, this.width / 2 + 2, j + 24 * 2, 98, 20, I18n.format("menu.online", new Object[0]).replace("Minecraft", "").trim()));

        this.buttonList.add(this.modButton = new GuiButton(6, this.width / 2 - 100, j + 24 * 2, 98, 20, I18n.format("fml.menu.mods")));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 124, j + 72 + 12, 20, 20, "L"));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, j + 72 + 12, 98, 20, I18n.format("menu.options")));
        this.buttonList.add(new GuiButton(4, this.width / 2 + 2, j + 72 + 12, 98, 20, I18n.format("menu.quit")));

        synchronized (this.threadLock) {
            this.openGLWarning1Width = this.fontRenderer.getStringWidth(this.openGLWarning1);
            this.openGLWarning2Width = this.fontRenderer.getStringWidth(this.openGLWarning2);
            int k = Math.max(this.openGLWarning1Width, this.openGLWarning2Width);
            this.openGLWarningX1 = (this.width - k) / 2;
            this.openGLWarningY1 = (this.buttonList.get(0)).y - 24;
            this.openGLWarningX2 = this.openGLWarningX1 + k;
            this.openGLWarningY2 = this.openGLWarningY1 + 24;
        }

        this.mc.setConnectedToRealms(false);
/*
        if (Minecraft.getMinecraft().gameSettings.getOptionOrdinalValue(GameSettings.Options.REALMS_NOTIFICATIONS) && !this.hasCheckedForRealmsNotification) {
            RealmsBridge realmsbridge = new RealmsBridge();
            this.realmsNotification = realmsbridge.getNotificationScreen(this);
            this.hasCheckedForRealmsNotification = true;
        }

        if (this.areRealmsNotificationsEnabled()) {
            this.realmsNotification.setGuiSize(this.width, this.height);
            this.realmsNotification.initGui();
        }
*/
        this.modUpdateNotification = NotificationModUpdateScreen.init(this, this.modButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }

        if (button.id == 5) {
            this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
        }

        if (button.id == 1) {
            this.mc.displayGuiScreen(new GuiWorldSelection(this));
        }

        if (button.id == 2) {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }
/*
        if (button.id == 14 && this.realmsButton.visible) {
            RealmsBridge realmsbridge = new RealmsBridge();
            realmsbridge.switchToRealms(this);
        }
*/
        if (button.id == 4) {
            this.mc.shutdown();
        }

        if (button.id == 6) {
            this.mc.displayGuiScreen(new GuiModList(this));
        }

        if (button.id == 11) {
            this.mc.launchIntegratedServer("Demo_World", "Demo_World", WorldServerDemo.DEMO_WORLD_SETTINGS);
        }

        if (button.id == 12) {
            ISaveFormat isaveformat = this.mc.getSaveLoader();
            WorldInfo worldinfo = isaveformat.getWorldInfo("Demo_World");

            if (worldinfo != null) {
                this.mc.displayGuiScreen(new GuiYesNo(this, I18n.format("selectWorld.deleteQuestion"),
                        "\'" + worldinfo.getWorldName() + "\' "
                                + I18n.format("selectWorld.deleteWarning"),
                        I18n.format("selectWorld.deleteButton"),
                        I18n.format("gui.cancel"), 12));
            }
        }
    }

    @Override
    public void updateScreen() {
        this.ticks++;
        this.background.updateScreen();
/*
        if (this.areRealmsNotificationsEnabled()) {
            this.realmsNotification.updateScreen();
        }
*/
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.background.drawScreen(mouseX, mouseY, partialTicks);

        GlStateManager.enableCull();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

        //this.mc.getTextureManager().bindTexture(GuiMainMenu.);

        this.drawTexturedModalRect(0,0,256,256,256,256);
        this.drawTexturedModalRect(0, 0, 239, 0, 17, 16);

        ForgeHooksClient.renderMainMenu(this, this.fontRenderer, this.width, this.height, "");

        List<String> brandings = Lists.reverse(FMLCommonHandler.instance().getBrandings(true));
        for (int brdline = 0; brdline < brandings.size(); brdline++) {
            String brd = brandings.get(brdline);
            if (!Strings.isNullOrEmpty(brd)) {
                this.drawString(this.fontRenderer, brd, 2, this.height - (10 + brdline * (this.fontRenderer.FONT_HEIGHT + 1)), 16777215);
            }
        }

        this.drawString(this.fontRenderer, "Copyright Mojang AB.", this.widthCopyrightRest, this.height - 10, -1);

        if (mouseX > this.widthCopyrightRest && mouseX < this.widthCopyrightRest + this.widthCopyright && mouseY > this.height - 10 && mouseY < this.height && Mouse.isInsideWindow()) {
            drawRect(this.widthCopyrightRest, this.height - 1, this.widthCopyrightRest + this.widthCopyright, this.height, -1);
        }

        if (this.openGLWarning1 != null && !this.openGLWarning1.isEmpty()) {
            drawRect(this.openGLWarningX1 - 2, this.openGLWarningY1 - 2, this.openGLWarningX2 + 2, this.openGLWarningY2 - 1, 1428160512);
            this.drawString(this.fontRenderer, this.openGLWarning1, this.openGLWarningX1, this.openGLWarningY1, -1);
            this.drawString(this.fontRenderer, this.openGLWarning2, (this.width - this.openGLWarning2Width) / 2, (this.buttonList.get(0)).y - 12, -1);
        }

        for (GuiButton button : this.buttonList) {
            button.drawButton(this.mc, mouseX, mouseY, partialTicks);
        }
/*
        if (this.areRealmsNotificationsEnabled()) {
            this.realmsNotification.drawScreen(mouseX, mouseY, partialTicks);
        }
*/
        this.modUpdateNotification.drawScreen(mouseX, mouseY, partialTicks);
    }
}
