package com.perceus.spellcasting2.spellitem_spell;

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

public class SpellItem_EtherCrystal extends BaseSpellCapsule
{

	public SpellItem_EtherCrystal()
	{
		super(Material.QUARTZ, "§r§7§ko§r§7§lMagical Item: §r§fEther Crystal§r§7§ko§r", "EtherCrystal", 0, true, "§r§fElement: §bE§ft§bh§fe§br.",
				"§r§fSpell Type: §9Unique§f §bUtility§f.",
				"§r§fA crystalized source of pure §bE§ft§bh§fe§br energy.",
				"§r§fFirst two uses will grant +250 max mana.",
				"§r§fEvery other use of an Ether Crystal grants +50 max mana.",
				"§r§fThe crystal is very brittle and will break on use.",
				"§r§fCurrent mana will also be reset to 0.",
				"§r§4Warning§r§f: This action cannot be undone.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana() == 2000) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"You've obtained true maximum mana. This crystal has no effect on you.");
			return false;
		}
		
		if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() != PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana()) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"§r§fFIZZLE! Mana expansion requires the caster to expend §r§f§oall§r§f of their maximum mana. You are currently at " + PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() + " §r§fof " + PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana() + "§r§f.");
			return false;
		}
		
		if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana() < 1000) 
		{
			if (event.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) 
			{
				event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
			}
			else
			{
				event.getPlayer().getInventory().getItemInMainHand().setAmount(0);
			}
			
			SpellParticles.drawCylinder(event.getPlayer().getLocation(), 1, 50, 3, 1, Particle.ENCHANTMENT_TABLE, null);
			SpellParticles.drawCylinder(event.getPlayer().getLocation(), 3, 60, 4, 3, Particle.ENCHANTMENT_TABLE, null);
			SpellParticles.drawCylinder(event.getPlayer().getLocation(), 5, 75, 5, 5, Particle.ENCHANTMENT_TABLE, null);
			SpellParticles.drawCylinder(event.getPlayer().getLocation(), 7, 90, 6, 7, Particle.ENCHANTMENT_TABLE, null);
			
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 30, Particle.CLOUD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.CLOUD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 3, 3, 90, Particle.CLOUD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 4, 4, 120, Particle.CLOUD, null);
			
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, SoundCategory.MASTER, 1, 1);

			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana());
			ManaInterface.updateScoreBoard(event.getPlayer());
			
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setMaxMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana() + 250);
			
			PrintUtils.sendMessage(event.getPlayer(),"Maximum Mana Increased (+250). New maximum is now " + PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana() + ".");
			
			return true;
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
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.CLOUD, null);
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, SoundCategory.MASTER, 1, 1);

		PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana());
		ManaInterface.updateScoreBoard(event.getPlayer());
		
		PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setMaxMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana() + 50);
		
		PrintUtils.sendMessage(event.getPlayer(),"Maximum Mana Increased (+50). New maximum is now " + PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana() + ".");
		
		return true;
	}

}
