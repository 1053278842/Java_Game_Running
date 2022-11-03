package Game;

import java.awt.image.BufferedImage;
import Game.ToolImg;
/*
 * 角色的性质：
 * 1、缓冲衰减的公示
 * Empty的特性：
 * 自动寻路
 * 速度比玩家慢
 * 碰撞后触发游戏结束
 */
public class Empty {
	public static int oneFloor_Y=180,twoFloor_Y=72;
	int Y,X;
	int W,H;
	int minusCollisonDistance=15;
	BufferedImage img;
	//empty的动作属性
	float speed_Multipe=1.5f;
	int vector_x=0;
	int vector_y=0;
	public Empty() {
		img=ToolImg.getImg("/img/Empty.png");
		W=40;
		H=60;
//		X=GameFrame.fw-W;
		Y=oneFloor_Y;
	}
//	public void AutoMove(Player p, List<Building> halfInteractives) {
//		/*
//		 * 1、比较玩家的左右位置从而确定行走方向
//		 * 2、查看玩家的高低位置
//		 * 3、寻找最近的半交互物体
//		 * 4、给empty设置交互功能
//		 */
//		if(X>p.X+10) {
//			vector_x=-Math.abs((int) (GamePanel.Speed_player/speed_Multipe));
//		}else if(X<p.X-10) {
//			vector_x=Math.abs((int) (GamePanel.Speed_player/speed_Multipe));
//		}
//		//玩家上二楼的情况下
//		Building box = null;
//		if(p.Y<Y) {
//			//遍历一楼的半交互对象
//			box=getNearestBox(1,halfInteractives);
//		}else if(p.Y>Y){
//			box=getNearestBox(2,halfInteractives);
//		}
//		if(p.Y!=Y) {
//			if(X-box.X>0) {
//				vector_x=-Math.abs((int) (GamePanel.Speed_player/speed_Multipe));
//			}else if(X-box.X<0){
//				vector_x=Math.abs((int) (GamePanel.Speed_player/speed_Multipe));
//			}
//			//到达情况下
//			if(Math.abs(X-box.X)<minusCollisonDistance) {
//				if(box.name=="ladder") {
//					if(Y!=Player.twoFloor_Y+p.H-H&&Y!=Player.oneFloor_Y+p.H-H)
//						vector_x=0;
//					vector_y=-Math.abs((int) (GamePanel.Speed_player/speed_Multipe));
//					if(Y+vector_y+H<Player.twoFloor_Y+p.H) {
//						vector_y=0;
//						Y=Player.twoFloor_Y+p.H-H;
//					}
//				}
//			}
//		}
//		X+=vector_x;
//		Y+=vector_y;
//	}
//	private Building getNearestBox(int h,List<Building> list) {
//		List<Building> dts=new ArrayList<Building>();
//		for(int i=0;i<list.size();i++) {
//			Building box=list.get(i);
//			if(box.name=="ladder") {
//				//寻找最近的梯子
//				dts.add(box);
//			}else if(box.name=="door") {
//				//最近的电梯
//				if(h==1) {		
//					if(box.Y>Player.twoFloor_Y)
//						dts.add(box);
//				}else {
//					if(box.Y<Player.twoFloor_Y)
//						dts.add(box);
//				}
//			}
//		}
//		int Min=Math.abs(dts.get(0).X-X);
//		int index=0;
//		for(int i=0;i<dts.size();i++) {
//			Building box=dts.get(i);
//			if(Min>Math.abs(box.X-X)) {
//				Min=Math.abs(box.X-X);
//				index=i;
//			}
//		}
//		return dts.get(index);
//	}
	public boolean collisionP(Player p,boolean isMove) {
		//right-left
		if((X+minusCollisonDistance<p.X+p.W&&X+W>p.X+p.W
				||X-minusCollisonDistance+W>p.X&&X<p.X)&&(Y+H/2>p.Y&&Y+H/2<p.Y+p.H)) {
			if(isMove)
				return true;
		}
		return false;
	}
	public void move() {
		X+=GamePanel.direSpeed;
	}
}