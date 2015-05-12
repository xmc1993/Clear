package clear.ui.logic;
/**
 * 登录模块
 */
public interface LoginServiceInterface {
	/**
	 * 验证用户信息
	 * @param name 用户名
	 * @param password 密码
	 * @return 返回身份验证信息
	 */
	public String check(String name,String password , int index);
}
