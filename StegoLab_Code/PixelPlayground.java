import java.awt.Color;

public class PixelPlayground {

    public static Picture zeroBlue(Picture p) {
        Picture newPic = p;
        Pixel[][] pixels = newPic.getPixels2D();

        for(Pixel[] row : pixels) {
            for(Pixel actualPixel : row) {
                actualPixel.setBlue(0);
            }
        }
        return newPic;


    }

    public static void main(String[] args)

 {

Picture beachPic = new Picture("beach.jpg");

beachPic.explore();

Picture beachPicNoBlue = zeroBlue(beachPic);

beachPicNoBlue.explore();

 }
    
}
