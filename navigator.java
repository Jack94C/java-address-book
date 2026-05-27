//Student Name: Lanzilong,Xiaowenjie
/*Student Number: 32024140163
				  32024140156*/
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

public class navigator{
    int recordNo = 1; // 当前记录编号（从1开始）
    int totalRecords = 0; // 总记录数
    File file = new File("readme.txt"); // 数据文件
    // 记录分隔符（替代手动空行）
    private static final String RECORD_SEPARATOR = "---";

    // 读取所有记录
    private ArrayList<String[]> readAllRecords() {
        ArrayList<String[]> records = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder currentRecord = new StringBuilder();
            int fieldCount = 0; // 字段计数（0-6：姓名到备注）

            while ((line = br.readLine()) != null) {
                line = line.trim();
                // 遇到分隔符：当前记录结束，加入列表
                if (line.equals(RECORD_SEPARATOR)) {
                    if (currentRecord.length() > 0) {
                        String[] fields = currentRecord.toString().split("\\|");
                        // 补全7个字段（避免缺失）
                        String[] record = new String[7];
                        for (int i = 0; i < 7; i++) {
                            record[i] = (i < fields.length) ? fields[i] : "";
                        }
                        records.add(record);
                        currentRecord = new StringBuilder();
                        fieldCount = 0;
                    }
                    continue;
                }

                // 收集7个字段（姓名、电话、性别、生日、喜好、星标、备注）
                if (fieldCount < 7) {
                    if (currentRecord.length() > 0) {
                        currentRecord.append("|"); // 字段分隔符
                    }
                    currentRecord.append(line);
                    fieldCount++;
                }
            }
            br.close();

            // 处理最后一条记录（无分隔符的情况，自动补全）
            if (currentRecord.length() > 0) {
                String[] fields = currentRecord.toString().split("\\|");
                String[] record = new String[7];
                for (int i = 0; i < 7; i++) {
                    record[i] = (i < fields.length) ? fields[i] : "";
                }
                records.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { if (br != null) br.close(); } catch (Exception e) {}
        }

        totalRecords = records.size();
        return records;
    }

    // 写入所有记录（自动添加分隔符）
    private void writeAllRecords(ArrayList<String[]> records) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            for (String[] record : records) {
                // 写入7个字段
                for (int i = 0; i < 7; i++) {
                    pw.println(record[i] == null ? "" : record[i]);
                }
                // 自动写入分隔符（替代手动空行）
                pw.println(RECORD_SEPARATOR);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { if (pw != null) pw.close(); } catch (Exception e) {}
        }
    }

    // 下一条记录
    public String[] next(){
        ArrayList<String[]> records = readAllRecords();
        String[] rn = {"","","","","","",""};

        if (records.isEmpty()) return rn;

        // 边界判断
        if (recordNo < totalRecords) {
            recordNo++;
        } else {
            JOptionPane.showMessageDialog(null, "已到达最后一条记录！", "提示", JOptionPane.INFORMATION_MESSAGE);
        }

        // 返回当前记录
        rn = records.get(recordNo - 1);
        System.out.println("总记录数：" + totalRecords + "，当前记录：" + recordNo);
        return rn;
    }

    // 上一条记录
    public String[] previous(){
        ArrayList<String[]> records = readAllRecords();
        String[] rn = {"","","","","","",""};

        if (records.isEmpty()) return rn;

        // 边界判断
        if (recordNo > 1) {
            recordNo--;
        } else {
            JOptionPane.showMessageDialog(null, "已到达第一条记录！", "提示", JOptionPane.INFORMATION_MESSAGE);
        }

        rn = records.get(recordNo - 1);
        System.out.println("总记录数：" + totalRecords + "，当前记录：" + recordNo);
        return rn;
    }

    // 第一条记录
    public String[] first(){
        ArrayList<String[]> records = readAllRecords();
        String[] rn = {"","","","","","",""};

        if (!records.isEmpty()) {
            recordNo = 1;
            rn = records.get(0);
        }

        System.out.println("总记录数：" + totalRecords + "，当前记录：1");
        return rn;
    }

    // 最后一条记录
    public String[] last(){
        ArrayList<String[]> records = readAllRecords();
        String[] rn = {"","","","","","",""};

        if (!records.isEmpty()) {
            recordNo = totalRecords;
            rn = records.get(totalRecords - 1);
        }

        System.out.println("总记录数：" + totalRecords + "，当前记录：" + recordNo);
        return rn;
    }

    // 按记录编号查询
    public String[] search(int targetRecordNo){
        ArrayList<String[]> records = readAllRecords();
        String[] rn = {"","","","","","",""};

        if (targetRecordNo < 1 || targetRecordNo > totalRecords) {
            JOptionPane.showMessageDialog(null, "查询编号无效！\n当前总记录数：" + totalRecords, "查询失败", JOptionPane.ERROR_MESSAGE);
            return rn;
        }

        recordNo = targetRecordNo;
        rn = records.get(targetRecordNo - 1);
        System.out.println("总记录数：" + totalRecords + "，查询记录：" + targetRecordNo);
        return rn;
    }

    // 按姓名字母排序（自动写入文件）
    public String[] update(){
        ArrayList<String[]> records = readAllRecords();
        String[] rn = {"","","","","","",""};

        if (records.isEmpty()) return rn;

        // 按姓名排序
        Collections.sort(records, new Comparator<String[]>() {
            @Override
            public int compare(String[] r1, String[] r2) {
                return r1[0].compareTo(r2[0]); // 按姓名排序
            }
        });

        // 写入排序后的记录（自动补充分隔符）
        writeAllRecords(records);

        // 返回第一条记录
        recordNo = 1;
        rn = records.get(0);
        System.out.println("排序完成，总记录数：" + totalRecords);
        return rn;
    }

    // 新增记录（外部调用，用于add窗口）
    public void addRecord(String[] newRecord) {
        ArrayList<String[]> records = readAllRecords();
        // 补全7个字段
        String[] record = new String[7];
        for (int i = 0; i < 7; i++) {
            record[i] = (i < newRecord.length) ? newRecord[i].trim() : "";
        }
        records.add(record);
        // 写入文件（自动补充分隔符）
        writeAllRecords(records);
        totalRecords = records.size();
    }

    // 删除记录（外部调用，用于主窗口）
    public void deleteCurrentRecord() {
        ArrayList<String[]> records = readAllRecords();
        if (recordNo < 1 || recordNo > totalRecords) return;

        // 删除当前记录
        records.remove(recordNo - 1);
        // 写入文件
        writeAllRecords(records);
        totalRecords = records.size();
        // 重置当前记录
        if (recordNo > totalRecords && totalRecords > 0) {
            recordNo = totalRecords;
        } else if (totalRecords == 0) {
            recordNo = 0;
        }
    }
}