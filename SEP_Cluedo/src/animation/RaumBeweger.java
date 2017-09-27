
package animation;


import kacheln.Kachel;
import kacheln.KachelContainer;
import kommunikation.PlayerCircleManager;
import cluedoNetworkLayer.CluedoPlayer;
import cluedoNetworkLayer.CluedoPosition;
import enums.Rooms;

public class RaumBeweger {

	public final PlayerCircleManager pcManager;
	private KachelContainer kacheln;

	private Kachel zielImRaum;
	
	public RaumBeweger(PlayerCircleManager pcm, KachelContainer kacheln){
		pcManager = pcm;
		this.kacheln = kacheln;
	}

	public Rooms checkRaum(Kachel aufenthalt) {
		if(aufenthalt.getRaum() != null){
			return aufenthalt.getRaum();
		}
		else{
			return null;
		}
	}
	
	public Kachel positonInDerTuer(Rooms raum){
		Kachel tuerKachel = new Kachel();
		if (raum == Rooms.study){
			tuerKachel.setPosition(new CluedoPosition(6,3));
		}
		else if (raum == Rooms.hall){
			tuerKachel.setPosition(new CluedoPosition(11,6));
		}
		else if (raum == Rooms.lounge){
			tuerKachel.setPosition(new CluedoPosition(17,5));
		}
		else if (raum == Rooms.diningroom){
			tuerKachel.setPosition(new CluedoPosition(16,12));
		}
		else if (raum == Rooms.kitchen){
			tuerKachel.setPosition(new CluedoPosition(19,18));
		}
		else if (raum == Rooms.ballroom){
			tuerKachel.setPosition(new CluedoPosition(15,19));
		}
		else if (raum == Rooms.conservatory){
			tuerKachel.setPosition(new CluedoPosition(4,19));
		}
		else if (raum == Rooms.billiard){
			tuerKachel.setPosition(new CluedoPosition(5,15));
		}
		else if (raum == Rooms.library){
			tuerKachel.setPosition(new CluedoPosition(6,8));
		}
		else if (raum == Rooms.pool){
			tuerKachel.setPosition(new CluedoPosition(11,8));
		}
		
		return tuerKachel;
	}
	
	public Kachel positionImRaumWaffe(Rooms raum){
		Kachel zielImRaumWaffe = kacheln.getKacheln()[11][11];
		if (raum == Rooms.study){
			zielImRaumWaffe = kacheln.getKacheln()[3][4];
		}
		else if (raum == Rooms.hall){
			zielImRaumWaffe = kacheln.getKacheln()[2][9];
		}
		else if (raum == Rooms.lounge){
			zielImRaumWaffe = kacheln.getKacheln()[3][17];
		}
		else if (raum == Rooms.diningroom){
			zielImRaumWaffe = kacheln.getKacheln()[9][19];
		}
		else if (raum == Rooms.kitchen){
			zielImRaumWaffe = kacheln.getKacheln()[18][21];
		}
		else if (raum == Rooms.ballroom){
			zielImRaumWaffe = kacheln.getKacheln()[17][11];
		}
		else if (raum == Rooms.conservatory){
			zielImRaumWaffe = kacheln.getKacheln()[19][2];
		}
		else if (raum == Rooms.billiard){
			zielImRaumWaffe = kacheln.getKacheln()[13][5];
		}
		else if (raum == Rooms.library){
			zielImRaumWaffe = kacheln.getKacheln()[10][5];
		}
		else if (raum == Rooms.pool){
			zielImRaumWaffe = kacheln.getKacheln()[11][11];
		}
		
		return zielImRaumWaffe;
	}
	
