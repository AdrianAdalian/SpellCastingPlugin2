package com.perceus.spellcasting2.fire_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellEruption extends BaseSpellCapsule
{

	public SpellEruption()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: Eruption§r§f§ko§r", "SpellEruption", 800, true, "§r§fElement: §r§cFire§r§f.","§r§fSpell Type: §cOffensive§f §dAOE§f.","§r§fEmit a surge of §r§cFire§r§f energy around the caster,","§r§fpriming all nearby targets within range.","§r§fThose effected will explode after a short duration.","§r§fDelay: 5 seconds.","§r§fRange: 30 meters.","§r§fMana cost: 800 §r§9mana§r§f.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		if (event.getPlayer().getNearbyEntities(30, 30, 30).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_BLAZE_AMBIENT, SoundCategory.MASTER, 1, 1);
		
		for (Entity target : event.getPlayer().getNearbyEntities(30, 30, 30)) 
		{
			
			if (target instanceof Player) 
			{
				continue;
			}
			
			SpellParticles.drawDisc(target.getLocation(), 2, 2, 20, Particle.LAVA, null);
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.FLAME, null);
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					target.getWorld().createExplosion(target.getLocation(), 10);
				}
			}.runTaskLater(Eden.getInstance(), 100);
		}
		return true; 
	}

}
//,"§r§fSpell Type: §7Debuff§f §dAOE§f."