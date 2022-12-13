package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.apache.ibatis.session.SqlSession;
import pojos.Administrador;
import pojos.RespuestaLogin;
import pojos.Usuario;


@Path("acceso")
public class AccesoWS {

    @Context
    private UriInfo context;

    public AccesoWS() {
    }
    
    @Path("escritorio")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLogin  iniciarSesionEscritorio( @FormParam("correo")  String correo,
                                            @FormParam("password") String password   ){
        
        RespuestaLogin resp = new RespuestaLogin();

        Administrador administrador =  new Administrador();
        administrador.setCorreo(correo);
        administrador.setPassword(password);
                
        Administrador administradorResultado = null;
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if (conexionBD != null) {
            try{
                administradorResultado = conexionBD.selectOne("administrador.iniciarSesion",administrador);

                conexionBD.commit();
                if(administradorResultado != null){

                    resp.setError(false);
                    resp.setMensaje("Bienvenido ");
                    resp.setNombre(administradorResultado.getNombre());
                    resp.setApellidoPaterno(administradorResultado.getApellidoPaterno());
                    resp.setApellidoMaterno(administradorResultado.getApellidoMaterno());
                }else{
                    resp.setError(true);
                    resp.setMensaje("Error al iniciar sesión");
                }
            }catch(Exception e){
                resp.setError(true);
                resp.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            resp.setError(true);
            resp.setMensaje("Sin conexión al sistema");
        }        


        return resp;
    }

    @Path("movil")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLogin  iniciarSesionMovil( @FormParam("correo")  String correo,
                                            @FormParam("password") String password   ){
        
        RespuestaLogin resp = new RespuestaLogin();

        Usuario usuario =  new Usuario();
        usuario.setCorreo(correo);
        usuario.setPassword(password);
                
        Usuario usuarioResultado = null;
        
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if (conexionBD != null) {
            try{
                usuarioResultado = conexionBD.selectOne("usuario.iniciarSesion",usuario);

                conexionBD.commit();
                if(usuarioResultado != null){

                    resp.setError(false);
                    resp.setMensaje("Bienvenido ");
                    resp.setNombre(usuarioResultado.getNombre());
                    resp.setApellidoPaterno(usuarioResultado.getApellidoPaterno());
                    resp.setApellidoMaterno(usuarioResultado.getApellidoMaterno());
                }else{
                    resp.setError(true);
                    resp.setMensaje("Error al iniciar sesión");
                }
            }catch(Exception e){
                resp.setError(true);
                resp.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            resp.setError(true);
            resp.setMensaje("Sin conexión al sistema");
        }        


        return resp;
    }
        
}
