package com.perceus.spellcasting2.manamechanic;

import com.google.gson.annotations.Expose;

public class StorePlayerMana
{
	@Expose
	private int currentMana = 0;
	@Expose
	private int maxMana = 1000;
	@Expose
	private int minMana = 0;
	
	public int getCurrentMana()
	{
		return currentMana;
	}

	public void setCurrentMana(int currentMana)
	{
		this.currentMana = currentMana;
	}

	public int getMaxMana()
	{
		return maxMana;
	}

	public void setMaxMana(int maxMana)
	{
		this.maxMana = maxMana;
	}

	public int getMinMana()
	{
		return minMana;
	}

	public void setMinMana(int minMana)
	{
		this.minMana = minMana;
	}	
}
