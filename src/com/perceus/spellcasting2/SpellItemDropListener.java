package com.perceus.spellcasting2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.perceus.spellcasting2.accounts.*;
import com.perceus.spellcasting2.aethereal_spells.SpellAetherealOverride;
import com.perceus.spellcasting2.aethereal_spells.SpellAetherealOverridePlus;
import com.perceus.spellcasting2.aethereal_spells.SpellCleanse;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyAquaAffinity;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyBaneOfArthropods;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyBlastProtection;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyChanneling;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyDepthStrider;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyDurability;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyEfficiency;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyFeatherFalling;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyFireAspect;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyFireProtection;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyFishingLuck;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyFlame;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyFortune;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyFrostWalker;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyImpaling;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyInfinity;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyKnockback;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyLooting;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyLoyalty;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyLure;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyMending;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyMultishot;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyPiercing;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyPower;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyProjectileProtection;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyProtection;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyPunch;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyQuickCharge;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyRespiration;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyRipTide;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifySharpness;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifySilkTouch;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifySmite;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifySoulSpeed;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifySweepingEdge;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifySwiftSneak;
import com.perceus.spellcasting2.aethereal_spells.SpellFortifyThorns;
import com.perceus.spellcasting2.aethereal_spells.SpellPurify;
import com.perceus.spellcasting2.ancient_spells.SpellCreateAxolotl;
import com.perceus.spellcasting2.ancient_spells.SpellCreateBat;
import com.perceus.spellcasting2.ancient_spells.SpellCreateBee;
import com.perceus.spellcasting2.ancient_spells.SpellCreateBlaze;
import com.perceus.spellcasting2.ancient_spells.SpellCreateCaveSpider;
import com.perceus.spellcasting2.ancient_spells.SpellCreateChicken;
import com.perceus.spellcasting2.ancient_spells.SpellCreateCow;
import com.perceus.spellcasting2.ancient_spells.SpellCreateCreeper;
import com.perceus.spellcasting2.ancient_spells.SpellCreateDolphin;
import com.perceus.spellcasting2.ancient_spells.SpellCreateDonkey;
import com.perceus.spellcasting2.ancient_spells.SpellCreateDrowned;
import com.perceus.spellcasting2.ancient_spells.SpellCreateEnderman;
import com.perceus.spellcasting2.ancient_spells.SpellCreateEvoker;
import com.perceus.spellcasting2.ancient_spells.SpellCreateFox;
import com.perceus.spellcasting2.ancient_spells.SpellCreateFrog;
import com.perceus.spellcasting2.ancient_spells.SpellCreateGhast;
import com.perceus.spellcasting2.ancient_spells.SpellCreateGlowSquid;
import com.perceus.spellcasting2.ancient_spells.SpellCreateGoat;
import com.perceus.spellcasting2.ancient_spells.SpellCreateGuardian;
import com.perceus.spellcasting2.ancient_spells.SpellCreateHoglin;
import com.perceus.spellcasting2.ancient_spells.SpellCreateHorse;
import com.perceus.spellcasting2.ancient_spells.SpellCreateHusk;
import com.perceus.spellcasting2.ancient_spells.SpellCreateIronGolem;
import com.perceus.spellcasting2.ancient_spells.SpellCreateLlama;
import com.perceus.spellcasting2.ancient_spells.SpellCreateMagmaCube;
import com.perceus.spellcasting2.ancient_spells.SpellCreateMooshroom;
import com.perceus.spellcasting2.ancient_spells.SpellCreateMule;
import com.perceus.spellcasting2.ancient_spells.SpellCreateOcelot;
import com.perceus.spellcasting2.ancient_spells.SpellCreatePanda;
import com.perceus.spellcasting2.ancient_spells.SpellCreateParrot;
import com.perceus.spellcasting2.ancient_spells.SpellCreatePhantom;
import com.perceus.spellcasting2.ancient_spells.SpellCreatePig;
import com.perceus.spellcasting2.ancient_spells.SpellCreatePiglin;
import com.perceus.spellcasting2.ancient_spells.SpellCreatePiglinBrute;
import com.perceus.spellcasting2.ancient_spells.SpellCreatePillager;
import com.perceus.spellcasting2.ancient_spells.SpellCreatePolarBear;
import com.perceus.spellcasting2.ancient_spells.SpellCreateRabbit;
import com.perceus.spellcasting2.ancient_spells.SpellCreateRavager;
import com.perceus.spellcasting2.ancient_spells.SpellCreateSheep;
import com.perceus.spellcasting2.ancient_spells.SpellCreateShulker;
import com.perceus.spellcasting2.ancient_spells.SpellCreateSkeleton;
import com.perceus.spellcasting2.ancient_spells.SpellCreateSlime;
import com.perceus.spellcasting2.ancient_spells.SpellCreateSnowGolem;
import com.perceus.spellcasting2.ancient_spells.SpellCreateSpider;
import com.perceus.spellcasting2.ancient_spells.SpellCreateSquid;
import com.perceus.spellcasting2.ancient_spells.SpellCreateStray;
import com.perceus.spellcasting2.ancient_spells.SpellCreateStrider;
import com.perceus.spellcasting2.ancient_spells.SpellCreateTurtle;
import com.perceus.spellcasting2.ancient_spells.SpellCreateVex;
import com.perceus.spellcasting2.ancient_spells.SpellCreateVindicator;
import com.perceus.spellcasting2.ancient_spells.SpellCreateWitch;
import com.perceus.spellcasting2.ancient_spells.SpellCreateWitherSkeleton;
import com.perceus.spellcasting2.ancient_spells.SpellCreateWolf;
import com.perceus.spellcasting2.ancient_spells.SpellCreateZoglin;
import com.perceus.spellcasting2.ancient_spells.SpellCreateZombie;
import com.perceus.spellcasting2.ancient_spells.SpellCreateZombifiedPiglin;
import com.perceus.spellcasting2.darkmagic_spells.SpellBloodletting;
import com.perceus.spellcasting2.darkmagic_spells.SpellCurse;
import com.perceus.spellcasting2.darkmagic_spells.SpellDeath;
import com.perceus.spellcasting2.darkmagic_spells.SpellEtherBreak;
import com.perceus.spellcasting2.darkmagic_spells.SpellHex;
import com.perceus.spellcasting2.darkmagic_spells.SpellLawOfProgression;
import com.perceus.spellcasting2.fire_spells.SpellChaosFireball;
import com.perceus.spellcasting2.fire_spells.SpellChaosMeteor;
import com.perceus.spellcasting2.fire_spells.SpellEruption;
import com.perceus.spellcasting2.fire_spells.SpellLavaBomb;
import com.perceus.spellcasting2.fire_spells.SpellOverheat;
import com.perceus.spellcasting2.geo_spells.SpellInscribeLuck;
import com.perceus.spellcasting2.geo_spells.SpellNullify;
import com.perceus.spellcasting2.geo_spells.SpellSandstorm;
import com.perceus.spellcasting2.geo_spells.SpellTillEarth;
import com.perceus.spellcasting2.geo_spells.SpellToxicSludge;
import com.perceus.spellcasting2.geo_spells.SpellTransmute;
import com.perceus.spellcasting2.holy_spells.SpellAmeliorate;
import com.perceus.spellcasting2.holy_spells.SpellBestowLife;
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
import com.perceus.spellcasting2.holy_spells.SpellUrgentSupport;
import com.perceus.spellcasting2.relics.BoonGrimoire;
import com.perceus.spellcasting2.spellitem_spell.SpellItem_EtherCrystal;
import com.perceus.spellcasting2.storm_spells.SpellElectrocute;
import com.perceus.spellcasting2.storm_spells.SpellStormSurge;
import com.perceus.spellcasting2.storm_spells.SpellTornado;
import com.perceus.spellcasting2.storm_spells.SpellWildBolt;
import com.perceus.spellcasting2.unholy_spells.SpellDesintegrationAmpule;
import com.perceus.spellcasting2.unholy_spells.SpellDrainingAura;
import com.perceus.spellcasting2.unholy_spells.SpellReapAndSew;
import com.perceus.spellcasting2.unholy_spells.SpellSkullOfNight;
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

