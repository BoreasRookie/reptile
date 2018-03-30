package com.boreas;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.regex.Pattern;

public class CloneImage {
    private static final String CELL ="cells";
    private static final String F_CELL ="cell_first_cell";
    private static final String L_CELL ="cell_loarzy_cells";
    private static int count =0;
    public static void main(String[] args) {
        File file = new File("D:/imgsourcesfile/tooopen.com.html");
        String charset = "UTF-8";
        parseHtml(file, charset);
    }

    private static void parseHtml(File file, String charsetName) {
        try {
            Document document = Jsoup.parse(file, charsetName);
            Document docSub;
            Document cellSub;
            Element element = document.getElementById("container");
//            System.out.println("element :" + element.toString());
//            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Elements elements = element.getElementsByClass("waterfall_column");
            for (int i = 0; i < elements.size(); i++) {
                docSub = Jsoup.parse(elements.get(i).toString());
                Elements cells = docSub.getElementsByClass("cell");
                Elements cell_loarzy_cells = docSub.getElementsByClass("cell loarzy-cell");
                Elements cell_first_cell = docSub.getElementsByClass("cell first-cell");
//                System.out.println("cells size ；" + cells.size()
//                        + "\t\t cell_loarzy_cells :" + cell_loarzy_cells.size()
//                        + "\t\t cell_first_cell :" + cell_first_cell.size());
//                System.out.println("-------------------------------------------------waterfall_column------------------------------------------------------");
                parseCell(cells,CELL);
                parseCell(cell_loarzy_cells,L_CELL);
                parseCell(cell_first_cell,F_CELL);
            }
            System.out.println("img count :" + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseCell(Elements elements,String tag) {
        for (int i = 0; i < elements.size(); i++) {
            System.out.println("----------------------------------------------------------------"+tag+"------------------------------------------------------------");
//            System.out.println(tag + "\t\t" + elements.get(i).toString());
            Element element =  elements.get(i);
            Elements a_tag = element.getElementsByClass("pic");
//            System.out.println("a_tag :"+a_tag.toString());
            Elements title = a_tag.select("a");
            System.out.println("图片标题：" + title.text());
            Elements src = a_tag.select("img");
            String url = src.attr("data-src");
            System.out.println("图片地址：" + url);
            if(null != url && !"".equals(url)){
                count++;
            }
        }
    }


//    public static void main(String[] args) throws MalformedURLException {
//        URL url = null;
//        URLConnection urlConnection;
//        BufferedReader br = null;
//        PrintWriter pw = null;
//        String regex = "http://[\\w+\\.?/?]+\\.[A-Za-z]+";
//        Pattern p = Pattern.compile(regex);
//        StringBuilder sb = new StringBuilder();
//        try {
//            url = new URL("http://www.tooopen.com/img/87_312.aspx");
//            urlConnection = url.openConnection();
//            pw = new PrintWriter(new FileWriter("D:/url.txt"), true);//这里我们把收集到的链接存储在了E盘底下的一个叫做url的txt文件中
//            br = new BufferedReader(new InputStreamReader(
//                    urlConnection.getInputStream()));
//            String buf = null;
//            while ((buf = br.readLine()) != null) {
//
////                sb.append(buf);
//                boolean isExist = buf.contains("<img src=");
//                if(isExist){
//                    System.out.println(buf);
//                }
//            }
//            System.out.println("-----------------------------------------------------------------------------------------------------------");
////            System.out.println("---------" + sb.toString());
//           // parseHtml(sb.toString());
////            System.out.println("获取成功！");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                br.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            pw.close();
//        }
//    }
//    private static void parseHtml(String htmltest){
//        String html = htmltest;
//        Document doc = Jsoup.parse(html);
//        Elements rows = doc.select("imglist clearfix pageNum0");
//        if (rows.size() == 1) {
//            System.out.println("没有结果");
//        }else {
//            System.out.println("--------------------------- 查询结果 ---------------------------");
//            Elements e = rows.select("li");
//            String url = e.select("data-thumburl").text();
//            System.out.println("图片地址：" + url);
//        }
//    }
}
