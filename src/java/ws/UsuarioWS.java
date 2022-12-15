package ws;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.apache.ibatis.session.SqlSession;
import pojos.Respuesta;
import pojos.Usuario;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import mybatis.MyBatisUtil;
import pojos.Promocion;
import pojos.RespuestaUsuario;

@Path("usuarios")
public class UsuarioWS {

    @Context
    private UriInfo context;

    public UsuarioWS() {
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> buscarDatos(){
        
        List<Usuario> usuarios = null;
        SqlSession conn = mybatis.MyBatisUtil.getSession();
        if (conn != null) {
            try{
                usuarios = conn.selectList("usuario.getAllUsuarios");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return usuarios;
    }     
        
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrar( @FormParam("nombre") String nombre,
                                @FormParam("apellidoPaterno") String apellidoPaterno,
                                @FormParam("apellidoMaterno") String apellidoMaterno,
                                @FormParam("telefono") String telefono,
                                @FormParam("correo") String correo,
                                @FormParam("direccion") String direccion,
                                @FormParam("fechaNacimiento") String fechaNacimiento,
                                @FormParam("password") String password
                            ){
        Usuario usuarioRegistro = new Usuario();
        usuarioRegistro.setNombre(nombre);
        usuarioRegistro.setApellidoPaterno(apellidoPaterno);
        usuarioRegistro.setApellidoMaterno(apellidoMaterno);
        usuarioRegistro.setTelefono(telefono);
        usuarioRegistro.setCorreo(correo);
        usuarioRegistro.setDireccion(direccion);
        usuarioRegistro.setFechaNacimiento(fechaNacimiento);
        usuarioRegistro.setPassword(password);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conn = mybatis.MyBatisUtil.getSession();
        if(conn != null){
            try{
                int resultadoMapper = conn.insert("usuario.registrar", usuarioRegistro);
                conn.commit();
                if(resultadoMapper > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Cuenta creada correctamente...");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo crear la cuenta...");
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje(e.getMessage());
            }finally{
                conn.close();
            }
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("Sin conexión con el sistema...");
        }
        return respuestaWS;
    }
    
    @Path("obtenerUsuario")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaUsuario  iniciarSesionEscritorio( @FormParam("correo")  String correo){
        
        RespuestaUsuario resp = new RespuestaUsuario();

        Usuario usuario =  new Usuario();
        usuario.setCorreo(correo);
                
        Usuario usuarioResultado = null;
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if (conexionBD != null) {
            try{
                usuarioResultado = conexionBD.selectOne("usuario.obtenerUsuario",usuario);

                conexionBD.commit();
                if(usuarioResultado != null){

                    resp.setError(false);
                    resp.setMensaje("Bienvenido ");
                    resp.setIdUsuario(usuarioResultado.getIdUsuario());
                    resp.setNombre(usuarioResultado.getNombre());
                    resp.setApellidoPaterno(usuarioResultado.getApellidoPaterno());
                    resp.setApellidoMaterno(usuarioResultado.getApellidoMaterno());
                    resp.setTelefono(usuarioResultado.getTelefono());
                    resp.setDireccion(usuarioResultado.getDireccion());
                    resp.setFechaNacimiento(usuarioResultado.getFechaNacimiento());
                    resp.setPassword(usuarioResultado.getPassword());                    
                }else{
                    resp.setError(true);
                    resp.setMensaje("Error al obtener datos");
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

    @Path("modificar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta modificar(@FormParam("idUsuario") int idUsuario,
                            @FormParam("nombre") String nombre,
                            @FormParam("apellidoPaterno") String apellidoPaterno,
                            @FormParam("apellidoMaterno") String apellidoMaterno,
                            @FormParam("telefono") String telefono,
                            @FormParam("direccion") String direccion,
                            @FormParam("fechaNacimiento") String fechaNacimiento,
                            @FormParam("password") String password
                            ){

        Usuario usuarioRegistro = new Usuario();
        usuarioRegistro.setIdUsuario(idUsuario);
        usuarioRegistro.setNombre(nombre);
        usuarioRegistro.setApellidoPaterno(apellidoPaterno);
        usuarioRegistro.setApellidoMaterno(apellidoMaterno);
        usuarioRegistro.setTelefono(telefono);
        usuarioRegistro.setDireccion(direccion);
        usuarioRegistro.setFechaNacimiento(fechaNacimiento);
        usuarioRegistro.setPassword(password);

        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if (conexionBD != null) {
            try{
                int resultadoMapper = conexionBD.insert("usuario.modificar",usuarioRegistro);
                conexionBD.commit();
                if(resultadoMapper > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Usuario modificado correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo modificar el registro enviado..");
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("Sin conexión al sistema");
        }

        return respuestaWS;
    }
    
    @Path("bycategoria/{categoriaPromocion}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Promocion> getCatalogoiD( @PathParam("categoriaPromocion") Integer categoriaPromocion ){
        
        List<Promocion> catalogos = null;        
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        
        if(conexionBD != null){
            try{
                catalogos = conexionBD.selectList("usuario.byCategoria",categoriaPromocion);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        
        return catalogos;
    }
    
    @Path("obtenerFoto/{idPromocion}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Promocion obtenerFotoPromocion( @PathParam("idPromocion") Integer idPromocion  ){
        Promocion promocion = new Promocion();
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if(conexionBD != null){
            try{
                promocion = conexionBD.selectOne("usuario.obtenerFoto",idPromocion);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
                
        return promocion;
    }

}
