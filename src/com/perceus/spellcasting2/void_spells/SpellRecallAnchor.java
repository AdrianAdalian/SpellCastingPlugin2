package com.perceus.spellcasting2.void_spells;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellRecallAnchor extends BaseSpellCapsule
{

	public SpellRecallAnchor()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: Recall Anchor§r§f§ko§r", "SpellRecallAnchor", 0, false, "§r§fElement: §r§3§lVOID§r§f.","§r§fSpell Type: §bUtility§f.","§r§fA tome with an incantation that allows the caster","§r§fto set a teleport anchor.","§r§fThis anchor can only be accessed with this tome.","§r§fLeft-Click to set the anchor's location.","§r§fMana cost: 350 §r§9mana§r§f.","§r§fRight-Click to recall to that anchor.","§r§fMana cost: 100 §r§9mana§r§f.");
	}
	
	private static Map<UUID, Location> playerStoredLocations = new HashMap<>();
	
	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		if (event.getAction().equals(Action.LEFT_CLICK_BLOCK))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getAction().equals(Action.LEFT_CLICK_AIR))
		{
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 350);
			if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			} 
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_SHULKER_SHOOT, SoundCategory.MASTER, 1, 1);
			playerStoredLocations.put(event.getPlayer().getUniqueId(), event.getPlayer().getLocation());
			PrintUtils.sendMessage(event.getPlayer(), "Anchor Created.");
			return true;
			
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 100);
			if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			
			if (!playerStoredLocations.containsKey(event.getPlayer().getUniqueId())) 
			{
				PrintUtils.sendMessage(event.getPlayer(), "You have not yet created an Anchor.");
				return false;
			}
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_SHULKER_SHOOT, SoundCategory.MASTER, 1, 1);
			event.getPlayer().teleport(playerStoredLocations.get(event.getPlayer().getUniqueId()));
			new BukkitRunnable()
			{
				 @Override
				  public void run()
				  {
					 	SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
						event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 1);
				  }
				 
			}.runTaskLater(Eden.getInstance(), 10);
			return true;
		}
		PrintUtils.sendMessage(event.getPlayer(),"FIZZLE!");
		return false;
	}

}
