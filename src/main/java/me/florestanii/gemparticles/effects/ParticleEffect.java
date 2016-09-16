package me.florestanii.gemparticles.effects;

import java.util.HashMap;
import java.util.Map;

import me.florestanii.gemparticles.GemParticleEffects;
import me.mickyjou.plugins.gems.gemextras.abilitymanager.Ability;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Player;

public abstract class ParticleEffect implements Ability{

	protected static Map<Player, ParticleEffect> playerEffects = new HashMap<Player, ParticleEffect>();
	
	private final String name;
	
	private final String title;
		
	protected int cost;
	
	public ParticleEffect(String name, String title, int cost) {
		this.cost = cost;
		this.name = name;
		this.title = title;
	}
	
	public abstract void playOnPlayer(Player player);
	
	public abstract void loopOnPlayer(Player player, int periode);
	
	public abstract void stopEffect(Player player);
	
	public boolean buyEffect(Player player) {
		if (hasEffect(player)) {
			player.sendMessage(ChatColor.DARK_RED + "You have this effect already!");
			return false;
		}
		
		if (GemParticleEffects.getPlugin().getGemApi().removeGems(player, cost)) {
			giveToPlayer(player);
			player.sendMessage(ChatColor.GREEN + "You bought " + title + ChatColor.GREEN + " for " + ChatColor.GOLD + cost + ChatColor.GREEN + " Gems");
			return true;
		} else {
			player.sendMessage("You don't have enough Gems to buy this.");
			return false;
		}
		
	}
	
	public abstract void giveToPlayer(Player player);
	
	public abstract boolean hasEffect(Player player);
	
	public String getName() {
		return name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public static ParticleEffect getCurrentEffect(Player p) {
		return playerEffects.get(p);
	}

	public static void setCurrentEffect(Player p, ParticleEffect effect) {
		playerEffects.put(p, effect);
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}

	@Override
	public String getDisplayName() {
		return title;
	}
	
	@Override
	public String getIdentifier() {
		return name;
	}
}