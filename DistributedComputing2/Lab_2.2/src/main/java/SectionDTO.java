import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SectionDTO {
    private long id;
    private String name;
    private List<ProductDTO> products = new ArrayList<>();
}
