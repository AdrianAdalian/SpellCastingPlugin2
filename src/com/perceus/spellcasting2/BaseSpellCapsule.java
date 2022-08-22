package com.perceus.spellcasting2;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.component.GuiComponent;
import fish.yukiemeralis.eden.surface2.component.GuiItemStack;
import fish.yukiemeralis.eden.utils.ItemUtils;

public abstract class BaseSpellCapsule implements GuiComponent
{
	int manaCost;
	protected String displayName;
	protected String[] lore;
	protected Material material;
	protected boolean glow = false;
	protected String internalName;
	protected boolean bookspell;
	//protected double cooldown;
	
	public BaseSpellCapsule(Material material, String displayName, String internalName, int manaCost, boolean glow, String... lore) 
	{
		this(material, displayName, internalName, manaCost, false, glow, lore);
	}
	
	public BaseSpellCapsule(Material material, String displayName, String internalName, int manaCost, boolean bookspell,boolean glow, String... lore) 
	{
		this.displayName = displayName;
		this.manaCost = manaCost;
		this.material = material;
		this.lore = lore;		
		this.internalName = internalName;
		this.glow = glow;
		this.bookspell = bookspell;
		
		//this.cooldown = cooldown;
	}
	
	public int getManaCost() 
	{
		return manaCost;
	}
	
	public String getInternalName() 
	{
		return internalName;
	}
	
	public abstract boolean cast(PlayerInteractEvent event);
	
	@Override
	public GuiItemStack generate()
	{
		GuiItemStack spellitem = SimpleComponentBuilder.build(material, displayName, (event) -> 
		{
			int bookname = SpellToBookListener.getSpellbookSlot((Player)event.getWhoClicked(), "magic_spell_book");
			event.getWhoClicked().getInventory().setItem(bookname, this.generate());
			event.getWhoClicked().closeInventory();
			
		}, lore);
		
		ItemUtils.saveToNamespacedKey(spellitem, "spellname", internalName);
		if (glow == true)
		{
			ItemUtils.saveToNamespacedKey(spellitem, "itemglowflag", "true");
			ItemMeta meta = spellitem.getItemMeta();
			meta.addEnchant(Enchantment.DURABILITY, 1, glow);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			spellitem.setItemMeta(meta);
		}
		
		return spellitem;

	}

}
