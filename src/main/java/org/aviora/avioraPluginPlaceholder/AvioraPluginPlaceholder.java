package org.aviora.avioraPluginPlaceholder;

import org.aviora.avioraPluginCore.AvioraPluginCore;
import org.aviora.avioraPluginCore.utils.AvioraUtils;
import org.aviora.avioraPluginPlaceholder.placeholder.PlaceholderRegistry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AvioraPluginPlaceholder extends JavaPlugin {

	private AvioraPluginCore _core;

	public AvioraPluginCore getCore() {
		return _core;
	}

	@Override
	public void onEnable() {
		// Plugin startup logic

		_core = (AvioraPluginCore) getServer().getPluginManager().getPlugin("AvioraPluginCore");

		if (_core == null) {
			AvioraUtils.log("<red>AvioraPluginCore not found! <gray>Plugin will be <red>disabled<gray>.");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}

		PlaceholderRegistry.registerDefaultPlaceholders();

		AvioraUtils.log(AvioraUtils.getBanner(this.getName()));
		AvioraUtils.log(AvioraUtils.getEnabled(this.getName()));
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic

		AvioraUtils.log(AvioraUtils.getDisabled(this.getName()));
	}
}
