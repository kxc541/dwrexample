package com.g4g.utils;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.log4j.Level;

import com.g4g.delegator.AvatarsServiceDelegator;
import com.g4g.dto.AvatarsDTO;

/**
 * @author ankur
 *
 */
public class ImageUtils {

	/**
	 * @param imagefile
	 * @param height
	 * @param width
	 * @return etPNGImageByteArrayFromImage(resize(toBufferedImage(imageObject(imagefile)),
	 *         height, width))
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static byte[] getPngFormatImageByteArrayAfterResize(byte[] imagefile, int height, int width) throws IOException,InterruptedException,NullPointerException {
		return getPNGImageByteArrayFromImage(resize(toBufferedImage(imageObject(imagefile)), height, width));
	}

	/**
	 * @param image
	 * @return byte[]
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static byte[] getPNGImageByteArrayFromImage(Image image)throws IOException, InterruptedException,NullPointerException {
		StringBuffer filename = new StringBuffer(G4GConstants.IMAGEUTILS_TEMPIMAGE);
		byte[] array = null;
		synchronized (filename) {
			RenderedImage rendImage = toBufferedImage(image);
			ImageIO.write(rendImage, G4GConstants.IMAGE_TYPE, new File(filename.toString()));
			array = getBytesFromFile(new File(filename.toString()));
		}
		return array;
	}

	/**
	 * @param image
	 */
	// shows image in a frame Test method
	public static void showimage(Image image) throws NullPointerException {
		JFrame frame = new JFrame();
		JLabel label = new JLabel(new ImageIcon(image));
		frame.getContentPane().add(label, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}

	// This method returns a buffered image with the contents of an image
	/**
	 * @param image
	 * @return BufferedImage)image
	 * @throws InterruptedException
	 */
	public static BufferedImage toBufferedImage(Image image)throws InterruptedException,NullPointerException {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}

		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();

		// Determine if the image has transparent pixels; for this method's
		// implementation, see e661 Determining If an Image Has Transparent
		// Pixels
		boolean hasAlpha = hasAlpha(image);

		// Create a buffered image with a format that's compatible with the
		// screen
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
			if (hasAlpha) {
				transparency = Transparency.BITMASK;
			}

			// Create the buffered image
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null), image
					.getHeight(null), transparency);
		} catch (HeadlessException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,new StringBuffer(ImageUtils.class.getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(
							G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES).append(e.getMessage()).toString(),Level.ERROR);
			throw e;
		}

		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			if (hasAlpha) {
				type = BufferedImage.TYPE_INT_ARGB;
			}
			bimage = new BufferedImage(image.getWidth(null), image
					.getHeight(null), type);
		}

		// Copy image to buffered image
		Graphics g = bimage.createGraphics();

		// Paint the image onto the buffered image
		g.drawImage(image, G4GConstants.ZERO, G4GConstants.ZERO, null);
		g.dispose();
		return bimage;
	}

	// This method returns true if the specified image has transparent pixels
	/**
	 * @param image
	 * @return bimage.getColorModel().hasAlpha()
	 * @throws InterruptedException
	 */
	public static boolean hasAlpha(Image image) throws InterruptedException,NullPointerException {
		// If buffered image, the color model is readily available
		if (image instanceof BufferedImage) {
			BufferedImage bimage = (BufferedImage) image;
			return bimage.getColorModel().hasAlpha();
		}

		// Use a pixel grabber to retrieve the image's color model;
		// grabbing a single pixel is usually sufficient
		PixelGrabber pg = new PixelGrabber(image, G4GConstants.ZERO,
				G4GConstants.ZERO, G4GConstants.ONE_NUMBER,
				G4GConstants.ONE_NUMBER, false);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(ImageUtils.class.getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							G4GConstants.CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(e.getMessage()).toString(),
					Level.ERROR);
		}

		// Get the image's color model
		ColorModel cm = pg.getColorModel();
		return cm.hasAlpha();
	}

	// This methods check if user had enter image only
	// Supported format GIF, PNG, and JPEG Will get this
	/**
	 * @param imagearray
	 * @return boolean
	 */
	public static boolean isImage(byte[] imagearray) throws NullPointerException {
		try {
			return ImageIO.read(new ByteArrayInputStream(imagearray)) != null;
		} catch (IOException e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(ImageUtils.class.getName()).append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(e.getMessage()).toString(),Level.ERROR);
			return false;
		}
	}

	/**
	 * @param imagearray
	 * @return Image
	 * @throws IOException
	 */
	public static Image imageObject(byte[] imagearray) throws IOException,NullPointerException {
		return ImageIO.read(new ByteArrayInputStream(imagearray));
	}

	/**
	 * @param file
	 * @return bytes
	 * @throws IOException
	 */
	// Returns the contents of the file in a byte array.
	public static byte[] getBytesFromFile(File file) throws IOException,NullPointerException {
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();


		// Create the byte array to hold the data

		byte[] bytes = new byte[Integer.parseInt(Long.toString(length))];

		// Read in the bytes
		int offset = G4GConstants.ZERO;
		int numRead = G4GConstants.ZERO;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= G4GConstants.ZERO) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException(G4GConstants.COULD_NOT_COMPLETELY_READ_FILE
					+ file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

	private static BufferedImage resize(BufferedImage image, int width,int height) throws NullPointerException {
		int t = image.getType() == G4GConstants.ZERO ? BufferedImage.TYPE_INT_ARGB: image.getType();
		BufferedImage resizedImg = new BufferedImage(width, height, t);
		Graphics2D gr = resizedImg.createGraphics();
		gr.setComposite(AlphaComposite.Src);
		gr.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		gr.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		gr.drawImage(image, G4GConstants.ZERO, G4GConstants.ZERO, width,height, null);
		gr.dispose();
		return resizedImg;
	}

	/**
	 * Test Method
	 *
	 * @param directoryname
	 * @throws IOException
	 */
	public static void uploadAvatarsInAvatarTableFromADirectory(String directoryname) throws IOException,NullPointerException {
		File dir = null;
		if (directoryname == null) {
			// $ANALYSIS-IGNORE
			dir = new File(
					"C:/Tools/Workspaces/workspace-G4GStruts/G4GOrigin/WebRoot/WebContent/wg/images/avatar");
		} else {
			dir = new File(directoryname);
		}
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File element : files) {
				if (element.isFile()) {
					AvatarsDTO dto = AvatarsServiceDelegator
							.getAvatars(G4GConstants.TEN_NUMBER);
					if (dto == null) {
						dto = new AvatarsDTO();
					}
					dto
							.setImage(getBytesFromFile(new File(
									"C:/Documents and Settings/Administrator.TEMP/Desktop/New Avatars/rodrigoAVTR.jpg")));
					try {
						AvatarsServiceDelegator.update(dto);
					} catch (Exception exception) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_HIBERNATE,
								new StringBuffer(ImageUtils.class.getName()).append(
										G4GConstants.COLON_WITH_SPACES).append(
										G4GConstants.CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										G4GConstants.CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										G4GConstants.DASHES).append(
												exception.getMessage()).toString(),
								Level.ERROR);
					}
				}
			}
			dir = null;
			System.gc();
		}
	}
}
