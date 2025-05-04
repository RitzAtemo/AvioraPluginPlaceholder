package org.aviora.avioraPluginPlaceholder.placeholder;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlaceholderRegistry {

	public static final String DefaultNullValue = "null";

	private static final Map<String, PlaceholderProvider> placeholders = new HashMap<>();

	public static void register(String key, PlaceholderProvider provider) {
		placeholders.put(key.toLowerCase(), provider);
	}


	public static String replacePlaceholders(Player player, String text) {
		String result = text;

		for (Map.Entry<String, PlaceholderProvider> entry : placeholders.entrySet()) {
			String key = entry.getKey();
			String value;

			try {
				value = entry.getValue().getValue(player);
			} catch (Exception e) {
				value = "%error%";
			}

			result = result.replaceAll("%aviora_" + key + "%", value != null ? value : "");
		}

		return result;
	}

	public static void registerDefaultPlaceholders() {
		register("name", p -> p != null ? p.getName() : DefaultNullValue);
		register("uuid", p -> p != null ? p.getUniqueId().toString() : DefaultNullValue);
		register("world", p -> p != null ? p.getWorld().getName() : DefaultNullValue);
		register("health", p -> p != null ? String.valueOf((int) p.getHealth()) : DefaultNullValue);
		register("online", p -> String.valueOf(Bukkit.getOnlinePlayers().size()));
		register("max_online", p -> String.valueOf(Bukkit.getMaxPlayers()));
	}
}