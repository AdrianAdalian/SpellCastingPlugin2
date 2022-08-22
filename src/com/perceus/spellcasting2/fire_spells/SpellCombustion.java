package com.perceus.spellcasting2.fire_spells;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellCombustion extends BaseSpellCapsule
{

	public SpellCombustion()
	{
		super(Material.BLAZE_POWDER, "§r§7§ko§r§7§lSpell: §r§fCombustion§r§7§ko§r", "SpellCombustion", 400, true,true, "§r§fElement: §r§cFire§r§f.","§r§fSpell Type: §bUtility§f.", "§r§fBy concentrating a small flame in the caster's hand,","§r§fthe caster is able to smelt any ore.","§r§fCobbled stones are smeltable.","§r§fMana cost: 400 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		ItemStack stack = event.getPlayer().getInventory().getItemInOffHand();
		
		List<Material> material = List.of(Material.RAW_COPPER, 
				Material.RAW_GOLD, 
				Material.RAW_IRON, 
				Material.RAW_COPPER_BLOCK, 
				Material.RAW_GOLD_BLOCK,
				Material.RAW_IRON_BLOCK,
				Material.COAL_ORE,
				Material.DEEPSLATE_COAL_ORE,
				Material.DEEPSLATE_COPPER_ORE,
				Material.DEEPSLATE_DIAMOND_ORE,
				Material.DEEPSLATE_EMERALD_ORE,
				Material.DEEPSLATE_GOLD_ORE,
				Material.DEEPSLATE_IRON_ORE,
				Material.DEEPSLATE_LAPIS_ORE,
				Material.DEEPSLATE_REDSTONE_ORE,
				Material.DIAMOND_ORE,
				Material.EMERALD_ORE,
				Material.GOLD_ORE,
				Material.IRON_ORE,
				Material.LAPIS_ORE,
				Material.REDSTONE_ORE,
				Material.COBBLESTONE,
				Material.COBBLED_DEEPSLATE,
				Material.COPPER_ORE);
        
        if (stack == null || !material.contains(stack.getType()))
        {
            PrintUtils.sendMessage(event.getPlayer(),"Valid Item Not Detected in Offhand.");
            return false;
        }
		
        if (stack.getType() == Material.COPPER_ORE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.COPPER_INGOT);
			return true;
		}
        
		if (stack.getType() == Material.RAW_IRON) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.IRON_INGOT);
			return true;
		}
		if (stack.getType() == Material.RAW_GOLD) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.GOLD_INGOT);
			return true;
		}
		if (stack.getType() == Material.RAW_COPPER) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.COPPER_INGOT);
			return true;
		}
		if (stack.getType() == Material.RAW_IRON_BLOCK) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.IRON_BLOCK);
			return true;
		}
		if (stack.getType() == Material.RAW_GOLD_BLOCK) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.GOLD_BLOCK);
			return true;
		}
		if (stack.getType() == Material.RAW_COPPER_BLOCK) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.COPPER_BLOCK);
			return true;
		}
		if (stack.getType() == Material.COAL_ORE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.COAL);
			return true;
		}
		if (stack.getType() == Material.DEEPSLATE_COAL_ORE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.COAL);
			return true;
		}
		if (stack.getType() == Material.DEEPSLATE_COPPER_ORE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.COPPER_INGOT);
			return true;
		}
		if (stack.getType() == Material.DEEPSLATE_DIAMOND_ORE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.DIAMOND);
			return true;
		}
		if (stack.getType() == Material.DEEPSLATE_EMERALD_ORE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.EMERALD);
			return true;
		}
		if (stack.getType() == Material.DEEPSLATE_GOLD_ORE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.GOLD_INGOT);
			return true;
		}
		if (stack.getType() == Material.DEEPSLATE_IRON_ORE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.IRON_INGOT);
			return true;
		}
		if (stack.getType() == Material.DEEPSLATE_LAPIS_ORE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.LAPIS_LAZULI);
			return true;
		}
		if (stack.getType() == Material.DEEPSLATE_REDSTONE_ORE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.REDSTONE);
			return true;
		}
		if (stack.getType() == Material.DIAMOND_ORE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.DIAMOND);
			return true;
		}
		if (stack.getType() == Material.EMERALD_ORE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.EMERALD);
			return true;
		}
		if (stack.getType() == Material.IRON_ORE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.IRON_INGOT);
			return true;
		}
		if (stack.getType() == Material.GOLD_ORE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.GOLD_INGOT);
			return true;
		}
		if (stack.getType() == Material.LAPIS_ORE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.LAPIS_LAZULI);
			return true;
		}
		if (stack.getType() == Material.REDSTONE_ORE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.REDSTONE);
			return true;
		}
		if (stack.getType() == Material.COBBLESTONE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.STONE);
			return true;
		}
		if (stack.getType() == Material.COBBLED_DEEPSLATE) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().getInventory().getItemInOffHand().setType(Material.DEEPSLATE);
			return true;
		}
		PrintUtils.sendMessage(event.getPlayer(),"FIZZLE!");
		return false;
	}

}
