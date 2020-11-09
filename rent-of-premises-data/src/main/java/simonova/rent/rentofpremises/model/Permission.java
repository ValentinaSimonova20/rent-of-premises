package simonova.rent.rentofpremises.model;

/**
 * Класс, в котором определены доступные действия для разных пользователей
 */
public enum Permission {

    DEVELOPERS_READ("developers:read"),
    DEVELOPERS_WRITE("developers:write"),
    DEVELOPERS_ADD_USERS("developers:addUsers");

    private final String permission;

    public String getPermission() {
        return permission;
    }

    Permission(String permission) {
        this.permission = permission;
    }
}
