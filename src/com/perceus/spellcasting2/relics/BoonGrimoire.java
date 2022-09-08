package com.perceus.spellcasting2.relics;

import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class BoonGrimoire extends BaseSpellCapsule
{

	public BoonGrimoire()
	{
		super(Material.ENCHANTED_BOOK, "§aPri§bmor§edi§6al§f Tome: Boon Codex", "BoonGrimoire", 0, false, "§r§fElement: §aPri§bmor§edi§6al§f.",
				"§r§fAn old grimoire containing knowledge of Relics.",
				"§r§fBoons are magicical constructs containing unique",
				"§r§fpowers dependent on their respective element.",
				"§r§fAuras are §dAOE§f boons.",
				"§r§fRight-Click to be given a random Boon.",
				"§r§fThe grimoire will be incinerated upon use.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		event.setUseInteractedBlock(Event.Result.DENY);
		event.setUseItemInHand(Event.Result.DENY);
	
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
		Random random = new Random();
		List<BaseSpellCapsule> lootList = List.of(
				new BoonOfAbsoluteProtection(), new BoonOfAntiGravity(), new BoonOfDefense(), new BoonOfInsulation(), new BoonOfInteraction(), new BoonOfManaRegeneration(), new BoonOfNightVision(),
				new BoonOfRegeneration(), new BoonOfSpeed(), new BoonOfStrength(), new BoonOfTheAngels(), new BoonOfTheSeaGod(), new BoonOfWaterbreathing(), new AuraOfFire(), new AuraOfHoly(), new AuraOfStorm(),
				new AuraOfWater(), new AuraOfGeo(), new AuraOfUnholy(), new AuraOfVoid());
		ItemStack s = lootList.get(random.nextInt(lootList.size())).generate();
		SpellParticles.drawCylinder(event.getPlayer().getLocation(), 1, 50, 4, 1, Particle.ENCHANTMENT_TABLE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		event.getPlayer().getInventory().addItem(s);
		return true;
	}

}
