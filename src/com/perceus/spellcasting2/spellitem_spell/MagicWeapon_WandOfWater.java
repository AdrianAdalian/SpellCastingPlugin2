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

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class MagicWeapon_WandOfWater extends BaseSpellCapsule
{

	public MagicWeapon_WandOfWater()
	{
		super(Material.STICK,  ChatUtils.of("Magic Weapon: Wand of Water", "FFE748","89CFF0","§l§o"), "MagicWeaponWandOfWater", 0, true, "§r§fElement: §r§9Water§r§f.",
				"§r§fA simple stick infused with the element of §r§9Water§r§f.",
				"§r§7§lSpell: §r§fWater Lash",
				"§r§fLeft-Click: Cast out a large blob of water,",
				"§r§fbriefly slowing whatever it hits.",
				"§r§fDuration: 10 seconds.",
				"§r§fMana cost: 30 §r§9mana§r§f.",
				"§r§6Ability§r§f: Ethereal Reconstitution",
				"§r§fRight-Click: Restores 20 §r§9mana§r§f while storming.");
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
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 30);
			if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ARROW_SHOOT, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
			Arrow arrow = event.getPlayer().launchProjectile(Arrow.class);
			arrow.setPickupStatus(PickupStatus.DISALLOWED);
			arrow.setCritical(false);
			arrow.setKnockbackStrength(1);
			arrow.setDamage(0);	
			arrow.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 200, 0), true);
			return true;
		}
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			
			boolean weather = Bukkit.getWorlds().get(0).isClearWeather();
			
			if (weather == true) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"It's not currently storming.");
				return false;
			}
			
			if (weather == false) 
			{
				if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() == PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana()) 
				{
					PrintUtils.sendMessage(event.getPlayer(),"You're already at full Mana.");
					return false;
				}
				
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() + 20);
				if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana()>PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana()) 
				{
					PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana());
				}
				ManaInterface.updateScoreBoard(event.getPlayer());
			}
			return true;
			
		}
		return false;
	}

}
