package clear.map;

import java.util.ArrayList;

//import java.util.Scanner;

public class Map implements MapServiceInterface{
	
	private final int EMPTY=7;//�趨EMPTYֵ��Ϊ������״̬
	private final int A_prop_0=8;//�趨A���ߵ�ֵ
	private final int A_prop_1=9;//�趨A���ߵ�ֵ
	private final int A_prop_2=10;//�趨A���ߵ�ֵ
	private final int A_prop_3=11;//�趨A���ߵ�ֵ
	private final int A_prop_4=12;//�趨A���ߵ�ֵ
	private final int A_prop_5=13;//�趨A���ߵ�ֵ
	private final int A_prop_6=14;//�趨A���ߵ�ֵ
	
	private final int B_prop=15;//�趨B���ߵ�ֵ
	private final int Map_Row=9;//Map������
	private final int Map_Col=9;//Map������
	private final int NumOfPattern=7;//ͼ����������
	private final static int EDGE_LEN=54;
	private int Map[][];//��ͼ
	private int fallDisMap[][];
	private int xClicked;//���ڴ洢��һ�ε����ͼ����x����
	private int yClicked;//���ڴ洢��һ�ε����ͼ����y����
	private boolean isDoubleClicked = false;//�����ж��Ƿ��ǵ�һ�ε��
	private int col_count;//����������������
	private int row_count;//���������������
	private int score = 0;//�÷�
//	private boolean is_C = false;
//	private boolean is_E = false;
	private boolean is_super = false;

	public Map(int[][] Map){
		this.Map = Map ;
	}
	
