package com.perceus.spellcasting2.spellitem_spell;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellItem_BloodCrystal extends BaseSpellCapsule
{

	public SpellItem_BloodCrystal()
	{
		super(Material.QUARTZ, "§r§7§ko§r§7§lMagical Item: §r§fBlood Crystal§r§7§ko§r", "SpellItem_BloodCrystal", 0, true, "§r§fElement: §r§4Dark Magic§r§f.","§r§fSpell Type: §bUtility§f.", "§r§fA conglomerate of crystalized §r§4Dark Magic§r§f.","§r§fThis crystal, when broken,","§r§fand fully restores §ahealth§f and §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}	
		
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() == PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana() && event.getPlayer().getHealth() == event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Your current Mana and Health is already full.");
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
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_STEM_BREAK, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 100, Particle.CRIMSON_SPORE, null);
		PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana());
		ManaInterface.updateScoreBoard(event.getPlayer());		
		event.getPlayer().setHealth(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		return true;		
	}

}
