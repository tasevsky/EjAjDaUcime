package mk.ukim.finki.ejajdaucime.model


import org.springframework.security.core.GrantedAuthority


enum class Role : GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    override fun getAuthority(): String {
        return name
    }
}
