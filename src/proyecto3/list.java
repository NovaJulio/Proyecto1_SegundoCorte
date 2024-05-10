package proyecto3;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class list {

    Child fChild;
    Tutor fTutor;
    long fA = System.currentTimeMillis();

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

    public Tutor search(int pa) {
        if (fTutor == null) {
            return null;
        } else {
            Tutor p = fTutor;
            while (p != null) {
                int idA = Integer.parseInt(p.id);
                if (idA == pa) {
                    return p;
                } else {
                    p = (Tutor) p.next;
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
                if (p.name.equals(pa)) {
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

    public void actJCBtutor(JComboBox jCBtutor) {
        jCBtutor.removeAllItems();
        jCBtutor.addItem("Acudientes");
        Tutor p = fTutor;
        while (p != null) {
            jCBtutor.addItem(p.id);
            p = (Tutor) p.next;
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
            search = search(Integer.parseInt(i.getText()));
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
            JComboBox m,
            JSlider l,
            Tutor a) {
        Child buscar = null;
        if (m.getSelectedItem().toString().equals("Municipios")) {
            JOptionPane.showMessageDialog(null, "Elija un municipio", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (i.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el registro civil", "Error", JOptionPane.ERROR_MESSAGE);
            i.requestFocus();
            return null;
        }
        if (n.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre", "Error", JOptionPane.ERROR_MESSAGE);
            n.requestFocus();
            return null;
        }
        if (a == null) {
            JOptionPane.showMessageDialog(null, "Elija un tutor valido", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
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
                Child info = new Child((Integer) s.getValue(), (Float) w.getValue(), a, n.getText(), i.getText(), m.getSelectedItem().toString(), l.getValue());
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
        while (p != null) {
            if (pos == p.pos()) {
                return p;
            } else {
                p = (Tutor) p.next;
            }
        }
        return null;
    }

    public void addChildToEnd(
            JTextField i,
            JTextField n,
            JSpinner s,
            JSpinner w,
            JComboBox m,
            JSlider l,
            JComboBox tn
    ) {
        Child p = fChild;
        Child info = CreateChild(i, n, s, w, m, l, search(Integer.parseInt(tn.getSelectedItem().toString())));
        if (info != null) {
            if (p == null) {
                fChild = info;
            } else {
                Child ult = (Child) getEnd(p);
                ult.next = info;
                info.prev = ult;
            }
            System.out.println("" + info.name);
        }
    }

    public Child getPos(int b) {

        Child n = fChild;

        while (b != n.pos()) {
            n = (Child) n.next;
        }
        return n;
    }

    public void addIn(
            JTextField i,
            JTextField n,
            JSpinner s,
            JSpinner w,
            JComboBox tn,
            JComboBox m,
            JSlider l,
            int bpos
    ) {
        Child info = CreateChild(i, n, s, w, m, l, search(Integer.parseInt(tn.getSelectedItem().toString())));
        Child sh = getPos(bpos);
        Child g = (Child) sh.next;
        if (info != null) {
            if (isEmpty()) {
                fChild = info;
            } else if (sh == fChild && g == null) {
                fChild.next = info;
                info.prev = fChild;
            } else if (sh == fChild && g != null) {
                fChild.next = info;
                g.prev = info;
                info.next = g;
                info.prev = fChild;
            } else if (sh == getEnd(fChild)) {
                sh.next = info;
                info.prev = sh;
            } else {
                sh.next = info;
                g.prev = info;
                info.prev = sh;
                info.next = g;
            }
        }
    }

    public void addCab(
            JTextField i,
            JTextField n,
            JSpinner s,
            JSpinner w,
            JComboBox m,
            JSlider l,
            JComboBox tn) {
        int t = 0;

        try {
            t = Integer.parseInt(tn.getSelectedItem().toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Seleccione un acudiente valido");
            return;
        }
        Child info = CreateChild(i, n, s, w, m, l, search(t));
        if (info != null) {
            if (isEmpty()) {
                fChild = info;
            } else {
                Child oc = fChild;
                fChild = info;
                fChild.next = oc;
                oc.prev = fChild;
            }
        }
    }

    public void searchByTutorId(String pa, JComboBox jCBChild, JPanel a) {
        if (fChild == null) {
            JOptionPane.showMessageDialog(null, "No hay ningun niño en la lista");
        } else {
            Tutor b = search(Integer.parseInt(pa));
            Child p = (Child) getEnd(fChild);
            if (cant(b) == 1) {
                while (p != null) {
                    if (p.Tutor.id.equals(pa)) {
                        JOptionPane.showMessageDialog(null, "El peso del infante es: " + p.weight
                                + " y su talla: " + p.size);
                        break;
                    } else {
                        p = (Child) p.prev;
                    }
                }
            } else {
                jCBChild.removeAllItems();
                jCBChild.addItem(makeObj("Infantes"));
                a.setVisible(true);
                while (p != null) {
                    if (p.Tutor.id.equals(pa)) {
                        String Id = p.id;
                        jCBChild.addItem(makeObj(Id));
                        p = (Child) p.prev;
                    } else {
                        p = (Child) p.prev;
                    }
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
        if (p == fChild && fChild.next != null) {
            fChild = (Child) fChild.next;
        } else if (p == fChild) {
            fChild = null;
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
            if (t.id.equals(i)) {
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
                    if (t == fTutor && fTutor.next != null) {
                        fTutor = (Tutor) fTutor.next;
                    } else if (t == fTutor) {
                        fTutor = null;
                    } else if (t == getEnd(fTutor)) {
                        t.prev.next = null;
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
        t.setValueAt(n.Age, fila, 2);
        t.setValueAt(n.Tutor.id, fila, 3);
        t.setValueAt(n.Tutor.name, fila, 4);
        t.setValueAt(n.weight, fila, 5);
        t.setValueAt(n.size, fila, 6);
        t.setValueAt(n.Municipio, fila, 7);
    }

    public void rowCreator(DefaultTableModel t, int fila, Tutor n) {
        t.setValueAt(n.id, fila, 0);
        t.setValueAt(n.name, fila, 1);
    }

    public int fillTable(JTable t, boolean ToC) {
        int i = 0;
        DefaultTableModel m = new DefaultTableModel();
        if (ToC) {
            if (isEmpty()) {
                m.addColumn("Registro civil");
                m.addColumn("Nombre");
                m.addColumn("Edad");
                m.addColumn("Id del tutor");
                m.addColumn("Nombre del tutor");
                m.addColumn("Peso");
                m.addColumn("Estatura");
                m.addColumn("Municipio");
                t.setModel(m);
                return 0;
            }
            Child p = fChild;
            m.addColumn("Registro civil");
            m.addColumn("Nombre");
            m.addColumn("Edad");
            m.addColumn("Id del tutor");
            m.addColumn("Nombre del tutor");
            m.addColumn("Peso");
            m.addColumn("Estatura");
            m.addColumn("Municipio");
            while (p != null) {
                m.addRow(new Object[]{"", "", "", "", "", "", "", ""});
                rowCreator(m, i, p);
                p = (Child) p.next;
                i++;
                t.setModel(m);
            }
            return 1;
        } else {
            if (fTutor == null) {
                m.addColumn("Identificacion");
                m.addColumn("Nombre");
                t.setModel(m);
                return 0;
            }
            Tutor p = fTutor;
            m.addColumn("Identificacion");
            m.addColumn("Nombre");
            while (p != null) {
                m.addRow(new Object[]{"", ""});
                rowCreator(m, i, p);
                p = (Tutor) p.next;
                i++;
                t.setModel(m);
            }
            return 2;

        }

    }

    public void txt() throws FileNotFoundException, UnsupportedEncodingException {
        Child p = fChild;
        Tutor t = fTutor;
        String user = System.getProperty("user.home");
        String ruta = user + "\\Documents\\Registro" + fA + ".txt";

        File file = new File(ruta);
        try {
            file.createNewFile();
        } catch (IOException e) {

        }
        try (PrintWriter writer = new PrintWriter(ruta, "UTF-8")) {
            if (isEmpty()) {
                writer.println("No Hay Nada En La Lista");
                return;
            }
            writer.println("Registro de infantes: ");
            int i = 1;
            while (p != null) {
                writer.println("Infante N°" + i + "{");
                writer.println("Registro civil: " + p.id);
                writer.println("Nombre: " + p.name);
                writer.println("Edad: " + p.Age);
                writer.println("Altura: " + p.size);
                writer.println("Peso: " + p.weight);
                writer.println("Id del acudiente: " + p.Tutor.id);
                writer.println("Nombre del acudiente" + p.Tutor.name);
                writer.println("Municipio: " + p.Municipio + "}\n");
                p = (Child) p.next;
                i++;
            }
            writer.println("\nRegistro de acudientes");
            while (t != null) {
                writer.println("Identificacion: " + t.id);
                writer.println("Nombre: " + t.name);
                writer.println("Tiene " + cant(t) + " infantes vinculados\n");
                t = (Tutor) t.next;
            }
        }

    }

    public int lowSizeByCity(String m) {
        Child p = fChild;
        int c = 0;
        while (p != null) {
            if (4 <= p.Age && p.Age <= 6 && p.size < 100 && p.Municipio.equals(m)) {
                c++;
            }
            p = (Child) p.next;
        }
        return c;
    }

    public void reportLowWeight(String m, JTextArea o) {
        Child p = fChild;
        int c = 0;
        o.append("Informacion de los infantes de " + m + ":\n");
        while (p != null) {
            if (p.Age >= 2 && p.Age <= 3 && p.weight <= 15 && p.Municipio.equals(m)) {
                o.append("Registro civil: " + p.id + "\n");
                o.append("Nombre: " + p.name + "\n");
                o.append("Tutor: " + p.Tutor.name + "\n");
                o.append("Municipio: " + p.Municipio + "\n");
                o.append("Peso: " + p.weight + "\n\n");
                c++;
            }
            p = (Child) p.next;
        }
        o.append("Son un total de: " + c + " en el municipio\n\n");
    }

    public void reportAll(String m, JTextArea o) {
        Child p = fChild;
        int c = 0;
        o.append("Informacion de los infantes de " + m + ":\n");
        while (p != null) {
            if (p.Municipio.equals(m)) {
                c++;
                o.append("Registro civil: " + p.id + "\n");
                o.append("Nombre: " + p.name + "\n");
                o.append("Edad: " + p.Age + "\n");
                o.append("Altura: " + p.size + "\n");
                o.append("Peso: " + p.weight + "\n");
                o.append("Id del acudiente: " + p.Tutor.id + "\n");
                o.append("Nombre del acudiente" + p.Tutor.name + "\n");
                o.append("Municipio: " + p.Municipio + "\n\n");

            }
            p = (Child) p.next;
        }
        o.append("Son un total de: " + c + " en el municipio\n\n");
    }

}
