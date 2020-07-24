package com.dmtrllv.moddy.blocks;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import com.dmtrllv.moddy.init.ModItemGroups;

import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public final class Blocks {

	private static class BlockHolder<T> {
		public final T block;
		public final String ID;
		public final boolean useAsItem;

		public BlockHolder(T block, String ID, boolean useAsItem) {
			this.block = block;
			this.ID = ID;
			this.useAsItem = useAsItem;
		}
	}

	public static final ArrayList<BlockHolder<?>> blocks = new ArrayList<>();

	public static void initBlockItems(IForgeRegistry<Item> registry) {
		blocks.forEach((block) -> {
			if (block.useAsItem) {
				Block b = (Block) block.block;
				final Item item = new BlockItem(b, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
				item.setRegistryName(block.ID);
				registry.register(item);
			}
		});
	}

	public static void initBlocks(IForgeRegistry<Block> registry) {
		blocks.forEach((block) -> {
			registry.register((Block)block.block);
		});
	}

	public static <T> T register(T block, String ID) {
		return register(block, ID, false);
	}

	public static <T> T register(T block, String ID, boolean useAsItem) {
		blocks.add(new BlockHolder<T>(block, ID, useAsItem));
		((Block)block).setRegistryName(ID);
		return block;
	}

	// public static <T> T register(Callable<T> registerBlock, String ID) throws Exception {
	// 	return register(registerBlock.call(), ID, false);
	// }

	// public static <T> T register(Callable<T> registerBlock, String ID, boolean useAsItem) throws Exception {
	// 	T block = registerBlock.call();
	// 	blocks.add(new BlockHolder<T>(block, ID, useAsItem));
	// 	((Block)block).setRegistryName(ID);
	// 	return block;
	// }

	public static final Block WHITE_OAK_PLANK = register(new Block(Properties.create(Material.WOOD)), "white_oak_plank", true);
	
	public static final LogBlock STRIPPED_WHITE_OAK_LOG = register(new LogBlock(MaterialColor.WOOD, Properties.create(Material.WOOD)), "stripped_white_oak_log", true);
	public static final LogBlock STRIPPED_WHITE_OAK_WOOD = register(new LogBlock(MaterialColor.WOOD, Properties.create(Material.WOOD)), "stripped_white_oak_wood", true);
	
	public static final TransformLogBlock WHITE_OAK_LOG = register(new TransformLogBlock(Blocks.STRIPPED_WHITE_OAK_LOG), "white_oak_log", true);
	public static final TransformLogBlock WHITE_OAK_WOOD = register(new TransformLogBlock(Blocks.STRIPPED_WHITE_OAK_WOOD), "white_oak_wood", true);
	
}

interface IRegisterBlock<T>
{
	T register();
}
