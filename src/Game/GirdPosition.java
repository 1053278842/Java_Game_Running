package Game;



/*
 * ����x,y�����Լ�������
 * ��������ת���Ĺ��캯��
 */
public class GirdPosition {
	private int x;
	private int y;
	private GirdPosition parent;
	public GirdPosition() {}
	//˫��������캯��
	public GirdPosition(int x,int y) {
		this.x=x;
		this.y=y;
	}
	//����������캯��
	public GirdPosition(int x,int y,GirdPosition fp) {
		this.x=x;
		this.y=y;
		parent=fp;
	}
	//����
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public GirdPosition getParent() {
		return parent;
	}
	public void move() {
		x+=GamePanel.direSpeed;
	}
	//��дequals
	@Override
	public boolean equals(Object obj) {
		if(((GirdPosition)obj).getX() == this.x && ((GirdPosition)obj).getY() == this.y){
			return true;
		}else{
			return false;
		}
	}
}
