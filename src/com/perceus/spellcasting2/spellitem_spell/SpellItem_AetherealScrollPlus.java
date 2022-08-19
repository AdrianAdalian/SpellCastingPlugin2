package com.perceus.spellcasting2.spellitem_spell;

import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellItem_AetherealScrollPlus extends BaseSpellCapsule
{

	public SpellItem_AetherealScrollPlus()
	{
		super(Material.PAPER, "§r§7§ko§r§7§lMagical Item: §r§bAethereal Scroll§r§e+§r§7§ko§r", "SpellItem_AetherealScrollPlus", 0, true, "§r§fElement: §r§b§l§oAethereal§r§f.","§r§fA scroll containing highly unstable §r§b§l§oAethereal§r§f energy.","§r§fA spell is haphazardly", "§r§fwritten on this scroll that allows","§r§fthe caster to randomly enchant normal books.","§r§fEnchantment level reflects the maximum value possible.","§r§fThe scroll will be incinerated on use.");
		// TODO Auto-generated constructor stub
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
		
		List<Material> material = List.of(Material.BOOK);
		
		ItemStack offhand = event.getPlayer().getInventory().getItemInOffHand();
		Random random = new Random();
		Enchantment randomEnchantment = Enchantment.values()[random.nextInt(Enchantment.values().length)]; // Generate a random enchantment
		
		if (stack == null || !material.contains(stack.getType()))
	    {
			PrintUtils.sendMessage(event.getPlayer(),"Valid Item Not Detected in Offhand.");
            return false;
	    }		
		
		if (offhand.getAmount() > 1) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"You can't enchant more than one book at a time.");
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
		
		offhand.setType(Material.ENCHANTED_BOOK);
		SpellParticles.drawCylinder(event.getPlayer().getLocation(), 1, 50, 4, 1, Particle.ENCHANTMENT_TABLE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
		EnchantmentStorageMeta bookmeta = (EnchantmentStorageMeta) offhand.getItemMeta();
		bookmeta.addStoredEnchant(randomEnchantment, randomEnchantment.getMaxLevel(), true);
		offhand.setItemMeta(bookmeta);
		
		return true;
	}

}
