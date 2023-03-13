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

    public static Picture keepOnlyBlue(Picture p){
        Picture newPic = new Picture(p);
        Pixel[][] pixels = newPic.getPixels2D();

        for(Pixel[] row : pixels) {
            for(Pixel actualPixel : row) {
                actualPixel.setRed(0);
                actualPixel.setGreen(0);
            }
        }
        return newPic;
    }

    public static Picture negate(Picture p){
        Picture newPic = new Picture(p);
        Pixel[][] pixels = newPic.getPixels2D();

        for(Pixel[] row : pixels) {
            for(Pixel actualPixel : row) {
                actualPixel.setRed(255 - actualPixel.getRed());
                actualPixel.setGreen(255 - actualPixel.getGreen());
                actualPixel.setBlue(255 - actualPixel.getBlue());
            }
        }
        return newPic;
    }

    public static Picture grayScale(Picture p){
        Picture newPic = new Picture(p);
        Pixel[][] pixels = newPic.getPixels2D();

        for(Pixel[] row : pixels) {
            for(Pixel actualPixel : row) {
                int average = (actualPixel.getRed() + actualPixel.getGreen() + actualPixel.getBlue()) / 3;
                actualPixel.setRed(average);
                actualPixel.setGreen(average);
                actualPixel.setBlue(average);
            }
        }
        return newPic;
    }

public static Picture fixUnderwater(Picture p ){
    Picture newPic = new Picture(p);
    Pixel[][] pixels = newPic.getPixels2D();

    for(Pixel[] row : pixels) {
        for(Pixel actualPixel : row) {
            if (actualPixel.getRed() < 20){
                actualPixel.setRed(255);
            }
        }
    }
    return newPic;

}

    public static void main(String[] args) {

        Picture waterPic = new Picture("water.jpg");
        waterPic.explore();

        // Picture beachPicNoBlue = zeroBlue(beachPic);
        // beachPicNoBlue.explore();

        // Picture beachPicOnlyBlue = keepOnlyBlue(beachPic);
        // beachPicOnlyBlue.explore();

        // Picture negativePic = negate(beachPic);
        // negativePic.explore();

        // Picture grayPic = grayScale(beachPic);
        // grayPic.explore();

        Picture fixPix = fixUnderwater(waterPic);
        fixPix.explore();
    }
    
}
