package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import model.shapes.SCircle;
import model.shapes.SConstruction;
import model.shapes.SPixel;
import model.shapes.SSquare;

class SConstruction_tst {

	//objet qui sera utilisé pour récupérer le flux passant vers la console
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final SConstruction builtConstruct = new SConstruction();
    private final SConstruction defaultConstruct = new SConstruction();


    //connection de outContent avec le flux de sortie (redirige System.out) 
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        
        // prepare construction
        SSquare s = new SSquare(new Point(20,30), 50);
		SSquare s2 = new SSquare(new Point(2,40), 5);
		SCircle c = new SCircle(new Point(100, 100), 10);
		SPixel p = new SPixel(new Point(30,50));
		
		builtConstruct.addShape(s);
		builtConstruct.addShape(s2);
		builtConstruct.addShape(c);
		builtConstruct.addShape(p);
    }

    //libérer le flux de sortie et remettre system.out
    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

   
	// vérifier que l'affichage en texte est correct.
	@Test
	void Given_DefaultConstruct_When_toString_Then_ShouldReturnText(){
		assertEquals("Assemblage : isSelected=false", defaultConstruct.toString());
	}
	
	@Test
	void Given_BuiltConstruct_When_toString_Then_ShouldReturnText(){
        
		SSquare s = new SSquare(new Point(20,30), 50);
		SSquare s2 = new SSquare(new Point(2,3), 5);
		SCircle c = new SCircle(new Point(100, 100), 10);
		SPixel p = new SPixel(new Point());
		
		SConstruction manualConstruct = new SConstruction();
		manualConstruct = new SConstruction();
		manualConstruct.addShape(s);
		manualConstruct.addShape(s2);
		manualConstruct.addShape(c);
		manualConstruct.addShape(p);
		
		StringBuffer sb = new StringBuffer();
		sb.append("Assemblage : isSelected=false");
		sb.append("\n\t").append(s);
		sb.append("\n\t").append(s2);
		sb.append("\n\t").append(c);
		sb.append("\n\t").append(p);
		assertEquals(sb.toString(), manualConstruct.toString());
	}

	// vérifier que l'impression est correcte. // Print() prend l'affichage en texte et l'envoie sur le flux de sortie système (System.out)
	@Test
	void Given_DefaultConstruct_When_print_Then_ShouldDisplayText(){
		defaultConstruct.print();
		assertEquals("Assemblage : isSelected=false", outContent.toString().replace("\r\n", "\n"));
	}
	
	@Test
	void Given_DefaultConstructSelected_When_Print_Then_ShouldDisplayText(){
		defaultConstruct.select();
		defaultConstruct.print();
		assertEquals("Assemblage : isSelected=true", outContent.toString().replace("\r\n", "\n"));
	}	
		
	// vérifier que la requette de localisation est correcte. (la localisation est le point en haut à gauche du rectangle
	@Test
	void Given_defaultConstruct_When_getLocation_Then_LocationOf0_0() {
		Point expectedLocation = new Point(0,0); 
		assertEquals(expectedLocation, defaultConstruct.getLoc());
	}
	
	@Test
	void Given_builtConstruct_When_getLocation_Then_Location() {
		Point expectedLocation = new Point(2,30); //location of s2.x and s.y  
		assertEquals(expectedLocation, builtConstruct.getLoc());
	}
	
	//vérifier que la modification de localisation est correcte.
	@Test
	void Given_defaultConstruction_When_setLocation_Then_printShouldBe() {
		Point expectedLocation = new Point(); // la localisation d'une construction vide sera toujours 0.0
		defaultConstruct.setLoc(expectedLocation);
		assertEquals(expectedLocation, defaultConstruct.getLoc()); 
	}
	
		@Test
		void Given_builtConstruction_When_setLocation_Then_printShouldBe() {
			Point expectedLocation = new Point(10, 438); 
			builtConstruct.setLoc(expectedLocation);
			assertEquals(expectedLocation, builtConstruct.getLoc()); 
		}
	
	// vérifier que la requette de bordures est correcte.
	@Test
	void Given_DefaultSquare_When_GettingBounds_Then_shouldReturnBounds() {
		Rectangle bounds = defaultConstruct.getBounds();  //localisation 0 taille 0
		Point expectedBoundsLocation = new Point(0, 0);
		assertEquals(bounds.getLocation(), expectedBoundsLocation);
		assertEquals(0, (int)bounds.getWidth()); 
		assertEquals(0, (int)bounds.getHeight()); 
	}
	
	//vérifier les bordures avec un constructeur valué avec deux points
	@Test
	void Given_builtConstruct_When_GettingBounds_Then_shouldReturnBounds() {
		Rectangle bounds = builtConstruct.getBounds();

		Point expectedBoundsLocation = new Point(2, 30);
		assertEquals(bounds.getLocation(), expectedBoundsLocation);
		assertEquals(110, (int)bounds.getMaxX()); 
		assertEquals(110, (int)bounds.getMaxY()); 
		assertEquals(108, (int)bounds.getWidth()); 
		assertEquals(80, (int)bounds.getHeight());			
	}
	
	
	//tester les translations
	@Test
	void Given_DefaultConstruct_When_translating_Then_boundsshouldBe() {
		defaultConstruct.translate(20, 30); 
		Rectangle bounds = defaultConstruct.getBounds();  //localisation 0 taille 0
		Point expectedBoundsLocation = new Point(0, 0);
		assertEquals(bounds.getLocation(), expectedBoundsLocation);
		assertEquals(0, (int)bounds.getWidth()); 
		assertEquals(0, (int)bounds.getHeight()); 
	}
	
	
	@Test
	void Given_BuildConstruct_When_translating_Then_boundsshouldBe() {
		builtConstruct.translate(20, 30); 
		
		Rectangle bounds = builtConstruct.getBounds();

		Point expectedBoundsLocation = new Point(2+20, 30+30);
		assertEquals(bounds.getLocation(), expectedBoundsLocation);
		assertEquals(110+20, (int)bounds.getMaxX()); 
		assertEquals(110+30, (int)bounds.getMaxY()); 
		assertEquals(108, (int)bounds.getWidth()); 
		assertEquals(80, (int)bounds.getHeight());	
	}
	
	@Test
	void Given_BuildConstruct_When_translatingNegative_Then_boundsshouldBe() {
		builtConstruct.translate(-20, -30); 
		
		Rectangle bounds = builtConstruct.getBounds();

		Point expectedBoundsLocation = new Point(2-20, 30-30);
		assertEquals(bounds.getLocation(), expectedBoundsLocation);
		assertEquals(110-20, (int)bounds.getMaxX()); 
		assertEquals(110-30, (int)bounds.getMaxY()); 
		assertEquals(108, (int)bounds.getWidth()); 
		assertEquals(80, (int)bounds.getHeight());	
	}
	
}
	