package com.Geekpower14.quake.stuff;

import com.Geekpower14.quake.Quake;
import com.Geekpower14.quake.stuff.grenade.FragGrenade;
import com.Geekpower14.quake.stuff.hoe.*;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
	
	public Quake plugin;
	
	public List<TItem> stuff = new ArrayList<TItem>(); 
	
	public ItemManager(Quake pl)
	{
		plugin = pl;
		
		stuff.add(new WoodenHoe());
		stuff.add(new StoneHoe());
		stuff.add(new IronHoe());
		stuff.add(new GoldHoe());
		stuff.add(new DiamondHoe());
		stuff.add(new AmazingHoe());
		stuff.add(new BlasterHoe());
		stuff.add(new PotatoHoe());

		stuff.add(new FragGrenade());
	}
	
	public TItem getItemByName(String name)
	{
		for(TItem i : stuff)
		{
			if(i.getName().equals(name))
				return (TItem) i.clone();
		}

		return getItemByName("woodenhoe");
	}

	public TItem getItemByName(String name, String defaut)
	{
		for(TItem i : stuff)
		{
			if(i.getName().equals(name))
				return (TItem) i.clone();
		}

		return getItemByName(defaut);
	}

}