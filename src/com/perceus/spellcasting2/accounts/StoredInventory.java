package com.perceus.spellcasting2.accounts;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import com.google.gson.annotations.Expose;

import fish.yukiemeralis.eden.utils.JsonUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class StoredInventory 
{
	private static Map<UUID,StoredInventory> mapOfInventories = new HashMap<>();

	private Inventory inventory = null;

	@Expose
	private Map<Integer,String> serializedInventory = new HashMap<>();
	private static final int invSize = 54;
	
	public StoredInventory() {}

	public StoredInventory(Inventory inventory)
	{
		this.inventory = inventory;
		generateSerializedInventory();
	}
	
	private void generateSerializedInventory()
	{
		for(int i = 0; i < invSize; i++)
		{
			serializedInventory.put(i,toBase64(inventory.getItem(i)));
		}	
	}
	
	private ItemStack[] deserialize() 
	{
		ItemStack[] data = new ItemStack[invSize];
		for (int i = 0 ; i < invSize ; i++) 
		{
			if(!serializedInventory.containsKey(i)) { continue; }

			data[i] = fromBase64(serializedInventory.get(i));
		}
		
		return data;
	}
	
	public static boolean hasInventory(Player player) 
	{
		if (mapOfInventories.containsKey(player.getUniqueId())) 
		{
			return true;
		}
		return JsonUtils.fromJsonFile("./plugins/Eden/playerdata/storedinventories/" + player.getUniqueId().toString() + ".json", StoredInventory.class) != null;
	}
	
	public void openInventory(Player player) 
	{
		if (inventory == null) 
		{
			inventory = Bukkit.createInventory(null, invSize, "Astral Inventory");
			inventory.setContents(this.deserialize());
		}
		
		player.openInventory(inventory);
	}
	
	public static StoredInventory getStoredInventory(Player player) 
	{
		StoredInventory inventory;
		
		if (!mapOfInventories.containsKey(player.getUniqueId())) 
		{
			inventory = JsonUtils.fromJsonFile("./plugins/Eden/playerdata/storedinventories/" + player.getUniqueId().toString() + ".json", StoredInventory.class);
			
			if(inventory == null) 
			{
				inventory = new StoredInventory(Bukkit.createInventory(null,invSize, "Astral Inventory"));
			}
			
			mapOfInventories.put(player.getUniqueId(), inventory);
			
		}
		
		return mapOfInventories.get(player.getUniqueId());
	}
	
	public static void save() 
	{
		mapOfInventories.forEach((uuid, inventory) -> 
		{
			inventory.generateSerializedInventory();
			JsonUtils.toJsonFile("./plugins/Eden/playerdata/storedinventories/" + uuid + ".json", inventory);
		});
	}

	/**
	 * Serialise an ItemStack
	 * @param item The itemstack
	 * @return BASE64 Encoded string
	 * @throws IllegalStateException
	 */
	public static String toBase64(ItemStack item)
	{
	    try 
	    {            
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        BukkitObjectOutputStream boos = new BukkitObjectOutputStream(baos);
	        boos.writeObject(item);
	        boos.close();
	        
	        return Base64.getEncoder().encodeToString(baos.toByteArray());
	    } 
	    catch (Exception e)
	    {
	        PrintUtils.log("Could not encode base64 itemstack!");
	        return "";
	    }
	}

	/**
	 * deserialise an ItemStack
	 * @param encodedItem BASE64 String
	 * @return ItemStack
	 * @throws IllegalStateException
	 */
	public static ItemStack fromBase64(String encodedItem)
	{
	    try
	    {            
	        byte [] data = Base64.getDecoder().decode(encodedItem);
	        BukkitObjectInputStream bois = new BukkitObjectInputStream(new ByteArrayInputStream(  data ) );
	        ItemStack stack = (ItemStack) bois.readObject();
	        bois.close();
	        
	        return stack;
	    }
	    catch(Exception e)
	    {
	        PrintUtils.log("Could not decode base64 itemstack!");
	        return new ItemStack(Material.GRASS_BLOCK);
	    }
	}
}