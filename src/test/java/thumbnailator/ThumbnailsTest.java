package thumbnailator;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.filters.Watermark;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ThumbnailsTest
 * @description TODO
 * @date 2023/9/7
 */
public class ThumbnailsTest {
    @Test
    void test() throws IOException {
        //  Thumbnails.of(new File("C:\\Users\\zhengzuozhang\\Pictures\\1.jpg")).asBufferedImage();
        BufferedImage image = ImageIO.read(new FileInputStream("C:\\Users\\zhengzuozhang\\Pictures\\3.jpg"));
        Thumbnails.of(image).size(400, 400);
        Thumbnails.of(new File("C:\\Users\\zhengzuozhang\\Pictures\\1.jpg"))
                .size(400, 400)
                .watermark(new Watermark(Positions.CENTER, Thumbnails.of(image).size(400, 400).asBufferedImage(), 0.3f))
                .toFile(new File("C:\\Users\\zhengzuozhang\\Pictures\\1-1.jpg"));
    }

    @Test
    void test1() {
    }
}
