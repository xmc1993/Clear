package clear.ui.logic;

import java.util.ArrayList;

import clear.bean.PlayerInfo;

public interface FriendDataServiceInterface {
	/**
	 * �����ҵ�FriendDataģ��
	 * @param name ����ǳ�
	 */
	public ArrayList<String> getFriends(String name);
	public ArrayList<String> getOnlineFriends(String name);
	/**
	 * �������
	 * @param name ����ǳ�
	 */
	public String addFriend(String name);
	/**
	 * �鿴��������
	 * @param player ���
	 * @return name ����ǳ�
	 */
	public PlayerInfo scanPlayerInfo(String name);
	/**
	 * ɾ������
	 * @param name ����ǳ�
	 */
	public String deleteFriend(String name);
	/**
	 * ͬ����Ӻ���
	 * @return name ����ǳ�
	 */
	public String agreeFriend(String name);
	
}
