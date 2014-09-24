/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao.query;

/**
 *
 * @author User
 * @param <T>
 */
public interface TypedUpdateQuery<T> {    

    public int update(Criteria beans, Criteria criteria) throws QueryExecutionException; 
    
    
}
