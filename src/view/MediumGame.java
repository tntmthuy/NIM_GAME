/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author user
 */
import controller.MediumListener;
import data.SaveLoadMedium;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MediumGame extends JFrame {
    public JPanel play_panel; // để hiển thị phần chính của game
    public JPanel[] panel;
    public JPanel remove_panel; // chứa nút remove
    public JButton[] pileButtons; // nút click
    public JLabel[][] pileLabels; // hình ảnh các diêm
    public int[] piles = {1, 3, 5, 7}; // số lượng diêm trong mỗi đống
    public boolean playerOnesTurn = true; // lượt chơi
    public int row; // lưu chỉ số hàng 1 nút 
    public int selectedRow = -1; // Hàng đang được chọn

    public Font font = new Font("Rockwell Extra Bold", 0, 14);
    public JLabel info_label;
    public int mode = 1; // trạng thái trò chơi 2 người chơi
    private final JButton btn_reset;
    private final JButton btn_save;
    public final JButton btn_switch;
    
    public SaveLoadMedium saveLoadMedium = new SaveLoadMedium(this);
    private final ImageIcon icon;
    private final JButton btn_load;
    private final JButton btn_back;
    
    public MediumGame() {
        setTitle("Nim Game");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        ActionListener actionListener = new MediumListener(this);
        
        JPanel opt_Panel = new JPanel(new FlowLayout());
        
        Icon icon1 = new ImageIcon(getClass().getResource("/img/settings.png"));

        JButton btn_setting = new JButton();
        btn_setting.setIcon(icon1);
        btn_setting.setContentAreaFilled(false);
        btn_setting.setBorderPainted(false);
        
        // Thêm ActionListener cho btn_setting
        btn_setting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hiển thị cửa sổ JOptionPane để chọn chế độ
                String[] options = {"Play with Computer", "2 Players"};
                int choice = JOptionPane.showOptionDialog(
                        null, 
                        "Choose game mode:", 
                        "Game Mode", 
                        JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, 
                        null, 
                        options, 
                        options[0]);

                // Xử lý sự lựa chọn của người dùng
                if (choice == 0) {
                    // Chế độ chơi với máy được chọn
                    mode = 2;
                    info_label.setText("Player 1 turn");
                    reset();
                    System.out.println("Chơi với máy");
                } else if (choice == 1) {
                    // Chế độ 2 người chơi được chọn
                    mode = 1;
                    info_label.setText("Player 1 turn");
                    reset();
                    System.out.println("Chơi với người");
                }
            }
        });
        opt_Panel.add(btn_setting);
        
        btn_save = new JButton("Save Game");
        btn_save.setFont(font);
        btn_save.addActionListener(actionListener);
        opt_Panel.add(btn_save);
        
        btn_load = new JButton("Load Game");
        btn_load.setFont(font);
        btn_load.addActionListener(actionListener);
        opt_Panel.add(btn_load);
        
        btn_reset = new JButton("Reset");
        btn_reset.setFont(font);
        btn_reset.addActionListener(actionListener); 
        opt_Panel.add(btn_reset);
        
        icon = new ImageIcon(getClass().getResource("/img/3.png"));
        
        play_panel = new JPanel();
        play_panel.setLayout(new GridLayout(4, 1));
        play_panel.setBackground(Color.WHITE);
        play_panel.setOpaque(true);
        
        remove_panel = new JPanel(new GridLayout(4, 1)); 

        panel = new JPanel[4]; 
        pileButtons = new JButton[4]; 
        pileLabels = new JLabel[4][];

        for (int i = 0; i < 4; i++) { 
            row = i;
            panel[i] = new JPanel(); // Khởi tạo mỗi phần tử của panel
            panel[i].setLayout(new FlowLayout());
            panel[i].setBackground(Color.white);
            
            pileLabels[i] = new JLabel[piles[i]]; 
            for(int j=0; j<piles[i]; j++){ 
                pileLabels[i][j] = new JLabel(icon);
                panel[i].add(pileLabels[i][j]);
            }
            pileButtons[i] = new JButton("click"); // Tạo nút "Remove" cho mỗi đống
            pileButtons[i].addActionListener(actionListener);
            pileButtons[i].setFont(font);
            remove_panel.add(pileButtons[i]); // Thêm nút "Remove" vào remove_panel
            play_panel.add(panel[i]);
        }
        
        JPanel switchPanel = new JPanel();
        info_label = new JLabel("Player 1 turn");
        info_label.setFont(font);
        btn_switch = new JButton("move");
        btn_switch.setFont(font);
        btn_switch.addActionListener(actionListener);
        
        btn_back = new JButton("Back");
        btn_back.setFont(font);
        btn_back.addActionListener(actionListener);
        
        switchPanel.add(info_label);
        switchPanel.add(btn_switch);
        switchPanel.add(btn_back);
        
        this.add(opt_Panel, BorderLayout.PAGE_START);
        this.add(play_panel, BorderLayout.CENTER);
        this.add(remove_panel, BorderLayout.EAST);
        this.add(switchPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    
    public boolean isGameOver() {
        for (int pile : piles) {
            if (pile > 0) {
                return false;
            }
        }
        return true;
    }

    public void removeMatches(int row, int take) {
        // Remove matches from the selected row
        // This is where you implement your logic to remove matches from the selected row
        // For now, it just prints the row and number of matches to remove
        for(int i = 0; i < take; i++){
            if (piles[row] > 0) {
                // Chỉ xóa nhãn nếu có nhiều hơn 0 nhãn trong hàng
                pileLabels[row][piles[row] - 1].setVisible(false); // Xóa nhãn cuối cùng trong hàng đã chọn
                System.out.println("Đã ẩn pileLabels["+row+"]["+(piles[row]-1)+"]");
                piles[row]--; // Giảm số lượng nhãn trong hàng đi 1
                System.out.println("Removing " + take + " matches from row " + row);
            }
        }
        
    }
    
    public void reset() {
        // Reset số lượng diêm trong mỗi đống
        piles = new int[]{1, 3, 5, 7};
        
        // Reset trạng thái lượt chơi
        playerOnesTurn = true;

        // Reset hàng đã chọn
        selectedRow = -1;
        
        play_panel.removeAll();
        for (int i = 0; i < 4; i++) { 
            row = i;
            panel[i] = new JPanel(); // Khởi tạo mỗi phần tử của panel
            panel[i].setLayout(new FlowLayout());
            panel[i].setBackground(Color.white);
            
            pileLabels[i] = new JLabel[piles[i]]; 
            for(int j=0; j<piles[i]; j++){ 
                pileLabels[i][j] = new JLabel(icon);
                panel[i].add(pileLabels[i][j]);
            }
            play_panel.add(panel[i]);
            pileButtons[i].setEnabled(true);
        }
        // Cập nhật giao diện
        play_panel.repaint();
        play_panel.revalidate();

        // Cập nhật thông báo lượt chơi
        if(mode==1)
        info_label.setText("Player 1 turn");
        else info_label.setText("You turn");
        // Trong phương thức reset
        System.out.println("Resetting game state...");
    }
    
    public int nimSum() {
        int nimSum = 0;
        //duyệt qua mỗi đống trong mảng piles
        for (int pile : piles) {
            nimSum ^= pile;
        }
        return nimSum;
    }
    
    public void computerMove() {
        int nimSum = nimSum();
        if (nimSum == 0) {
            // Nếu phép cộng Nim là 0, chọn một nước đi ngẫu nhiên từ một đống có số match khác 0
            Random random = new Random();
            int pileIndex = random.nextInt(piles.length);
            int take = random.nextInt(piles[pileIndex]) + 1;
            removeMatches(pileIndex, take);
            System.out.println("Máy đã đi ngẫu nhiên");
        } else {
            // Tìm nước đi tốt nhất dựa trên phép cộng Nim
            
            for (int i = 0; i < piles.length; i++) {
                int take = piles[i] ^ nimSum;
                if (take < piles[i]) {
                    removeMatches(i, piles[i] - take);
                    System.out.println("Máy đã đi nước  đi tốt nhất");
                    break;
                }
            }
        }
    }
    
    public void updateInfoLabel() {
        if (playerOnesTurn) {
            info_label.setText("Player 1 turn");
        } else if (mode==2 && !playerOnesTurn){
            info_label.setText("Computer turn");
        } else {
            info_label.setText("Player 2 turn");
        }
    }
    
    public void switchPlayer() {
    // Nếu đang ở chế độ chơi 2 người
    if (mode == 1) {
        if (playerOnesTurn) {
            playerOnesTurn = false; // Chuyển sang lượt của Player 2
            info_label.setText("Player 2 turn");
        } else {
            playerOnesTurn = true; // Chuyển lại lượt của Player 1
            info_label.setText("Player 1 turn");
        }
    } else if (mode == 2) {
        if (playerOnesTurn) {
            playerOnesTurn = false;
            System.out.println("Lượt của máy");
            info_label.setText("Computer turn");
            // Tạo một luồng để tạm dừng chương trình trong 2 giây
            // Vô hiệu hóa các nút khi máy đang đi và hiển thị số label máy chọn trong 2 giây
            disableButtons();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000); // Dừng chương trình trong 2 giây
                        computerMove();
                        // Kiểm tra trạng thái kết thúc trò chơi
                        if (isGameOver()) {
                            // Xác định người thắng và hiển thị thông báo
                            String winner = playerOnesTurn ? "You" : "Computer";
                            JOptionPane.showMessageDialog(null, winner + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                            reset(); // Reset trò chơi sau khi kết thúc
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //kích hoạt lại các nút
                    enableButtons();
                    playerOnesTurn = true;
                    System.out.println("Chuyển sang lượt của người");
                    // Cập nhật lại thông tin lượt chơi sau khi máy đã thực hiện nước đi
                    info_label.setText("You turn");
                }
            });
            thread.start(); // Khởi động luồng
        } 
    }
}

    // Kích hoạt lại các nút
    private void enableButtons() {
        btn_save.setEnabled(true);
        btn_reset.setEnabled(true);
        btn_switch.setEnabled(true);
        btn_load.setEnabled(true);
        btn_back.setEnabled(true);
    }
    
    // Vô hiệu hóa các nút
    private void disableButtons() {
        btn_save.setEnabled(false);
        btn_reset.setEnabled(false);
        btn_switch.setEnabled(false);
        btn_load.setEnabled(false);
        btn_back.setEnabled(false);
    }
    
    public static void main(String[] args) {
        new MediumGame();
    }

    public void disablePileButtons() {
        // Disable all pileButtons
        for (JButton button : pileButtons) {
            button.setEnabled(false);
        }
    }

    public void enablePileButtons() {
        // Enable all pileButtons
        for (JButton button : pileButtons) {
            button.setEnabled(true);
        }
    }
}



