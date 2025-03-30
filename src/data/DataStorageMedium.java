/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.awt.Font;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class DataStorageMedium implements Serializable{
    public JButton[] pileButtons; //nút click
    public JLabel[][] pileLabels; //hình ảnh các đống
    public int[] piles; // số lượng diêm trong mỗi đống
    public boolean playerOnesTurn; //lượt chơi
    public int row; //lưu chỉ số hàng 1 nút 
    public int selectedRow; // Hàng đang được chọn
//    public JLabel info_label;
    public String info_labelName;
    public int mode ; //trạng thái trò chơi 2 người chơi
}
