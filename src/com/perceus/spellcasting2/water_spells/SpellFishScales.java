package com.perceus.spellcasting2.water_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellFishScales extends BaseSpellCapsule
{

	public SpellFishScales()
	{
		super(Material.PRISMARINE_CRYSTALS, "§r§7§ko§r§7§lSpell: §r§fFish Scales§r§7§ko§r", "SpellFishScales", 50, true, true, "§r§fElement: §r§9Water§r§f.","§r§fHardens the caster's flesh into fish scales.","§r§fOn Right-Click:","§r§fApply 20% damage reduction for 20 seconds.","§r§fOn Left-Click:","§r§fExpell a fish scale that slows, weakens and fatigues","§r§fthose hit. Also deal minor damage.","§r§fDeals 2 hearts of §r§cdamage§r§f.","§r§fDuration of effects: 20 seconds.","§r§f","§r§fMana cost: 50 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (event.getAction().equals(Action.LEFT_CLICK_BLOCK))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		
		if (event.getAction().equals(Action.LEFT_CLICK_AIR))
		{
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ARROW_SHOOT, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
			Arrow arrow = event.getPlayer().launchProjectile(Arrow.class);
			arrow.setPickupStatus(PickupStatus.DISALLOWED);
			arrow.setCritical(false);
			arrow.setKnockbackStrength(1);
			arrow.setDamage(4);	
			arrow.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 400, 0), true);
			arrow.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 0), true);
			arrow.addCustomEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 400, 0), true);
			return true;
		}
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			 SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
			 event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, SoundCategory.MASTER, 1, 1);
			 event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 0));
			 
			 new BukkitRunnable()
			 {
			   @Override
			   public void run()
			   {
				   event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1, 1);
			   }
			 }.runTaskLater(Eden.getInstance(), 405);
			return true;
		}
		PrintUtils.sendMessage(event.getPlayer(),"FIZZLE!");
		return false;
	}

}
