import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

class GameImage extends Component {
    private int numlocs = 2;
    private int numcells = numlocs * numlocs;
    private int[] cells;
    private BufferedImage bi;
    int w, h, cw, ch;
    public GameImage(File imageSrc) {
        System.out.println("AYYYYEEE "+imageSrc);
        try {
            bi = ImageIO.read(imageSrc);
            w = bi.getWidth(null);
            h = bi.getHeight(null);
        } catch (IOException e) {
            System.out.println("Image could not be read");
        }
        cw = w / numlocs;
        ch = h / numlocs;
        cells = new int[numcells];
        for (int i = 0; i < numcells; i++) {
            cells[i] = i;
        }
    }
    public Dimension getPreferredSize() {
        return new Dimension(w, h);
    }
    public void paint(Graphics g) {
        int dx, dy;
        for (int x = 0; x < numlocs; x++) {
            int sx = x * cw;
            for (int y = 0; y < numlocs; y++) {
                int sy = y * ch;
                int cell = cells[x * numlocs + y];
                dx = (cell / numlocs) * cw;
                dy = (cell % numlocs) * ch;
                g.drawImage(bi, dx, dy, dx + cw, dy + ch, sx, sy, sx + cw, sy + ch, null);
            }
        }
    }
}