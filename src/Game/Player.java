package Game;

import java.awt.image.BufferedImage;
/*
 * 角色的性质：
 * 1、缓冲衰减的公示
 * Empty的特性：
 * 自动寻路
 * 速度比玩家慢
 * 碰撞后触发游戏结束
 */
public class Player {
	public static int oneFloor_Y=180,twoFloor_Y=70;
	public static int oneFloorGame;
	public static int twoFloorGame;
	int Y,X;
	int W,H;
	//缓冲
	int s;
	float t,v0,e;
	BufferedImage img;
	public Player() {
		s=0;
		t=0.1f;
		v0=0f;
		e=2f;
		X=40;
		Y=oneFloor_Y;
		W=40;
		H=60;
		oneFloorGame=oneFloor_Y+H;
		twoFloorGame=twoFloor_Y+H;
	}
	public void delayMove() {
//		System.out.println(GamePanel.Speed);
		if(v0>0) {
			v0-=e*t;
			if(t*v0<1) {
				v0=0;
				GamePanel.Speed=0;
			}
		}else if(v0<0){
			v0=0;
			GamePanel.Speed=0;
		}
		if(X<0) {
			v0=0;
//			System.out.println(v0);
			X=0;
			GamePanel.Speed=0;
		}else if(X>GameFrame.fw-W) {
//			v0=0;
			GamePanel.Speed=0;
			X=GameFrame.fw-W;
		}
		s=(int) (t*v0);
		if(GamePanel.isRight) {
			s=Math.abs(s);
		}else {
			s=-Math.abs(s);
		}
//		X=(int) (X+s);
	}
}
