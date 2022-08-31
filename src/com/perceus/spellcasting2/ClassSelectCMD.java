package com.perceus.spellcasting2;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.perceus.spellcasting2.class_select.ClassSelectMainPageGUI;

import fish.yukiemeralis.eden.command.EdenCommand;
import fish.yukiemeralis.eden.command.annotations.EdenCommandHandler;
import fish.yukiemeralis.eden.module.EdenModule;

public class ClassSelectCMD extends EdenCommand
{
	public ClassSelectCMD(EdenModule parent_module) 
	{
		super("ClassSelect", parent_module);
	}
	
	@EdenCommandHandler(usage = "Opens class select window.", description = "Debug purposes only.", argsCount = 0)
	public void edencommand_nosubcmd(CommandSender sender, String commandLabel, String[] args) 
	{
		new ClassSelectMainPageGUI().display((Player) sender);
	}
}
