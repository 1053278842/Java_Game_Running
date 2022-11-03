package Game;

public class CollisionBox {
	int x,y;
	int w,h;
	public CollisionBox() {
		x=0;
		y=0;
		w=40;
		h=80;
	}
	public boolean isCollision(Player player) {
		
		 if(player.X-GamePanel.direSpeed>x-player.W&&player.X-GamePanel.direSpeed<x+w&&player.Y<y+h&&player.Y>y-player.H) {
			 return true;
		 }
		return false;
	}
	public void move() {
		x+=GamePanel.direSpeed;
	}
	public static boolean collisionHandling(Player player) {
		if(player.X>GameFrame.fw-player.W) {//右边界：人物移动限制范围
			 return true;
		 }
		 if(player.X<0) {//左边界：人物移动限制范围
			 return true;
		 }
		 return false;
	}
}
