package me.florestanii.gemparticles.effects;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.florestanii.gemparticles.GemParticleEffects;
import net.md_5.bungee.api.ChatColor;

public class FlameRingEffects extends ParticleEffect{

	private final Map<Player, Integer> schedulers;
	
	public FlameRingEffects() {
		super("flamerings", ChatColor.GOLD + "Flame Rings", 1000);
		
		this.schedulers = new HashMap<Player, Integer>();
		
		GemParticleEffects.getPlugin().getServer().getPluginManager().registerEvents(new Listener() {
			
			@EventHandler
			public void onPlayerQuit(PlayerQuitEvent event) {
				
				stopEffect(event.getPlayer());
				
			}
			
		}, GemParticleEffects.getPlugin());
		
	}

	@Override
	public void playOnPlayer(Player player) {
		
		BukkitRunnable br = new BukkitRunnable() {
			
			double t = 0;
			
			@Override
			public void run() {
				
				t += Math.PI/16;
				
				Location loc = player.getLocation();
				
				double x = 2*Math.cos(t);
				double y = 1.62 + 0.3*Math.sin(t);
				double z = 2*Math.cos(t);
				
				loc.add(x, y, z);
				
				loc.getWorld().spigot().playEffect(loc, Effect.FLAME, 0, 0, 0, 0, 0, 0, 1, 100);
				
				loc.subtract(x, y, z);
				
				if (t == 2*Math.PI) {
					this.cancel();
				}
				
			}
		};
		
		br.runTaskTimer(GemParticleEffects.getPlugin(), 0, 1);
		
		schedulers.put(player, br.getTaskId());
		
	}

	@Override
	public void loopOnPlayer(Player player, int periode) {

		BukkitRunnable br = new BukkitRunnable() {
			
			double t = 0;
			
			@Override
			public void run() {
				
				t += Math.PI/16;
				
				Location loc = player.getLocation();
				
				double x = 2*Math.cos(t);
				double y = 1.62 + 0.5*Math.sin(t);
				double z = 2*Math.cos(t);
				
				loc.add(x, y, z);
				
				loc.getWorld().spigot().playEffect(loc, Effect.FLAME, 0, 0, 0, 0, 0, 0, 1, 100);

				loc.subtract(x, y, z);
				
				if (t == 2*Math.PI) {
					t = 0;
				}
				
			}
		};
		
		br.runTaskTimer(GemParticleEffects.getPlugin(), 0, 1);
		
		schedulers.put(player, br.getTaskId());
		
	}

	@Override
	public void stopEffect(Player player) {
		Integer scheduler = schedulers.remove(player);
		
		if (scheduler != null) {
			
			GemParticleEffects.getPlugin().getServer().getScheduler().cancelTask(scheduler);
			
		}
	}
	
}
