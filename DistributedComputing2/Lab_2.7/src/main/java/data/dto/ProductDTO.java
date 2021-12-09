package data.dto;
import java.io.Serializable;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ProductDTO implements Serializable {
    private Long id;
    private Long productId;
    private String name;
    private Long price;
}
