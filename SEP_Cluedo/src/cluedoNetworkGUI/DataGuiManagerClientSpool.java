package cluedoNetworkGUI;


import java.util.ArrayList;

import javafx.application.Platform;
import staticClasses.Config;
import staticClasses.NetworkMessages;
import staticClasses.auxx;
import cluedoClient.ServerItem;
import cluedoClient.ServerPool;
import cluedoNetworkLayer.CluedoGameClient;
import cluedoNetworkLayer.CluedoStatement;
import enums.GameStates;
import enums.PlayerStates;
import enums.ServerStatus;

public class DataGuiManagerClientSpool extends DataGuiManager{
	
	ServerPool serverPool;
	ServerItem selectedServer;
	
	public DataGuiManagerClientSpool(CluedoClientGUI gui,ServerPool spool) {
		super(gui);
		serverPool = spool;
	}
	
	public void setServer(){
		try {
			refreshGamesListServer(serverPool.get(0));
			
		} catch (Exception e) {
			auxx.loginfo("no server to refresh gamelist from");
			emptyGamesList();
		}
	}
	
	public void addChatMsgIn(String chatmsg, String ts,ServerItem server){
		if (server == getSelectedServer()) addMsgIn(ts+" : "+chatmsg);
		server.addChatMsg(chatmsg, ts);
	}
	
	public void addGameToServer(ServerItem server, int gameID, String nick,String color){
		CluedoGameClient newgame = new CluedoGameClient(gameID,server);
		newgame.joinGame(color, nick);
		addGameToGui(gameID, nick,"",newgame.getGameState(),server.getGroupName(),server.getIpString());
		
		server.addGame(newgame);
	}
	
	public void addRunningGameToServer(ServerItem server, CluedoGameClient runningGame){
		addGameToGui(runningGame.getGameId(),runningGame.getNicksConnected(),runningGame.getWatchersConnected(),runningGame.getGameState(),server.getGroupName(),server.getIpString());
		
		server.addGame(runningGame);
		runningGame.start();
	}
	
	public void addClient(ServerItem server , String nick){
		if (!nick.equals(server.getMyNick()) && server.addClient(nick) && server == selectedServer){
			getGui().addClient(nick);
		}				
	}
	
	
	public void setClients(ServerItem server ,ArrayList<String> nicks){
		server.setClientNicks(nicks);		
	}
	
	public void setSelectedServer(ServerItem selectedServer) {
		this.selectedServer = selectedServer;
		setStatus("selected server : "+selectedServer.getGroupName());
		setWindowName("logged in to server "+selectedServer.getGroupName()+" as "+selectedServer.getMyNick());
		cleanInput();		
		setNicksGui(selectedServer);
		refreshGamesListServer(selectedServer);
		addMsgIn(selectedServer.getChat());
	}
	
	public ServerItem getSelectedServer() {
		return selectedServer;
	}
	
	public void removePlayerFromGameOnServer(int gameID,String nick,ServerItem server){
		CluedoGameClient game = server.getGameByGameID(gameID);
		game.removePlayer(nick);
		game.removeWatcher(nick);
		if (server == selectedServer) refreshGamesListServer(server);
	}
	
	public boolean joinGameOnServer(ServerItem server,int gameID,String color,String nick){
		CluedoGameClient game = server.getGameByGameID(gameID);
		if (game.joinGame(color, nick)){
			if (game.getNumberConnected() >= Config.MIN_CLIENTS_FOR_GAMESTART) {
				setReadyGame(gameID);				
			}				
			updateGame(gameID, game.getNicksConnected(),game.getWatchersConnected());

			return true;
		}
		return false;
	}
	
	public void startGameOnServer(ServerItem server,int gameID,String gameState, ArrayList <String> order){
		CluedoGameClient game = server.getGameByGameID(gameID);
		//game.setOrder(order);
		if (game.hasPlayerConnectedByNick(server.getMyNick()) || game.hasWatcherConnectedByNick(server.getMyNick()))
			game.start(order);
		game.setGameState(GameStates.getState(gameState));
		setRunningGame(gameID);
	}
	
	public boolean deleteGameOnServer(ServerItem server,int gameID){
		if (server.removeGameByID(gameID)){
			removeGameGui(gameID);
			return true;
		}
		
		return false;
	}
	
	public boolean removeClientFromSystemServer(ServerItem server,String nickID){
		if (server.removeClient(nickID) && server == selectedServer){
			getGui().removeClient(nickID);
		}		
		if (server.removePlayerFromGames(nickID)){
			refreshGamesListServer(server);
			return true;
		}
		return false;
	}

	public void refreshGamesListServer(ServerItem server){
		emptyGamesList();
		addGamesToGui(server.getGameList());		
	}
	
	public void setServerLoggedIn(ServerItem server, ArrayList<CluedoGameClient> gameslist,String servername,String serverip,String status){
		setGamesOnServer(server,gameslist);		
		updateNetworkActorGui(servername,serverip,status);
		server.setStatus(ServerStatus.connected);
		setSelectedServer(server);
	}
	
	public void setGameEndedOnServer(String nick, ServerItem server,int gameID){
		CluedoGameClient game = server.getGameByGameID(gameID);
		game.killCommunicator();
		game.setGameState(GameStates.ended);
		setGameEndedGui(gameID);
		if ( ! nick.equals("")){
			game.somebodyIsAccusing(nick);
		}
		
	}
	
	public void setGamesOnServer(ServerItem server, ArrayList<CluedoGameClient> glist){
		server.addGames(glist);
		addGamesToGui(glist);
	}
	
	public boolean addServer(ServerItem server,String status){
		if (serverPool.add(server)){
			addNetworkActorToGui(server.getGroupName(),server.getIpString(),status);
			//setSelectedServer(server);
			return true;
		}
		
		return false;
	}
	
