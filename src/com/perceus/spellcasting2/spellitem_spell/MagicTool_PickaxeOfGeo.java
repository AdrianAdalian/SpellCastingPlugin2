package com.perceus.spellcasting2.spellitem_spell;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class MagicTool_PickaxeOfGeo extends BaseSpellCapsule
{

	public MagicTool_PickaxeOfGeo()
	{
		super(Material.NETHERITE_PICKAXE, "§r§f§lMagic Tool§r§f: Pickaxe of §6Geo§f", "MagicTool_PickaxeOfGeo", 0, false, "§r§fElement: §r§6Geo§r§f.",
				"§r§fSpell Type: §6Buff§f and §bUtility§f.",
				"§r§fA Netherite Pickaxe infused with §6Geo§f energy.",
				"§r§fOn Right-Click Air: grant haste (lv3) for 1 minute.",
				"§r§fMana cost: 100 §r§9mana§r§f.",
				"§r§fOn Right-Click Block: if the target is an ore, instantly mine it.",
				"§r§fMana cost: 25 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{

		event.setCancelled(false);
		event.setUseInteractedBlock(Event.Result.ALLOW);
		event.setUseItemInHand(Event.Result.ALLOW);
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			 PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 100);
			 ManaInterface.updateScoreBoard(event.getPlayer());
			 SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
			 event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.MASTER, 1, 1);
			 event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1200, 2));
			 return true;
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) 
		{
			Block target = event.getPlayer().getTargetBlock(null, 10);
			
			if (target == null)
			{
				PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
				return false;
			}
			
			List<Material> material = List.of(Material.COAL_ORE,Material.DEEPSLATE_COAL_ORE,Material.DEEPSLATE_COPPER_ORE,Material.DEEPSLATE_DIAMOND_ORE,Material.DEEPSLATE_EMERALD_ORE,Material.DEEPSLATE_GOLD_ORE,
					Material.DEEPSLATE_IRON_ORE,Material.DEEPSLATE_LAPIS_ORE,Material.DIAMOND_ORE,Material.EMERALD_ORE,Material.GOLD_ORE,Material.IRON_ORE,Material.LAPIS_ORE,Material.REDSTONE_ORE,
					Material.NETHER_QUARTZ_ORE, Material.NETHER_GOLD_ORE, Material.ANCIENT_DEBRIS, Material.COPPER_ORE, Material.DEEPSLATE_REDSTONE_ORE, Material.REDSTONE_ORE);
			
			if (!material.contains(target.getType()))
	        {
	            PrintUtils.sendMessage(event.getPlayer(),"Target is not an ore.");
	            return false;
	        }
			
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 25);
			ManaInterface.updateScoreBoard(event.getPlayer());
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_STONE_BREAK, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(target.getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
			target.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, target.getLocation(), 1);
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					target.breakNaturally();
				}
			}.runTaskLater(Eden.getInstance(), 1);
			
			return true;
		}
		
		return true;
	}

}
