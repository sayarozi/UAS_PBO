/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

//import dao.daoCariBarang;
import dao.daoCariBarang;
import java.util.List;
import javax.swing.JOptionPane;
import model.*;
//import view.FormBarang;
import view.FormCariBarang;
/**
 *
 * @author User
 */
public class controllerCariBarang {
    FormCariBarang frame;
    List<Barang> listBrg;
    List<Barang> listCariBrg;
    daoCariBarang daoCariBrg = new daoCariBarang();
   // Barang brg = new Barang();
    
    public controllerCariBarang(FormCariBarang frame){
        this.frame = frame;
        listBrg = daoCariBrg.getData();
        listCariBrg = daoCariBrg.getCariData(frame.getTxtNamaBrg().getText());
    }
    
    public void tampil_tabel(){
        TabelModelCariBarang tabelBrg = new TabelModelCariBarang(listBrg);
        frame.getTblBarang().setModel(tabelBrg);
    }
    
    public void tampilFilter_tabel(){
        TabelModelCariBarang tabelBrg = new TabelModelCariBarang(listCariBrg);
        frame.getTblBarang().setModel(tabelBrg);
    }
    
    public void cekNamaBrg() {
        if (frame.getTxtNamaBrg().getText().equals("")) {
            JOptionPane.showMessageDialog(frame, "Nama Barang belum diisi");
        } else {
            daoCariBrg.getCariData(frame.getTxtNamaBrg().getText());
           // daoCariBrg.cekNama(frame.getTxtNamaBrg().getText());
            tampilFilter_tabel();
            //int row = daoCariBrg.cekNama(frame.getTxtNamaBrg().getText());
           // JOptionPane.showMessageDialog(frame, "berhasil");
            //daoCariBrg.getCariData();
        }
    }
    
    public void keluar(){
        frame.dispose();
    }
}
