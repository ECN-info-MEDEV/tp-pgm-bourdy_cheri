package edu.ecn.medev2021;

import org.junit.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PgmReaderTest {
    private static PgmReader reader;
    private static int[][] image;

    @BeforeClass
    public static void setUp() throws Exception {
        System.out.println("Initializing !");
        reader = new PgmReader();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        System.out.println("Tests finished !");
        File temp = new File("temptest.pgm");
        temp.delete();
    }

    @Test
    public void readPgmFile() throws FileNotFoundException {
        image = reader.readPgmFile(new File("test_case.pgm"));
        int[][] correct = new int[][] {new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {0, 255, 255, 255, 255, 255, 255, 255, 255, 0},
                new int[] {0, 255, 255, 255, 255, 255, 255, 255, 255, 0},
                new int[] {0, 255, 255, 255, 255, 255, 255, 255, 255, 0},
                new int[] {0, 255, 255, 255, 50, 50, 255, 255, 255, 0},
                new int[] {0, 255, 255, 255, 50, 50, 255, 255, 255, 0},
                new int[] {0, 255, 255, 255, 255, 255, 255, 255, 255, 0},
                new int[] {0, 255, 255, 255, 255, 255, 255, 255, 255, 0},
                new int[] {0, 255, 255, 255, 255, 255, 255, 255, 255, 0},
                new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                };
        Assert.assertArrayEquals(correct, image);
    }

    @Test
    public void resizePgmFile() throws FileNotFoundException {
        int[][] nimg = reader.resizeImage(image, 3, 3);
        int[][] correct = new int[][] {new int[] {0, 0, 0},
                new int[] {0, 255, 255},
                new int[] {0, 255, 255}
        };
        Assert.assertArrayEquals(correct, nimg);
    }

    @Test
    public void getHistogram() {
        int nb255 = 8*8-4;
        int nb50 = 4;
        int nb0 = 10*4-4;
        int[] correct = new int[256];
        correct[255] = nb255;
        correct[50] = nb50;
        correct[0] = nb0;

        int[] histo = reader.getHistogram(image);
        Assert.assertArrayEquals(correct, histo);
    }

    @Test
    public void difference() {
        int[][] other = new int[][] {new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {0, 255, 255, 255, 255, 255, 255, 255, 255, 0},
                new int[] {0, 255, 255, 255, 255, 255, 255, 255, 255, 0},
                new int[] {0, 255, 255, 255, 255, 255, 255, 255, 255, 0},
                new int[] {0, 255, 255, 255, 100, 100, 255, 255, 255, 0},
                new int[] {0, 255, 255, 255, 100, 100, 255, 255, 255, 0},
                new int[] {0, 255, 255, 255, 255, 255, 255, 255, 255, 0},
                new int[] {0, 255, 255, 255, 255, 255, 255, 255, 255, 0},
                new int[] {0, 255, 255, 255, 255, 255, 255, 255, 255, 0},
                new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] correct = new int[][] {new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {0, 0, 0, 0, -50, -50, 0, 0, 0, 0},
                new int[] {0, 0, 0, 0, -50, -50, 0, 0, 0, 0},
                new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] nimg = reader.difference(image, other);
        Assert.assertArrayEquals(correct, nimg);
    }

    @Test
    public void saveAndLoad() throws IOException {
        PgmGenerator gen = new PgmGenerator();
        File temp = new File("temptest.pgm");
        gen.writePgmImage(temp, image);
        int[][] loaded = reader.readPgmFile(temp);
        Assert.assertArrayEquals(image, loaded);
    }
}