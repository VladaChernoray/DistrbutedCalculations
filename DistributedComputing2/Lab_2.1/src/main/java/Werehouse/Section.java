package Werehouse;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class Section {
  @NonNull
  private String name;
  private int type;
}
