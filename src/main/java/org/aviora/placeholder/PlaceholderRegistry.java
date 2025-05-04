package org.aviora.placeholder;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Реестр шаблонизаторов
 * @since 1.0
 */
public class PlaceholderRegistry {
	/**
	 * Стандартное значение
	 * @since 1.0
	 */
	public static final String NULL_VALUE = "null";

	/**
	 * Реестр
	 * @since 1.0
	 */
	private static final Map<String, PlaceholderProvider> _placeholders = new HashMap<>();

	/**
	 * Зарегистрировать новый шаблонизатор
	 * @param key Ключ (будет использоваться как {@code %aviora_key%})
	 * @param provider Провайдер, предоставляющий значение для замены
	 * @since 1.0
	 */
	public static void register(String key, PlaceholderProvider provider) {
		_placeholders.put(key.toLowerCase(), provider);
	}

	/**
	 * Заменить значение
	 * @param player Игрок, у которого будут браться значения, или {@code NULL} если значение не зависит от игрока
	 * @param raw Исходный текст
	 * @since 1.0
	 */
	public static String replacePlaceholders(Player player, String raw) {
		String result = raw;

		for (Map.Entry<String, PlaceholderProvider> entry : _placeholders.entrySet()) {
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

	/**
	 * Стандартные шаблонизаторы
	 * @since 1.0
	 */
	public static void registerDefaultPlaceholders() {
		register("name", p -> p != null ? p.getName() : NULL_VALUE);
		register("uuid", p -> p != null ? p.getUniqueId().toString() : NULL_VALUE);
		register("world", p -> p != null ? p.getWorld().getName() : NULL_VALUE);
		register("health", p -> p != null ? String.valueOf((int) p.getHealth()) : NULL_VALUE);
		register("online", p -> String.valueOf(Bukkit.getOnlinePlayers().size()));
		register("max_online", p -> String.valueOf(Bukkit.getMaxPlayers()));
	}
}