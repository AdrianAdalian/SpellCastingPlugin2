package com.perceus.spellcasting2.accounts;

import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class ObfuscatedSpellPage extends BaseSpellCapsule
{

	public ObfuscatedSpellPage()
	{
		super(Material.PAPER, "§r§a§l§ko§r§fObfuscated Spell Page§r§a§l§ko§r", "ObfuscatedSpellpage", 0, true, "§r§fThis obfuscated Spell Page contains an unknown spell.","§r§fPerhaps studying the magic written here could","§r§fallow insight on what spell it contains.","§r§fRight-Click will consume the item and","§r§fgive one random Spellbook Spell Page.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			event.setCancelled(true);
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) 
		{
			event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
		}
		else
		{
			event.getPlayer().getInventory().getItemInMainHand().setAmount(0);
		}
		
		Random random = new Random();
		
		List<BaseSpellCapsule> lootList = List.of(
				new KBSpellAccelerateLife(), new KBSpellAccolades(), new KBSpellAngelicFlight(), new KBSpellAntiGravity(), new KBSpellAntimatter(), new KBSpellArcaneBolt(), new KBSpellBanishGreaterEvil(),
				new KBSpellBarrier(), new KBSpellBlink(), new KBSpellBoulder(), new KBSpellCataclysm(), new KBSpellCombustion(), new KBSpellConduitEther(), new KBSpellCrush(), new KBSpellCrystalize(), 
				new KBSpellCure(),new KBSpellDebilitate(), new KBSpellDemonicReflexes(), new KBSpellDemonSight(), new KBSpellDoom(), new KBSpellDracoMorph(), 
				new KBSpellEarthquake(), new KBSpellEmbers(), new KBSpellDarkHarvest(),new KBSpellEmitDamagingForce(), new KBSpellEmitForce(), new KBSpellExpellLiving(), new KBSpellExpellUndead(), 
				new KBSpellExplosiveBolt(), new KBSpellExpulsion(), new KBSpellExtricate(), new KBSpellFireball(),new KBSpellFishScales(),
				new KBSpellFlamethrower(), new KBSpellGaleForce(), new KBSpellGalvanicNeedle(), new KBSpellGate(), new KBSpellGeoMorph(), new KBSpellGills(), new KBSpellGravitas(), 
				new KBSpellGust(), new KBSpellHealingHands(),new KBSpellHellFire(), new KBSpellIgnite(), new KBSpellIgnitionDrive(), 
				new KBSpellInsulate(), new KBSpellJudgement(), new KBSpellKindleFlame(), new KBSpellLawOfRegression(), new KBSpellLifeSteal(),
				new KBSpellMageMorph(), new KBSpellManaBurn(), new KBSpellMendingWater(), new KBSpellMetalMorph(), new KBSpellMeteor(), 
				new KBSpellNaturesGift(), new KBSpellNaturesWrath(), new KBSpellOverclockProtocol(),
				new KBSpellPenance(), new KBSpellPoisonCloud(), new KBSpellPoisonGas(), new KBSpellPolarize(), new KBSpellPull(), 
				new KBSpellRagingCurrent(), new KBSpellRaiseDead(), new KBSpellReapSouls(), new KBSpellReconstitute(), new KBSpellRegenerate(),
				new KBSpellRift(), new KBSpellRipTide(), new KBSpellRot(), new KBSpellSandBlast(), new KBSpellSapEther(), new KBSpellSatiate(), 
				new KBSpellSaturate(), new KBSpellSmite(), new KBSpellSmokescreen(), new KBSpellSoak(),
				new KBSpellSoothingCurrent(), new KBSpellSplash(), new KBSpellStaticCharge(), new KBSpellSuffocate(), new KBSpellTailWind(), 
				new KBSpellTeleport(), new KBSpellTerraform(), new KBSpellThunderStorm(), new KBSpellThunderStrike(),
				new KBSpellTidalForce(), new KBSpellToxicGas(), new KBSpellUberCharge(), new KBSpellUndeadEffigy(), new KBSpellVaporize(), 
				new KBSpellVectorPlate(), new KBSpellVoidBurst(), new KBSpellVoidGate(), new KBSpellWaveWake()
				);
		
		ItemStack s = lootList.get(random.nextInt(lootList.size())).generate();
		SpellParticles.drawCylinder(event.getPlayer().getLocation(), 1, 50, 4, 1, Particle.ENCHANTMENT_TABLE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		event.getPlayer().getInventory().addItem(s);
		
		return true;
	}

}
