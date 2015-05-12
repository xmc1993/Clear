package ResultMessage;

public enum ResultMessage {
	//错误类型：
		DataNotExist,//数据不存在错误
		
		DataUnmatch,//数据不匹配错误
		DataFormatError,//数据格式错误
		OverTime,//连接超时
		
		DataExist,//数据已存在错误
		
		OperationFail,//操作错误
		
		//成功：
		Success,
}
