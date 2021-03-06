package com.geekpower14.quake.commands;

import com.geekpower14.quake.Quake;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

/*
 * This file is part of Quake.
 *
 * Quake is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Quake is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Quake.  If not, see <http://www.gnu.org/licenses/>.
 */
public class CommandsManager implements CommandExecutor{
	
	public Quake plugin;
	
	private HashMap<String, BasicCommand> commands;
	
	public CommandsManager(Quake pl)
	{
		plugin = pl;
		commands = new HashMap<>();
		loadCommands();
	}
	
	private void loadCommands()
	{
		commands.put("leave", new LeaveCommand(plugin));
		commands.put("addspawn", new AddSpawnCommand(plugin));
		commands.put("startGame", new StartCommand(plugin));
		commands.put("stop", new StopCommand(plugin));

	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd,
			final String commandLabel, String[] args) {
		
		Player player;
		if (sender instanceof Player) {
			player = (Player) sender;
		}else
		{
			sender.sendMessage("You need to be a player !");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("q")) {
			
			if(args == null || args.length < 1){
				player.sendMessage(ChatColor.YELLOW + "Plugin By Geekpower14 !" + " Version: Beta 1.0.1");
				return true;
			}
			
			if(args[0].equalsIgnoreCase("help")){
				help(player);
				return true;
			}

			if(args[0].equalsIgnoreCase("error")){
				return true;
			}
			
			String sub = args[0];

			List<String> l  = Arrays.asList(args);
			l.remove(0);
			args = l.toArray(new String[l.size()]);
			if(!commands.containsKey(sub)){
				player.sendMessage(ChatColor.RED+"Command dosent exist.");
				player.sendMessage(ChatColor.GOLD +"Type /q help for help" );
				return true;
			}
			try{
				commands.get(sub).onCommand(player,  args);
			}catch(Exception e){
				e.printStackTrace(); 
				
			player.sendMessage(ChatColor.RED+"An error occured while executing the command. Check the console");
			player.sendMessage(ChatColor.BLUE +"Type /q help for help" );
			
			}
			
			return true;
		}		
		
		return true;
	}

	public void help(Player p){
		
		p.sendMessage("/q <command> <args>");
		for(BasicCommand v: commands.values()){
			p.sendMessage(ChatColor.GRAY+ "- " + v.help(p));
		}
	}

}
