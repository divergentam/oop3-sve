package rs.ac.ni.oop3.tamara333.vezbe_3_3;

import java.util.*;

public class Main {

    private static final int SCALE = 10;

    public static void main(String[] args) {
     final int argCount = args.length;
     final List<Figure> figures;

         if(argCount >= 2){
             final int m = Integer.parseInt(args[0]);
             final int n = Integer.parseInt(args[1]);

             figures = generateFigures(m, n);
         }
         else if(argCount == 1){
             final int n = Integer.parseInt(args[0]);
             figures = generateFigures(n, n);
         }
         else{
             // Pitamo korisniko koliko krugova, koliko pravouganika.
            final Scanner in = new Scanner(System.in);
            int m = in.nextInt();
            int n  = in.nextInt();
            in.close();

             figures = generateFigures(m, n);
         }
/*
         Collections.sort(figures);
*/
         figures.sort(new Comparator<Figure>() {
                 @Override
                 public int compare(Figure figure1, Figure figure2) {
                     return Double.compare(figure1.circumference(), figure2.circumference());
                 }
             }
         );

       //  figures.sort((o1, o2) -> Double.compare(o1.circumference(), o2.circumference()));

         printDetails(figures);

         System.out.println("****************************************************************************************************************");
         System.out.println("****************************************************************************************************************");
         System.out.println("****************************************************************************************************************");
         System.out.println("****************************************************************************************************************");
         System.out.println("****************************************************************************************************************");

         Collections.shuffle(figures);

         printDetails(figures);

    }

    private static void printDetails(List<Figure> figures) {
        for(final Figure figure : figures){
            System.out.println(figure.getLabel() + ": " + figure);
            System.out.println("area: " + figure.area());
            System.out.println("circumference: " + figure.circumference());
            System.out.println("________________________________________________________________________________________________________________");

        }
    }

    private static List<Figure> generateFigures(int numberOfCircles, int numberOfRectangles){
        final List<Figure> figures = new ArrayList<>();
        final Random random = new Random();

        for(int i = 0; i < numberOfCircles; i++){
            final String label = "Circle_" + (i + 1);
            final double radius = random.nextDouble() * SCALE;

            figures.add(Circle.builder().label(label).r(radius).build());
        }

        for(int i = 0; i < numberOfRectangles; i++){
            final String label = "Rectangle_" + (i + 1);
            final double a = random.nextDouble() * SCALE;
            final double b = random.nextDouble() * SCALE;

            figures.add(Rectangle.builder().label(label).a(a).b(b).build());
        }
        return figures;
    }
}

 /*  final Figure circle = new Circle("K1", 10);
        final Figure rectangle = new Rectangle("R1", 10, 10);
        final Figure square = new Square("Sq1", 10);

        final List<Figure> figures = new ArrayList<>();
        figures.add(circle);
        figures.add(rectangle);
        figures.add(square);

        for (Figure figure : figures) {
            System.out.println(figure.getLabel() + ": " + figure);
        }

        System.out.println("Broj argumenta: " + args.length);

        for(String arg : args){
            System.out.println("argument: " + arg);
        }*/


//System.out.println(square.equals(rectangle));
//System.out.println(rectangle.equals(square));

       /* final Figure a = new Figure("test") {
            @Override
            public double area() {
                return 100;
            }

            @Override
            public double circumference() {
                return 20;
            }
        };

        System.out.println("Povrsina kruga je : " + c.area());
        System.out.println("Povrsina pravougaonika je : " + r.area());
        System.out.println("Povrsina neke figure je : " + a.area());*/