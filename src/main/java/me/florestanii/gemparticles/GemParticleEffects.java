package me.florestanii.gemparticles;

import org.bukkit.plugin.java.JavaPlugin;

import de.craften.plugins.mcguilib.ViewManager;

public class GemParticleEffects extends JavaPlugin{

	private static GemParticleEffects instance;
	private ViewManager viewManager;
	
	@Override
	public void onLoad() {
		
		instance = this;
		
		super.onLoad();
	}
	
	@Override
	public void onEnable() {
		
		getCommand("particles").setExecutor(new ParticlesCommand());
		
		this.viewManager = new ViewManager(this);
		
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
	
}
