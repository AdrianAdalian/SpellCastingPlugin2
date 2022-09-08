package com.perceus.spellcasting2.spellitem_spell;

import org.bukkit.Bukkit;
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
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class MagicWeapon_WandOfStorm extends BaseSpellCapsule
{

	public MagicWeapon_WandOfStorm()
	{
		super(Material.STICK, ChatUtils.of("Magic Weapon: Wand of Storm", "FFE748","CC8899","§l§o"), "MagicWeaponWandOfStorm", 0, true, "§r§fElement: §r§dStorm§r§f.",
				"§r§fA simple stick infused with the element of §r§dStorm§r§f.",
				"§r§7§lSpell: §r§fDischarge",
				"§r§fLeft-Click: Summon a bolt of electricity that",
				"§r§fbriefly cripples the target.",
				"§r§fDuration: 5 seconds.",
				"§r§fMana cost: 75 §r§9mana§r§f.",
				"§r§6Ability§r§f: Lightning Rod",
				"§r§fRight-Click: Boosts the caster's mobility during a storm.",
				"§r§fDuration: 20 seconds.",
				"§r§fMana cost: 30 §r§9mana§r§f.");
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
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 75);
			if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ARROW_SHOOT, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.ELECTRIC_SPARK, null);
			Arrow arrow = event.getPlayer().launchProjectile(Arrow.class);
			arrow.setPickupStatus(PickupStatus.DISALLOWED);
			arrow.setCritical(false);
			arrow.setKnockbackStrength(0);
			arrow.setDamage(0);	
			arrow.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 99), true);
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			
			boolean weather = Bukkit.getWorlds().get(0).isClearWeather();
			
			if (weather == true) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"It's not currently storming.");
				return false;
			}
			
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 35);
			if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			
			if (weather == false) 
			{
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.ELECTRIC_SPARK, null);
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 0));
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 400, 0));
				new BukkitRunnable()
				{
					 @Override
					  public void run()
					  {
						 event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
					  }
				}.runTaskLater(Eden.getInstance(), 405);
				return true;
			}
			return true;
			
		}
		return false;
	}

}
