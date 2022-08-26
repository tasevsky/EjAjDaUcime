package mk.ukim.finki.ejajdaucime.repository

import mk.ukim.finki.ejajdaucime.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface UserRepository : JpaRepository<User?, String?> {
    fun findByUsernameAndPassword(username: String?, password: String?): Optional<User?>?
    fun findByUsername(username: String?): Optional<User?>?
    fun findByUsernameIgnoreCase(username: String): User?
}