	public boolean removeServer(ServerItem server){
		auxx.loginfo("trying to remove server "+server.getGroupName());
		killAllGamesOnServer(server);
		if (serverPool.remove(server)){
			removeNetworkActorFromGui(server.getGroupName(), server.getIpString());
			if (server == selectedServer){
				emptyGamesList();
				emptyClientNicks();
			}			
			return true;
		}		
		return false;
			
	}
	
	public void killAllGamesOnServer(ServerItem server){
		server.killAllGames();
	}	
	
	public ServerItem getServerByIndex(int index){
		return serverPool.get(index);
	}
	
	public ServerItem getServerByID(String name, String ip){
		for (ServerItem s: serverPool)
			if (s.getGroupName().equals(name) && s.getIpString().equals(ip))
				return s;
		return null;
	}
	
	@Override
	public CluedoClientGUI getGui() {
		return (CluedoClientGUI) super.getGui();
	}
	
	public void sayGoodbye(){
		leaveAllGames();
		serverPool.sendToAll(NetworkMessages.disconnectMsg());
	}
	
	public void addGamesToGui(ArrayList<CluedoGameClient> glist ){
		for (CluedoGameClient cg: glist){
			addGameToGui(cg.getGameId(), cg.getNicksConnected(),cg.getWatchersConnected(),cg.getGameState(),cg.getServer().getGroupName(),cg.getServer().getIpString());
		}
	}
	
	public void leaveAllGames(){
		ArrayList<CluedoGameClient> glist = serverPool.getGamesConnected();
		for (CluedoGameClient g: glist)
			auxx.sendTCPMsg(g.getServer().getSocket(), NetworkMessages.leave_gameMsg(g.getGameId()));
	}
	
	public void leaveGame(int gameID){
		selectedServer.getGameByGameID(gameID).kill();
	}
	
	public void setNicksGui(ServerItem server){
		getGui().setClientNicks(server.getClientNicks());
	}
	
	public void addWatcherToGame(ServerItem server,int gameID,String nick){
		CluedoGameClient game = server.getGameByGameID(gameID);
		game.addWatcher(nick);
		if (server == getSelectedServer()){
			updateGame(gameID, game.getNicksConnected(),game.getWatchersConnected());
		}
		refreshGamesListServer(server);
	};	
	
	public void joinGameAsWatcher(ServerItem server,int gameID){
		server.sendMsg(NetworkMessages.watch_gameMsg(gameID));
	}
	
	public void emptyClientNicks(){
		getGui().emptyClientNicks();
	  }
	
	public void handleStateUpdate(int gameID,String gameNick,ServerItem server,ArrayList<String> newStates){
		CluedoGameClient game = server.getGameByGameID(gameID); 
		if (game != null){
			if (game.hasPlayerConnectedByNick(gameNick)){ //never ever trust anyone
				game.getPlayerByNick(gameNick).setPossibleStatesFromStringList(newStates);
				if (gameNick.equals(server.getMyNick())){ // its me
					if (newStates.contains(PlayerStates.roll_dice.getName())){
						game.itsYourTurn();
					}
					else if (newStates.contains(PlayerStates.disprove.getName())){
						game.disprove();
					}
					else if (newStates.contains(PlayerStates.do_nothing.getName())){
						game.currentPlayerToNothing();
					}
					else if (newStates.contains(PlayerStates.end_turn.getName())){ //KI
						game.moved();	
					}
					else {
						game.handlePostMoveKI();
					}
//					else if(newStates.contains(PlayerStates.suspect.getName())){
////						Platform.runLater(() -> {game.changeZugFensterButtons(newStates);});
//					}
//					else if(newStates.contains(PlayerStates.use_secret_passage.getName())){
////						Platform.runLater(() -> {game.changeZugFensterButtons(newStates);});
//					}
					
					Platform.runLater(() -> {game.changeZugFensterButtons(newStates);});
					game.showyourstates("you can : "+auxx.formatStringList(newStates, "or"));
					System.out.println("stathandle thinks impartofgame");
				}
				else if (game.hasWatcherConnectedByNick(server.getMyNick())){
					game.getCommunicator().setTitle(gameNick+ "is about to "+ auxx.formatStringList(newStates, "or"));
				}
				else { //its some other bloke
					if (newStates.contains(PlayerStates.roll_dice.getName())){
						game.itsSomeonesTurn(gameNick);			
					}
					game.showothersstates(gameNick+" can :"+auxx.formatStringList(newStates, "or"));				
				}				
			}			
		}
	}	

	public void handleSuspicion(int gameID, CluedoStatement suspicion,ServerItem server) {
		CluedoGameClient game = server.getGameByGameID(gameID);
		if (game.hasPlayerConnectedByNick(server.getMyNick())){ //never ever trust anyone
			game.setCurrentSuspicion(suspicion);
		}		
		game.showSuspicionCards(gameID, suspicion);
		game.moveForSuspicion(gameID,suspicion);
	}	
	
	public void handleDisprove(int gameID,ServerItem server){
		server.getGameByGameID(gameID).disprove();
	}

	public void noDisprove(int gameID, ServerItem server) {
		CluedoGameClient game = server.getGameByGameID(gameID);
		if (game != null){
			game.showyourstates("No disprove this round");
		}
		
	}

	public void setCardsOnGame(int gameID,ArrayList<String> cards,ServerItem server) {
		CluedoGameClient game = server.getGameByGameID(gameID);
		game.setCardsForPlayer(cards);		
	}


	public void handleDisproved(int gameID, ServerItem server, String card) {
		server.getGameByGameID(gameID).showDisprove(card);

	}
	
	public void openWinner(){
		
	}
	
	public void openLoser(){
		
	}
	
}
