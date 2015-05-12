package clear.ui.logic;

import java.util.ArrayList;

import clear.bean.PlayerInfo;

public interface FriendDataServiceInterface {
	/**
	 * 获得玩家的FriendData模块
	 * @param name 玩家昵称
	 */
	public ArrayList<String> getFriends(String name);
	public ArrayList<String> getOnlineFriends(String name);
	/**
	 * 添加朋友
	 * @param name 玩家昵称
	 */
	public String addFriend(String name);
	/**
	 * 查看好友资料
	 * @param player 玩家
	 * @return name 玩家昵称
	 */
	public PlayerInfo scanPlayerInfo(String name);
	/**
	 * 删除好友
	 * @param name 玩家昵称
	 */
	public String deleteFriend(String name);
	/**
	 * 同意添加好友
	 * @return name 玩家昵称
	 */
	public String agreeFriend(String name);
	
}
