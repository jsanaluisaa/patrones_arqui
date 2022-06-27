package test;
import dao.DaoInquilinos;
import dao.impl.DaoInquilinosImpl;
import dto.Inquilinos;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
public class TestDaoInquilinos {
    public static void main(String[] args) throws ParseException {
        Scanner scanner;
        DaoInquilinos daoInquilinos = new DaoInquilinosImpl();
        Inquilinos inquilino;
        Integer opt;
        do {
            scanner = new Scanner(System.in);
            inicio();
            opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    List<Inquilinos> list = daoInquilinos.inquilinosSel();
                    if (list != null) {
                        try {
                            list.forEach((t) -> {
                                System.out.println(t.getIdinquilinos().toString() + " "
                                        + t.getDni() + " "
                                        + t.getNombres() + " "
                                        + t.getPaterno() + " "
                                        + t.getMaterno() + " "
                                        + t.getTelefono() + " "
                                        + t.getCorreo() + " "
                                        + t.getDeuda().toString() + " "
                                        + t.getFecha_ingreso().toString());
                            });
                        } catch (Exception e) {
                            System.out.println(daoInquilinos.getMessage());
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Lista vacía");
                    }
                    break;
                case 2:
                    inquilino = daoInquilinos.inquilinosGet(1);
                    if (inquilino != null) {
                        try {
                            System.out.println(inquilino.getIdinquilinos().toString() + " "
                                    + inquilino.getDni() + " "
                                    + inquilino.getNombres() + " "
                                    + inquilino.getPaterno() + " "
                                    + inquilino.getMaterno() + " "
                                    + inquilino.getTelefono() + " "
                                    + inquilino.getCorreo() + " "
                                    + inquilino.getDeuda().toString() + " "
                                    + inquilino.getFecha_ingreso().toString());
                        } catch (Exception e) {
                            System.out.println(daoInquilinos.getMessage());
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Usuario no encontrado");
                    }
                    break;
                case 3:
                    inquilino = new Inquilinos();
                    inquilino.setDni("71005052");
                    inquilino.setNombres("ROBERT");
                    inquilino.setPaterno("JONES");
                    inquilino.setMaterno("SMITH");
                    inquilino.setTelefono("997777822");
                    inquilino.setCorreo("robert@mail.com");
                    inquilino.setDeuda(0d);
                    inquilino.setFecha_ingreso(LocalDate.parse("2010-12-18"));
                    try {
                        daoInquilinos.inquilinosIns(inquilino);
                    } catch (Exception e) {
                        System.out.println(daoInquilinos.getMessage());
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    inquilino = new Inquilinos();
                    inquilino.setIdinquilinos(5);
                    inquilino.setDni("79905052");
                    inquilino.setNombres("PAUL");
                    inquilino.setPaterno("WAYNE");
                    inquilino.setMaterno("DIAZ");
                    inquilino.setTelefono("998484822");
                    inquilino.setCorreo("paul@mail.com");
                    inquilino.setDeuda(0d);
                    inquilino.setFecha_ingreso(LocalDate.parse("2012-12-28"));
                    try {
                        daoInquilinos.inquilinosUpd(inquilino);
                    } catch (Exception e) {
                        System.out.println(daoInquilinos.getMessage());
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    List<Integer> del = new ArrayList<>();
                    del.add(4);
                    del.add(3);
                    try {
                        daoInquilinos.inquilinosDel(del);
                    } catch (Exception e) {
                        System.out.println(daoInquilinos.getMessage());
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (opt != 6);
    }
    private static void inicio() {
        System.out.println("--------Claves de las prueba-----");
        System.out.println("1: Lista de usuarios UsuariosSel");
        System.out.println("2: Obtener usuario UsuariosGet");
        System.out.println("3: Insertar usuario UsuariosUpd");
        System.out.println("4: Actualizar usuario UsuariosIns");
        System.out.println("5: Eliminar usuario UsuariosDel");
        System.out.println("6: Salir");
        System.out.println("-----------------------------------");
        System.out.print("\nIngrese la clave: ");
    }
}
