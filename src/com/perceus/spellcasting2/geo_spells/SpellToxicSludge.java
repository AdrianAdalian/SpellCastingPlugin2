package com.perceus.spellcasting2.geo_spells;

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

public class SpellToxicSludge extends BaseSpellCapsule
{

	public SpellToxicSludge()
	{
		super(Material.ENCHANTED_BOOK, "§r§fTome: Toxic Sludge", "SpellToxicSludge", 200, true, "§r§fElement: §r§6Geo§r§f.",
				"§r§fSpell Type: §cOffensive§f §dAOE§f.",
				"§r§fThrow an ampule of toxic contaminant.",
				"§r§fLeaves a small field of §2Poison§f upon collision.",
				"§r§2Poison§f Duration: 1 minute.",
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
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.WARPED_SPORE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_LINGERING_POTION_THROW, SoundCategory.MASTER, 1, 1);
		
		ItemStack thrownpotion = new ItemStack(Material.LINGERING_POTION);
		PotionMeta meta = (PotionMeta) thrownpotion.getItemMeta();
		meta.setBasePotionData(new PotionData(PotionType.POISON));
		meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 1200, 0), true);
		thrownpotion.setItemMeta(meta);
		
		ThrownPotion sludge = event.getPlayer().launchProjectile(ThrownPotion.class);
		sludge.setItem(thrownpotion);
		
		return true;
	}

}
