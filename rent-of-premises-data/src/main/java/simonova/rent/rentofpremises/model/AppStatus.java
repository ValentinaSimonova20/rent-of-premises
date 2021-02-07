package simonova.rent.rentofpremises.model;
/**
 * Перечисления статусов заявки на аренду
 * WAIT_FOR_CONSIDERATION - ожидает рассмотрения
 * IN_PROCESSING - в обработке
 * ACCEPTED - принята
 * REJECTED - отклонена*/

public enum  AppStatus {

    WAIT_FOR_CONSIDERATION ("Ожидает рассмотрения"), IN_PROCESSING("Рассматривается"),
    ACCEPTED("Принята"), REJECTED("Отклонена");

    private String name;

    AppStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
