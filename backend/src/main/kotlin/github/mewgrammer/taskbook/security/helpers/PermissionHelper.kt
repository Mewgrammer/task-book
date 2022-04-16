package github.mewgrammer.taskbook.security.helpers

import org.springframework.security.core.userdetails.User
import github.mewgrammer.taskbook.security.model.Role

class PermissionHelper {
    companion object {
        @JvmStatic
        fun userIsAdmin(user: User) =
            user.authorities.any { a -> a.authority.equals(Role.ADMIN.name) }
    }
}