package com.perceus.spellcasting2.holy_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellUrgentSupport extends BaseSpellCapsule
{

	public SpellUrgentSupport()
	{
		super(Material.ENCHANTED_BOOK, "§r§fTome: Urgent Support", "SpellUrgentSupport", 100, true, "§r§fElement: §r§f§o§lHoly§r§f.",
				"§r§fSpell Type: §aSupport§f §dAOE§f.",
				"§r§fThrow an ampule of holy water.",
				"§r§fThose within splash range will be §aHealed§f 4 hearts,",
				"§r§fgiven 4 hearts of Absorption and Resistance.",
				"§r§fEffect Duration: 20 seconds.",
				"§r§fMana cost: 100 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_LINGERING_POTION_THROW, SoundCategory.MASTER, 1, 1);
		
		ItemStack thrownpotion = new ItemStack(Material.SPLASH_POTION);
		PotionMeta meta = (PotionMeta) thrownpotion.getItemMeta();
		meta.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
		meta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1), true);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 0), true);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.ABSORPTION, 400, 1), true);
		thrownpotion.setItemMeta(meta);
		
		ThrownPotion sludge = event.getPlayer().launchProjectile(ThrownPotion.class);
		sludge.setItem(thrownpotion);
		
		return true;
	}

}