	public Map(){
		init() ;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		Map=new int[Map_Row][Map_Col];
		for(int i=0;i<Map_Row;i++){
			for(int j=0;j<Map_Col;j++){
				int ran = (int)(Math.random()*NumOfPattern);
				Map[i][j] = ran;
			}
		}
		fallDisMap=new int[Map_Row][Map_Col];
		for(int i=0;i<Map_Row;i++){
			for(int j=0;j<Map_Col;j++){
				fallDisMap[i][j]=0;
			}
		}
		initFallDisMap();
		if(haveDis()){
			init();
		}
		if(!isNotDead()){
			init();
		}
	}
	public void initFallDisMap(){
		fallDisMap=new int[Map_Row][Map_Col];
		for(int i=0;i<Map_Row;i++){
			for(int j=0;j<Map_Col;j++){
				fallDisMap[i][j]=0;
			}
		}
	}
	@Override
	public void restart() {
		// TODO Auto-generated method stub
		init();
	}
	@Override
	public  int[][] getMap() {
		// TODO Auto-generated method stub
		return Map;
	}
	@Override
	public void setMap(int[][] map) {
		// TODO Auto-generated method stub
		Map = map;
	}
	//�鿴����Ƿ�����ͬ��ͼ��
	public boolean left(int x,int y){
		if(y==0){
			return false;
		}
		else if((Map[x][y]==Map[x][y-1])&&(Map[x][y-1]!=EMPTY)&&(Map[x][y]!=EMPTY)){
			return true;
		}
		else{
			return false;
		}
	}
	//�鿴�ұ��Ƿ�����ͬ��ͼ��
	public boolean right(int x,int y){
		if(y==Map_Col-1){
			return false;
		}
		else if((Map[x][y]==Map[x][y+1])&&(Map[x][y+1]!=EMPTY)&&(Map[x][y]!=EMPTY)){
			return true;
		}
		else{
			return false;
		}
	}
	//�鿴�ϱ��Ƿ�����ͬ��ͼ��
	public boolean above(int x,int y){
		if(x==0){
			return false;
		}
		else if((Map[x][y]==Map[x-1][y])&&(Map[x-1][y]!=EMPTY)&&(Map[x][y]!=EMPTY)){
			return true;
		}
		else{
			return false;
		}
	}
	//�鿴�±��Ƿ�����ͬ��ͼ��
	public boolean under(int x,int y){
		if(x==Map_Row-1){
			return false;
		}
		else if((Map[x+1][y]==Map[x][y])&&(Map[x+1][y]!=EMPTY)&&(Map[x][y]!=EMPTY)){
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public void clearLinked(int x, int y) {
		// TODO Auto-generated method stub
		int temp=Map[x][y];//һ����ʱֵ��������¼������걾����ֵ�Ա㵱����A����ʱ��������ͬ������
		int i;
		//��ֵһ���µĶ�ά���飬������в�����������֮���ٸ�ֵΪMap
		//Mapֱ�Ӳ����Ļ���ʹ�ø�ֵEMPTYʱ���ִ���
		int copy[][] = new int[Map_Row][Map_Col];
		for(i=0;i<Map_Row;i++){
			for(int j=0;j<Map_Col;j++){
				copy[i][j]=Map[i][j];
			}
		}
		row_count=0;
		col_count=0;
		if(is_Chain(x,y)==1){
			if(temp>7){
				for(i=0;i<y+1;i++){
					if((Map[x][y-i]>7)&&(Map[x][y-i]<15)){
						copy_A_prop(x, y-i, copy);
					}
					else{
						break;
					}
				}
				for(i=1;i<Map_Col-y;i++){
					if((Map[x][y+i]>7)&&(Map[x][y+i]<15)){
						copy_A_prop(x, y+i, copy);
					}
					else{
						break;
					}
				}
			}
			else{
				//������
				for(i=0;i<y+1;i++){
					if(left(x,y-i)){
						copy[x][y-i] = EMPTY;
						row_count++;
					}
					else{
						copy[x][y-i] = EMPTY;
						row_count++;
						break;
					}
				}
				
				//����ұ�
				for(i=0;i<Map_Col-y;i++){
					if(right(x,y+i)){
						copy[x][y+i] = EMPTY;
						row_count++;
					}
					else{
						copy[x][y+i] = EMPTY;
						row_count++;
						break;
					}
				}
			}
		}
		else if(is_Chain(x,y)==-1){
			if(temp>7){
				for(i=0;i<Map_Row-x;i++){
					if((Map[x+i][y]>7)&&(Map[x+i][y]<15)){
						copy_A_prop(x+i, y, copy);
					}
					else{
						break;
					}
				}
				for(i=1;i<x+1;i++){
					if((Map[x-i][y]>7)&&(Map[x-i][y]<15)){
						copy_A_prop(x-i, y, copy);
					}
					else{
						break;
					}
				}
			}
			else{
				//����±�
				for(i=0;i<Map_Row-x;i++){
					if(under(x+i,y)){
						copy[x+i][y]=EMPTY;
						col_count++;
					}
					else{
						copy[x+i][y]=EMPTY;
						col_count++;
						break;
					}
				}
				//����ϱ�
				for(i=0;i<x+1;i++){

					if(above(x-i,y)){
						copy[x-i][y]=EMPTY;
						col_count++;
					}
					else{
						copy[x-i][y]=EMPTY;
						col_count++;
						break;
					}
				}
			}

		}
		else if(is_Chain(x,y)==2){
			if(temp>7){
				for(i=0;i<y+1;i++){
					if((Map[x][y-i]>7)&&(Map[x][y-i]<15)){
						copy_A_prop(x, y-i, copy);
					}
					else{
						break;
					}
				}
				for(i=1;i<Map_Col-y;i++){
					if((Map[x][y+i]>7)&&(Map[x][y+i]<15)){
						copy_A_prop(x, y+i, copy);
					}
					else{
						break;
					}
				}
				for(i=1;i<Map_Row-x;i++){
					if((Map[x+i][y]>7)&&(Map[x+i][y]<15)){
						copy_A_prop(x+i, y, copy);
					}
					else{
						break;
					}
				}
				for(i=1;i<x+1;i++){
					if((Map[x-i][y]>7)&&(Map[x-i][y]<15)){
						copy_A_prop(x-i, y, copy);
					}
					else{
						break;
					}
				}
			}
			else{
				//������
				for(i=0;i<y+1;i++){
					if(left(x,y-i)){
						copy[x][y-i] = EMPTY;
						row_count++;
					}
					else{
						copy[x][y-i] = EMPTY;
						row_count++;
						break;
					}
				}
				
				//����ұ�
				for(i=0;i<Map_Col-y;i++){
					if(right(x,y+i)){
						copy[x][y+i] = EMPTY;
						row_count++;
					}
					else{
						copy[x][y+i] = EMPTY;
						row_count++;
						break;
					}
				}
				//����±�
				for(i=0;i<Map_Row-x;i++){
					if(under(x+i,y)){
						copy[x+i][y]=EMPTY;
						col_count++;
					}
					else{
						copy[x+i][y]=EMPTY;
						col_count++;
						break;
					}
				}
				//����ϱ�
				for(i=0;i<x+1;i++){
					if(above(x-i,y)){
						copy[x-i][y]=EMPTY;
						col_count++;
					}
					else{
						copy[x-i][y]=EMPTY;
						col_count++;
						break;
					}
				}
			}
		}
		
		if(row_count!=0){
			row_count--;
		}
		if(col_count!=0){
			col_count--;
		}
		
		if((row_count==3&&col_count==4)||(row_count==4&&col_count==3)||(row_count==5)||(col_count==5)){
			copy[x][y]=B_prop;
		}
		else if((row_count==3&&col_count==3)||(row_count==4)||(col_count==4)){
			switch (temp) {
			case 0:
				copy[x][y]=A_prop_0;
				break;
			case 1:
				copy[x][y]=A_prop_1;
				break;
			case 2:
				copy[x][y]=A_prop_2;
				break;
			case 3:
				copy[x][y]=A_prop_3;
				break;
			case 4:
				copy[x][y]=A_prop_4;
				break;
			case 5:
				copy[x][y]=A_prop_5;
				break;
			case 6:
				copy[x][y]=A_prop_6;
				break;
			default:
				break;
			}
			
		}
		//�������copy�ٸ�ֵ��Map
		for(i=0;i<Map_Row;i++){
			for(int j=0;j<Map_Col;j++){
				Map[i][j]=copy[i][j];
			}
		}
	}

	@Override
	public boolean isInChain(int x,int y){
		if(is_Chain(x,y)!=0){
			return true;
		}
		else{
			return false;
		}
	}
	//����1��ʾ������������-1��ʾ������������0��ʾ��������2��ʾ��������
	public int is_Chain(int x, int y) {
		// TODO Auto-generated method stub
		int _row_count=0;
		int _col_count=0;
		int i;
		//������
		for(i=0;i<y+1;i++){
			
			if(left(x,y-i)){
				_row_count++;
			}
			else{
				break;
			}
		}
		//����ұ�
		for(i=0;i<Map_Col-y;i++){
			if(right(x,y+i)){
				_row_count++;
			}
			else{
				break;
			}
		}
		//����±�
		for(i=0;i<Map_Row-x;i++){
			if(under(x+i,y)){
				_col_count++;
			}
			else{
				break;
			}
		}
		//����ϱ�
		for(i=0;i<x+1;i++){
			if(above(x-i,y)){
				_col_count++;
			}
			else{
				break;
			}
		}
		if(_row_count>1&&_col_count>1){
			return 2;
		}
		if(_row_count>1){
			return 1;
		}
		else if(_col_count>1){
			return -1;
		}
		else{
			return 0;
		}
	}

	@Override
	public void moveMap() {
		// TODO Auto-generated method stub
		for(int i=0;i<Map_Row;i++){
			for(int j=0;j<Map_Col;j++){
				if(Map[i][j]==EMPTY){
					int h=i;
					while(h>0){
						int temp = Map[h][j];
						Map[h][j] = Map[h-1][j];
						Map[h-1][j] = temp;
						h--;
					}
				}
			}
		}
	}

	@Override
	public void updateMap() {
		// TODO Auto-generated method stub
		for(int j=0;j<Map_Col;j++){
			for(int i=0;i<Map_Row;i++){
				if(Map[i][j]==EMPTY){
					Map[i][j] = (int)(Math.random()*NumOfPattern);
					while(!isNotDead()){
						Map[i][j] = (int)(Math.random()*NumOfPattern);
					}
				}
			}
		}
		
	}
	
	@Override
	public boolean haveDis() {
		// TODO Auto-generated method stub
		for(int j=0;j<Map_Col;j++){
			for(int i=0;i<Map_Row;i++){
				if(isInChain(i,j)){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void clearAllMap() {
		// TODO Auto-generated method stub
		for(int i=0;i<Map_Row;i++){
			for(int j=0;j<Map_Col;j++){
				if(is_Chain(i,j)!=0){
					clearLinked(i,j);
//					System.out.println(i+" "+j+" "+row_count+" "+col_count);
					calculateScore();
				}
				
			}
		}
		initFallDisMap();           //ˢ���������
		setFallDisMap();             //�õ��������
	}

	@Override
	public int getScore(){
		// TODO Auto-generated method stub
		return score;
	}
	
	public void calculateScore() {
		if(is_super){
			if(col_count>2){
				switch (col_count) {
				case 3:
					score = score + 200;
					break;
				case 4:
					score = score + 400;
					break;
				default:
					score = score + 1000;
					break;
				}
			}
			if(row_count>2){
				switch (row_count) {
				case 3:
					score = score + 200;
					break;
				case 4:
					score = score + 400;
					break;
				default:
					score = score + 1000;
					break;
				}
			}
		}
		else{
			if(col_count>2){
				switch (col_count) {
				case 3:
					score = score + 100;
					break;
				case 4:
					score = score + 200;
					break;
				default:
					score = score + 500;
					break;
				}
			}
			if(row_count>2){
				switch (row_count) {
				case 3:
					score = score + 100;
					break;
				case 4:
					score = score + 200;
					break;
				default:
					score = score + 500;
					break;
				}
			}
			
		}
		row_count=0;
		col_count=0;
	}

	@Override
	public int returnMapValue(int x, int y) {
		// TODO Auto-generated method stub
		return Map[x][y];
	}

	@Override
	public boolean swap(int x, int y) {
		// TODO Auto-generated method stub
		if(isDoubleClicked){
			if(((xClicked==x-1)&&(yClicked==y))
					||((xClicked==x+1)&&(yClicked==y))
					||((xClicked==x)&&(yClicked==y-1))
					||((xClicked==x)&&(yClicked==y+1))){
				int temp = Map[x][y];
				Map[x][y] = Map[xClicked][yClicked];
				Map[xClicked][yClicked] = temp;
				if(isInChain(x,y)||isInChain(xClicked,yClicked)){
					if(isInChain(x,y)){
						clearLinked(x,y);
						calculateScore();
					}
					if(isInChain(xClicked,yClicked)){
						clearLinked(xClicked,yClicked);
						calculateScore();
					}
					isDoubleClicked=false;
					initFallDisMap();           //ˢ���������
					setFallDisMap();             //�õ��������
					return true;
				}
				else{
					temp = Map[x][y];
					Map[x][y] = Map[xClicked][yClicked];
					Map[xClicked][yClicked] = temp;
					isDoubleClicked=false;
				}
			}
			else{
				isDoubleClicked = true;
				xClicked = x;
				yClicked = y;
			}
		}
		else{
			isDoubleClicked = true;
			xClicked = x;
			yClicked = y;
		}
		return false;
	}

	@Override
	public boolean clickDone(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isNotDead(){
		for(int i=0;i<Map_Row;i++){
			for(int j=0;j<Map_Col;j++){
				if((i==0)&&(j==0)){
					if(small_check(0, 0, 0, 1)||small_check(0, 0, 1, 0)){
						return true;
					}
				}
				else if((i==0)&&(j==Map_Col-1)){
					if(small_check(0, Map_Col-1, 0, Map_Col-2)||small_check(0, Map_Col-1, 1, Map_Col-1)){
						return true;
					}
				}
				else if((i==0)&&(j!=0)&&(j!=Map_Col-1)){
					if(small_check(0, j, 0, j-1)||small_check(0, j, 0, j+1)||small_check(0, j, 1, j)){
						return true;
					}
				}
				else if((i==Map_Row-1)&&(j==0)){
					if(small_check(Map_Row-1, 0, Map_Row-2, 0)||small_check(Map_Row-1, 0, Map_Row-1, 1)){
						return true;
					}
				}
				else if((i==Map_Row-1)&&(j==Map_Col-1)){
					if(small_check(Map_Row-1, Map_Col-1, Map_Row-1, Map_Col-2)||small_check(Map_Row-1, Map_Col-1, Map_Row-2, Map_Col-1)){
						return true;
					}
				}
				else if((i==Map_Row-1)&&(j!=Map_Col-1)&&(j!=0)){
					if(small_check(Map_Row-1,j, Map_Row-1, j-1)||small_check(Map_Row-1, j, Map_Row-2, j)||small_check(Map_Row-1,j, Map_Row-1, j+1)){
						return true;
					}
				}
				else if((j==Map_Col-1)&&(i!=0)&&(i!=Map_Row-1)){
					if(small_check(i,j, i-1, j)||small_check(i, j, i+1, j)||small_check(i,j, i, j-1)){
						return true;
					}
				}
				else if((j==0)&&(i!=0)&&(i!=Map_Row-1)){
					if(small_check(i,j, i-1, j)||small_check(i, j, i+1, j)||small_check(i,j, i, j+1)){
						return true;
					}
				}
				else{
					if(small_check(i, j, i-1, j)||small_check(i, j, i, j-1)||small_check(i, j, i+1, j)||small_check(i, j, i, j+1)){
						return true;
					}
				}
			}
		}
		return false;
	}
	//��ֹ�������õ�һ������
	public boolean small_check(int x1,int y1,int x2,int y2){
//		System.out.println(Map[x1][y1]+" "+Map[x2][y2]);
		int temp = Map[x1][y1];
		Map[x1][y1] = Map[x2][y2];
		Map[x2][y2] = temp;
//		System.out.println(Map[x1][y1]+" "+Map[x2][y2]);
		if(isInChain(x1,y1)||isInChain(x2,y2)){
			temp = Map[x1][y1];
			Map[x1][y1] = Map[x2][y2];
			Map[x2][y2] = temp;
			return true;
		}
		else{
			temp = Map[x1][y1];
			Map[x1][y1] = Map[x2][y2];
			Map[x2][y2] = temp;
			return false;
		}
	}
	@Override
	public ArrayList<Integer> hint() {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<Map_Row;i++){
			for(int j=0;j<Map_Col;j++){
				if((i==0)&&(j==0)){
					if(small_check(0, 0, 0, 1)){
						list.add(i);
						list.add(j);
						list.add(i);
						list.add(j+1);
						return list;
					}
					else if(small_check(0, 0, 1, 0)){
						list.add(i);
						list.add(j);
						list.add(i+1);
						list.add(j);
						return list;
					}
				}
				else if((i==0)&&(j==Map_Col-1)){
					if(small_check(i, j, i, j-1)){
						list.add(i);
						list.add(j);
						list.add(i);
						list.add(j-1);
						return list;
					}
					else if(small_check(0, Map_Col-1, 1, Map_Col-1)){
						list.add(i);
						list.add(j);
						list.add(i+1);
						list.add(j);
						return list;
					}
				}
				else if((i==0)&&(j!=0)&&(j!=Map_Col-1)){
					if(small_check(0, j, 0, j-1)){
						list.add(i);
						list.add(j);
						list.add(i);
						list.add(j-1);
						return list;
					}
					else if(small_check(0, j, 0, j+1)){
						list.add(i);
						list.add(j);
						list.add(i);
						list.add(j+1);
						return list;
					}
					else if(small_check(0, j, 1, j)){
						list.add(i);
						list.add(j);
						list.add(i+1);
						list.add(j);
						return list;
					}
				}
				else if((i==Map_Row-1)&&(j==0)){
					if(small_check(Map_Row-1, 0, Map_Row-2, 0)){
						list.add(i);
						list.add(j);
						list.add(i-1);
						list.add(j);
						return list;
					}
					else if(small_check(Map_Row-1, 0, Map_Row-1, 1)){
						list.add(i);
						list.add(j);
						list.add(i);
						list.add(j+1);
						return list;
					}
				}
				else if((i==Map_Row-1)&&(j==Map_Col-1)){
					if(small_check(Map_Row-1, Map_Col-1, Map_Row-1, Map_Col-2)){
						list.add(i);
						list.add(j);
						list.add(i);
						list.add(j-1);
						return list;
					}
					else if(small_check(Map_Row-1, Map_Col-1, Map_Row-2, Map_Col-1)){
						list.add(i);
						list.add(j);
						list.add(i-1);
						list.add(j);
						return list;
					}
				}
				else if((i==Map_Row-1)&&(j!=Map_Col-1)&&(j!=0)){
					if(small_check(Map_Row-1,j, Map_Row-1, j-1)){
						list.add(i);
						list.add(j);
						list.add(i);
						list.add(j-1);
						return list;
					}
					else if(small_check(Map_Row-1, j, Map_Row-2, j)){
						list.add(i);
						list.add(j);
						list.add(i-1);
						list.add(j);
						return list;
					}
					else if(small_check(Map_Row-1,j, Map_Row-1, j+1)){
						list.add(i);
						list.add(j);
						list.add(i);
						list.add(j+1);
						return list;
					}
				}
				else if((j==Map_Col-1)&&(i!=0)&&(i!=Map_Row-1)){
					if(small_check(i,j, i-1, j)){
						list.add(i);
						list.add(j);
						list.add(i-1);
						list.add(j);
						return list;
					}
					else if(small_check(i, j, i+1, j)){
						list.add(i);
						list.add(j);
						list.add(i+1);
						list.add(j);
						return list;
					}
					else if(small_check(i,j, i, j-1)){
						list.add(i);
						list.add(j);
						list.add(i);
						list.add(j-1);
						return list;
					}
				}
				else if((j==0)&&(i!=0)&&(i!=Map_Row-1)){
					if(small_check(i,j, i-1, j)){
						list.add(i);
						list.add(j);
						list.add(i-1);
						list.add(j);
						return list;
					}
					else if(small_check(i, j, i+1, j)){
						list.add(i);
						list.add(j);
						list.add(i+1);
						list.add(j);
						return list;
					}
					else if(small_check(i,j, i, j+1)){
						list.add(i);
						list.add(j);
						list.add(i);
						list.add(j+1);
						return list;
					}
				}
				else{
					if(small_check(i, j, i-1, j)){
						list.add(i);
						list.add(j);
						list.add(i-1);
						list.add(j);
						return list;
					}
					else if(small_check(i, j, i, j-1)){
						list.add(i);
						list.add(j);
						list.add(i);
						list.add(j-1);
						return list;
					}
					else if(small_check(i, j, i+1, j)){
						list.add(i);
						list.add(j);
						list.add(i+1);
						list.add(j);
						return list;
					}
					else if(small_check(i, j, i, j+1)){
						list.add(i);
						list.add(j);
						list.add(i);
						list.add(j+1);
						return list;
					}
				}
			}
		}
		return list;
	}
	@Override
	public void A_prop(int x,int y) {
		// TODO Auto-generated method stub
		Map[x][y]=EMPTY;
		for(int i=-1;i<2;i++){
			for(int j=-1;j<2;j++){
				if((x+i>=0)&&(x+i<Map_Row)&&(y+j>=0)&&(y+j<Map_Col)&&(Map[x+i][y+j]<7)&&(!(i==0&&j==0))){
					Map[x+i][y+j]=EMPTY;
				}
			}
		}
		if(is_super){
			score+=1000;
		}
		else{
			score+=500;
		}
		initFallDisMap();           //ˢ���������
		setFallDisMap();             //�õ��������
		
	}
	
	public void copy_A_prop(int x,int y,int[][] copy) {
		// TODO Auto-generated method stub
		copy[x][y]=EMPTY;
		for(int i=-1;i<2;i++){
			for(int j=-1;j<2;j++){
				if((x+i>=0)&&(x+i<Map_Row)&&(y+j>=0)&&(y+j<Map_Col)&&(Map[x+i][y+j]<7)&&(!(i==0&&j==0))){
					copy[x+i][y+j]=EMPTY;
				}
			}
		}
		if(is_super){
			score+=1000;
		}
		else{
			score+=500;
		}
		initFallDisMap();           //ˢ���������
		setFallDisMap();             //�õ��������
	}
	@Override
	public void B_prop(int x, int y) {
		// TODO Auto-generated method stub
		int i;
		for(i=0;i<Map_Row;i++){
			if(Map[i][y]<7){
				Map[i][y]=EMPTY;
			}
		}
		for(i=0;i<Map_Col;i++){
			if(Map[x][i]<7){
				Map[x][i]=EMPTY;
			}
		}
		Map[x][y]=EMPTY;
		if(is_super){
			score+=2000;
		}
		else{
			score+=1000;
		}
		initFallDisMap();           //ˢ���������
		setFallDisMap();             //�õ��������
	}
	@Override
	public void C_prop() {
		// TODO Auto-generated method stub
//		is_C = true;
	}
	@Override
	public void D_prop() {
		// TODO Auto-generated method stub
		score=score+score/10;
	}
	@Override
	public void E_prop() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void superMode(boolean bool) {
		// TODO Auto-generated method stub
		if(bool){
			is_super=true;
		}
		else{
			is_super=false;
		}
	}
	@Override
	public boolean needSwap(int x, int y) {
		// TODO Auto-generated method stub
		return (isDoubleClicked&&((Math.abs(x-xClicked)+Math.abs(y-yClicked))==1));
	}
	@Override
	public String getLastClicked() {
		// TODO Auto-generated method stub
		return yClicked+";"+xClicked;
	}
	@Override
	public boolean isDoubleClicked() {
		// TODO Auto-generated method stub
		return isDoubleClicked;
	}
	@Override
	public int getGold(int level) {
		// TODO Auto-generated method stub
		return score/100+score*(level-1)/10000;
	}
	@Override
	public int returnFallDisMapValue(int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getFallDis(int x, int y) {
		return fallDisMap[x][y];
	}
	private void setFallDisMap(){
		for(int i=0;i<Map_Row;i++){
			int count=0;
			int j=0;
			while(Map[j][i]!=EMPTY){//������������
				j++;
				if(j>8){
					break;
				}
			}
			while((j<9)&&(Map[j][i]==EMPTY)){//���������εĸ�����
				j++;
				count++;
				if(j>8){
					break;
				}
			}
			for(int k=0;k<j;k++){//����Ҫ�����ͼ�긳ֵ
				fallDisMap[k][i]=count*EDGE_LEN;
			}
			
		}
	}
}
