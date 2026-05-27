//Student Name: Lanzilong,Xiaowenjie
/*Student Number: 32024140163
				  32024140156*/
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.FileDialog;

public class MyActListener1 implements ActionListener{
    // 引用add窗口实例
    add ex;
    // 记录分隔符
    private static final String RECORD_SEPARATOR = "---";

    // 构造函数：接收add窗口对象
    public MyActListener1(add example){
        ex = example;
    }

    // 点击“阅读”按钮执行逻辑（修复：跳过分隔符，仅显示7个字段）
    public void actionPerformed(ActionEvent e){
        String fileName = "readme.txt";
        int choice = ex.getOptions();
        System.out.println("选项:" + choice);
        
        // 固定读取readme.txt文件（与写入文件一致）
        fileName = "readme.txt";
        ex.setRFname(fileName);
        fileName = ex.getRFname();
        File file = new File(fileName);

        // 读取文件内容并显示到文本域（跳过分隔符）
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuffer text = new StringBuffer();
            String line;
            int fieldCount = 0; // 仅统计7个字段

            while((line = br.readLine()) != null){
                line = line.trim();
                // 跳过分隔符，不显示给用户
                if (line.equals(RECORD_SEPARATOR)) {
                    text.append("\n"); // 记录间空一行增强可读性
                    fieldCount = 0;
                    continue;
                }
                // 仅显示7个字段，避免多余内容
                if (fieldCount < 7) {
                    text.append(line + "\n");
                    fieldCount++;
                }
            }
            br.close();

            // 若文件为空，提示用户；否则仅显示已有记录（无分隔符）
            if(text.length() == 0){
                ex.setTaText("文件为空，请按以下格式添加记录：\n姓名\n电话号码\n性别\n生日\n喜好\n星标\n备注");
            } else {
                ex.setTaText(new String(text));
                // 提示用户直接在末尾添加新记录，无需空行
                JOptionPane.showMessageDialog(ex, "已加载所有记录！\n直接在文本域末尾添加新记录（7行）即可，无需空行", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(Exception exc){
            exc.printStackTrace();
            JOptionPane.showMessageDialog(ex, "文件读取失败！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}