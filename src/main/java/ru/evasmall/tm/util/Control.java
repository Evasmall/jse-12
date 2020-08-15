package ru.evasmall.tm.util;

import ru.evasmall.tm.controller.AbstractController;
import ru.evasmall.tm.controller.SystemController;

public class Control extends AbstractController {

    private final SystemController systemController = new SystemController();

    //Функция возврата значения Long
    public Long scannerIdIsLong () {
        final String idStr = scanner.nextLine();
        final Long id;
        try {
            Long.parseLong(idStr);
            return id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            systemController.displayNotInt();
            return null;
        }
    }

    //Функция возврата значения Integer
    public Integer scannerIndexIsInteger () {
        final String idStr = scanner.nextLine();
        final Integer index;
        try {
            Integer.parseInt(idStr);
            return index = Integer.parseInt(idStr) - 1;
        } catch (NumberFormatException e) {
            systemController.displayNotInt();
            return null;
        }
    }

}
