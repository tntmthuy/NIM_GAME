/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.EasyGame;
import view.EasyMedium;
import view.StartScreen;

/**
 *
 * @author user
 */
public class EasyListener implements ActionListener{
    private EasyGame main;

    public EasyListener(EasyGame main) {
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        
        //listener nut 1
       if(src.equals("1")){
           //xac nhan nut 1 da duoc chon
           main.isBtnMoveOneSelected = true;
           
           //neu truoc do nut 2 da duoc chon thì 2 label cuoi mang ve binh thuong
           if(main.isBtnMoveTwoSelected){
               main.label[main.lastLabelIndex].setEnabled(true);
               main.label[main.lastLabelIndex-1].setEnabled(true);
               main.isBtnMoveTwoSelected=false;
           }
           
           //lam mo 1 label cuoi mang
           main.label[main.lastLabelIndex].setEnabled(false);
           main.btn_move.setEnabled(true);
       } 
       
       //listener nut 2
       if(src.equals("2")){
           //xac nhan nut 2 da duoc chon
           main.isBtnMoveTwoSelected = true;
           
           //neu truoc do nut 1 da duoc chon thì 1 label cuoi mang ve binh thuong
           if(main.isBtnMoveOneSelected){
               main.label[main.lastLabelIndex].setEnabled(true);
               main.isBtnMoveOneSelected=false;
           }
           
           
           if (main.lastLabelIndex > 1) {
            //lam mo 2 label cuoi mang
             main.label[main.lastLabelIndex].setEnabled(false);
             main.label[main.lastLabelIndex-1].setEnabled(false);
           }
           
           main.btn_move.setEnabled(true);
       }
       
       if (src.equals("Move")) {
                main.move();
                System.out.println("Số que diêm còn lại : "+main.lastLabelIndex);
                if (!main.label[1].isEnabled()) {
                    if(main.mode == 1){
                        // Nếu label đầu tiên đã bị vô hiệu hóa, người chơi hiện tại thắng
                        JOptionPane.showMessageDialog(main, "Người chơi " + main.currentPlayer + " thắng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        main.reset();
                    } else {
                    // Nếu label đầu tiên đã bị vô hiệu hóa, người chơi hiện tại thắng
                        JOptionPane.showMessageDialog(main, "Bạn thắng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        main.reset();
                    }
                    
                } else {
                    // Đặt lại trạng thái của các nút
                    main.isBtnMoveOneSelected = false;
                    main.isBtnMoveTwoSelected = false;
                    main.switchPlayer();
                    System.out.println("Đến lượt người chơi "+  main.currentPlayer);
                }

                main.btn_move.setEnabled(false); // Vô hiệu hóa nút "Move" sau khi hoàn thành nước đi
            
        }
        if(src.equals("Back")){
            new EasyMedium().setVisible(true);
            main.dispose();
       }
       
       if(src.equals("Reset")){
            main.reset();
       }
       
       if(src.equals("Save Game")){
           main.saveLoad.save();
           JOptionPane.showMessageDialog(main, "Saved!", "Save Game", JOptionPane.INFORMATION_MESSAGE);
       }
     
       if(src.equals("Load Game")){
           main.saveLoad.load();
           JOptionPane.showMessageDialog(main, "Loaded!", "Load Game", JOptionPane.INFORMATION_MESSAGE);
       }
    
    }
       
}
    


