/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database_Package;

import java.sql.ResultSet;

/**
 *
 * @author AdemAybar
 */

/** Interface to DataBase **/
public interface DBConnectivityInterface {
    
    /** Establish Connection to mysql database **/
    public void connect();
    
    /** Disconnect program from mysql database**/
    public void closeConnection();
    
    //** Read values From mysql DataBase**/
    public ResultSet read(String sql);
    
    /** Write new values to my sql Database**/
    public void write(String sql);

}