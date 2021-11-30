package Werehouse;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class Products {
  @NonNull
  private Section section;
  @NonNull
  private String name;
  private int price;
}
