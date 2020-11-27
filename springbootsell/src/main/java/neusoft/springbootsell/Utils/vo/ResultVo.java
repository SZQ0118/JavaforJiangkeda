package neusoft.springbootsell.Utils.vo;

import lombok.Data;

@Data
public class ResultVo<T> {
    //错误码
    private Integer code;
    //提示信息
    private String msg;
    //返回的具体内容
    private T date;
}
