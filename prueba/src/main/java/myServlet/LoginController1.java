
package myServlet;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;/***/
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;



@WebServlet(name = "LoginController1", urlPatterns = {"/LoginController1"})
public class LoginController1 extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           String accion;
       RequestDispatcher dispatcher=null;
       accion=request.getParameter("accion");
       String usu=request.getParameter("username");
       String cla=request.getParameter("password");
        Usuario oUsuario = new Usuario();            
          UsuarioDAO oUsuarioDAO= new UsuarioDAO(); 
       
        if ((accion==null)||accion.isEmpty())
       {
    
           dispatcher=request.getRequestDispatcher("login.jsp");
       } else if ("loguear".equals(accion)){
         
         
          
          oUsuario.setUsername(usu);
          oUsuario.setPassword(cla);
           try {
              
               if (oUsuarioDAO.loguearUsuario(oUsuario)){
                   dispatcher = request.getRequestDispatcher("index.jsp");
                   request.setAttribute("username", usu);
                   
                   
               }} catch(ClassNotFoundException ex) {
               Logger.getLogger(LoginController1.class.getName()).log(Level.SEVERE, null, ex);
           }
       }else if ("nuevo".equals(accion))
        {
          
            dispatcher = request.getRequestDispatcher("register.jsp");
          
            
        }else if ("insert".equals(accion)){
            
              
            String nombre_ = request.getParameter("txtNombre");
            String apellido_ = request.getParameter("txtApellido");
            String correo_ = request.getParameter("txtCorreo");
            String username_ = request.getParameter("txtUsername");
            String password_ = request.getParameter("txtPassword");
            
            Usuario nusuario = new Usuario(0,nombre_,apellido_,correo_,username_,password_);
            
            oUsuarioDAO.insertarUsuario(nusuario);
            
            dispatcher = request.getRequestDispatcher("login.jsp");
            
            
        }else if("listar".equals(accion)){
        
           dispatcher = request.getRequestDispatcher("listarUsuario_pag.jsp");
           List<Usuario> listaUsu = oUsuarioDAO.listarUsuario();
           request.setAttribute("lista", listaUsu);
        }
        else if ("eliminar".equals(accion)){
              int idUsuario=Integer.parseInt(request.getParameter("idUsu"));
              oUsuarioDAO.eliminarUsuario(idUsuario);
              dispatcher = request.getRequestDispatcher("listarUsuario_pag.jsp");  
              List<Usuario> listaUsu = oUsuarioDAO.listarUsuario();
              request.setAttribute("lista",listaUsu);
          }
        else if("modificar".equals(accion)){
              dispatcher = request.getRequestDispatcher("modificar.jsp");
              int id=Integer.parseInt(request.getParameter("idUsu"));
              Usuario usuario = oUsuarioDAO.mostrarUsuario(id);
              request.setAttribute("usuario", usuario );
              
          } else if ("update".equals(accion)){
           
            int id_ = Integer.parseInt(request.getParameter("id"));
            String nombre_ = request.getParameter("nombre");
            String apellido_ = request.getParameter("apellido");
            String correo_ = request.getParameter("correo");
            String username_ = request.getParameter("username");
            String password_ = request.getParameter("password");
            
            Usuario usuario = new Usuario(id_,nombre_,apellido_,correo_,username_,password_);
            
            oUsuarioDAO.modificarUsuario(usuario);
            dispatcher = request.getRequestDispatcher("listarUsuario_pag.jsp");
            List<Usuario> listaUsu = oUsuarioDAO.listarUsuario();
            request.setAttribute("lista",listaUsu);
            
          }
        else if ("insertar".equals(accion)){
            
              
           int id_ = Integer.parseInt(request.getParameter("id"));
            String nombre_ = request.getParameter("nombre");
            String apellido_ = request.getParameter("apellido");
            String correo_ = request.getParameter("correo");
            String username_ = request.getParameter("username");
            String password_ = request.getParameter("password");
            
            Usuario usuario = new Usuario(id_,nombre_,apellido_,correo_,username_,password_);

            
            oUsuarioDAO.insertarUsuario(usuario);
            dispatcher = request.getRequestDispatcher("listarUsuario_pag.jsp");
            List<Usuario> listaUsu = oUsuarioDAO.listarUsuario();
             request.setAttribute("lista", listaUsu);
        }else if("agregar".equals(accion)){
               dispatcher = request.getRequestDispatcher("nuevos.jsp");
        }else if("buscar".equals(accion)){
              String textoBuscar=request.getParameter("txtBuscar");
              List<Usuario>listaUsu = oUsuarioDAO.buscarUsuario(textoBuscar);
              dispatcher = request.getRequestDispatcher("listarUsuario_pag.jsp");
              request.setAttribute("lista",listaUsu);
          }else{
              dispatcher = request.getRequestDispatcher("listarUsuario_pag.jsp");
              List<Usuario>listaUsu = oUsuarioDAO.listarUsuario();
              request.setAttribute("lista",listaUsu);
          }
       dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          doGet(request,response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
