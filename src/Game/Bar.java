package Game;

import Game.Player;

public class Bar {
	int x,y,w,h;
	Bar(int pw){
		w=0;
		h=5;
	}
	public void position(Player p){
		x=p.X;
		y=p.Y-10;
	}
}
