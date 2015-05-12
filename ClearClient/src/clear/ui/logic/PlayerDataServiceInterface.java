package clear.ui.logic;

public interface PlayerDataServiceInterface {
	/**
	 * ��������
	 * @param roomName ��������������������ȡ�ģ������������ظ���
	 * @param roomMaster ���������������������ǳƣ��ݶ�һ����Ҳ���ͬʱӵ���������䣩
	 */
	public boolean createRoom(String roomName , String roomMaster);
	/**
	 * ע������
	 * @param roomName ��������������������ȡ�ģ�
	 * @param roomMaster ���������������������ǳƣ�
	 * ����ֻ���ɷ�������
	 */
	public boolean deleteRoom(String roomName, String roomMaster);
	/**
	 * ������ѣ��ݶ�ֻ��������ѣ�İ���˲��ܱ����룩
	 * @param roomName ��������������������ȡ�ģ�
	 * @param roomMaster ���������������������ǳƣ�
	 * @param friendName ��������������ĺ����ǳƣ�
	 * ����ֻ���ɷ�������
	 */
	public void inviteFriend(String roomName , String roomMaster , String friendName) ;
	/**
	 * ͬ����루������ĺ���ͬ�����÷��䣩
	 * @param roomName ��������������������ȡ�ģ�
	 * @param friendName ��������������ĺ����ǳƣ�
	 * @param roomMaster ���������������������ǳƣ�
	 * ����ֻ���ɷ�������
	 */
	public void joinInRoom(String roomName , String friendName , String roomMaster ) ;
	/**
	 * ������루û������ĺ��ѻ�İ����Ҳ����������뷿�䣩
	 * @param roomName ������
	 * @param roomMaster ���������������������ǳƣ�
	 * @param playerName �������������뷿�������ǳƣ�
	 * ����ֻ���ɷ�������
	 */
	public void applyToJoin(String roomName , String playerName , String roomMaster );
	/**
	 * ͬ����루û������ĺ��ѻ�İ����Ҳ����������뷿�䣩
	 * @param roomName ������
	 * @param roomMaster ���������������������ǳƣ�
	 * @param playerName �������������뷿�������ǳƣ�
	 * ����ֻ���ɷ�������
	 */
	public void agreeToJoin(String roomName , String playerName , String roomMaster );
}
