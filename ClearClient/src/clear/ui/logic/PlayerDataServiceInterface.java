package clear.ui.logic;

public interface PlayerDataServiceInterface {
	/**
	 * 创建房间
	 * @param roomName 房间名（创建房间的玩家取的，房间名允许重复）
	 * @param roomMaster 房主名（创建房间的玩家昵称，暂定一个玩家不能同时拥有两个房间）
	 */
	public boolean createRoom(String roomName , String roomMaster);
	/**
	 * 注销房间
	 * @param roomName 房间名（创建房间的玩家取的）
	 * @param roomMaster 房主名（创建房间的玩家昵称）
	 * 房间只能由房主销毁
	 */
	public boolean deleteRoom(String roomName, String roomMaster);
	/**
	 * 邀请好友（暂定只能邀请好友，陌生人不能被邀请）
	 * @param roomName 房间名（创建房间的玩家取的）
	 * @param roomMaster 房主名（创建房间的玩家昵称）
	 * @param friendName 好友名（被邀请的好友昵称）
	 * 房间只能由房主销毁
	 */
	public void inviteFriend(String roomName , String roomMaster , String friendName) ;
	/**
	 * 同意加入（被邀请的好友同意加入该房间）
	 * @param roomName 房间名（创建房间的玩家取的）
	 * @param friendName 好友名（被邀请的好友昵称）
	 * @param roomMaster 房主名（创建房间的玩家昵称）
	 * 房间只能由房主销毁
	 */
	public void joinInRoom(String roomName , String friendName , String roomMaster ) ;
	/**
	 * 申请加入（没被邀请的好友或陌生人也可以申请加入房间）
	 * @param roomName 房间名
	 * @param roomMaster 房主名（创建房间的玩家昵称）
	 * @param playerName 玩家名（申请加入房间的玩家昵称）
	 * 房间只能由房主销毁
	 */
	public void applyToJoin(String roomName , String playerName , String roomMaster );
	/**
	 * 同意加入（没被邀请的好友或陌生人也可以申请加入房间）
	 * @param roomName 房间名
	 * @param roomMaster 房主名（创建房间的玩家昵称）
	 * @param playerName 玩家名（申请加入房间的玩家昵称）
	 * 房间只能由房主销毁
	 */
	public void agreeToJoin(String roomName , String playerName , String roomMaster );
}
