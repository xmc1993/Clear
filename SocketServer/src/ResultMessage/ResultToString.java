package ResultMessage;

public class ResultToString {
	
	ResultMessage result ;
	
	public ResultToString(ResultMessage result){
		this.result = result ;
	}

	public String transfer(){
		String message = null;
		if(result == ResultMessage.OverTime){
			message = "���ӳ�ʱ";
		}else if(result == ResultMessage.Success){
			message = "�ɹ�";
		}else if(result == ResultMessage.DataUnmatch){
			message = "���ݲ�ƥ��";
		}else if(result == ResultMessage.DataNotExist){
			message = "���ݲ�����";
		}else if(result == ResultMessage.DataFormatError){
			message = "���ݸ�ʽ����";
		}else if(result == ResultMessage.OperationFail){
			message = "����ʧ��";
		}else if(result == ResultMessage.DataExist){
			message = "�����Ѵ���";
		}
		return message ;
	}
	
}
