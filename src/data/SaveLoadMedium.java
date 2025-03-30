/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.MediumGame;

/**
 *
 * @author user
 */
public class SaveLoadMedium {
    MediumGame mediumGame;
    Icon icon = new ImageIcon(getClass().getResource("/img/3.png"));
    
    public SaveLoadMedium(MediumGame mediumGame) {
        this.mediumGame = mediumGame;
    }
    
    public void save(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("saveMedium.dat")));
            
            DataStorageMedium ds = new DataStorageMedium();
            
            ds.pileButtons = mediumGame.pileButtons;
            ds.pileLabels = mediumGame.pileLabels;
            ds.piles = mediumGame.piles;
            ds.playerOnesTurn = mediumGame.playerOnesTurn;
            ds.row = mediumGame.row;
            ds.selectedRow = mediumGame.selectedRow;
            ds.info_labelName = mediumGame.info_label.getName();
            ds.mode = mediumGame.mode;
            
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
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("saveMedium.dat")));
            
            // Clear existing labels
            mediumGame.play_panel.removeAll();
            
            DataStorageMedium ds = (DataStorageMedium)ois.readObject();
            mediumGame.pileLabels = ds.pileLabels;
            mediumGame.piles = ds.piles;
            mediumGame.playerOnesTurn = ds.playerOnesTurn;
            mediumGame.mode = ds.mode;
            
            // Cập nhật info_label nếu đến lượt của người chơi 1
        if (mediumGame.playerOnesTurn) {
            mediumGame.info_label.setText("You turn");
        } else mediumGame.info_label.setText("Computer turn");
//            // Tìm lại info_label bằng tên đã lưu
//            for (Component comp : mediumGame.getComponents()) {
//                if (comp instanceof JLabel && comp.getName().equals(ds.info_labelName)) {
//                    mediumGame.info_label = (JLabel) comp;
//                    break;
//                }
//            }           
           // Thêm các panel và label đã lưu trữ vào play_panel
        for (int i = 0; i < 4; i++) {
            JPanel newPanel = new JPanel();
            newPanel.setLayout(new FlowLayout());
            newPanel.setBackground(Color.WHITE);

            for (int j = 0; j < mediumGame.piles[i]; j++) {
                if (mediumGame.pileLabels[i][j].isVisible()) {
                    newPanel.add(mediumGame.pileLabels[i][j]);
                }
            }

            // Thêm panel mới vào play_panel
            mediumGame.play_panel.add(newPanel);
        }

            
            // Thử gọi lại phương thức repaint() và revalidate() của panel
            mediumGame.play_panel.repaint();
            mediumGame.play_panel.revalidate();
            
            ois.close(); // Đóng luồng sau khi đọc
            System.out.println("Game loaded successfully.");
        }catch (Exception e) {
            System.out.println("Load Exception!");
        } 
    } 
}
