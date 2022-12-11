/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author jair1
 */
@Path("usuarios")
public class UsuarioWS {

    @Context
    private UriInfo context;

    public UsuarioWS() {
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrar(@FormParam("correo") String correo,
                            @FormParam("password") String password
                            ){
        Usuario usuarioRegistro = new Usuario();
        usuarioRegistro.setCorreo(correo);
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
    
    
    @Path("registrarComplemento")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarComplemento(@FormParam("correo") String correo,
                            @FormParam("nombre") String nombre,
                            @FormParam("apellidoPaterno") String apellidoPaterno,
                            @FormParam("apellidoMaterno") String apellidoMaterno,
                            @FormParam("telefono") Integer telefono,
                            @FormParam("direccion") String direccion,
                            @FormParam("fechaNacimiento") String fechaNaciento
                            ){
        Usuario usuarioRegistro = new Usuario();
        usuarioRegistro.setCorreo(correo);
        usuarioRegistro.setNombre(nombre);
        usuarioRegistro.setApellidoPaterno(apellidoPaterno);
        usuarioRegistro.setApellidoMaterno(apellidoMaterno);
        usuarioRegistro.setTelefono(telefono);
        usuarioRegistro.setDireccion(direccion);
        usuarioRegistro.setFechaNacimiento(fechaNaciento);
        
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conn = mybatis.MyBatisUtil.getSession();
        if(conn != null){
            try{
                int resultadoMapper = conn.insert("usuario.registrarComplemento", usuarioRegistro);
                conn.commit();
                if(resultadoMapper > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("El registro se ha completado correctamente...");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo completar el registro...");
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
    
    
}
