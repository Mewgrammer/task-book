package github.mewgrammer.taskbook.security.helpers

import github.mewgrammer.taskbook.security.model.Privilege
import org.springframework.security.core.userdetails.User
import github.mewgrammer.taskbook.security.model.Role

class PermissionHelper {
    companion object {
        @JvmStatic
        fun userIsAdmin(user: User) =
            user.authorities.any { a -> a.authority.equals(Role.ADMIN.name) }

        @JvmStatic
        fun hasRole(user: User, role: Role) =
            user.authorities.any { a -> a.authority.equals(role.name) || a.authority.equals(Role.ADMIN.name) }

        @JvmStatic
        fun hasPrivilege(user: User, privilege: Privilege) =
            user.authorities.any { a -> a.authority.equals(privilege.name) || a.authority.equals(Role.ADMIN.name) }

    }
}