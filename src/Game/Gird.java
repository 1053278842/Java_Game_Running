package Game;
import java.util.ArrayList;
import java.util.List;

/*
 * A*算法的网格
 * 覆盖整个地图
 * 是其他对象的子对象（随之移动）
 * 能够画出静态地图中的可移动区域
 */
import javax.swing.JPanel;



public class Gird extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int girdDistance=1;//点和点之间的间隔兼panel化后的size
	public static List<GirdPosition> allP=new ArrayList<GirdPosition>();
	public Gird() {
	}
	public void drawP(int s,int e) {
		allP.clear();
		//一楼
		for(int x=s;x<e-girdDistance;x+=girdDistance) {
			GirdPosition p=new GirdPosition(x,Player.oneFloorGame);
			allP.add(p);
		}
		//二楼
		for(int x=s;x<e-girdDistance;x+=girdDistance) {
			GirdPosition p=new GirdPosition(x,Player.twoFloorGame);
			allP.add(p);
		}
		//将楼梯路线加入
		/*
		 * 找到接触梯子的P坐标x
		 * @ Hid halfInteractivebox 半交互对象
		 */
		for(Building hib:GamePanel.lds) {
			if(hib.name=="ladder") {
				//找到符合单位的点（接触到梯子的)
				int x = 0;
				for(GirdPosition p:allP) {
					if(p.getX()>hib.X+hib.W/3&&p.getX()<hib.X+hib.W) {
						x=p.getX();
						break;
					}
				}
				for(int i=Player.twoFloorGame;i<Player.oneFloorGame;i+=girdDistance) {
					GirdPosition p=new GirdPosition(x,i);
//					System.out.println("铺设的最下面一个方块y坐标为"+i+"一楼坐标为："+Player.oneFloorGame);
					allP.add(p);
				}
			}
		}
		//排除碰撞箱范围内的
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
