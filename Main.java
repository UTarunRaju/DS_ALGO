package Anjaneya;

public class Main {
    public static void main(String[] args) {
        DisjointSet DS= new DisjointSet(7);
        DS.union(1,3);
        System.out.println(DS.find(3));
        DS.union(2,4);
        DS.union(3,6);
        DS.union(1,4);
        System.out.println(DS.find(3));
        DS.union(1,5);
    }
}
