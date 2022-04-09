package github.mewgrammer.taskbook.security.annotations

import org.springframework.security.access.prepost.PreAuthorize
import java.lang.annotation.Inherited

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
@Retention(value = AnnotationRetention.RUNTIME)
@Inherited
@PreAuthorize("hasAnyAuthority(T(github.mewgrammer.taskbook.security.model.Role).USER.name(), T(github.mewgrammer.taskbook.security.model.Role).ADMIN.name())")
annotation class UserAuthorization