package com.perceus.spellcasting2.manamechanic;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import fish.yukiemeralis.eden.utils.JsonUtils;

public class PlayerDataMana
{

	private static Map<UUID, StorePlayerMana> mana_registry = new HashMap<>();
	
	public static StorePlayerMana getPlayerData(UUID uuid) 
	{
		
		if (mana_registry.containsKey(uuid)) 
		{
			return mana_registry.get(uuid);
		}
		
		StorePlayerMana data;
		
		File file = new File("./plugins/Eden/playerdata/data/" + uuid.toString() + ".json");
		
		if (!file.exists()) 
		{
			data = new StorePlayerMana();
			JsonUtils.toJsonFile(file.getAbsolutePath(), data);
			mana_registry.put(uuid, data);
			return data;
			//creates a file for player data if it doesn't already exists.
		}
		
		data = (StorePlayerMana) JsonUtils.fromJsonFile(file.getAbsolutePath(), StorePlayerMana.class);
		if (data == null) 
		{
			data = new StorePlayerMana();
			JsonUtils.toJsonFile(file.getAbsolutePath(), data);
			mana_registry.put(uuid, data);
			return data;
			
			//checks to see if the data is corrupted, if true, then it will recreate that player data.
		}
		//if the player data is not == null, then we return the file's data.
		mana_registry.put(uuid, data);
		return data;
		
	}
	
	public static Map<UUID, StorePlayerMana> getalldata()
	{
		return mana_registry;
	}
	
}
