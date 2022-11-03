package Game;

import java.util.ArrayList;
import java.util.List;

/*
 * �Զ�Ѱ·
 */
public class AutoFindWay {
	/*
	 * �õ������·��
	 * ԭ�������㿪ʼ��ɢ�������ܡ�ͬʱ�������ĵ�Ϊ������
	 * �ж������Ƿ������ɢ�����������ص�����ֹ��ɢ������Ѱ�Ҹ���������ʼ��
	 * 1����ɢ��������ʼ��Ϊ��λ�����ܵ����openList,�������ǵĸ��ڵ�����Ϊ���ļ���closeList
	 * 2������openList������ɢ���������ĵ��
	 * 3���ж�openList�Ƿ�����˽�β
	 * 4������Ѱ��·��wayList
	 */
	public static GirdPosition begin = null;
	public static GirdPosition end= null;
	List<GirdPosition> openList = new ArrayList<>();
	List<GirdPosition> closeList = new ArrayList<>();
	public static final int size=Gird.girdDistance;//��λ
	/*
	 * BUGbackMoveʹ�����ƶ���afwʧЧ���������ɿ��Ի�ȡ��ʼ����
	 */
	public List<GirdPosition> getWay(Player player,Empty empty) {
		openList.clear();
		closeList.clear();
		/*
		 * 1����buildingת��ΪGirdp��
		 * 2������㿪ʼ��ɢ
		 */
		List<GirdPosition> wayList = new ArrayList<>();
		List<GirdPosition> tmpList = null;
		//���GirdP���͵���ʼ�㣨����ǵ��ˣ�
		for(GirdPosition p:Gird.allP) {
			if(p.getX()>=player.X&&p.getX()<player.X+player.W&&Math.abs(p.getY()-(player.Y+player.H))<=0) {
				end=p;
//				System.out.println("����������");
				break;
			}
		}
		for(GirdPosition p:Gird.allP) {
			if(p.getX()>=empty.X&&p.getX()<empty.X+empty.W&&Math.abs(p.getY()-(empty.Y+empty.H))<=size-1) {
				begin=p;
//				System.out.println("��þ�������");
				break;
			}
		}
		//��ʼ
		openList.add(begin);
		for(int i=0;i<openList.size();i++) {
			GirdPosition cp=openList.get(i);
			tmpList=aroundP(cp);
//			wayList.addAll(tmpList);
			//���openList
			for(GirdPosition p:tmpList){
				if(!openList.contains(p)) {
					openList.add(p);
				}
			}
			//�ж��Ƿ�Ӵ���end
			if(tmpList.contains(end)) {
				for(GirdPosition gp:tmpList) {
					if(gp.equals(end)) {
						closeList.add(gp);//���һ�����յ�
//						System.out.println("�Ӵ����յ�");
						break;
					}
				}
				break;
			}
			//ɾ����ɢ������Ŀ�
			openList.remove(i);
			i--;
		}
		//���ݲ���
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
			//û�ҵ��յ�ʱ
			if(closeList.get(i).equals(end)) {
				wayList.add(closeList.get(i)); 
				closeList.remove(i);
				i=-1;
				continue;
			}
		}
		if(wayList.size()==0||wayList==null) {
			System.out.println("δ�ҵ�·");
		}
		return wayList;
	}
	private List<GirdPosition> aroundP(GirdPosition cp) {
		List<GirdPosition> list = new ArrayList<>();
		GirdPosition p;
		//����
		p=new GirdPosition(cp.getX(),cp.getY()-size,cp);
		if(Gird.allP.contains(p)) {
			if(!openList.contains(p)&&!closeList.contains(p)) {
				list.add(p);
//				System.out.println("��");
			}
		}
		//����
		p=new GirdPosition(cp.getX(),cp.getY()+size,cp);
		if(Gird.allP.contains(p)) {
			if(!openList.contains(p)&&!closeList.contains(p)) {
				list.add(p);
//				System.out.println("��");
			}
		}
		//����
		p=new GirdPosition(cp.getX()-size,cp.getY(),cp);
		if(Gird.allP.contains(p)) {
			if(!openList.contains(p)&&!closeList.contains(p)) {
				list.add(p);
//				System.out.println("��");
			}
		}
		//�ұ�
		p=new GirdPosition(cp.getX()+size,cp.getY(),cp);
		if(Gird.allP.contains(p)) {
			if(!openList.contains(p)&&!closeList.contains(p)) {
				list.add(p);
//				System.out.println("��");
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
