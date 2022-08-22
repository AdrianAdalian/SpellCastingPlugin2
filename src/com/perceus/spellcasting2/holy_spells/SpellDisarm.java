package com.perceus.spellcasting2.holy_spells;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellDisarm extends BaseSpellCapsule
{

	public SpellDisarm()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: Disarm§r§f§ko§r", "SpellDisarm", 100, false, "§r§fElement: §r§f§o§lHoly§r§f.","§r§fSpell Type: §aSupport§f.", "§r§fAn incantation capable of disarming target player.","§r§fRange: 30 meters.","§r§fMana cost: 100 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false; 
		}
		
		Entity target = getNearestPlayerInSight(event.getPlayer(), 15);
		
		if (target == null) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		if (!(target instanceof Player)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		List<Material> material = List.of(Material.DIAMOND_SWORD,
				Material.GOLDEN_SWORD,
				Material.IRON_SWORD,
				Material.NETHERITE_SWORD,
				Material.STONE_SWORD,
				Material.WOODEN_SWORD,
				Material.DIAMOND_AXE,
				Material.WOODEN_AXE,
				Material.STONE_AXE,
				Material.IRON_AXE,
				Material.GOLDEN_AXE,
				Material.NETHERITE_AXE,
				Material.BOW);
		
		ItemStack stack = ((Player) target).getInventory().getItemInMainHand();
		
		if (stack == null || !material.contains(stack.getType()))
        {
            PrintUtils.sendMessage(event.getPlayer(),"Valid Item Not Detected in Player's Hand.");
            return false;
        }
		
		if (stack.getType().equals(Material.DIAMOND_SWORD)) 
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			target.getLocation().getWorld().dropItem(target.getLocation(), stack);
			((Player) target).getInventory().getItemInMainHand().setAmount(0);
			PrintUtils.sendMessage(((Player) target), "You have been disarmed.");
			return true;
			
		}
		if (stack.getType().equals(Material.GOLDEN_SWORD)) 
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			target.getLocation().getWorld().dropItem(target.getLocation(), stack);
			((Player) target).getInventory().getItemInMainHand().setAmount(0);
			PrintUtils.sendMessage(((Player) target), "You have been disarmed.");
			return true;
		}
		if (stack.getType().equals(Material.IRON_SWORD)) 
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			target.getLocation().getWorld().dropItem(target.getLocation(), stack);
			((Player) target).getInventory().getItemInMainHand().setAmount(0);
			PrintUtils.sendMessage(((Player) target), "You have been disarmed.");
			return true;
		}
		if (stack.getType().equals(Material.NETHERITE_SWORD)) 
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			target.getLocation().getWorld().dropItem(target.getLocation(), stack);
			((Player) target).getInventory().getItemInMainHand().setAmount(0);
			PrintUtils.sendMessage(((Player) target), "You have been disarmed.");
			return true;
		}
		if (stack.getType().equals(Material.STONE_SWORD)) 
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			target.getLocation().getWorld().dropItem(target.getLocation(), stack);
			((Player) target).getInventory().getItemInMainHand().setAmount(0);
			PrintUtils.sendMessage(((Player) target), "You have been disarmed.");
			return true;
		}
		if (stack.getType().equals(Material.WOODEN_SWORD)) 
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			target.getLocation().getWorld().dropItem(target.getLocation(), stack);
			((Player) target).getInventory().getItemInMainHand().setAmount(0);
			PrintUtils.sendMessage(((Player) target), "You have been disarmed.");
			return true;
		}
		if (stack.getType().equals(Material.WOODEN_AXE)) 
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			target.getLocation().getWorld().dropItem(target.getLocation(), stack);
			((Player) target).getInventory().getItemInMainHand().setAmount(0);
			PrintUtils.sendMessage(((Player) target), "You have been disarmed.");
			return true;
		}
		if (stack.getType().equals(Material.STONE_AXE)) 
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			target.getLocation().getWorld().dropItem(target.getLocation(), stack);
			((Player) target).getInventory().getItemInMainHand().setAmount(0);
			PrintUtils.sendMessage(((Player) target), "You have been disarmed.");
			return true;
		}
		if (stack.getType().equals(Material.IRON_AXE)) 
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			target.getLocation().getWorld().dropItem(target.getLocation(), stack);
			((Player) target).getInventory().getItemInMainHand().setAmount(0);
			PrintUtils.sendMessage(((Player) target), "You have been disarmed.");
			return true;
		}
		if (stack.getType().equals(Material.GOLDEN_AXE)) 
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			target.getLocation().getWorld().dropItem(target.getLocation(), stack);
			((Player) target).getInventory().getItemInMainHand().setAmount(0);
			PrintUtils.sendMessage(((Player) target), "You have been disarmed.");
			return true;
		}
		if (stack.getType().equals(Material.DIAMOND_AXE)) 
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			target.getLocation().getWorld().dropItem(target.getLocation(), stack);
			((Player) target).getInventory().getItemInMainHand().setAmount(0);
			PrintUtils.sendMessage(((Player) target), "You have been disarmed.");
			return true;
		}
		if (stack.getType().equals(Material.NETHERITE_AXE)) 
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			target.getLocation().getWorld().dropItem(target.getLocation(), stack);
			((Player) target).getInventory().getItemInMainHand().setAmount(0);
			PrintUtils.sendMessage(((Player) target), "You have been disarmed.");
			return true;
		}
		if (stack.getType().equals(Material.SHIELD)) 
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			target.getLocation().getWorld().dropItem(target.getLocation(), stack);
			((Player) target).getInventory().getItemInMainHand().setAmount(0);
			PrintUtils.sendMessage(((Player) target), "You have been disarmed.");
			return true;
		}
		PrintUtils.sendMessage(event.getPlayer(), "FIZZLE!");
		return false;
	}
	private Entity getNearestPlayerInSight(Player player, int range) 
	{
		
	    ArrayList<Entity> entities = (ArrayList<Entity>) player.getNearbyEntities(range, range, range);
	    ArrayList<Block> sightBlock = null;
	    try 
	    {
	        sightBlock = (ArrayList<Block>) player.getLineOfSight(null, range);
	    } catch (IllegalStateException error) 
	    {
	        return null;
	    }
	     
	    ArrayList<Location> sight = new ArrayList<Location>();
	    for (int i = 0;i<sightBlock.size();i++)
	        sight.add(sightBlock.get(i).getLocation());
	    for (int i = 0;i<sight.size();i++) {
	        for (int k = 0;k<entities.size();k++) {
	            if (entities.get(k) instanceof LivingEntity && !(entities.get(k) instanceof ArmorStand)) {
	                if (Math.abs(entities.get(k).getLocation().getX()-sight.get(i).getX())<1.3) {
	                    if (Math.abs(entities.get(k).getLocation().getY()-sight.get(i).getY())<1.5) {
	                        if (Math.abs(entities.get(k).getLocation().getZ()-sight.get(i).getZ())<1.3) {
	                                
	                        	return entities.get(k);
	                        	
	                        }
	                    }
	                }
	            }
	        }
	    }
	    	return null; // Return null if no entity was found
	}
}
