package me.florestanii.gemparticles;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ParticlesCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.DARK_RED + "This command is only for players!");
			return true;
		}
		
		Player player = (Player) sender;
		
		GemParticleEffects.getPlugin().getViewManager().showView(player, new ParticleMenu(player));
		
		return false;
	}

}
