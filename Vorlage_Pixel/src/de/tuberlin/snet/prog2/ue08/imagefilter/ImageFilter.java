package de.tuberlin.snet.prog2.ue08.imagefilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Class for showing and manipulating images.
 * @author prog2-team
 *
 */
public class ImageFilter extends Application {

	final static String TU_LOGO = "file:TU_Logo_kurz_RGB_schwarz.jpeg";
	final static String PIC1 = "file:pic1.jpg";
	final static String PIC2 = "file:pic2.bmp";
	
	/** max x-coordinate + 1 */
	int width;
	/** max y-coordinate + 1 */
	int height;
	
	public static void main(String[] args) throws IOException {
		ImageFilter.launch(args);
	}

    @Override
    public void start(Stage primaryStage) {
        
    	// load and set the image to the view
        Image image = new Image(TU_LOGO);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        
        width = (int)image.getWidth();
        height = (int)image.getHeight();
        
        // transform image to collection of pixels
        PixelReader pixelReader = image.getPixelReader();
        Collection<Pixel> imagePixels;
        imagePixels = readImage(pixelReader);
        // manipulate image
        imagePixels = manipulateImage(imagePixels);

        // TODO: erzeuge und manipuliere 2d-Array
        
        // write writableImage via pixelWriter
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        imagePixels.forEach( p -> pixelWriter.setColor(p.x, p.y, p.color) );
        
        // set the manipulated image to the view
        ImageView destImageView = new ImageView();
        destImageView.setImage(writableImage);
        
        FlowPane root = new FlowPane(Orientation.VERTICAL);
        root.getChildren().addAll(imageView, destImageView);
        Scene scene = new Scene(root);
        primaryStage.setTitle("TU-Filter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * manipulates the the given pixels with diverse mathods
     * @param imagePixels
     * @return manipulated image pixels
     */
    private Collection<Pixel> manipulateImage(Collection<Pixel> imagePixels) {
    	Collection<Pixel> filteredImagePixels;
        // TODO: apply filter methods
        filteredImagePixels = copyPixels(imagePixels);
		return filteredImagePixels;
	}
    

    /**
     * Creates a collection of pixels.
     * @param pixelReader
     * @return collection of pixels
     */
	private Collection<Pixel> readImage(PixelReader pixelReader) {
    	ArrayList<Pixel> imagePixels = new ArrayList<Pixel>();
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                imagePixels.add(new Pixel(x, y, pixelReader.getColor(x, y)));
            }
        }
		return imagePixels;
	}
    

    /**
     * Creates a copy of the given collection of pixels.
     * @param imagePixels
     * @return copy of imagePixels
     */
	private Collection<Pixel> copyPixels(Collection<Pixel> imagePixels) {
    	Collection<Pixel> filteredImagePixels;
    	filteredImagePixels = imagePixels.stream()
    			.map(p -> p)
    			.collect(Collectors.toCollection(ArrayList::new));
    	return filteredImagePixels;
    }
	

}
