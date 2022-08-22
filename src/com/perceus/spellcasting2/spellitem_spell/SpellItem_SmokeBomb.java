package com.perceus.spellcasting2.spellitem_spell;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellItem_SmokeBomb extends BaseSpellCapsule
{

	public SpellItem_SmokeBomb()
	{
		super(Material.FIREWORK_STAR, "§r§7§ko§r§7§lCrafted Spell: §r§fSmoke Bomb§r§7§ko§r", "SpellItem_SmokeBomb", 0, true, "§r§fElement: §r§4§o§lUnholy§r§f.","§r§fSpell Type: §6Buff§f.","§r§fA smoke bomb that cloaks the caster briefly.","§r§fDuration: 10 seconds.");
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
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_SHOOT, SoundCategory.MASTER, 1, 1);
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 15, Particle.SMOKE_LARGE, null);
		
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 0));
			
		return true;
	}
}
