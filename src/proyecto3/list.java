package proyecto3;

import java.awt.HeadlessException;
import javax.swing.*;

public class list {

    Child fChild;
    Tutor fTutor;

    private Object makeObj(final String item) {
        return new Object() {
            @Override
            public String toString() {
                return item;
            }
        };
    }

    public list() {
        fChild = null;
        fTutor = null;
    }

    public Child searchid(String pa) {
        if (fChild == null) {
            return null;
        } else {
            Child p = fChild;
            while (p != null) {
                if (p.id.equals(pa)) {
                    return p;
                } else {
                    p = (Child) p.next;
                }
            }
            return null;
        }
    }

    public Tutor search(String pa) {
        if (fTutor == null) {
            return null;
        } else {
            Tutor p = fTutor;
            while (p != null) {
                if (p.id.equals(pa)) {
                    return p;
                } else {
                    p = (Tutor) p.next;
                }
            }
            return null;
        }
    }

    public boolean isEmpty() {
        return fChild == null;
    }

    public void actJCBtutor(JComboBox jCBtutor, String pa) {
        jCBtutor.removeAllItems();
        if (fTutor != null) {
            Tutor p = fTutor;
            while (p != null) {
                if (p.name.startsWith(pa)) {
                    String name = p.name;
                    jCBtutor.addItem(makeObj(name));
                } else {
                    p = (Tutor) p.next;
                }
            }
        }
    }


    public int getlong() {
        int c = 0;
        Child p = null;
        if (fChild == null) {
            return 0;
        } else {
            p = fChild;
            while (p != null) {
                c++;
                p = (Child) p.next;
            }
            return c;
        }
    }

    public Tutor CreateTutor(
            JTextField i,
            JTextField n) {
        Tutor search = null;
        try {
            search = search(i.getText());
            if (search != null) {
                JOptionPane.showMessageDialog(null,
                        "Error: Esta identificacion ya se encuentra "
                        + "registrado.");
                i.setText("");
                i.requestFocus();
                return null;
            } else {
                Tutor info = new Tutor(n.getText(), i.getText());
                return info;
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "" + e);
            return null;
        }
    }

    public Child CreateChild(
            JTextField i,
            JTextField n,
            JSpinner s,
            JSpinner w,
            Tutor a) {
        Child buscar = null;
        try {
            buscar = searchid(i.getText());
            if (buscar != null) {
                JOptionPane.showMessageDialog(null,
                        "Error: Este registro civil ya se encuentra "
                        + "registrado.");
                i.setText("");
                i.requestFocus();
                return null;
            } else {
                Child info = new Child((Integer) s.getValue(), (Float) w.getValue(), a, i.getText(), n.getText());
                return info;
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "" + e);
            return null;
        }
    }

    public Nodo getEnd(Nodo cab) {
        if (cab == null) {
            return null;
        } else {
            Nodo p = cab;
            while (p.next != null) {
                p = p.next;
            }
            return p;
        }
    }

    public void addTutorToEnd(
            JTextField i,
            JTextField n
    ) {
        Nodo p = fTutor;
        Nodo info = CreateTutor(i, n);
        if (p == null) {
            p = info;
        } else {
            Nodo ult = getEnd(p);
            ult.next = info;
            info.prev = ult;
        }
    }

    public void addChildToEnd(
            JTextField i,
            JTextField n,
            JSpinner s,
            JSpinner w,
            JComboBox tn
    ) {
        Nodo p = fChild;
        Nodo info = CreateChild(i, n, s, w, search(tn.getSelectedItem().toString()));
        if (p == null) {
            p = info;
        } else {
            getEnd(p).next = info;
            info.prev = getEnd(p);
        }
    }

    public void addIn(
            JTextField i,
            JTextField n,
            JSpinner s,
            JSpinner w,
            JComboBox tn,
            JTextField bid
    ) {
        Nodo p = fChild;
        Nodo info = CreateChild(i, n, s, w, search(tn.getSelectedItem().toString()));
        if (p == null) {
            p = info;
        } else {
            Nodo sh = searchid(bid.getText());
            Nodo g = sh.next;
            sh.next = info;
            g.prev = info;
            info.prev = sh;
            info.next = g;
        }
    }

