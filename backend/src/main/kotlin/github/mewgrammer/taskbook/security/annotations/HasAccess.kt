package github.mewgrammer.taskbook.security.annotations

import github.mewgrammer.taskbook.security.model.Privilege
import github.mewgrammer.taskbook.security.model.Role
import org.springframework.security.access.prepost.PreAuthorize

import java.lang.annotation.Inherited

@Target(AnnotationTarget.FUNCTION)
@Retention(value = AnnotationRetention.RUNTIME)
annotation class HasAccess(val role: Role, val privilege: Privilege)
