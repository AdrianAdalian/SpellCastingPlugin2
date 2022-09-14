package com.perceus.spellcasting2;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.perceus.spellcasting2.gui.BaseSpellCapsuleGUI;
import com.perceus.spellcasting2.recipe_book.RecipeBookMainPageGUI;
import com.perceus.spellcasting2.spellitem_recipe.MagicSpellBook_Recipe;

import fish.yukiemeralis.eden.utils.ItemUtils;

public class SpellToBookListener implements Listener
{
	//This list is to blacklist consumable spells, wands, and tomes from being allowed to transform into a book.
	//If there's a spell that should apply to these conditions, register it here.
	
	private static List<String> CONSUMABLE_BLACKLIST = List.of(
			"SpellHeal","SpellGuardianAngel","SpellRadiance","SpellDrainingAura","SpellStormSurge", "SpellWildBolt","SpellNullify", "SpellTransmute", "SpellInscribeLuck",
			"SpellOverheat","SpellChaosFireball","SpellSolarFlare", "SpellChaosMeteor","SpellSolGate","SpellPoseidonsGift","SpellNullPointConfiguration",
			"SpellVoidContainment","SpellEtherContinuity","SpellVoidShift","SpellPrimordialShift","SpellOverrideClimate","SpellItem_Fireball",
			"SpellItem_ManaCrystal","SpellItem_ProtectivePaste","SpellItem_HealthCrystal","SpellItem_SmokeBomb","SpellItem_Updraft","MagicWeaponWandOfFire",
			"MagicWeaponWandOfGeo","MagicWeaponWandOfUnholy","MagicWeaponWandOfHoly","MagicWeaponWandOfWater","MagicWeaponWandOfVoid","MagicWeaponWandOfStorm",
			"SpellBloodletting","SpellEtherBreak","SpellCurse","SpellHex","SpellDeath","SpellTillEarth","SpellProlongedFlight","SpellItem_XpCrystal","SpellLawOfProgression",
			"SpellFortifyDurability","SpellFortifyMending","SpellFortifySharpness","SpellFortifyEfficiency","SpellFortifySilkTouch","SpellFortifyFortune",
			"SpellFortifyLure","SpellFortifyFishingLuck","SpellFortifyProtection","SpellCleanse","SpellFortifyFireProtection","SpellFortifyBlastProtection",
			"SpellFortifyProjectileProtection","SpellFortifyFeatherFalling","SpellFortifyThorns","SpellFortifyRespiration","SpellFortifyAquaAffinity","SpellFortifySwiftSneak",
			"SpellFortifyDepthStrider","SpellFortifyFrostWalker","SpellFortifySoulSpeed","SpellFortifySmite","SpellFortifyBaneOfArthropods","SpellFortifyLooting",
			"SpellFortifySweepingEdge","SpellFortifyKnockback","SpellFortifyFireAspect","SpellFortifyPower","SpellFortifyPunch","SpellFortifyInfinity",
			"SpellFortifyFlame","SpellFortifyMultishot","SpellFortifyQuickCharge","SpellFortifyPiercing","SpellFortifyChanneling","SpellFortifyImpaling",
			"SpellFortifyLoyalty","SpellFortifyRipTide","SpellAmeliorate","SpellAetherealOverride","SpellAetherealOverridePlus","SpellItem_AetherealScroll",
			"SpellItem_AetherealScrollPlus","SpellItem_BloodCrystal","SpellItem_BloodCrystalPlus","SpellCreateCow","SpellCreateChicken","SpellCreatePig",
			"SpellCreateSheep","SpellCreateRabbit","SpellCreateZombie","SpellSandstorm","SpellPacify","SpellDisarm",
			"KBSpellKindleFlame","KBSpellEmbers","KBSpellIgnite","KBSpellFireBall","KBSpellExpulsion","KBSpellOverclockProtocol",
			"KBSpellSmokescreen","KBSpellFlamethrower","KBSpellInsulate","KBSpellMeteor","KBSpellCombustion","KBSpellHellFire","KBSpellIgnitionDrive",
			"KBSpellManaBurn","KBSpellReconstitute","KBSpellTidalForce","KBSpellFishScales","KBSpellSoak",
			"KBSpellSaturate","KBSpellWaveWake","KBSpellRagingCurrent","KBSpellGills","KBSpellConduitEther","KBSpellSuffocate",
			"KBSpellMendingWater","KBSpellCrystalize","KBSpellSplash","KBSpellRipTide",
			"SpellUndeadRecall","SpellLivingRecall","SpellComet","SpellLavaBomb","SpellEruption","KBSpellTerraform",
			"KBSpellGeoMorph","KBSpellSandBlast","KBSpellGravitas","KBSpellMageMorph","KBSpellPoisonGas","KBSpellMetalMorph","KBSpellBoulder","KBSpellToxicGas",
			"KBSpellPoisonCloud","KBSpellDracoMorph","KBSpellNaturesGift","KBSpellNaturesWrath","KBSpellEarthquake","KBSpellGust","KBSpellBlink",
			"KBSpellGaleForce","KBSpellStaticCharge","KBSpellSoothingCurrent","KBSpellSmite","KBSpellGalvanicNeedle","KBSpellUberCharge","KBSpellThunderStorm",
			"KBSpellThunderStrike","KBSpellExplosiveBolt","KBSpellVaporize","KBSpellArcaneBolt","KBSpellTailWind",
			"KBSpellVoidBurst","KBSpellPull","KBSpellVectorPlate","KBSpellTeleport","KBSpellAntiGravity","KBSpellRift","KBSpellAntimatter","KBSpellCrush",
			"KBSpellPolarize","KBSpellAccelerateLife","KBSpellCataclysm","KBSpellGate","KBSpellVoidGate",
			"KBSpellBanishGreaterEvil","KBSpellLifeSteal","KBSpellDemonicReflexes","KBSpellDemonSight","KBSpellEmitDamagingForce","KBSpellDoom","KBSpellLightShift","KBSpellReapSouls",
			"KBSpellExpellLiving","KBSpellRot","KBSpellDebilitate","KBSpellDarkHarvest","KBSpellUndeadEffigy","KBSpellRaiseDead","KBSpellSapEther","KBSpellEmitForce",
			"KBSpellMagicBolt","KBSpellHealingHands","KBSpellBarrier","KBSpellCure","KBSpellSatiate","KBSpellLawOfRegression","KBSpellExpellUndead","KBSpellAngelicFlight",
			"KBSpellRegenerate","KBSpellAccolades","KBSpellExtricate","KBSpellPenance","KBSpellJudgement","SpellResetTome","SpellUnlockTome",
			"SpellRecallAnchor","SpellCreateAllay","SpellCreateAxolotl","SpellCreateBat","SpellCreateBee","SpellCreateDolphin","SpellCreateDonkey","SpellCreateFox","SpellCreateFrog",
			"SpellCreateHorse","SpellCreateGoat","SpellCreateLlama","SpellCreateMooshroom","SpellCreateMule","SpellCreateOcelot","SpellCreatePanda","SpellCreateParrot","SpellCreatePolarBear",
			"SpellCreateSquid","SpellCreateTurtle","SpellCreateWolf","SpellCreateCaveSpider","SpellCreateCreeper","SpellCreateDrowned","SpellCreateEnderman","SpellCreateEvoker",
			"SpellCreateGlowSquid","SpellCreateGhast","SpellCreateGuardian","SpellCreateHoglin","SpellCreateHusk","SpellCreateMagmaCube","SpellCreatePhantom","SpellCreatePiglinBrute",
			"SpellCreatePiglin,","SpellCreateRavager","SpellCreatePillager","SpellCreateSkeleton","SpellCreateSlime","SpellCreateSpider","SpellCreateStray","SpellCreateStrider",
			"SpellCreateBlaze","SpellCreateVex","SpellCreateVindicator","SpellCreateWitch","SpellCreateWitherSkeleton","SpellCreateZoglin",
			"SpellCreateZombifiedPiglin","SpellCreateIronGolem","SpellCreateSnowGolem","SpellCreateWither","SpellCreateElderGuardian","SpellCreateWarden","SpellFrostBite","SpellBlizzard","SpellIceAge",
			"SpellTelekinesis","SpellSingularity","SpellRestoration","SpellEtherTransference","SpellElectrocute","SpellReapAndSew","SpellInferno","SpellLunarBolt","SpellLunarEclipse","SpellSolarEclipse",
			"SpellSuspendedMatter","SpellSolarBolt","SpellFertility","SpellProtectOther","SpellHealingAura","EtherCrystal","CrackedEtherCrystal","SpellUrgentTeleport","SpellSuspendGravity",
			"SpellCreateItem","SpellMoonBeam","SpellMoonBlast","SpellKarma","SpellEquilibrium","SpellUndyingSoul","MagicWeapon_ElementalStaff","MagicWeapon_SpiritualStaff","MagicTool_PickaxeOfGeo",
			"Armament_Repentance","Armament_Artifice","BoonOfStrength","Armament_Firebrand","Armament_War","MagicTool_ScytheOfUnholy",
			"MagicTool_ShovelOfGeo","LivingArmor_DragonScaleChestplate","LivingArmor_DragonScaleHelmet","LivingArmor_DragonScaleLeggings","LivingArmor_DragonScaleBoots","SpellRemedy","SpellEtherEquilibrium",
			"SpellPurify","SpellTornado","SpellTsunami","ObfuscatedSpellpage","BoonOfRegeneration","BoonOfManaRegeneration","BoonOfWaterBreathing","BoonOfTheSeaGod","BoonOfAbsoluteProtection","BoonOfTheAngels",
			"BoonOfDefense","BoonOfInteraction","BoonOfSpeed","BoonOfAntiGravity","BoonOfInsulation",
			"BoonOfNightVision","BoonGrimoire","AuraOfFire","AuraOfHoly","AuraOfStorm","AuraOfWater","AuraOfGeo","AuraOfUnholy","AuraOfVoid","SpellBestowLife","DevSpellBanish","SpellSkullOfNight",
			"SpellToxicSludge","SpellDesintegrationAmpule","SpellUrgentSupport","SpellCrimsonFury","SpellCrimsonVigor","SpellEnrage","SpellWrath","SpellAstralProjection","SpellVectorManipulation",
			"SpellAetherBreak",
			"SpellTimeDilation");
	
	
	/**
	 * Gets the item slot a spellbook resides in, inside a given player's inventory.
	 * @param target The player to search.
	 * @param key The expected book NSK
	 * @return The item slot a spellbook resides in. Returns <code>Integer.MIN_VALUE</code> if a book is not found.
	 */
	public static int getSpellbookSlot(Player target, String key)
	{
	    Inventory inv = target.getInventory();

	    for (int i = 0; i < target.getInventory().getSize(); i++)
	    {
	        ItemStack item = inv.getItem(i);
	        if (item == null)
	        {
	            continue;
	        }            

	        if (!ItemUtils.hasNamespacedKey(item, key))
	        {
	            continue;
	        }

	        return i;
	    }
	    
	    return Integer.MIN_VALUE;
	}
	@EventHandler
	public void onClick(InventoryClickEvent event)
	{
		
		if (event.getClickedInventory() == null) 
		{
			return;
		}
		
		if (!event.getClickedInventory().getType().equals(InventoryType.PLAYER))
		{
		    return;
		}
		
		ItemStack stack = event.getView().getItem(event.getRawSlot());
		
		if (stack == null || stack.getType().equals(Material.AIR)) 
		{
			return;
		}
		
		if(ItemUtils.hasNamespacedKey(stack,"spellname"))
		{
		
			if (CONSUMABLE_BLACKLIST.contains(ItemUtils.readFromNamespacedKey(stack, "spellname")))
			{
			  return;
			}
			
			event.setCancelled(true);	
			int slot = event.getView().convertSlot(event.getRawSlot());
			
			event.getWhoClicked().getInventory().setItem(slot, MagicSpellBook_Recipe.getFinal_item().clone());
			event.getWhoClicked().getOpenInventory().setCursor(new ItemStack(Material.AIR));
			event.getWhoClicked().closeInventory();
		}
	}
	@EventHandler
	public void Interact(PlayerInteractEvent event) 
	{
		ItemStack held;
		held = event.getPlayer().getInventory().getItem(EquipmentSlot.HAND) ;
		
		if (event.getHand() == null) 
		{
			return;
		}
		
		if (event.getHand().equals(EquipmentSlot.OFF_HAND))
		{
			return;
		}
		if(held==null)
		{
			return;
		}
		if(held.getType().equals(Material.AIR)) 
		{
			return;
		}
		if (ItemUtils.hasNamespacedKey(held, "magic_spell_book"))
		{
			if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
			{
				return;
			}
			event.setCancelled(true);
			BaseSpellCapsuleGUI gui = new BaseSpellCapsuleGUI();
			gui.display(event.getPlayer());
		}
		if (ItemUtils.hasNamespacedKey(held, "sc2_recipe_book"))
		{
			if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
			{
				event.setCancelled(true);
				return;
			}
			event.setCancelled(true);
			new RecipeBookMainPageGUI().display(event.getPlayer());
		}
	}
}
