package com.example.ecommercemobileapp2hand.Controllers;

import com.example.ecommercemobileapp2hand.Models.UserAddress;
import com.example.ecommercemobileapp2hand.Models.config.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserAddressHandler {
    private static DBConnect dbConnect = new DBConnect();
    private static Connection conn;

    public static String getAddress(int id)
    {
        try
        {
            conn = dbConnect.connectionClass();
            String sql = "SELECT USER_ADDRESS_STREET, USER_ADDRESS_CITY, USER_ADDRESS_STATE, USER_ADDRESS_ZIPCODE, user_account.phone_number FROM USER_ADDRESS JOIN USER_ORDER ON USER_ORDER.user_address_id = user_address.user_address_id JOIN USER_ACCOUNT ON USER_ORDER.user_id = user_account.user_id WHERE user_order.user_order_id = " + id + "";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next())
            {
                String s = rs.getString(1) + " " + rs.getString(2) + ", " + rs.getString(3) + " " + rs.getString(4) + "\n" + rs.getString(5);
                rs.close();
                st.close();
                conn.close();
                return s;
            }
            else
            {
                rs.close();
                st.close();
                conn.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<UserAddress> getListAdressByUserId(String userId) {
        ArrayList<UserAddress> list = new ArrayList<>();
        Connection conn = dbConnect.connectionClass();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        if (conn != null) {
            try {
                String sql = "SELECT user_address_id, user_address_street, user_address_city, user_address_state, user_address_zipcode, user_address_phone FROM user_address WHERE user_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, userId);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    UserAddress address = new UserAddress();
                    address.setUser_address_id(rs.getInt("user_address_id"));
                    address.setUser_address_street(rs.getString("user_address_street"));
                    address.setUser_address_city(rs.getString("user_address_city"));
                    address.setUser_address_state(rs.getString("user_address_state"));
                    address.setUser_address_zipcode(rs.getString("user_address_zipcode"));
                    address.setUser_address_phone(rs.getString("user_address_phone"));
                    list.add(address);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
    public static boolean insertAddress(String UserID,String street,String city,String state, String zipcode,String phoneNumber){
        conn = dbConnect.connectionClass();
        try{
            String sql = "Insert into user_address values (?,?,?,?,?,?)";
            if(conn!=null){
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,UserID);
                preparedStatement.setString(2,street);
                preparedStatement.setString(3,city);
                preparedStatement.setString(4,state);
                preparedStatement.setString(5,zipcode);
                preparedStatement.setString(6,phoneNumber);
                int rs = preparedStatement.executeUpdate();
                return rs > 0;
            }


        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());

        }finally {
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean updateAddressById(int addressId, String street, String city, String state, String zip, String phone) {
        Connection conn = dbConnect.connectionClass();
        PreparedStatement pstmt = null;

        if (conn != null) {
            try {
                String sql = "UPDATE user_address SET user_address_street = ?, user_address_city = ?, user_address_state = ?, user_address_zipcode = ?, user_address_phone = ? WHERE user_address_id = ?";
                pstmt = conn.prepareStatement(sql);
                System.out.println(street);
                System.out.println(city);
                System.out.println(state);
                pstmt.setString(1, street);
                pstmt.setString(2, city);
                pstmt.setString(3, state);
                pstmt.setString(4, zip);
                pstmt.setString(5, phone);
                pstmt.setInt(6,addressId);

                int rowsUpdated = pstmt.executeUpdate();
                return rowsUpdated > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    public static boolean deleteAddressById(int addressId) {
        Connection conn = dbConnect.connectionClass();
        PreparedStatement pstmt = null;

        if (conn != null) {
            try {
                String sql = "DELETE FROM user_address WHERE user_address_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, addressId);

                int rowsDeleted = pstmt.executeUpdate();
                return rowsDeleted > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}
