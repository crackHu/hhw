package com.vds.app.util;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jbarcode.JBarcode;
import org.jbarcode.JBarcodeFactory;
import org.jbarcode.paint.TextPainter;
import org.jbarcode.util.ImageUtil;
 
public class BarcodeUtil {

	private static final int BARCODE_HEIGHT = 12;
	private static final int BARCODE_DPI = ImageUtil.DEFAULT_DPI;
	private static final String FONT_FAMILY = "consola";
	private static final int FONT_SIZE = 12;

	private static JBarcode jbc = null;

	static JBarcode getJBarcode() {
		if (jbc == null) {
			jbc = JBarcodeFactory.getInstance().createCode93();
			jbc.setTextPainter(CustomTextPainter.getInstance());
			jbc.setBarHeight(BARCODE_HEIGHT);
		}
		return jbc;
	}

	public static void createBarcode(String message, File file) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			createBarcode(message, fos);
			fos.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void createBarcode(String message, OutputStream os) {
		try {
			BufferedImage image = getJBarcode().createBarcode(message);
			ImageUtil.encodeAndWrite(image, ImageUtil.PNG, os, BARCODE_DPI, BARCODE_DPI);
			os.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 自定义的 TextPainter， 允许定义字体，大小等等。
	 */
	static class CustomTextPainter implements TextPainter {
		public static CustomTextPainter instance = new CustomTextPainter();

		public static CustomTextPainter getInstance() {
			return instance;
		}

		@Override
		public void paintText(BufferedImage barCodeImage, String text, int width) {
			Graphics g2d = barCodeImage.getGraphics();

			Font font = new Font(FONT_FAMILY, Font.PLAIN, FONT_SIZE * width);
			g2d.setFont(font);
			FontMetrics fm = g2d.getFontMetrics();
			int height = fm.getHeight();
			int center = (barCodeImage.getWidth() - fm.stringWidth(text)) / 2;

			g2d.setColor(Color.WHITE);
			g2d.fillRect(0, 0, barCodeImage.getWidth(), barCodeImage.getHeight() * 1 / 20);
			g2d.fillRect(0, barCodeImage.getHeight() - (height * 9 / 10), barCodeImage.getWidth(), (height * 9 / 10));
			g2d.setColor(Color.BLACK);
			g2d.drawString(text, center, barCodeImage.getHeight() - (height / 10) - 2);
		}
	}

	public static void main(String[] args) {
		BarcodeUtil.createBarcode("123456789012", new File("c:\\1.png"));
	}
}