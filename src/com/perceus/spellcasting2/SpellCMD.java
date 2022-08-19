package com.perceus.spellcasting2;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fish.yukiemeralis.eden.command.EdenCommand;
import fish.yukiemeralis.eden.module.EdenModule;

public class SpellCMD extends EdenCommand
{

	public SpellCMD(EdenModule parent_module) 
	{
		super("SystemCall", parent_module);
		this.addBranch("give").addBranch("<ALLSPELLS>");
	}
	
	@EdenCommandHandler(usage = "Base Spell Super", description = "Calls to server's spell registry, and summons a spell.", argsCount = 0)
	public void edencommand_nosubcmd(CommandSender sender, String commandLabel, String[] args) 
	{
		//new BaseSpellCapsuleGUI().display((HumanEntity) sender);
	}
	@EdenCommandHandler(usage = "Give Spell Item", description = "Gives the player a spell corresponding to the name.", argsCount = 2)
	public void edencommand_give(CommandSender sender, String commandLabel, String[] args) 
	{
		ItemStack spellitem = CastListener.spell_registry.get(args[1]).generate();
		
		((Player) sender).getInventory().addItem(spellitem);
	}
}