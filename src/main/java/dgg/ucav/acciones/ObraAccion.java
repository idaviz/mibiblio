/*
 * 
 */
package dgg.ucav.acciones;

/**
 *
 * @author dgarcia25
 */
import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import dgg.ucav.dao.javabeans.Obra;
import dgg.ucav.dao.modelos.ModeloObraDAO;

/**
 * @author David
 */
@SuppressWarnings("serial")
public class ObraAccion extends ActionSupport implements Preparable, ModelDriven {

    private Obra obra;
    private List<Obra> listaObras;
    private int id_tb_obra;

    /**
     *
     * @throws Exception
     */
    @Override
    public void prepare() throws Exception {
        ModeloObraDAO modeloObraDAO = new ModeloObraDAO();
        // en creación, crear un nuevo objeto vacío 
        if (id_tb_obra == 0) {
            obra = new Obra();
        } // en modificación, devolver la información del objeto 
        else {
            obra = modeloObraDAO.getObra(id_tb_obra);
        }
    }

    /**
     * 
     * @return Objeto Obra
     */
    @Override
    public Object getModel() {
        return obra;
    }

    public int getId_tb_obra() {
        return id_tb_obra;
    }

    public void setId_tb_obra(int id_tb_obra) {
        this.id_tb_obra = id_tb_obra;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public List<Obra> getListaObras() {
        ModeloObraDAO ModeloObraDAO = new ModeloObraDAO();

        listaObras = (ArrayList<Obra>) ModeloObraDAO.getListaObras();
        return listaObras;
    }

    public void setListaObras(List<Obra> listaObras) {
        this.listaObras = listaObras;
    }

    // devolver la lista de clientes tras la recuperación 
    public String listado() {
        ModeloObraDAO ModeloObraDAO = new ModeloObraDAO();
        listaObras = (ArrayList<Obra>) ModeloObraDAO.getListaObras();
        return SUCCESS;
    }

    // Añadir, borrar y editar Obras
    // agregar el obra al modelo
    public String agregar() {

        if (this.obra.getIsbn().equals("")) {
            System.out.println("Error en el isbn");
            addFieldError("isbn", "ISBN no puede estar en blanco");
            //addActionError("Se han encontrado errores en el campo ISBN");
            return "input";
        } else {
            ModeloObraDAO ModeloObraDAO = new ModeloObraDAO();
            ModeloObraDAO.agregarObra(obra);
            addActionMessage("Obra añadida con éxito");
            return "success";
        }
    }

    // mostrar el formulario en edición
    public String editar() {
        return SUCCESS;
    }

    // modificar una obra
    public String modificar() {
        ModeloObraDAO ModeloObraDAO = new ModeloObraDAO();
        ModeloObraDAO.modificarObra(obra);
        return SUCCESS;
    }

    // eliminar una obra a partir del parámetro recibido llamado idObra
    public String eliminar() {
        ModeloObraDAO ModeloObraDAO = new ModeloObraDAO();
        ModeloObraDAO.eliminarObra(this.id_tb_obra);
        addActionMessage("Obra eliminada con éxito");
        return SUCCESS;
    }
            
}