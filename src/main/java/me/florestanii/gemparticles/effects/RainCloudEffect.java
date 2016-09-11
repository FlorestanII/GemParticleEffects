package me.florestanii.gemparticles.effects;

import java.util.HashMap;
import java.util.Map;

import me.florestanii.gemparticles.GemParticleEffects;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class RainCloudEffect extends ParticleEffect{
	private final Map<Player, Integer> schedulers;
	
	public RainCloudEffect() {
		super("raincloud", ChatColor.AQUA + "Rain Cloud", 1000);
		
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

		playerEffects.put(player, this);
		
		BukkitRunnable br = new BukkitRunnable() {
						
			@Override
			public void run() {
				
				Location loc = player.getLocation();
				
				double x = 0;
				double y = 2;
				double z = 0;
				loc.add(x, y, z);
				
				loc.getWorld().spigot().playEffect(loc, Effect.SPLASH, 0, 0, 0, 0, 0, 0, 1, 100);
				loc.getWorld().spigot().playEffect(loc, Effect.PARTICLE_SMOKE, 0, 0, 0, 0, 0, 0, 1, 100);
				
				loc.subtract(x, y, z);
				
				for (double phi = 0; phi < 2*Math.PI; phi += Math.PI/8) {
					x = 0.3 * Math.cos(phi);
					y = 2;
					z = 0.3 * Math.sin(phi);
					loc.add(x, y - 0.15, z);
					
					loc.getWorld().spigot().playEffect(loc, Effect.SPLASH, 0, 0, 0, 0, 0, 0, 1, 100);
					
					loc.add(0, 0.3, 0);
					
					loc.getWorld().spigot().playEffect(loc, Effect.PARTICLE_SMOKE, 0, 0, 0, 0, 0, 0, 1, 100);

					loc.subtract(0, 0.15, 0);
					
					loc.subtract(x, y, z);

				}
				for (double phi = 0; phi < 2*Math.PI; phi += Math.PI/16) {
					x = 0.5 * Math.cos(phi);
					y = 2;
					z = 0.5 * Math.sin(phi);
					loc.add(x, y, z);
					
					loc.getWorld().spigot().playEffect(loc, Effect.PARTICLE_SMOKE, 0, 0, 0, 0, 0, 0, 1, 100);
					
					loc.subtract(x, y, z);

				}
				this.cancel();
			}
		};
		
		br.runTaskTimer(GemParticleEffects.getPlugin(), 0, 1);
		
		schedulers.put(player, br.getTaskId());
	}

	@Override
	public void loopOnPlayer(Player player, int periode) {
		
		playerEffects.put(player, this);
		
		BukkitRunnable br = new BukkitRunnable() {
						
			@Override
			public void run() {
				
				Location loc = player.getLocation();
				
				double x = 0;
				double y = 2;
				double z = 0;
				loc.add(x, y, z);
				
				loc.getWorld().spigot().playEffect(loc, Effect.SPLASH, 0, 0, 0, 0, 0, 0, 1, 100);
				loc.getWorld().spigot().playEffect(loc, Effect.PARTICLE_SMOKE, 0, 0, 0, 0, 0, 0, 1, 100);
				
				loc.subtract(x, y, z);
				
				for (double phi = 0; phi < 2*Math.PI; phi += Math.PI/8) {
					x = 0.3 * Math.cos(phi);
					y = 2;
					z = 0.3 * Math.sin(phi);
					loc.add(x, y - 0.15, z);
					
					loc.getWorld().spigot().playEffect(loc, Effect.SPLASH, 0, 0, 0, 0, 0, 0, 1, 100);
					
					loc.add(0, 0.3, 0);
					
					loc.getWorld().spigot().playEffect(loc, Effect.PARTICLE_SMOKE, 0, 0, 0, 0, 0, 0, 1, 100);

					loc.subtract(0, 0.15, 0);
					
					loc.subtract(x, y, z);

				}
				for (double phi = 0; phi < 2*Math.PI; phi += Math.PI/16) {
					x = 0.5 * Math.cos(phi);
					y = 2;
					z = 0.5 * Math.sin(phi);
					loc.add(x, y, z);
					
					loc.getWorld().spigot().playEffect(loc, Effect.PARTICLE_SMOKE, 0, 0, 0, 0, 0, 0, 1, 100);
					
					loc.subtract(x, y, z);

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
