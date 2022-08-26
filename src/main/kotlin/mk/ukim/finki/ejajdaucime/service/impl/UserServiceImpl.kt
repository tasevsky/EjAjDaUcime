package mk.ukim.finki.ejajdaucime.service.impl

import mk.ukim.finki.ejajdaucime.config.getEncoder
import mk.ukim.finki.ejajdaucime.model.Role
import mk.ukim.finki.ejajdaucime.model.User
import mk.ukim.finki.ejajdaucime.model.exception.InvalidUsernameOrPasswordException
import mk.ukim.finki.ejajdaucime.model.exception.PasswordsDoNotMatchException
import mk.ukim.finki.ejajdaucime.model.exception.UserNotExistingException
import mk.ukim.finki.ejajdaucime.model.exception.UsernameAlreadyExistsException
import mk.ukim.finki.ejajdaucime.repository.UserRepository
import mk.ukim.finki.ejajdaucime.security.UserDetailsServiceImpl
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
class UserServiceImpl(userRepository: UserRepository) :
    UserDetailsServiceImpl(userRepository) {
    private val passwordEncoder: PasswordEncoder = getEncoder()

    fun register(
        username: String?,
        email: String,
        password: String?,
        repeatedPassword: String?,
        role: Role
    ): User? {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) throw InvalidUsernameOrPasswordException()
        if (password != repeatedPassword) throw PasswordsDoNotMatchException()
        if (userRepository.findByUsername(username)?.isPresent == true) throw UsernameAlreadyExistsException(username)
        val user = User(0, username, email, passwordEncoder.encode(password), role)
        return userRepository.save(user)
    }


    @Transactional
    fun edit(
        id: Long,
        username: String,
        email: String,
        password: String,
        repeatedPassword: String?,
        role: Role
    ): User? {
        val user = userRepository.findById(id)
        if (password != repeatedPassword) throw PasswordsDoNotMatchException()
        return userRepository.save(User(user?.id ?: 0, username, email, passwordEncoder.encode(password), role))
    }
}
