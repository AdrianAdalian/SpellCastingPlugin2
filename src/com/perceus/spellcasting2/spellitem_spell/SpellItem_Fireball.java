package com.perceus.spellcasting2.spellitem_spell;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellItem_Fireball extends BaseSpellCapsule
{

	public SpellItem_Fireball()
	{
		super(Material.FIRE_CHARGE, "§r§7§ko§r§7§lCrafted Spell: §r§fHot Coals§r§7§ko§r", "SpellItem_Fireball", 0, true, "§r§fElement: §r§cFire§r§f.","§r§fSpell Type: §cOffensive§f.","§r§fLaunch hot coals that set whatever's hit ablaze.");
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
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_FIRECHARGE_USE, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.LAVA, null);
		//casting animation for Element: Fire.
		
		event.getPlayer().launchProjectile(SmallFireball.class);
		return true;
	}

}
