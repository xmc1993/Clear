package clear.ui.logic;

import clear.bean.*;

public interface UploadDataServiceInterface {
	/**
	 * 初始化玩家的UploadData模块
	 * @param player 玩家
	 */
	public void init(Player player);
	/**
	 * 更新玩家的最高分
	 * @param score 该局分数 
	 * @return 返回该局分数是否为最高分
	 */
	public boolean updateHigestScore(int score);
	/**
	 * 更新玩家最高连击数
	 * @param count 该局连击数
	 * @return 返回该连击数是否为最高
	 */
	public boolean updateHigestComboCount(int count);
	/**
	 * 更新玩家总局数
	 */
	public void updateTotalCount();
	/**
	 * 更新平均得分
	 * @param score 该局分数
	 */
	public void updateAverageScore(int score);
	/**
	 * 更新每日局数
	 */
	public void updateDayPlayedCount();
	/**
	 * 更新每日平均得分
	 * @param score 该局得分
	 */
	public void updateDayAverageScore(int score);
	/**
	 * 更新每局得分
	 * @param socre 该局得分
	 */
	public void updateScore(int socre);
	/**
	 * 更新经验（升级的话也要更新相应等级）
	 * @param exp 本局经验值
	 */
	public void updateExperence(int exp);
	/**
	 * 更新玩家金钱值
	 * @param money 本局金钱值
	 */
	public void updateGold(int money);
}
