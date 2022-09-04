package com.perceus.spellcasting2;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;

import com.perceus.spellcasting2.accounts.*;
import com.perceus.spellcasting2.astral_spells.SpellEquilibrium;
import com.perceus.spellcasting2.astral_spells.SpellEtherEquilibrium;
import com.perceus.spellcasting2.astral_spells.SpellKarma;
import com.perceus.spellcasting2.astral_spells.SpellNullifyGravity;
import com.perceus.spellcasting2.astral_spells.SpellSuspendedMatter;
import com.perceus.spellcasting2.astral_spells.SpellUrgentTeleport;
import com.perceus.spellcasting2.fire_spells.SpellChaosFireball;
import com.perceus.spellcasting2.fire_spells.SpellChaosMeteor;
import com.perceus.spellcasting2.fire_spells.SpellEruption;
import com.perceus.spellcasting2.fire_spells.SpellLavaBomb;
import com.perceus.spellcasting2.fire_spells.SpellOverheat;
import com.perceus.spellcasting2.geo_spells.SpellInscribeLuck;
import com.perceus.spellcasting2.geo_spells.SpellNullify;
import com.perceus.spellcasting2.geo_spells.SpellSandstorm;
import com.perceus.spellcasting2.geo_spells.SpellTillEarth;
import com.perceus.spellcasting2.geo_spells.SpellTransmute;
import com.perceus.spellcasting2.holy_spells.SpellAmeliorate;
import com.perceus.spellcasting2.holy_spells.SpellDisarm;
import com.perceus.spellcasting2.holy_spells.SpellEtherTransference;
import com.perceus.spellcasting2.holy_spells.SpellFertility;
import com.perceus.spellcasting2.holy_spells.SpellGuardianAngel;
import com.perceus.spellcasting2.holy_spells.SpellHeal;
import com.perceus.spellcasting2.holy_spells.SpellHealingAura;
import com.perceus.spellcasting2.holy_spells.SpellLivingRecall;
import com.perceus.spellcasting2.holy_spells.SpellPacify;
import com.perceus.spellcasting2.holy_spells.SpellProlongedFlight;
import com.perceus.spellcasting2.holy_spells.SpellProtectOther;
import com.perceus.spellcasting2.holy_spells.SpellRadiance;
import com.perceus.spellcasting2.holy_spells.SpellRemedy;
import com.perceus.spellcasting2.holy_spells.SpellRestoration;
import com.perceus.spellcasting2.lunar_spells.SpellLunarBolt;
import com.perceus.spellcasting2.lunar_spells.SpellLunarEclipse;
import com.perceus.spellcasting2.lunar_spells.SpellMoonBeam;
import com.perceus.spellcasting2.lunar_spells.SpellMoonBlast;
import com.perceus.spellcasting2.relics.BoonGrimoire;
import com.perceus.spellcasting2.solar_spells.SpellComet;
import com.perceus.spellcasting2.solar_spells.SpellInferno;
import com.perceus.spellcasting2.solar_spells.SpellSolGate;
import com.perceus.spellcasting2.solar_spells.SpellSolarBolt;
import com.perceus.spellcasting2.solar_spells.SpellSolarEclipse;
import com.perceus.spellcasting2.solar_spells.SpellSolarFlare;
import com.perceus.spellcasting2.spellitem_spell.MagicWeapon_WandOfVoid;
import com.perceus.spellcasting2.spellitem_spell.SpellItem_AetherealScroll;
import com.perceus.spellcasting2.spellitem_spell.SpellItem_AetherealScrollPlus;
import com.perceus.spellcasting2.spellitem_spell.SpellItem_BloodCrystal;
import com.perceus.spellcasting2.spellitem_spell.SpellItem_BloodCrystalPlus;
import com.perceus.spellcasting2.spellitem_spell.SpellItem_EtherCrystal;
import com.perceus.spellcasting2.spellitem_spell.SpellItem_HealthCrystal;
import com.perceus.spellcasting2.spellitem_spell.SpellItem_ManaCrystal;
import com.perceus.spellcasting2.spellitem_spell.SpellItem_XpCrystal;
import com.perceus.spellcasting2.storm_spells.SpellElectrocute;
import com.perceus.spellcasting2.storm_spells.SpellStormSurge;
import com.perceus.spellcasting2.storm_spells.SpellTornado;
import com.perceus.spellcasting2.storm_spells.SpellWildBolt;
import com.perceus.spellcasting2.unholy_spells.SpellDrainingAura;
import com.perceus.spellcasting2.unholy_spells.SpellReapAndSew;
import com.perceus.spellcasting2.unholy_spells.SpellUndeadRecall;
import com.perceus.spellcasting2.unholy_spells.SpellUndyingSoul;
import com.perceus.spellcasting2.void_spells.SpellEtherContinuity;
import com.perceus.spellcasting2.void_spells.SpellNullPointConfiguration;
import com.perceus.spellcasting2.void_spells.SpellOverrideClimate;
import com.perceus.spellcasting2.void_spells.SpellPrimordialShift;
import com.perceus.spellcasting2.void_spells.SpellRecallAnchor;
import com.perceus.spellcasting2.void_spells.SpellSingularity;
import com.perceus.spellcasting2.void_spells.SpellTelekinesis;
import com.perceus.spellcasting2.void_spells.SpellVoidContainment;
import com.perceus.spellcasting2.void_spells.SpellVoidShift;
import com.perceus.spellcasting2.water_spells.SpellBlizzard;
import com.perceus.spellcasting2.water_spells.SpellFrostBite;
import com.perceus.spellcasting2.water_spells.SpellIceAge;
import com.perceus.spellcasting2.water_spells.SpellPoseidonsGift;
import com.perceus.spellcasting2.water_spells.SpellTsunami;

