/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.dao;

/**
 *
 * @author User
 */
public class MysqlUserDao implements UserDao {
   
    private MysqlUserDao(){}
    
    static UserDao getInstance() {
       return new MysqlUserDao();
    }
    
}
