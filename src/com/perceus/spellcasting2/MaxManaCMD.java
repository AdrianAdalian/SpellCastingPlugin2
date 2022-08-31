package com.perceus.spellcasting2;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.command.EdenCommand;
import fish.yukiemeralis.eden.command.annotations.EdenCommandHandler;
import fish.yukiemeralis.eden.module.EdenModule;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class MaxManaCMD extends EdenCommand
{
	public MaxManaCMD(EdenModule parent_module) 
	{
		super("SetMaxMana", parent_module);
	}
	
	@EdenCommandHandler(usage = "Sets Mana", description = "Sets administrator mana to internal true maximum. For debug purposes only.", argsCount = 0)
	public void edencommand_nosubcmd(CommandSender sender, String commandLabel, String[] args) 
	{
		PlayerDataMana.getPlayerData(((Player) sender).getUniqueId()).setMaxMana(2000);
		PrintUtils.sendMessage(((Player) sender),"Maximum Mana Increased. New maximum is now " + PlayerDataMana.getPlayerData(((Player) sender).getUniqueId()).getMaxMana() + ".");
		ManaInterface.updateScoreBoard(((Player) sender));
	}
}
