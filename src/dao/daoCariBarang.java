/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import controller.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.*;
/**
 *
 * @author User
 */
public class daoCariBarang {
    Connection connection;
    final String select = "SELECT * FROM barang ORDER BY nama ASC;";
    final String selectData = "SELECT * FROM barang WHERE nama LIKE %?%;";
    final String cari = "SELECT * FROM barang WHERE nama LIKE ?;";
    
    public daoCariBarang(){
        connection = koneksi.connection();
    }
    
    public void tampil(Barang brg){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(select);
            statement.setString(1, brg.getKode());
            statement.executeUpdate();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public List<Barang> getData(){
        List<Barang> listBrg = null;
        try {
            listBrg = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()){
                Barang brg = new Barang();
                brg.setKode(rs.getString("kode"));
                brg.setNama(rs.getString("nama"));
                brg.setJumlah(rs.getInt("jumlah"));
                brg.setHarga(rs.getInt("harga"));
                brg.setMerek(rs.getString("merek"));
                listBrg.add(brg);
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return listBrg;
    }
    
    public List<Barang> getCariData(String NamaBrg){
        PreparedStatement statement = null;
        List<Barang> listBrg = null;
        try {
            listBrg = new ArrayList<>();
//            Statement st = connection.createStatement();
//            ResultSet rs = st.executeQuery(selectData);
            statement = connection.prepareStatement(cari);
            statement.setString(1, "%" + NamaBrg + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Barang brg = new Barang();
                brg.setKode(rs.getString("kode"));
                brg.setNama(rs.getString("nama"));
                brg.setJumlah(rs.getInt("jumlah"));
                brg.setHarga(rs.getInt("harga"));
                brg.setMerek(rs.getString("merek"));
                listBrg.add(brg);
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return listBrg;
    }
    
    
    
    public int cekNama(String nama){
        PreparedStatement statement = null;
        int ketemu = 0;
        try {
            statement = connection.prepareStatement(selectData);
            statement.setString(1, nama);
            ResultSet rs = statement.executeQuery(cari);
            while(rs.next()) {
                ketemu++;
            }
        }catch (SQLException ex){
            
        }
        return ketemu;
    }
}
