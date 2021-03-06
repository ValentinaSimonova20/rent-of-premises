package simonova.rent.rentofpremises.model;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Класс возможных ролей пользователя
 */
public enum Role {

    USER(Set.of(Permission.DEVELOPERS_READ)),
    ADMIN(Set.of(Permission.DEVELOPERS_READ, Permission.DEVELOPERS_WRITE, Permission.DEVELOPERS_ADD_USERS)),
    MANAGER(Set.of(Permission.DEVELOPERS_READ, Permission.DEVELOPERS_WRITE)),
    NONE(Set.of(Permission.CLIENT));
    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
