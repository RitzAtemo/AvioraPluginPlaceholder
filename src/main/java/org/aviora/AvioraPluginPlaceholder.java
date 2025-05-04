package org.aviora;

import org.aviora.utils.AvioraUtils;
import org.aviora.placeholder.PlaceholderRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public final class AvioraPluginPlaceholder extends JavaPlugin {
	@Override
	public void onEnable() {
		PlaceholderRegistry.registerDefaultPlaceholders();

		AvioraUtils.log(AvioraUtils.getBanner(this.getName()));
		AvioraUtils.log(AvioraUtils.getEnabled(this.getName()));
	}

	@Override
	public void onDisable() {
		AvioraUtils.log(AvioraUtils.getDisabled(this.getName()));
	}
}
