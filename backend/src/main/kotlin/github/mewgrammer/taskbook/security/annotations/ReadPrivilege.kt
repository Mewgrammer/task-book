package github.mewgrammer.taskbook.security.annotations

import org.springframework.security.access.prepost.PreAuthorize

import java.lang.annotation.Inherited


@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
@Retention(value = AnnotationRetention.RUNTIME)
@Inherited
@PreAuthorize("hasRole(T(github.mewgrammer.taskbook.security.model.Privilege).READ)")
annotation class ReadPrivilege
