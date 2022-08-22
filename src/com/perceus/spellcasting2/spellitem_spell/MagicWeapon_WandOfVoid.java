package com.perceus.spellcasting2.spellitem_spell;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class MagicWeapon_WandOfVoid extends BaseSpellCapsule
{

	public MagicWeapon_WandOfVoid()
	{
		super(Material.STICK, ChatUtils.of("Magic Weapon: Wand of Void", "FFE748","30D5C8","§l§o"), "MagicWeaponWandOfVoid", 25, true, "§r§fElement: §r§3§lVOID§r§f.","§r§fSpell Type: §cOffensive§f and §6Buff§f.",
				"§r§fA simple stick infused with the element of §r§3§lVOID§r§f.",
				"§r§7§lSpell: §r§fVoid Bolt",
				"§r§fLeft-Click: Cast a void bolt at a target,",
				"§r§flifting them up in the air 4 meters.",
				"§r§fInitial strike deals 1 heart of §r§cdamage§r§f.",
				"§r§fMana cost: 25 §r§9mana§r§f.",
				"§r§6Ability§r§f: Low Gravity",
				"§r§fRight-Click: Caster briefly resists the effects of gravity.",
				"§r§fDuration: 10 seconds.",
				"§r§fMana cost: 25 §r§9mana§r§f.");
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
		
		if (event.getAction().equals(Action.LEFT_CLICK_AIR))
		{
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_SHULKER_SHOOT, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
			Arrow arrow = event.getPlayer().launchProjectile(Arrow.class);
			arrow.setPickupStatus(PickupStatus.DISALLOWED);
			arrow.setCritical(false);
			arrow.setKnockbackStrength(0);
			arrow.setDamage(2);	
			arrow.addCustomEffect(new PotionEffect(PotionEffectType.LEVITATION, 100, 0), true);
			return true;
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 200, 0));
			new BukkitRunnable()
			{
				 @Override
				  public void run()
				  {
					 event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
				  }
			}.runTaskLater(Eden.getInstance(), 205);
			return true;
		}
		return false;
	}

}
