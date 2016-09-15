package me.florestanii.gemparticles;

import me.florestanii.gemparticles.effects.FlameRingEffect;
import me.florestanii.gemparticles.effects.FrostLordEffect;
import me.florestanii.gemparticles.effects.ParticleEffect;
import me.florestanii.gemparticles.effects.RainCloudEffect;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.craften.plugins.mcguilib.Button;
import de.craften.plugins.mcguilib.ClickListener;
import de.craften.plugins.mcguilib.GuiElement;
import de.craften.plugins.mcguilib.SinglePageView;

public class ParticleMenu extends SinglePageView{

	private final Player player;
	
	public static final FlameRingEffect flameEffect = new FlameRingEffect();
	public static final FrostLordEffect frostLordEffect = new FrostLordEffect();
	public static final RainCloudEffect rainCloudEffect = new RainCloudEffect();
	
	public ParticleMenu(Player player) {
		super("§6§l★ §4§lParticle Menu §6§l ★", 54);
		this.player = player;
		
		insertElement(53, createGemIcon());
		insertElement(12, createFlameEffect());
		insertElement(14, createFrostLordEffect());
		insertElement(21, createRainCloudEffect());
	}
	
	public GuiElement createGemIcon() {
		return new Button(Material.EMERALD, ChatColor.GREEN + "You have " + ChatColor.GOLD + GemParticleEffects.getPlugin().getGemApi().getGems(player) + ChatColor.GREEN + " Gems.");
	}
	
	public GuiElement createFlameEffect() {
		
		if (flameEffect.hasEffect(player)) {
			Button b = new Button(Material.BLAZE_POWDER, flameEffect.getTitle());
			
			b.setOnClick(new ClickListener() {
				
				@Override
				public void clicked(InventoryClickEvent event) {
					
					Player player = (Player) event.getWhoClicked();
					
					if (ParticleEffect.getCurrentEffect(player) == null) {
						flameEffect.loopOnPlayer(player, -1);
					} else {
						flameEffect.stopEffect(player);
						ParticleEffect.setCurrentEffect(player, null);
					}
					
				}
			});
			
			return b;
		} else {
			
			Button b = new Button(Material.BLAZE_POWDER, "Click to buy " + flameEffect.getTitle() + " for " + flameEffect.getCost() + " Gems for one hour.");
			b.setIcon(Material.INK_SACK, (byte)8);
			
			b.setOnClick(new ClickListener() {
				
				@Override
				public void clicked(InventoryClickEvent event) {
					
					Player player = (Player) event.getWhoClicked();
					
					if (flameEffect.buyEffect(player)) {
						b.setIcon(Material.BLAZE_POWDER);
						b.setTitle(flameEffect.getTitle());
						flameEffect.loopOnPlayer(player, -1);
					}
					
				}
			});
			
			return b;
			
		}
		
	}

	public GuiElement createRainCloudEffect() {
		
		if (rainCloudEffect.hasEffect(player)) {
			Button b = new Button(Material.INK_SACK, rainCloudEffect.getTitle());
			b.setIcon(Material.INK_SACK, (byte)2);
			b.setOnClick(new ClickListener() {
				
				@Override
				public void clicked(InventoryClickEvent event) {
					
					Player player = (Player) event.getWhoClicked();
					
					if (ParticleEffect.getCurrentEffect(player) == null) {
						rainCloudEffect.loopOnPlayer(player, -1);
					} else {
						rainCloudEffect.stopEffect(player);
						ParticleEffect.setCurrentEffect(player, null);
					}
					
				}
			});
			
			return b;
		} else {
			
			Button b = new Button(Material.INK_SACK, "Click to buy " + rainCloudEffect.getTitle() + " for " + rainCloudEffect.getCost() + " Gems for one hour.");
			b.setIcon(Material.INK_SACK, (byte)2);
			b.setIcon(Material.INK_SACK, (byte)8);
			
			b.setOnClick(new ClickListener() {
				
				@Override
				public void clicked(InventoryClickEvent event) {
					
					Player player = (Player) event.getWhoClicked();
					
					if (rainCloudEffect.buyEffect(player)) {
						b.setIcon(Material.INK_SACK, (byte)2);
						b.setTitle(rainCloudEffect.getTitle());
						rainCloudEffect.loopOnPlayer(player, -1);
					}
					
				}
			});
			
			return b;
			
		}
		
	}
	
	public GuiElement createFrostLordEffect() {
		
		if (frostLordEffect.hasEffect(player)) {
			Button b = new Button(Material.SNOW_BALL, frostLordEffect.getTitle());
			
			b.setOnClick(new ClickListener() {
				
				@Override
				public void clicked(InventoryClickEvent event) {
					
					Player player = (Player) event.getWhoClicked();
					
					if (ParticleEffect.getCurrentEffect(player) == null) {
						frostLordEffect.loopOnPlayer(player, -1);
					} else {
						frostLordEffect.stopEffect(player);
						ParticleEffect.setCurrentEffect(player, null);
					}
					
				}
			});
			
			return b;
		} else {
			
			Button b = new Button(Material.SNOW_BALL, "Click to buy " + frostLordEffect.getTitle() + " for " + frostLordEffect.getCost() + " Gems for one hour.");
			b.setIcon(Material.INK_SACK, (byte)8);
			
			b.setOnClick(new ClickListener() {
				
				@Override
				public void clicked(InventoryClickEvent event) {
					
					Player player = (Player) event.getWhoClicked();
					
					if (frostLordEffect.buyEffect(player)) {
						b.setIcon(Material.SNOW_BALL);
						b.setTitle(frostLordEffect.getTitle());
						frostLordEffect.loopOnPlayer(player, -1);
					}
					
				}
			});
			
			return b;
			
		}
		
	}

	
	public Player getPlayer() {
		return player;
	}
}
