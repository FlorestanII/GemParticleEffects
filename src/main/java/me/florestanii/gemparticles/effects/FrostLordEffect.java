package me.florestanii.gemparticles.effects;

import java.time.Duration;
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

public class FrostLordEffect extends ParticleEffect {
	
	private final Map<Player, Integer> schedulers;
	
	public FrostLordEffect() {
		super("frostlord", ChatColor.WHITE + "Frost Lord", 1000);
		
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
			
			double t = 0;
			
			@Override
			public void run() {
				
				t += Math.PI/16;
				
				Location loc = player.getLocation();
				
				for (double phi = 0; phi < 2*Math.PI; phi += Math.PI/2) {
					double x = 0.3*(4*Math.PI - t) * Math.cos(t + phi);
					double y = 0.2*t;
					double z = 0.3*(4*Math.PI - t) * Math.sin(t + phi);
					loc.add(x, y, z);
					
					loc.getWorld().spigot().playEffect(loc, Effect.SNOW_SHOVEL, 0, 0, 0, 0, 0, 0, 1, 100);
					
					loc.subtract(x, y, z);

					if (t >= 4*Math.PI) {
						loc.add(x, y, z);
						loc.getWorld().spigot().playEffect(loc, Effect.SNOW_SHOVEL, 0, 0, 0, 0, 0, 1, 50, 100);
						stopEffect(player);
					}
				}
				
			}
		};
		
		br.runTaskTimer(GemParticleEffects.getPlugin(), 0, 1);
		
		schedulers.put(player, br.getTaskId());
		
	}

	@Override
	public void loopOnPlayer(Player player, int periode) {
		
		playerEffects.put(player, this);
		
		BukkitRunnable br = new BukkitRunnable() {
			
			double t = 0;
			
			@Override
			public void run() {
				
				t += Math.PI/16;
				
				Location loc = player.getLocation();
				
				for (double phi = 0; phi < 2*Math.PI; phi += Math.PI/2) {
					double x = 0.3*(4*Math.PI - t) * Math.cos(t + phi);
					double y = 0.2*t + 0.05;
					double z = 0.3*(4*Math.PI - t) * Math.sin(t + phi);
					loc.add(x, y, z);
					
					loc.getWorld().spigot().playEffect(loc, Effect.SNOW_SHOVEL, 0, 0, 0, 0, 0, 0, 1, 100);
					
					loc.subtract(x, y, z);

					if (t >= 4*Math.PI) {
						loc.add(x, y, z);
						loc.getWorld().spigot().playEffect(loc, Effect.SNOW_SHOVEL, 0, 0, 0, 0, 0, 1, 50, 100);
						t = 0;
					}
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



	@Override
	public void giveToPlayer(Player player) {
		GemParticleEffects.getPlugin().getAbilityManager().giveAbilityTo(FrostLord.class, player, Duration.ofHours(1));		
	}



	@Override
	public boolean hasEffect(Player player) {
		return GemParticleEffects.getPlugin().getAbilityManager().hasAbility(FrostLord.class, player);
	}

}
