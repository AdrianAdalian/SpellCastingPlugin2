package com.perceus.spellcasting2.holy_spells;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.FlightClockForSpellProlongedFlight;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellProlongedFlight extends BaseSpellCapsule
{

	public SpellProlongedFlight()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: Prolonged Flight§r§f§ko§r", "SpellProlongedFlight", 0, false, "§r§fElement: §r§f§o§lHoly§r§f.","§r§fAn incantation that allows caster' flight","§r§fso long as they have enough §r§9mana§r§f.","§r§fRight-Click to activate/deactivate the spell.","§r§fMana cost: 20 §r§9mana§r§f/second.");
	}
	
	Map<UUID, FlightClockForSpellProlongedFlight> clocks = new HashMap<>();
	
	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}			
		
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() == PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"You don't have enough mana.");
			return false;
		}
		
		if (clocks.containsKey(event.getPlayer().getUniqueId())) 
		{
			if (!clocks.get(event.getPlayer().getUniqueId()).isEnabled()) 
			{
				clocks.remove(event.getPlayer().getUniqueId());
				clocks.put(event.getPlayer().getUniqueId(), new FlightClockForSpellProlongedFlight(event.getPlayer()));
				clocks.get(event.getPlayer().getUniqueId()).start();
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
				return true;
			}
			
			clocks.get(event.getPlayer().getUniqueId()).stop();
			clocks.remove(event.getPlayer().getUniqueId());
			event.getPlayer().setFlying(false);
			event.getPlayer().setAllowFlight(false);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);

		}
		else 
		{
			clocks.put(event.getPlayer().getUniqueId(), new FlightClockForSpellProlongedFlight(event.getPlayer()));
			clocks.get(event.getPlayer().getUniqueId()).start();
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
			return true;
		}
		return false;
	}
}
