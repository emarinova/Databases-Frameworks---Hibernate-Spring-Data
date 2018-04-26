package p05_IntersectionOfCircles;

public class Circle {

    public Circle(Point c, int r){
        this.center = c;
        this.radius = r;
    }

    private Point center;
    private int radius;

    public boolean intersect(Circle c2){
        double distance = Math.sqrt(Math.pow((this.center.getX()-c2.center.getX()),2) + Math.pow((this.center.getY()-c2.center.getY()),2));
        if (distance <= this.radius + c2.radius) return true;
        else return false;
    }
}
