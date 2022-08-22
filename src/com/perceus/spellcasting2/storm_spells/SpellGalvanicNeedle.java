package com.perceus.spellcasting2.storm_spells;

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

public class SpellGalvanicNeedle extends BaseSpellCapsule
{

	public SpellGalvanicNeedle()
	{
		super(Material.AMETHYST_SHARD, "§r§7§ko§r§7§lSpell: §r§fGalvanic Needle§r§7§ko§r", "SpellGalvanicNeedle", 60, true, true, "§r§fElement: §r§dStorm§r§f.","§r§fSpell Type: §cOffensive§f.","§r§fFire an electrified crystal shard","§r§fthat acts like a lightning rod.","§r§fDeals 1 heart of §r§cdamage §r§ffrom crystal strike,","§r§fthen summons a bolt of lightning.","§r§fMana cost: 60 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{

		if (!event.getAction().equals(Action.LEFT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.ELECTRIC_SPARK, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_AMETHYST_BLOCK_FALL, SoundCategory.MASTER, 1, 1);
		Arrow arrow = event.getPlayer().launchProjectile(Arrow.class);
		arrow.setDamage(2);
		arrow.setPickupStatus(PickupStatus.DISALLOWED);
		arrow.setCritical(false);
		new BukkitRunnable()
		  {
		    @Override
		    public void run()
		    {
		    	arrow.getWorld().strikeLightning(arrow.getLocation());
		    	arrow.remove();
		    }
		  }.runTaskLater(Eden.getInstance(), 20);
		return true;
	}

}
