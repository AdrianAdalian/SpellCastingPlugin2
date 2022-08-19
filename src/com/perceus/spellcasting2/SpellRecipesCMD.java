package com.perceus.spellcasting2;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;

import com.perceus.spellcasting2.recipe_book.RecipeBookMainPageGUI;

import fish.yukiemeralis.eden.command.EdenCommand;
import fish.yukiemeralis.eden.module.EdenModule;

public class SpellRecipesCMD extends EdenCommand
{
	public SpellRecipesCMD(EdenModule parent_module) 
	{
		super("SpellRecipes", parent_module);
	}
	
	@EdenCommandHandler(usage = "Recipes For Spells", description = "Display spell recipes.", argsCount = 0)
	public void edencommand_nosubcmd(CommandSender sender, String commandLabel, String[] args) 
	{
		new RecipeBookMainPageGUI().display((HumanEntity) sender);
	}
}
