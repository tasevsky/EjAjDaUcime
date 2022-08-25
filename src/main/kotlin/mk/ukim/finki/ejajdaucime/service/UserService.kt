package mk.ukim.finki.ejajdaucime.service

import mk.ukim.finki.ejajdaucime.model.Role
import mk.ukim.finki.ejajdaucime.model.User
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService : UserDetailsService {
    fun register(
        username: String?,
        email: String,
        password: String?,
        repeatPassword: String?,
        name: String?,
        surname: String?,
        role: Role
    ): User?

    fun loadUserByUsernameNew(s: String): User?
}

