package com.dmtrllv.moddy;

import com.dmtrllv.moddy.blocks.Blocks;
import com.dmtrllv.moddy.init.ModItemGroups;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Moddy.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class ModEventSubscriber {
	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		final Item pebble = new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
		pebble.setRegistryName("pebble");
		event.getRegistry().register(pebble);
		Blocks.initBlockItems(event.getRegistry());
	}

	@SubscribeEvent
	public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		Blocks.initBlocks(event.getRegistry());
	}
}
