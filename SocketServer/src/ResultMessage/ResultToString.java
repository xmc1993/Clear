package ResultMessage;

public class ResultToString {
	
	ResultMessage result ;
	
	public ResultToString(ResultMessage result){
		this.result = result ;
	}

	public String transfer(){
		String message = null;
		if(result == ResultMessage.OverTime){
			message = "连接超时";
		}else if(result == ResultMessage.Success){
			message = "成功";
		}else if(result == ResultMessage.DataUnmatch){
			message = "数据不匹配";
		}else if(result == ResultMessage.DataNotExist){
			message = "数据不存在";
		}else if(result == ResultMessage.DataFormatError){
			message = "数据格式错误";
		}else if(result == ResultMessage.OperationFail){
			message = "操作失败";
		}else if(result == ResultMessage.DataExist){
			message = "数据已存在";
		}
		return message ;
	}
	
}
