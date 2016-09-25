package me.florestanii.gemparticles;

import me.florestanii.gemparticles.effects.FlameRingEffect;
import me.florestanii.gemparticles.effects.FrostLordEffect;
import me.florestanii.gemparticles.effects.RainCloudEffect;
import me.mickyjou.plugins.gems.gemextras.abilitymanager.AbilityManager;
import me.mickyjou.plugins.gems.gemextras.shop.GemShop;
import org.bukkit.plugin.java.JavaPlugin;

public class GemParticleEffects extends JavaPlugin {
    private static GemParticleEffects instance;

    @Override
    public void onLoad() {
        super.onLoad();
        instance = this;
    }

    @Override
    public void onEnable() {
        AbilityManager.getInstance().ifPresent(abilityManager -> {
            abilityManager.registerAbility(new RainCloudEffect());
            abilityManager.registerAbility(new FlameRingEffect());
            abilityManager.registerAbility(new FrostLordEffect());
        });

        GemShop.getInstance().ifPresent(gemShop -> {
            gemShop.addItem(new ParticleMenu());
        });

        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static GemParticleEffects getPlugin() {
        return instance;
    }
}
