package vorpal6.voltaics.gui.basic;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vorpal6.voltaics.container.basic.ContainerBasic;

public class GuiBasic extends GuiContainer {

    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/demo_background.png");
    private IInventory objectInventory;

    public GuiBasic(InventoryPlayer playerInv, IInventory objectInventory, int numSlots) {
        super(new ContainerBasic(playerInv, objectInventory, numSlots));
        this.objectInventory = objectInventory;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String string = this.objectInventory.hasCustomName() ? this.objectInventory.getName() : I18n.format(this.objectInventory.getName(), new Object[0]);
        this.fontRendererObj.drawString(string, this.xSize / 2 - this.fontRendererObj.getStringWidth(string), 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 94, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}
