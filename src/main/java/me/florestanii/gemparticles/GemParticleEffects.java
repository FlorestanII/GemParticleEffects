package me.florestanii.gemparticles;

import me.florestanii.gemparticles.effects.FlameRingEffect;
import me.florestanii.gemparticles.effects.FrostLordEffect;
import me.florestanii.gemparticles.effects.RainCloudEffect;
import me.mickyjou.plugins.gems.api.GemProvider;
import me.mickyjou.plugins.gems.gemextras.GemExtras;
import me.mickyjou.plugins.gems.gemextras.abilitymanager.AbilityManager;

import org.bukkit.plugin.java.JavaPlugin;

import de.craften.plugins.mcguilib.ViewManager;

public class GemParticleEffects extends JavaPlugin{

	private static GemParticleEffects instance;
	private ViewManager viewManager;
	
	private AbilityManager abilityManager;
	
	@Override
	public void onLoad() {
		
		instance = this;
		
		super.onLoad();
	}
	
	@Override
	public void onEnable() {
		
		this.viewManager = new ViewManager(this);
		
		this.abilityManager = getPlugin(GemExtras.class).getAbilityManager();
		
		this.abilityManager.registerAbility(new RainCloudEffect());
		this.abilityManager.registerAbility(new FlameRingEffect());
		this.abilityManager.registerAbility(new FrostLordEffect());
		
		getPlugin(GemExtras.class).getGemShop().addItem(new ParticleMenu());
		
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
	public static GemParticleEffects getPlugin() {
		return instance;
	}
	
	public ViewManager getViewManager() {
		return viewManager;
	}
	
	public GemProvider getGemApi() {
		return getServer().getServicesManager().getRegistration(GemProvider.class).getProvider();
	}

	public AbilityManager getAbilityManager() {
		return abilityManager;
	}
	
}
