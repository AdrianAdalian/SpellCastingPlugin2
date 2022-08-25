package com.perceus.spellcasting2.spellitem_spell;

import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellItem_BloodCrystalPlus extends BaseSpellCapsule
{

	public SpellItem_BloodCrystalPlus()
	{
		super(Material.QUARTZ, "§r§7§ko§r§7§lMagical Item: §r§fBlood Crystal§r§e+§r§7§ko§r", "SpellItem_BloodCrystalPlus", 0, true, "§r§fElement: §r§4Dark Magic§r§f.","§r§fSpell Type: §bUtility§f.", "§r§fA conglomerate of crystalized §r§4Dark Magic§r§f.","§r§fThis crystal is impossible to break,","§r§fas the amount of pure dark magic radiating","§r§ffrom the crystal allows the wielder to","§r§ffully recover §r§aHealth§r§f and §r§9Mana§r§f at will.","§r§fAs a result of using this cursed object,", "§r§fthe wielder will suffer random negative effects.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}	
		
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() == PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana() && event.getPlayer().getHealth() == event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Your current Mana and Health is already full.");
			return false;
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_STEM_BREAK, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 100, Particle.CRIMSON_SPORE, null);
		PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana());
		ManaInterface.updateScoreBoard(event.getPlayer());		
		event.getPlayer().setHealth(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		
		List<PotionEffectType> negEffects = List.of(PotionEffectType.WEAKNESS,
				PotionEffectType.BAD_OMEN,
				PotionEffectType.CONFUSION,
				PotionEffectType.POISON,
				PotionEffectType.SLOW_DIGGING,
				PotionEffectType.WITHER,
				PotionEffectType.BLINDNESS,
				PotionEffectType.DARKNESS,
				PotionEffectType.HUNGER,
				PotionEffectType.UNLUCK,
				PotionEffectType.SLOW);
		
		Random random = new Random();
		PotionEffectType randomPotionEffect = PotionEffectType.values()[random.nextInt(PotionEffectType.values().length)];
		
		while (!(negEffects.contains((PotionEffectType)randomPotionEffect))) 
		{
			randomPotionEffect = PotionEffectType.values()[random.nextInt(PotionEffectType.values().length)];
		}

		event.getPlayer().addPotionEffect(new PotionEffect(randomPotionEffect, 600, 0, true));
		
		return true;
	}

}
