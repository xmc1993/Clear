package clear.bean;

public class Pattern {
	//��Ӧ��ͼ�� .
	private int image;
	//ͼ����x��y����
	private int x_coor;
	private int y_coor;
	//�߳�
	private static int EDGE_LEN=54;
	/*������*/
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
	/*ͼ���Ӧ��ͼƬ*/
	public void setImage(int img){
		image=img;
	}
	public int getImage(){
		return image;
	}
	/*ͼ���Ӧ��x����*/
	public void setX_coor(int x_coor){
		this.x_coor=x_coor;
	}
	public int getX_coor(){
		return x_coor;
	}
	/*ͼ���Ӧ��y����*/
	public void setY_coor(int y_coor){
		this.y_coor=y_coor;
	}
	public int getY_coor(){
		return y_coor;
	}
	/*���ر߳�*/
	public static int getPatternEdge_Len(){
		return EDGE_LEN;
	}
}
