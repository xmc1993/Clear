package clear.map;

import java.util.ArrayList;

public interface MapServiceInterface {
	/**
	 * ��ʼ��map�ȳ�Ա����
	 */
	public void init();
	/**
	 * ������Ϸʱ��Map�ȱ����ٴγ�ʼ��
	 */
	public void restart();
	/**
	 * ���������õ������������������ͼ���Ӧ��Mapֵ���EMPTY��
	 * @param x ͼ����x����
	 * @param y ͼ����y����
	 */
	public void clearLinked(int x,int y);
    /**
     * ��������ͼ�������º������ж��Ƿ���ڰ�����������
     * @return true���������Ӧ������false����������Ӧ������
     * @param x ͼ����x����
     * @param y ͼ����y����
     */
	public boolean isInChain(int x,int y);
    /**
     * �����������а���������ͼ����Mapֵ�ı����EMPTY
     * �˺�����ʵ��ͼ������������ƣ�����Map�е�EMPTYֵ���������Ϸ����ҷ� 
     */
	public void moveMap();
	/**
	 * ÿ��downMap�󶼵��ô˺�����ֵΪEMPTY��Map�����¸�ֵһ�������
	 */
	public void updateMap();
	/**
	 * ��������Map�ж�������ͼ���Ƿ��������
	 * @return true�������false��������
	 */
    public boolean haveDis();
    /**
     * ��������Map�����е�����ͼ����������������ӦMapֵ����ΪEMPTY��
     */
    public void clearAllMap();
    /**
     * 
     * @return ���ؼ���õ��ķ���ֵ
     */
    public int getScore();
    /**
     * ����ĳ�������Ӧ��Mapֵ
     * @param x ͼ����x����
     * @param y ͼ����y����
     * @return ���ض�Ӧ��Mapֵ
     */
    public int returnMapValue(int x,int y);
    /**
     * ÿ�ε������ͽ����괫�ݸ��������
     * @param x �����ͼ����x����
     * @param y �����ͼ����y����
     * @return �����Ƿ��ܴ�������
     */
    public boolean swap(int x,int y);
    public void initFallDisMap();
    /**
     * ����ÿһ�ε������Ч�������
     * @param x �����ͼ����x����
     * @param y ������ͼ����y����
     * @return z
     */
    public boolean clickDone(int x,int y);
    /**
     * ����Ƿ�������
     * @return true��ʾ�������֣�false��ʾ������
     */
    public boolean isNotDead();
    /**
     * �û��Ҳ�������ʱ������ʾ
     * @return list�е�һ����ʾx���ڶ�����ʾy
     */
    public ArrayList<Integer> hint();
    /**
     * A���ߵ�Ч��
     */
    public void A_prop(int x,int y);
    /**
     * B���ߵ�Ч��
     */
    public void B_prop(int x,int y);
    /**
     * C���ߵ�Ч��
     * ���ٽ���superģʽ
     * 
     */
    public void C_prop();
    /**
     * D���ߵ�Ч��
     * �÷�����10%
     */
    public void D_prop();
    /**
     * E���ߵ�Ч��
     * ������ʾ
     */
    public void E_prop();
    /**
     * superģʽ
     */
    public void superMode(boolean bool);
    /**
     * �����Ƿ��ǵڶ��ε���
     * @return 
     */
    public boolean needSwap(int x,int y);
    /**
     * ������һ�εĵ����Ϣ
     * @return ��һ�εĵ����Ϣ
     */
    public String getLastClicked();
    /**
     * ��֤�Ƿ���˫��
     * @return isDoubleClicked
     */
    public boolean isDoubleClicked();
    /**
     * ���㱾�����ý��
     * @return �������ý��
     */
    public int getGold(int level);
    /**
     * ����ĳ�������Ӧ��fillDisMapֵ
     * @param x ͼ����x����
     * @param y ͼ����y����
     * @return ���ض�Ӧ��fillDisMapֵ
     */    
    public int returnFallDisMapValue(int x, int y);
    
    public int getFallDis(int x,int y);
    
	public  int[][] getMap();

	public void setMap(int[][] map);
	
}
