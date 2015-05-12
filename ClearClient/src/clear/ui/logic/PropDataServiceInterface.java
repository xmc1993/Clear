package clear.ui.logic;

import clear.bean.*;

public interface PropDataServiceInterface {
	/**
	 * 初始化玩家的道具模块
	 * @param player 玩家
	 */
	public void init(Player player);
	/**
	 * 购买道具
	 * @param prop 道具
	 * @return 是否购买成功（金钱不足）
	 */
	public boolean buyProp(Prop prop);
}
