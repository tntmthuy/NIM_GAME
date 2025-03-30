/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import view.EasyGame;

/**
 *
 * @author user
 */
public class SaveLoad {
    EasyGame easyGame;

    public SaveLoad(EasyGame easyGame) {
        this.easyGame = easyGame;
    }
    
    public void save(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            
            DataStorage ds = new DataStorage();
            
            ds.label = easyGame.label;
            ds.lastLabelIndex = easyGame.lastLabelIndex;
            ds.isBtnMoveOneSelected = easyGame.isBtnMoveOneSelected;
            ds.isBtnMoveTwoSelected = easyGame.isBtnMoveTwoSelected;
            ds.currentPlayer = easyGame.currentPlayer;
            ds.info_LabelName= easyGame.info_Label.getName();
            
            //luu dataStorage object
            oos.writeObject(ds);
            
            System.out.println("Game saved successfully.");
        } catch (Exception e) {
            System.out.println("Save Exception!");
        }
    }
    
    public void load(){
        //doc DataStorage 
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
            
            // Clear existing labels
            easyGame.play_Panel.removeAll();
            
            DataStorage ds = (DataStorage)ois.readObject();
            easyGame.label = ds.label;
            easyGame.lastLabelIndex = ds.lastLabelIndex;
            easyGame.isBtnMoveOneSelected = ds.isBtnMoveOneSelected;
            easyGame.isBtnMoveTwoSelected = ds.isBtnMoveTwoSelected;
            easyGame.currentPlayer = ds.currentPlayer;
//            easyGame.info_Label = ds.info_Labe;
            
            // Tìm lại info_label bằng tên đã lưu
        for (Component comp : easyGame.getComponents()) {
            if (comp instanceof JLabel && comp.getName().equals(ds.info_LabelName)) {
                easyGame.info_Label = (JLabel) comp;
                break;
            }
        }
        
            // thêm các label được lưu vào panel
        for (int i = 1; i <= easyGame.lastLabelIndex; i++) {
            easyGame.play_Panel.add(easyGame.label[i]);
        }
        for(int i = easyGame.lastLabelIndex+1;i<=10; i++){
            easyGame.play_Panel.add(easyGame.label[i]); //thêm label bị ẩn vào
        }

        // Ẩn các label không sử dụng
        for (int i = easyGame.lastLabelIndex + 1; i <= 10; i++) {
            easyGame.label[i].setVisible(false);
        }

        // Cập nhật thông tin người chơi
        if(easyGame.mode == 1){
            if (easyGame.currentPlayer == 1) {
                easyGame.info_Label.setText("Player 1's turn     ");
            } else {
                easyGame.info_Label.setText("Player 2's turn     ");
            }
        } else easyGame.info_Label.setText("You turn");
        
        
        //Cập nhật nếu button 2 bị vô hiệu
        if(!easyGame.btn_move_two.isEnabled()){
            easyGame.btn_move_two.setEnabled(true);
        }
        
        // Repaint the play_Panel to reflect changes
        easyGame.play_Panel.revalidate();
        easyGame.play_Panel.repaint();
               
        ois.close(); // Đóng luồng sau khi đọc
        System.out.println("Game loaded successfully.");
            
        } catch (Exception e) {
            System.out.println("Load Exception!");
        }
    }
    
}
