package com.perceus.spellcasting2.unholy_spells;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellDrainingAura extends BaseSpellCapsule
{

	public SpellDrainingAura()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: Draining Aura§r§f§ko§r", "SpellDrainingAura", 0, false, "§r§fElement: §r§4§o§lUnholy§r§f.","§r§fSpell Type: §cOffensive§f §dAOE§f.",
				"§r§fA spelltome radiating §r§4§o§lUnholy§r§f energy.",
				"§r§fThis spelltome has two incantations each with",
				"§r§ftheir own varying effects.",
				"§f§fOn Left-Click:","§r§fDrain nearby non-undead target's lifeforce.",
				"§r§fAll within range take 4 hearts of §r§cdamage§r§f.","§r§4Drain§r§f 2 hearts for each target.","§r§fRange: 15 meters.","§r§fMana cost: 175 §r§9mana§r§f.",
				"§r§fOn Right-Click", "§r§fSap nearby player's ether at the cost of","§r§fhalf of the caster's current max health.", 
				"§r§4Drain§r§f 250 §r§9mana§r§f from each player.",
				"§r§fRestore 125 §r§9mana§r§f to self for each target player.",
				"§r§fRange: 15 meters.");
	}

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
		
		if (event.getPlayer().getNearbyEntities(15, 15, 15).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		List<EntityType> entity = List.of(EntityType.SKELETON,
				EntityType.ZOMBIE,
				EntityType.DROWNED,
				EntityType.STRAY,
				EntityType.HUSK,
				EntityType.ZOMBIE_VILLAGER,
				EntityType.ZOMBIFIED_PIGLIN,
				EntityType.WITHER_SKELETON,
				EntityType.VEX,
				EntityType.ZOMBIE_HORSE,
				EntityType.SKELETON_HORSE,
				EntityType.ZOGLIN,
				EntityType.WITHER,
				EntityType.WARDEN);
		
		if (event.getAction().equals(Action.LEFT_CLICK_AIR))
		{
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 175);
			if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_AMBIENT, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.SMOKE_LARGE, null);
			
			for (Entity target : event.getPlayer().getNearbyEntities(15, 15, 15))
			{
			  if (entity.contains(target.getType()))
			  {
			    continue;
			  }
			  if (!(target instanceof Damageable))
			  {
			    continue;
			  }

			  ((Damageable) target).damage(8, event.getPlayer());
			  SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.SMOKE_LARGE, null);

			  try 
			  {
				  event.getPlayer().setHealth(event.getPlayer().getHealth()+2);
			  }
			  catch(IllegalArgumentException e)
			  {
				  event.getPlayer().setHealth(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
			  }
			}
			return true;
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			
			for (Entity target : event.getPlayer().getNearbyEntities(15, 15, 15)) 
			{
				
				if (!(target instanceof Player)) 
				{
					continue;
				}
				
				if (PlayerDataMana.getPlayerData((Player) target).getCurrentMana()<=PlayerDataMana.getPlayerData((Player) target).getMinMana()) 
				{
					continue;
				}
				
				if (event.getPlayer().getHealth() <= 1.0 ) 
				{
					PrintUtils.sendMessage(event.getPlayer(),"You don't have enough health to cast this spell.");
					return false;
				}
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.SMOKE_LARGE, null);
				event.getPlayer().setHealth(event.getPlayer().getHealth() / 2.0);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.SMOKE_LARGE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_SHOOT, SoundCategory.MASTER, 1, 1);
				((Player) target).playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_AMBIENT, SoundCategory.MASTER, 1, 1);
				PlayerDataMana.getPlayerData((Player) target).setCurrentMana(PlayerDataMana.getPlayerData((Player) target).getCurrentMana() - 250);
				if (PlayerDataMana.getPlayerData((Player) target).getCurrentMana()<=PlayerDataMana.getPlayerData((Player) target).getMinMana()) 
				{
					PlayerDataMana.getPlayerData((Player) target).setCurrentMana(PlayerDataMana.getPlayerData((Player) target).getMinMana());
				}
				ManaInterface.updateScoreBoard((Player) target);
				PrintUtils.sendMessage(((Player) target),"You're mana has been drained.");
				
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() + 125);
				if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()>PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana()) 
				{
					PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana());
				}
				ManaInterface.updateScoreBoard(event.getPlayer());
				
			}
			return true;
		}
		
		PrintUtils.sendMessage(event.getPlayer(),"FIZZLE!");
		return false;
	}

}
