package neusoft.springbootsell.form;

import lombok.Data;

@Data
public class OrderForm {

    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;
}
