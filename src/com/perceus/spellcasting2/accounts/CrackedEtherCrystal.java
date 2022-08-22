package com.perceus.spellcasting2.accounts;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class CrackedEtherCrystal extends BaseSpellCapsule
{

	public CrackedEtherCrystal()
	{
		super(Material.QUARTZ, "§r§7§ko§r§4Corrupt Magical Object§r§f: Cracked Ether Crystal§r§7§ko§r", "CrackedEtherCrystal", 0, true, "§r§fReset caster's maximum mana to 1000.","§r§4§o§lWARNING§r§f: This action cannot be undone.","§r§c§oDeveloper Item§r§f");
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
		
		if (event.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) 
		{
			event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
		}
		else
		{
			event.getPlayer().getInventory().getItemInMainHand().setAmount(0);
		}
		
		SpellParticles.drawCylinder(event.getPlayer().getLocation(), 1, 50, 3, 1, Particle.ENCHANTMENT_TABLE, null);
		SpellParticles.drawCylinder(event.getPlayer().getLocation(), 2, 60, 4, 2, Particle.ENCHANTMENT_TABLE, null);
		SpellParticles.drawCylinder(event.getPlayer().getLocation(), 3, 75, 5, 3, Particle.ENCHANTMENT_TABLE, null);
		SpellParticles.drawCylinder(event.getPlayer().getLocation(), 4, 90, 6, 4, Particle.ENCHANTMENT_TABLE, null);
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 30, Particle.CRIMSON_SPORE, null);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.CRIMSON_SPORE, null);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 3, 3, 90, Particle.CRIMSON_SPORE, null);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 4, 4, 120, Particle.CRIMSON_SPORE, null);
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ROOTS_BREAK, SoundCategory.MASTER, 1, 1);

		PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
		ManaInterface.updateScoreBoard(event.getPlayer());
		
		PlayerDataMana.getPlayerData(event.getPlayer()).setMaxMana(500);
		
		PrintUtils.sendMessage(event.getPlayer(),"Maximum Mana Reset. New maximum is now " + PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana() + ".");
		
		return true;
	}

}
