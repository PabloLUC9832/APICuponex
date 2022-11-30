package ws;

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
import pojos.Empresa;
import pojos.Respuesta;

@Path("empresas")
public class EmpresaWS {

    @Context
    private UriInfo context;

    public EmpresaWS() {
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empresa> buscarDatos(){
        
        List<Empresa> empresas = null;
        SqlSession conn = mybatis.MyBatisUtil.getSession();
        if (conn != null) {
            try{
                empresas = conn.selectList("empresa.getAllEmpresas");
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
                            @FormParam("nombreComercial") String nombreComercial,
                            @FormParam("nombreRepresentanteLegal") String nombreRepresentanteLegal,
                            @FormParam("correo") String correo,
                            @FormParam("direccion") String direccion,
                            @FormParam("codigoPostal") Integer codigoPostal,
                            @FormParam("ciudad") String ciudad,
                            @FormParam("telefono") Integer telefono,
                            @FormParam("paginaWeb") String paginaWeb,
                            @FormParam("rfc") String rfc,
                            @FormParam("idEstatus") Integer idEstatus
                            ){

        Empresa empresaRegistro = new Empresa();
        empresaRegistro.setNombre(nombre);
        empresaRegistro.setNombreComercial(nombreComercial);
        empresaRegistro.setNombreRepresentanteLegal(nombreRepresentanteLegal);
        empresaRegistro.setCorreo(correo);
        empresaRegistro.setDireccion(direccion);
        empresaRegistro.setCodigoPostal(codigoPostal);
        empresaRegistro.setCiudad(ciudad);
        empresaRegistro.setTelefono(telefono);
        empresaRegistro.setPaginaWeb(paginaWeb);
        empresaRegistro.setRfc(rfc);
        empresaRegistro.setIdEstatus(idEstatus);


        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if (conexionBD != null) {
            try{
                int resultadoMapper = conexionBD.insert("empresa.registrar",empresaRegistro);
                conexionBD.commit();
                if(resultadoMapper > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Empresa registrada correctamente");
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
    public Respuesta modificar(@FormParam("idEmpresa") int idEmpresa,
                            @FormParam("nombre") String nombre,
                            @FormParam("nombreComercial") String nombreComercial,
                            @FormParam("nombreRepresentanteLegal") String nombreRepresentanteLegal,
                            @FormParam("correo") String correo,
                            @FormParam("direccion") String direccion,
                            @FormParam("codigoPostal") Integer codigoPostal,
                            @FormParam("ciudad") String ciudad,
                            @FormParam("telefono") Integer telefono,
                            @FormParam("paginaWeb") String paginaWeb,
                            @FormParam("idEstatus") Integer idEstatus
                            ){

        Empresa empresaRegistro = new Empresa();
        empresaRegistro.setIdEmpresa(idEmpresa);
        empresaRegistro.setNombre(nombre);
        empresaRegistro.setNombreComercial(nombreComercial);
        empresaRegistro.setNombreRepresentanteLegal(nombreRepresentanteLegal);
        empresaRegistro.setCorreo(correo);
        empresaRegistro.setDireccion(direccion);
        empresaRegistro.setCodigoPostal(codigoPostal);
        empresaRegistro.setCiudad(ciudad);
        empresaRegistro.setTelefono(telefono);
        empresaRegistro.setPaginaWeb(paginaWeb);
        empresaRegistro.setIdEstatus(idEstatus);
                
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if (conexionBD != null) {
            try{
                int resultadoMapper = conexionBD.insert("empresa.modificar",empresaRegistro);
                conexionBD.commit();
                if(resultadoMapper > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Empresa modificada correctamente");
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
    public Respuesta eliminar(@FormParam("idEmpresa") int idEmpresa){

        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if (conexionBD != null) {
            try{
                int resultadoMapper = conexionBD.update("empresa.eliminar",idEmpresa);
                conexionBD.commit();
                if(resultadoMapper > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Empresa eliminada correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("El identificador de la empresa enviado, no existe.");
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
    public List<Empresa> buscarById(@PathParam("nombre") String nombre ){
       
        List<Empresa> empresas = null;
        SqlSession conn = mybatis.MyBatisUtil.getSession();
        if (conn != null) {
            try {
                empresas = conn.selectList("empresa.getByNombreORFCORepresentante",nombre);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return empresas;
    }    
                
}
