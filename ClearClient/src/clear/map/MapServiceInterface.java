package clear.map;

import java.util.ArrayList;

public interface MapServiceInterface {
	/**
	 * 初始化map等成员变量
	 */
	public void init();
	/**
	 * 重启游戏时将Map等变量再次初始化
	 */
	public void restart();
	/**
	 * 消除包含该点的连锁（即被消除的图标对应的Map值变成EMPTY）
	 * @param x 图案的x坐标
	 * @param y 图案的y坐标
	 */
	public void clearLinked(int x,int y);
    /**
     * 遍历坐标图案的上下和左右判断是否存在包含它的连锁
     * @return true代表存在相应的连锁false代表不存在相应的连锁
     * @param x 图案的x坐标
     * @param y 图案的y坐标
     */
	public boolean isInChain(int x,int y);
    /**
     * 在消除函数中把消除掉的图案的Map值改变成了EMPTY
     * 此函数将实现图案的下落或右移，即将Map中的EMPTY值都交换到上方或右方 
     */
	public void moveMap();
	/**
	 * 每次downMap后都调用此函数将值为EMPTY的Map点重新赋值一个随机数
	 */
	public void updateMap();
	/**
	 * 遍历整个Map判断整个地图中是否存在连锁
	 * @return true代表存在false代表不存在
	 */
    public boolean haveDis();
    /**
     * 遍历整个Map将所有的连锁图案都消除掉（将对应Map值设置为EMPTY）
     */
    public void clearAllMap();
    /**
     * 
     * @return 返回计算得到的分数值
     */
    public int getScore();
    /**
     * 返回某个坐标对应的Map值
     * @param x 图案的x坐标
     * @param y 图案的y坐标
     * @return 返回对应的Map值
     */
    public int returnMapValue(int x,int y);
    /**
     * 每次点击坐标就将坐标传递给这个函数
     * @param x 被点击图案的x坐标
     * @param y 被点击图案的y坐标
     * @return 返回是否能存在连锁
     */
    public boolean swap(int x,int y);
    public void initFallDisMap();
    /**
     * 处理每一次的鼠标有效点击操作
     * @param x 被点击图案的x坐标
     * @param y 被单机图案的y坐标
     * @return z
     */
    public boolean clickDone(int x,int y);
    /**
     * 检测是否是死局
     * @return true表示不是死局，false表示是死局
     */
    public boolean isNotDead();
    /**
     * 用户找不到消除时进行提示
     * @return list中第一个表示x，第二个表示y
     */
    public ArrayList<Integer> hint();
    /**
     * A道具的效果
     */
    public void A_prop(int x,int y);
    /**
     * B道具的效果
     */
    public void B_prop(int x,int y);
    /**
     * C道具的效果
     * 快速进入super模式
     * 
     */
    public void C_prop();
    /**
     * D道具的效果
     * 得分增加10%
     */
    public void D_prop();
    /**
     * E道具的效果
     * 快速提示
     */
    public void E_prop();
    /**
     * super模式
     */
    public void superMode(boolean bool);
    /**
     * 返回是否是第二次单机
     * @return 
     */
    public boolean needSwap(int x,int y);
    /**
     * 返回上一次的点击信息
     * @return 上一次的点击信息
     */
    public String getLastClicked();
    /**
     * 验证是否是双击
     * @return isDoubleClicked
     */
    public boolean isDoubleClicked();
    /**
     * 计算本局所得金币
     * @return 本局所得金币
     */
    public int getGold(int level);
    /**
     * 返回某个坐标对应的fillDisMap值
     * @param x 图案的x坐标
     * @param y 图案的y坐标
     * @return 返回对应的fillDisMap值
     */    
    public int returnFallDisMapValue(int x, int y);
    
    public int getFallDis(int x,int y);
    
	public  int[][] getMap();

	public void setMap(int[][] map);
	
}
