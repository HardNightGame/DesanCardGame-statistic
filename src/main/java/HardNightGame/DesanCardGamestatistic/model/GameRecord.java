package HardNightGame.DesanCardGamestatistic.model;

import lombok.*;


import javax.naming.Name;
import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class GameRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private String Name;
    private Integer GameTime;
}
