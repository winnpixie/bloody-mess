package io.github.winnpixie.bloodymess;

import io.github.winnpixie.bloodymess.commands.admin.BloodyMessCommand;
import io.github.winnpixie.bloodymess.listeners.EntityDamageListener;
import io.github.winnpixie.commons.spigot.SpigotHelper;
import io.github.winnpixie.commons.spigot.configs.AnnotationConfiguration;
import io.github.winnpixie.commons.spigot.configs.adapters.BukkitAdapter;
import org.bukkit.plugin.java.JavaPlugin;

public class BloodyMess extends JavaPlugin {
    public final AnnotationConfiguration configuration = new AnnotationConfiguration();

    @Override
    public void onEnable() {
        super.saveDefaultConfig();

        configuration.setAdapter(new BukkitAdapter(super.getConfig())).linkClass(Config.class).load();

        SpigotHelper.addListener(new EntityDamageListener(this));

        SpigotHelper.addCommand(new BloodyMessCommand(this));
    }
}
