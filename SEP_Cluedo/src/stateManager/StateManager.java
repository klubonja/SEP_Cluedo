package stateManager;

import java.util.Stack;

import kommunikation.PlayerCircleManager;
import kommunikation.ServerBeweger;
import staticClasses.auxx;
import cluedoNetworkLayer.CluedoPlayer;
import enums.GameStates;
import enums.PlayerStates;

public class StateManager {
	GameStates currentState;
	CluedoStateMachine stateMachine = new CluedoStateMachine(PlayerStates.do_nothing);
	PlayerCircleManager pcm;
	ServerBeweger beweger;
	
	public StateManager(PlayerCircleManager pcm,ServerBeweger beweger){
		this.pcm = pcm;
		this.beweger = beweger;
	}
	
	public boolean setNextTurnRec(int dept){
		if (dept == 0)	return false; //endrecursion condition  game will be ended
		
		Stack<CluedoPlayer> players  = pcm.getPlayers();
		for (int i = 0;i < pcm.getSize(); i++){
			if (i == pcm.getCurrentPlayerIndex()){
				CluedoPlayer nextplayer = players.get(i);
				nextplayer.setPossibleStates(stateMachine.getSucStates(PlayerStates.do_nothing));
				if (nextplayer.hasAccused()){
					nextplayer.setPossibleState(PlayerStates.do_nothing);
					pcm.next(); // HIER WIRD DAS EINZIGE MAL IN DIESER KLASSE DER SPIELERPOINTER VERÄNDERT
					setNextTurnRec(dept-1);
					break;
				}
				else if (!beweger.secretPassagePossible(nextplayer.getCluedoPerson())) {
					nextplayer.removeFromPossibleStates(PlayerStates.use_secret_passage);
				}
				nextplayer.removeFromPossibleStates(PlayerStates.disprove);
				auxx.loginfo("currentplayer :"+ nextplayer.getNick()+" has states :"+nextplayer.getStatesAsStringFormatted());
			}
			else{
				players.get(i).setPossibleState(PlayerStates.do_nothing); // hier werden possible moves geleert und do nothing hinzugefügt
			}			
		}
		
		return true;
	}
	
	public void transitionByAction(PlayerStates actionstate){
		CluedoPlayer curplayer = pcm.getCurrentPlayer();
		curplayer.setPossibleStates(
					stateMachine.getSucStates(actionstate)
					);
		switch (actionstate) {
			case move :
				if (!beweger.isRaum(curplayer.getPosition())){
					curplayer.removeFromPossibleStates(PlayerStates.suspect);
				}
				break;
		}
	}
	
	public void handleDisprove(int curindex){
		for (int i = 0; i < pcm.getSize(); i++ ){
			if (curindex == i) pcm.getPlayerByIndex(i).setPossibleState(PlayerStates.disprove);
			else pcm.getPlayerByIndex(i).setPossibleState(PlayerStates.do_nothing);
		}
	}
}
