package fr.rmorel.moncompagnonbudgetapi.app.test

import javax.persistence.*


@Entity
@Table(name = "testronan")
class TestEntity (
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column var title: String = "",
    @Column var description: String = "",
    @Column var published: Boolean = false
)  {
    override fun toString() = "Tutorial [id=$id, title=$title, desc=$description, published=$published]"
}