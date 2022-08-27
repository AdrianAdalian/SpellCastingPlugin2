package com.perceus.spellcasting2.spellitem_spell;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

public class MagicTool_ShovelOfGeo extends BaseSpellCapsule
{

	public MagicTool_ShovelOfGeo()
	{
		super(Material.NETHERITE_SHOVEL, "§r§f§lMagic Tool§r§f: Netherite Shovel§e+", "MagicTool_ShovelOfGeo", 0, false, "§r§fElement: §r§6§lConstruct§r§f.",
				"§r§fA Netherite Pickaxe infused with §6Geo§f energy.",
				"§r§6Ability§f: Terraformer's Haste.",
				"§r§fOn Right-Click Air: grant haste (lv3) for 1 minute.",
				"§r§fMana cost: 100 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		event.setCancelled(false);
		event.setUseInteractedBlock(Event.Result.ALLOW);
		event.setUseItemInHand(Event.Result.ALLOW);
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			 PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 100);
			 ManaInterface.updateScoreBoard(event.getPlayer());
			 SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
			 event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.MASTER, 1, 1);
			 event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1200, 2));
			 return true;
		}
		return true;
	}

}
