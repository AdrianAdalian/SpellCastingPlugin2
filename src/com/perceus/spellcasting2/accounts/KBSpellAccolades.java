package com.perceus.spellcasting2.accounts;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.CastListener;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.permissions.PlayerData;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class KBSpellAccolades extends BaseSpellCapsule
{

	public KBSpellAccolades()
	{
		super(Material.PAPER, "§r§f§l§ko§r§fSpell Page: Accolades§r§f§l§ko§r", "KBSpellAccolades", 0, true, "§r§fA torn page of magical knowledge containing", "§r§fthe means to cast the §r§f§l§oHoly§r§f spell: Accolades.","§r§fRight-Click to permanently unlock the spell.","§r§fUnlocked spells can be accessed in the spellbook.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			event.setCancelled(true);
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		PlayerData account = Eden.getPermissionsManager().getPlayerData(event.getPlayer());
		
		if (PlayerAccountManagement.hasSpellUnlocked(event.getPlayer(), CastListener.spell_registry.get("SpellAccolades")) == false) 
		{
			if (event.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) 
			{
				event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
			}
			else
			{
				event.getPlayer().getInventory().getItemInMainHand().setAmount(0);
			}
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawCylinder(event.getPlayer().getLocation(), 1, 50, 4, 1, Particle.ENCHANTMENT_TABLE, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.CLOUD, null);
			account.getModuleData("unlockedspells").setValue("SpellAccolades", true);
			PrintUtils.sendMessage(event.getPlayer(), "New Spell Discovered: Accolades.");
			return true;
		}
		
		PrintUtils.sendMessage(event.getPlayer(), "You already know how to cast this spell.");
		return false;
	}
	
}