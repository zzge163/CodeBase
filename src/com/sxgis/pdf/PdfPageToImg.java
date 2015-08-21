package com.sxgis.pdf;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.ImageIOUtil;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PdfPageToImg {
	public static void main(String[] args) {
		pdfToImage("D:/test/tank.pdf","D:/test/");
	}

	/**
	 * PDFBOX转图片
	 * 
	 * @param pdfUrl
	 *            pdf的路径
	 * @param imgTempUrl
	 *            图片输出路径
	 */
	public static void pdfToImage(String pdfUrl, String imgTempUrl) {
		try {
			// 读入PDF
			PdfReader pdfReader = new PdfReader(pdfUrl);
			// 计算PDF页码数
			int pageCount = pdfReader.getNumberOfPages();
			// 循环每个页码
			for (int i = pageCount; i >= pdfReader.getNumberOfPages(); i--) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				PdfStamper pdfStamper = null;
				PDDocument pdDocument = null;

				pdfReader = new PdfReader(pdfUrl);
				pdfReader.selectPages(String.valueOf(i));
				pdfStamper = new PdfStamper(pdfReader, out);
				pdfStamper.close();
				// 利用PdfBox生成图像
				pdDocument = PDDocument.load(new ByteArrayInputStream(out
						.toByteArray()));
				OutputStream outputStream = new FileOutputStream(imgTempUrl
						+ "ImgName" + "-" + i + ".bmp");

				ImageOutputStream output = ImageIO
						.createImageOutputStream(outputStream);
				PDPage page = (PDPage) pdDocument.getDocumentCatalog()
						.getAllPages().get(0);
				BufferedImage image = page.convertToImage(
						BufferedImage.TYPE_INT_RGB, 96);
				ImageIOUtil.writeImage(image, "bmp", outputStream, 96);
				if (output != null) {
					output.flush();
					output.close();
				}
				pdDocument.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}