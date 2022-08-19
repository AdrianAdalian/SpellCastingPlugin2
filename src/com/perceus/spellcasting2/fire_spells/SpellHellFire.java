package com.perceus.spellcasting2.fire_spells;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.LargeFireball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellHellFire extends BaseSpellCapsule
{

	public SpellHellFire()
	{
		super(Material.BLAZE_POWDER, "§r§7§ko§r§7§lSpell: §r§fHell Fire§r§7§ko§r", "SpellHellFire", 0, true, true, "§r§fElement: §r§cFire§r§f.", "§r§fThe caster wields intense hellfire capable of many usages.","§r§fOn Left-Click:","§r§fSummon a huge ball of fire.","§r§fMana cost: 35 §r§9mana§r§f.","§r§fOn Right-Click:","§r§fThe caster is able to smelt any Nether Ore.","§r§fTo smelt Ancient Debris: 500 §r§9mana§r§f.","§r§fQuartz and Gold Ore: 250 §r§9mana§r§f.","§r§fGilded Blackstone is smeltable.");
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
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 35);
			if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
        	
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_FIRECHARGE_USE, SoundCategory.MASTER, 1, 1);
			LargeFireball largefireball = event.getPlayer().launchProjectile(LargeFireball.class) ;
			largefireball.setVelocity(largefireball.getVelocity().multiply(10));
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			ItemStack stack = event.getPlayer().getInventory().getItemInOffHand();
			
			List<Material> material = List.of(Material.ANCIENT_DEBRIS,
					Material.NETHER_GOLD_ORE,
					Material.NETHER_QUARTZ_ORE,
					Material.GILDED_BLACKSTONE);
	        
	        if (stack == null || !material.contains(stack.getType()))
	        {
	            PrintUtils.sendMessage(event.getPlayer(),"Valid Item Not Detected in Offhand.");
	            return false;
	        }
	        
	        if (stack.getType() == Material.ANCIENT_DEBRIS) 
			{
	        	PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 500);
				if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
				{
					PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
					PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient to Smelt Ancient Debris.");
					return false;
				}
	        	
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.NETHERITE_SCRAP);
				
				return true;
			}
	        if (stack.getType() == Material.NETHER_GOLD_ORE) 
			{
	        	PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 250);
				if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
				{
					PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
					PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient to Smelt Golden Nether Ore.");
					return false;
				}
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.GOLD_INGOT);
				return true;
			}
	        if (stack.getType() == Material.NETHER_QUARTZ_ORE) 
			{
	        	PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 250);
				if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
				{
					PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
					PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient to Smelt Nether Quartz Ore.");
					return false;
				}
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.QUARTZ);
				return true;
			}
	        if (stack.getType() == Material.GILDED_BLACKSTONE) 
			{
	        	PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 250);
				if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
				{
					PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
					PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient to Smelt Gilded Blackstone.");
					return false;
				}
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().getInventory().getItemInOffHand().setType(Material.GOLD_INGOT);
				return true;
			}
		}
		return false;
	}
}
