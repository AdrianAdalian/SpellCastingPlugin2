package com.perceus.spellcasting2.water_spells;

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

public class SpellPoseidonsGift extends BaseSpellCapsule
{

	public SpellPoseidonsGift()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: §r§fPoseidon's Gift§r§f§ko§r", "SpellPoseidonsGift", 0, false, "§r§fElement: §r§9Water§r§f.","§r§fSpell Type: §bUtility§f.","§r§fAn enchantment used to soothe one's body.","§r§fRegenerate the caster's mana while swimming.","§r§fRestores 200 §r§9Mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		
		
		if (event.getPlayer().isSwimming())
		{
			if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() == PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana()) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"You're already at full Mana.");
				return false;
			}
			
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);			
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() + 200);
			
			if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()>PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana());
			}
			
			ManaInterface.updateScoreBoard(event.getPlayer());
			return true;
		}
		return false;
	}

}
