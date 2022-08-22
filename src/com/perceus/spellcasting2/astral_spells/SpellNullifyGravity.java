package com.perceus.spellcasting2.astral_spells;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellNullifyGravity extends BaseSpellCapsule
{

	public SpellNullifyGravity()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("✩ Astral Tome: Suspend Gravity ✩", "8B008B","FFFFFF",""), "SpellSuspendGravity", 1000, false, 
				"§r§fElement: §r§5A§ds§5t§dr§5α§dl§r§f.",
				"§r§fSpell Type: §7Debuff§f §dAOE§f.",
				"§r§fA §5c§de§bl§3e§cs§4t§6i§eal §r§ftome.",
				"§r§fThis spell allows the caster to nullify gravity",
				"§r§ffor every living organism in the world.",
				"§r§fDuration: 1 minute.", 
				"§r§fMana cost: 1000 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().getWorld().getLivingEntities().size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.DRAGON_BREATH, null);
		Bukkit.broadcastMessage("§r§fThe world has fallen into §r§5A§ds§5t§dr§5α§dl§r§f distortion cast by " + event.getPlayer().getDisplayName() + "§r§f.");
		for (Entity target : event.getPlayer().getWorld().getLivingEntities())
		{
			if (target instanceof LivingEntity)
			{
				if (target.equals(event.getPlayer())) 
				{
					continue;
				}
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 1200, 0));
				SpellParticles.drawDisc(target.getLocation(), 2, 2, 20, Particle.DRAGON_BREATH, null);
				if (target instanceof Player) 
				{
					PrintUtils.sendMessage((Player) target, "Space has become distorted. The effects of gravity have been nullified.");
				}
			}
		}
		
		return true;
	}
}
