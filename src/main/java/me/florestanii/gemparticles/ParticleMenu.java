package me.florestanii.gemparticles;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import de.craften.plugins.mcguilib.Button;
import de.craften.plugins.mcguilib.GuiElement;
import de.craften.plugins.mcguilib.SinglePageView;

public class ParticleMenu extends SinglePageView{

	private final Player player;
	
	public ParticleMenu(Player player) {
		super("§6§l★ §4§lParticle Menu §6§l ★", 54);
		this.player = player;
		
		insertElement(53, createGemIcon());
	}
	
	public GuiElement createGemIcon() {
		return new Button(Material.EMERALD, ChatColor.GREEN + "You have" + ChatColor.GOLD + GemParticleEffects.getPlugin().getGemApi().getGems(player) + ChatColor.GREEN + " Gems.");
	}
	
	public Player getPlayer() {
		return player;
	}
}
