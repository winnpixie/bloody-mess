package io.github.winnpixie.bloodymess;

import io.github.winnpixie.bloodymess.commands.BloodyMessCommand;
import io.github.winnpixie.bloodymess.listeners.EntityDamageListener;
import io.github.winnpixie.hukkit.Hukkit;
import io.github.winnpixie.hukkit.configs.AnnotatedConfigurationManager;
import io.github.winnpixie.hukkit.configs.adapters.BukkitAdapter;
import org.bukkit.plugin.java.JavaPlugin;

public class BloodyMess extends JavaPlugin {
    public final AnnotatedConfigurationManager configManager = new AnnotatedConfigurationManager();

    @Override
    public void onEnable() {
        super.saveDefaultConfig();

        configManager.setAdapter(new BukkitAdapter(super.getConfig())).linkClass(Config.class).load();

        Hukkit.addListener(new EntityDamageListener(this));

        Hukkit.addCommand(new BloodyMessCommand(this));
    }
}
