/*Radhika Banerjea 
 * February 14, 2021
 * Class that performs various functions of a rectangle (or two) , such as calculating its area, and perimeter
 * 
 */

//import so that the intersection method can be used.
import java.awt.Rectangle;

/**
 * Class that performs various functions of a rectangle (or two) , such as calculating its area, and perimeter
 * @author radhi
 * February 14, 2021
 */
public class Rectangles {
	//variables for width, height, x and y values of the top right corner, and x and y values of the bottom left corners of a rectangle.
	int left;
	int bottom;
	int width;
	int height;
	int right;
	int top;
	
	/**
	 * constructor takes any negative value and makes it equal to zero. then uses that values given to find the top right corner of the rectangle
	 * sets all the instance variables for the specific rectangle into the parameter given
	 * @param left x value of left bottom corner
	 * @param bottom y value of left bottom corner
	 * @param width width of the rectangle
	 * @param height height of the rectangle
	 */
	public Rectangles (int left, int bottom, int width, int height){
		this.left = left;
		this.bottom = bottom;
		this.width = width;
		this.height = height;
		topcorner();
		if (left<0) {
			this.left =0;
		}
		if (bottom<0) {
			this.bottom =0;
		}
		if (width<0) {
			this.width=0;
		}
		if (height<0) {
			this.height =0;
		}

	}
	
	/**
	 * constructor that sets all the values of the rectangle to 0
	 */
	public Rectangles() {
		left =0;
		bottom =0;
		width=0;
		height=0;
		right = 0;
		top = 0;
		
	}

	public static void main(String[] args) {
		Rectangles a = new Rectangles (1,2,10,20);
		System.out.println("the top corner coordinate is: (" + a.right + ","+a.top+")");
		Rectangles b = new Rectangles (1,2,10,20);	
		System.out.println("the top corner coordinate is: (" + b.right + ","+b.top+")");
		System.out.println(toString(a.left, a.bottom, a.width, a.height));
		System.out.println(toString(b.left, b.bottom, b.width, b.height));
		Rectangles hello = intersection(a, b);
		System.out.println(toString (hello.left, hello.bottom, hello.width, hello.height));
		System.out.println("the total perimeter is: " + totalPerimeter(a,b));
		
		System.out.println(a.contains(b));
	}
	/**
	 * method returns the information provided about the rectangle in one sentence/String
	 * @param left the x coordinate of the bottom left corner
	 * @param bottom the y coordinate of the bottom left corner
	 * @param width width of the rectangle
	 * @param height height of the rectangle
	 * @return returns the information given as a string
	 */
	public static String toString(int left, int bottom, int width, int height) {
		return "base: (" +left + "," + bottom + ") w:"+width+" h:"+height;
	}
	
	/**
	 * method that takes the height and with of a rectangle and multiplies them, giving the area of that rectangle
	 * @param width width of the rectangle
	 * @param height height of the rectangle
	 * @return the area of the rectangle
	 */
	public int area(int width, int height) {
		return width*height;
	}
	
	/**
	 * multiplies the width by two and the height by two then adds them together to give the perimeter of a given rectangle
	 * @param width width of the rectangle
	 * @param height height of the rectangle
	 * @return the perimeter of the rectangle
	 */
	public int perimeter(int width, int height) {
		return (2*width)+(2*height);
	}
	
