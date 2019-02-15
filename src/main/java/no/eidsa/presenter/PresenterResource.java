package no.eidsa.presenter;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

@RestController
public class PresenterResource {

    private final Robot robot;
    private final ScreenService screenService;

    public PresenterResource(ScreenService screenService) throws AWTException {
        this.screenService = screenService;
        robot = new Robot();
    }

    @GetMapping("/api/next")
    public String nextSlide(@RequestParam(name = "image", defaultValue = "true") Boolean image) throws InterruptedException, IOException {
        robot.keyPress(KeyEvent.VK_RIGHT);
        Thread.sleep(10);
        robot.keyRelease(KeyEvent.VK_RIGHT);
        Thread.sleep(500);

        if (image) {
            return screenService.captureScreen();
        } else {
            return "";
        }
    }

    @GetMapping("/api/prev")
    public String prevSlide(@RequestParam(name = "image", defaultValue = "true") Boolean image) throws InterruptedException, IOException {
        robot.keyPress(KeyEvent.VK_LEFT);
        Thread.sleep(10);
        robot.keyRelease(KeyEvent.VK_LEFT);

        if (image) {
            return screenService.captureScreen();
        } else {
            return "";
        }
    }

    @RequestMapping(value = "/image/{id:.+}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id")String id, HttpServletResponse response) {

        byte[] image = screenService.getImage(id);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        return ResponseEntity.ok(image);
    }

}
