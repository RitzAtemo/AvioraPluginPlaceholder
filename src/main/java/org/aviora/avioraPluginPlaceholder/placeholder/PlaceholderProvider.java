package org.aviora.avioraPluginPlaceholder.placeholder;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface PlaceholderProvider {
	String getValue(Player player);
}