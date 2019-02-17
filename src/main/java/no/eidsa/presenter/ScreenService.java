package no.eidsa.presenter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ScreenService {

    private final PresenterProperties presenterProperties;

    private final Robot robot;

    public ScreenService(PresenterProperties presenterProperties) throws AWTException {
        this.presenterProperties = presenterProperties;
        this.robot = new Robot();
    }

    public String captureScreen() {
        BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

        String timeMillis = String.valueOf(System.currentTimeMillis());

        Path path = Paths.get(presenterProperties.getTemp(), timeMillis + ".png");

        try {
            Path tempFolder = Paths.get(presenterProperties.getTemp());

            if (tempFolder.toFile().exists() && presenterProperties.getCleanTemp()) {
                FileUtils.cleanDirectory(tempFolder.toFile());
            } else {
                Files.createDirectories(tempFolder);
            }

            ImageIO.write(image, "png", path.toFile());
            return timeMillis + ".png";
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file");
        }

    }

    public byte[] getImage(String id) {
        Path path = Paths.get(presenterProperties.getTemp(), id);

        try (InputStream in = new FileInputStream(path.toFile())) {
            return IOUtils.toByteArray(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to find file");
        }
    }
}
