package com.perceus.spellcasting2.unholy_spells;

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

public class SpellDesintegrationAmpule extends BaseSpellCapsule
{

	public SpellDesintegrationAmpule()
	{
		super(Material.ENCHANTED_BOOK, "§r§fTome: Corrosion", "SpellDesintegrationAmpule", 200, true, "§r§fElement: §r§4§o§lUnholy§r§f.",
				"§r§fSpell Type: §cOffensive§f §dAOE§f.",
				"§r§fThrow an ampule of corroding contaminant.",
				"§r§fLeaves behind a small affected field upon collision.",
				"§r§fThose that enter will receive §4Wither§f.",
				"§r§4Wither§f Duration: 1 minute.",
				"§r§fMana cost: 200 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.SMOKE_LARGE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_LINGERING_POTION_THROW, SoundCategory.MASTER, 1, 1);
		
		ItemStack thrownpotion = new ItemStack(Material.LINGERING_POTION);
		PotionMeta meta = (PotionMeta) thrownpotion.getItemMeta();
		meta.setBasePotionData(new PotionData(PotionType.INSTANT_DAMAGE));
		meta.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 1200, 0), true);
		thrownpotion.setItemMeta(meta);
		
		ThrownPotion sludge = event.getPlayer().launchProjectile(ThrownPotion.class);
		sludge.setItem(thrownpotion);
		
		return true;
	}

}
