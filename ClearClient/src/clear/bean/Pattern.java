package clear.bean;

public class Pattern {
	//对应的图案 .
	private int image;
	//图案的x、y坐标
	private int x_coor;
	private int y_coor;
	//边长
	private static int EDGE_LEN=54;
	/*构造器*/
	public Pattern(){
		image=0;
		x_coor=0;
		y_coor=0;
	}
	public Pattern(int image,int x_coor,int y_coor){
		this.image=image;
		this.x_coor=x_coor;
		this.y_coor=y_coor;
	}
	/*图标对应的图片*/
	public void setImage(int img){
		image=img;
	}
	public int getImage(){
		return image;
	}
	/*图标对应的x坐标*/
	public void setX_coor(int x_coor){
		this.x_coor=x_coor;
	}
	public int getX_coor(){
		return x_coor;
	}
	/*图标对应的y坐标*/
	public void setY_coor(int y_coor){
		this.y_coor=y_coor;
	}
	public int getY_coor(){
		return y_coor;
	}
	/*返回边长*/
	public static int getPatternEdge_Len(){
		return EDGE_LEN;
	}
}
