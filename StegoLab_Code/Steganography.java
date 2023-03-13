import java.awt.Color;

public class Steganography{
    public static void clearLow(Pixel p) 
    {
        p.setRed((p.getRed() / 4) * 4);
        p.setGreen((p.getGreen() / 4) * 4);
        p.setBlue((p.getBlue() / 4) * 4);
    } 

    public static Picture testClearLow(Picture pic) {
        Picture newPic = new Picture(pic);
        Pixel[][] pixelArray = newPic.getPixels2D();

        for(int i = 0; i < newPic.getWidth()-1; i++) {
            for(int j = 0; j < newPic.getHeight()-1; j++) {
                clearLow(pixelArray[j][i]);
            }
        }
        return newPic;
    }
    
    public static void setLow (Pixel p, Color c) {
        clearLow(p);

        int getLowRed = c.getRed() / 64;
        int getLowBlue = c.getBlue() / 64;
        int getLowGreen = c.getGreen() / 64;

        p.setRed(p.getRed() + getLowRed);
        p.setGreen(p.getGreen() + getLowGreen);
        p.setBlue(p.getBlue() + getLowBlue);
    }

    public static Picture testSetLow(Picture p, Color c) {
        Picture newPic = new Picture(p);
        Pixel[][] pixelArray = newPic.getPixels2D();

        for(int i = 0; i < newPic.getWidth()-1; i++) {
            for(int j = 0; j < newPic.getHeight()-1; j++) {
                setLow(pixelArray[j][i], c);
            }
        }

        return newPic;
    }

    public static Picture revealPicture(Picture hidden) { 
        Picture copy = new Picture(hidden); 
        Pixel[][] pixels = copy.getPixels2D();
        Pixel[][] source = hidden.getPixels2D(); 
        
        for (int r = 0; r < pixels.length; r++) { 
            for (int c = 0; c < pixels[0].length; c++) { 	
                Color col = source[r][c].getColor();

                int redVal = col.getRed();
                int greenVal = col.getGreen();
                int blueVal = col.getBlue();

                pixels[r][c].setRed((redVal % 4) * 64);
                pixels[r][c].setGreen((greenVal % 4) * 64);
                pixels[r][c].setBlue((blueVal % 4) * 64);
                
                
                  
                
            }
         }
        return copy; 
    }

    public static boolean canHide(Picture source, Picture secret) {
        return ((secret.getWidth() == source.getWidth() && secret.getHeight() == source.getHeight()));
    }

    public static Picture hidePicture(Picture source, Picture secret) {
         Picture copy = new Picture(source);
        
        if(canHide(source, secret)) {
            Pixel[][] pixelArraySource = source.getPixels2D();
            Pixel[][] pixelArraySecret = secret.getPixels2D();

            for(int i = 0; i < copy.getWidth()-1; i++) {
                for(int j = 0; j < copy.getHeight()-1; j++) {
                    setLow(pixelArraySource[j][i], pixelArraySecret[j][i].getColor());
                }
            }
        }
        return copy;
    }
       


    public static void main(String[] args) {
        Picture waterPic = new Picture("beach.jpg");
        waterPic.setTitle("waterPic");
        waterPic.explore();

        Picture otherPic = new Picture("blueMotorcycle.jpg");
        otherPic.setTitle("otherPic");
        otherPic.explore();
        
        System.out.println(canHide(waterPic, otherPic));

        Picture fixPic = hidePicture(waterPic, otherPic);
        fixPic.setTitle("fixPic");
        fixPic.explore();
    
        Picture revPic = revealPicture(fixPic);
        revPic.setTitle("revPic");
        revPic.explore();
        
    }

    

}