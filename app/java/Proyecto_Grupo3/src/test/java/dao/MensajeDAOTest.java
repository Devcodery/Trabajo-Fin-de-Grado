package dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import conexionBBDD.ConexionBBDD;
import modelo.Mensaje;

public class MensajeDAOTest {

    private ConexionBBDD mockConexionBBDD;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private MensajeDAO mensajeDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConexionBBDD = mock(ConexionBBDD.class);
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        when(mockConexionBBDD.getConexion()).thenReturn(mockConnection);
        mensajeDAO = new MensajeDAO(mockConexionBBDD);
    }

    @Test
    @DisplayName("Éxito: Crear y guardar un nuevo mensaje en la base de datos")
    public void testCreate_Exito() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean resultado = mensajeDAO.create(1, 2, "Contenido del mensaje", "Asunto Test");

        assertTrue(resultado);
        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).setInt(2, 2);
        verify(mockPreparedStatement).setString(3, "Contenido del mensaje");
        verify(mockPreparedStatement).setString(4, "Asunto Test");
    }

    @Test
    @DisplayName("Excepción: Fallo al intentar crear un nuevo mensaje")
    public void testCreate_Exception() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Error en DB"));

        boolean resultado = mensajeDAO.create(1, 2, "Error", "Error");

        assertFalse(resultado);
    }

   @Test
    @DisplayName("Éxito: Leer los mensajes enviados por el usuario en una consulta")
    public void testReadMensajesUsuarioEnviar_Exito() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        
        when(mockResultSet.getInt("id_usuario")).thenReturn(2);
        when(mockResultSet.getInt("id_consulta")).thenReturn(1);
        when(mockResultSet.getString("asunto")).thenReturn("Test Envio");
        when(mockResultSet.getString("descripcion")).thenReturn("Hola, este es mi mensaje");

        ArrayList<Mensaje> resultado = mensajeDAO.readMensajesUsuarioEnviar(2, 1);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Test Envio", resultado.get(0).getAsunto());

        verify(mockPreparedStatement).setInt(1, 2);
        verify(mockPreparedStatement).setInt(2, 1);
    }

    @Test
    @DisplayName("Éxito: Leer los mensajes recibidos de otros usuarios en una consulta")
    public void testReadMensajesUsuarioRecibir_Exito() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        
        when(mockResultSet.getInt("id_usuario")).thenReturn(5);
        when(mockResultSet.getInt("id_consulta")).thenReturn(1);
        when(mockResultSet.getString("asunto")).thenReturn("Respuesta");
        when(mockResultSet.getString("descripcion")).thenReturn("Hola, te respondo tu duda");

        ArrayList<Mensaje> resultado = mensajeDAO.readMensajesUsuarioRecibir(2, 1);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Respuesta", resultado.get(0).getAsunto());
        
        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).setInt(2, 2); 
    }

    @Test
    @DisplayName("Éxito: Encontrar un mensaje específico mediante su ID")
    public void testReadMensaje_Encontrado() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        
        when(mockResultSet.getInt("id_usuario")).thenReturn(4);
        when(mockResultSet.getInt("id_consulta")).thenReturn(8);
        when(mockResultSet.getString("asunto")).thenReturn("Asunto Único");
        when(mockResultSet.getString("descripcion")).thenReturn("Cuerpo");

        Mensaje resultado = mensajeDAO.readMensaje(100);

        assertNotNull(resultado);
        assertEquals(4, resultado.getIdUsuario());
        assertEquals(8, resultado.getIdConsulta());
    }

    @Test
    @DisplayName("Fallo controlado: Buscar un mensaje con un ID inexistente")
    public void testReadMensaje_NoEncontrado() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        Mensaje resultado = mensajeDAO.readMensaje(999);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Éxito: Borrar todos los mensajes asociados a una consulta")
    public void testBorrarMensajesConsulta_Exito() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean resultado = mensajeDAO.borrarMensajesConsulta(10);

        assertTrue(resultado);
        verify(mockPreparedStatement).setInt(1, 10); 
    }

    @Test
    @DisplayName("Excepción: Fallo al intentar borrar los mensajes de una consulta")
    public void testBorrarMensajesConsulta_Exception() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Error al borrar en DB"));

        boolean resultado = mensajeDAO.borrarMensajesConsulta(10);

        assertFalse(resultado);
    }
}