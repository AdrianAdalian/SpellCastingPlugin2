package com.perceus.spellcasting2.fire_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Fireball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;
//A comprehensive guide to how I create spells:
public class SpellFireball extends BaseSpellCapsule
{
	/*
	 * At the start of the class(the file), we have a couple of modifiers in which the file can be accessed by other files, in this case, public means that it can be viewed by anything no matter the location
	 * in the plugin's directory (file path).
	 */
	public SpellFireball()
	{
		super(Material.FIRE_CHARGE, "§r§7§ko§r§7§lSpell: §r§fFireball§r§7§ko§r", "SpellFireBall", 35, true, true,"§r§fElement: §r§cFire§r§f.","§r§fSpell Type: §cOffensive§f.",
				"§r§fSummons a small ball of fire that explodes on impact.","§r§fMana cost: 35 §r§9mana§r§f.");
	}
	/*
	 * The "super" is our literal item constructor. A constructor is also the "super". Simply, a constructor allows us to construct the item that we want to attach the "effects" of the spell to, apply
	 * a namespacekey, in which it is accessed by the SpellCasting class and SpellResgistry. A spell has a Material, or an Icon, a name, an Internal Access Name, a Mana Cost, 
	 * and two variables for if the first is true, it
	 * is considered to be a spellbook spell, and if false, it is not, and will be ignored as a non-switchable item. 
	 * For the second boolean (if statement), if true, it will look as though "enchanted" in game. If false, it will not have a fake enchantment effect applied to the spell item. 
	 * Next, it has lore. Lore for spells is typically assigned as follows:
	 * Element Type, Spell Type, description of spell's effects, and then mana cost. These values are separated by line, or the ",".
	 */
	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		/*
		 * Now we get to start telling our SpellCasting class what happens when we interact with the item. By default, the player must and can only right click in the air as the cast condition for a spell.
		 * In this case, it is Right Click Air; if you know how Minecraft works on the PC, you'll know there are 4 distinct interact events: Right/Left Click Air, and Right/Left Click Block.
		 */
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		/*
		 * Cast conditions are almost always defined by if statements *BEFORE* the effects of the spell are declared.
		 * Since there are no other conditions we want to check, we can now begin writing the effects of the spell.
		 */
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_FIRECHARGE_USE, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
		/*
		 * Spells usually have casting animations and sounds applied to them, in this case, we're replecating the Firecharge Use sound, 
		 * and casting a disc of Flame particles at the caster's feet. These two mechanics are implemented by a colleague of mine's Java API in which my spell casting plugin operates off of.
		 */
		event.getPlayer().launchProjectile(Fireball.class);
		/*
		 * We now see the one line of truly pertinent code: The declared purpose.
		 * This spell's main purpose is to simply summon a Fireball.
		 * The minecraft fireball has many different behind the scenes stuff associated with it, but for ease of understanding, 
		 * just know that what we are literally doing, is getting the player and then from there, getting their location and summoning the specified entity in which will be cast.  
		 */
		return true;
		/*
		 * After everything else above has, hopefully, successfully executed to our specifications, we now must return, or stop the code from running any further.
		 * Per my plugin's design, you must return a true statement for it to charge the player mana. This is assuming it worked properly, thus returning true, taking away mana, and ending the cast cycle.
		 */
	}
}
