package me.florestanii.gemparticles;

import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import de.craften.plugins.mcguilib.ViewManager;
import de.craften.plugins.playerdatastore.api.PlayerDataStore;
import de.craften.plugins.playerdatastore.api.PlayerDataStoreService;

public class GemParticleEffects extends JavaPlugin{

	private static GemParticleEffects instance;
	private ViewManager viewManager;
	private PlayerDataStoreService storage;
	
	@Override
	public void onLoad() {
		
		instance = this;
		
		super.onLoad();
	}
	
	@Override
	public void onEnable() {

		getCommand("particles").setExecutor(new ParticlesCommand());
		
		this.viewManager = new ViewManager(this);
		
		this.storage = getServer().getServicesManager().getRegistration(PlayerDataStoreService.class).getProvider();
		
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
	
	public PlayerDataStoreService getStorage() {
		return storage;
	}
	
	public PlayerDataStore getPlayerStorage(OfflinePlayer player) {
		return storage.getStore(player);
	}
	
	public PlayerDataStore getPlayerStorage(UUID uuid) {
		return storage.getStore(uuid);
	}
}
