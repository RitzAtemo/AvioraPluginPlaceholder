package org.aviora.placeholder;

import org.bukkit.entity.Player;

/**
 * Интерфейс шаблонизатора
 * @since 1.0
 */
@FunctionalInterface
public interface PlaceholderProvider {
	String getValue(Player player);
}