public class SpellItemDropListener implements Listener
{
	private static final Random random = new Random();
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) 
	{	

//		if (!(event.getEntity().getKiller() instanceof Player))
//		{
//			return;
//		}
//		If I were to ever want to have the player kill mobs to have a chance at a loot drop, uncomment the code above.
		if (!mapOfMobs.containsKey(event.getEntity().getType()) && !mapOfMobs2.containsKey(event.getEntity().getType()) && !bossMobs.containsKey(event.getEntity().getType()) && !enchantSpells.containsKey(event.getEntity().getType())) 
		{
			return;
		}
		
		//drop rate for normal mob drops
		if (mapOfMobs.containsKey(event.getEntity().getType()) && random.nextDouble() < 0.19) 
		{
			event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), mapOfMobs.get(event.getEntity().getType())[random.nextInt(mapOfMobs.get(event.getEntity().getType()).length)].generate());
		}
		
		//drop rate for boss mob drops
		if (bossMobs.containsKey(event.getEntity().getType()) && random.nextDouble() < 1.0) 
		{
			event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), bossMobs.get(event.getEntity().getType())[random.nextInt(bossMobs.get(event.getEntity().getType()).length)].generate());
		}
		
		//drop rate for soul item mob drops
		if (mapOfMobs2.containsKey(event.getEntity().getType()) && random.nextDouble() < 0.34) 
		{
			event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), mapOfMobs2.get(event.getEntity().getType())[random.nextInt(mapOfMobs2.get(event.getEntity().getType()).length)].generate());
		}
		
		//drop rate for aethereal spells dropped by enderman
		if (enchantSpells.containsKey(event.getEntity().getType()) && random.nextDouble() < 0.39) 
		{
			event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), enchantSpells.get(event.getEntity().getType())[random.nextInt(enchantSpells.get(event.getEntity().getType()).length)].generate());
		}
		
		return;
		
	}
	
	@SuppressWarnings("serial")
	private static Map<EntityType, BaseSpellCapsule[]> mapOfMobs = new HashMap<>() 
	{{
		//holy spell drops
		put(EntityType.COW, new BaseSpellCapsule[]{new SpellPacify(), new SpellDisarm(), new SpellHeal(), new KBSpellEmitForce(), new KBSpellHealingHands(), new KBSpellCure(), new ObfuscatedSpellPage()});
		put(EntityType.PIG, new BaseSpellCapsule[]{new SpellPacify(), new SpellDisarm(), new KBSpellEmitForce(), new KBSpellHealingHands(), new KBSpellSatiate()});
		put(EntityType.CHICKEN, new BaseSpellCapsule[]{new SpellPacify(), new SpellDisarm(), new KBSpellEmitForce(), new KBSpellHealingHands(), new KBSpellAccolades()});
		put(EntityType.SHEEP, new BaseSpellCapsule[]{new SpellPacify(), new SpellDisarm(), new KBSpellEmitForce(), new KBSpellHealingHands()});
		put(EntityType.PARROT, new BaseSpellCapsule[]{new SpellPacify(), new SpellDisarm(), new KBSpellEmitForce(), new KBSpellAccolades(), new SpellHealingAura()});
		put(EntityType.PANDA, new BaseSpellCapsule[]{new KBSpellEmitForce(), new SpellUrgentSupport(), new KBSpellBarrier(), new KBSpellCure(), new KBSpellSatiate(), new SpellHealingAura(), new SpellRemedy()});
		put(EntityType.GOAT, new BaseSpellCapsule[]{new SpellPacify(), new KBSpellEmitForce(), new KBSpellBarrier(), new KBSpellCure(), new SpellHealingAura(), new SpellProtectOther()});
		put(EntityType.FOX, new BaseSpellCapsule[]{new SpellPacify(), new SpellDisarm(), new KBSpellEmitForce(), new KBSpellAccolades()});
		put(EntityType.MULE, new BaseSpellCapsule[]{new SpellPacify(), new KBSpellEmitForce(), new SpellHealingAura(), new SpellProtectOther()});
		put(EntityType.LLAMA, new BaseSpellCapsule[]{new SpellPacify(), new KBSpellEmitForce(), new KBSpellAccolades(), new SpellHealingAura(), new SpellProtectOther()});
		put(EntityType.HORSE, new BaseSpellCapsule[]{new SpellPacify(), new KBSpellEmitForce(), new SpellHealingAura(), new SpellProtectOther()});
		//void spell drops
		put(EntityType.ENDERMAN, new BaseSpellCapsule[] {new KBSpellVoidBurst(), new KBSpellPull(), new KBSpellVectorPlate(), new KBSpellTeleport(), new KBSpellRift(), new KBSpellAntimatter(),
				new KBSpellCrush(), new KBSpellPolarize(), new KBSpellAccelerateLife(), new KBSpellCataclysm(), 
				new KBSpellGate(), new KBSpellVoidGate(), new SpellVoidContainment(), new SpellNullPointConfiguration(), new SpellRecallAnchor(), new SpellTelekinesis(), new ObfuscatedSpellPage()});
		
		//water spell drops
		put(EntityType.DROWNED, new BaseSpellCapsule[] {new KBSpellRipTide(), new KBSpellSuffocate(), new KBSpellSoak(), new ObfuscatedSpellPage()});
		put(EntityType.COD, new BaseSpellCapsule[] {new KBSpellGills(), new KBSpellSplash()});
		put(EntityType.POLAR_BEAR, new BaseSpellCapsule[] {new KBSpellReconstitute(), new KBSpellSoak(), new SpellFrostBite()});
		put(EntityType.SLIME, new BaseSpellCapsule[] {new KBSpellReconstitute()});
		put(EntityType.SNOWMAN, new BaseSpellCapsule[] {new KBSpellReconstitute(), new KBSpellSuffocate(), new SpellBlizzard()});
		put(EntityType.FROG, new BaseSpellCapsule[] {new KBSpellGills(), new KBSpellSplash()});
		put(EntityType.TADPOLE, new BaseSpellCapsule[] {new KBSpellGills(), new KBSpellSplash()});
		put(EntityType.SALMON, new BaseSpellCapsule[] {new KBSpellGills(), new KBSpellSplash()});
		put(EntityType.TROPICAL_FISH, new BaseSpellCapsule[] {new KBSpellGills(), new KBSpellSplash()});
		put(EntityType.SQUID, new BaseSpellCapsule[] {new KBSpellGills(), new KBSpellSplash()});
		put(EntityType.DOLPHIN, new BaseSpellCapsule[] {new KBSpellGills(), new KBSpellRagingCurrent()});
		put(EntityType.GUARDIAN, new BaseSpellCapsule[] {new KBSpellConduitEther(), new KBSpellFishScales(), new KBSpellGills(), new KBSpellSaturate(), new KBSpellTidalForce()});
		put(EntityType.GLOW_SQUID, new BaseSpellCapsule[] {new KBSpellConduitEther(), new KBSpellGills(), new SpellRadiance()});
		
		//fire spell drops
		put(EntityType.BLAZE, new BaseSpellCapsule[] {new KBSpellKindleFlame(), new KBSpellEmbers(), new KBSpellFireball(), new KBSpellIgnite(), new ObfuscatedSpellPage(),
				new KBSpellMeteor(), new KBSpellFlamethrower(), new KBSpellCombustion(), new KBSpellHellFire(), new SpellEruption(), new SpellOverheat()});
		put(EntityType.STRIDER, new BaseSpellCapsule[] {new KBSpellKindleFlame(), new KBSpellExpulsion(), new KBSpellHellFire(), new KBSpellFireball(), new SpellLavaBomb()});
		put(EntityType.MAGMA_CUBE, new BaseSpellCapsule[] {new KBSpellKindleFlame(),new KBSpellEmbers(), new KBSpellIgnite(), new KBSpellInsulate(), 
				new KBSpellExpulsion(), new KBSpellSmokescreen(), new KBSpellCombustion(), new KBSpellFireball(), new KBSpellHellFire(), new SpellLavaBomb()});
		put(EntityType.PIGLIN, new BaseSpellCapsule[] {new KBSpellKindleFlame(), new KBSpellInsulate(), new KBSpellFireball(), new KBSpellOverclockProtocol()});
		put(EntityType.PIGLIN_BRUTE, new BaseSpellCapsule[] {new KBSpellKindleFlame(), new KBSpellInsulate(), new KBSpellIgnitionDrive()});
		put(EntityType.HOGLIN, new BaseSpellCapsule[] {new KBSpellKindleFlame(), new KBSpellIgnite(), new KBSpellInsulate()});
		
		//geo spell drops
		put(EntityType.HUSK, new BaseSpellCapsule[] {new KBSpellTerraform(), new KBSpellGeoMorph(), new KBSpellSandBlast(), new SpellSandstorm(), new KBSpellBoulder(), new SpellProtectOther()});
		put(EntityType.SPIDER, new BaseSpellCapsule[] {new KBSpellTerraform(), new KBSpellGeoMorph(), new KBSpellPoisonGas(), new KBSpellPoisonCloud()});
		put(EntityType.CAVE_SPIDER, new BaseSpellCapsule[] {new KBSpellTerraform(), new KBSpellGeoMorph(), new KBSpellPoisonCloud(), new KBSpellToxicGas(), new SpellToxicSludge()});
		
		//storm spell drops
		put(EntityType.PILLAGER, new BaseSpellCapsule[] {new KBSpellSmite(), new KBSpellStaticCharge(), new KBSpellGalvanicNeedle(), new KBSpellExplosiveBolt(), new SpellElectrocute()});
		put(EntityType.VINDICATOR, new BaseSpellCapsule[] {new KBSpellThunderStorm(), new KBSpellUberCharge(), new KBSpellThunderStrike()});
		
		//unholy spell drops
		put(EntityType.BAT, new BaseSpellCapsule[] {new KBSpellLifeSteal(), new SpellFertility(), new SpellProtectOther()});
		//Magic Bolt (internal name) aka magic missile, is a spell that can only be dropped by the skeleton regardless of it being undead and the spell element being Holy.
		put(EntityType.SKELETON, new BaseSpellCapsule[] {new KBSpellReapSouls(), new KBSpellDemonSight(), new KBSpellDemonicReflexes(), new KBSpellLightShift(), new KBSpellMagicBolt()});
		put(EntityType.ZOMBIE, new BaseSpellCapsule[] {new KBSpellLifeSteal(), new KBSpellDemonSight(), new KBSpellDemonicReflexes(), new KBSpellEmitDamagingForce()});
		put(EntityType.ZOGLIN, new BaseSpellCapsule[] {new KBSpellReapSouls(), new KBSpellDemonSight(), new KBSpellDemonicReflexes(), new KBSpellDebilitate()});
		put(EntityType.ZOMBIFIED_PIGLIN, new BaseSpellCapsule[] {new KBSpellLifeSteal(), new KBSpellReapSouls(), new KBSpellDemonSight(), new KBSpellDemonicReflexes(), new KBSpellDebilitate()});
		put(EntityType.STRAY, new BaseSpellCapsule[] {new KBSpellLifeSteal(), new KBSpellReapSouls(), new KBSpellDemonSight(), new KBSpellDemonicReflexes(), 
				new KBSpellEmitDamagingForce(), new KBSpellDebilitate(), new SpellFrostBite(), new SpellBlizzard()});
		
		//msc mobs that can drop any element of spell
		put(EntityType.SILVERFISH, new BaseSpellCapsule[] {new KBSpellGills(), new KBSpellPoisonGas(), new KBSpellSuffocate(), new KBSpellGeoMorph(), new KBSpellTerraform()});
		put(EntityType.CREEPER, new BaseSpellCapsule[] {new KBSpellKindleFlame(), new KBSpellEmbers()});
		put(EntityType.SHULKER, new BaseSpellCapsule[] {new KBSpellGravitas(), new KBSpellMetalMorph(), new KBSpellAntiGravity(), new SpellNullify(), new KBSpellBarrier(), new SpellTelekinesis()});
		put(EntityType.IRON_GOLEM, new BaseSpellCapsule[] {new KBSpellTerraform(), new KBSpellReconstitute(), 
				new KBSpellGeoMorph(), new KBSpellMetalMorph(), new KBSpellNaturesGift(), new KBSpellNaturesWrath(), new SpellTillEarth(), new SpellInscribeLuck(), new KBSpellEarthquake(), new SpellTransmute()});
		put(EntityType.WITHER_SKELETON, new BaseSpellCapsule[] {new KBSpellExpulsion(), new SpellSkullOfNight(), new SpellDesintegrationAmpule(),
				new KBSpellHellFire(), new SpellUndeadRecall(), new KBSpellDarkHarvest(), new KBSpellExpellLiving(), new KBSpellExpellUndead(), new KBSpellDemonicReflexes(), new KBSpellDoom(), new KBSpellRot()});
		put(EntityType.WITCH, new BaseSpellCapsule[] {new KBSpellKindleFlame(), new ObfuscatedSpellPage(),
				new KBSpellIgnite(), new KBSpellSoak(), new SpellPacify(), new KBSpellPoisonGas(), new KBSpellArcaneBolt(), new KBSpellDebilitate(), new KBSpellRaiseDead(), new KBSpellCure(),
				new SpellDisarm(), new KBSpellMageMorph(), new KBSpellBarrier(), new SpellProtectOther()});
		put(EntityType.PHANTOM, new BaseSpellCapsule[] {new KBSpellAngelicFlight(), new KBSpellSmite(), new KBSpellGust(), new KBSpellGaleForce(), new KBSpellTailWind(), new SpellRemedy(), new SpellTornado()});
		put(EntityType.VEX, new BaseSpellCapsule[] {new KBSpellAngelicFlight(),
				new KBSpellSmite(), new KBSpellGust(), new KBSpellLifeSteal(), new KBSpellReapSouls(), new KBSpellEmitForce(), new KBSpellEmitDamagingForce(), new KBSpellTailWind(), new KBSpellBlink()});
		put(EntityType.EVOKER, new BaseSpellCapsule[] {new SpellRemedy(), new KBSpellBlink(), new SpellWildBolt(),
				new SpellStormSurge(), new KBSpellMageMorph(), new KBSpellArcaneBolt(), new ObfuscatedSpellPage(),
				new KBSpellVoidBurst(), new KBSpellUndeadEffigy(), new KBSpellAccelerateLife(),
				new KBSpellPolarize(), new KBSpellRaiseDead(), new KBSpellExpellLiving(), new KBSpellExpellUndead(), new KBSpellPull(), new SpellEtherTransference(), new SpellFertility(), new SpellProtectOther()});
		put(EntityType.RAVAGER, new BaseSpellCapsule[] {new KBSpellGeoMorph(), new KBSpellEmitDamagingForce(), new KBSpellCrush(), new KBSpellVectorPlate(), new KBSpellUndeadEffigy()});
		put(EntityType.AXOLOTL, new BaseSpellCapsule[] {new KBSpellGills(), new KBSpellReconstitute(), new KBSpellRegenerate(), new KBSpellSplash(), new KBSpellMendingWater(), new KBSpellPenance()});
		put(EntityType.BEE, new BaseSpellCapsule[] {new KBSpellTailWind(), new KBSpellGust(), new KBSpellCure(), new KBSpellHealingHands(), new KBSpellPoisonGas(), new KBSpellBarrier()});
		put(EntityType.TURTLE, new BaseSpellCapsule[] {new KBSpellTerraform(), new KBSpellGeoMorph(), new KBSpellBoulder(), 
				new KBSpellGills(), new KBSpellSoak(), new KBSpellRipTide(), new SpellTillEarth(), new KBSpellBarrier()});
		put(EntityType.GHAST, new BaseSpellCapsule[]{new SpellFertility(), new SpellUrgentSupport(), new SpellPacify(), new KBSpellFireball(), new ObfuscatedSpellPage(), 
				new KBSpellIgnite(), new KBSpellRegenerate(), new KBSpellAngelicFlight(), new KBSpellPenance(), new KBSpellExtricate(), new SpellProtectOther()});
		put(EntityType.WOLF, new BaseSpellCapsule[] {new KBSpellCure(), new SpellUrgentSupport(), new KBSpellSatiate(), new KBSpellAccolades(), new KBSpellEmitForce(), new SpellFertility(), new SpellProtectOther(), new SpellRemedy()});
	}};
	
	@SuppressWarnings("serial")
	private static Map<EntityType, BaseSpellCapsule[]> bossMobs = new HashMap<>() 
	{{
		//bosses' drops 
		put(EntityType.ELDER_GUARDIAN, new BaseSpellCapsule[] {new BoonGrimoire(), new SpellIceAge(), new KBSpellConduitEther(), 
				new KBSpellWaveWake(), new KBSpellCrystalize(), new KBSpellMendingWater(), new KBSpellFishScales(), new KBSpellGills(), new SpellPoseidonsGift(), 
				new KBSpellSoothingCurrent(), new SpellLivingRecall(), new KBSpellPenance(), new SpellEtherTransference(), new KBSpellBarrier(), new SpellItem_EtherCrystal(), new SpellTsunami(), 
				new ObfuscatedSpellPage()});
		put(EntityType.ENDER_DRAGON, new BaseSpellCapsule[] {new BoonGrimoire(), new SpellChaosFireball(), new SpellChaosMeteor(), new KBSpellManaBurn(), new KBSpellBarrier(), new ObfuscatedSpellPage(), 
				new KBSpellDracoMorph(), new SpellLivingRecall(), new SpellRecallAnchor(), new KBSpellVoidGate(), new KBSpellGate(), new SpellItem_EtherCrystal(), new SpellTornado()});
		put(EntityType.WITHER, new BaseSpellCapsule[] {new BoonGrimoire(), new SpellDeath(), new SpellBloodletting(), new SpellHex(), new SpellCurse(), new ObfuscatedSpellPage(), 
				new SpellLawOfProgression(), new SpellEtherBreak(), new KBSpellArcaneBolt(), new SpellNullify(), new SpellDesintegrationAmpule(), 
				new SpellDrainingAura(), new SpellUndeadRecall(), new KBSpellDarkHarvest(), new KBSpellReapSouls(),
				new KBSpellDemonSight(), new KBSpellSapEther(), new KBSpellRaiseDead(), new KBSpellUndeadEffigy(), 
				new KBSpellDemonicReflexes(), new KBSpellExpellLiving(), new KBSpellExpellUndead(), new KBSpellDoom(), 
				new KBSpellRot(), new KBSpellBarrier(), new SpellReapAndSew(), new SpellItem_EtherCrystal(),new SpellUndyingSoul(), new SpellSkullOfNight()});
		put(EntityType.ALLAY, new BaseSpellCapsule[] {new BoonGrimoire(), new SpellUrgentSupport(), new SpellBestowLife(), new SpellLivingRecall(), new SpellRecallAnchor(), new KBSpellJudgement(), new SpellAmeliorate(), 
				new KBSpellVaporize(), new KBSpellArcaneBolt(), new SpellProlongedFlight(), new SpellGuardianAngel(), new KBSpellLawOfRegression(), 
				new KBSpellPenance(), new SpellRestoration(), new SpellEtherTransference(), new ObfuscatedSpellPage(), 
				new SpellHealingAura(), new SpellProtectOther(), new SpellFertility(), new KBSpellBarrier(), new SpellItem_EtherCrystal(),new SpellUndyingSoul(), new SpellRemedy()});
		put(EntityType.WARDEN, new BaseSpellCapsule[] {new BoonGrimoire(), new KBSpellBanishGreaterEvil(), new SpellPrimordialShift(), new SpellVoidShift(), new ObfuscatedSpellPage(), 
				new SpellEtherContinuity(), new SpellOverrideClimate(), new SpellSingularity(), new KBSpellBarrier(), new SpellItem_EtherCrystal()});
	}};
	
	@SuppressWarnings("serial")
	private static Map<EntityType, BaseSpellCapsule[]> mapOfMobs2 = new HashMap<>() 
	{{
		//ancient magic soul crystals of mobs' drops
		
		//passive mobs
		put(EntityType.COW, new BaseSpellCapsule[]{new SpellCreateCow()});
		put(EntityType.SHEEP, new BaseSpellCapsule[]{new SpellCreateSheep()});
		put(EntityType.RABBIT, new BaseSpellCapsule[]{new SpellCreateRabbit()});
		put(EntityType.PIG, new BaseSpellCapsule[]{new SpellCreatePig()});
		put(EntityType.CHICKEN, new BaseSpellCapsule[]{new SpellCreateChicken()});
		put(EntityType.WOLF, new BaseSpellCapsule[]{new SpellCreateWolf()});
		put(EntityType.TURTLE, new BaseSpellCapsule[]{new SpellCreateTurtle()});
		put(EntityType.SQUID, new BaseSpellCapsule[]{new SpellCreateSquid()});
		put(EntityType.GLOW_SQUID, new BaseSpellCapsule[]{new SpellCreateGlowSquid()});
		put(EntityType.POLAR_BEAR, new BaseSpellCapsule[]{new SpellCreatePolarBear()});
		put(EntityType.PARROT, new BaseSpellCapsule[]{new SpellCreateParrot()});
		put(EntityType.PANDA, new BaseSpellCapsule[]{new SpellCreatePanda()});
		put(EntityType.MUSHROOM_COW, new BaseSpellCapsule[]{new SpellCreateMooshroom()});
		put(EntityType.OCELOT, new BaseSpellCapsule[]{new SpellCreateOcelot()});
		put(EntityType.MULE, new BaseSpellCapsule[]{new SpellCreateMule()});
		put(EntityType.LLAMA, new BaseSpellCapsule[]{new SpellCreateLlama()});
		put(EntityType.GOAT, new BaseSpellCapsule[]{new SpellCreateGoat()});
		put(EntityType.HORSE, new BaseSpellCapsule[]{new SpellCreateHorse()});
		put(EntityType.DONKEY, new BaseSpellCapsule[]{new SpellCreateDonkey()});
		put(EntityType.FROG, new BaseSpellCapsule[]{new SpellCreateFrog()});
		put(EntityType.FOX, new BaseSpellCapsule[]{new SpellCreateFox()});
		put(EntityType.DOLPHIN, new BaseSpellCapsule[]{new SpellCreateDolphin()});
		put(EntityType.BEE, new BaseSpellCapsule[]{new SpellCreateBee()});
		put(EntityType.BAT, new BaseSpellCapsule[]{new SpellCreateBat()});
		put(EntityType.AXOLOTL, new BaseSpellCapsule[]{new SpellCreateAxolotl()});
		put(EntityType.IRON_GOLEM, new BaseSpellCapsule[]{new SpellCreateIronGolem()});
		put(EntityType.SNOWMAN, new BaseSpellCapsule[]{new SpellCreateSnowGolem()});
		
		//enemy mobs
		put(EntityType.CAVE_SPIDER, new BaseSpellCapsule[]{new SpellCreateCaveSpider()});
		put(EntityType.CREEPER, new BaseSpellCapsule[]{new SpellCreateCreeper()});
		put(EntityType.DROWNED, new BaseSpellCapsule[]{new SpellCreateDrowned()});
		put(EntityType.ENDERMAN, new BaseSpellCapsule[]{new SpellCreateEnderman()});
		put(EntityType.EVOKER, new BaseSpellCapsule[]{new SpellCreateEvoker()});
		put(EntityType.GHAST, new BaseSpellCapsule[]{new SpellCreateGhast()});
		put(EntityType.GUARDIAN, new BaseSpellCapsule[]{new SpellCreateGuardian()});
		put(EntityType.HOGLIN, new BaseSpellCapsule[]{new SpellCreateHoglin()});
		put(EntityType.HUSK, new BaseSpellCapsule[]{new SpellCreateHusk()});
		put(EntityType.MAGMA_CUBE, new BaseSpellCapsule[]{new SpellCreateMagmaCube()});
		put(EntityType.RAVAGER, new BaseSpellCapsule[]{new SpellCreateRavager()});
		put(EntityType.PILLAGER, new BaseSpellCapsule[]{new SpellCreatePillager()});
		put(EntityType.PIGLIN, new BaseSpellCapsule[]{new SpellCreatePiglin()});
		put(EntityType.PIGLIN_BRUTE, new BaseSpellCapsule[]{new SpellCreatePiglinBrute()});
		put(EntityType.PHANTOM, new BaseSpellCapsule[]{new SpellCreatePhantom()});
		put(EntityType.SHULKER, new BaseSpellCapsule[]{new SpellCreateShulker()});
		put(EntityType.SKELETON, new BaseSpellCapsule[]{new SpellCreateSkeleton()});
		put(EntityType.SLIME, new BaseSpellCapsule[]{new SpellCreateSlime()});
		put(EntityType.SPIDER, new BaseSpellCapsule[]{new SpellCreateSpider()});
		put(EntityType.STRAY, new BaseSpellCapsule[]{new SpellCreateStray()});
		put(EntityType.STRIDER, new BaseSpellCapsule[]{new SpellCreateStrider()});
		put(EntityType.BLAZE, new BaseSpellCapsule[]{new SpellCreateBlaze()});
		put(EntityType.VEX, new BaseSpellCapsule[]{new SpellCreateVex()});
		put(EntityType.VINDICATOR, new BaseSpellCapsule[]{new SpellCreateVindicator()});
		put(EntityType.WITHER_SKELETON, new BaseSpellCapsule[]{new SpellCreateWitherSkeleton()});
		put(EntityType.WITCH, new BaseSpellCapsule[]{new SpellCreateWitch()});
		put(EntityType.ZOGLIN, new BaseSpellCapsule[]{new SpellCreateZoglin()});
		put(EntityType.ZOMBIFIED_PIGLIN, new BaseSpellCapsule[]{new SpellCreateZombifiedPiglin()});
		put(EntityType.ZOMBIE, new BaseSpellCapsule[]{new SpellCreateZombie()});
	}};
	
	@SuppressWarnings("serial")
	private static Map<EntityType, BaseSpellCapsule[]> enchantSpells = new HashMap<>() 
	{{
		put(EntityType.ENDERMAN, new BaseSpellCapsule[]{new SpellFortifyDurability(), new SpellFortifyMending(), new SpellFortifySharpness(), new SpellFortifyEfficiency(),
				new SpellFortifySilkTouch(), new SpellFortifyFortune(), new SpellFortifyLure(), new SpellFortifyFishingLuck(), new SpellFortifyProtection(), new SpellCleanse(), new SpellFortifyFireProtection(), 
				new SpellFortifyBlastProtection(), new SpellFortifyProjectileProtection(), new SpellFortifyFeatherFalling(), new SpellFortifyThorns(), new SpellFortifyRespiration(), new SpellFortifyAquaAffinity(),
				new SpellFortifySwiftSneak(), new SpellFortifyDepthStrider(), new SpellFortifyFrostWalker(), new SpellFortifySoulSpeed(), new SpellFortifySmite(), new SpellFortifyBaneOfArthropods(), new SpellFortifyLooting(),
				new SpellFortifySweepingEdge(), new SpellFortifyKnockback(), new SpellFortifyFireAspect(), new SpellFortifyPower(), new SpellFortifyPunch(), new SpellFortifyInfinity(), new SpellFortifyFlame(), 
				new SpellFortifyMultishot(), new SpellFortifyQuickCharge(), new SpellFortifyPiercing(), new SpellFortifyChanneling(), new SpellFortifyImpaling(), new SpellFortifyLoyalty(),
				new SpellFortifyRipTide(), new SpellAetherealOverride(), new SpellAetherealOverridePlus(), new SpellPurify()});
	}};
}
