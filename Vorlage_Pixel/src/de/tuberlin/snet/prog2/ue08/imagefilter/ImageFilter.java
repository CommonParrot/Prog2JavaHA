package de.tuberlin.snet.prog2.ue08.imagefilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import akka.japi.Option;
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
 * 
 * @author prog2-team
 *
 */
public class ImageFilter extends Application {

	final static String TU_LOGO = "file:C:\\Users\\Jakob\\Pictures\\Saved Pictures\\466544911337116586_1448449608.jpg";
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

		width = (int) image.getWidth();
		height = (int) image.getHeight();

		// transform image to collection of pixels
		PixelReader pixelReader = image.getPixelReader();
		Collection<Pixel> imagePixels;
		imagePixels = readImage(pixelReader);
		// manipulate image
		// imagePixels = manipulateImage(imagePixels);

		// TODO: erzeuge und manipuliere 2d-Array
		ImageArray pixelsArray = new ImageArray(image.getHeight(), image.getWidth());
		imagePixels.stream().forEach(x -> pixelsArray.add(x));
		synchronized(pixelsArray){
		manipulateImage(pixelsArray).stream().forEach(x -> pixelsArray.add(x));
		}
		// write writableImage via pixelWriter
		WritableImage writableImage = new WritableImage(width, height);
		PixelWriter pixelWriter = writableImage.getPixelWriter();
		pixelsArray.forEach(p -> pixelWriter.setColor(p.x, p.y, p.color));

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
	 * manipulates the the given pixels with diverse methods
	 * 
	 * @param imagePixels
	 * @return manipulated image pixels
	 */
	private Collection<Pixel> manipulateImage(ImageArray imagePixels) {
		for (int i = 0; i < imagePixels.size(); ++i) {

			ArrayList<Color> colors = new ArrayList<>();
			
			tryAddColor(colors,imagePixels.get(imagePixels.get(i).x - 1, imagePixels.get(i).y));
			tryAddColor(colors,imagePixels.get(imagePixels.get(i).x + 1, imagePixels.get(i).y));
			tryAddColor(colors,imagePixels.get(imagePixels.get(i).x, imagePixels.get(i).y - 1));
			tryAddColor(colors,imagePixels.get(imagePixels.get(i).x, imagePixels.get(i).y + 1));

			double blue = 0;
			double green = 0;
			double red = 0;
			double opacity = 0;

			for (Color x : colors) {

				blue = blue + x.getBlue();
				green = green + x.getGreen();
				red = red + x.getRed();
				opacity = opacity + x.getOpacity();

			}

			Color neue = new Color(red / colors.size(), green / colors.size(), blue / colors.size(),
					opacity / colors.size());

			imagePixels.get(i).color = neue;
		}

		return imagePixels;

	}


	public void tryAddColor(Collection<Color> a, Pixel p) {

		if (p.x!=-1 && p.y != -1) {
			a.add(p.getColor());
		} else {
		}

	}

	/**
	 * Creates a collection of pixels.
	 * 
	 * @param pixelReader
	 * @return collection of pixels
	 */
	private Collection<Pixel> readImage(PixelReader pixelReader) {
		ArrayList<Pixel> imagePixels = new ArrayList<Pixel>();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				imagePixels.add(new Pixel(x, y, pixelReader.getColor(x, y)));
			}
		}
		return imagePixels;
	}

	/**
	 * Creates a copy of the given collection of pixels.
	 * 
	 * @param imagePixels
	 * @return copy of imagePixels
	 */
	private Collection<Pixel> copyPixels(Collection<Pixel> imagePixels) {
		Collection<Pixel> filteredImagePixels;
		filteredImagePixels = imagePixels.stream().map(p -> p).collect(Collectors.toCollection(ArrayList::new));
		return filteredImagePixels;
	}

}
