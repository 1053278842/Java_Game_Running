package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import Game.AutoFindWay;
import Game.Bar;
import Game.Building;
import Game.Gird;
import Game.GirdPosition;
import Game.ToolImg;

public class GamePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Gird gird=new Gird();
	public static AutoFindWay afw=new AutoFindWay();
	//
	static int Speed_player=2;//真角色速度
	static int Speed=0;//角色速度
	static int direSpeed=2;//建筑物速度
	//状态
	public int emptyTimeNum=0;
	boolean isCanUsingCollisionBox=true;
	boolean isPlayerHide=false;
	final int intervalTime=20;
	static boolean isRight=true;
	boolean isOnLadder=false;	
	boolean isReadingBar=false;
	int pigmentNum=3;//可收集到的颜料数量
	int getPigmentNum=0;//已经收到的颜料数
	boolean isPigmentMoveAnimation=false;
	static InteractiveBox isUsingBox;
	boolean isTime=false;
	Building usingDoor;
	boolean isBrushWin=false;//是否能涂鸦了此时出现箭头
	boolean isBrushedWin=false;//是否能涂鸦了此时箭头消失，图像出现，游戏结束
	//助跑功能
	int keyTime=0;
	//进度条
	Bar bar;
	int barTime1=1500/intervalTime;
	int barTime2=2500/intervalTime;
	int barNum=0;//计次
	boolean isCanSearch=true;//实现不可一直搜索同一个对象
	//Building
	Building road=new Building("road");
	Building bd=new Building("hourse");
	Building bd2=new Building("hourse");
	Building bd3=new Building("hourse");
	Player player =new Player();
	Building wl=new Building("weilan");
	BufferedImage bg;
	Building jt=new Building("jiantou");
	//半交互
	Building ld=new Building("ladder");
	Building ld2=new Building("ladder");
	Building door1=new Building("door",1);
	Building door2=new Building("door",1);
	Building door3=new Building("door",2);
	Building door4=new Building("door",2);
	//CollisionBox
	CollisionBox cls1=new CollisionBox();
	CollisionBox cls2=new CollisionBox();
	CollisionBox cls3=new CollisionBox();
	//交互
	InteractiveBox st1=new InteractiveBox();;
	InteractiveBox st2=new InteractiveBox();;
	InteractiveBox st3=new InteractiveBox();;
	InteractiveBox st4=new InteractiveBox();;
	InteractiveBox wBarrel1=new InteractiveBox("WoodenBox");
	InteractiveBox wBarrel2=new InteractiveBox("WoodenBox");
	InteractiveBox wood=new InteractiveBox("muban");
	InteractiveBox winP=new InteractiveBox("winP");
	//敌人
	Empty empty=new Empty();
	//集合
	public static List<Building> lds=new ArrayList<Building>();	//会受角色影响而移动的半交互集合
	public static List<Building> bgs=new ArrayList<Building>();	//被移动的背景集合
	public static List<CollisionBox> clsboxes=new ArrayList<CollisionBox>();	//碰撞箱集合
	public static List<InteractiveBox> itabox=new ArrayList<InteractiveBox>();	//交互集合
	public static List<PigmentBox> pigmentUI=new ArrayList<PigmentBox>();	//颜料UI集合
	public static  List<GirdPosition> nearestWay=new ArrayList<GirdPosition>();	//敌人自动最近路线
	//A*路径
	//----------------------------------------------初始化----------------------------------
	 GamePanel(GameFrame f) {
		 setLayout(null);
		 //提示语句
//		 System.out.println("地图全长"+(road.W-road.X));//640
		 //

		bg=ToolImg.getImg("/Img/GameBack.png");
		bar=new Bar(player.W);
		//建筑物初始位置
		bd2.X=bd.X+bd.W-20;
		bd3.X=bd2.X+bd2.W+40;
		wood.X=bd2.X+bd2.W-15;
		wood.Y=Player.twoFloor_Y+player.H-wood.H+5;
		//CollisonBox位置
		cls1.w=10;
		cls1.x=bd2.X+cls1.w/2;
		cls1.y=Player.twoFloor_Y+player.H-cls1.h;
		cls2.x=bd2.X+bd2.W-10;
		cls2.w=40+10*2;
		cls2.y=Player.twoFloor_Y+player.H-cls2.h;
		cls3.x=bd.X-cls3.w;
		cls3.w=40+10;
		cls3.y=Player.twoFloor_Y+player.H-cls3.h;
		//半交互位置
		ld.X=bd.X+bd.W-60;
		ld.Y=player.H+player.Y-ld.H;
		ld2.X=bd3.X+60;
		ld2.Y=player.H+player.Y-ld2.H;
		door1.X=bd.X+60;
		door1.Y=Player.oneFloor_Y+player.H-door1.H-8;
		door2.X=bd2.X+60;
		door2.Y=Player.twoFloor_Y+player.H-door2.H-10;
		door3.X=bd.X+30;
		door3.Y=Player.twoFloor_Y+player.H-door3.H-8;
		door4.X=bd3.X+bd3.W-20-door4.W;
		door4.Y=Player.twoFloor_Y+player.H-door4.H-10;
		//全交互位置
		st1.X=bd.X+20;
		st1.Y=Player.twoFloor_Y+player.H-st1.H-6;
		st2.X=bd.X+bd.W-20;
		st2.Y=Player.oneFloor_Y+player.H-st2.H-6;
		st3.X=bd2.X+20;
		st3.Y=Player.twoFloor_Y+player.H-st3.H-6;
		st4.X=bd3.X+20;
		st4.Y=Player.oneFloor_Y+player.H-st4.H-6;
		wBarrel1.X=bd3.X+10;
		wBarrel1.Y=Player.twoFloor_Y+player.H-wBarrel1.H-6;
		wBarrel2.X=bd3.X+bd3.W-10-wBarrel2.W;;
		wBarrel2.Y=Player.oneFloor_Y+player.H-wBarrel2.H-6;
		winP.X=bd.X+bd.W/2-winP.W/2+20;
		winP.Y=Player.twoFloor_Y+player.H-winP.H-6-winP.H/2;
			//指示箭头在涂鸦上方
		jt.X=winP.X+winP.W/2-jt.W/2;
		jt.Y=winP.Y-jt.H*2;
		//Empty
		empty.X=road.X+road.W-empty.W;
		//背景集合bgs
		bgs.add(road);
		bgs.add(wl);
		bgs.add(bd);
		bgs.add(bd2);
		bgs.add(bd3);
			//这里的半交互对象也归属于背景集合，有相同的性质
		bgs.add(door1);
		bgs.add(door2);
		bgs.add(door3);
		bgs.add(door4);
		bgs.add(ld);
		bgs.add(ld2);
		bgs.add(jt);
		//交互梯子(半交互)集合
		lds.add(ld);
		lds.add(ld2);
		lds.add(door1);
		lds.add(door2);
		lds.add(door3);
		lds.add(door4);
		//全交互集合
		itabox.add(st1);
		itabox.add(st2);
		itabox.add(st3);
		itabox.add(st4);
		itabox.add(wBarrel1);
		itabox.add(wBarrel2);

		//碰撞体集合
		clsboxes.add(cls1);
//		clsboxes.add(cls2);
		clsboxes.add(cls3);
		 //////////////////////
		 gird.drawP(road.X,road.X+road.W);//重绘地图
		 nearestWay=afw.getWay(player, empty);
		 //画线
//		 for(GirdPosition p:nearestWay) {
//			 GirdRect gr=new GirdRect(p);
//			 girdrect.add(gr);
//		 }
		 System.out.println("游戏开始");
		 //////////////////////
		//随机分布颜料到交互对象中
		int i=0;
		while(i<pigmentNum) {
			for(int n=0;n<itabox.size();n++) {
//				System.out.println(i);
				InteractiveBox itb=itabox.get(n);
				if(!itb.isHasPigment) {//判断是否已经颜料
					double randomNum_Add=Math.random();
					if(randomNum_Add>0.5) {//随机概率加入颜料
						itb.isHasPigment=true;
						i++;
						//分配颜料的颜色
						double randomNum_Set=Math.random();

						if(randomNum_Set>0&&randomNum_Set<(1.0/3)) {
							itb.pigmentIIndex=1;//红色
							itb.addPigment(itb.pigmentIIndex,itb);
							pigmentUI.add(itb.pb);
						}else if(randomNum_Set<(2.0/3)){
							itb.pigmentIIndex=2;//绿色
							itb.addPigment(itb.pigmentIIndex,itb);
							pigmentUI.add(itb.pb);
						}else {
							itb.pigmentIIndex=3;//蓝色
							itb.addPigment(itb.pigmentIIndex,itb);
							pigmentUI.add(itb.pb);
						}
						System.out.println(itb.toString()+"  "+itb.X+" "+itb.Y+"i="+i+"颜料颜色为："+itb.pigmentIIndex);
						if(i==pigmentNum) {
							break;
						}
					}
				}
			}
		}
		itabox.add(wood);
		itabox.add(winP);

		//=======kEY======
		KeyAdapter ka=new KeyAdapter() {
			//-------------------------按下鼠标时------------------
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				int keyCode=e.getKeyCode();
				
				if(!isOnLadder) {
					if(keyCode==KeyEvent.VK_LEFT) {
						if(player.v0==0) {
							isRight=false;
							Speed=Math.abs(Speed_player);
							//助跑计时
							keyTime++;
						}
					}
					if(keyCode==KeyEvent.VK_RIGHT) {	
						if(player.v0==0) {
							isRight=true;
							Speed=-Math.abs(Speed_player);
							//助跑计时
							keyTime++;
						}
					}
				}
				//up
				if(keyCode==KeyEvent.VK_UP) {
					/*
					 * 检测是否有梯子/门
						检测高度是否在一楼
						向上移动
					 */
					if(getCode("ladder")==-1&&player.v0==0) {//梯子
						if(player.Y<=Player.oneFloor_Y&&player.Y>Player.twoFloor_Y) {
							isOnLadder=true;
							player.Y-=2;
						}else if(player.Y==Player.twoFloor_Y) {
							isOnLadder=false;
						}
					}else if(getCode("door")>0&&Speed==0&&player.v0==0){//门
						usingDoor=getUsingDoor();
						/*条件：首先当前的无
						 * 传送：
						 * 1、需要知道间隔多少秒
						 * 2、实现人物隐身
						 * 2.5、不可以动
						 * 3、坐标提升到二楼或者一楼
						 * 坐标移动不能是瞬移，或者背景会进行坐标检测
						 */
						//定义此时不会被抓 即是无敌变量
						//隐身
						isPlayerHide=true;
						isTime=true;
						//移动
						if(getUsingDoor().X<getSameCodeDoor(getUsingDoor()).X) {
							Speed=-Math.abs(Speed_player*2);
						}else {
							Speed=Math.abs(Speed_player*2);
						}
					}
				}
				//down
				if(keyCode==KeyEvent.VK_DOWN) {
					if(getCode("ladder")==-1&&player.v0==0) {//梯子
						if(player.Y>=Player.twoFloor_Y&&player.Y<Player.oneFloor_Y) {
							isOnLadder=true;
							player.Y+=2;		
						}else if(player.Y==Player.oneFloor_Y) {
							isOnLadder=false;
						}
					}
				}
				//Space
				if(keyCode==KeyEvent.VK_SPACE) {
					
					/*
					 * 检测是否有交互物体在范围内
					 * 检测是否可以进行交互（获得喷漆）
					 */
					if(getNearbyInteractiveBox()!=null&&isCanSearch){//检测附近是否有交互对象
						//读条
						if(getNearbyInteractiveBox().Name=="winP"&&isBrushWin) {//最终涂鸦可触发的条件						
							isReadingBar=true;
						}
						if(getNearbyInteractiveBox().Name!="winP"&&getNearbyInteractiveBox().Name!="muban") {	//interactiveBox中两个特殊情况物体排除						
							isReadingBar=true;
						}
						//更新最近搜索的对象
						isUsingBox=getNearbyInteractiveBox();
					}
				}else {
					clearBarLoading();//实现移动时中断进度条（更快）
				}
			}
			//-------------松开键盘时----------------
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyReleased(e);
				runDelay(keyTime);
				keyTime=0;
				if(player.v0==0) {
					Speed=0;
				}
				clearBarLoading();
			}
		};
		f.addKeyListener(ka);
	}
	 //----------------------------清空进度条----------------
	 protected void clearBarLoading() {
		 isReadingBar=false;	//不显示进度条
		 barNum=0;
		 isCanSearch=true;
	}
	//------------------------------读条--------------------
	protected void isReadingBar() {
		barNum++;
		if(barNum<=barTime1) {
//			bar.w=player.W*(1/(barTime1-barNum));
			bar.w=(int)(barNum*((float)player.W/barTime1));
//			bar.w=barNum;
		}else {
			isCanSearch=false;
			if(getPigmentNum<3) {
				if(getNearbyInteractiveBox().isHasPigment) {
					getNearbyInteractiveBox().isHasPigment=false;
					System.out.println("坐标处颜料已经回收"+"  "+getNearbyInteractiveBox().X+" "+getNearbyInteractiveBox().Y+"颜色为："+getNearbyInteractiveBox().pigmentIIndex);
					getPigmentNum++;
					//动画
//					getPigmentAnimation(getPigmentNum);
					isPigmentMoveAnimation=true;
					if(getPigmentNum==3) {
						//创建指示位置
						/*
						 * 创建胜利点，因为可以交互，所以是交互类型
						 * 激活点，空格可以进行交互
						 * 设置读条时间为2秒
						 * 读条完毕后获得胜利，结束所有动画
						 */
						isBrushWin=true;
					}
				}else {
					System.out.println("搜索结束,一无所获");
				}
				isReadingBar=false;
				barNum=0;
			}else {
				if(getNearbyInteractiveBox().Name=="winP") {
					isBrushedWin=true;
					isBrushWin=false;
				}
				System.out.println("你已经装不下更多的颜料了，请尽快使用！");
			}
		}
	}
	//-------------------------颜料动画---------------------------
	private void pigmentMoveAnimation() {
		int num=getPigmentNum;//第几个已经获取颜料
		PigmentBox box=isUsingBox.pb;//获取搜索的颜料对象
		
		box.isSearched=true;
		if(box.x<(num-2)*20+250-10||box.x>(num-2)*20+250+2) {
			box.x+=Math.round(((num-2)*20+250-isUsingBox.X)/(500.0/intervalTime));
			box.y+=Math.round((10-isUsingBox.Y)/(500.0/intervalTime));
		}else {
			box.x=(num-2)*20+250;
			box.y=10;
			isPigmentMoveAnimation=false;
		}
	}
	//------------------判定是否在交互范围（交互物体）---------
	 protected InteractiveBox getNearbyInteractiveBox() {
		 for(int i=0;i<itabox.size();i++) {
			 InteractiveBox l=itabox.get(i);
			 if(player.X+player.W/2>l.X&&player.X<l.X+l.W&&player.Y+player.H>=l.Y&&player.Y+player.H/2<=l.Y+l.H) {
				 return l;
			 }
		 }
		 return null;
	}
	 //------------------获取对应的门---------------------
	  //获取当前的门
	 private Building getUsingDoor() {
		 for(int i=0;i<lds.size();i++) {
			 Building l=lds.get(i);
			 if(player.X+player.W/2>l.X&&player.X<l.X+l.W&&player.Y+player.H/2>=l.Y&&player.Y+player.H/2<=l.Y+l.H) {
				 return l;
			 }
		 }
		 return null;
	 }
	 //获取对应的门
	 private Building getSameCodeDoor(Building b) {
		 for(int i=0;i<lds.size();i++) {
			 Building l=lds.get(i);
			 if(l.code==b.code&&l.code>0&&l!=b) {
				 return l;
			 }
		 }
		 return null;
	 }
	//------------------判定是否在交互范围（梯子和门）的code值---------
	 /*
	  * 区分梯子与门
	  * 梯子的code值为-1，而门的code大于-1，0值作为未找到
	  */
	 protected int getCode(String s) {
		 for(int i=0;i<lds.size();i++) {
			 Building l=lds.get(i);
			 if(s=="door") {//梯子和门的判定范围有区别，这里偷懒了一下
				 if(player.X+player.W/2>l.X&&player.X<l.X+l.W&&player.Y+player.H/2>=l.Y&&player.Y+player.H/2<=l.Y+l.H) {
					 return l.code;
				 }
			 }else if(s=="ladder"){
				 if(player.X+player.W/2>l.X&&player.X<l.X+l.W) {
					 return l.code;
				 }
			 }
		 }
		 return 0;
	}
	//----------------------------------跑步缓冲--------------------------
	 protected void runDelay(int t) {
//		 System.out.println(t);
		if(t>=35) {
			//加载图片
			System.out.println("你放了个屁！");
			//人物缓冲动作
			player.v0=20;
		}
	}
	 //-----------------------------------start----------------------------
	 //new Thread(){public void run(){线程所做的事情}}.start();
	public void startGame() {
		new Thread() {public void run() {
			while(true) {
				try {
					Thread.sleep(intervalTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//==========游戏的进程================
				//敌人的进程 
				try{
					GirdPosition p=nearestWay.get(nearestWay.size()-1);
					empty.X=p.getX();
					empty.Y=p.getY()-empty.H;
					nearestWay=afw.getWay(player, empty);
					nearestWay.remove(nearestWay.size()-1);
				}
				catch (Exception e) {
					// TODO: handle exception
					System.out.println("。。。");
				}
				//////
				if(empty.collisionP(player,isCanUsingCollisionBox)) {
					System.out.println("E n d !");
					break;
				}
				//玩家的缓冲和移动
				player.delayMove();
				if(Speed!=0&&player.v0==0) {
					backMove(player.X);
				}
				if(player.v0!=0) {
					backMove(Speed);
				}
				//进度条
				bar.position(player);//进度条始终等同角色的坐标
				if(getNearbyInteractiveBox()==null) {
					isReadingBar=false;
				}
				if(isReadingBar)
					isReadingBar();
				/*
				 * get颜料的动画
				 * 1、交互对象InteractiveBox添加属性index
				 * 并且也创建PigmentBox对象在InteractiveBox中
				 * 并在初始化中的随机添加颜料中，随机添加代码（代码123,0为无）
				 * 在读条结束中添加判定变量isPigmentAnimation=true;
				 * 线程中判定如果isPigmentAnimation=true就触发方法pigmentAnimation
				 * 方法中首先需要知道当前是获得的第几个颜料（根据这个确定最终位置）
				 * 需要知道搜索的当前物体，方便确定出现的位置
				 * 最后根据初始位置以及最终位置进行平移动画
				 * 抵达最终位置后isPigmentAnimation=false;
				 */
				if(isPigmentMoveAnimation)
					pigmentMoveAnimation();
				//传送动画
				if(isTime) {
					doorIfArrive();
				}
				//箭头动画永远在动
				jt.HVMove();
//				nearestWay=afw.getWay(player, empty);
				repaint();
				//==============================
			}
		}}.start();
	 }
	public void startEmptyMove() {
		new Thread() {public void run() {
			//!empty.collisionP(player,isCanUsingCollisionBox)
			while(true){
				//					System.out.println(nearestWay.size());
//				if(!(nearestWay.size()==0)) {
////						emptyAutoMove(nearestWay);
//					GirdPosition p=nearestWay.get(nearestWay.size()-1);
//					empty.X=p.getX();
//					empty.Y=p.getY()-empty.H;
//					nearestWay=afw.getWay(player, empty);
//					try {		
//						nearestWay.remove(nearestWay.size()-1);
//					}
//					catch (Exception e) {
//						nearestWay=afw.getWay(player, empty);
//					}
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
////						nearestWay=afw.getWay(player, empty);
//				}else {
////						System.out.println("为0");
//				}
				
			}
		}}.start();
	}
	protected void emptyAutoMove(List<GirdPosition> list) throws InterruptedException {
		//自动寻路A*
		/*
		 * BuG2:应该是backMove导致的nearestWay路线后退
		 */
//		GirdPosition p=list.get(list.size()-2);
//		for(int i=list.size()-2;i>0;i--) {//减2是排除序列从0开始的原因和终点
//			GirdPosition p=list.get(i);
//			emptyTimeNum++;
			GirdPosition p=list.get(list.size()-1);
			empty.X=p.getX();
			empty.Y=p.getY()-empty.H;
//			nearestWay.remove(p);
			
//			if(isPlayerHide) {
//				emptyTimeNum=0;
//			}
//			if(emptyTimeNum>15) {
//				emptyTimeNum=0;
//				nearestWay=afw.getWay(player, empty);
//			}
			
//			if(list.size()==10) {
//				nearestWay=afw.getWay(player, empty);
//			}
//			GirdPosition pl=nearestWay.get(list.size()-2);
////			list.remove(p);
//			Empty e=new Empty();
//			e.X=pl.getX();
//			e.Y=pl.getY();
//			nearestWay=afw.getWay(player, e);
			Thread.sleep(40);
//			if(p.getX()!=empty.X) {
//				while(empty.X >= p.getX()){
//					empty.X-= 1;
//					repaint();
////				System.out.println("启动3");
//					Thread.sleep(20);
//				}
//				while(empty.X < p.getX()){
//					empty.X+= 1;
//					repaint();
////				System.out.println("启动4");
//					Thread.sleep(20);
//				}
//			}else
//			if(p.getY()!=empty.Y) {
//				while(empty.Y +empty.H> p.getY()){
//					empty.Y-=1;
//					repaint();
////				System.out.println("启动1");
//					Thread.sleep(20);
//				}
//				while(empty.Y +empty.H< p.getY()){
//					empty.Y+=1;
//					repaint();
////				System.out.println("启动2");
//					Thread.sleep(20);
//				}
////			}
//			list.remove(p);
//			i--;
//		}
	}
	//传送计时 门是否到位的判断
	protected void doorIfArrive() {
		if(player.X>=getSameCodeDoor(usingDoor).X&&Speed<0||player.X<=getSameCodeDoor(usingDoor).X&&Speed>0) {//后抵达
//			System.out.println("arrive");
			isPlayerHide=false;
			isCanUsingCollisionBox=true;
			player.v0=0;
			Speed=0;
			isOnLadder=false;
			isTime=false;
			if(getSameCodeDoor(usingDoor).Y!=Player.oneFloor_Y+player.H-door1.H-8) {//对应门不在一楼
//				player.X=getSameCodeDoor(usingDoor).X;
				player.Y=Player.twoFloor_Y;
			}else {
//				player.X=getSameCodeDoor(usingDoor).X;
				player.Y=Player.oneFloor_Y;
			}
		}else {//未抵达时
//			System.out.println("NotArrive");
			player.v0=20;
			isCanUsingCollisionBox=false;
			isOnLadder=true;
			isPlayerHide=true;
		}
	}
	//-------------------------------------镜头移动-------------------------------------
	 private void backMove(int x) {
		 ///////////////////////////////////////////
		 player.delayMove();
		 //////////////////////
		 direSpeed=Speed;
		 if(isCanUsingCollisionBox) {//服务于传送拉镜
			 for(int i=0;i<clsboxes.size();i++) {
				 CollisionBox cb=clsboxes.get(i);
				 if(cb.isCollision(player)) {
					 if(Speed<0) {
						 player.X=cb.x-player.W;
					 }else if(Speed>0) {
						 player.X=cb.x+cb.w;
					 }
					 System.out.println("发生碰撞"+(player.X+player.W)+" "+cb.x);
					 Speed=0;
					 direSpeed=Speed;
				 }
			 }
		 }
//		 player.X-=Speed;
		 if(Speed<0) {//Right
			 if(player.X<=GameFrame.fw/3) {//当人物距离100不足时人物可移动，视为背景不动
				 player.X-=Speed;
				 direSpeed=0;
				 player.X=(int) (player.X+player.s);
			 }else {//人物到达指定位置后人物不动，背景开始移动；
//				 player.X+=Speed;
				 direSpeed=Speed;
			 }
			 if(road.X+road.W<GameFrame.fw) {//限制区域：右边边界时，背景不动，人动
				 direSpeed=0;
				 player.X-=Speed;
				 player.X=(int) (player.X+player.s);
				 if(CollisionBox.collisionHandling(player)) {//右边界：人物移动限制范围
					 player.X=GameFrame.fw-player.W;
				 }
			 }
			 if(road.X+road.W<GameFrame.fw-6) {
				 direSpeed+=-Speed;
			 }
		 }else if(Speed>0) {//Left
			 if(player.X>GameFrame.fw/3) {//人物距离不足100时，人动背景不动
				 direSpeed=0;
				 player.X-=Speed;
				 player.X=(int) (player.X+player.s);
			 }else {//人物到达指定位置后人物不动，背景开始移动；
				 direSpeed=Speed;
//				 System.out.println("人不动！");
//				 player.X+=Speed
			 }
			 if(road.X==0) {//左边界时：人动背景不动
				 player.X-=Speed;
				 direSpeed=0;
				 player.X=(int) (player.X+player.s);
				 if(CollisionBox.collisionHandling(player)) {//左边界：人物移动限制范围
					 player.X=0;
				 }
			 }
//			 if(road.X>2) {//
//				 direSpeed+=-Speed;
//			 }
		 }
		 //背景：围栏、马路、房子、梯子
		 for(int i=0;i<bgs.size();i++) {
				Building bd=bgs.get(i);
				bd.move();
		}
		 //碰撞体
		for(int i=0;i<clsboxes.size();i++) {
			CollisionBox cs=clsboxes.get(i);
			cs.move();
		}
		//交互：筒，花盆
		for(int i=0;i<itabox.size();i++) {
			InteractiveBox st=itabox.get(i);
			st.move();
		}
		//敌人 最佳路径是实时计算出来的,不需要根据背景移动
//		nearestWay=afw.getWay(player, empty);
//		empty.move();
		for(GirdPosition p:Gird.allP) {
			p.move();
		}
		if(nearestWay.size()!=0||nearestWay!=null) {		
			for(GirdPosition pn:nearestWay) {
				pn.move();
			}
			empty.move();
		}
//		if(!(nearestWay.size()==0)) {
//			try {
//				emptyAutoMove(nearestWay);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			nearestWay=afw.getWay(player, empty);
//		}
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		 g.drawImage(bg, 0, 0, null);
		 for(int i=0;i<bgs.size();i++) {
			 Building box=bgs.get(i);
			 if(box.Name=="jiantou") {//指示箭头出现
				 if(isBrushWin) {
					 g.drawImage(box.img, box.X, box.Y,box.W,box.H, null);
				 }
			 }else {
				 g.drawImage(box.img, box.X, box.Y,box.W,box.H, null);
			 }
		 }
		 //交互物体
		 for(int i=0;i<itabox.size();i++) {
			 InteractiveBox box=itabox.get(i);
			 if(box.Name=="winP") {			//胜利	
				 if(isBrushedWin) {
					 g.drawImage(box.img, box.X, box.Y,box.W,box.H, null);
					 g.setColor(Color.red);//胜利UI
					 g.setFont(new Font("swfit_slm_fw", Font.PLAIN,30));
					 g.drawString("W i n !", 100,50 );				 
					 g.setColor(Color.BLACK);
				 }
			 }else{
				 g.drawImage(box.img, box.X, box.Y,box.W,box.H, null);
			}
		 }
		 //player
		 if(!isPlayerHide)
			 g.fillRect(player.X,player.Y,player.W,player.H);
		 g.setColor(new Color(95,205,228));
		 if(isReadingBar)
			 g.fillRoundRect(bar.x, bar.y, bar.w, bar.h, 1, 2);
		 g.setColor(Color.black);
		 g.drawImage(wl.img, wl.X,wl.Y,wl.W,wl.H,null);
		 //Empty
		 g.drawImage(empty.img, empty.X, empty.Y, empty.W,empty.H,null);
		 //CollisionBox
//		 g.drawRect(cls1.x,cls1.y,cls1.w,cls1.h);
//		 g.drawRect(cls2.x,cls2.y,cls2.w,cls2.h);
//		 g.drawRect(cls3.x,cls3.y,cls3.w,cls3.h);
		 //UI
		 for(int i=0;i<getPigmentNum;i++) {
			 g.drawRect(250+(i-1)*20-1, 10-1, 16, 21);
		 }
		 //Animation_Pigment
		 for(int i=0;i<pigmentUI.size();i++) {
			 PigmentBox box=pigmentUI.get(i);
			 if(box.isSearched) {
				 if(box.index==1)//red
					 g.setColor(new Color(172,50,50));
				 if(box.index==2)//red
					 g.setColor(new Color(62,120,81));
				 if(box.index==3)//red
					 g.setColor(new Color(95,205,208));
				 g.fillRect(box.x,box.y, box.w, box.h);
				 g.setColor(Color.black);
			 }
		 }
		 //a*
//		 for(GirdPosition gp:nearestWay) {
//			 g.setColor(Color.green);
//			 g.fillRect(gp.getX(), gp.getY(), 1,1);
//		 }
//		 for(GirdPosition gp:Gird.allP) {
//			 g.setColor(Color.blue);
//			 g.drawRect(gp.getX(), gp.getY(), 1,1);
//		 }
	}

}

