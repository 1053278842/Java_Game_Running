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
	static int Speed_player=2;//���ɫ�ٶ�
	static int Speed=0;//��ɫ�ٶ�
	static int direSpeed=2;//�������ٶ�
	//״̬
	public int emptyTimeNum=0;
	boolean isCanUsingCollisionBox=true;
	boolean isPlayerHide=false;
	final int intervalTime=20;
	static boolean isRight=true;
	boolean isOnLadder=false;	
	boolean isReadingBar=false;
	int pigmentNum=3;//���ռ�������������
	int getPigmentNum=0;//�Ѿ��յ���������
	boolean isPigmentMoveAnimation=false;
	static InteractiveBox isUsingBox;
	boolean isTime=false;
	Building usingDoor;
	boolean isBrushWin=false;//�Ƿ���Ϳѻ�˴�ʱ���ּ�ͷ
	boolean isBrushedWin=false;//�Ƿ���Ϳѻ�˴�ʱ��ͷ��ʧ��ͼ����֣���Ϸ����
	//���ܹ���
	int keyTime=0;
	//������
	Bar bar;
	int barTime1=1500/intervalTime;
	int barTime2=2500/intervalTime;
	int barNum=0;//�ƴ�
	boolean isCanSearch=true;//ʵ�ֲ���һֱ����ͬһ������
	//Building
	Building road=new Building("road");
	Building bd=new Building("hourse");
	Building bd2=new Building("hourse");
	Building bd3=new Building("hourse");
	Player player =new Player();
	Building wl=new Building("weilan");
	BufferedImage bg;
	Building jt=new Building("jiantou");
	//�뽻��
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
	//����
	InteractiveBox st1=new InteractiveBox();;
	InteractiveBox st2=new InteractiveBox();;
	InteractiveBox st3=new InteractiveBox();;
	InteractiveBox st4=new InteractiveBox();;
	InteractiveBox wBarrel1=new InteractiveBox("WoodenBox");
	InteractiveBox wBarrel2=new InteractiveBox("WoodenBox");
	InteractiveBox wood=new InteractiveBox("muban");
	InteractiveBox winP=new InteractiveBox("winP");
	//����
	Empty empty=new Empty();
	//����
	public static List<Building> lds=new ArrayList<Building>();	//���ܽ�ɫӰ����ƶ��İ뽻������
	public static List<Building> bgs=new ArrayList<Building>();	//���ƶ��ı�������
	public static List<CollisionBox> clsboxes=new ArrayList<CollisionBox>();	//��ײ�伯��
	public static List<InteractiveBox> itabox=new ArrayList<InteractiveBox>();	//��������
	public static List<PigmentBox> pigmentUI=new ArrayList<PigmentBox>();	//����UI����
	public static  List<GirdPosition> nearestWay=new ArrayList<GirdPosition>();	//�����Զ����·��
	//A*·��
	//----------------------------------------------��ʼ��----------------------------------
	 GamePanel(GameFrame f) {
		 setLayout(null);
		 //��ʾ���
//		 System.out.println("��ͼȫ��"+(road.W-road.X));//640
		 //

		bg=ToolImg.getImg("/Img/GameBack.png");
		bar=new Bar(player.W);
		//�������ʼλ��
		bd2.X=bd.X+bd.W-20;
		bd3.X=bd2.X+bd2.W+40;
		wood.X=bd2.X+bd2.W-15;
		wood.Y=Player.twoFloor_Y+player.H-wood.H+5;
		//CollisonBoxλ��
		cls1.w=10;
		cls1.x=bd2.X+cls1.w/2;
		cls1.y=Player.twoFloor_Y+player.H-cls1.h;
		cls2.x=bd2.X+bd2.W-10;
		cls2.w=40+10*2;
		cls2.y=Player.twoFloor_Y+player.H-cls2.h;
		cls3.x=bd.X-cls3.w;
		cls3.w=40+10;
		cls3.y=Player.twoFloor_Y+player.H-cls3.h;
		//�뽻��λ��
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
		//ȫ����λ��
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
			//ָʾ��ͷ��Ϳѻ�Ϸ�
		jt.X=winP.X+winP.W/2-jt.W/2;
		jt.Y=winP.Y-jt.H*2;
		//Empty
		empty.X=road.X+road.W-empty.W;
		//��������bgs
		bgs.add(road);
		bgs.add(wl);
		bgs.add(bd);
		bgs.add(bd2);
		bgs.add(bd3);
			//����İ뽻������Ҳ�����ڱ������ϣ�����ͬ������
		bgs.add(door1);
		bgs.add(door2);
		bgs.add(door3);
		bgs.add(door4);
		bgs.add(ld);
		bgs.add(ld2);
		bgs.add(jt);
		//��������(�뽻��)����
		lds.add(ld);
		lds.add(ld2);
		lds.add(door1);
		lds.add(door2);
		lds.add(door3);
		lds.add(door4);
		//ȫ��������
		itabox.add(st1);
		itabox.add(st2);
		itabox.add(st3);
		itabox.add(st4);
		itabox.add(wBarrel1);
		itabox.add(wBarrel2);

		//��ײ�弯��
		clsboxes.add(cls1);
//		clsboxes.add(cls2);
		clsboxes.add(cls3);
		 //////////////////////
		 gird.drawP(road.X,road.X+road.W);//�ػ��ͼ
		 nearestWay=afw.getWay(player, empty);
		 //����
//		 for(GirdPosition p:nearestWay) {
//			 GirdRect gr=new GirdRect(p);
//			 girdrect.add(gr);
//		 }
		 System.out.println("��Ϸ��ʼ");
		 //////////////////////
		//����ֲ����ϵ�����������
		int i=0;
		while(i<pigmentNum) {
			for(int n=0;n<itabox.size();n++) {
//				System.out.println(i);
				InteractiveBox itb=itabox.get(n);
				if(!itb.isHasPigment) {//�ж��Ƿ��Ѿ�����
					double randomNum_Add=Math.random();
					if(randomNum_Add>0.5) {//������ʼ�������
						itb.isHasPigment=true;
						i++;
						//�������ϵ���ɫ
						double randomNum_Set=Math.random();

						if(randomNum_Set>0&&randomNum_Set<(1.0/3)) {
							itb.pigmentIIndex=1;//��ɫ
							itb.addPigment(itb.pigmentIIndex,itb);
							pigmentUI.add(itb.pb);
						}else if(randomNum_Set<(2.0/3)){
							itb.pigmentIIndex=2;//��ɫ
							itb.addPigment(itb.pigmentIIndex,itb);
							pigmentUI.add(itb.pb);
						}else {
							itb.pigmentIIndex=3;//��ɫ
							itb.addPigment(itb.pigmentIIndex,itb);
							pigmentUI.add(itb.pb);
						}
						System.out.println(itb.toString()+"  "+itb.X+" "+itb.Y+"i="+i+"������ɫΪ��"+itb.pigmentIIndex);
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
			//-------------------------�������ʱ------------------
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
							//���ܼ�ʱ
							keyTime++;
						}
					}
					if(keyCode==KeyEvent.VK_RIGHT) {	
						if(player.v0==0) {
							isRight=true;
							Speed=-Math.abs(Speed_player);
							//���ܼ�ʱ
							keyTime++;
						}
					}
				}
				//up
				if(keyCode==KeyEvent.VK_UP) {
					/*
					 * ����Ƿ�������/��
						���߶��Ƿ���һ¥
						�����ƶ�
					 */
					if(getCode("ladder")==-1&&player.v0==0) {//����
						if(player.Y<=Player.oneFloor_Y&&player.Y>Player.twoFloor_Y) {
							isOnLadder=true;
							player.Y-=2;
						}else if(player.Y==Player.twoFloor_Y) {
							isOnLadder=false;
						}
					}else if(getCode("door")>0&&Speed==0&&player.v0==0){//��
						usingDoor=getUsingDoor();
						/*���������ȵ�ǰ����
						 * ���ͣ�
						 * 1����Ҫ֪�����������
						 * 2��ʵ����������
						 * 2.5�������Զ�
						 * 3��������������¥����һ¥
						 * �����ƶ�������˲�ƣ����߱��������������
						 */
						//�����ʱ���ᱻץ �����޵б���
						//����
						isPlayerHide=true;
						isTime=true;
						//�ƶ�
						if(getUsingDoor().X<getSameCodeDoor(getUsingDoor()).X) {
							Speed=-Math.abs(Speed_player*2);
						}else {
							Speed=Math.abs(Speed_player*2);
						}
					}
				}
				//down
				if(keyCode==KeyEvent.VK_DOWN) {
					if(getCode("ladder")==-1&&player.v0==0) {//����
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
					 * ����Ƿ��н��������ڷ�Χ��
					 * ����Ƿ���Խ��н�����������ᣩ
					 */
					if(getNearbyInteractiveBox()!=null&&isCanSearch){//��⸽���Ƿ��н�������
						//����
						if(getNearbyInteractiveBox().Name=="winP"&&isBrushWin) {//����Ϳѻ�ɴ���������						
							isReadingBar=true;
						}
						if(getNearbyInteractiveBox().Name!="winP"&&getNearbyInteractiveBox().Name!="muban") {	//interactiveBox������������������ų�						
							isReadingBar=true;
						}
						//������������Ķ���
						isUsingBox=getNearbyInteractiveBox();
					}
				}else {
					clearBarLoading();//ʵ���ƶ�ʱ�жϽ����������죩
				}
			}
			//-------------�ɿ�����ʱ----------------
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
	 //----------------------------��ս�����----------------
	 protected void clearBarLoading() {
		 isReadingBar=false;	//����ʾ������
		 barNum=0;
		 isCanSearch=true;
	}
	//------------------------------����--------------------
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
					System.out.println("���괦�����Ѿ�����"+"  "+getNearbyInteractiveBox().X+" "+getNearbyInteractiveBox().Y+"��ɫΪ��"+getNearbyInteractiveBox().pigmentIIndex);
					getPigmentNum++;
					//����
//					getPigmentAnimation(getPigmentNum);
					isPigmentMoveAnimation=true;
					if(getPigmentNum==3) {
						//����ָʾλ��
						/*
						 * ����ʤ���㣬��Ϊ���Խ����������ǽ�������
						 * ����㣬�ո���Խ��н���
						 * ���ö���ʱ��Ϊ2��
						 * ������Ϻ���ʤ�����������ж���
						 */
						isBrushWin=true;
					}
				}else {
					System.out.println("��������,һ������");
				}
				isReadingBar=false;
				barNum=0;
			}else {
				if(getNearbyInteractiveBox().Name=="winP") {
					isBrushedWin=true;
					isBrushWin=false;
				}
				System.out.println("���Ѿ�װ���¸���������ˣ��뾡��ʹ�ã�");
			}
		}
	}
	//-------------------------���϶���---------------------------
	private void pigmentMoveAnimation() {
		int num=getPigmentNum;//�ڼ����Ѿ���ȡ����
		PigmentBox box=isUsingBox.pb;//��ȡ���������϶���
		
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
	//------------------�ж��Ƿ��ڽ�����Χ���������壩---------
	 protected InteractiveBox getNearbyInteractiveBox() {
		 for(int i=0;i<itabox.size();i++) {
			 InteractiveBox l=itabox.get(i);
			 if(player.X+player.W/2>l.X&&player.X<l.X+l.W&&player.Y+player.H>=l.Y&&player.Y+player.H/2<=l.Y+l.H) {
				 return l;
			 }
		 }
		 return null;
	}
	 //------------------��ȡ��Ӧ����---------------------
	  //��ȡ��ǰ����
	 private Building getUsingDoor() {
		 for(int i=0;i<lds.size();i++) {
			 Building l=lds.get(i);
			 if(player.X+player.W/2>l.X&&player.X<l.X+l.W&&player.Y+player.H/2>=l.Y&&player.Y+player.H/2<=l.Y+l.H) {
				 return l;
			 }
		 }
		 return null;
	 }
	 //��ȡ��Ӧ����
	 private Building getSameCodeDoor(Building b) {
		 for(int i=0;i<lds.size();i++) {
			 Building l=lds.get(i);
			 if(l.code==b.code&&l.code>0&&l!=b) {
				 return l;
			 }
		 }
		 return null;
	 }
	//------------------�ж��Ƿ��ڽ�����Χ�����Ӻ��ţ���codeֵ---------
	 /*
	  * ������������
	  * ���ӵ�codeֵΪ-1�����ŵ�code����-1��0ֵ��Ϊδ�ҵ�
	  */
	 protected int getCode(String s) {
		 for(int i=0;i<lds.size();i++) {
			 Building l=lds.get(i);
			 if(s=="door") {//���Ӻ��ŵ��ж���Χ����������͵����һ��
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
	//----------------------------------�ܲ�����--------------------------
	 protected void runDelay(int t) {
//		 System.out.println(t);
		if(t>=35) {
			//����ͼƬ
			System.out.println("����˸�ƨ��");
			//���ﻺ�嶯��
			player.v0=20;
		}
	}
	 //-----------------------------------start----------------------------
	 //new Thread(){public void run(){�߳�����������}}.start();
	public void startGame() {
		new Thread() {public void run() {
			while(true) {
				try {
					Thread.sleep(intervalTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//==========��Ϸ�Ľ���================
				//���˵Ľ��� 
				try{
					GirdPosition p=nearestWay.get(nearestWay.size()-1);
					empty.X=p.getX();
					empty.Y=p.getY()-empty.H;
					nearestWay=afw.getWay(player, empty);
					nearestWay.remove(nearestWay.size()-1);
				}
				catch (Exception e) {
					// TODO: handle exception
					System.out.println("������");
				}
				//////
				if(empty.collisionP(player,isCanUsingCollisionBox)) {
					System.out.println("E n d !");
					break;
				}
				//��ҵĻ�����ƶ�
				player.delayMove();
				if(Speed!=0&&player.v0==0) {
					backMove(player.X);
				}
				if(player.v0!=0) {
					backMove(Speed);
				}
				//������
				bar.position(player);//������ʼ�յ�ͬ��ɫ������
				if(getNearbyInteractiveBox()==null) {
					isReadingBar=false;
				}
				if(isReadingBar)
					isReadingBar();
				/*
				 * get���ϵĶ���
				 * 1����������InteractiveBox�������index
				 * ����Ҳ����PigmentBox������InteractiveBox��
				 * ���ڳ�ʼ���е������������У������Ӵ��루����123,0Ϊ�ޣ�
				 * �ڶ�������������ж�����isPigmentAnimation=true;
				 * �߳����ж����isPigmentAnimation=true�ʹ�������pigmentAnimation
				 * ������������Ҫ֪����ǰ�ǻ�õĵڼ������ϣ��������ȷ������λ�ã�
				 * ��Ҫ֪�������ĵ�ǰ���壬����ȷ�����ֵ�λ��
				 * �����ݳ�ʼλ���Լ�����λ�ý���ƽ�ƶ���
				 * �ִ�����λ�ú�isPigmentAnimation=false;
				 */
				if(isPigmentMoveAnimation)
					pigmentMoveAnimation();
				//���Ͷ���
				if(isTime) {
					doorIfArrive();
				}
				//��ͷ������Զ�ڶ�
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
////						System.out.println("Ϊ0");
//				}
				
			}
		}}.start();
	}
	protected void emptyAutoMove(List<GirdPosition> list) throws InterruptedException {
		//�Զ�Ѱ·A*
		/*
		 * BuG2:Ӧ����backMove���µ�nearestWay·�ߺ���
		 */
//		GirdPosition p=list.get(list.size()-2);
//		for(int i=list.size()-2;i>0;i--) {//��2���ų����д�0��ʼ��ԭ����յ�
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
////				System.out.println("����3");
//					Thread.sleep(20);
//				}
//				while(empty.X < p.getX()){
//					empty.X+= 1;
//					repaint();
////				System.out.println("����4");
//					Thread.sleep(20);
//				}
//			}else
//			if(p.getY()!=empty.Y) {
//				while(empty.Y +empty.H> p.getY()){
//					empty.Y-=1;
//					repaint();
////				System.out.println("����1");
//					Thread.sleep(20);
//				}
//				while(empty.Y +empty.H< p.getY()){
//					empty.Y+=1;
//					repaint();
////				System.out.println("����2");
//					Thread.sleep(20);
//				}
////			}
//			list.remove(p);
//			i--;
//		}
	}
	//���ͼ�ʱ ���Ƿ�λ���ж�
	protected void doorIfArrive() {
		if(player.X>=getSameCodeDoor(usingDoor).X&&Speed<0||player.X<=getSameCodeDoor(usingDoor).X&&Speed>0) {//��ִ�
//			System.out.println("arrive");
			isPlayerHide=false;
			isCanUsingCollisionBox=true;
			player.v0=0;
			Speed=0;
			isOnLadder=false;
			isTime=false;
			if(getSameCodeDoor(usingDoor).Y!=Player.oneFloor_Y+player.H-door1.H-8) {//��Ӧ�Ų���һ¥
//				player.X=getSameCodeDoor(usingDoor).X;
				player.Y=Player.twoFloor_Y;
			}else {
//				player.X=getSameCodeDoor(usingDoor).X;
				player.Y=Player.oneFloor_Y;
			}
		}else {//δ�ִ�ʱ
//			System.out.println("NotArrive");
			player.v0=20;
			isCanUsingCollisionBox=false;
			isOnLadder=true;
			isPlayerHide=true;
		}
	}
	//-------------------------------------��ͷ�ƶ�-------------------------------------
	 private void backMove(int x) {
		 ///////////////////////////////////////////
		 player.delayMove();
		 //////////////////////
		 direSpeed=Speed;
		 if(isCanUsingCollisionBox) {//�����ڴ�������
			 for(int i=0;i<clsboxes.size();i++) {
				 CollisionBox cb=clsboxes.get(i);
				 if(cb.isCollision(player)) {
					 if(Speed<0) {
						 player.X=cb.x-player.W;
					 }else if(Speed>0) {
						 player.X=cb.x+cb.w;
					 }
					 System.out.println("������ײ"+(player.X+player.W)+" "+cb.x);
					 Speed=0;
					 direSpeed=Speed;
				 }
			 }
		 }
//		 player.X-=Speed;
		 if(Speed<0) {//Right
			 if(player.X<=GameFrame.fw/3) {//���������100����ʱ������ƶ�����Ϊ��������
				 player.X-=Speed;
				 direSpeed=0;
				 player.X=(int) (player.X+player.s);
			 }else {//���ﵽ��ָ��λ�ú����ﲻ����������ʼ�ƶ���
//				 player.X+=Speed;
				 direSpeed=Speed;
			 }
			 if(road.X+road.W<GameFrame.fw) {//���������ұ߽߱�ʱ�������������˶�
				 direSpeed=0;
				 player.X-=Speed;
				 player.X=(int) (player.X+player.s);
				 if(CollisionBox.collisionHandling(player)) {//�ұ߽磺�����ƶ����Ʒ�Χ
					 player.X=GameFrame.fw-player.W;
				 }
			 }
			 if(road.X+road.W<GameFrame.fw-6) {
				 direSpeed+=-Speed;
			 }
		 }else if(Speed>0) {//Left
			 if(player.X>GameFrame.fw/3) {//������벻��100ʱ���˶���������
				 direSpeed=0;
				 player.X-=Speed;
				 player.X=(int) (player.X+player.s);
			 }else {//���ﵽ��ָ��λ�ú����ﲻ����������ʼ�ƶ���
				 direSpeed=Speed;
//				 System.out.println("�˲�����");
//				 player.X+=Speed
			 }
			 if(road.X==0) {//��߽�ʱ���˶���������
				 player.X-=Speed;
				 direSpeed=0;
				 player.X=(int) (player.X+player.s);
				 if(CollisionBox.collisionHandling(player)) {//��߽磺�����ƶ����Ʒ�Χ
					 player.X=0;
				 }
			 }
//			 if(road.X>2) {//
//				 direSpeed+=-Speed;
//			 }
		 }
		 //������Χ������·�����ӡ�����
		 for(int i=0;i<bgs.size();i++) {
				Building bd=bgs.get(i);
				bd.move();
		}
		 //��ײ��
		for(int i=0;i<clsboxes.size();i++) {
			CollisionBox cs=clsboxes.get(i);
			cs.move();
		}
		//������Ͳ������
		for(int i=0;i<itabox.size();i++) {
			InteractiveBox st=itabox.get(i);
			st.move();
		}
		//���� ���·����ʵʱ���������,����Ҫ���ݱ����ƶ�
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
			 if(box.Name=="jiantou") {//ָʾ��ͷ����
				 if(isBrushWin) {
					 g.drawImage(box.img, box.X, box.Y,box.W,box.H, null);
				 }
			 }else {
				 g.drawImage(box.img, box.X, box.Y,box.W,box.H, null);
			 }
		 }
		 //��������
		 for(int i=0;i<itabox.size();i++) {
			 InteractiveBox box=itabox.get(i);
			 if(box.Name=="winP") {			//ʤ��	
				 if(isBrushedWin) {
					 g.drawImage(box.img, box.X, box.Y,box.W,box.H, null);
					 g.setColor(Color.red);//ʤ��UI
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

