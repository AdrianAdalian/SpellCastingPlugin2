package com.perceus.spellcasting2.lunar_spells;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellMoonBlast extends BaseSpellCapsule
{

	public SpellMoonBlast()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("☽ Lunar Tome: Moonblast ☽", "30D5C8","FFFFFF",""), "SpellMoonBlast", 350, false, "§r§fElement: §r§f§o§l§bL§3u§bn§3α§br§r§f.",
				"§r§fA §5C§de§bl§3e§cs§4t§6i§eal §r§ftome.","§r§fSpell Type: §cOffensive§f.",
				"§r§fThe spell within grows stronger during §7Nightfall§f.",
				"§r§fDuring the §r§6Day§r§f:",
				"§r§fCast a weak blast of §r§f§o§l§bL§3u§bn§3a§br§r§f energy.",
				"§r§fDeals 3 hearts of §r§cdamage§r§f.",
				"§r§fLevitate those hit for 10 seconds.",
				"§r§fWhile during §r§7Nightfall§r§f:",
				"§r§fCast out a concentrated blast of §r§f§o§l§bL§3u§bn§3a§br§r§f energy.",
				"§r§fDeals 8 hearts of §r§cdamage§r§f.",
				"§r§fLevitate those hit for 20 seconds.",
				"§r§fMana cost: 350 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		long time = Bukkit.getWorlds().get(0).getTime();

		if(time >= 0 && time <= 12000) //while time is day
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.GLOW_SQUID_INK, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			Arrow arrow = event.getPlayer().launchProjectile(Arrow.class);
			arrow.setColor(Color.TEAL);
			arrow.setGlowing(true);
			arrow.setPickupStatus(PickupStatus.DISALLOWED);
			arrow.setCritical(false);
			arrow.setKnockbackStrength(1);
			arrow.setDamage(6);
			arrow.addCustomEffect(new PotionEffect(PotionEffectType.LEVITATION, 200, 0), true);
			new BukkitRunnable()
			  {
			    @Override
			    public void run()
			    {
			    	arrow.remove();
			    }
			  }.runTaskLater(Eden.getInstance(), 20);
			return true;
		}
		
		if(time >=12005 && time <= 24000) //while time is night
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.GLOW_SQUID_INK, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			Arrow arrow = event.getPlayer().launchProjectile(Arrow.class);
			arrow.setColor(Color.TEAL);
			arrow.setGlowing(true);
			arrow.setPickupStatus(PickupStatus.DISALLOWED);
			arrow.setCritical(false);
			arrow.setKnockbackStrength(3);
			arrow.setDamage(16);
			arrow.addCustomEffect(new PotionEffect(PotionEffectType.LEVITATION, 400, 0), true);
			new BukkitRunnable()
			  {
			    @Override
			    public void run()
			    {
			    	arrow.remove();
			    }
			  }.runTaskLater(Eden.getInstance(), 40);
			return true;
		}
		
		PrintUtils.sendMessage(event.getPlayer(),"FIZZLE!");
		return false;
	}

}
