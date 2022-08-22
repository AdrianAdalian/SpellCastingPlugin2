package com.perceus.spellcasting2.astral_spells;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellKarma extends BaseSpellCapsule
{

	public SpellKarma()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("✩ Astral Tome: Karma ✩", "8B008B","FFFFFF",""), "SpellKarma", 100, false, "§r§fElement: §r§5A§ds§5t§dr§5α§dl§r§f.",
				"§r§fSpell Type: §cOffensive§f.",
				"§r§fA §5c§de§bl§3e§cs§4t§6i§eal §r§ftome.",
				"§r§fThis tome casts karma on any who are", 
				"§r§funfortunate enough to damage the caster.",
				"§r§fDeals §cDamage§f to target", 
				"§r§fequal to current missing health of the caster.", 
				"§r§fThe spell will fizzle if no damage accrued",
				"§r§for if the last instance of player damage", 
				"§r§fwas not dealt by a living target and melee attack.",
				"§r§fRange: 25 meters.",
				"§r§fMana cost: 100 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		
		if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Illegal Argument: This spell cannot be cast in creative mode.");
			return false;
		}
		
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		Entity target = getNearestEntityInSight(event.getPlayer(), 25);
		
		if(target == null) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		if (!(target instanceof LivingEntity)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		if (event.getPlayer().getHealth() == event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"FIZZLE! You have not taken any damage.");
			return false;
		}
		
		if (!(event.getPlayer().getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) || event.getPlayer().getLastDamageCause().getCause() == null) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"FIZZLE! Last instance of damage was not dealt by a living target/melee attack.");
			return false;
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.DRAGON_BREATH, null);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.DRAGON_BREATH, null);

		
		double currentHealth = event.getPlayer().getHealth();
		AttributeInstance maxHealth = event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH);
		
		((Damageable) target).damage(maxHealth.getValue() - currentHealth);
		SpellParticles.drawDisc(target.getLocation(), 2, 2, 20, Particle.CRIMSON_SPORE, null);
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
