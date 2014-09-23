/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query;

import java.util.List;

/**
 * Реализация паттерна QueryObject - класс-запрос, сохраняющий данные типа T в хранилище.
 * @param <T> тип сохраняемых данных
 */
public interface TypedSaveQuery<T> {    

    public void save(List<T> beans) throws QueryExecutionException; 
    
    
}
