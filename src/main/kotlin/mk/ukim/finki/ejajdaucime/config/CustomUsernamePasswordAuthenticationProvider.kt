package mk.ukim.finki.ejajdaucime.config

import mk.ukim.finki.ejajdaucime.service.impl.UserServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Bean
fun getEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
}

@Component
class CustomUsernamePasswordAuthenticationProvider(userService: UserServiceImpl) :
    AuthenticationProvider {
    private val userService: UserServiceImpl
    private val passwordEncoder: PasswordEncoder

    init {
        this.userService = userService
        this.passwordEncoder = getEncoder()
    }

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials.toString()
        if (username.isBlank() || password.isBlank()) {
            throw BadCredentialsException("Invalid Credentials")
        }
        val userDetails: UserDetails = userService.loadUserByUsername(username)
        if (!passwordEncoder.matches(password, userDetails.password)) {
            throw BadCredentialsException("Password is incorrect!")
        }
        return UsernamePasswordAuthenticationToken(userDetails, userDetails.password, userDetails.authorities)
    }

    override fun supports(aClass: Class<*>): Boolean {
        return aClass == UsernamePasswordAuthenticationToken::class.java
    }
}

