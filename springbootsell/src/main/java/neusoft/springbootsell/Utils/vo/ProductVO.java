package neusoft.springbootsell.Utils.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import neusoft.springbootsell.dataobject.ProductInfo;

import java.util.List;

@Data
public class ProductVO {
    @JsonProperty("name")
   private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
