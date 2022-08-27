package com.perceus.spellcasting2;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.command.EdenCommand;
import fish.yukiemeralis.eden.command.annotations.EdenCommandHandler;
import fish.yukiemeralis.eden.module.EdenModule;

public class ManaCMD extends EdenCommand
{
	public ManaCMD(EdenModule parent_module) 
	{
		super("GiveMana", parent_module);
	}
	
	@EdenCommandHandler(usage = "Gives Mana", description = "Gives the maximum ammount of mana to the sender of this cmd.", argsCount = 0)
	public void edencommand_nosubcmd(CommandSender sender, String commandLabel, String[] args) 
	{
		PlayerDataMana.getPlayerData(((Player) sender).getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(((Player) sender).getUniqueId()).getMaxMana());
		ManaInterface.updateScoreBoard(((Player) sender));
	}
}
