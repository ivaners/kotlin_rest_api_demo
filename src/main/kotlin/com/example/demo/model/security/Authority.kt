package com.example.demo.model.security

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "AUTHORITY")
class Authority {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
    @SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
    var id: Int? = null

    @Column(name = "NAME", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    var name: AuthorityName? = null

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    var users: List<User>? = null
}