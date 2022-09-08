package com.perceus.spellcasting2.fire_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellLavaBomb extends BaseSpellCapsule
{

	public SpellLavaBomb()
	{
		super(Material.ENCHANTED_BOOK, "§r§fTome: Lava Bomb", "SpellLavaBomb", 250, true, 
				"§r§fElement: §r§cFire§r§f.","§r§fSpell Type: §cOffensive§f.", 
				"§r§fFire a blob of lava which after a delay,",
				"§r§fcauses an explosion.",
				"§r§fDelay: 2 seconds.",
				"§r§fMana cost: 250 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_FIRECHARGE_USE, SoundCategory.MASTER, 1, 1);
		
		Arrow arrow = event.getPlayer().launchProjectile(Arrow.class);
		arrow.setDamage(0);
		arrow.setPickupStatus(PickupStatus.DISALLOWED);
		arrow.setCritical(false);
		new BukkitRunnable()
		  {
		    @Override
		    public void run()
		    {
		    	arrow.getWorld().createExplosion(arrow.getLocation(), 10);
		    	arrow.remove();
		    }
		  }.runTaskLater(Eden.getInstance(), 40);
		  
		return true;
	}

}
