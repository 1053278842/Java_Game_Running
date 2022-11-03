package Game;

import java.util.ArrayList;
import java.util.List;

/*
 * 自动寻路
 */
public class AutoFindWay {
	/*
	 * 得到最近的路线
	 * 原理，以启点开始扩散激活四周。同时设置中心点为父对象
	 * 判断四周是否可以扩散，若包括了重点则终止扩散，回溯寻找父对象至起始点
	 * 1、扩散功能以起始点为单位；四周点加入openList,并将他们的父节点设置为中心加入closeList
	 * 2、遍历openList继续扩散，设置中心点等
	 * 3、判断openList是否包含了结尾
	 * 4、回溯寻找路线wayList
	 */
	public static GirdPosition begin = null;
	public static GirdPosition end= null;
	List<GirdPosition> openList = new ArrayList<>();
	List<GirdPosition> closeList = new ArrayList<>();
	public static final int size=Gird.girdDistance;//单位
	/*
	 * BUGbackMove使背景移动后，afw失效，但是依旧可以获取起始坐标
	 */
	public List<GirdPosition> getWay(Player player,Empty empty) {
		openList.clear();
		closeList.clear();
		/*
		 * 1、将building转换为Girdp点
		 * 2、从起点开始扩散
		 */
		List<GirdPosition> wayList = new ArrayList<>();
		List<GirdPosition> tmpList = null;
		//获得GirdP类型的起始点（起点是敌人）
		for(GirdPosition p:Gird.allP) {
			if(p.getX()>=player.X&&p.getX()<player.X+player.W&&Math.abs(p.getY()-(player.Y+player.H))<=0) {
				end=p;
//				System.out.println("获得玩家坐标");
				break;
			}
		}
		for(GirdPosition p:Gird.allP) {
			if(p.getX()>=empty.X&&p.getX()<empty.X+empty.W&&Math.abs(p.getY()-(empty.Y+empty.H))<=size-1) {
				begin=p;
//				System.out.println("获得警察坐标");
				break;
			}
		}
		//初始
		openList.add(begin);
		for(int i=0;i<openList.size();i++) {
			GirdPosition cp=openList.get(i);
			tmpList=aroundP(cp);
//			wayList.addAll(tmpList);
			//添加openList
			for(GirdPosition p:tmpList){
				if(!openList.contains(p)) {
					openList.add(p);
				}
			}
			//判断是否接触到end
			if(tmpList.contains(end)) {
				for(GirdPosition gp:tmpList) {
					if(gp.equals(end)) {
						closeList.add(gp);//最后一个是终点
//						System.out.println("接触到终点");
						break;
					}
				}
				break;
			}
			//删除扩散后的中心块
			openList.remove(i);
			i--;
		}
		//回溯查找
		for(int i=0;i<closeList.size();i++) {
			if(wayList.size()>0) {
				if(wayList.get(wayList.size()-1).getParent().equals(closeList.get(i))) {
					wayList.add(closeList.get(i));
					if(closeList.get(i).equals(begin)) {
						break;
					}
					closeList.remove(i);
					i=-1;
					continue;
				}
			}
			//没找到终点时
			if(closeList.get(i).equals(end)) {
				wayList.add(closeList.get(i)); 
				closeList.remove(i);
				i=-1;
				continue;
			}
		}
		if(wayList.size()==0||wayList==null) {
			System.out.println("未找到路");
		}
		return wayList;
	}
	private List<GirdPosition> aroundP(GirdPosition cp) {
		List<GirdPosition> list = new ArrayList<>();
		GirdPosition p;
		//查上
		p=new GirdPosition(cp.getX(),cp.getY()-size,cp);
		if(Gird.allP.contains(p)) {
			if(!openList.contains(p)&&!closeList.contains(p)) {
				list.add(p);
//				System.out.println("上");
			}
		}
		//查下
		p=new GirdPosition(cp.getX(),cp.getY()+size,cp);
		if(Gird.allP.contains(p)) {
			if(!openList.contains(p)&&!closeList.contains(p)) {
				list.add(p);
//				System.out.println("下");
			}
		}
		//查左
		p=new GirdPosition(cp.getX()-size,cp.getY(),cp);
		if(Gird.allP.contains(p)) {
			if(!openList.contains(p)&&!closeList.contains(p)) {
				list.add(p);
//				System.out.println("左");
			}
		}
		//右边
		p=new GirdPosition(cp.getX()+size,cp.getY(),cp);
		if(Gird.allP.contains(p)) {
			if(!openList.contains(p)&&!closeList.contains(p)) {
				list.add(p);
//				System.out.println("右");
			}
		}
		closeList.add(cp);
		return list;
	}
	public void clearList() {
		openList.clear();
		closeList.clear();
	}
}
