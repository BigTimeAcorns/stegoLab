import java.awt.Color;
import java.util.ArrayList;

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
        return ((secret.getWidth() <= source.getWidth() && secret.getHeight() <= source.getHeight()));
    }

    public static Picture hidePicture(Picture source, Picture secret) {
         Picture copy = new Picture(source);
        
        if(canHide(source, secret)) {
            Pixel[][] pixelArrayCopy = copy.getPixels2D();
            Pixel[][] pixelArraySecret = secret.getPixels2D();

            for(int i = 0; i < copy.getWidth()-1; i++) {
                for(int j = 0; j < copy.getHeight()-1; j++) {
                    setLow(pixelArrayCopy[j][i], pixelArraySecret[j][i].getColor());
                }
            }
        }
        return copy;
    }

    public static Picture hidePicture(Picture source, Picture secret, int startRow, int startColumn) {
         Picture copy = new Picture(source);
        
        if(canHide(source, secret)) {
            Pixel[][] pixelArrayCopy = copy.getPixels2D();
            Pixel[][] pixelArraySecret = secret.getPixels2D();

            for(int i = startColumn; i < copy.getWidth()-1; i++) {
                for(int j = startRow; j < copy.getHeight()-1; j++) {
                    setLow(pixelArrayCopy[j][i], pixelArraySecret[j][i].getColor());
                }
            }
        }
        return copy;
    }

    public static boolean isSame(Picture pic1, Picture pic2) {
        return (pic1.equals(pic2));
    }

    public static ArrayList<Integer> findDifferences(Picture pic1, Picture pic2) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        if(!(pic1.getWidth() != pic2.getWidth() || pic1.getHeight() != pic2.getHeight())) {
            Pixel[][] pixelArrayPic1 = pic1.getPixels2D();
            Pixel[][] pixelArrayPic2 = pic2.getPixels2D();

            for(int i = 0; i < pic1.getWidth()-1; i++) {
                for(int j = 0; j < pic1.getHeight()-1; j++) {
                    if (pixelArrayPic1[j][i].getColor().equals(pixelArrayPic2[j][i].getColor())) {
                        list.add(i);
                        list.add(j);
                    }
                }
            } 
        } 
        return list;
    }

    public static Picture showDifferentArea(Picture p, ArrayList<Integer> list) {
        Picture pic = new Picture(p);
        Pixel[][] pixelArray = pic.getPixels2D();
        
        for(int i = 0; i < list.size(); i += 2) {
            for(int j = 1; j < list.size()+1; j += 2) {
                pixelArray[list.get(j)][list.get(i)].setColor(Color.PINK);
            }
        }
        return pic;
    }
        
        
       


    public static void main(String[] args) {
        Picture waterPic = new Picture("beach.jpg");
        waterPic.setTitle("waterPic");
        waterPic.explore();

        Picture otherPic = new Picture("blueMotorcycle.jpg");
        otherPic.setTitle("otherPic");
        otherPic.explore();
    
        Picture fixPic = hidePicture(waterPic, otherPic, 100, 100);
        fixPic.setTitle("fixPic");
        fixPic.explore();
    
        Picture revPic = revealPicture(fixPic);
        revPic.setTitle("revPic");
        revPic.explore();
        
    }

    

}