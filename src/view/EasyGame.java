package view;
import controller.EasyListener;
import data.SaveLoad;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class EasyGame extends JFrame implements Serializable{

    public JLabel[] label;
    public JPanel play_Panel;
    public int lastLabelIndex = 10; // Index của label cuối cùng
    public boolean isBtnMoveOneSelected = false; // Trạng thái của nút "1"
    public boolean isBtnMoveTwoSelected = false; // Trạng thái của nút "2"
    public int currentPlayer = 1; // Biến để theo dõi người chơi hiện tại, người chơi 1 chơi trước
    public JLabel info_Label;

    public Font font = new Font("Rockwell Extra Bold", 0, 14);
    public JButton btn_move_two;
    
    public SaveLoad saveLoad = new SaveLoad(this);
    public JButton btn_play_with_computer; // Nút để chọn chế độ chơi với máy
    public JButton btn_play_with_human; // Nút để chọn chế độ chơi với người
    public int mode = 1; //Kiểm tra chế độ trò chơi
    public JButton btn_move;
    private JButton btn_move_one;
    public int numToTake; //so diem ma may se di
    
    public EasyGame() {
        label = new JLabel[11]; // Khởi tạo mảng
        this.init();
    }
    
    public void init() {
        this.setTitle("Nim Game");
        this.setSize(700, 400);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        ActionListener actionListener = new EasyListener(this);
        
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
                    info_Label.setText("Player 1 turn");
                    reset();
                    System.out.println("Chơi với máy");
                } else if (choice == 1) {
                    // Chế độ 2 người chơi được chọn
                    mode = 1;
                    info_Label.setText("Player 1 turn");
                    reset();
                    System.out.println("Chơi với người");
                }
            }
        });
        
        opt_Panel.add(btn_setting);
        
        
        JButton btn_save = new JButton("Save Game");
        btn_save.setFont(font);
        btn_save.addActionListener(actionListener);
        
        JButton btn_load = new JButton("Load Game");
        btn_load.setFont(font);
        btn_load.addActionListener(actionListener);
        
        JButton btn_reset = new JButton("Reset");
        btn_reset.setFont(font);
        btn_reset.addActionListener(actionListener);      

        
        // Thêm các nút vào panel tùy chọn
        opt_Panel.add(btn_save);
        opt_Panel.add(btn_load);
        opt_Panel.add(btn_reset);
        
        this.add(opt_Panel, BorderLayout.PAGE_START );
        
        play_Panel = new JPanel(new GridLayout(1,10));
        play_Panel.setBackground(Color.WHITE);
        Icon icon = new ImageIcon(getClass().getResource("/img/4.png"));
        for (int i = 1; i <= 10; i++) {
            label[i] = new JLabel(icon);
            play_Panel.add(label[i]);
        }
        
        JPanel move_Panel = new JPanel(new FlowLayout());
        info_Label = new JLabel("Player 1 go first   ");
        info_Label.setFont(font);
        
        btn_move_one = new JButton("1");
        btn_move_one.addActionListener(actionListener);
        btn_move_one.setFont(font);
        
        btn_move_two = new JButton("2");
        btn_move_two.addActionListener(actionListener);
        btn_move_two.setFont(font);

        btn_move = new JButton("Move");
        btn_move.setFont(font);
        btn_move.addActionListener(actionListener);
        
        JButton btn_back = new JButton("Back");
        btn_back.setFont(font);
        btn_back.addActionListener(actionListener);

        move_Panel.add(info_Label);        
        move_Panel.add(btn_move_one);
        move_Panel.add(btn_move_two);
        move_Panel.add(btn_move);
        move_Panel.add(btn_back);
        
        this.add(play_Panel, BorderLayout.CENTER);
        this.add(move_Panel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    
   
    public static void main(String[] args) {
        new EasyGame();
    }
        
   
    
    public void reset(){
        lastLabelIndex = 10;
        isBtnMoveOneSelected = false;
        isBtnMoveTwoSelected = false;
        currentPlayer = 1;
        btn_move_two.setEnabled(true);


        // Hiển thị thông báo cho người chơi 1 bắt đầu
        if(mode==1)
        info_Label.setText("Player 1 go first   ");
        else info_Label.setText("You turn");
        // Đặt lại trạng thái ban đầu cho các label
        Icon icon = new ImageIcon(getClass().getResource("/img/4.png"));
            for (int i = 1; i <= 10; i++) {
                label[i].setIcon(icon);
                label[i].setVisible(true);
                label[i].setEnabled(true); // Đảm bảo rằng label có thể được nhấp vào lại
            }
    }
      
    public void move(){
        //kiểm tra label có bị vô hiệu hóa không, nếu có thì biến mất
        for (int i = 10; i >= 1; i--) {
            if (!label[i].isEnabled()) {
                label[i].setVisible(false);
                lastLabelIndex = i-1;
            }
        }
        
        if (lastLabelIndex == 1) {
            btn_move_two.setEnabled(false);
        }
    }
    
    // Phương thức để chuyển người chơi
    public void switchPlayer() {
        
        if (mode == 2) { 
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
            info_Label.setText((currentPlayer == 1) ? "Your turn" : "Player Computer's turn");
            if(currentPlayer==2){
                
                System.out.println("Số diêm còn lại: "+lastLabelIndex);
                // Vô hiệu hóa các nút khi máy đang đi và hiển thị số label máy chọn trong 2 giây
                disableButtons();

                // Tạo một luồng để tạm dừng chương trình trong 2 giây
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000); // Dừng chương trình trong 2 giây
                            computerMove();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // Kích hoạt lại các nút và chuyển lượt chơi cho người chơi tiếp theo
                        enableButtons();
                        currentPlayer = 1;
                        info_Label.setText((currentPlayer == 1) ? "Your turn" : "Player Computer's turn");
                        // Kiểm tra điều kiện kết thúc trò chơi
                        if (lastLabelIndex < 1) {
                            JOptionPane.showMessageDialog(EasyGame.this, "Computer wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                            reset(); // Đặt lại trò chơi sau khi kết thúc
                        }
                        if (lastLabelIndex < 1 && currentPlayer == 1) {
                            JOptionPane.showMessageDialog(EasyGame.this, "Human wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                            reset(); // Đặt lại trò chơi sau khi kết thúc
                        }
                    }
                });

                thread.start(); // Bắt đầu luồng
            }
            
            
        } else { // Chơi hai người
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
            info_Label.setText("Player " + currentPlayer + "'s turn");
        }
    }
    
        public void computerMove() {
            int count = 0;
            if (lastLabelIndex == 1) {
                label[lastLabelIndex].setVisible(false);
                lastLabelIndex--;
                count++;
            } else if (lastLabelIndex == 2) {
                label[lastLabelIndex].setVisible(false);
                label[lastLabelIndex - 1].setVisible(false);
                lastLabelIndex -= 2;
                count += 2;
            } else {
                int remainder = lastLabelIndex % 3;
                if (remainder == 0) {
                    label[lastLabelIndex].setVisible(false);
                    label[lastLabelIndex - 1].setVisible(false);
                    lastLabelIndex-=2;
                    count += 2;
                } else {
                    for (int i = 1; i <= remainder; i++) {
                        label[lastLabelIndex].setVisible(false);
                        lastLabelIndex--;
                        count++;
                    }
                }
            }
            System.out.println("Máy đã đi: " + count + " diêm");
            count = 0;
        
    }
    // Kích hoạt lại các nút
    private void enableButtons() {
        btn_move.setEnabled(true);
        btn_move_one.setEnabled(true);
        btn_move_two.setEnabled(true);
    }
    
    // Vô hiệu hóa các nút
    private void disableButtons() {
        btn_move.setEnabled(false);
        btn_move_one.setEnabled(false);
        btn_move_two.setEnabled(false);
    }
    
}


