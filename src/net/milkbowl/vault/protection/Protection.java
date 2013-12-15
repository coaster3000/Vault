package net.milkbowl.vault.protection;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public abstract class Protection {
	
	protected static final Logger log = Logger.getLogger("Minecraft");
	protected Plugin plugin;
	
	/**
     * Gets name of Protection method
     * @return Name of Protection Method
     */
	abstract public String getName();
	
	/**
     * Checks if protection method is enabled.
     * @return Success or Failure
     */
    abstract public boolean isEnabled();
    
    /**
     * Checks if the player can build at specified location
     * @param player the player to check for permission to build
     * @param loc Location to build
     * @return Allowed or Disallowed
     */
    abstract public boolean canBuild(Player player, Location loc);
    
    
    /**
     * Checks if the player can build at their location.
     * @param player the player to check for permission to build
     * @return Allowed or Disallowed
     */
    public boolean canBuild(Player player)
    {
    	return canBuild(player, player.getLocation());
    }
    
    /**
     * Checks if the player can place the specified material at specified location.
     * @param player the player to check for permission to place object.
     * @param loc the location of where the object is to be placed.
     * @param material material being placed
     * @return Allowed or Disallowed
     */
    abstract public boolean canPlace(Player player, Location loc, Material material);
    
    /**
     * Checks if the player can break the specified block.
     * @param player the player to check for permission to break the specified block.
     * @param block the block to break.
     * @return Allowed or Disallowed
     */
    abstract public boolean canBreak(Player player, Block block);

    /**
     * Checks if the player can use the specified Item at the specified Location
     * @param player the player to check for permission to use the specified item at specified location.
     * @param loc the location of where the item is being used.
     * @param item the itemstack to match if they are allowed to use.
     * @return Allowed or Disallowed
     */
    abstract public boolean canUse(Player player, Location loc, ItemStack item);
    
    /**
     * Checks if the player can use items at the specified location.
     * @param player the player to check for permission to use the items at specified location.
     * @param loc the location to check if player is allowed to use items
     * @return Allowed or Disallowed
     */
    abstract public boolean canUse(Player player, Location loc);

    /**
     * Checks if the player can engage in pvp at the specified location.
     * @param player the player to check if they can engage in pvp.
     * @param loc the location of where to check if player can engage in pvp.
     * @return Allowed or Disallowed
     */
    abstract public boolean canPVP(Player player, Location loc);
    
    /**
     * Checks if the player can engage in pvp at current location.
     * @param player the player to check if they can engage in pvp.
     * @return Allowed or Disallowed
     */
    public boolean canPVP(Player player) {
    	return canPVP(player, player.getLocation());
    }

    /**
     * Checks if the player can teleport into the specified location.
     * @param player the player to check if they are allowed to teleport to specified location.
     * @param loc the location to teleport to.
     * @return Allowed or Disallowed
     */
    abstract public boolean canTeleport(Player player, Location loc);

    /**
     * Tells whether or not the region system has a flag system in place. <br/><br/>
     * 
     * This will include custom permissions not relating to players such as fire spread and such.
     * 
     * @return Has Flag Support or Doesn't Have Flag Support
     */
    abstract public boolean hasFlagSupport();
    
    /**
     * Tells whether or not this Region system has ownership support.
     * @return If the region system has ownership support.
     */
    abstract public boolean hasOwnerSupport();
    
    /**
     * Tells whether or not this Region system has membership support.
     * @return If the region system has membership support.
     */
    abstract public boolean hasMemberSupport();

    //TODO: Add flag api.
    
}
