package proyecto3;

public abstract class Nodo {

    Nodo next;
    Nodo prev;
        final int pos(){
        if (prev==null){
            return 1;
        }else{
            return prev.pos()+1;
        }
    }

    public Nodo() {
        prev = null;
        next = null;
    }

}