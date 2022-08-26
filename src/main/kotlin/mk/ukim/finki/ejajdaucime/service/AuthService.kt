package mk.ukim.finki.ejajdaucime.service

import mk.ukim.finki.ejajdaucime.model.User

interface AuthService {
    fun login(username: String?, password: String?): User?
}
