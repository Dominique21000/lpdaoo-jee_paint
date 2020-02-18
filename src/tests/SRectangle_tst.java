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
import model.shapes.SRectangle;

class SRectangle_tst{

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

   
	// vérifier que l'affichage en texte du rectangle est correct.
	@Test
	void Given_DefaultRectangle_When_toString_Then_ShouldReturnText(){
		SRectangle r = new SRectangle();
		assertEquals("Rectangle : startPoint=1|1 ; endPoint=3|5 ; isSelected=false", r.toString());
	}

	// vérifier que l'impression du rectangle est correcte. // Print() prend l'affichage en texte et l'envoie sur le flux de sortie système (System.out)
	@Test
	void Given_DefaultRectangle_When_print_Then_ShouldDisplayText(){
		SRectangle r = new SRectangle();
		r.print();
		assertEquals("Rectangle : startPoint=1|1 ; endPoint=3|5 ; isSelected=false", outContent.toString().replace("\r\n", "\n"));
	}
	
	void Given_DefaultRectangleSelected_When_Print_Then_ShouldDisplayText(){
		SRectangle r = new SRectangle();
		r.select();
		r.print();
		assertEquals("Rectangle : startPoint=1|1 ; endPoint=3|5 ; isSelected=true", outContent.toString().replace("\r\n", "\n"));
	}

	//vérifier le constructeur valué avec deux points
	@Test
	void Given_Nothing_When_CreatingRectangleWithTwoPointsAndPrint_Then_shouldDisplay() {
		SRectangle r = new SRectangle(new Point(100,10), new Point(130, 150));
		// vérifier que l'impression du rectangle est correcte.
		r.print();
		assertEquals("Rectangle : startPoint=100|10 ; endPoint=130|150 ; isSelected=false", outContent.toString().replace("\r\n", "\n"));
	}
	
	//vérifier le constructeur valué avec un points longueur largeur
		@Test
		void Given_Nothing_When_CreatingRectangleWithwidthHeightAndPrint_Then_shouldDisplay() {
			SRectangle r = new SRectangle(new Point(100,10), 50, 90);
			// vérifier que l'impression du rectangle est correcte.
			r.print();
			assertEquals("Rectangle : startPoint=100|10 ; endPoint=150|100 ; isSelected=false", outContent.toString().replace("\r\n", "\n"));
		}
	
	// vérifier que la requette de localisation est correcte. (la localisation est le point en haut à gauche du rectangle
	@Test
	void Given_defaultRectangle_When_getLocation_Then_LocationOf1_1() {
		SRectangle r = new SRectangle();
		Point expectedLocation = new Point(1,1); 
		assertEquals(expectedLocation, r.getLoc());
	}
	
	@Test
	void Given_RectangleFrom2Points_When_getLocation_Then_Location() {
		SRectangle r = new SRectangle(new Point(100,10), new Point(130, 150));
		Point expectedLocation = new Point(100,10); 
		assertEquals(expectedLocation, r.getLoc());
	}
	
	//vérifier que la modification de localisation est correcte.
	@Test
	void Given_defaultRectangle_When_setLocation_Then_printShouldBe() {
		SRectangle r = new SRectangle();
		Point expectedLocation = new Point(40,40);
		r.setLoc(expectedLocation);
		assertEquals("Rectangle : startPoint=40|40 ; endPoint=42|44 ; isSelected=false", r.toString()); //42 = 40+width = 40+ endX-startX= 40+ 3-1
		}
	
	// vérifier que la requette de bordures est correcte.
	@Test
	void Given_DefaultRectangle_When_GettingBounds_Then_shouldReturnBounds() {
		SRectangle r = new SRectangle();
		Rectangle bounds = r.getBounds();
		Point expectedBoundsLocation = new Point(1, 1);
		assertEquals(bounds.getLocation(), expectedBoundsLocation);
		assertEquals((int)bounds.getWidth(), 2); // width = endPoint.x-startPoint.x = 3-1
		assertEquals((int)bounds.getHeight(), 4); // height = endPoint.y-startPoint.y = 5-1
	}
	
	//vérifier les bordures avec un constructeur valué avec deux points
	@Test
	void Given_RectangleFrom2Points_When_GettingBounds_Then_shouldReturnBounds() {
		SRectangle r = new SRectangle(new Point(100,10), new Point(130, 150));
		Rectangle bounds = r.getBounds();

		Point expectedBoundsLocation = new Point(100, 10);
		assertEquals(bounds.getLocation(), expectedBoundsLocation);
		assertEquals((int)bounds.getWidth(), 30); // width = endPoint.x-startPoint.x = 130-100
		assertEquals((int)bounds.getHeight(), 140); // height = endPoint.y-startPoint.y = 150-10
	}
	
	
	//tester les translations
	@Test
	void Given_DefaultRectangle_When_translating_Then_boundsshouldBe() {
		SRectangle r = new SRectangle();
		r.translate(20, 30);
		Rectangle bounds = r.getBounds();
		
		Point expectedBoundsLocation = new Point(21, 31); //1+20  et 1+30
		assertEquals(bounds.getLocation(), expectedBoundsLocation);
		assertEquals((int)bounds.getWidth(), 2); // inchangée
		assertEquals((int)bounds.getHeight(), 4); // inchangée
	}
	
	
	@Test
	void Given_Rectangle_When_translating_Then_boundsshouldBe() {
		SRectangle r = new SRectangle();
		r.translate(-20, -30);
		Rectangle bounds = r.getBounds();
		
		Point expectedBoundsLocation = new Point(-19, -29); //1+20  et 1+30
		assertEquals(expectedBoundsLocation, bounds.getLocation() );
		assertEquals(2, (int)bounds.getWidth()); // inchangée
		assertEquals(4, (int)bounds.getHeight()); // inchangée
	}
}