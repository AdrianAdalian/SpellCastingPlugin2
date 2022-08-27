package com.perceus.spellcasting2.spellitem_spell;

import java.util.ArrayList;

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
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.ChatUtils;

public class Armament_Firebrand extends BaseSpellCapsule
{

	public Armament_Firebrand()
	{
		super(Material.NETHERITE_SWORD, ChatUtils.of("Armament: Fire Brand", "FFE748","FFFFFF","§l§o"), "Armament_Firebrand", 0, false, "§r§fElement: §r§6§lConstruct§r§f.",
				"§r§fA Netherite Sword infused with §cFire§f energy.",
				"§r§fThis weapon has two unique abilities.",
				"§r§6Ability§r§f: Smoldering Slash.",
				"§r§fRight-Click Target:",
				"§r§fSlash a target igniting them for 10 seconds.",
				"§r§fInitial §cdamage§f: 5 hearts.",
				"§r§fRange: 10 meters.",
				"§r§fMana cost: 150 §r§9mana§f.",
				"§r§6Ability§r§f: Sharpen.",
				"§r§fRight-Click Air:",
				"§r§fGrant Lv1 strength for 30 seconds.",
				"§r§fMana cost: 100 §r§9mana§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{

		event.setCancelled(false);
		event.setUseInteractedBlock(Event.Result.ALLOW);
		event.setUseItemInHand(Event.Result.ALLOW);
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			Entity target = getNearestEntityInSight(event.getPlayer(), 10);
			
			if (target==null) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 100);
				ManaInterface.updateScoreBoard(event.getPlayer());
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.LAVA, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 0, true));
				return true;
			}
			
			if (target instanceof LivingEntity) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 150);
				ManaInterface.updateScoreBoard(event.getPlayer());
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.LAVA, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.FLAME, null);
				((LivingEntity) target).damage(10);
				target.setFireTicks(200);
				return true;
			}
		}
		
		return true;
	}
	private Entity getNearestEntityInSight(Player player, int range) 
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
