//Student Name: Lanzilong,Xiaowenjie
/*Student Number: 32024140163
				  32024140156*/
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class MyActListener2 implements ActionListener{
    add ex;
    // 引入导航类，调用新增接口
    navigator nav = new navigator();

    public MyActListener2(add example){
        ex = example;
    }

    public void actionPerformed(ActionEvent e){
        String text = ex.getTaText().trim();
        String[] lines = text.split("\n");

        // 提取7个字段
        String[] newRecord = new String[7];
        for (int i = 0; i < 7; i++) {
            newRecord[i] = (i < lines.length) ? lines[i].trim() : "";
        }

        // 格式校验：姓名不能为空
        if (newRecord[0].isEmpty() || newRecord[0].contains("请按以下格式填写")) {
            JOptionPane.showMessageDialog(ex, 
                    "姓名不能为空！\n请按顺序填写：\n1.姓名 2.电话号码 3.性别 4.生日 5.喜好 6.星标 7.备注", 
                    "格式错误", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 调用新增接口
        nav.addRecord(newRecord);

        JOptionPane.showMessageDialog(ex, "记录保存成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
        // 重置文本域
        ex.setTaText("记录已保存！可继续添加新记录：\n姓名\n电话号码\n性别\n生日\n喜好\n星标\n备注");
    }

    public String replaceLineSeparator(String s){
        return s;
    }
}