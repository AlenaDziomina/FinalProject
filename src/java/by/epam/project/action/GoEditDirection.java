/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.ActionCommand.PARAM_NAME_COUNTRY_LIST;
import static by.epam.project.action.ActionCommand.PARAM_NAME_CURR_COUNTRY_TAGS;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.entity.Country;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class GoEditDirection implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        
        List<Country> countryList = (List<Country>) request.getSessionAttribute(PARAM_NAME_COUNTRY_LIST);
        if (countryList == null || countryList.isEmpty()){
            new GoShowCountry().execute(request);
            countryList = (List<Country>) request.getSessionAttribute(PARAM_NAME_COUNTRY_LIST);
        }
        
        List<Country> countryTagList = new ArrayList<>();
        countryTagList.add(countryList.get(0));
        countryTagList.add(countryList.get(2));
        //request.setSessionAttribute(PARAM_NAME_COUNTRY_TAGS_LIST, countryTagList);
        List<String> tags = new ArrayList<>();
        for (Country c: countryTagList){
            tags.add(c.getIdCountry().toString());
        }
        request.setAttribute(PARAM_NAME_CURR_COUNTRY_TAGS, tags.toArray());
        
        return null;
    }
    
}
