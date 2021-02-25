package simonova.rent.rentofpremises.model;

/**
 * Класс, в котором определены доступные действия для разных пользователей
 */
public enum Permission {

    DEVELOPERS_READ("developers:read"),
    CLIENT("None"),
    DEVELOPERS_WRITE("developers:write"),
    DEVELOPERS_ADD_USERS("developers:addUsers");

    private final String name;

    public String getPermission() {
        return name;
    }

    Permission(String name) {
        this.name = name;
    }
}
