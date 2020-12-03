package neusoft.springbootsell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
/**
 * 卖家用户详细信息
 */
public class SellerInfo {
    @Id
    //卖家用户账号
    private String Id;

    //卖家用户名
    private String username;

    //卖家用户密码
    private String password;

    //卖家用户Openid
    private String openId;

    //用户创建时间
    private Date createTime;

    //用户更新时间
    private Date updateTime;
}
