package github.mewgrammer.taskbook.security.annotations

import github.mewgrammer.taskbook.security.model.Role

@Target(AnnotationTarget.FUNCTION)
@Retention(value = AnnotationRetention.RUNTIME)
annotation class HasRole(val role: Role)
