package com.dmtrllv.moddy.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class TransformLogBlock extends LogBlock {
	public final LogBlock transformBlock;

	public TransformLogBlock(LogBlock transformBlock) {
		super(MaterialColor.WOOD, Properties.create(Material.WOOD));
		this.transformBlock = transformBlock;
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) { 
			if(player.getHeldItem(hand).getItem() instanceof AxeItem)
			{
				BlockState bs = world.getBlockState(pos);
				world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if (!world.isRemote)
                    world.setBlockState(pos, transformBlock.getDefaultState().with(RotatedPillarBlock.AXIS, bs.get(RotatedPillarBlock.AXIS)), 11);
				return ActionResultType.SUCCESS;
			}

		return ActionResultType.PASS;
	}
}
