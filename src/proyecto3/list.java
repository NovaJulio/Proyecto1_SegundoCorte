package proyecto3;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
                if (pa.equals(p.name)) {
                    String name = p.name;
                    jCBtutor.addItem(makeObj(name));
                    p = (Tutor) p.next;
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
            JTextField m,
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
                Child info = new Child((Integer) s.getValue(), (Float) w.getValue(), a, i.getText(), n.getText(), m.getText());
                System.out.println("True");
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

    public Tutor getEndTutor() {
        if (fTutor == null) {
            return null;
        } else {
            Tutor p = fTutor;
            while (p.next != null) {
                p = (Tutor) p.next;
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
            fTutor = (Tutor) info;
        } else {
            Tutor ult = getEndTutor();
            ult.next = info;
            info.prev = ult;
        }
    }

    public Tutor TNE(int pos) {
        Tutor p = fTutor;
        int i = 1;
        while (p != null) {
            if (i == pos) {
                return p;
            } else {
                p = (Tutor) p.next;
                i++;
            }
        }
        return null;
    }

    public void addChildToEnd(
            JTextField i,
            JTextField n,
            JSpinner s,
            JSpinner w,
            JTextField m,
            JComboBox tn
    ) {
        Nodo p = fChild;
        Nodo info = CreateChild(i, n, s, w, m, search(tn.getSelectedItem().toString()));
        if (p == null) {
            p = info;
        } else {
            getEnd(p).next = info;
            info.prev = getEnd(p);
        }
    }

    public Child getPos(int b) {
        int i = 1;
        Child n = fChild;

        while (i > b) {
            n = (Child) n.next;
            i++;
        }
        return n;
    }

    public void addIn(
            JTextField i,
            JTextField n,
            JSpinner s,
            JSpinner w,
            JComboBox tn,
            JTextField m,
            int bpos
    ) {
        Nodo p = fChild;
        Nodo info = CreateChild(i, n, s, w, m, search(tn.getSelectedItem().toString()));
        if (p == null) {
            p = info;
        } else {
            Nodo sh = getPos(bpos);
            Nodo g = sh.prev;
            info.prev = g;
            info.next = sh;
            g.next = info;
            sh.prev = info;
        }
    }

    public void addCab(
            JTextField i,
            JTextField n,
            JSpinner s,
            JSpinner w,
            JTextField m,
            JComboBox tn) {
        Nodo p = fChild;
        Nodo info = CreateChild(i, n, s, w, m, search(tn.getSelectedItem().toString()));
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

    public void searchByTutorId(String pa, JComboBox jCBChild) {
        if (fChild == null) {
            JOptionPane.showMessageDialog(null, "No hay ningun niño en la lista");
        } else {
            Child p = (Child) getEnd(fChild);
            while (p != null) {
                if (p.Tutor.id.equals(pa)) {
                    String name = p.name;
                    jCBChild.addItem(makeObj(name));
                    p = (Child) p.prev;
                } else {
                    p = (Child) p.prev;
                }
            }
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

    public void DeleteChild(String i) {
        Child p = searchid(i);
        if (p == fChild) {
            fChild = (Child) fChild.next;
        } else if (getEnd(fChild) == p) {
            p.prev.next = null;
        } else {
            p.prev.next = p.next;
            p.next.prev = p.prev;
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

    public String getDatos(String i) {
        Child n = searchid(i);
        return "El peso del infante es: " + n.weight
                + " y su talla: " + n.size;
    }

    public void rowCreator(DefaultTableModel t, int fila, Child n) {
        t.setValueAt(n.id, fila, 0);
        t.setValueAt(n.name, fila, 1);
        t.setValueAt(n.Tutor.id, fila, 2);
        t.setValueAt(n.Tutor.name, fila, 3);
        t.setValueAt(n.weight, fila, 4);
        t.setValueAt(n.size, fila, 5);
    }

    public void rowCreator(DefaultTableModel t, int fila, Tutor n) {
        t.setValueAt(n.id, fila, 0);
        t.setValueAt(n.name, fila, 1);
    }

    public void fillTable(JTable t, boolean ToC) {
        int i = 0;
        DefaultTableModel m = new DefaultTableModel();
        if (ToC) {
            Child p = fChild;
            m.addColumn("Registro civil");
            m.addColumn("Nombre");
            m.addColumn("Identificacion del tutor");
            m.addColumn("Nombre del tutor");
            m.addColumn("Peso");
            m.addColumn("Estatura");
            m.addColumn("Municipio");
            while (p != null) {
                m.addRow(new Object[]{"", "", "", "", "", "", ""});
                rowCreator(m, i, p);
                p = (Child) p.next;
                i++;
            }
        } else {
            Tutor p = fTutor;
            m.addColumn("Identificacion");
            m.addColumn("Nombre");
            while (p != null) {
                m.addRow(new Object[]{"", ""});
                rowCreator(m, i, p);
                p = (Tutor) p.next;
                i++;
            }
            t.setModel(m);
        }
    }

    public void txt(String dir) throws FileNotFoundException, UnsupportedEncodingException {
        Child p = fChild;
        Tutor t = fTutor;
        String ruta = dir + "archivo.txt";

        File file = new File(ruta);
        try {
            file.createNewFile();
        } catch (IOException e) {

        }
        while (p != null) {
            PrintWriter writer = new PrintWriter(ruta, "UTF-8");
            if (isEmpty()) {
                writer.println("No Hay Nada En La Lista");
            }
            int i = 1;
            while (p != null) {

                writer.println(i);
                i++;
                p = (Child) p.next;
            }
            writer.close();
        }

    }
}
