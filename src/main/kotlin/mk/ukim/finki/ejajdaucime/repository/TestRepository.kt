package mk.ukim.finki.ejajdaucime.repository

import mk.ukim.finki.ejajdaucime.model.Test
import mk.ukim.finki.ejajdaucime.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface TestRepository: JpaRepository<Test, Long> {

    fun findByName(name: String) : Optional<Test>
}