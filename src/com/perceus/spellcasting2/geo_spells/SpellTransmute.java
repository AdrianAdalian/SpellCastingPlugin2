package com.perceus.spellcasting2.geo_spells;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellTransmute extends BaseSpellCapsule
{

	public SpellTransmute()
	{
		super(Material.ENCHANTED_BOOK, "§r§fTome: Transmute", "SpellTransmute", 150, false, "§r§fElement: §r§6Geo§r§f.","§r§fSpell Type: §bUtility§f.","§r§fInfuse any ore with the forces of §r§6Geo§r§f.","§r§fTransmute that ore into its next level of rarity.","§r§fDoes the same thing in off-hand.","§r§fRange: 10 meters.","§r§fMana cost: 150 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		
		if (event.getAction().equals(Action.LEFT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		if (event.getAction().equals(Action.LEFT_CLICK_BLOCK))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			
			ItemStack stack = event.getPlayer().getInventory().getItemInOffHand();
			
			List<Material> material = List.of(Material.RAW_COPPER, 
					Material.RAW_GOLD, 
					Material.RAW_IRON, 
					Material.COAL_ORE,
					Material.DEEPSLATE_COAL_ORE,
					Material.DEEPSLATE_COPPER_ORE,
					Material.DEEPSLATE_DIAMOND_ORE,
					Material.DEEPSLATE_EMERALD_ORE,
					Material.DEEPSLATE_GOLD_ORE,
					Material.DEEPSLATE_IRON_ORE,
					Material.DEEPSLATE_LAPIS_ORE,
					Material.DIAMOND_ORE,
					Material.EMERALD_ORE,
					Material.GOLD_ORE,
					Material.IRON_ORE,
					Material.LAPIS_ORE,
					Material.REDSTONE_ORE,
					Material.LAPIS_LAZULI,
					Material.COAL,
					Material.COPPER_ORE,
					Material.DIAMOND);
	        
	        if (stack == null || !material.contains(stack.getType()))
	        {
	            PrintUtils.sendMessage(event.getPlayer(),"Valid Item Not Detected in Offhand.");
	            return false;
	        }
	        //precious gemstones & copper
	        if (stack.getType() == Material.RAW_COPPER) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.LAPIS_LAZULI);
				return true;
			}
	        if (stack.getType() == Material.LAPIS_LAZULI) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.EMERALD);
				return true;
			}
	        if (stack.getType() == Material.COPPER_ORE) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.LAPIS_ORE);
				return true;
			}
	        if (stack.getType() == Material.LAPIS_ORE) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.EMERALD_ORE);
				return true;
			}
	        if (stack.getType() == Material.DEEPSLATE_COPPER_ORE) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.DEEPSLATE_LAPIS_ORE);
				return true;
			}
	        if (stack.getType() == Material.DEEPSLATE_LAPIS_ORE) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.DEEPSLATE_EMERALD_ORE);
				return true;
			}
	        if (stack.getType() == Material.COAL) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.RAW_IRON);
				return true;
			}
	        if (stack.getType() == Material.RAW_IRON) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.RAW_GOLD);
				return true;
			}
	        if (stack.getType() == Material.RAW_GOLD) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.DIAMOND);
				return true;
			}
	        if (stack.getType() == Material.DIAMOND) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.NETHERITE_SCRAP);
				return true;
			}
	        
	        if (stack.getType() == Material.COAL_ORE) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.IRON_ORE);
				return true;
			}
	        if (stack.getType() == Material.IRON_ORE) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.GOLD_ORE);
				return true;
			}
	        if (stack.getType() == Material.GOLD_ORE) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.DIAMOND_ORE);
				return true;
			}
	        if (stack.getType() == Material.DIAMOND_ORE) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.ANCIENT_DEBRIS);
				return true;
			}
	        
	        //deepslate ores
	        
	        if (stack.getType() == Material.DEEPSLATE_COAL_ORE) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.DEEPSLATE_IRON_ORE);
				return true;
			}
	        if (stack.getType() == Material.DEEPSLATE_IRON_ORE) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.DEEPSLATE_GOLD_ORE);
				return true;
			}
	        if (stack.getType() == Material.DEEPSLATE_GOLD_ORE) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.DEEPSLATE_DIAMOND_ORE);
				return true;
			}
	        if (stack.getType() == Material.DEEPSLATE_DIAMOND_ORE) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.ANCIENT_DEBRIS);
				return true;
			}
	        
	        PrintUtils.sendMessage(event.getPlayer(),"FIZZLE!");
            return false;
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			int TARGETRANGE = 10;
			
			Block target = event.getPlayer().getTargetBlock(null, TARGETRANGE) ;
			
			if (target.getType() != null)
			{
				
				if (target.getType().equals(Material.COAL_ORE)) 
				{
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, SoundCategory.MASTER, 1, 1);
					target.setType(Material.IRON_ORE);
					return true;
				}
				
				if (target.getType().equals(Material.IRON_ORE)) 
				{
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, SoundCategory.MASTER, 1, 1);
					target.setType(Material.GOLD_ORE);
					return true;
				}
				
				if (target.getType().equals(Material.GOLD_ORE)) 
				{
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_DIAMOND, SoundCategory.MASTER, 1, 1);
					target.setType(Material.DIAMOND_ORE);
					return true;
				}
				
				if (target.getType().equals(Material.DIAMOND_ORE)) 
				{
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, SoundCategory.MASTER, 1, 1);
					target.setType(Material.ANCIENT_DEBRIS);
					return true;
				}
				
				if (target.getType().equals(Material.COPPER_ORE)) 
				{
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.MASTER, 1, 1);
					target.setType(Material.LAPIS_ORE);
					return true;
				}
				
				if (target.getType().equals(Material.LAPIS_ORE)) 
				{
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.MASTER, 1, 1);
					target.setType(Material.EMERALD_ORE);
					return true;
				}
				//DEEPSLATE TARGETS
				if (target.getType().equals(Material.DEEPSLATE_COAL_ORE)) 
				{
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, SoundCategory.MASTER, 1, 1);
					target.setType(Material.DEEPSLATE_IRON_ORE);
					return true;
				}
				
				if (target.getType().equals(Material.DEEPSLATE_IRON_ORE)) 
				{
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, SoundCategory.MASTER, 1, 1);
					target.setType(Material.DEEPSLATE_GOLD_ORE);
					return true;
				}
				
				if (target.getType().equals(Material.DEEPSLATE_GOLD_ORE)) 
				{
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_DIAMOND, SoundCategory.MASTER, 1, 1);
					target.setType(Material.DEEPSLATE_DIAMOND_ORE);
					return true;
				}
				
				if (target.getType().equals(Material.DEEPSLATE_DIAMOND_ORE)) 
				{
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, SoundCategory.MASTER, 1, 1);
					target.setType(Material.ANCIENT_DEBRIS);
					return true;
				}
				
				if (target.getType().equals(Material.DEEPSLATE_COPPER_ORE)) 
				{
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.MASTER, 1, 1);
					target.setType(Material.DEEPSLATE_LAPIS_ORE);
					return true;
				}
				
				if (target.getType().equals(Material.DEEPSLATE_LAPIS_ORE)) 
				{
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.MASTER, 1, 1);
					target.setType(Material.DEEPSLATE_EMERALD_ORE);
					return true;
				}
			}
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		PrintUtils.sendMessage(event.getPlayer(),"FIZZLE!");
        return false;
	}
}
