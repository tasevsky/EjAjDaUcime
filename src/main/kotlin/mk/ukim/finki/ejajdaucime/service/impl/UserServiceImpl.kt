package mk.ukim.finki.ejajdaucime.service.impl

import mk.ukim.finki.ejajdaucime.config.getEncoder
import mk.ukim.finki.ejajdaucime.model.Role
import mk.ukim.finki.ejajdaucime.model.User
import mk.ukim.finki.ejajdaucime.model.exception.InvalidUsernameOrPasswordException
import mk.ukim.finki.ejajdaucime.model.exception.PasswordsDoNotMatchException
import mk.ukim.finki.ejajdaucime.model.exception.UsernameAlreadyExistsException
import mk.ukim.finki.ejajdaucime.repository.UserRepository
import mk.ukim.finki.ejajdaucime.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserServiceImpl(userRepository: UserRepository) :
    UserService {
    private val userRepository: UserRepository
    private val passwordEncoder: PasswordEncoder

    init {
        this.userRepository = userRepository
        this.passwordEncoder = getEncoder()
    }

    override fun register(
        username: String?,
        email: String,
        password: String?,
        repeatPassword: String?,
        name: String?,
        surname: String?,
        role: Role
    ): User? {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) throw InvalidUsernameOrPasswordException()
        if (password != repeatPassword) throw PasswordsDoNotMatchException()
        if (userRepository.findByUsername(username)?.isPresent() == true) throw UsernameAlreadyExistsException(username)
        val user = User(0, username, email, passwordEncoder.encode(password), role)
        return userRepository.save(user)
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsernameNew(s: String): User? {
        return userRepository.findByUsername(s)?.orElseThrow { UsernameNotFoundException(s) }
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        TODO("Not yet implemented")
    }
}
