package uk.co.jacekk.bukkit.bloodmoon.featurelisteners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityExplodeEvent;

import uk.co.jacekk.bukkit.baseplugin.v1.event.BaseListener;
import uk.co.jacekk.bukkit.bloodmoon.BloodMoon;
import uk.co.jacekk.bukkit.bloodmoon.Config;

public class SuperCreepersListener extends BaseListener<BloodMoon> {
	
	public SuperCreepersListener(BloodMoon plugin){
		super(plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onEntityExplode(EntityExplodeEvent event){
		EntityType type = event.getEntityType();
		
		if (type != null && type == EntityType.CREEPER){
			Location location = event.getLocation();
			World world = location.getWorld();
			
			if (plugin.isActive(world.getName())){
				event.setCancelled(true);
				
				world.createExplosion(location, (float) plugin.config.getDouble(Config.FEATURE_SUPER_CREEPERS_POWER), true);
			}
		}
	}
	
}
