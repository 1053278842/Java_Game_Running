package Game;
/*
 * UI
 */
public class PigmentBox {
	int w,h,x,y;
	int index;//代表自身的颜色
	boolean isSearched=false;//解决从交互对象转变为UI对象后x坐标的问题
	PigmentBox(int i){
		w=15;
		h=20;
		index=i;
	}
//	public void position(Player p){
//		x=p.X+p.W/2-w/2;
//		y=p.Y-p.H/2-h/2;
//	}
}
