package com.perceus.spellcasting2.spellitem_spell;

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

public class SpellItem_ProtectivePaste extends BaseSpellCapsule
{

	public SpellItem_ProtectivePaste()
	{
		super(Material.CLAY_BALL, "§r§7§ko§r§7§lCrafted Spell: §r§fProtective Paste§r§7§ko§r", "SpellItem_ProtectivePaste", 0, true, "§r§fElement: §r§6Geo§r§f.","§r§fSpell Type: §6Buff§f.", "§r§fClay paste infused with §r§6Geo§r§f energy.", "§r§fWhen spread on the caster,", "§r§fgrant minor physical resistance.","§r§fDuration: 1 minute.");
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
		 SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
		 event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_GRAVEL_PLACE, SoundCategory.MASTER, 1, 1);
		 event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200, 0));
		 
		 new BukkitRunnable()
		 {
		   @Override
		   public void run()
		   {
			   event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_GRAVEL_BREAK, SoundCategory.MASTER, 1, 1);
		   }
		 }.runTaskLater(Eden.getInstance(), 1205);
		 return true;
	}

}
