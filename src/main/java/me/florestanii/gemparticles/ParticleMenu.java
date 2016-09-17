package me.florestanii.gemparticles;

import me.florestanii.gemparticles.effects.FlameRingEffect;
import me.florestanii.gemparticles.effects.FrostLordEffect;
import me.florestanii.gemparticles.effects.RainCloudEffect;
import me.mickyjou.plugins.gems.gemextras.shop.Product;
import me.mickyjou.plugins.gems.gemextras.shop.ProductGroup;
import me.mickyjou.plugins.gems.gemextras.shop.SimpleProduct;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class ParticleMenu extends ProductGroup{

	
	public static final FlameRingEffect flameEffect = new FlameRingEffect();
	public static final FrostLordEffect frostLordEffect = new FrostLordEffect();
	public static final RainCloudEffect rainCloudEffect = new RainCloudEffect();
	
	public ParticleMenu() {
		super("§6§l★ §4§lParticle Menu §6§l ★", new ItemStack(Material.BLAZE_ROD));
		
		addItem(new SimpleProduct(Material.BLAZE_POWDER, flameEffect.getTitle(), 5) {
			
			@Override
			public void onBought(Player player) {
				flameEffect.buyEffect(player);
			}
		});
		
		addItem(new SimpleProduct(Material.SNOW_BALL, frostLordEffect.getTitle(), 5) {
			
			@Override
			public void onBought(Player player) {
				frostLordEffect.buyEffect(player);
			}
		});

		addItem(new Product() {
			
			@Override
			public String getDisplayName() {
				return rainCloudEffect.getTitle();
			}
			
			@Override
			public ItemStack createDisplayItem() {
				return new ItemStack(Material.INK_SACK, 1, (byte)4 );
			}
			
			@Override
			public void onBought(Player player) {
				rainCloudEffect.buyEffect(player);
			}
			
			@Override
			public int getCost() {
				return 5;
			}
		});
	}

}
