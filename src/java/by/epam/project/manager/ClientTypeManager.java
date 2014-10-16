/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.manager;

import by.epam.project.entity.ClientType;

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
