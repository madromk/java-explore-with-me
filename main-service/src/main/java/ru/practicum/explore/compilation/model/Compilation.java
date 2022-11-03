package ru.practicum.explore.compilation.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "compilations")
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean pinned;
    @Column(length = 64)
    private String title;
}
