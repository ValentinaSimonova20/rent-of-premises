package simonova.rent.rentofpremises.model;
/**
 * Перечисления статусов заявки на аренду
 * WAIT_FOR_CONSIDERATION - ожидает рассмотрения
 * IN_PROCESSING - в обработке
 * ACCEPTED - принята
 * REJECTED - отклонена*/

public enum  AppStatus {

    WAIT_FOR_CONSIDERATION, IN_PROCESSING, ACCEPTED, REJECTED
}
