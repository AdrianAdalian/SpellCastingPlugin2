package com.perceus.spellcasting2.spellitem_spell;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class Armament_Artifice extends BaseSpellCapsule
{

	public Armament_Artifice()
	{
		super(Material.SHIELD, ChatUtils.of("Armament: Artifice", "FFE748","FFFFFF","§l§o"), "Armament_Artifice", 0, false, "§r§fElement: §r§6Geo§r§f.",
				"§r§fA simple shield infused with the element of §r§6Geo§f.",
				"§r§6Ability§r§f: Fortress.",
				"§r§fWhile the caster is blocking,",
				"§r§fgrant total immunity to all damage.",
				"§r§fAs consequence for immunity to damage,",
				"§r§fthe spell will only activate while", "§r§fusing the shield in the main hand.",
				"§r§fMana cost: 30 §r§9mana§f/second.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		
		event.setCancelled(false);
		event.setUseInteractedBlock(Event.Result.ALLOW);
		event.setUseItemInHand(Event.Result.ALLOW);
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{		
			
				new BukkitRunnable() 
				{
					@Override
					public void run() 
					{        
						if(!event.getPlayer().isOnline()) 
						{
							this.cancel();
							return;
						}
						
						if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() <= PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana())
						{
							PrintUtils.sendMessage(event.getPlayer(),"The spell of the shield has been broken. Insufficient Mana.");
							this.cancel();
							return;
						}
						
						if (!event.getPlayer().isHandRaised())
						{
							this.cancel();
							return;
						}
						
						PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 30);
						ManaInterface.updateScoreBoard(event.getPlayer());
						event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 99, true));
						event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40, 0, true));
						
					}
				}.runTaskTimer(Eden.getInstance(), 0, 20);			
				  
		}
		return true;
		
	}
}
