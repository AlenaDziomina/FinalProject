package by.epam.project.action;

import by.epam.project.exception.ServletLogicException;

/**
 * Interface {@code ActionCommand} should be implemented by any class whose 
 * instances represent a custom command.
 * @author Helena.Grouk
 */
@FunctionalInterface
public interface ActionCommand {
    /**
     * Executes the command.
     * @param request parameters and attributes of the request and the session
     * @return page name with relative path to display the results of command
     * @throws by.epam.project.exception.ServletLogicException if the command 
     * can not be executed
    */
    String execute(SessionRequestContent request) throws ServletLogicException;
}
