package net.milkbowl.vault.protection.plugins;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;

import de.bananaco.permissions.Permissions;

import net.milkbowl.vault.protection.Protection;

public class Protection_WorldGuard extends Protection {
	private final String name = "WorldGuard";
	private WorldGuardPlugin wg = null;
	
	public Protection_WorldGuard(Plugin plugin)
	{
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(new ProtectionServerListener(), plugin);
		if (wg == null) {
            Plugin p = plugin.getServer().getPluginManager().getPlugin("WorldGuard");
            if (p != null) {
            	wg = (p instanceof WorldGuardPlugin)? (WorldGuardPlugin) p: null;
                if (wg != null)
                	log.info(String.format("[%s][Protection] %s hooked.", plugin.getDescription().getName(), name));
            }
        }
	}
	
	public class ProtectionServerListener implements Listener {
		@EventHandler(priority = EventPriority.MONITOR)
        public void onPluginEnable(PluginEnableEvent event) {
            if (wg == null) {
                Plugin p = event.getPlugin();
                if(p.getDescription().getName().equals("WorldGuard") && p.isEnabled()) {
                    wg = (p instanceof WorldGuardPlugin)? (WorldGuardPlugin) p: null;
                    if (wg != null)
                    	log.info(String.format("[%s][Protection] %s hooked.", plugin.getDescription().getName(), name));
                }
            }
        }

        @EventHandler(priority = EventPriority.MONITOR)
        public void onPluginDisable(PluginDisableEvent event) {
            if (wg != null) {
                if (event.getPlugin().getDescription().getName().equals("WorldGuard")) {
                    wg = null;
                    log.info(String.format("[%s][Protection] %s un-hooked.", plugin.getDescription().getName(), name));
                }
            }
        }
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isEnabled() {
		if (wg == null)
			return false;
		else
			return wg.isEnabled();
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
		return WGBukkit.getRegionManager(loc.getWorld()).getApplicableRegions(loc).canBuild(WGBukkit.getPlugin().wrapPlayer(player));
	}

	@Override
	public boolean canPlace(Player player, Location loc, Material material) {
		return canBuild(player, loc);
	}

	@Override
	public boolean canBreak(Player player, Block block) {
		return canBuild(player, block.getLocation());
	}

	@Override
	public boolean canUse(Player player, Location loc, ItemStack item) {
		return canUse(player, loc);
	}

	@Override
	public boolean canUse(Player player, Location loc) {
		return WGBukkit.getRegionManager(loc.getWorld()).getApplicableRegions(loc).allows(DefaultFlag.USE, WGBukkit.getPlugin().wrapPlayer(player));
	}

	@Override
	public boolean canPVP(Player player, Location loc) {
		return WGBukkit.getRegionManager(loc.getWorld()).getApplicableRegions(loc).allows(DefaultFlag.PVP, WGBukkit.getPlugin().wrapPlayer(player));
	}

	@Override
	public boolean canTeleport(Player player, Location loc) {
		return true;
	}

	@Override
	public boolean hasFlagSupport() {
		return true;
	}

	@Override
	public boolean hasOwnerSupport() {
		return true;
	}

	@Override
	public boolean hasMemberSupport() {
		return true;
	}
	//TODO: Implement flag support.
}
