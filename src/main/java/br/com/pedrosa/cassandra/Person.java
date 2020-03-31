package br.com.pedrosa.springbootcassandra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @PrimaryKey
    private String id;
    private String name;
    private Integer age;
}
