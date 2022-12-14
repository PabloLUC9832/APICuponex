package ws;

import java.util.HashMap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import pojos.Respuesta;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojos.Promocion;

@Path("promociones")
public class PromocionWS {

    @Context
    private UriInfo context;

    public PromocionWS() {
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Promocion> buscarDatos(){
        
        List<Promocion> empresas = null;
        SqlSession conn = mybatis.MyBatisUtil.getSession();
        if (conn != null) {
            try{
                empresas = conn.selectList("promocion.getAllPromociones");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return empresas;
    }      
    
    @Path("allIds")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Promocion> buscarPromocionesId(){
        
        List<Promocion> empresas = null;
        SqlSession conn = mybatis.MyBatisUtil.getSession();
        if (conn != null) {
            try{
                empresas = conn.selectList("promocion.getAllPromocionesid");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return empresas;
    }  
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrar(@FormParam("nombre") String nombre,
                            @FormParam("descripcion") String descripcion,
                            @FormParam("fechaInicio") String fechaInicio,
                            @FormParam("fechaTermino") String fechaTermino,
                            @FormParam("restricciones") String restricciones,
                            @FormParam("tipoPromocion") Integer tipoPromocion,
                            @FormParam("porcentaje") String porcentaje,
                            @FormParam("costoPromocion") float costoPromocion,
                            @FormParam("categoriaPromocion") Integer categoriaPromocion,
                            @FormParam("idEstatus") Integer idEstatus,
                            @FormParam("idSucursal") Integer idSucursal
                            ){
        
        Promocion promocionRegistro = new Promocion();
        promocionRegistro.setNombre(nombre);
        promocionRegistro.setDescripcion(descripcion);
        promocionRegistro.setFechaInicio(fechaInicio);
        promocionRegistro.setFechaTermino(fechaTermino);
        promocionRegistro.setRestricciones(restricciones);
        promocionRegistro.setTipoPromocion(tipoPromocion);
        promocionRegistro.setPorcentaje(porcentaje);
        promocionRegistro.setCostoPromocion(costoPromocion);
        promocionRegistro.setCategoriaPromocion(categoriaPromocion);
        promocionRegistro.setIdEstatus(idEstatus);
        promocionRegistro.setIdSucursal(idSucursal);
                                
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if (conexionBD != null) {
            try{
                int resultadoMapper = conexionBD.insert("promocion.registrar",promocionRegistro);
                conexionBD.commit();
                if(resultadoMapper > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Promoci??n registrada correctamente");
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
            respuestaWS.setMensaje("Sin conexi??n al sistema");
        }
        
        return respuestaWS;
    }    
    
    @Path("modificar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta modificar(@FormParam("idPromocion") int idPromocion,
                            @FormParam("nombre") String nombre,
                            @FormParam("descripcion") String descripcion,
                            @FormParam("restricciones") String restricciones,
                            @FormParam("tipoPromocion")Integer tipoPromocion,
                            @FormParam("porcentaje") String porcentaje,
                            @FormParam("costoPromocion") float costoPromocion,
                            @FormParam("categoriaPromocion") Integer categoriaPromocion,
                            @FormParam("idEstatus") Integer idEstatus,
                            @FormParam("idSucursal") Integer idSucursal
                            ){
        
        Promocion promocionRegistro = new Promocion();
        promocionRegistro.setIdPromocion(idPromocion);
        promocionRegistro.setNombre(nombre);
        promocionRegistro.setDescripcion(descripcion);
        promocionRegistro.setRestricciones(restricciones);
        promocionRegistro.setTipoPromocion(tipoPromocion);
        promocionRegistro.setTipoPromocion(tipoPromocion);
        promocionRegistro.setPorcentaje(porcentaje);
        promocionRegistro.setCostoPromocion(costoPromocion);
        promocionRegistro.setCategoriaPromocion(categoriaPromocion);
        promocionRegistro.setIdEstatus(idEstatus);
        promocionRegistro.setIdSucursal(idSucursal);
        
                
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if (conexionBD != null) {
            try{
                int resultadoMapper = conexionBD.insert("promocion.modificar",promocionRegistro);
                conexionBD.commit();
                if(resultadoMapper > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Promoci??n modificada correctamente");
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
            respuestaWS.setMensaje("Sin conexi??n al sistema");
        }

        return respuestaWS;
    }
    
    @Path("eliminar")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminar(@FormParam("idPromocion") int idPromocion){

        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if (conexionBD != null) {
            try{
                int resultadoMapper = conexionBD.update("promocion.eliminar",idPromocion);
                conexionBD.commit();
                if(resultadoMapper > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Promocion eliminada correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("El identificador de la promocion enviada, no existe.");
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("Sin conexi??n al sistema");
        }
        
        return respuestaWS;

    }
    
    @Path("byNombre/{nombre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Promocion> buscarById(@PathParam("nombre") String nombre ){
       
        List<Promocion> promociones = null;
        SqlSession conn = mybatis.MyBatisUtil.getSession();
        if (conn != null) {
            try {
                promociones = conn.selectList("promocion.getByNombreOFechaInicioOFechaTermino",nombre);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return promociones;
    }     
    
    @Path("subirImagen/{idPromocion}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta subirImagen ( @PathParam("idPromocion") Integer idPromocion ,
                                   byte[] fotoPromocion                              
                                 ){
        
        SqlSession conexionBD = MyBatisUtil.getSession();
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        if (conexionBD != null) {
            
            try{
                HashMap<String,Object> parametros = new HashMap<>();
                parametros.put("idPromocion", idPromocion);
                parametros.put("fotoPromocion", fotoPromocion);
                System.out.println(idPromocion);
                System.out.println(fotoPromocion);
                int filasAfectadas = conexionBD.update("promocion.subirfoto", parametros);
                
                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Foto de la promoci??n guardada correctamente");
                    conexionBD.commit();
                }else{
                    respuesta.setMensaje("Error al guardar la foto de la promoci??n");
                }
            }catch(Exception e){
                respuesta.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
            
            
        }else{
            respuesta.setMensaje("Por el momento no hay conexi??n para el guardado de la imagen...");
        }
        
        
        return respuesta;
    }
        
}
