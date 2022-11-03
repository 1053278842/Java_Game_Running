package Game;



/*
 * 包含x,y坐标以及父对象
 * 包含几个转换的构造函数
 */
public class GirdPosition {
	private int x;
	private int y;
	private GirdPosition parent;
	public GirdPosition() {}
	//双点基础构造函数
	public GirdPosition(int x,int y) {
		this.x=x;
		this.y=y;
	}
	//三点基础构造函数
	public GirdPosition(int x,int y,GirdPosition fp) {
		this.x=x;
		this.y=y;
		parent=fp;
	}
	//方法
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
	//重写equals
	@Override
	public boolean equals(Object obj) {
		if(((GirdPosition)obj).getX() == this.x && ((GirdPosition)obj).getY() == this.y){
			return true;
		}else{
			return false;
		}
	}
}
