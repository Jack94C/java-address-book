//Student Name: Lanzilong,Xiaowenjie
/*Student Number: 32024140163
				  32024140156*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class add extends JFrame {
    // 组件声明
    JButton jb1 = new JButton("阅读");
    JButton jb2 = new JButton("撰写");
    JButton jb3 = new JButton("说明");
    JButton jb4 = new JButton("OK");
    JLabel jl1 = new JLabel("readme.txt");
    JLabel[] jl = {new JLabel("待读取和被写入的文件:"), new JLabel(""), new JLabel(""), new JLabel("")};
    JTextArea jta;
    JScrollPane jsp;
    JRadioButton[] jrb = {new JRadioButton("文件对话框"), new JRadioButton("选择文件"), new JRadioButton("无")};

    // 无参构造函数
    public add() {
        String defaultText = "请按以下格式填写新记录：\n姓名\n电话号码\n性别\n生日\n喜好\n星标\n备注";
        jta = new JTextArea(defaultText);
        jsp = new JScrollPane(jta);
        makeLayout();
        addListeners();
        initRadioButtons();
        this.setSize(450, 350);
        this.setTitle("添加记录");
        this.setResizable(false);
        this.setVisible(true);
    }

    // 带参数构造函数
    public add(String[] recordData) {
        String standardTemplate = String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s",
                recordData[0].trim(), 
                recordData[1].trim(), 
                recordData[2].trim(), 
                recordData[3].trim(), 
                recordData[4].trim(), 
                recordData[5].trim(), 
                recordData[6].trim());
        jta = new JTextArea(standardTemplate);
        jsp = new JScrollPane(jta);
        makeLayout();
        addListeners();
        initRadioButtons();
        this.setSize(450, 350);
        this.setTitle("添加记录");
        this.setResizable(false);
        this.setVisible(true);
    }

    // 初始化单选按钮
    private void initRadioButtons() {
        ButtonGroup bg = new ButtonGroup();
        for (JRadioButton rb : jrb) {
            bg.add(rb);
        }
        jrb[2].setSelected(true);
    }

    // 布局设置
    public void makeLayout() {
        Container ct = getContentPane();
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                ImageIcon img = new ImageIcon("63[1].jpg");
                g.drawImage(img.getImage(), 0, 0, null);
                super.paintComponent(g);
            }
        };
        panel.setOpaque(false);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        Border raisedbevel, loweredbevel, compound;
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);

        GBLHelper.setForeColor(Color.RED);
        jta.setMargin(new Insets(5, 5, 5, 5));
        panel.setLayout(gbl);

        // 添加组件
        GBLHelper.addComponent(panel, jl[0], gbc, gbl, 0, 0, 1, 1, 5, 10, GBLHelper.LEFT);
        GBLHelper.addComponent(panel, jl1, gbc, gbl, 0, 1, 1, 1, 0, 10);
        GBLHelper.addComponent(panel, jb1, gbc, gbl, 0, 2, 1, 1, 0, 10);
        GBLHelper.addComponent(panel, jb2, gbc, gbl, 0, 3, 1, 1, 0, 10);
        GBLHelper.addComponent(panel, jb3, gbc, gbl, 0, 4, 1, 1, 0, 10);
        GBLHelper.addComponent(panel, jb4, gbc, gbl, 0, 5, 1, 1, 0, 10);
        GBLHelper.addComponent(panel, jsp, gbc, gbl, 2, 0, 1, 7, 50, 0);
        GBLHelper.addComponent(panel, new JLabel(""), gbc, gbl, 1, 0, 1, 1, 5, 0);
        GBLHelper.addComponent(panel, new JLabel(""), gbc, gbl, 0, 6, 1, 1, 0, 10);

        ct.add(panel);
    }

    // 事件监听
    public void addListeners() {
        // 阅读按钮
        jb1.addActionListener(new MyActListener1(this));
        jb1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jb2.setBackground(Color.yellow);
            }
        });

        // 撰写按钮
        jb2.addActionListener(new MyActListener2(this));
        jb2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jb4.setBackground(Color.yellow);
            }
        });

        // 说明按钮（鼠标进入/退出/点击）
        jb3.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                jb3.setBackground(Color.red);
                jb3.setForeground(Color.yellow);
            }
        });
        jb3.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent e) {
                jb3.setBackground(Color.gray);
                jb3.setForeground(Color.red);
            }
        });
        jb3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jb1.setBackground(Color.yellow);
                instruction();
            }
        });

        // OK按钮
        jb4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "操作完成！返回主窗口刷新查看", "提示", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // 关闭当前窗口
            }
        });
    }

    // 说明弹窗
    public void instruction() {
        JOptionPane.showMessageDialog(
                null,
                "1. 点击''阅读''可查看已有记录\n2. 直接填写7行新记录（姓名-备注）\n3. 点击''撰写''保存新记录\n4. 点击''OK''关闭窗口",
                "操作说明",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // 设置文本域内容
    public void setTaText(String s) {
        jta.setText(s);
    }

    // 获取文本域内容
    public String getTaText() {
        return jta.getText();
    }

    // 设置读取文件名
    public void setRFname(String name) {
        jl1.setText(name);
    }

    // 获取读取文件名
    public String getRFname() {
        return jl1.getText();
    }

    // 设置写入文件名
    public void setWFname(String name) {
        jl1.setText(name);
    }

    // 获取写入文件名
    public String getWFname() {
        return jl1.getText();
    }

    // 获取选中的选项
    public int getOptions() {
        for (int i = 0; i < jrb.length; i++) {
            if (jrb[i].isSelected())
                return i;
        }
        return -1;
    }

    // 窗口边距
    public Insets getInsets() {
        return new Insets(45, 15, 15, 15);
    }

    // 主函数
    public static void main(String[] args) {
        new add();
    }
}