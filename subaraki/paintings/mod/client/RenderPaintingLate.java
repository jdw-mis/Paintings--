package subaraki.paintings.mod.client;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import subaraki.paintings.config.ConfigurationHandler;

@SideOnly(Side.CLIENT)
public class RenderPaintingLate extends Render implements IRenderFactory
{
	private ResourceLocation art = null;

	public RenderPaintingLate(RenderManager renderManager) {
		super(renderManager);
		art = new ResourceLocation("subaraki:art/"+ConfigurationHandler.instance.texture+".png");		
	}

	public static float getSize()
	{
		switch (ConfigurationHandler.instance.texture) {
		case "insane":
		case "tinypics":
		case "new_insane":
		case "mediumpics":
			return 512.0F;
		case "massive":
			return 1008.0F;
		default:
			return 256.0F;
		}
	}

	public void doRender(EntityPainting entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
		GlStateManager.enableRescaleNormal();
		this.bindEntityTexture(entity);
		EntityPainting.EnumArt enumart = entity.art;
		float f = 0.0625F;
		GlStateManager.scale(0.0625F, 0.0625F, 0.0625F);

		if (this.renderOutlines)
		{
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(this.getTeamColor(entity));
		}

		this.renderPainting(entity, enumart.sizeX, enumart.sizeY, enumart.offsetX, enumart.offsetY);

		if (this.renderOutlines)
		{
			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
		}

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	private void renderPainting(EntityPainting painting, int width, int height, int textureU, int textureV)
	{
		float f = (float)(-width) / 2.0F;
		float f1 = (float)(-height) / 2.0F;
		float f2 = 0.5F;
		float f3 = 0.75F;
		float f4 = 0.8125F;
		float f5 = 0.0F;
		float f6 = 0.0625F;
		float f7 = 0.75F;
		float f8 = 0.8125F;
		float f9 = 0.001953125F;
		float f10 = 0.001953125F;
		float f11 = 0.7519531F;
		float f12 = 0.7519531F;
		float f13 = 0.0F;
		float f14 = 0.0625F;

		for (int i = 0; i < width / 16; ++i)
		{
			for (int j = 0; j < height / 16; ++j)
			{
				float f15 = f + (float)((i + 1) * 16);
				float f16 = f + (float)(i * 16);
				float f17 = f1 + (float)((j + 1) * 16);
				float f18 = f1 + (float)(j * 16);
				this.setLightmap(painting, (f15 + f16) / 2.0F, (f17 + f18) / 2.0F);
				float f19 = (float)(textureU + width - i * 16) / getSize();
				float f20 = (float)(textureU + width - (i + 1) * 16) / getSize();
				float f21 = (float)(textureV + height - j * 16) / getSize();
				float f22 = (float)(textureV + height - (j + 1) * 16) / getSize();
				Tessellator tessellator = Tessellator.getInstance();
				VertexBuffer vertexbuffer = tessellator.getBuffer();
				vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
				vertexbuffer.pos((double)f15, (double)f18, -0.5D).tex((double)f20, (double)f21).normal(0.0F, 0.0F, -1.0F).endVertex();
				vertexbuffer.pos((double)f16, (double)f18, -0.5D).tex((double)f19, (double)f21).normal(0.0F, 0.0F, -1.0F).endVertex();
				vertexbuffer.pos((double)f16, (double)f17, -0.5D).tex((double)f19, (double)f22).normal(0.0F, 0.0F, -1.0F).endVertex();
				vertexbuffer.pos((double)f15, (double)f17, -0.5D).tex((double)f20, (double)f22).normal(0.0F, 0.0F, -1.0F).endVertex();
				vertexbuffer.pos((double)f15, (double)f17, 0.5D).tex(0.75D, 0.0D).normal(0.0F, 0.0F, 1.0F).endVertex();
				vertexbuffer.pos((double)f16, (double)f17, 0.5D).tex(0.8125D, 0.0D).normal(0.0F, 0.0F, 1.0F).endVertex();
				vertexbuffer.pos((double)f16, (double)f18, 0.5D).tex(0.8125D, 0.0625D).normal(0.0F, 0.0F, 1.0F).endVertex();
				vertexbuffer.pos((double)f15, (double)f18, 0.5D).tex(0.75D, 0.0625D).normal(0.0F, 0.0F, 1.0F).endVertex();
				vertexbuffer.pos((double)f15, (double)f17, -0.5D).tex(0.75D, 0.001953125D).normal(0.0F, 1.0F, 0.0F).endVertex();
				vertexbuffer.pos((double)f16, (double)f17, -0.5D).tex(0.8125D, 0.001953125D).normal(0.0F, 1.0F, 0.0F).endVertex();
				vertexbuffer.pos((double)f16, (double)f17, 0.5D).tex(0.8125D, 0.001953125D).normal(0.0F, 1.0F, 0.0F).endVertex();
				vertexbuffer.pos((double)f15, (double)f17, 0.5D).tex(0.75D, 0.001953125D).normal(0.0F, 1.0F, 0.0F).endVertex();
				vertexbuffer.pos((double)f15, (double)f18, 0.5D).tex(0.75D, 0.001953125D).normal(0.0F, -1.0F, 0.0F).endVertex();
				vertexbuffer.pos((double)f16, (double)f18, 0.5D).tex(0.8125D, 0.001953125D).normal(0.0F, -1.0F, 0.0F).endVertex();
				vertexbuffer.pos((double)f16, (double)f18, -0.5D).tex(0.8125D, 0.001953125D).normal(0.0F, -1.0F, 0.0F).endVertex();
				vertexbuffer.pos((double)f15, (double)f18, -0.5D).tex(0.75D, 0.001953125D).normal(0.0F, -1.0F, 0.0F).endVertex();
				vertexbuffer.pos((double)f15, (double)f17, 0.5D).tex(0.751953125D, 0.0D).normal(-1.0F, 0.0F, 0.0F).endVertex();
				vertexbuffer.pos((double)f15, (double)f18, 0.5D).tex(0.751953125D, 0.0625D).normal(-1.0F, 0.0F, 0.0F).endVertex();
				vertexbuffer.pos((double)f15, (double)f18, -0.5D).tex(0.751953125D, 0.0625D).normal(-1.0F, 0.0F, 0.0F).endVertex();
				vertexbuffer.pos((double)f15, (double)f17, -0.5D).tex(0.751953125D, 0.0D).normal(-1.0F, 0.0F, 0.0F).endVertex();
				vertexbuffer.pos((double)f16, (double)f17, -0.5D).tex(0.751953125D, 0.0D).normal(1.0F, 0.0F, 0.0F).endVertex();
				vertexbuffer.pos((double)f16, (double)f18, -0.5D).tex(0.751953125D, 0.0625D).normal(1.0F, 0.0F, 0.0F).endVertex();
				vertexbuffer.pos((double)f16, (double)f18, 0.5D).tex(0.751953125D, 0.0625D).normal(1.0F, 0.0F, 0.0F).endVertex();
				vertexbuffer.pos((double)f16, (double)f17, 0.5D).tex(0.751953125D, 0.0D).normal(1.0F, 0.0F, 0.0F).endVertex();
				tessellator.draw();
			}
		}
	}

	private void setLightmap(EntityPainting painting, float p_77008_2_, float p_77008_3_)
	{
		int i = MathHelper.floor(painting.posX);
		int j = MathHelper.floor(painting.posY + (double)(p_77008_3_ / 16.0F));
		int k = MathHelper.floor(painting.posZ);
		EnumFacing enumfacing = painting.facingDirection;

		if (enumfacing == EnumFacing.NORTH)
		{
			i = MathHelper.floor(painting.posX + (double)(p_77008_2_ / 16.0F));
		}

		if (enumfacing == EnumFacing.WEST)
		{
			k = MathHelper.floor(painting.posZ - (double)(p_77008_2_ / 16.0F));
		}

		if (enumfacing == EnumFacing.SOUTH)
		{
			i = MathHelper.floor(painting.posX - (double)(p_77008_2_ / 16.0F));
		}

		if (enumfacing == EnumFacing.EAST)
		{
			k = MathHelper.floor(painting.posZ + (double)(p_77008_2_ / 16.0F));
		}

		int l = this.renderManager.world.getCombinedLight(new BlockPos(i, j, k), 0);
		int i1 = l % 65536;
		int j1 = l / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)i1, (float)j1);
		GlStateManager.color(1.0F, 1.0F, 1.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1EntityPainting)
	{
		return art;
	}

	@Override
	public Render createRenderFor(RenderManager manager) {
		return this;
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		doRender((EntityPainting)par1Entity, par2, par4, par6, par8, par9);
	}
}
