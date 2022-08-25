package mk.ukim.finki.ejajdaucime.model.exception

class UsernameAlreadyExistsException(username: String?) :
    RuntimeException(String.format("User with username: %s already exists", username))
