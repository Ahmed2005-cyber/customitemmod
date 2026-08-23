package com.example.customitemmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Haupt-Einstiegspunkt des Mods. Wird von Fabric Loader beim Start
 * aufgerufen (siehe "entrypoints" -> "main" in fabric.mod.json).
 */
public class CustomItemMod implements ModInitializer {

	// Die Mod-ID muss exakt mit der ID in fabric.mod.json übereinstimmen.
	public static final String MOD_ID = "customitemmod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("[{}] Initialisiere Custom Item Mod", MOD_ID);

		// Items registrieren (siehe ModItems.java)
		ModItems.registerModItems();

		// Eigenes Item in einen bestehenden Kreativ-Tab einhängen.
		// Alternative: einen komplett eigenen ItemGroup-Tab erstellen,
		// siehe Kommentar am Ende der Datei.
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.add(ModItems.RUBY_GEM);
		});

		LOGGER.info("[{}] Initialisierung abgeschlossen", MOD_ID);
	}
}

/*
 * Falls stattdessen ein EIGENER Kreativ-Tab gewünscht ist (statt Einordnung
 * in einen vorhandenen wie ItemGroups.INGREDIENTS), sieht die Registrierung
 * in 1.21.x so aus:
 *
 * public static final RegistryKey<ItemGroup> CUSTOM_GROUP_KEY = RegistryKey.of(
 *     RegistryKeys.ITEM_GROUP, Identifier.of(MOD_ID, "custom_group"));
 *
 * Registry.register(Registries.ITEM_GROUP, CUSTOM_GROUP_KEY, FabricItemGroup.builder()
 *     .icon(() -> new ItemStack(ModItems.RUBY_GEM))
 *     .displayName(Text.translatable("itemgroup.customitemmod.custom_group"))
 *     .entries((displayContext, entries) -> {
 *         entries.add(ModItems.RUBY_GEM);
 *     })
 *     .build());
 *
 * Dann zusätzlich "itemgroup.customitemmod.custom_group": "Custom Group"
 * in lang/en_us.json ergänzen.
 */
