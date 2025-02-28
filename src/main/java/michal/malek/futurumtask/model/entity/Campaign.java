package michal.malek.futurumtask.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class Campaign {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String campaignName;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Keyword> keywords;
    private int bidAmount;
    private int campaignFound;
    private boolean status;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Town town;
    private int radius;
}

