package proyecto3;

public class Child extends Nodo{
    int size;
    float weight;
    Tutor Tutor;
    String name;
    String id;
    String Municipio;
    int Age;

    public Child(int size, float weight, Tutor Acudiente, String name, String id, String Municipio, int Age) {
        this.size = size;
        this.weight = weight;
        this.Tutor = Acudiente;
        this.name = name;
        this.id = id;
        this.Municipio = Municipio;
        this.Age = Age;
    }
}