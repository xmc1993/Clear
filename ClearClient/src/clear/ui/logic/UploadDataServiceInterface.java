package clear.ui.logic;

import clear.bean.*;

public interface UploadDataServiceInterface {
	/**
	 * ��ʼ����ҵ�UploadDataģ��
	 * @param player ���
	 */
	public void init(Player player);
	/**
	 * ������ҵ���߷�
	 * @param score �þַ��� 
	 * @return ���ظþַ����Ƿ�Ϊ��߷�
	 */
	public boolean updateHigestScore(int score);
	/**
	 * ����������������
	 * @param count �þ�������
	 * @return ���ظ��������Ƿ�Ϊ���
	 */
	public boolean updateHigestComboCount(int count);
	/**
	 * ��������ܾ���
	 */
	public void updateTotalCount();
	/**
	 * ����ƽ���÷�
	 * @param score �þַ���
	 */
	public void updateAverageScore(int score);
	/**
	 * ����ÿ�վ���
	 */
	public void updateDayPlayedCount();
	/**
	 * ����ÿ��ƽ���÷�
	 * @param score �þֵ÷�
	 */
	public void updateDayAverageScore(int score);
	/**
	 * ����ÿ�ֵ÷�
	 * @param socre �þֵ÷�
	 */
	public void updateScore(int socre);
	/**
	 * ���¾��飨�����Ļ�ҲҪ������Ӧ�ȼ���
	 * @param exp ���־���ֵ
	 */
	public void updateExperence(int exp);
	/**
	 * ������ҽ�Ǯֵ
	 * @param money ���ֽ�Ǯֵ
	 */
	public void updateGold(int money);
}
