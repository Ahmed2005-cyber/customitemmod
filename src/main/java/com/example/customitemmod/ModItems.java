package com.example.customitemmod;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item RUBY_GEM = registerItem("ruby_gem", new Item.Settings());

    private static Item registerItem(String path, Item.Settings settings) {
        return Registry.register(Registries.ITEM, Identifier.of(CustomItemMod.MOD_ID, path), new Item(settings));
    }

    public static void registerModItems() {
        CustomItemMod.LOGGER.info("Registering mod items for " + CustomItemMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(RUBY_GEM);
        });
    }
}