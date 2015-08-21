package com.sxgis.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;


public class CreatePdfImage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exportImg();
	}

	public static void exportImg() {
        Document document=null;
        try {
//            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 设置中文字体
//            Font headFont = new Font(bfChinese, 10, Font.NORMAL);// 设置字体大小
            
            document = new Document(); 
            PdfWriter.getInstance(document, new FileOutputStream("D:/test/img.pdf"));
            //设定文档的作者
            document.addAuthor("林计钦"); //测试无效
            document.open();
            document.add(new Paragraph("你好，Img！"));
            //读取一个图片
            Image image = Image.getInstance("D:/test/1.jpg");
            //插入一个图片
            document.add(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(document!=null){
                document.close();
            }
        }
    }
}