	/**
	 *  a class method, intersection, that has two Rectangle parameters. The method should return the rectangle formed by the area common to the two rectangles (i.e., the rectangle formed where they overlap with each other). If they do not intersect, the method should return a rectangle where all fields are zero. If the rectangles only touch, but do not overlap, then the width or height should be zero, but all other parameters should be properly calculated and stored. 
	 * @param a the first rectangle
	 * @param b the second rectangle
	 * @return returns the rectangle formed by the overlapping area
	 */
	public static Rectangles intersection (Rectangles a,Rectangles b) {
		//if the rectangles overlap, then it converts the "Rectangles" into the type "Rectangle" so that the method called intersection is able to find the rectangle that is the result of the two overlapping areas
		//it then converts the overlapped Rectangle into the type "Rectangles" then returns that.
		if (overlap(a,b)) {
			Rectangle c = new Rectangle (a.left, a.bottom, a.width, a.height);
			Rectangle d = new Rectangle (b.left , b.bottom, b.width, b.height);
			Rectangle intersection = c.intersection(d);
			Rectangles returned = new Rectangles(intersection.x, intersection.y, intersection.width, intersection.height);
			return returned;
		}
		//if the two rectangles don't overlap then it goes to the constructor that sets all the values of the rectangle as 0.
		else {
			return new Rectangles();
		}
	}
	
	/**
	 * method that checks if two rectangles are on top of each other.
	 * @param b rectangle being checked if it is on top of the Rectangle originally called
	 * @return returns whether or not the rectangles are on top of each other.
	 */
	public boolean onTop(Rectangles b) {
		//if the width, the height, and the bottom left corner are the same, then the rectangles are on top of each other.
		if ((left==b.left)&&(bottom==b.bottom)&&(width==b.width)&&(height==b.height)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * an instance method, contains, that has one parameter of type Rectangle. The method should return true if every point of the specified rectangle (i.e., passed by the explicit parameter) is on or within the implicit parameter (i.e., the object invoking the instance method).
	It should return false otherwise. 
	 * @param b the rectangle being checked if it is in the original rectangle
	 * @return returns whether or not b is contained within the Rectangle being checked
	 */
	public boolean contains(Rectangles b) {
		//if the x and y coordinates of the bottom corner of b are greater than the x and y coordinates of the bottom corner of the original rectangle AND if the x and y coordinates of the top corner of b are less than the x and y coordinates of the top corner of the original rectangle, THEN then rectangle b is in the original rectangle
		if((b.left>=left)&&(b.bottom>=bottom)&&(b.right<=right)&&(b.top<=top)) {
			return true;
		}
		return false;
	}

	/**
	 * a class method, totalPerimeter, that has two Rectangle parameters. The method should return the total perimeter of the figure formed by the two rectangles. It should only count those portions that are on the edges of the exterior of the resulting figure. If one rectangle is completely contained by the other, then return the perimeter of the outer rectangle. If the rectangles do not intersect, the method should return the sum of the individual perimeters. 
	 * @param a first rectangle
	 * @param b second rectangle
	 * @return returns the total perimeter
	 */
	public static int totalPerimeter(Rectangles a, Rectangles b) {
		//if the rectangles are on top of each other, then return the perimeter of one of the rectangles
		if (a.onTop(b)) {
			return (a.perimeter(a.width,a.height));
		}
		//if the rectangles don't overlap then return sum of the perimeters of each rectangle
		if (!(overlap(a, b))) {
			return ((a.perimeter(a.width,a.height))+(b.perimeter(b.width,b.height)));
		}
		//otherwise, see where they intersect, then subtract the perimeter of the interected rectangle from the sum of the perimeters of each rectangle
		Rectangles perim = intersection (a,b);
		return ((a.perimeter(a.width, a.height))+(b.perimeter(b.width, b.height)))-(perim.perimeter(perim.width, perim.height));
	}

	/**
	 * given a rectangle, calculating the top right corner of it.
	 */
	public void topcorner() {	
		right =left+width;
		top=bottom + height;
	}
	
	/**
	 * method that checks if two rectangles overlap
	 * @param a the first rectangle
	 * @param b the second rectangle
	 * @return
	 */
	public static boolean overlap(Rectangles a,Rectangles b) {
		//the if statements check if the corners of the rectangles overlap each other.
		if (b.left>=a.right) {
			return false;
		}
		if (b.bottom>=a.top) {
			return false;
		}
		if (a.left>=b.right) {
			return false;
		}
		if (a.bottom>=b.top) {
			return false;
		}
		
		return true;
	}
	
	
}
