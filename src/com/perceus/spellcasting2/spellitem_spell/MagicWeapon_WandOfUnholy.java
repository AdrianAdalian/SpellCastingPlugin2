package com.perceus.spellcasting2.spellitem_spell;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class MagicWeapon_WandOfUnholy extends BaseSpellCapsule
{

	public MagicWeapon_WandOfUnholy()
	{
		super(Material.STICK, ChatUtils.of("Magic Weapon: Wand of Unholy", "FFE748","8B0000","§l§o"), "MagicWeaponWandOfUnholy", 0, true, "§r§fElement: §r§4§o§lUnholy§r§f.",
				"§r§fA simple stick infused with the element of §r§4§o§lUnholy§r§f.",
				"§r§7§lSpell: §r§fDark Pulse",
				"§r§fLeft-Click: Summon a bolt of darkness,",
				"§r§fthat withers those hit.",
				"§r§fDeals 2 hearts of §r§cdamage§r§f over 8 seconds.",
				"§r§fMana cost: 25 §r§9mana§r§f.",
				"§r§6Ability§r§f: Shade",
				"§r§fRight-Click: Envelope the caster in darkness,",
				"§r§fgranting brief invisibility.",
				"§r§fDuration: 15 seconds.",
				"§r§fMana cost: 30 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getAction().equals(Action.LEFT_CLICK_AIR)) 
		{
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 25);
			if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_SHOOT, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.SMOKE_LARGE, null);
			Arrow arrow = event.getPlayer().launchProjectile(Arrow.class);
			arrow.setPickupStatus(PickupStatus.DISALLOWED);
			arrow.setCritical(false);
			arrow.setKnockbackStrength(0);
			arrow.setDamage(0);	
			arrow.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 160, 0), true);
			return true;
		}
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 30);
			if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_AMBIENT, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.SMOKE_LARGE, null);
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 300, 0));
			return true;
		}
		return false;
	}

}
