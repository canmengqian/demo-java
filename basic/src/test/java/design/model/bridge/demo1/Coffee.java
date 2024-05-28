package design.model.bridge.demo1;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Coffee {
    long capacity;

    String additives;

    String name;


}