public class ChestLootTablesListener implements Listener
{
	
	private static final Random random = new Random();

	@EventHandler
	public void chestOpen(LootGenerateEvent event) 
	{
		
		List<BaseSpellCapsule> lootList = List.of(new SpellChaosFireball(),
				new SpellOverheat(),new SpellChaosMeteor(),
				new SpellLavaBomb(),new SpellEruption(),new SpellHeal(),new SpellGuardianAngel(),
				new SpellRadiance(),new SpellProlongedFlight(),new SpellAmeliorate(),new SpellPacify(),
				new SpellDisarm(),new SpellLivingRecall(),new SpellRestoration(),new SpellEtherTransference(),
				new SpellPrimordialShift(),new SpellOverrideClimate(),new SpellVoidShift(),new SpellNullPointConfiguration(),
				new SpellVoidContainment(),new SpellEtherContinuity(),new SpellRecallAnchor(),new SpellTelekinesis(),
				new SpellSingularity(),new SpellNullify(),new SpellInscribeLuck(),new SpellSandstorm(),
				new SpellTransmute(),new SpellTillEarth(),new SpellPoseidonsGift(),new SpellFrostBite(),
				new SpellBlizzard(),new SpellIceAge(),new SpellDrainingAura(),new SpellUndeadRecall(),
				new SpellReapAndSew(),new SpellStormSurge(),new SpellWildBolt(),new SpellElectrocute(),
				new SpellItem_ManaCrystal(),new SpellItem_HealthCrystal(),new SpellItem_XpCrystal(),new SpellItem_AetherealScroll(),
				new SpellItem_AetherealScrollPlus(),new SpellItem_BloodCrystal(),new KBSpellKindleFlame(),new KBSpellEmbers(),
				new KBSpellIgnite(),new KBSpellFireball(),new KBSpellExpulsion(),new KBSpellOverclockProtocol(),
				new KBSpellSmokescreen(),new KBSpellFlamethrower(),new KBSpellInsulate(),new KBSpellMeteor(),
				new KBSpellCombustion(),new KBSpellHellFire(),new KBSpellIgnitionDrive(),new KBSpellManaBurn(),
				new KBSpellRipTide(),new KBSpellReconstitute(),new KBSpellTidalForce(),new KBSpellFishScales(),
				new KBSpellSoak(),new KBSpellSaturate(),new KBSpellWaveWake(),new KBSpellRagingCurrent(),
				new KBSpellGills(),new KBSpellConduitEther(),new KBSpellSuffocate(),new KBSpellMendingWater(),
				new KBSpellCrystalize(),new KBSpellSplash(),new KBSpellTerraform(),new KBSpellGeoMorph(),
				new KBSpellSandBlast(),new KBSpellGravitas(),new KBSpellMageMorph(),new KBSpellPoisonGas(),
				new KBSpellMetalMorph(),new KBSpellBoulder(),new KBSpellToxicGas(),
				new KBSpellPoisonCloud(),new KBSpellDracoMorph(),new KBSpellNaturesGift(),new KBSpellNaturesWrath(),
				new KBSpellEarthquake(),new KBSpellGust(),new KBSpellBlink(),new KBSpellGaleForce(),
				new KBSpellStaticCharge(),new KBSpellSoothingCurrent(),new KBSpellSmite(),new KBSpellGalvanicNeedle(),
				new KBSpellUberCharge(),new KBSpellThunderStorm(),new KBSpellThunderStrike(),new KBSpellExplosiveBolt(),
				new KBSpellVaporize(),new KBSpellArcaneBolt(),new KBSpellTailWind(),new KBSpellVoidBurst(),
				new KBSpellPull(),new KBSpellVectorPlate(),new KBSpellTeleport(),new KBSpellAntiGravity(),
				new KBSpellRift(),new KBSpellAntimatter(),new KBSpellCrush(),new KBSpellPolarize(),
				new KBSpellAccelerateLife(),new KBSpellCataclysm(),new KBSpellGate(),new KBSpellVoidGate(),
				new KBSpellBanishGreaterEvil(),new KBSpellLifeSteal(),new KBSpellDemonicReflexes(),new KBSpellDemonSight(),
				new KBSpellEmitDamagingForce(),new KBSpellDoom(),new KBSpellLightShift(),new KBSpellReapSouls(),
				new KBSpellExpellLiving(),new KBSpellRot(),new KBSpellDebilitate(),new KBSpellDarkHarvest(),
				new KBSpellUndeadEffigy(),new KBSpellRaiseDead(),new KBSpellSapEther(),new KBSpellEmitForce(),
				new KBSpellMagicBolt(),new KBSpellHealingHands(),new KBSpellBarrier(),new KBSpellCure(),
				new KBSpellSatiate(),new KBSpellLawOfRegression(),new KBSpellExpellUndead(),new KBSpellAngelicFlight(),
				new KBSpellRegenerate(),new KBSpellAccolades(),new KBSpellExtricate(),new KBSpellPenance(),
				new KBSpellJudgement(), new SpellFertility(), new SpellProtectOther(), new SpellHealingAura(), 
				new SpellItem_EtherCrystal(), new SpellUndyingSoul(), new SpellRemedy(), new SpellTsunami(), new SpellTornado(), new ObfuscatedSpellPage(), new BoonGrimoire()
				);
		
		List<BaseSpellCapsule> lootList2 = List.of(new SpellLunarBolt(), new SpellLunarEclipse(), new SpellInferno(), new SpellItem_BloodCrystalPlus(), new MagicWeapon_WandOfVoid(),
				new SpellSolarEclipse(), new SpellComet(), new SpellSolarFlare(), new SpellSolGate(), new SpellSuspendedMatter(), new SpellSolarBolt(), new SpellItem_EtherCrystal(), new SpellUrgentTeleport(),
				new SpellNullifyGravity(), new SpellMoonBeam(), new SpellMoonBlast(), new SpellKarma(), new SpellEquilibrium(), new SpellEtherEquilibrium(), new BoonGrimoire());
		
		if (event.getWorld() == Bukkit.getWorld("world") && random.nextDouble() < 1.0)
		{	
			
			ItemStack s = lootList.get(random.nextInt(lootList.size())).generate();
			
			if (event.getLoot().size() == 28)
			{	
				event.getLoot().add(random.nextInt(), s);
			}
			else
			{
				event.getLoot().add(s);
			}
		}
		
		if (event.getWorld() == Bukkit.getWorld("world_nether") && random.nextDouble() < 1.0) 
		{
			ItemStack s = lootList.get(random.nextInt(lootList.size())).generate();
			ItemStack s2 = lootList.get(random.nextInt(lootList.size())).generate();
			ItemStack s3 = lootList2.get(random.nextInt(lootList2.size())).generate();
			
			if (event.getLoot().size() == 28)
			{	
				event.getLoot().add(random.nextInt(), s);
				event.getLoot().add(random.nextInt(), s2);
				event.getLoot().add(random.nextInt(), s3);
			}
			else
			{
				event.getLoot().add(s);
				event.getLoot().add(s2);
				event.getLoot().add(s3);
			}
		}
		
		
		if (event.getWorld() == Bukkit.getWorld("world_the_end") && random.nextDouble() < 1.0) 
		{				
			ItemStack s = lootList2.get(random.nextInt(lootList2.size())).generate();
			ItemStack s2 = lootList2.get(random.nextInt(lootList2.size())).generate();
			ItemStack s3 = lootList2.get(random.nextInt(lootList2.size())).generate();
			
			if (event.getLoot().size() == 28)
			{	
				event.getLoot().add(random.nextInt(), s);
				event.getLoot().add(random.nextInt(), s2);
				event.getLoot().add(random.nextInt(), s3);

			}
			else
			{
				event.getLoot().add(s);	
				event.getLoot().add(s2);
				event.getLoot().add(s3);
			}
			
		}
	}
}