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
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellDarkHarvest extends BaseSpellCapsule
{

	public SpellDarkHarvest()
	{
		super(Material.BLACK_DYE, "§r§7§ko§r§7§lSpell: §r§fDark Harvest§r§7§ko§r", "SpellDarkHarvest", 300, true, true, "§r§fElement: §r§4§o§lUnholy§r§f.","§r§fEmit a huge burst of §r§4§o§lUnholy§r§f energy around the caster,","§r§fcausing those within range to","§r§fhave their lifeforce severely drained.","§r§fUndead targets are immune,","§r§fand The Warden and Wither are also immune.","§r§fAll within range take 5 hearts of §r§cdamage§r§f.","§r§4Drain§r§f 1 1/2 hearts for each target.","§r§fRange: 20 meters.","§r§fMana cost: 300 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().getNearbyEntities(20, 20, 20).size() == 0)
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
				EntityType.WARDEN,
				EntityType.WITHER);
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_AMBIENT, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.SMOKE_LARGE, null);
		
		for (Entity near : event.getPlayer().getNearbyEntities(15, 15, 15))
		{
		  if (entity.contains(near.getType()))
		  {
		    continue;
		  }

		  if (!(near instanceof Damageable))
		  {
		    continue;
		  }

		  ((Damageable) near).damage(6, event.getPlayer());

		  double maxHealth = event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
		  if (event.getPlayer().getHealth() + 2 >= maxHealth)
		  {
		    event.getPlayer().setHealth(maxHealth);
		    continue; // Maybe return?
		  }  

		  event.getPlayer().setHealth(event.getPlayer().getHealth() + 2);
		}
		return true;
	}

}
