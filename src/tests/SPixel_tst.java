package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import model.shapes.SPixel;

class SPixel_tst {
	//objet qui sera utilisé pour récupérer le flux passant vers la console
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    //connection de outContent avec le flux de sortie (redirige System.out) 
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    //libérer le flux de sortie et remettre system.out
    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }
    
    
	// vérifier que l'affichage en texte du pisel est correct.
	@Test
	void Given_DefaultPixel_When_toString_Then_ShouldReturnText(){
		SPixel p = new SPixel();
		assertEquals("Pixel : 0|0 ; isSelected=false", p.toString());		
	}

	// vérifier que l'impression du pixel est correcte. // Print() prend l'affichage en texte et l'envoie sur le flux de sortie système (System.out)
	@Test
	void Given_DefaultPixel_When_print_Then_ShouldDisplayText(){
		SPixel p = new SPixel();
		p.print();
		assertEquals("Pixel : 0|0 ; isSelected=false", outContent.toString().replace("\r\n", "\n"));
	}
	
	@Test
	void Given_DefaultPixelSelected_When_Print_Then_ShouldDisplayText(){
		SPixel p = new SPixel();
		p.select();
		p.print();
		assertEquals("Pixel : 0|0 ; isSelected=true", outContent.toString().replace("\r\n", "\n"));
	}

	//vérifier le constructeur valué avec des coordonées 
	@Test
	void Given_Nothing_When_CreatingPixelWithCoordinatesAndPrint_Then_shouldDisplay() {
		SPixel p = new SPixel(100, 10);
		// vérifier que l'impression du rectangle est correcte.
		p.print();
		assertEquals("Pixel : 100|10 ; isSelected=false", outContent.toString().replace("\r\n", "\n"));
	}
	
	//vérifier le constructeur valué avec un point
	@Test
	void Given_Nothing_When_CreatingPixelWithPointAndPrint_Then_shouldDisplay() {
		SPixel p = new SPixel(new Point(100, 10));
		// vérifier que l'impression du rectangle est correcte.
		p.print();
		assertEquals("Pixel : 100|10 ; isSelected=false", outContent.toString().replace("\r\n", "\n"));
	}
	
	
	// vérifier que la requette de localisation est correcte. 
	@Test
	void Given_defaultPixel_When_getLocation_Then_LocationOf0_0() {
		SPixel p = new SPixel();
		Point expectedLocation = new Point(0,0); 
		assertEquals(expectedLocation, p.getLoc());
	}
	
	// vérifier que la requette de localisation est correcte. 
	@Test
	void Given_Pixel100_10_When_getLocation_Then_LocationOf0_0() {
		Point expectedLocation = new Point(100,10); 
		SPixel p = new SPixel(expectedLocation);
		assertEquals(expectedLocation, p.getLoc());
	}
	
	
	// vérifier le changement de localisation
	@Test
	void Given_defaultPixel_When_setLocation_Then_LocationOf() {
		Point expectedLocation = new Point(50,59); 
		SPixel p = new SPixel();
		
		p.setLoc(expectedLocation);
		
		assertEquals(expectedLocation, p.getLoc());
	}
	
	// vérifier que la requette de bordures est correcte. (bordure à la localisaiton du pixel taille 1_1)
	@Test
	void Given_DefaultPixel_When_GettingBounds_Then_shouldReturnBounds() {
		SPixel p = new SPixel();
		Rectangle bounds = p.getBounds();
		
		Point expectedBoundsLocation = new Point(0, 0);
		assertEquals(bounds.getLocation(), expectedBoundsLocation);
		assertEquals(1, (int)bounds.getWidth()); 
		assertEquals(1, (int)bounds.getHeight());
	}
	
	//vérifier les bordures avec un constructeur valué avec deux points
	@Test
	void Given_RectangleFrom2Points_When_GettingBounds_Then_shouldReturnBounds() {
		SPixel p = new SPixel(100, 10);
		Rectangle bounds = p.getBounds();
		
		Point expectedBoundsLocation = new Point(100, 10);
		assertEquals(bounds.getLocation(), expectedBoundsLocation);
		assertEquals(1, (int)bounds.getWidth()); 
		assertEquals(1, (int)bounds.getHeight());
	}
	
	
	//tester les translations
	@Test
	void Given_DefaulPixel_When_translatingAndPrint_Then_printedShouldBe() {
		SPixel p = new SPixel();
		p.translate(20, 30);
		assertEquals("Pixel : 20|30 ; isSelected=false", p.toString());
		p.translate(-5, 20);
		assertEquals("Pixel : 15|50 ; isSelected=false", p.toString());
		p.translate(85, -50);
		assertEquals("Pixel : 100|0 ; isSelected=false", p.toString());
	}
	

}
