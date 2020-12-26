package pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    private String PersonName;
    private String PersonPhone;
    private String PersonCall;
    private String PersonAddress;
    private Integer PGId;
    private String PersonSex;
}
