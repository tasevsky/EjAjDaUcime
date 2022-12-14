package mk.ukim.finki.ejajdaucime.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @NotBlank
    @Size(max = 20)
    val username: String,

    @NotBlank
    @Size(max = 50)
    val email: String,

    @NotBlank
    @Size(max = 120)
    val password: String,

    @Enumerated(value = EnumType.STRING)
    val role: Role

) {
    override fun toString(): String = "User(id=$id, username=$username, email=$email, role=$role)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (username != other.username) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (role != other.role) return false
        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + role.hashCode()
        return result
    }
}