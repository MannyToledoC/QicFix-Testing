/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Utility;

/**
 *
 * @author Juan
 */
public class Client extends User {

    private Integer id;

    public Client() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean create() {

        boolean resp = false;
        int parameterIndex = 0;

        String sql = "INSERT INTO client (email) VALUES (?)";

        this.setUserTypeId(1);

        if (createUser()) {

            Database db = Database.getInstance();
            try {
                db.Connect();
                db.setPreparedStatement(sql);
                db.getPreparedStatement().setString(++parameterIndex, this.getEmail());
                db.ExecuteNonQuery();
                resp = true;
            } catch (SQLException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (db != null) {
                    try {
                        db.Close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return resp;
    }

    public List<Client> selectAll() {
        List<Client> list = new ArrayList<Client>();
        String sql;
        ResultSet rs = null;

        sql = "SELECT c.id, c.email, u.user_type_id, u.fname, u.lname, u.street_address, u.city, u.state, u.zipcode, u.dob, u.blocked FROM user u, client c"
                + " WHERE c.email=u.email";

        //Database db = new Database();
        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            while (rs.next()) {
                Client obj = readResult(rs);
                list.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
            try {
                db.Close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }

        return list;
    }

    public void selectIdByEmail() {
        String sql;
        ResultSet rs = null;

        sql = "SELECT id FROM client WHERE email='" + this.getEmail() + "'";

        //Database db = new Database();
        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            if (rs.next()) {
                this.setId(rs.getString("id") != null ? rs.getInt("id") : null);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
            try {
                db.Close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }

    }

    public Client selectById(Integer clientId) {
        //List<Client> list = new ArrayList<Client>();
        String sql;
        ResultSet rs = null;
        Client obj = null;

        sql = "SELECT c.id, c.email, u.user_type_id, u.fname, u.lname, u.street_address, u.city, u.state, u.zipcode, u.dob, u.blocked FROM user u, client c"
                + " WHERE c.email=u.email AND c.id=" + clientId;

        //Database db = new Database();
        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            if (rs.next()) {
                obj = readResult(rs);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
            try {
                db.Close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }

        return obj;
    }

    private Client readResult(ResultSet rs) throws SQLException {
        Client obj = new Client();
        obj.setId(rs.getString("id") != null ? rs.getInt("id") : null);
        obj.setEmail(rs.getString("email"));
        obj.setFname(rs.getString("fname"));
        obj.setLname(rs.getString("lname"));
        obj.setStreetAddress(rs.getString("street_address"));
        obj.setCity(rs.getString("city"));
        obj.setState(rs.getString("state"));
        obj.setZipcode(rs.getString("zipcode"));
        obj.setDob(rs.getString("dob") != null ? rs.getDate("dob") : null);
        obj.setBlocked(rs.getString("blocked"));
        return obj;
    }

    public static String toJson(List<Client> list) {
        Gson gson = new GsonBuilder().setDateFormat(Utility.DATE_FORMAT_STRING_SHORT).create();
        String gsonString = gson.toJson(list, new TypeToken<List<Client>>() {
        }.getType());
        return gsonString;
    }

    public static List<Client> fromJson(String json) throws JsonSyntaxException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new Utility.JsonDateDeserializer()).create();
        List<Client> list = gson.fromJson(json, new TypeToken<List<Client>>() {
        }.getType());
        return list;
    }

    public List<Client> selectClientByEmail(String email) {
        List<Client> list = new ArrayList<Client>();
//        String sql;
//        ResultSet rs = null;
//        Client obj = null;
//
//        sql = "SELECT c.id, c.email, u.user_type_id, u.fname, u.lname, u.street_address, u.city, u.state, u.zipcode, u.dob, u.blocked FROM user u, client c"
//                + " WHERE c.email=u.email AND u.email='" + email + "'";
//
//        //Database db = new Database();
//        Database db = Database.getInstance();
//        try {
//            db.Connect();
//            db.setStatement();
//            rs = db.ExecuteQuery(sql);
//            while (rs.next()) {
//                obj = readResult(rs);
//                list.add(obj);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.toString());
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException ex) {
//                    System.out.println(ex.toString());
//                }
//            }
//            try {
//                db.Close();
//            } catch (SQLException ex) {
//                System.out.println(ex.toString());
//            }
//        }
        List<Client> listOfClients = new ArrayList<Client>();
        
        listOfClients.add(new Client());
        listOfClients.add(new Client());
        listOfClients.add(new Client());
    	
        listOfClients.get(0).setId(1);
        listOfClients.get(0).setEmail("123@gmail.com");
        listOfClients.get(0).setPassword("password123");
        listOfClients.get(0).setFname("Derrick");
        listOfClients.get(0).setLname("Mcdermott");
        listOfClients.get(0).setUserTypeId(1);
    	listOfClients.get(0).setStreetAddress("245 Fairway Street");
    	listOfClients.get(0).setCity("Apple Valley");
    	listOfClients.get(0).setState("CA");
    	listOfClients.get(0).setZipcode("92307");
    	listOfClients.get(0).setDob(new Date(456789));
    	listOfClients.get(0).setPhone("5552226666");
    	
    	listOfClients.get(0).setId(2);
    	listOfClients.get(1).setEmail("AUser@gmail.com");
    	listOfClients.get(1).setPassword("123");
    	listOfClients.get(1).setFname("Hawa");
    	listOfClients.get(1).setLname("Hutchinson");
    	listOfClients.get(1).setUserTypeId(2);
    	listOfClients.get(1).setStreetAddress("85 Mill Pond St.");
    	listOfClients.get(1).setCity("Eden Prairie");
    	listOfClients.get(1).setState("MN");
    	listOfClients.get(1).setZipcode("55347");
    	listOfClients.get(1).setDob(new Date(456753));
    	listOfClients.get(1).setPhone("4442225555");
    	
    	listOfClients.get(0).setId(3);
    	listOfClients.get(2).setEmail("JohnDoe@gmail.com");
    	listOfClients.get(2).setPassword("12345678");
    	listOfClients.get(2).setFname("John");
    	listOfClients.get(2).setLname("Doe");
    	listOfClients.get(2).setUserTypeId(3);
    	listOfClients.get(2).setStreetAddress("7826 Mill Ave.");
    	listOfClients.get(2).setCity("Lawrence Township");
    	listOfClients.get(2).setState("NJ");
    	listOfClients.get(2).setZipcode("08648");
    	listOfClients.get(2).setDob(new Date(241262));
    	listOfClients.get(2).setPhone("3332224444");
    	
    	for(Client client: listOfClients) {
    		if(client.getEmail().equals(email)) {
    			list.add(client);
    		}
    	}
        return list;
    }
    
    //Inherits 
    public boolean updateClient() {
        return update();
    }

}
