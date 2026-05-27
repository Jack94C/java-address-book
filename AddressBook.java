//Student Name: Lanzilong,Xiaowenjie
/*Student Number: 32024140163
				  32024140156*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class AddressBook extends JFrame {
    // 组件声明
    JLabel jl1 = new JLabel("姓名:");
    JLabel jl2 = new JLabel("电话号码:");
    JLabel jl3 = new JLabel("性别:");
    JLabel jl4 = new JLabel("生日:");
    JLabel jl5 = new JLabel("喜好:");
    JLabel jl6 = new JLabel("星标:");
    JLabel jl7 = new JLabel("备注:");
    JTextField jtf1 = new JTextField();
    JTextField jtf2 = new JTextField();
    JTextArea jtf3 = new JTextArea();
    JTextField jtf4 = new JTextField();
    JTextField jtf5 = new JTextField();
    JTextArea jta = new JTextArea();
    JButton jb1 = new JButton("第一个");
    JButton jb2 = new JButton("上一个");
    JButton jb3 = new JButton("下一个");
    JButton jb4 = new JButton("最后一个");
    JButton jb5 = new JButton("新建");
    JButton jb6 = new JButton("刷新");
    JButton jb7 = new JButton("删除");
    Color color = jb7.getBackground();
    Cursor cursor = Cursor.getDefaultCursor();
    String[] sjcb = {"查询", "1", "2", "3", "4", "5", "6"};
    JComboBox jcb = new JComboBox(sjcb);
    JScrollPane jsp = new JScrollPane(jta);
    JScrollPane jsp1 = new JScrollPane(jtf3);
    Container container;
    JPanel pl1 = new JPanel();
    JPanel pl2 = new JPanel();
    JPanel panel = new JPanel();
    File file = new File("readme.txt");
    navigator nav = new navigator();
    JComboBox<String> starList; 
    ArrayList al = new ArrayList();
    int insertTo = 0;
    int totalRecords = 0;
    int lineNo = 0;
    int recordNo = 0;

    public AddressBook() {
        addif();
        makeLayout();
        addListener();
        this.setSize(700, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("通讯簿");
        this.setResizable(false);
        this.setVisible(true);
    }

    // 检查文件是否存在，不存在则创建空文件
    public void addif() {
        if (!file.exists()) {
            try {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
                pw.write("");
                pw.close();
            } catch (Exception exc) {
                System.out.println(exc.toString());
            }
        }
    }

    // 布局设置
    public void makeLayout() {
        String[] genderStrings = {"   ", "*"};
        starList = new JComboBox<>(genderStrings); // 初始化星标下拉框
        starList.setSelectedIndex(0);

        container = getContentPane();
        container.setLayout(new BorderLayout(5, 0));
        GridBagLayout gbl = new GridBagLayout();
        panel.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 10, 5, 5);

        // 添加组件
        GBLHelper.addComponent(panel, jl1, gbc, gbl, 0, 1, 1, 1, 5, 20, GBLHelper.LEFT);
        GBLHelper.addComponent(panel, jl2, gbc, gbl, 0, 2, 1, 1, 0, 20, GBLHelper.LEFT);
        GBLHelper.addComponent(panel, jl3, gbc, gbl, 0, 3, 1, 1, 0, 20, GBLHelper.LEFT);
        GBLHelper.addComponent(panel, jl4, gbc, gbl, 0, 4, 1, 1, 0, 20, GBLHelper.LEFT);
        GBLHelper.addComponent(panel, jl5, gbc, gbl, 0, 5, 1, 1, 0, 20, GBLHelper.LEFT);
        GBLHelper.addComponent(panel, jl6, gbc, gbl, 0, 6, 1, 1, 0, 20, GBLHelper.LEFT);
        GBLHelper.addComponent(panel, jl7, gbc, gbl, 0, 7, 1, 2, 0, 20, GBLHelper.LEFT);
        GBLHelper.addComponent(panel, jtf1, gbc, gbl, 1, 1, 1, 1, 10, 0);
        GBLHelper.addComponent(panel, jtf2, gbc, gbl, 1, 2, 1, 1, 0, 0);
        GBLHelper.addComponent(panel, jsp1, gbc, gbl, 1, 3, 1, 1, 0, 0);
        GBLHelper.addComponent(panel, jtf4, gbc, gbl, 1, 4, 1, 1, 0, 0);
        GBLHelper.addComponent(panel, jtf5, gbc, gbl, 1, 5, 1, 1, 0, 0);
        GBLHelper.addComponent(panel, starList, gbc, gbl, 1, 6, 1, 1, 0, 0);
        GBLHelper.addComponent(panel, jsp, gbc, gbl, 1, 7, 1, 1, 0, 0);

        jta.setLineWrap(true);
        jta.setMargin(new Insets(40, 5, 5, 5));
        jtf3.setLineWrap(true);

        container.add(panel, BorderLayout.NORTH);
        pl1.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));
        pl1.add(jb1);
        pl1.add(jb2);
        pl1.add(jb3);
        pl1.add(jb4);
        container.add(pl1, BorderLayout.CENTER);

        pl2.add(jb5);
        pl2.add(jb6);
        pl2.add(jb7);
        pl2.add(jcb);
        container.add(pl2, BorderLayout.SOUTH);
        container.setBackground(Color.gray);
    }

    // 事件监听
    public void addListener() {
        // 第一个
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] sdr = nav.first();
                updateForm(sdr);
            }
        });

        // 上一个
        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] sdr = nav.previous();
                updateForm(sdr);
            }
        });

        // 下一个
        jb3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] sdr = nav.next();
                updateForm(sdr);
            }
        });

        // 最后一个
        jb4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] sdr = nav.last();
                updateForm(sdr);
            }
        });

        // 新建
        jb5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makeSound();
                showQuestion();
            }
        });

        // 刷新（更新）
        jb6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        null,
                        "已按字母顺序排序，刷新成功！",
                        "Update",
                        JOptionPane.INFORMATION_MESSAGE
                );
                String[] sdr = nav.update();
                updateForm(sdr);
            }
        });

        // 删除按钮鼠标事件
        jb7.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setForeground(Color.red);
                Cursor newcursor = new Cursor(Cursor.HAND_CURSOR);
                source.setCursor(newcursor);
            }

            public void mouseExited(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setForeground(Color.black);
            }
        });

        // 删除按钮点击事件
        jb7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makesure();
            }
        });

        // 下拉框查询
        jcb.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                search();
            }
        });

        jcb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jcb.getSelectedIndex() == 0) {
                    System.out.println("查询:查询");
                    System.out.println("我**之前已经跟你说过了!!!");
                    clearForm();
                } else {
                    recordNo = Integer.parseInt(sjcb[jcb.getSelectedIndex()]);
                    System.out.println("查询: " + recordNo);
                    String[] sdr = nav.search(recordNo);
                    updateForm(sdr);
                }
            }
        });
    }

    // 更新表单数据
    private void updateForm(String[] sdr) {
        jtf1.setText(sdr[0]);
        jtf2.setText(sdr[1]);
        jtf3.setText(sdr[2]);
        jtf4.setText(sdr[3]);
        jtf5.setText(sdr[4]);
        starList.setSelectedItem(sdr[5].equals("*") ? "*" : "   ");
        jta.setText(sdr[6]);
    }

    // 清空表单
    private void clearForm() {
        jtf1.setText("啥都没有!!!");
        jtf2.setText("啥都没有!!!");
        jtf3.setText("啥都没有!!!");
        jtf4.setText("啥都没有!!!");
        jtf5.setText("啥都没有!!!");
        jta.setText("啥都没有!!!");
    }

    // 修复：调用nav的删除接口，无需手动处理文件
    private void deleteRecord() {
        nav.deleteCurrentRecord();
        clearForm();
        System.out.println("此记录已从文件中删除!");
    }

    // 获取颜色
    public Color getColor() {
        return color;
    }

    // 播放声音
    public void makeSound() {
        System.out.println("注意!!!");
        AudioPlayer ap = new AudioPlayer();
        ap.startPlay("dog003.au", false);
    }

    // 新建确认弹窗
    public void showQuestion() {
        Object[] options = {"Yes", "No"};
        int n = JOptionPane.showOptionDialog(
                this,
                "你确定要新建一个记录吗?\n" + "如果你选择 ''是'' ,\n" + "将会打开一个新的界面,\n" + "然后你就可以进行编辑了!",
                "打开一个新界面以添加信息",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]
        );
        setLabel1(n);
    }

    // 删除确认弹窗（修复：调用新的deleteRecord方法）
    public void makesure() {
        String[] options = {"YES", "NO", "NOT SURE"};
        int n = JOptionPane.showOptionDialog(
                this,
                "你确定要 删除 这个记录?",
                "Delete records",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]
        );
        if (n == 0) {
            deleteRecord(); // 调用修复后的删除方法
        }
    }

    // 查询说明
    public void search() {
        JOptionPane.showMessageDialog(
                null,
                "请不要选 ''查询'', 谢谢!\n(里面啥都没有!)\n" + "你可以选择数字 1~6\n",
                "说明--''查询'' 按键",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // 新建记录：打开add窗口并传递数据
    public void setLabel1(int n) {
        if (n == 0) {
            String[] newRecord = {
                    jtf1.getText().trim(),
                    jtf2.getText().trim(),
                    jtf3.getText().trim(),
                    jtf4.getText().trim(),
                    jtf5.getText().trim(),
                    starList.getSelectedItem().toString().trim(),
                    jta.getText().trim()
            };
            new add(newRecord);
        }
    }

    // 窗口边距
    public Insets getInsets() {
        return new Insets(40, 10, 10, 10);
    }

    // 主函数
    public static void main(String[] args) {
        AddressBook ex = new AddressBook();
    }
}