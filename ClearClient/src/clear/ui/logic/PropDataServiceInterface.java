package clear.ui.logic;

import clear.bean.*;

public interface PropDataServiceInterface {
	/**
	 * ��ʼ����ҵĵ���ģ��
	 * @param player ���
	 */
	public void init(Player player);
	/**
	 * �������
	 * @param prop ����
	 * @return �Ƿ���ɹ�����Ǯ���㣩
	 */
	public boolean buyProp(Prop prop);
}