    public void addCab(
            JTextField i,
            JTextField n,
            JSpinner s,
            JSpinner w,
            JComboBox tn) {
        Nodo p = fChild;
        Nodo info = CreateChild(i, n, s, w, search(tn.getSelectedItem().toString()));
        if (p == null) {
            p = info;
        } else {
            Nodo ne = p.next;
            Nodo oCab = p;
            p = info;
            ne.prev = oCab;
            p.next = oCab;
            oCab.prev = p;
            oCab.next = ne;
        }
    }

    public Child searchTutorId(String pa) {
        if (fChild == null) {
            return null;
        } else {
            Child p = (Child) getEnd(fChild);
            while (p != null) {
                if (p.Tutor.id.equals(pa)) {
                    return p;
                } else {
                    p = (Child) p.prev;
                }
            }
            return null;
        }
    }

    public Child DeleteChild(Child i) {
        if (i == fChild) {
            fChild = (Child) i.next;
            return fChild;
        } else if (getEnd(fChild) == i) {
            i.prev.next = null;
            return null;
        } else {
            Nodo a = i.prev;
            Child b = (Child) i.next;
            a.next = b;
            b.prev = a;
            return b;
        }

    }
    public void DeleteChild(String i){
        Child p = searchid(i);
        if (p==fChild){
            fChild=(Child)fChild.next;
        }else if (getEnd(fChild)==p){
            p.prev.next=null;
        }else{
            p.prev.next=p.next;
            p.next.prev=p.prev;
        }
    }

    public int cant() {
        Child p = fChild;
        int c = fChild == null ? 0 : 1;
        while (p != null) {
            p = (Child) p.next;
            c++;
        }
        return c;
    }

    public int cant(Tutor n) {
        Child p = fChild;
        int c = 0;
        while (p != null) {
            if (p.Tutor == n) {
                c++;
                p = (Child) p.next;
            } else {
                p = (Child) p.next;
            }
        }
        return c;
    }

    @SuppressWarnings({"null", "IncompatibleEquals"})
    public void DeleteTutor(String i) {
        Child p = fChild;
        Tutor t = fTutor;
        while (p != null) {
            if (t.equals(i)) {
                break;
            } else {
                t = (Tutor) t.next;
            }
        }
        if (p == null) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado ningun tutor con esa id");
        } else {
            while (p != null) {
                if (t == p.Tutor) {
                    break;
                } else {
                    p = (Child) p.next;
                }
            }
            if (p == null) {
                if (t == getEnd(fTutor)) {
                    t.prev.next = null;
                } else if (t == fTutor) {
                    fTutor = (Tutor) fTutor.next;
                } else {
                    t.prev.next = t.next;
                    t.next.prev = t.prev;
                }
            } else {
                int option = JOptionPane.showOptionDialog(null, "Este tutor tiene:"
                        + " " + cant(t) + "\nal eliminarlo estos se eliminaran tambien\n"
                        + "¿Desea continuar?", "Advertencia",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, null, null);
                if (option == JOptionPane.OK_OPTION) {
                    p = fChild;
                    while (p != null) {
                        p = fChild;
                        while (p != null) {
                            if (p.Tutor == t) {
                                p = DeleteChild(p);
                            } else {
                                p = (Child) p.next;
                            }
                        }
                    }
                    if (t == getEnd(fTutor)) {
                        t.prev.next = null;
                    } else if (t == fTutor) {
                        fTutor = (Tutor) fTutor.next;
                    } else {
                        t.prev.next = t.next;
                        t.next.prev = t.prev;
                    }
                } else {
                }
            }
        }

    }
    
    public void getDatos(String i, JComboBox ToC, JComboBox SelectC){
        /*hola
        si, asi es para hacerlo mas largo*/
                
    }
}
