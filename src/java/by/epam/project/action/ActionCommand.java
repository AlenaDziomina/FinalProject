/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import by.epam.project.controller.SessionRequestContent;

/**
 *
 * @author User
 */
public interface ActionCommand {
    String execute(SessionRequestContent request);
}