package com.perceus.spellcasting2.spellitem_spell;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.SmallFireball;
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

public class MagicWeapon_WandOfFire extends BaseSpellCapsule
{

	public MagicWeapon_WandOfFire()
	{
		super(Material.STICK, ChatUtils.of("Magic Weapon: Wand of Fire", "FFE748","FF1919","§l§o"), "MagicWeaponWandOfFire", 0, true, "§r§fElement: §r§cFire§r§f.","§r§fSpell Type: §cOffensive§f and §6Buff§f.",
				"§r§fA simple stick infused with the element of §r§cFire§r§f.",
				"§r§7§lSpell: §r§fHot Coals",
				"§r§fLeft-Click: Cast a tiny burst of flame towards a target.",
				"§r§fMana cost: 20 §r§9mana§r§f.",
				"§r§6Ability§r§f: Insulate-α",
				"§r§fRight-Click: Grant brief heat aborption.",
				"§r§fDuration: 10 seconds.",
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
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 20);
			if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_FIRECHARGE_USE, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.LAVA, null);
			SmallFireball fireball = event.getPlayer().launchProjectile(SmallFireball.class);
			fireball.setVelocity(fireball.getVelocity().multiply(10));
			return true;
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 30);
			if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_FIRECHARGE_USE, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.LAVA, null);
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 0));
			return true;
		}
		return false;
	}

}
