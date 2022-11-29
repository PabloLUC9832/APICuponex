package ws;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.apache.ibatis.session.SqlSession;
import pojos.Respuesta;
import pojos.Sucursal;

@Path("sucursales")
public class SucursalWS {

    @Context
    private UriInfo context;

    public SucursalWS() {
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> buscarDatos(){
        
        List<Sucursal> sucursales = null;
        SqlSession conn = mybatis.MyBatisUtil.getSession();
        if (conn != null) {
            try{
                sucursales = conn.selectList("sucursal.getAllSucursales");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return sucursales;
    }       

    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrar(@FormParam("nombre") String nombre,
                            @FormParam("direccion") String direccion,
                            @FormParam("codigoPostal") Integer codigoPostal,
                            @FormParam("colonia") String colonia,
                            @FormParam("ciudad") String ciudad,
                            @FormParam("telefono") Integer telefono,
                            @FormParam("latitud") String latitud,
                            @FormParam("longitud") String longitud,
                            @FormParam("encargado") String encargado,
                            @FormParam("idEmpresa") Integer idEmpresa
                            ){

        
        Sucursal sucursalRegistro = new Sucursal();
        sucursalRegistro.setNombre(nombre);
        sucursalRegistro.setDireccion(direccion);
        sucursalRegistro.setCodigoPostal(codigoPostal);
        sucursalRegistro.setColonia(colonia);
        sucursalRegistro.setCiudad(ciudad);
        sucursalRegistro.setTelefono(telefono);
        sucursalRegistro.setLatitud(latitud);
        sucursalRegistro.setLongitud(longitud);
        sucursalRegistro.setEncargado(encargado);
        sucursalRegistro.setIdEmpresa(idEmpresa);
                        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if (conexionBD != null) {
            try{
                int resultadoMapper = conexionBD.insert("sucursal.registrar",sucursalRegistro);
                conexionBD.commit();
                if(resultadoMapper > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Sucursal registrada correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo guardar el registro enviado..");
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
    
    
    @Path("modificar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta modificar(@FormParam("idSucursal") int idSucursal,
                            @FormParam("nombre") String nombre,
                            @FormParam("direccion") String direccion,
                            @FormParam("codigoPostal") Integer codigoPostal,
                            @FormParam("colonia") String colonia,
                            @FormParam("ciudad") String ciudad,
                            @FormParam("telefono") Integer telefono,
                            @FormParam("latitud") String latitud,
                            @FormParam("longitud") String longitud,
                            @FormParam("encargado") String encargado,
                            @FormParam("idEmpresa") Integer idEmpresa
                            ){
        
        Sucursal  sucursalRegistro = new Sucursal();
        sucursalRegistro.setIdSucursal(idSucursal);
        sucursalRegistro.setNombre(nombre);
        sucursalRegistro.setDireccion(direccion);
        sucursalRegistro.setCodigoPostal(codigoPostal);
        sucursalRegistro.setColonia(colonia);
        sucursalRegistro.setCiudad(ciudad);
        sucursalRegistro.setTelefono(telefono);
        sucursalRegistro.setLatitud(latitud);
        sucursalRegistro.setLongitud(longitud);
        sucursalRegistro.setEncargado(encargado);        
        sucursalRegistro.setIdEmpresa(idEmpresa);
                
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if (conexionBD != null) {
            try{
                int resultadoMapper = conexionBD.insert("sucursal.modificar",sucursalRegistro);
                conexionBD.commit();
                if(resultadoMapper > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Sucursal modificada correctamente");
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
    
    @Path("eliminar")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminar(@FormParam("idSucursal") int idSucursal){

        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if (conexionBD != null) {
            try{
                int resultadoMapper = conexionBD.update("sucursal.eliminar",idSucursal);
                conexionBD.commit();
                if(resultadoMapper > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("La sucursal ha sido eliminada correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("El identificador de la sucursal enviado, no existe.");
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
    
    @Path("byNombre/{nombre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //public Sucursal buscarById(@PathParam("nombre") String nombre ){
    public List<Sucursal> buscarById(@PathParam("nombre") String nombre ){
        //Sucursal sucursalResultado = null;
        List<Sucursal> sucursales = null;
        SqlSession conn = mybatis.MyBatisUtil.getSession();
        if (conn != null) {
            try {
                //sucursalResultado = conn.selectOne("sucursal.getByNombre",nombre);
                sucursales = conn.selectList("sucursal.getByNombre",nombre);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return sucursales;
    }    
    



}
