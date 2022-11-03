package ru.practicum.explore.user.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 32, nullable=false)
    private String name;
    @Column(length = 32, nullable=false)
    private String email;
}
