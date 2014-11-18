package by.epam.project.manager;

import by.epam.project.dao.ClientType;

/**
 *
 * @author User
 */
public abstract class ClientTypeManager {
    public static ClientType clientTypeOf(String role) {
        if (role == null) {
            return ClientType.GUEST;
        }

        try {
            return ClientType.valueOf(role);
        } catch (IllegalArgumentException ex) {
            return ClientType.GUEST;
        }
    }
}
