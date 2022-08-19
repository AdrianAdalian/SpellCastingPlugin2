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

public class SpellItem_ManaCrystal extends BaseSpellCapsule
{

	public SpellItem_ManaCrystal()
	{
		super(Material.QUARTZ, "§r§7§ko§r§7§lMagical Item: §r§fMana Crystal§r§7§ko§r", "SpellItem_ManaCrystal", 0, true, "§r§fElement: §r§9Water§r§f.", "§r§fA conglomerate of crystalized §r§9mana§r§f.","§r§fThis crystal, when broken,","§r§fwill restore 500 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() == PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana()) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"You're already at full Mana.");
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
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, SoundCategory.MASTER, 1, 1);
		PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() + 500);
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()>PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana()) 
		{
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana());
		}
		ManaInterface.updateScoreBoard(event.getPlayer());
		return true;
	}

}
