import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;


public class ChatListener extends PlayerListener{
	Main plugin;

	public ChatListener(Main instance){
		plugin = instance;
	}

	public void onPlayerChat(PlayerChatEvent event){

		if(event.getMessage().equalsIgnoreCase("hit")){
			if(plugin.turn != null && event.getPlayer().equals(plugin.turn.getPlayer())){
				event.setCancelled(true);
				plugin.turn.getPlayer().sendMessage(ChatColor.GOLD + "hit");
				plugin.turn.giveCard(plugin.blackjack.hit());
				plugin.playerTurn(plugin.turn);
			}
		}
		else if(event.getMessage().equalsIgnoreCase("stay")){
			if(plugin.turn != null && event.getPlayer().equals(plugin.turn.getPlayer())){
					event.setCancelled(true);
					plugin.turn.getPlayer().sendMessage(ChatColor.GOLD + "stay");
					plugin.blackjack.stay(plugin.turn);
					plugin.game();
			}
		}
		else if(event.getMessage().equalsIgnoreCase("double down")){
			if(plugin.turn != null && event.getPlayer().equals(plugin.turn.getPlayer())){
				event.setCancelled(true);
				int bet = plugin.blackjack.bets.get(plugin.turn);
				if(plugin.turn.getCash() >= bet){
					plugin.turn.getPlayer().sendMessage(ChatColor.GOLD + "double down");
					plugin.turn.takeCash(bet);
					plugin.blackjack.bets.put(plugin.turn,bet*2);
					plugin.turn.giveCard(plugin.blackjack.hit());
					plugin.blackjack.stay(plugin.turn);
					plugin.game();
				}
				else{
					plugin.turn.getPlayer().sendMessage(ChatColor.RED + "You don't have enough money to do that!");
				}
			}
		}
		else if(event.getMessage().equalsIgnoreCase("leave")){
			if(plugin.blackjack.containsPlayer(event.getPlayer())){
				event.setCancelled(true);
				plugin.leaveQueue.add(plugin.blackjack.match(event.getPlayer()));
				event.getPlayer().sendMessage("You will be removed from the game at the end of this hand...");
			}
		}
		else if(event.getMessage().startsWith("bet ")){
			if(plugin.better != null && event.getPlayer().equals(plugin.better.getPlayer()) && plugin.betting){
				event.setCancelled(true);
				int bet = Integer.parseInt(event.getMessage().split(" ")[1]);
				plugin.better.getPlayer().sendMessage(ChatColor.GOLD + "You bet " + bet);
				boolean betted = plugin.bet(plugin.better,bet);
				if(betted){
					plugin.betting();
				}
				else{
					event.getPlayer().sendMessage(ChatColor.RED + "You don't have enough money for that!");
				}
			}
		}
		else if(event.getMessage().equalsIgnoreCase("cash")||event.getMessage().equalsIgnoreCase("money")){
			if(plugin.blackjack.containsPlayer(event.getPlayer())){
				event.setCancelled(true);
				event.getPlayer().sendMessage("You have " + plugin.blackjack.match(event.getPlayer()).getCash() + " dollars.");
			}
		}
	}
}
