package Game;
import java.util.ArrayList;
import java.util.List;

/*
 * A*�㷨������
 * ����������ͼ
 * ������������Ӷ�����֮�ƶ���
 * �ܹ�������̬��ͼ�еĿ��ƶ�����
 */
import javax.swing.JPanel;



public class Gird extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int girdDistance=1;//��͵�֮��ļ����panel�����size
	public static List<GirdPosition> allP=new ArrayList<GirdPosition>();
	public Gird() {
	}
	public void drawP(int s,int e) {
		allP.clear();
		//һ¥
		for(int x=s;x<e-girdDistance;x+=girdDistance) {
			GirdPosition p=new GirdPosition(x,Player.oneFloorGame);
			allP.add(p);
		}
		//��¥
		for(int x=s;x<e-girdDistance;x+=girdDistance) {
			GirdPosition p=new GirdPosition(x,Player.twoFloorGame);
			allP.add(p);
		}
		//��¥��·�߼���
		/*
		 * �ҵ��Ӵ����ӵ�P����x
		 * @ Hid halfInteractivebox �뽻������
		 */
		for(Building hib:GamePanel.lds) {
			if(hib.name=="ladder") {
				//�ҵ����ϵ�λ�ĵ㣨�Ӵ������ӵ�)
				int x = 0;
				for(GirdPosition p:allP) {
					if(p.getX()>hib.X+hib.W/3&&p.getX()<hib.X+hib.W) {
						x=p.getX();
						break;
					}
				}
				for(int i=Player.twoFloorGame;i<Player.oneFloorGame;i+=girdDistance) {
					GirdPosition p=new GirdPosition(x,i);
//					System.out.println("�����������һ������y����Ϊ"+i+"һ¥����Ϊ��"+Player.oneFloorGame);
					allP.add(p);
				}
			}
		}
		//�ų���ײ�䷶Χ�ڵ�
		for(int i=0;i<allP.size();i++) {
			GirdPosition p=allP.get(i);
			for(CollisionBox cb:GamePanel.clsboxes) {
				if(p.getX()>cb.x&&p.getX()<cb.x+cb.w&&Math.abs(p.getY()-(cb.y+cb.h))<10) {
					allP.remove(i);
					i--;
				}
			}
		}
	}
	public 	List<GirdPosition> getAllP(){
		return allP;
	}
}
