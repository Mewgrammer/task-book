package github.mewgrammer.taskbook.persistence.audit;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityAuditAware implements AuditorAware<String> {

    @NotNull
    @Override
    public Optional<String> getCurrentAuditor() {
        var currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.ofNullable(currentUser.getUsername());
    }
}
