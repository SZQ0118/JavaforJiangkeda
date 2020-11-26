package neusoft.springbootsell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品详情
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {
    @Id
    @GeneratedValue
    /**商品Id*/
    private String ProductId;

    /**商品名字Id*/
    private String productName;
    /**商品单价*/
    private BigDecimal productPrice;
    /**库存*/
    private Integer productStock;
    /**描述*/
    private String productDecription;
    /**小图*/
    private String productIcon;
    /**状态 0正常 1下架 */
    private Integer productStatus = 0;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

}
