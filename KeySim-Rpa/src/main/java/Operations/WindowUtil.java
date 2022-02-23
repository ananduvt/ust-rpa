/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;


/**
 * Contains some utility functions for window oriented tasks
 *
 * @author u55369
 */
public class WindowUtil {

    /**
     *
     * @return Returns The Current window in focus
     */
    public static String getWindowTitle() {
        char[] buffer = new char[1024 * 2];
        WinDef.HWND hwnd = null;

        hwnd = User32.INSTANCE.GetForegroundWindow();
        User32.INSTANCE.GetWindowText(hwnd, buffer, 1024);
        return Native.toString(buffer);
    }

    public static void setClipBoardContents(String value) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        clipboard.setContents(new StringSelection(value), null);
    }

    public static String getClipBoardContents() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String value = null;
        try {
            value = (String) clipboard.getData(DataFlavor.stringFlavor);

        } catch (UnsupportedFlavorException | IOException ex) {
            System.err.println("Error in fetching clip board contents");
        }
        return value;
    }

    public static String getScreenValue(int x1, int y1, int x2, int y2) {
        picCrop(x1, y1, x2, y2);
        return getText();
    }

    private static void picCrop(int x1, int y1, int x2, int y2) {
        try {
            Robot robot = new Robot();
            String format = "jpg";
            String fileName = "FullScreenshot." + format;

            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, format, new File(fileName));

            System.out.println("A full screenshot saved!");
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }

        try {
            File imageFile = new File("FullScreenshot.jpg");
            BufferedImage bufferedImage = ImageIO.read(imageFile);

            int x = x1;
            int y = y1;
            int width = Math.abs(x1 - x2);
            int height = Math.abs(y1 - y2);

            BufferedImage croppedImage = bufferedImage.getSubimage(x, y, width, height);

            File pathFile = new File("image-crop.jpg");
            ImageIO.write(croppedImage, "jpg", pathFile);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String getText()  {

        File imageFile = new File("image-crop.jpg");
        Tesseract instance = new Tesseract();
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");

//Set the tessdata path
        instance.setDatapath(tessDataFolder.getAbsolutePath());
        System.out.println(tessDataFolder.getAbsolutePath());
        String result = "null";
        try {
            result = instance.doOCR(imageFile);
          
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }

//        BytePointer outText;
//
//        TessBaseAPI api = new TessBaseAPI();
//        // Initialize tesseract-ocr with English, without specifying tessdata path
//        
//        if (api.Init(".", "ENG") != 0) {
//            System.err.println("Could not initialize tesseract.");
//            System.exit(1);
//        }
//
//        // Open input image with leptonica library
//        PIX image = pixRead("image-crop.jpg");
//        api.SetImage(image);
//        // Get OCR result
//        outText = api.GetUTF8Text();
//
//        String string = outText.getString();
//        // System.out.println("OCR output:\n" + string);
//
//        // Destroy used object and release memory
//        api.End();
//        outText.deallocate();
//        pixDestroy(image);
        return result;
    }

}
