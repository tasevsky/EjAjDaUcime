package mk.ukim.finki.ejajdaucime.service.impl

import mk.ukim.finki.ejajdaucime.model.User
import mk.ukim.finki.ejajdaucime.model.exception.InvalidUserCredentialsException
import mk.ukim.finki.ejajdaucime.model.exception.InvalidUsernameOrPasswordException
import mk.ukim.finki.ejajdaucime.repository.UserRepository
import mk.ukim.finki.ejajdaucime.service.AuthService
import org.springframework.stereotype.Service


@Service
class AuthServiceImpl(private val userRepository: UserRepository) : AuthService {
    override fun login(username: String?, password: String?): User? {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw InvalidUsernameOrPasswordException()
        }
        return userRepository.findByUsernameAndPassword(
            username,
            password
        )!!.orElseThrow { InvalidUserCredentialsException() }
    }
}