	public Kachel positionInRaum(Rooms raum) {
		zielImRaum = kacheln.getKacheln()[11][11];
		if(pcManager.getCurrentPlayer().getCluedoPerson().getColor() == "purple"){
			if(raum == Rooms.hall){
				System.out.println("haaaaaaaaaaaaall");
				zielImRaum.getPosition().setX(11);
				zielImRaum.getPosition().setY(1);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.lounge){
				System.out.println("loooooounge");
				zielImRaum.getPosition().setX(19);
				zielImRaum.getPosition().setY(2);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.diningroom){
				System.out.println("diningrooom");
				zielImRaum.getPosition().setX(19);
				zielImRaum.getPosition().setY(11);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.kitchen){
				System.out.println("kiiiiiiiitchen");
				zielImRaum.getPosition().setX(20);
				zielImRaum.getPosition().setY(20);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.ballroom){
				System.out.println("baaaaaaaallroom");
				zielImRaum.getPosition().setX(11);
				zielImRaum.getPosition().setY(19);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.conservatory){
				System.out.println("cooooonservaaatory");
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(21);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.billiard){
				System.out.println("biiiilliiard");
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(13);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.library){
				System.out.println("libraryyyyyy");
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(7);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.study){
				System.out.println("stuuuuuuuuuuudy");
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(1);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.pool){
				System.out.println("poooooooooool");
				zielImRaum.getPosition().setX(10);
				zielImRaum.getPosition().setY(9);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
		}
		else if(pcManager.getCurrentPlayer().getCluedoPerson().getColor() == "blue"){
			if(raum == Rooms.hall){
				zielImRaum.getPosition().setX(11);
				zielImRaum.getPosition().setY(2);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.lounge){
				zielImRaum.getPosition().setX(20);
				zielImRaum.getPosition().setY(2);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.diningroom){
				zielImRaum.getPosition().setX(19);
				zielImRaum.getPosition().setY(12);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.kitchen){
				zielImRaum.getPosition().setX(21);
				zielImRaum.getPosition().setY(20);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.ballroom){
				zielImRaum.getPosition().setX(11);
				zielImRaum.getPosition().setY(20);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.conservatory){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(21);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.billiard){
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(14);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.library){
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(8);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.study){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(1);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.pool){
				zielImRaum.getPosition().setX(11);
				zielImRaum.getPosition().setY(9);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
		
		}
		else if(pcManager.getCurrentPlayer().getCluedoPerson().getColor() == "green"){
			if(raum == Rooms.hall){
				zielImRaum.getPosition().setX(11);
				zielImRaum.getPosition().setY(3);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.lounge){
				zielImRaum.getPosition().setX(21);
				zielImRaum.getPosition().setY(2);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.diningroom){
				zielImRaum.getPosition().setX(19);
				zielImRaum.getPosition().setY(13);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.kitchen){
				zielImRaum.getPosition().setX(22);
				zielImRaum.getPosition().setY(20);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.ballroom){
				zielImRaum.getPosition().setX(11);
				zielImRaum.getPosition().setY(21);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.conservatory){
				zielImRaum.getPosition().setX(4);
				zielImRaum.getPosition().setY(21);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.billiard){
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(15);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.library){
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(9);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.study){
				zielImRaum.getPosition().setX(4);
				zielImRaum.getPosition().setY(1);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.pool){
				zielImRaum.getPosition().setX(12);
				zielImRaum.getPosition().setY(9);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
		
		}
		else if(pcManager.getCurrentPlayer().getCluedoPerson().getColor() == "white"){
			if(raum == Rooms.hall){
				zielImRaum.getPosition().setX(12);
				zielImRaum.getPosition().setY(1);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.lounge){
				zielImRaum.getPosition().setX(19);
				zielImRaum.getPosition().setY(3);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.diningroom){
				zielImRaum.getPosition().setX(20);
				zielImRaum.getPosition().setY(11);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.kitchen){
				zielImRaum.getPosition().setX(20);
				zielImRaum.getPosition().setY(21);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.ballroom){
				zielImRaum.getPosition().setX(12);
				zielImRaum.getPosition().setY(19);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.conservatory){
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(22);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.billiard){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(13);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.library){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(7);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.study){
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(2);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.pool){
				zielImRaum.getPosition().setX(10);
				zielImRaum.getPosition().setY(13);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
		
		}
		else if(pcManager.getCurrentPlayer().getCluedoPerson().getColor() == "yellow"){
			if(raum == Rooms.hall){
				zielImRaum.getPosition().setX(12);
				zielImRaum.getPosition().setY(2);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.lounge){
				zielImRaum.getPosition().setX(20);
				zielImRaum.getPosition().setY(3);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.diningroom){
				zielImRaum.getPosition().setX(20);
				zielImRaum.getPosition().setY(12);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.kitchen){
				zielImRaum.getPosition().setX(21);
				zielImRaum.getPosition().setY(21);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.ballroom){
				zielImRaum.getPosition().setX(12);
				zielImRaum.getPosition().setY(20);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.conservatory){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(22);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.billiard){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(14);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.library){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(8);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.study){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(2);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.pool){
				zielImRaum.getPosition().setX(11);
				zielImRaum.getPosition().setY(13);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
		
		}
		else if(pcManager.getCurrentPlayer().getCluedoPerson().getColor() == "red"  || pcManager.getCurrentPlayer().getCluedoPerson().getColor() == "Peter"){
			if(raum == Rooms.hall){
				zielImRaum.getPosition().setX(12);
				zielImRaum.getPosition().setY(3);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.lounge){
				zielImRaum.getPosition().setX(21);
				zielImRaum.getPosition().setY(3);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.diningroom){
				zielImRaum.getPosition().setX(20);
				zielImRaum.getPosition().setY(13);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.kitchen){
				zielImRaum.getPosition().setX(22);
				zielImRaum.getPosition().setY(21);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.ballroom){
				zielImRaum.getPosition().setX(12);
				zielImRaum.getPosition().setY(21);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.conservatory){
				zielImRaum.getPosition().setX(4);
				zielImRaum.getPosition().setY(22);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.billiard){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(15);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.library){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(9);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.study){
				zielImRaum.getPosition().setX(4);
				zielImRaum.getPosition().setY(2);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.pool){
				zielImRaum.getPosition().setX(12);
				zielImRaum.getPosition().setY(13);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
		
		}
		return zielImRaum;
	}
	
	
	////////////////////////////////////////////////////////// NOCHMAAAAAAAAAAAAAAAAL ///////////////////////////////////////////////////////////
	
	
	
	public Kachel positionInRaumMitPlayer(CluedoPlayer playerDurchgezogen, Rooms raum) {
		zielImRaum = kacheln.getKacheln()[11][11];
		if(playerDurchgezogen.getCluedoPerson().getColor() == "purple"){
			if(raum == Rooms.hall){
				System.out.println("haaaaaaaaaaaaall");
				zielImRaum.getPosition().setX(11);
				zielImRaum.getPosition().setY(1);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.lounge){
				System.out.println("loooooounge");
				zielImRaum.getPosition().setX(19);
				zielImRaum.getPosition().setY(2);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.diningroom){
				System.out.println("diningrooom");
				zielImRaum.getPosition().setX(19);
				zielImRaum.getPosition().setY(11);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.kitchen){
				System.out.println("kiiiiiiiitchen");
				zielImRaum.getPosition().setX(20);
				zielImRaum.getPosition().setY(20);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.ballroom){
				System.out.println("baaaaaaaallroom");
				zielImRaum.getPosition().setX(11);
				zielImRaum.getPosition().setY(19);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.conservatory){
				System.out.println("cooooonservaaatory");
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(21);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.billiard){
				System.out.println("biiiilliiard");
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(13);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.library){
				System.out.println("libraryyyyyy");
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(7);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.study){
				System.out.println("stuuuuuuuuuuudy");
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(1);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.pool){
				System.out.println("poooooooooool");
				zielImRaum.getPosition().setX(10);
				zielImRaum.getPosition().setY(9);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
		}
		else if(playerDurchgezogen.getCluedoPerson().getColor() == "blue"){
			if(raum == Rooms.hall){
				zielImRaum.getPosition().setX(11);
				zielImRaum.getPosition().setY(2);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.lounge){
				zielImRaum.getPosition().setX(20);
				zielImRaum.getPosition().setY(2);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.diningroom){
				zielImRaum.getPosition().setX(19);
				zielImRaum.getPosition().setY(12);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.kitchen){
				zielImRaum.getPosition().setX(21);
				zielImRaum.getPosition().setY(20);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.ballroom){
				zielImRaum.getPosition().setX(11);
				zielImRaum.getPosition().setY(20);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.conservatory){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(21);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.billiard){
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(14);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.library){
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(8);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.study){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(1);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.pool){
				zielImRaum.getPosition().setX(11);
				zielImRaum.getPosition().setY(9);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
		
		}
		else if(playerDurchgezogen.getCluedoPerson().getColor() == "green"){
			if(raum == Rooms.hall){
				zielImRaum.getPosition().setX(11);
				zielImRaum.getPosition().setY(3);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.lounge){
				zielImRaum.getPosition().setX(21);
				zielImRaum.getPosition().setY(2);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.diningroom){
				zielImRaum.getPosition().setX(19);
				zielImRaum.getPosition().setY(13);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.kitchen){
				zielImRaum.getPosition().setX(22);
				zielImRaum.getPosition().setY(20);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.ballroom){
				zielImRaum.getPosition().setX(11);
				zielImRaum.getPosition().setY(21);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.conservatory){
				zielImRaum.getPosition().setX(4);
				zielImRaum.getPosition().setY(21);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.billiard){
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(15);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.library){
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(9);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.study){
				zielImRaum.getPosition().setX(4);
				zielImRaum.getPosition().setY(1);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.pool){
				zielImRaum.getPosition().setX(12);
				zielImRaum.getPosition().setY(9);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
		
		}
		else if(playerDurchgezogen.getCluedoPerson().getColor() == "white"){
			if(raum == Rooms.hall){
				zielImRaum.getPosition().setX(12);
				zielImRaum.getPosition().setY(1);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.lounge){
				zielImRaum.getPosition().setX(19);
				zielImRaum.getPosition().setY(3);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.diningroom){
				zielImRaum.getPosition().setX(20);
				zielImRaum.getPosition().setY(11);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.kitchen){
				zielImRaum.getPosition().setX(20);
				zielImRaum.getPosition().setY(21);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.ballroom){
				zielImRaum.getPosition().setX(12);
				zielImRaum.getPosition().setY(19);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.conservatory){
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(22);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.billiard){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(13);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.library){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(7);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.study){
				zielImRaum.getPosition().setX(2);
				zielImRaum.getPosition().setY(2);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.pool){
				zielImRaum.getPosition().setX(10);
				zielImRaum.getPosition().setY(13);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
		
		}
		else if(playerDurchgezogen.getCluedoPerson().getColor() == "yellow"){
			if(raum == Rooms.hall){
				zielImRaum.getPosition().setX(12);
				zielImRaum.getPosition().setY(2);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.lounge){
				zielImRaum.getPosition().setX(20);
				zielImRaum.getPosition().setY(3);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.diningroom){
				zielImRaum.getPosition().setX(20);
				zielImRaum.getPosition().setY(12);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.kitchen){
				zielImRaum.getPosition().setX(21);
				zielImRaum.getPosition().setY(21);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.ballroom){
				zielImRaum.getPosition().setX(12);
				zielImRaum.getPosition().setY(20);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.conservatory){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(22);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.billiard){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(14);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.library){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(8);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.study){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(2);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.pool){
				zielImRaum.getPosition().setX(11);
				zielImRaum.getPosition().setY(13);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
		
		}
		else if(playerDurchgezogen.getCluedoPerson().getColor() == "red"  || pcManager.getCurrentPlayer().getCluedoPerson().getColor() == "Peter"){
			if(raum == Rooms.hall){
				zielImRaum.getPosition().setX(12);
				zielImRaum.getPosition().setY(3);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.lounge){
				zielImRaum.getPosition().setX(21);
				zielImRaum.getPosition().setY(3);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.diningroom){
				zielImRaum.getPosition().setX(20);
				zielImRaum.getPosition().setY(13);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.kitchen){
				zielImRaum.getPosition().setX(22);
				zielImRaum.getPosition().setY(21);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.ballroom){
				zielImRaum.getPosition().setX(12);
				zielImRaum.getPosition().setY(21);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.conservatory){
				zielImRaum.getPosition().setX(4);
				zielImRaum.getPosition().setY(22);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.billiard){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(15);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.library){
				zielImRaum.getPosition().setX(3);
				zielImRaum.getPosition().setY(9);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.study){
				zielImRaum.getPosition().setX(4);
				zielImRaum.getPosition().setY(2);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
			else if(raum == Rooms.pool){
				zielImRaum.getPosition().setX(12);
				zielImRaum.getPosition().setY(13);
				zielImRaum = kacheln.getKacheln()[zielImRaum.getPosition().getY()][zielImRaum.getPosition().getX()];
			}
		
		}
		return zielImRaum;
	}
	
}	