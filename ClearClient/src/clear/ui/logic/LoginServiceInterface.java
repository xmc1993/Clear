package clear.ui.logic;
/**
 * ��¼ģ��
 */
public interface LoginServiceInterface {
	/**
	 * ��֤�û���Ϣ
	 * @param name �û���
	 * @param password ����
	 * @return ���������֤��Ϣ
	 */
	public String check(String name,String password , int index);
}
