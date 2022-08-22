package com.perceus.spellcasting2.void_spells;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellCataclysm extends BaseSpellCapsule
{

	public SpellCataclysm()
	{
		super(Material.ENDER_EYE, "§r§7§ko§r§7§lSpell: §r§fCataclysm§r§7§ko§r", "SpellCataclysm", 500, true, true, "§r§fElement: §r§3§lVOID§r§f.","§r§fSpell Type: §cOffensive§f.","§r§fSummon a super critical concentration of","§r§3§lVOID§r§f energy that expands and causes a chain of","§r§fexplosions within radius of any target block.","§r§fRange of cast: 30 meters.","§r§fRadius of first exlposion: 1 meter.","§r§fRadius of final explosion: 12 meters.","§r§fTotal explosions: 5.","§r§fMana cost: 500 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(), "Invalid Cast Method.");
			return false;
		}
		
		Random rand = new Random(); // Create a new random object

		int radiusOfCataclysm = 15;
		int TARGETBLOCKRADIUS = 30;
		
		Block target = event.getPlayer().getTargetBlock(null, TARGETBLOCKRADIUS) ;
		
		if (target.getType().equals(Material.AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(), "Invalid Target.");
			return false;
		}
		SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.PORTAL, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_SHULKER_SHOOT, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
		event.getPlayer().getWorld().createExplosion(target.getLocation(), 1);
		
		final Block Actualtarget = target ;
		
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					int offsetX = radiusOfCataclysm * -1 + rand.nextInt(radiusOfCataclysm * 2);
					int offsetZ = radiusOfCataclysm * -1 + rand.nextInt(radiusOfCataclysm * 2);

					Location targetLoc = Actualtarget.getLocation().add(new Location(event.getPlayer().getWorld(), offsetX, 0, offsetZ)); 
					targetLoc.getWorld().createExplosion(target.getLocation(), 3);
				}
			}.runTaskLater(Eden.getInstance(), 10);
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					int offsetX = radiusOfCataclysm * -1 + rand.nextInt(radiusOfCataclysm * 2);
					int offsetZ = radiusOfCataclysm * -1 + rand.nextInt(radiusOfCataclysm * 2);

					Location targetLoc = Actualtarget.getLocation().add(new Location(event.getPlayer().getWorld(), offsetX, 0, offsetZ)); 
					targetLoc.getWorld().createExplosion(target.getLocation(), 6);
				}
			}.runTaskLater(Eden.getInstance(), 20);
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					int offsetX = radiusOfCataclysm * -1 + rand.nextInt(radiusOfCataclysm * 2);
					int offsetZ = radiusOfCataclysm * -1 + rand.nextInt(radiusOfCataclysm * 2);

					Location targetLoc = Actualtarget.getLocation().add(new Location(event.getPlayer().getWorld(), offsetX, 0, offsetZ)); 
					targetLoc.getWorld().createExplosion(target.getLocation(), 8);
				}
			}.runTaskLater(Eden.getInstance(), 30);
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					int offsetX = radiusOfCataclysm * -1 + rand.nextInt(radiusOfCataclysm * 2);
					int offsetZ = radiusOfCataclysm * -1 + rand.nextInt(radiusOfCataclysm * 2);

					Location targetLoc = Actualtarget.getLocation().add(new Location(event.getPlayer().getWorld(), offsetX, 0, offsetZ)); 
					targetLoc.getWorld().createExplosion(target.getLocation(), 12);
				}
			}.runTaskLater(Eden.getInstance(), 40);
			
		return true;
	}

}
