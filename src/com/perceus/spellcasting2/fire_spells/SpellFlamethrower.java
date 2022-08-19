package com.perceus.spellcasting2.fire_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellFlamethrower extends BaseSpellCapsule
{

	public SpellFlamethrower()
	{
		super(Material.BLAZE_POWDER, "§r§7§ko§r§7§lSpell: §r§fFlamethrower§r§7§ko§r", "SpellFlamethrower", 75, true, true, "§r§fElement: §r§cFire§r§f.", "§r§fSummons three consective balls of flame.","§r§fThird ball deals 5 hearts of §r§cdamage§r§f.","§r§fMana cost: 75 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_FIRECHARGE_USE, SoundCategory.MASTER, 1, 1);
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
		
		SmallFireball largefireball = event.getPlayer().launchProjectile(SmallFireball.class) ;
		largefireball.setVelocity(largefireball.getVelocity().multiply(10));
		
		new BukkitRunnable()
		{

			@Override
			public void run()
			{
			
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_FIRECHARGE_USE, SoundCategory.MASTER, 1, 1);
				SmallFireball largefireball = event.getPlayer().launchProjectile(SmallFireball.class) ;
				largefireball.setVelocity(largefireball.getVelocity().multiply(10));
				
			}
			
		}.runTaskLater(Eden.getInstance(), 5);
		
		new BukkitRunnable()
		{

			@Override
			public void run()
			{
			
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_FIRECHARGE_USE, SoundCategory.MASTER, 1, 1);
				LargeFireball largefireball = event.getPlayer().launchProjectile(LargeFireball.class) ;
				largefireball.setVelocity(largefireball.getVelocity().multiply(2));
				
			}
			
		}.runTaskLater(Eden.getInstance(), 20);
		return true;
	}

}
