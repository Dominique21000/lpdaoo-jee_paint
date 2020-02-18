package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Executable;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import junit.framework.TestCase;
import model.shapes.SSquare;

class SSquare_tst{



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

   
	// vérifier que l'affichage en texte du carré est correct.
	@Test
	void Given_DefaultSquare_When_toString_Then_ShouldReturnText(){
		SSquare s = new SSquare();
		assertEquals("Square : location=0|0 ; width=1 ; isSelected=false", s.toString());
	}

	// vérifier que l'impression du carré est correcte. // Print() prend l'affichage en texte et l'envoie sur le flux de sortie système (System.out)
	@Test
	void Given_DefaultSquare_When_print_Then_ShouldDisplayText(){
		SSquare s = new SSquare();
		s.print();
		assertEquals("Square : location=0|0 ; width=1 ; isSelected=false", outContent.toString().replace("\r\n", "\n"));
	}
	
	@Test
	void Given_DefaultSquareSelected_When_Print_Then_ShouldDisplayText(){
		SSquare s = new SSquare();
		s.select();
		s.print();
		assertEquals("Square : location=0|0 ; width=1 ; isSelected=true", outContent.toString().replace("\r\n", "\n"));
	}

	//vérifier le constructeur valué avec un point et une largeur
	@Test
	void Given_Nothing_When_CreatingSquareWithPointAndWidthAndPrint_Then_shouldDisplay() {
		SSquare s = new SSquare(new Point(100,10), 50);
		// vérifier que l'impression du rectangle est correcte.
		s.print();
		assertEquals("Square : location=100|10 ; width=50 ; isSelected=false", outContent.toString().replace("\r\n", "\n"));
	}
	
	//vérifier le constructeur valué avec deux points valides
		@Test
		void Given_Nothing_When_CreatingSquareWithTwoPointOkAndPrint_Then_shouldDisplay() {
			SSquare s;
			try {
				s = new SSquare(new Point(100,10), new Point(200,110));
				s.print();
				assertEquals("Square : location=100|10 ; width=100 ; isSelected=false", outContent.toString().replace("\r\n", "\n"));
			} catch (Exception e) {
				fail("should not throw exception");
			}			
		}
	
		//vérifier le constructeur valué avec deux points non valides OLD STYLE
		@Test
		void Given_Nothing_When_CreatingSquareWithTwoPointNonOkAndPrint_Then_shouldDisplay_OLDSYTLE() {
			SSquare s;
			try {
				s = new SSquare(new Point(100,10), new Point(200,50));
				s.print();
				fail("shoud have thrown exception");
			} catch (Exception e) {
	            assertEquals(e.getMessage(), "The two points does not form a square : width=100 height=40");
			}			
		}
		
		//vérifier le constructeur valué avec deux points non valides ASSERTTRHOWS style (most recent way)
		// c'est la meilleur approche. elle permet de tester correctement l'appel d'exception tout en validant le message et les parametres de l'exeption
		@Test
	    @DisplayName("assertThrowExeption when creating bad square")
	    @Tag("constructor-testing")
		void Given_Nothing_When_CreatingSquareWithTwoPointNonOkAndPrint_Then_shouldDisplay_ASSERTTRHOWS() {
			assertThrows(Exception.class, () -> new SSquare(new Point(100,10), new Point(200,50))); //short way
			
			//complete way
			Throwable throwable = assertThrows(Exception.class, () -> new SSquare(new Point(100,10), new Point(200,50)) );
			assertAll(
					() -> assertEquals("The two points does not form a square : width=100 height=40", throwable.getMessage()),
					() -> assertNull(throwable.getCause())
			);
	    }
		
		
	// vérifier que la requette de localisation est correcte. (la localisation est le point en haut à gauche du rectangle
	@Test
	void Given_defaultSquare_When_getLocation_Then_LocationOf0_0() {
		SSquare s = new SSquare();
		Point expectedLocation = new Point(0,0); 
		assertEquals(expectedLocation, s.getLoc());
	}
	
	@Test
	void Given_SquareFrom2Points_When_getLocation_Then_Location() {
		SSquare s;
		try {
			s = new SSquare(new Point(100,10), new Point(200, 110));
			Point expectedLocation = new Point(100,10); 
			assertEquals(expectedLocation, s.getLoc());
		} catch (Exception e) {
			fail("shoud not throw exception");
		}
	}
	
	//vérifier que la modification de localisation est correcte.
	@Test
	void Given_defaultSquare_When_setLocation_Then_printShouldBe() {
		SSquare s = new SSquare();
		Point expectedLocation = new Point(40,40);
		s.setLoc(expectedLocation);
		assertEquals("Square : location=40|40 ; width=1 ; isSelected=false", s.toString()); 
		}
	
	// vérifier que la requette de bordures est correcte.
	@Test
	void Given_DefaultSquare_When_GettingBounds_Then_shouldReturnBounds() {
		SSquare s = new SSquare();
		Rectangle bounds = s.getBounds();
		Point expectedBoundsLocation = new Point(0, 0);
		assertEquals(bounds.getLocation(), expectedBoundsLocation);
		assertEquals(1, (int)bounds.getWidth()); 
		assertEquals(1, (int)bounds.getHeight()); 
	}
	
	//vérifier les bordures avec un constructeur valué avec deux points
	@Test
	void Given_SquareFrom2Points_When_GettingBounds_Then_shouldReturnBounds() {
		SSquare s;
		try {
			s = new SSquare(new Point(100,10), new Point(200, 110));
			Rectangle bounds = s.getBounds();

			Point expectedBoundsLocation = new Point(100, 10);
			assertEquals(bounds.getLocation(), expectedBoundsLocation);
			assertEquals((int)bounds.getWidth(), 100); 
			assertEquals((int)bounds.getHeight(), 100); 
		} catch (Exception e) {
			fail("should not throw exception");
		}
		
	}
	
	
	//tester les translations
	@Test
	void Given_DefaultSquare_When_translating_Then_boundsshouldBe() {
		SSquare s = new SSquare();
		s.translate(20, 30);
		Rectangle bounds = s.getBounds();
		
		Point expectedBoundsLocation = new Point(20, 30); 
		assertEquals(bounds.getLocation(), expectedBoundsLocation);
		assertEquals((int)bounds.getWidth(), 1); // inchangée
		assertEquals((int)bounds.getHeight(), 1); // inchangée
	}
	
	
	@Test
	void Given_Square_When_translating_Then_boundsshouldBe() {
		SSquare s = new SSquare();
		s.translate(-20, -30);
		Rectangle bounds = s.getBounds();
		
		Point expectedBoundsLocation = new Point(-20, -30); //1+20  et 1+30
		assertEquals(expectedBoundsLocation, bounds.getLocation() );
		assertEquals(1, (int)bounds.getWidth()); // inchangée
		assertEquals(1, (int)bounds.getHeight()); // inchangée
	}
	
	@Test
	void Given_SquareWithPoints_When_translating_Then_boundsshouldBe() {
		SSquare s = new SSquare(new Point(22,33), 40);
		s.translate(-20, -30);
		Rectangle bounds = s.getBounds();
		
		Point expectedBoundsLocation = new Point(2, 3); //1+20  et 1+30
		assertEquals(expectedBoundsLocation, bounds.getLocation() );
		assertEquals(40, (int)bounds.getWidth()); // inchangée
		assertEquals(40, (int)bounds.getHeight()); // inchangée
	}

//*/
}