package com.perceus.spellcasting2.water_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellMendingWater extends BaseSpellCapsule
{

	public SpellMendingWater()
	{
		super(Material.LAPIS_LAZULI, "§r§7§ko§r§7§lSpell: §r§fMending Water§r§7§ko§r", "SpellMendingWater", 250, true, true, "§r§fElement: §r§9Water§r§f.","§r§fThe caster emits a mending current of §r§9Water§r§f","§r§fto any damaged item in their left hand,","§r§frestoring it to its maximum durability.","§r§fMana cost: 250 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		ItemStack item = event.getPlayer().getInventory().getItemInOffHand();
		if (item == null) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Valid Item Not Detected in Offhand.");
			return false;
		}
		if (item.getType().equals(Material.AIR))
		{
		  PrintUtils.sendMessage(event.getPlayer(),"Valid Item Not Detected in Offhand.");
		  return false;
		}
		
		if (!(item.getItemMeta() instanceof Damageable))
		{
		  PrintUtils.sendMessage(event.getPlayer(),"Valid Item Not Detected in Offhand.");
		  return false;
		}
		
		Damageable meta = (Damageable) item.getItemMeta();
		 
		if (!meta.hasDamage())
		{
		  PrintUtils.sendMessage(event.getPlayer(),"This item is at maximum durability or is not applicable.");
		  return false;
		}
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
		meta.setDamage(0); // Fully repair the item
		item.setItemMeta(meta); // Apply the change
		return true;
	}

}
