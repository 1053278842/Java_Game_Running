package Game;

import java.awt.image.BufferedImage;


public class Building {
	int W,H;
	int X,Y;
	BufferedImage img;
	String name="";
	float Speed=1f;	//Χ���õ��ٶȱ���
	int code=-1;//������ʹ�õı��������ָ���ͨ����һ����
	String Name="";
	//��ͷ�ñ���
	int count=0;
	int jtSpeed=0;
	public Building(String s) {
		if(s=="hourse") {
			img=ToolImg.getImg("/Img/GameHourse.png");
			W=img.getWidth();
			H=img.getHeight();
			X=40;
			Y=0;
		}
		if(s=="ladder") {
			name=s;
			img=ToolImg.getImg("/Img/tizi.png");
			W=img.getWidth();
			H=img.getHeight();
		}
		if(s=="weilan") {
			img=ToolImg.getImg("/Img/weilan.png");
			W=(int)(img.getWidth()*2);
			H=(int)(img.getHeight()*2);
			X=-150;
			Y=GameFrame.fh-38-H;
			Speed=1.5f;
		}
		if(s=="road") {
			img=ToolImg.getImg("/Img/back.png");
			W=img.getWidth();
			H=img.getHeight();
			X=0;
			Y=210;
		}
		if(s=="jiantou") {
			Name=s;
			img=ToolImg.getImg("/Img/jiantou.png");
			W=img.getWidth();
			H=img.getHeight();
		}
	}
	public Building(String s,int code) {
		if(s=="door") {
			name=s;
			img=ToolImg.getImg("/Img/door.png");
			W=(int) (img.getWidth()*1.7);
			H=(int) (img.getHeight()*1.4);
			this.code=code;
		}
	}
	public void move() {
		X+=(int)(GamePanel.direSpeed*Speed);
	}
	public void HVMove() {
		if(Name=="jiantou") {
			//��
			Y+=jtSpeed;
			count++;
			if(count<=W/2) {
				jtSpeed=1;
			}else {
				jtSpeed=-1;
			}
			if(count==W) {
				count=0;
			}
		}
	}
}
