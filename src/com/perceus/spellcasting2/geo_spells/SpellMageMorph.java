package com.perceus.spellcasting2.geo_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellMageMorph extends BaseSpellCapsule
{

	public SpellMageMorph()
	{
		super(Material.GOLD_INGOT, "§r§7§ko§r§7§lSpell: §r§fMageMorph§r§7§ko§r", "SpellMageMorph", 75, true, true, "§r§fElement: §r§6Geo§r§f.","§r§fSpell Type: §6Buff§f.","§r§fA lightweight incantation that","§r§fprotects the caster from damage.","§r§fCoats the body in a protective coat of mage's armor.","§r§fGrant 20% damage reduction and fire protection.","§r§fAlso slightly boots movement speed.","§r§fDuration: 1 minute.","§r§fMana cost: 75 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		 SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
		 event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_DIAMOND, SoundCategory.MASTER, 1, 1);
		 event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200, 0));
		 event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1200, 0));
		 event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 0));
		 
		 new BukkitRunnable()
		 {
		   @Override
		   public void run()
		   {
			   event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1, 1);
		   }
		 }.runTaskLater(Eden.getInstance(), 1205);
		 return true;
	}

}
