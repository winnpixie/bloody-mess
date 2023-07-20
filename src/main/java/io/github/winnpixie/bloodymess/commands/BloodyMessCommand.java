package io.github.winnpixie.bloodymess.commands;

import io.github.winnpixie.bloodymess.BloodyMess;
import io.github.winnpixie.hukkit.commands.BaseCommand;
import io.github.winnpixie.hukkit.commands.CommandErrors;
import io.github.winnpixie.hukkit.configs.adapters.BukkitAdapter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class BloodyMessCommand extends BaseCommand<BloodyMess> {
    private final BaseComponent[] reloadedMessage = new ComponentBuilder("The configuration has been reloaded.")
            .color(ChatColor.GREEN)
            .create();

    public BloodyMessCommand(BloodyMess plugin) {
        super(plugin, "bloody-mess");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("bloody-mess.command") && !sender.isOp()) {
            sender.spigot().sendMessage(CommandErrors.LACKS_PERMISSIONS);
            return true;
        }

        if (args.length < 1) {
            sender.spigot().sendMessage(CommandErrors.MISSING_ARGUMENTS);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
            case "rl":
                reloadConfiguration(sender);
            default:
                sender.spigot().sendMessage(CommandErrors.INVALID_ARGUMENTS);
        }

        return true;
    }

    private void reloadConfiguration(CommandSender sender) {
        if (!sender.hasPermission("bloody-mess.command.reload") && !sender.isOp()) {
            sender.spigot().sendMessage(CommandErrors.LACKS_PERMISSIONS);
            return;
        }

        super.getPlugin().reloadConfig();

        BukkitAdapter adapter = (BukkitAdapter) getPlugin().configManager.getAdapter();
        adapter.setConfig(getPlugin().getConfig());
        getPlugin().configManager.load();

        sender.spigot().sendMessage(reloadedMessage);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return Collections.singletonList("reload");
    }
}
