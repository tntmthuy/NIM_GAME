/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class DataStorage implements Serializable{
    public JLabel[] label;
    public int lastLabelIndex; // Index của label cuối cùng
    public int btnOneClickCount;
    public int btnTwoClickCount; // Số lần nút "2" được nhấn trong mỗi lượt
    public boolean isBtnMoveOneSelected; // Trạng thái của nút "1"
    public boolean isBtnMoveTwoSelected; // Trạng thái của nút "2"
    public int currentPlayer; // Biến để theo dõi người chơi hiện tại, người chơi 1 chơi trước
//    public JLabel info_Label;
     public String info_LabelName;
}
