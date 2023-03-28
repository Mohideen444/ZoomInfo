package com.zoominfo.library;

import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class MonteScreenRecorder implements IScreenRecorder {

    File file;
    ScreenRecorder screenRecorder;

    public MonteScreenRecorder(String screenrecord_path) throws IOException, AWTException {
        createFile(screenrecord_path);
        setUpScreenRecorder();
    }
    private void createFile(String file_path) throws IOException, AWTException {
        file = new File(file_path);
    }

    private void setUpScreenRecorder() throws IOException, AWTException {//Path where video recording would be stored inside project
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        System.out.println(width);
        System.out.println(height);

        Rectangle captureSize = new Rectangle(0, 0, 1920, 1080);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
                getDefaultScreenDevice()
                .getDefaultConfiguration();
        screenRecorder = new ScreenRecorder(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
                        Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                null, file);

    }
    public void startScreenRecording() throws IOException {

            screenRecorder.start();
    }

    public void stopScreenRecording() throws IOException {
        screenRecorder.stop();
    }

    public static void main(String[] args) {
        try {
            MonteScreenRecorder screenRecorderUtil = new MonteScreenRecorder("E:\\eclipse-workspace\\neutrino\\ScreenRecording");
            screenRecorderUtil.startScreenRecording();
            TimeUnit.SECONDS.sleep(5);
            screenRecorderUtil.stopScreenRecording();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
