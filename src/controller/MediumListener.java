/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import view.EasyMedium;
import view.MediumGame;
import view.StartScreen;

/**
 *
 * @author user
 */
public class MediumListener implements ActionListener{
    private MediumGame main;

    public MediumListener(MediumGame main) {
        this.main = main;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        
        if(src.equals("click")){
            for (int i = 0; i < 4; i++) {
                if (e.getSource() == main.pileButtons[i]) {
                // Kiểm tra xem nút được click có còn số lần click và đã được chọn không
                main.removeMatches(i, 1); // Loại bỏ 1 que từ hàng đã chọn
                main.selectedRow = i;
                
                 // Vô hiệu hóa các nút click khác nếu hàng đã chọn không phải là hàng i
                for (int j = 0; j < 4; j++) {
                    if (j != i) {
                        main.pileButtons[j].setEnabled(false);
                    }
                }
                main.btn_switch.setEnabled(true);
                // Break out of the loop once action is performed to avoid array index out of bounds
                break;
            }
            }
        } 
        
        if(src.equals("move")){
            // Nếu là chế độ 1 người chơi
                main.switchPlayer();

                // Kiểm tra xem trò chơi đã kết thúc chưa
                if (main.isGameOver()) {
                    // Trò chơi đã kết thúc, xác định người thắng và hiển thị thông báo
                    String winner = main.playerOnesTurn ? "Player 2" : "Player 1";
                    JOptionPane.showMessageDialog(main, winner + " thắng!");
                    main.reset();
                } 
            
            // Vô hiệu hóa tất cả các nút nếu không còn lần click nào
            main.enablePileButtons();
            // Reset hàng đã chọn
            main.selectedRow = -1;
                
            //Cập nhật lại nút click
                for(int i=0;i<4;i++){
                    main.pileButtons[i].setEnabled(true);
                }
            
            main.btn_switch.setEnabled(false);
        }
        
        if(src.equals("Reset")){
            main.reset();
            // Trong phương thức actionPerformed, sau khi gọi phương thức reset
            System.out.println("Game reset. Checking action events...");
        }
        
        if(src.equals("Back")){
            new EasyMedium().setVisible(true);
            main.dispose();
        }
        
        if(src.equals("Save Game")){
           main.saveLoadMedium.save();
           JOptionPane.showMessageDialog(main, "Saved!", "Save Game", JOptionPane.INFORMATION_MESSAGE);
       }
     
       if(src.equals("Load Game")){
           main.saveLoadMedium.load();
           JOptionPane.showMessageDialog(main, "Loaded!", "Load Game", JOptionPane.INFORMATION_MESSAGE);
       }

        
    }
    
    
}
