package Game;

import java.awt.image.BufferedImage;

import Game.ToolImg;

public class InteractiveBox {
	int Y,X;
	int W,H;
	boolean isHasPigment;
	int pigmentIIndex=0;
	BufferedImage img;
	String Name="";
	//包含颜料对象
	PigmentBox pb;
	InteractiveBox(){
		pb=new PigmentBox(1);
		img=ToolImg.getImg("/Img/smalltree.png");
		W=(int)(img.getWidth()*1.2);
		H=(int)(img.getHeight()*1.2);
	}
	InteractiveBox(String str){
		if(str=="WoodenBox") {
			img=ToolImg.getImg("/Img/woodenBarrel.png");
			pb=new PigmentBox(1);
			W=(int)(img.getWidth()*1.3);
			H=(int)(img.getHeight()*1.3);
		}
		if(str=="muban") {
			Name=str;
			img=ToolImg.getImg("/Img/muban.png");
			pb=new PigmentBox(1);
			W=(int)(img.getWidth()/1.7);
			H=(int)(img.getHeight()*1);
		}
		if(str=="winP") {
			Name=str;
			img=ToolImg.getImg("/Img/tuya.png");
			pb=new PigmentBox(0);
			W=(int)(img.getWidth()/1);
			H=(int)(img.getHeight()*1);
		}
	}
	public void move() {
		X=X+GamePanel.direSpeed;
		if(!pb.isSearched)
			pb.x=X;
	}
	public void addPigment(int index, InteractiveBox pf){//自身型号以及父对象是谁
//		PigmentBox pb;
		pb=new PigmentBox(index);
		pb.x=pf.X;
		pb.y=pf.Y;
	}
}
